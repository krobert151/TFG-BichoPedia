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
  selector: 'species.component',
  styleUrl: 'species.component.css',
  templateUrl: 'species.component.html',
})
export class SpecieComponent implements OnInit {



  selectedValue?:string;
  displayedColumns: string[] = ['photo', 'scientificName', 'danger', 'type','actions'];
  dataSource: MatTableDataSource<SpecieItemResponse>;
  list : SpecieItemResponse[] = [];
  isSlideOpen: boolean = false; // Variable para controlar si el slide está abierto
  selectedSpecie: SpecieItemResponse | null = null; 


  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  @ViewChild(MatSort) sort: MatSort | undefined;


  constructor( public dialog: MatDialog,private router: Router,    private service: SpecieService
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
  editSpecie(item: SpecieItemResponse){
    this.router.navigate(['/species/edit', { item: JSON.stringify(item) }]);
  }


  getPhoto(photo:string,width:number,height:number){


    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  
  deleteRow(row: any) {
  }
  openSlide(specie: SpecieItemResponse) {
    this.selectedSpecie = specie;
    this.isSlideOpen = true;
  }
  
  closeSlide() {
    this.isSlideOpen = false;
  }

  onSubmit() {

    
  }


}