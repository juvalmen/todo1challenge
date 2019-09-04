import { Component, OnInit, ViewChild } from "@angular/core";
import { MessageService, ConfirmationService } from "primeng/api";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import { first } from "rxjs/operators";

import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { Kardexin } from "src/interfaces/kardexin.interface";
import { KardexinService } from "src/services/kardexin/kardexin.service";
import { InputMask } from "primeng/inputmask";
import { Producto } from "src/interfaces/producto.interface";
import { Persona } from "src/interfaces/persona.interface";
import { ProductoService } from "src/services/producto/producto.service";
import { PersonaService } from "src/services/persona/persona.service";
import { Kardexindetail } from "src/interfaces/kardexindetail.interface";

@Component({
   selector: "app-kardexin",
   templateUrl: "./kardexin.component.html",
   styleUrls: ["./kardexin.component.css"],
   providers: [MessageService, ConfirmationService]
})
export class KardexinAdminComponent implements OnInit {
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

   kardexInDetails: Kardexindetail[];

   withStock: string = "0";

   lastSecuence = 0;

   productEdit: Kardexindetail; 

   constructor(
      public messageService: MessageService,
      private _fb: FormBuilder,
      private kardexinService: KardexinService,
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
         idkardexin: [null],
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
         { field: "stockmovement", header: "Cantidad de entrada" }
      ];
   }

   initComponents() {
      this.kardexInDetails = [];
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
      this.kardexinService
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
         this.kardexInDetails.forEach(element => {
            if (element.idproduct == product.idproduct) {
               productoExist = true;
               return;
            }
         });
      } else if(this.productEdit.idproduct != product.idproduct){ 
         this.kardexInDetails.forEach(element => {
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

      this.kardexInDetails = this.kardexInDetails.filter(obj => obj !== this.productEdit);

      const kardexInDetails: Kardexindetail = {
         idkardexin: null,
         quantity: null,
         idproduct: product.idproduct,
         productname: productName,
         stockmovement: stockmovement,
         creationdate: new Date()
      };
 
      console.info(kardexInDetails);

      this.kardexInDetails.push(kardexInDetails);

      this.formProduct.controls["producto"].setValue(null);
      this.formProduct.controls["stockmovement"].setValue(null);

   }

   validarAntesDeGrabar() {
      if (this.kardexInDetails.length == 0) {
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

      const kardexin: Kardexin = {
         idkardexin: this.formProduct.value.idkardexin,
         creationdate: this.formProduct.value.creationdate,
         description: this.formProduct.value.description,
         sequential: this.formProduct.value.sequential,
         idperson: person.idperson,
         kardexInDetails: this.kardexInDetails
      };

      // Save or update
      console.log("kardexin", kardexin);
      if (kardexin.idkardexin == null) {
         this.saveKardexin(kardexin);
      }
   }

   saveKardexin(kardexin: Kardexin) {
      this.kardexinService
         .saveKardex(kardexin)
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined && data != null) {
                  if (data.statusCode == "200") {
                     this.addMessage(
                        "",
                        "Se ha registrado la entrada satisfactoriamente.",
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
         idkardexin: [null],
         creationdate: [null],
         sequential: [null, Validators.required],
         persona: [null, Validators.required],
         producto: [null],
         stockmovement: [null],
         description: [null, Validators.maxLength(250)]
      });

      console.info(this.formProduct);
   }

   deleteConfirm(kardexindetail: Kardexindetail) {
      this.confirmationService.confirm({
         message: " Estas seguro de eliminar este producto del movimiento ?",
         accept: () => {
            this.deleteProduct(kardexindetail);
         }
      });
   }

   deleteProduct(kardexindetail: Kardexindetail) { 
      this.kardexInDetails = this.kardexInDetails.filter(obj => obj !== kardexindetail);
   }

   onEditKardex(kardexindetail: Kardexindetail) {

      let product: Producto = {
         idproduct: kardexindetail.idproduct,
         name: kardexindetail.productname
      }

      this.formProduct.controls["producto"].setValue(product);
      this.formProduct.controls["stockmovement"].setValue(kardexindetail.stockmovement);

      this.productEdit = kardexindetail;

   }
}
