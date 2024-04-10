import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/user/login/login.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { SpecieComponent } from './components/species/species-table/species.component';
import { EditSpecieComponent } from './components/species/edit-specie/edit-specie.component';
import { SpecieDetailsInfoComponent } from './components/species/specie-details-info/specie-details-info.component';

const routes: Routes = [

  { path: 'login', component: LoginComponent}, 
  { path: 'home-page',component:HomePageComponent}, 
  { path: 'species',component:SpecieComponent}, 
  { path: 'species/edit',component:EditSpecieComponent}, 
  { path: 'species/:id',component:SpecieDetailsInfoComponent}, 
  { path: '', pathMatch: 'full', redirectTo: '/login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
