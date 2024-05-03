import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/user/login/login.component';
import { SpecieComponent } from './components/species/species-table/species.component';
const routes: Routes = [

  { path: 'login', component: LoginComponent}, 
  { path: 'species',component:SpecieComponent}, 
  { path: 'home-page',component:SpecieComponent}, /*Ilo shurra esto hay que cambiarlo*/ 
  { path: '', pathMatch: 'full', redirectTo: '/login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
