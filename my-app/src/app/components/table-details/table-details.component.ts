import {Component, OnInit} from '@angular/core';
import {TableService} from '../../services/table.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormGroup, FormControl, FormArray, FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-table-details',
  templateUrl: './table-details.component.html',
  styleUrls: ['./table-details.component.scss']
})
export class TableDetailsComponent implements OnInit {

  message = '';
  tableForm: FormGroup;
  tableId: string | null | undefined;
  dataColumns: any;

  constructor(
    private tableService: TableService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder) {
    this.tableForm = this.fb.group({
      tableName: '',
      columns: this.fb.array([]),
    });
  }

  ngOnInit(): void {
    this.message = '';
    this.tableId = this.route.snapshot.paramMap.get('id');
    this.getTable(this.tableId);
    this.tableForm = this.fb.group({
      tableName: '',
      columns: this.fb.array([]),
    });
  }

  getTable(id: string | null): void {
    this.tableService.get(id)
      .subscribe(data => {
        this.tableForm.controls.tableName.setValue(data.tableName);
        const columnList: { column: any; }[] = [];
        data.columns.forEach((column: any) => {
          columnList.push({
            column: column.columnName
          });
        });

        this.dataColumns = data.columns;

        this.tableForm.setControl('columns',
          this.fb.array(columnList.map(
            name => this.fb.group({column: name.column})
          )));
        this.tableId = data.id;
      }, error => {
        console.log(error);
      });
  }

  updateTable(): void {
    this.tableService.update(this.tableId, this.tableForm)
      .subscribe(
        res => {
          console.log(res);
          this.message = 'The table was updated successfully';
        },
        error => {
          console.log(error);
        }
      );
  }

  deleteTable(): void {
    this.tableService.delete(this.tableId)
      .subscribe(res => {
        console.log(res);
        this.router.navigate(['/tables']);
      }, error => {
        console.log(error);
      });
  }

  columns(): FormArray {
    return this.tableForm.get('columns') as FormArray;
  }

  newColumn(): FormGroup {
    return this.fb.group({
      column: '',
    });
  }

  addColumn(): void {
    this.columns().push(this.newColumn());
  }

  removeColumn(i: number): void {
    const columnId = this.dataColumns[i].columnId;
    this.columns().removeAt(i);
    this.tableService.deleteColumn(columnId)
      .subscribe(res => {
        console.log(res);
      }, error => {
        console.log(error);
      });
  }

  onSubmit(): void {
    this.updateTable();
  }

}
