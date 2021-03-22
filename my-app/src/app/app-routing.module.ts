import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TableListComponent } from './components/table-list/table-list.component';
import { TableDetailsComponent } from './components/table-details/table-details.component';
import { AddTableComponent } from './components/add-table/add-table.component';

const routes: Routes = [
  { path: '', redirectTo: 'tables', pathMatch: 'full' },
  { path: 'tables', component: TableListComponent },
  { path: 'tables/:id', component: TableDetailsComponent },
  { path: 'add', component: AddTableComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
