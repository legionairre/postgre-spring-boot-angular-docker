import {Component, OnInit} from '@angular/core';
import {TableService} from '../../services/table.service';
import {FormGroup, FormControl, FormArray, FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-add-table',
  templateUrl: './add-table.component.html',
  styleUrls: ['./add-table.component.scss']
})
export class AddTableComponent implements OnInit {
  submitted = false;

  tableForm: FormGroup;

  constructor(
    private tableService: TableService,
    private fb: FormBuilder) {
    this.tableForm = this.fb.group({
      tableName: '',
      columns: this.fb.array([]),
    });
  }

  ngOnInit(): void {
  }

  saveTable(): void {
    const data = this.tableForm.value;
    console.log(data);
    this.tableService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        }
      );
  }

  newTable(): void {
    this.submitted = false;
    this.tableForm = this.fb.group({
      tableName: '',
      columns: this.fb.array([]),
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
    this.columns().removeAt(i);
  }

  onSubmit(): void {
    this.saveTable();
  }

}
