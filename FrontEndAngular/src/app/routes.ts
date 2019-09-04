import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ContainerHomePersonaComponent } from "./container-home-persona/container-home-persona.component";
import { ContainerHomeProductoComponent } from "./container-home-producto/container-home-producto.component";
import { ContainerHomeKardexinComponent } from "./container-home-kardexin/container-home-kardexin.component";
import { ContainerHomeKardexoutComponent } from "./container-home-kardexout/container-home-kardexout.component";

const routes: Routes = [
   
   {
      path: "", component: ContainerHomePersonaComponent
   },
   {
      path: "persona", component: ContainerHomePersonaComponent
   },
   {
      path: "producto", component: ContainerHomeProductoComponent
   },
   {
      path: "kardexin", component: ContainerHomeKardexinComponent
   },
   {
      path: "kardexout", component: ContainerHomeKardexoutComponent
   }
    
];

@NgModule({
   imports: [RouterModule.forRoot(routes)],
   exports: [RouterModule]
})
export class AppRoutingModule {}
