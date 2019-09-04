import { Component, OnInit, ViewChild } from "@angular/core";
import { MessageService, ConfirmationService, MenuItem } from "primeng/api";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import { first } from "rxjs/operators";

import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { Categoria } from "src/interfaces/category.interface";
import { Persona } from "src/interfaces/persona.interface";
import { PersonaService } from "src/services/persona/persona.service";
import { InputMask } from "primeng/inputmask";
import { CategoryEnum } from "src/model/enum/CategoryEnum";

@Component({
   selector: "app-persona",
   templateUrl: "./persona.component.html",
   styleUrls: ["./persona.component.css"],
   providers: [MessageService, ConfirmationService]
})
export class PersonAdminComponent implements OnInit {
   @ViewChild("myInputMaskCcv") myInputMaskCcv: InputMask;

   formPerson: FormGroup;
   persons: Persona[] = [];
   dataTableCols: any[];
   categoriaPersona: Categoria[] = [];
   attrCcv: any;
   categoriaDropDown: Categoria;
   items: MenuItem[];
   
   constructor(
      public messageService: MessageService,
      private _fb: FormBuilder,
      private personService: PersonaService,
      private confirmationService: ConfirmationService
   ) {
      console.log("constructor");
      this.initForm();
      this.initComponents();
   }

   initForm() {
      this.formPerson = this._fb.group({
         idperson: [null],
         creationdate: [null],
         modificationdate: [null],
         identificationnumber: [null, Validators.required],
         categoriapersona: [null, Validators.required],
         name: [null, Validators.maxLength(80)],
         lastname: [null, Validators.maxLength(250)]
      });
   }

   ngOnInit() {
      this.dataTableCols = [
         { field: "name", header: "Nombre" },
         { field: "lastname", header: "Apellido" },
         { field: "identificationnumber", header: "Identificación" },       
         { field: "descriptionPersonCategory", header: "Categoría" }
      ];
      this.buildItemsEnumInArray();
      this.getPersons();
      this.itemsMenu();
   }

   itemsMenu() { 
    this.items = [
      {

        label: 'Administración',
        items: [
          {
            label: 'Vendedores',
            routerLink: ['/persona']
          },
          {
            label: 'Productos',
            routerLink: ['/producto']
          }
        ]
      },
      {
        label: 'Entradas/Salidas',
        items: [
          {
            label: 'Entrada',
            routerLink: ['/kardexin']
          },
          {
            label: 'Salida',
            routerLink: ['/kardexout']
          }
        ]
      }
    ];
   }

   async buildItemsEnumInArray() {
      this.categoriaPersona = await Object.keys(CategoryEnum).map(
         item => CategoryEnum[item]
      );
      console.log(this.categoriaPersona);
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

   getPersons() {
      this.personService
         .getPersons()
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

   onChangeCategoria() {
     
   }

   validarAntesDeGrabar() {
      let identificationnumber = this.formPerson.value.identificationnumber;
      console.log("identificationnumber", identificationnumber);
      if (identificationnumber != null && identificationnumber.length > 45) {
         this.addMessage(
            "",
            "El tamaño del campo Identificación es de máximo 45 caracteres.",
            "warn"
         );
         return;
      }
      let value = this.formPerson.value.categoriapersona.value;
      if (value == null) {
         this.addMessage("", "Se debe seleccionar la categoría.", "warn");
         return;
      }
      let name = this.formPerson.value.name;
      if (name == null || name.length == 0) {
         this.addMessage("", "El campo nombre es obligatorio.", "warn");
         return;
      }
      let lastname = this.formPerson.value.lastname;
      if (lastname == null || lastname.length == 0) {
         this.addMessage("", "El campo apellido es obligatorio.", "warn");
         return;
      }
    
      const person: Persona = {
         idperson: this.formPerson.value.idperson,
         creationdate: this.formPerson.value.creationdate,
         modificationdate: this.formPerson.value.modificationdate,
         identificationnumber: this.formPerson.value.identificationnumber,
         idPersonCategory: this.formPerson.value.categoriapersona.idcategory,
         valueCategoryPerson: this.formPerson.value.categoriapersona.value,
         name: this.formPerson.value.name,
         lastname: this.formPerson.value.lastname
      };
     
      // Save or update
      console.log("person", person);
      if (person.idperson == null) {
         this.savePerson(person);
      } else {
         this.updatePerson(person);
      }
   }

   savePerson(person: Persona) {
      this.personService
         .savePerson(person)
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined && data != null) {
                  if (data.statusCode == "200") {
                     this.addMessage("", 'Se ha registrado la persona satisfactoriamente.', "info");
                    this.getPersons();
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

   updatePerson(person: Persona) {
      this.personService
         .updatePerson(person)
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined && data != null) {
                  if (data.statusCode == "200") {
                     this.addMessage("", 'Se ha actualizado la persona satisfactoriamente.', "info");
                    this.getPersons();
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

   deletePerson(person: Persona) {
      this.personService
         .deletePerson(person.idperson)
         .pipe(first())
         .subscribe(
            data => {
               if (data != undefined && data != null) {
                  if (data.statusCode == "200") {
                     this.addMessage("", 'Se ha eliminado la persona satisfactoriamente.', "info");
                    this.getPersons();
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

      this.formPerson = this._fb.group({
         idperson: [null],
         creationdate: [null],
         modificationdate: [null],
         identificationnumber: [null, Validators.required],
         categoriapersona: [null, Validators.required],
         name: [null, Validators.maxLength(80)],
         lastname: [null, Validators.maxLength(250)]        
      });

   }
  
  deleteConfirm(person: Persona) {
   this.confirmationService.confirm({
      message: ' Estas seguro de eliminar el registro ?',
      accept: () => {
         this.deletePerson(person);
      }
   });
  }

   onEditPerson(person: Persona) {

      let categoria: Categoria = {
         idcategory: person.idPersonCategory,
         name: person.descriptionCategoryPerson,
         value: person.valueCategoryPerson
      };

      this.categoriaDropDown = categoria;

      this.formPerson = this._fb.group({
         idperson: [person.idperson],
         creationdate: [person.creationdate],
         modificationdate: [person.modificationdate],
         identificationnumber: [person.identificationnumber, Validators.required],
         categoriapersona: [categoria.value == '1' ? CategoryEnum.VEN : CategoryEnum.VEN, Validators.required],
         name: [person.name, Validators.maxLength(80)],
         lastname: [person.lastname, Validators.maxLength(250)]   
      });

   }
}
