import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AddTableComponent} from './components/add-table/add-table.component';
import {TableDetailsComponent} from './components/table-details/table-details.component';
import {TableListComponent} from './components/table-list/table-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddTableComponent,
    TableDetailsComponent,
    TableListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
