import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/user/login/login.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { SpecieComponent } from './components/sepecies/species/species.component';

const routes: Routes = [

  { path: 'login', component: LoginComponent}, 
  { path: 'home-page',component:HomePageComponent}, 
  { path: 'species',component:SpecieComponent}, 
  { path: '', pathMatch: 'full', redirectTo: '/login' },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
