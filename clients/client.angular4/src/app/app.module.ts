import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localeEn from '@angular/common/locales/en';
import localeFr from '@angular/common/locales/fr';
import localeDe from '@angular/common/locales/de';
//import localeLa from '@angular/common/locales/la*';

import { AppComponent } from './app.component';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [{provide: LOCALE_ID, useValue: 'en'}],
  bootstrap: [AppComponent]
})
export class AppModule {
  //registerLocaleData(localeEn, localeFr, localeDe);
 }
