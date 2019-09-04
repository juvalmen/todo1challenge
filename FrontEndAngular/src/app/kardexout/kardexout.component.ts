import { Component, OnInit, ViewChild } from "@angular/core";
import { MessageService, ConfirmationService } from "primeng/api";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import { first } from "rxjs/operators";

import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { Kardexout } from "src/interfaces/kardexout.interface";
import { KardexoutService } from "src/services/kardexout/kardexout.service";
import { InputMask } from "primeng/inputmask";
import { Producto } from "src/interfaces/producto.interface";
import { Persona } from "src/interfaces/persona.interface";
import { ProductoService } from "src/services/producto/producto.service";
import { PersonaService } from "src/services/persona/persona.service";
import { Kardexoutdetail } from "src/interfaces/kardexoutdetail.interface";

@Component({
   selector: "app-kardexout",
   templateUrl: "./kardexout.component.html",
   styleUrls: ["./kardexout.component.css"],
   providers: [MessageService, ConfirmationService]
})
export class KardexoutAdminComponent implements OnInit {
   @ViewChild("myInputMaskCcv") myInputMaskCcv: InputMask;

   formProduct: FormGroup;
   dataTableCols: any[];
   attrCcv: any;

   products: Producto[] = [];
   //Autocomplete
   keywordProduct: string;

   persons: Persona[] = [];
   //Autocomplete
   keywordPerson: string;

   kardexOutDetails: Kardexoutdetail[];

   withStock: string = "1";

   lastSecuence = 0;

   productEdit: Kardexoutdetail; 

   constructor(
      public messageService: MessageService,
      private _fb: FormBuilder,
      private kardexoutService: KardexoutService,
      private productService: ProductoService,
      private personService: PersonaService,
      private confirmationService: ConfirmationService
   ) {
      console.log("constructor");
      this.initForm();
      this.initComponents();
   }

   initForm() {
      this.formProduct = this._fb.group({
         idkardexout: [null],
         creationdate: [null],
         sequential: [null, Validators.required],
         persona: [null, Validators.required],
         producto: [null],
         stockmovement: [null],
         description: [null, Validators.maxLength(250)]
      });
   }

   ngOnInit() {
      this.dataTableCols = [
         { field: "productname", header: "Producto" },
         { field: "stockmovement", header: "Cantidad de salida" }
      ];
   }

   initComponents() {
      this.kardexOutDetails = [];
      this.attrCcv = { attributeValue: "" };
      this.productEdit = null;
      this.getSequential();
   }

   isInputMaskFilledCcv(event) {
      if (!this.myInputMaskCcv.filled) {
         this.attrCcv = { attributeValue: "" };
      }
   }

   searchProduct(event) {
      this.productService
         .getProductsByFilter(this.keywordProduct.toUpperCase(), this.withStock)
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined) {
                  if (data.statusCode == "200") {
                     this.products = data.responseBody;
                  } else {
                     this.addMessage("Error", data.responseMessage, "error");
                  }
               } else {
                  this.addMessage(
                     "Error",
                     "Error interno en la aplicación. Contacte con el administrador.",
                     "error"
                  );
               }
            },
            error => {
               this.addMessage("Error", error, "error");
            }
         );
   }

   searchPerson(event) {
      this.personService
         .getPersonsByFilter(this.keywordPerson.toUpperCase())
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined) {
                  if (data.statusCode == "200") {
                     this.persons = data.responseBody;
                  } else {
                     this.addMessage("Error", data.responseMessage, "error");
                  }
               } else {
                  this.addMessage(
                     "Error",
                     "Error interno en la aplicación. Contacte con el administrador.",
                     "error"
                  );
               }
            },
            error => {
               this.addMessage("Error", error, "error");
            }
         );
   }

   addMessage(title: string, message: string, type: string) {
      this.clearMessage();
      this.messageService.add({
         severity: type,
         summary: title,
         detail: message
      });
   }

   clearMessage() {
      this.messageService.clear();
   }

   getSequential() {
      this.kardexoutService
         .getLastSequence()
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined) {
                  if (data.statusCode == "200") {
                     this.lastSecuence = data.responseBody;   
                     this.formProduct.controls["sequential"].setValue(this.lastSecuence);
                  } else {
                     this.addMessage("Error", data.responseMessage, "error");
                  }
               } else {
                  this.addMessage(
                     "Error",
                     "Error interno en la aplicación. Contacte con el administrador.",
                     "error"
                  );
               }
            },
            error => {
               this.addMessage("Error", error, "error");
            }
         );
   }

   agregarProducto() {
      let product = this.formProduct.value.producto;

      if (product == null || product.idproduct == null) {
         this.addMessage("", "Se debe de ingresar el producto.", "warn");
         return;
      }

      let productName = this.formProduct.value.producto.name;

      let stockmovement = this.formProduct.value.stockmovement;
      if (stockmovement == null || stockmovement < 0) {
         this.addMessage(
            "",
            "El campo cantidad no debe ser nulo o negativo.",
            "warn"
         );
         return;
      }

      var productoExist = false;
  
      if (this.productEdit == null) {
         this.kardexOutDetails.forEach(element => {
            if (element.idproduct == product.idproduct) {
               productoExist = true;
               return;
            }
         });
      } else if(this.productEdit.idproduct != product.idproduct){ 
         this.kardexOutDetails.forEach(element => {
            if (element.idproduct == product.idproduct) {
               productoExist = true;
               return;
            }
         });
      }
    
      if (productoExist) { 
         this.addMessage(
            "",
            "El producto que intenta ingresar ya se encuentra en el movimiento.",
            "warn"
         );
         return;
      }

      this.kardexOutDetails = this.kardexOutDetails.filter(obj => obj !== this.productEdit);

      const kardexOutDetails: Kardexoutdetail = {
         idkardexout: null,
         quantity: null,
         idproduct: product.idproduct,
         productname: productName,
         stockmovement: stockmovement,
         creationdate: new Date()
      };
 
      console.info(kardexOutDetails);

      this.kardexOutDetails.push(kardexOutDetails);

      this.formProduct.controls["producto"].setValue(null);
      this.formProduct.controls["stockmovement"].setValue(null);

   }

   validarAntesDeGrabar() {
      if (this.kardexOutDetails.length == 0) {
         this.addMessage(
            "",
            "Los movimientos deben de tener al menos un producto.",
            "warn"
         );
         return;
      }
 
      let description = this.formProduct.value.description;
      if (description == null || description.length == 0) {
         this.addMessage("", "El campo descripción es obligatorio.", "warn");
         return;
      }

      let person = this.formProduct.value.persona;
      console.info(person);
      if (person == null || person.idperson == null) {
         this.addMessage("", "Se debe de ingresar la persona a cargo.", "warn");
         return;
      }
      
      let sequential = this.formProduct.value.sequential;
      if (sequential == null) {
         this.addMessage("", "Se debe de ingresar la secuencia.", "warn");
         return;
      }

      const kardexout: Kardexout = {
         idkardexout: this.formProduct.value.idkardexout,
         creationdate: this.formProduct.value.creationdate,
         description: this.formProduct.value.description,
         sequential: this.formProduct.value.sequential,
         idperson: person.idperson,
         kardexOutDetails: this.kardexOutDetails
      };

      // Save or update
      console.log("kardexout", kardexout);
      if (kardexout.idkardexout == null) {
         this.saveKardexout(kardexout);
      }
   }

   saveKardexout(kardexout: Kardexout) {
      this.kardexoutService
         .saveKardex(kardexout)
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined && data != null) {
                  if (data.statusCode == "200") {
                     this.addMessage(
                        "",
                        "Se ha registrado la salida satisfactoriamente.",
                        "info"
                     );
                     this.clearForm();
                  } else {
                     this.addMessage("", data.responseMessage, "warn");
                  }
               } else {
                  this.addMessage(
                     "",
                     "Error interno en la aplicación. Contacte con el administrador.",
                     "error"
                  );
               }
            },
            error => {
               this.addMessage(
                  "",
                  "Error interno en la aplicación. Contacte con el administrador." +
                     error,
                  "error"
               );
            }
         );
   }

   clearForm() {
      this.initComponents();

      this.formProduct = this._fb.group({
         idkardexout: [null],
         creationdate: [null],
         sequential: [null, Validators.required],
         persona: [null, Validators.required],
         producto: [null],
         stockmovement: [null],
         description: [null, Validators.maxLength(250)]
      });

      console.info(this.formProduct);
   }

   deleteConfirm(kardexoutdetail: Kardexoutdetail) {
      this.confirmationService.confirm({
         message: " Estas seguro de eliminar este producto del movimiento ?",
         accept: () => {
            this.deleteProduct(kardexoutdetail);
         }
      });
   }

   deleteProduct(kardexoutdetail: Kardexoutdetail) { 
      this.kardexOutDetails = this.kardexOutDetails.filter(obj => obj !== kardexoutdetail);
   }

   onEditKardex(kardexoutdetail: Kardexoutdetail) {

      let product: Producto = {
         idproduct: kardexoutdetail.idproduct,
         name: kardexoutdetail.productname
      }

      this.formProduct.controls["producto"].setValue(product);
      this.formProduct.controls["stockmovement"].setValue(kardexoutdetail.stockmovement);

      this.productEdit = kardexoutdetail;

   }
}
