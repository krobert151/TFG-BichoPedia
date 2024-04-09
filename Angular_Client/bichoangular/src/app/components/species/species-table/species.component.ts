import {AfterViewInit, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { SpecieItemResponse } from '../../../models/specie/specie.module';
import { SpecieService } from '../../../services/specie.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterModule } from '@angular/router';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { EditSpecieComponent } from '../edit-specie/edit-specie.component';

@Component({
  selector: 'app-specie',
  styleUrls: ['./species.component.css'],
  templateUrl: './species.component.html',
})
export class SpecieComponent implements OnInit {
  displayedColumns: string[] = ['photo', 'scientificName', 'danger', 'type', 'actions'];
  dataSource: MatTableDataSource<SpecieItemResponse>;
  list: SpecieItemResponse[] = [];
  isSlideOpen: boolean = false;
  selectedSpecie: SpecieItemResponse | null = null;
  searchWord = '';

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(
    private dialog: MatDialog,
    private router: Router,
    private service: SpecieService
  ) {
    this.dataSource = new MatTableDataSource(this.list);
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.service.allSpecies(this.searchWord).subscribe(resp => {
      this.list = resp;
      this.dataSource.data = this.list;
    });
  }

  editSpecie(item: SpecieItemResponse): void {
    this.router.navigate(['/species/edit', { item: JSON.stringify(item) }]);
  }

  deleteRow(id: string): void {
    this.service.deleteSpecie(id).subscribe(s => {
      console.log("Borrado");
      this.loadData(); 
    });
  }
  getPhoto(photo:string,width:number,height:number){
    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`
  }

  openSlide(specie: SpecieItemResponse): void {
    this.selectedSpecie = specie;
    this.isSlideOpen = true;
  }

  closeSlide(): void {
    this.isSlideOpen = false;
  }

  onSubmit(): void {
    this.loadData(); 
  }

  applyFilter(): void {
    this.loadData(); 
  }



}