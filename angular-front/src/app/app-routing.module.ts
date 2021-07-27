import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserListComponent} from "./user-list/user-list.component";
import {UserFormComponent} from "./user-form/user-form.component";
import {ProductListComponent} from "./product-list/product-list.component";
import {ProductFormComponent} from "./product-form/product-form.component";

const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: "user"},
  {path: "user", component: UserListComponent},
  {path: "user/:id", component: UserFormComponent},
  {path: "user/new", component: UserFormComponent},
  {path: "product", component:ProductListComponent},
  {path: "product/new", component:ProductFormComponent},
  {path: "product/:id", component:ProductFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
