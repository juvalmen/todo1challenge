import { Component, OnInit, ViewChild } from "@angular/core";
import { MessageService, ConfirmationService } from "primeng/api";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import { first } from "rxjs/operators";

import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { Categoria } from "src/interfaces/category.interface";
import { Producto } from "src/interfaces/producto.interface";
import { ProductoService } from "src/services/producto/producto.service";
import { InputMask } from "primeng/inputmask";
import { CategoryProductEnum } from "src/model/enum/CategoryProductEnum";

@Component({
   selector: "app-producto",
   templateUrl: "./producto.component.html",
   styleUrls: ["./producto.component.css"],
   providers: [MessageService, ConfirmationService]
})
export class ProductAdminComponent implements OnInit {
   @ViewChild("myInputMaskCcv") myInputMaskCcv: InputMask;

   formProduct: FormGroup;
   products: Producto[] = [];
   dataTableCols: any[];
   categoriaProducto: Categoria[] = [];
   attrCcv: any;
   categoriaDropDown: Categoria;

   constructor(
      public messageService: MessageService,
      private _fb: FormBuilder,
      private productService: ProductoService,
      private confirmationService: ConfirmationService
   ) {
      console.log("constructor");
      this.initForm();
      this.initComponents();
   }

   initForm() {
      this.formProduct = this._fb.group({
         idproduct: [null],
         creationndate: [null],
         modificationdate: [null],
         categoriaproducto: [null, Validators.required],
         price: [0, Validators.required],
         stock: [0],
         name: [null, Validators.maxLength(80)],
         description: [null, Validators.maxLength(250)]
      });
   }

   ngOnInit() {
      this.dataTableCols = [
         { field: "name", header: "Nombre" },
         { field: "description", header: "Descripción" },
         { field: "price", header: "Precio" },       
         { field: "stock", header: "Stock" },       
         { field: "descriptionProductCategory", header: "Categoría" }
      ];
      this.buildItemsEnumInArray();
      this.getProducts();
   }

   async buildItemsEnumInArray() {
      this.categoriaProducto = await Object.keys(CategoryProductEnum).map(
         item => CategoryProductEnum[item]
      );
      console.log(this.categoriaProducto);
   }

   initComponents() {
      this.attrCcv = { attributeValue: "" };
   }

   isInputMaskFilledCcv(event) {
      if (!this.myInputMaskCcv.filled) {
         this.attrCcv = { attributeValue: "" };
      }
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

   getProducts() {
      this.productService
         .getProducts()
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

   onChangeCategoria() {
     
   }

   validarAntesDeGrabar() {
      
      let value = this.formProduct.value.categoriaproducto.value;
      if (value == null) {
         this.addMessage("", "Se debe seleccionar la categoría.", "warn");
         return;
      }
      let name = this.formProduct.value.name;
      if (name == null || name.length == 0) {
         this.addMessage("", "El campo nombre es obligatorio.", "warn");
         return;
      }
      let description = this.formProduct.value.description;
      if (description == null || description.length == 0) {
         this.addMessage("", "El campo descripción es obligatorio.", "warn");
         return;
      }
      let price = this.formProduct.value.price;
      if (price == null || price < 0) {
         this.addMessage("", "El campo precio no debe ser nulo o negativo.", "warn");
         return;
      }
      let stock = this.formProduct.value.stock;
      if (stock == null || stock < 0) {
         this.addMessage("", "El campo stock no debe ser nulo o negativo.", "warn");
         return;
      }
    
      const product: Producto = {
         idproduct: this.formProduct.value.idproduct,
         creationdate: this.formProduct.value.creationdate,
         modificationdate: this.formProduct.value.modificationdate,
         idProductCategory: this.formProduct.value.categoriaproducto.idcategory,
         valueProductCategory: this.formProduct.value.categoriaproducto.value,
         price: this.formProduct.value.price,
         stock: this.formProduct.value.stock,
         name: this.formProduct.value.name,
         description: this.formProduct.value.description
      };
     
      // Save or update
      console.log("product", product);
      if (product.idproduct == null) {
         this.saveProduct(product);
      } else {
         this.updateProduct(product);
      }
   }

   saveProduct(product: Producto) {
      this.productService
         .saveProduct(product)
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined && data != null) {
                  if (data.statusCode == "200") {
                     this.addMessage("", 'Se ha registrado el producto satisfactoriamente.', "info");
                    this.getProducts();
                    this.clearForm();
                  } else {
                     this.addMessage("", data.responseMessage, "warn");
                  }
               } else {
                  this.addMessage("", "Error interno en la aplicación. Contacte con el administrador.", "error");
               }
            },
            error => {
               this.addMessage(
                  "",
                  "Error interno en la aplicación. Contacte con el administrador." + error,
                  "error"
               );
            }
         );
   }

   updateProduct(product: Producto) {
      this.productService
         .updateProduct(product)
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined && data != null) {
                  if (data.statusCode == "200") {
                     this.addMessage("", 'Se ha actualizado el producto satisfactoriamente.', "info");
                    this.getProducts();
                    this.clearForm();
                  } else {
                     this.addMessage("", data.responseMessage, "warn");
                  }
               } else {
                  this.addMessage("", "Error interno en la aplicación. Contacte con el administrador.", "error");
               }
            },
            error => {
               this.addMessage(
                  "",
                  "Error interno en la aplicación. Contacte con el administrador." + error,
                  "error"
               );
            }
         );
   }

   deleteProduct(product: Producto) {
      this.productService
         .deleteProduct(product.idproduct)
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined && data != null) {
                  if (data.statusCode == "200") {
                     this.addMessage("", 'Se ha eliminado el producto satisfactoriamente.', "info");
                    this.getProducts();
                    this.clearForm();
                  } else {
                     this.addMessage("", data.responseMessage, "warn");
                  }
               } else {
                  this.addMessage("", "Error interno en la aplicación. Contacte con el administrador.", "error");
               }
            },
            error => {
               this.addMessage(
                  "",
                  "Error interno en la aplicación. Contacte con el administrador." + error,
                  "error"
               );
            }
         );
   }

   clearForm() {
      this.initComponents();

      this.formProduct = this._fb.group({
         idproduct: [null],
         price: [0, Validators.required],
         stock: [0],
         creationdate: [null],
         modificationdate: [null],
         categoriaproducto: [null, Validators.required],
         name: [null, Validators.maxLength(80)],
         description: [null, Validators.maxLength(250)]

      });

      this.formProduct.controls["price"].setValue('0');
      this.formProduct.controls["stock"].setValue('0');

      console.info(this.formProduct);

   }
  
  deleteConfirm(product: Producto) {
   this.confirmationService.confirm({
      message: ' Estas seguro de eliminar el registro ?',
      accept: () => {
         this.deleteProduct(product);
      }
   });
  }

   onEditProduct(product: Producto) {

      let categoria: Categoria = {
         idcategory: product.idProductCategory,
         name: product.descriptionProductCategory,
         value: product.valueProductCategory
      };

      this.categoriaDropDown = categoria;

      let valueCategory: any;

      if (categoria.value == '1') { 
         valueCategory = CategoryProductEnum.CAM;
      }
      if (categoria.value == '2') { 
         valueCategory = CategoryProductEnum.VAS;
      }
      if (categoria.value == '3') { 
         valueCategory = CategoryProductEnum.JUG;
      }
      if (categoria.value == '4') { 
         valueCategory = CategoryProductEnum.ACC;
      }

      this.formProduct = this._fb.group({
         idproduct: [product.idproduct],
         creationdate: [product.creationdate],
         modificationdate: [product.modificationdate],
         categoriaproducto: [valueCategory, Validators.required],
         name: [product.name, Validators.maxLength(80)],
         description: [product.description, Validators.maxLength(250)],
         price: [product.price, Validators.required],
         stock: [product.stock]
      });

   }
}
