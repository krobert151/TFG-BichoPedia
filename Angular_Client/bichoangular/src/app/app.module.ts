import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/user/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomePageComponent } from './components/home-page/home-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ImportModule } from './components/import/import.module';

import { SpecieComponent } from './components/species/species-table/species.component';
import { EditSpecieComponent } from './components/species/edit-specie/edit-specie.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    NavbarComponent,
    SpecieComponent,
    EditSpecieComponent,

  ],
  imports: [
    FormsModule,
    ImportModule,
    BrowserModule,
    AppRoutingModule ,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
  ],
  providers: [
    provideHttpClient(withFetch())
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
