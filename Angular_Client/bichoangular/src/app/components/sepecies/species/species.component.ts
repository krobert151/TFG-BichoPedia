import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { SpecieItemResponse } from '../../../models/specie/specie.module';
import { SpecieService } from '../../../services/specie.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'species.component',
  styleUrl: 'species.component.css',
  templateUrl: 'species.component.html',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule,    MatButtonModule,
    MatIconModule, MatTableModule, MatSortModule, MatPaginatorModule],
})
export class SpecieComponent implements OnInit {
  displayedColumns: string[] = ['id', 'scientificName', 'danger', 'type','actions'];
  dataSource: MatTableDataSource<SpecieItemResponse>;
  list : SpecieItemResponse[] = [];

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  @ViewChild(MatSort) sort: MatSort | undefined;

  constructor(    private service: SpecieService
    ) {
    this.dataSource = new MatTableDataSource(this.list);
  }
  ngOnInit(): void {
    this.service.allSpecies('').subscribe(resp => {
      console.log(resp); // Log response to check if data is fetched successfully
      this.list = resp;
      this.dataSource.data = this.list; // Update data source after fetching
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  editRow(row: any) {
    // Implementa la lógica para editar la fila
  }
  
  deleteRow(row: any) {
    // Implementa la lógica para eliminar la fila
  }
}