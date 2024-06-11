import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/user/login/login.component';
import { SpecieComponent } from './components/species/species-table/species.component';
import { EncountersTableComponent } from './components/encounters/encounters-table/encounters-table.component';
import { ArticleTableComponent } from './components/article/article-table/article-table.component';
import { UserTableComponent } from './components/user/user-table/user-table.component';
const routes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: 'species', component: SpecieComponent },
  { path: 'encounters', component: EncountersTableComponent },
  { path: 'articles', component: ArticleTableComponent },
  { path: 'users', component: UserTableComponent }, /*Ilo shurra esto hay que cambiarlo*/
  { path: 'home-page', component: SpecieComponent }, /*Ilo shurra esto hay que cambiarlo*/
  { path: '', pathMatch: 'full', redirectTo: '/login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
