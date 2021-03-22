import {Component, OnInit} from '@angular/core';
import {TableService} from '../../services/table.service';

@Component({
  selector: 'app-table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.scss']
})
export class TableListComponent implements OnInit {

  tables: any;
  currentTable: any;
  currentIndex = -1;
  tableName = '';

  constructor(private tableService: TableService) {
  }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.tableService.getAll()
      .subscribe(data => {
        this.tables = data;
        console.log(data);
      }, error => {
        console.log(error);
      });
  }

  refreshList(): void {
    this.retrieveTables();
    this.currentTable = null;
    this.currentIndex = -1;
  }

  setActiveTable(table: null, index: number): void {
    this.currentTable = table;
    this.currentIndex = index;
  }

  removeAllTables(): void {
    this.tableService.deleteAll()
      .subscribe(res => {
        console.log(res);
        this.retrieveTables();
      }, err => {
        console.log(err);
      });
  }

  searchTitle(): void {
    this.tableService.findByTableName(this.tableName)
      .subscribe(data => {
        this.tables = data;
        console.log(data);
      }, error => {
        console.log(error);
      });
  }

}
