import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { PaginatorState } from 'primeng/paginator';
import { SpecieService } from '../../../services/specie.service';
import { FileSelectEvent, FileUploadEvent } from 'primeng/fileupload';
import { FileService } from '../../../services/file.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { EncountersItemResponse } from '../../../models/encounter/encounter.module';
import { EncounterService } from '../../../services/encounter.service';
import { EncounterEdit } from '../../../models/encounter/encounter-edit.module';
import { SpecieNameResponse } from '../../../models/specie/species-names.module';


@Component({
  selector: 'app-encounters-table',
  templateUrl: './encounters-table.component.html',
  styleUrl: './encounters-table.component.css',
  providers: [ConfirmationService, MessageService]
})
export class EncountersTableComponent {


  page: number = 0;

  rows1: number = 10;

  speciesNames!: SpecieNameResponse[]

  specieSeleccionated!: SpecieNameResponse;

  encounterEdit: EncounterEdit = { encounterId: '', specieId: '', description: '', location: '', photos: [''], date: undefined };
  editTitle!: string;

  hideLocation: boolean = false;
  list: EncountersItemResponse[] = [];
  selectAll = false;


  uploadedFiles: File[] = [];

  scName: string | undefined;
  visibleCreate: boolean = false;
  visibleEdit: boolean = false;




  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(
    private service: EncounterService,
    private specieService: SpecieService,
    private fileService: FileService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService) {
  }


  delete(id: string) {
    this.confirmationService.confirm({
      message: `Are you sure to remove this encounter?`,
      header: `Remove Encounter`,
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass: "p-button-text p-button-text",
      rejectButtonStyleClass: "p-button-danger p-button-text",
      acceptIcon: "none",
      rejectIcon: "none",
      accept: () => {
        this.service.deleteEncounter(id).subscribe({
          next: resp => {
            this.onSearch();
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'The encounter was removed.' });
          }, error: error => {
            console.log(error)
            this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
          }
        });
      },
      reject: () => {
        this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
      }
    });

  }

  ngOnInit(): void {
    this.specieService.allSpeciesNames().subscribe({
      next: resp => {
        this.speciesNames = resp;
      }, error: error => {
        console.log(error)
        this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
      }
    }
    );
    this.fetchSpecies("");
  }

  onSearch(): void {
    this.fetchSpecies(`?c=${this.rows1}&p=${this.page}`);
  }


  fetchSpecies(keyword: string): void {
    this.service.allEncounters(keyword).subscribe({
      next: x => {
        this.list = x;
      }, error: error => {
        console.log(error)
        this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
      }
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
    this.editTitle = encounter.scientificName.concat('-').concat(encounter.username);
    this.encounterEdit.encounterId = encounter.id;
    this.encounterEdit.description = encounter.description
    this.encounterEdit.location = encounter.lat + ',' + encounter.lon;
    this.encounterEdit.date = new Date(encounter.date)
    this.visibleEdit = true;
  }

  showDialogCreated() {
    this.visibleCreate = true;
  }

  getMapLink(encounter: EncountersItemResponse) {
    return 'https://www.google.es/maps/@' + encounter.lat + ',' + encounter.lon + ', 17z ? hl = es & entry=ttu'
  }

  saveEncounter() {

    if (this.uploadedFiles.length != 0) {
      this.uploadedFiles.forEach(file => {
        this.encounterEdit.photos.push(file.name)
      });
      this.uploadedFiles.forEach(x => {
        this.fileService.uploadImage(x).subscribe({
          next: resp => {

          }, error: error => {
            console.log(error)
            this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
          }
        }
        )
      });
    }

    this.encounterEdit.specieId = this.specieSeleccionated.id;
    this.service.editEncounter(this.encounterEdit).subscribe({
      next: resp => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: resp.scientificName + ' encounter edited.' });
      }, error: error => {
        console.log(error)
        this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
      }
    });

  }

  onUpload($event: FileSelectEvent) {
    this.uploadedFiles = $event.currentFiles;
  }




}
