import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng2PaginationModule } from 'ng2-pagination';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';

import { HomeComponent } from './home/home.component';
import { MenubarModule } from 'primeng/menubar';
import { PanelModule } from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import { InputMaskModule } from 'primeng/inputmask';
import { TableModule } from 'primeng/table';
import { FieldsetModule } from 'primeng/fieldset';
import { InputTextModule } from 'primeng/inputtext';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { DropdownModule } from 'primeng/dropdown';
import { SpinnerModule } from 'primeng/spinner';
import { PickListModule } from 'primeng/picklist';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { DialogModule } from 'primeng/dialog';
import { ToastModule } from 'primeng/toast';
import { AutoCompleteModule } from 'primeng/autocomplete';

import { PersonAdminComponent } from './persona/persona.component';
import { ProductAdminComponent } from './producto/producto.component';
import { KardexinAdminComponent } from './kardexin/kardexin.component';
import { KardexoutAdminComponent } from './kardexout/kardexout.component';


import { AppRoutingModule } from './routes';
import { ToastrModule } from 'ngx-toastr';
import { MenuComponent } from './menu/menu.component';

import { ContainerHomePersonaComponent } from './container-home-persona/container-home-persona.component';
import { ContainerHomeProductoComponent } from './container-home-producto/container-home-producto.component';
import { ContainerHomeKardexinComponent } from './container-home-kardexin/container-home-kardexin.component';
import { ContainerHomeKardexoutComponent } from './container-home-kardexout/container-home-kardexout.component';

@NgModule({
  declarations: [
    AppComponent,   
    HomeComponent,
    PersonAdminComponent,
    ProductAdminComponent,
    KardexinAdminComponent,
    KardexoutAdminComponent,
    MenuComponent,
    ContainerHomePersonaComponent, 
    ContainerHomeProductoComponent, 
    ContainerHomeKardexinComponent, 
    ContainerHomeKardexoutComponent 
  ],
  imports: [
    BreadcrumbModule,
    DropdownModule,
    MessagesModule,
    MessageModule,
    InputTextModule,
    FieldsetModule,
    TableModule,
    MenubarModule,
    ButtonModule,
    PanelModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    Ng2PaginationModule,
    SpinnerModule,
    PickListModule,
    InputMaskModule,
    ConfirmDialogModule,
    DynamicDialogModule,
    DialogModule,
    ToastModule,
    AutoCompleteModule,
    ToastrModule.forRoot()
  ],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
