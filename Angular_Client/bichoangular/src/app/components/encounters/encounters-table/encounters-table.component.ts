import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';

import { PaginatorModule, PaginatorState } from 'primeng/paginator';
import { Router } from '@angular/router';
import { SpecieService } from '../../../services/specie.service';
import { MultiSelectChangeEvent, MultiSelectModule, MultiSelectSelectAllChangeEvent } from 'primeng/multiselect';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { FloatLabelModule } from 'primeng/floatlabel';
import { FileBeforeUploadEvent, FileSelectEvent, FileUploadEvent } from 'primeng/fileupload';
import { FileService } from '../../../services/file.service';
import { CreateSpecie } from '../../../models/specie/create-specie.module';
import { log } from 'console';
import { SpecieUpdate } from '../../../models/specie/update-specie.module';
import { SpecieItemResponse } from '../../../models/specie/specie.module';
import { MessageService } from 'primeng/api';
import { EncountersItemResponse } from '../../../models/encounter/encounter.module';
import { EncounterService } from '../../../services/encounter.service';
import { EncounterEdit } from '../../../models/encounter/encounter-edit.module';
import e from 'express';
import { EncounterCreate } from '../../../models/encounter/encounter-create.module';
import { SpecieNameResponse } from '../../../models/specie/species-names.module';

interface PageEvent {
  first: number;
  rows: number;
  page: number;
  pageCount: number;
}

@Component({
  selector: 'app-encounters-table',
  templateUrl: './encounters-table.component.html',
  styleUrl: './encounters-table.component.css',
  providers: [MessageService]
})
export class EncountersTableComponent {


  page: number = 0;

  rows1: number = 10;

  speciesNames!:SpecieNameResponse[]
  encounterEdit: EncounterEdit = { encounterId: '', specieId: '', description: '', location: '', photos: [''], date:undefined };
  editTitle!:string;

  search = '?search='
  hideLocation:boolean=false;
  list: EncountersItemResponse[] = [];
  selectAll = false;

  selectedSpecie!:string;
  
  searchDangers: string = '';
  file!: File;


  
  searchTypes: string = '';

  scName: string | undefined;
  visibleCreate: boolean = false;
  visibleEdit: boolean = false;


  

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(
    private service: EncounterService,
    private specieService:SpecieService,
    private fileService: FileService,
    private messageService: MessageService) {
  }


  delete(id: string) {
    this.service.deleteSpecie(id).subscribe(resp => {
      this.onSearch();
      this.messageRemoved();
    });
  }

  searchByScientifName() {
    return this.search + 'scientificName~' + this.scName;
  }

  ngOnInit(): void {
    this.specieService.allSpeciesNames().subscribe(
      resp=>{
        this.speciesNames=resp;
    }
  );
    this.fetchSpecies("");
  }

  onSearch(): void {
      this.fetchSpecies(`?c=${this.rows1}&p=${this.page}`);
  }


  fetchSpecies(keyword: string): void {
    this.service.allEncounters(keyword).subscribe((x) => {
      this.list = x;
    });
  }

  getPhoto(photo: string, width: number, height: number) {
    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`
  }

  onPageChange1(event: PaginatorState) {
    this.page = event.page!;
    this.rows1 = event.rows!;
    this.onSearch();
  }

  showDialogEdit(encounter: EncountersItemResponse) {
    this.editTitle =encounter.scientificName.concat('-').concat(encounter.username);
    this.encounterEdit.encounterId = encounter.id;
    this.encounterEdit.description = encounter.description
    this.encounterEdit.date =  new Date(encounter.date)
    this.visibleEdit = true;
  }

  showDialogCreated() {
    this.visibleCreate = true;
  }


  saveEditSpecie() {
    if(this.file!=null){
    }

    this.fileService.uploadImage(this.file).subscribe();

  }



  onUpload($event: FileSelectEvent) {
    this.file = $event.files[0];
  }

  messageAdd() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: ` added.` });
  }

  messageEdit() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: ` edited.` });
  }

  messageRemoved() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: ` deleted.` });
  }


}
