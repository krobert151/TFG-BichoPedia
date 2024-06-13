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
import { ConfirmationService, MessageService } from 'primeng/api';

interface PageEvent {
  first: number;
  rows: number;
  page: number;
  pageCount: number;
}

interface Danger {
  name: string,
  code: string
}
interface Type {
  name: string,
  code: string
}

@Component({
  selector: 'app-specie',
  styleUrls: ['./species.component.css'],
  templateUrl: './species.component.html',
  providers: [ConfirmationService, MessageService]

})
export class SpecieComponent implements OnInit {




  page: number = 0;

  rows1: number = 10;

  selectedSpecie: SpecieUpdate = { id: '', scientificName: '', mainPhoto: '', danger: '', type: '' };
  createSpecie: CreateSpecie = { scientificName: '', mainPhoto: '', danger: '', type: '' };;

  search = '?search='

  list: SpecieItemResponse[] = [];
  selectAll = false;

  dangers!: Danger[];
  selectDangers!: Danger[];
  searchDangers: string = '';
  file!: File;

  types!: Type[];
  selcetTypes!: Type[];
  searchTypes: string = '';

  scName: string | undefined;
  visibleCreate: boolean = false;
  visibleEdit: boolean = false;

  editDanger!: Danger;
  editType!: Type;

  setDangers() {
    this.dangers = [
      { name: 'LC', code: 'LC' },
      { name: 'NT', code: 'NT' },
      { name: 'VU', code: 'VU' },
      { name: 'EN', code: 'EN' },
      { name: 'CR', code: 'CR' },
      { name: 'EW', code: 'EW' },
      { name: 'EX', code: 'EX' },
    ]
  }


  setTypes() {
    this.types = [
      { name: 'Arachnid', code: 'Arachnid' },
      { name: 'Amphibian', code: 'Amphibian' },
      { name: 'Bird', code: 'Bird' },
      { name: 'Lizzard', code: 'Lizzard' },
      { name: 'Snake', code: 'Snake' },
      { name: 'Mammal', code: 'Mammal' },
      { name: 'Fish', code: 'Fish' },
      { name: 'Insect', code: 'Insect' },
      { name: 'Salamander', code: 'Salamander' },
    ]
  }

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(
    private service: SpecieService,
    private fileService: FileService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
  }


  delete(id: string) {
    this.confirmationService.confirm({
      message: `All articles an encounter related to the specie will be removed.`,
      header: `Are you sure to remove this specie?`,
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass: "p-button-text p-button-text",
      rejectButtonStyleClass: "p-button-danger p-button-text",
      acceptIcon: "none",
      rejectIcon: "none",
      accept: () => {
        this.service.deleteSpecie(id).subscribe({
          next: resp => {
            this.onSearch();
            this.messageService.add({ severity: 'success', summary: 'Success', detail: 'The specie was removed.' });
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

  searchByScientifName() {
    return this.search + 'scientificName~' + this.scName;
  }


  typeOnChange(event: MultiSelectChangeEvent) {

    if (this.selcetTypes.length == 0) {
      this.searchTypes = ''
    }
    if (this.selcetTypes.length == 1) {
      this.searchTypes = `( type:${this.selcetTypes[0].name} )`
    }
    if (this.selcetTypes.length > 1) {
      this.searchTypes = '(';

      this.selcetTypes.forEach(type => {
        this.searchTypes = this.searchTypes.concat(` type:${type.name} OR`);
      });

      this.searchTypes = this.searchTypes.slice(0, -3);
      this.searchTypes = this.searchTypes.concat(' )');

    }
    console.log(this.searchTypes);
    this.onSearch()
  }


  dangerOnChange($event: MultiSelectChangeEvent) {
    if (this.selectDangers.length == 0) {
      this.searchDangers = ''
    }
    if (this.selectDangers.length == 1) {
      this.searchDangers = `( danger:${this.selectDangers[0].name} )`
    }
    if (this.selectDangers.length > 1) {
      this.searchDangers = '(';

      this.selectDangers.forEach(type => {
        this.searchDangers = this.searchDangers.concat(` danger:${type.name} OR`);
      });

      this.searchDangers = this.searchDangers.slice(0, -3);
      this.searchDangers = this.searchDangers.concat(' )');

    }
    console.log(this.searchDangers);
    this.onSearch()
  }

  ngOnInit(): void {
    this.fetchSpecies("");
    this.setDangers();
    this.setTypes();

  }

  onSearch(): void {
    let keyword = '';

    if (this.scName != '' && this.scName != null) {
      keyword = this.searchByScientifName();

      if (this.searchTypes !== '') {
        keyword += ` AND ${this.searchTypes}`;
      }
      if (this.searchDangers !== '') {
        keyword += ` AND ${this.searchDangers}`;
      }
    } else {
      if (this.searchTypes !== '') {
        keyword += `${this.search}${this.searchTypes}`;
        if (this.searchDangers !== '') {
          keyword += ` AND ${this.searchDangers}`;
        }
      } else {
        if (this.searchDangers !== '') {
          keyword += `${this.search}${this.searchDangers}`;
        }
      }
    }
    if (keyword == '') {
      this.fetchSpecies(`?c=${this.rows1}&p=${this.page}`);

    } else {
      keyword = keyword + `&c=${this.rows1}&p=${this.page}`
      this.fetchSpecies(keyword);
    }
  }


  fetchSpecies(keyword: string): void {
    this.service.allSpecies(keyword).subscribe({
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

  showDialogEdit(specie: SpecieItemResponse) {
    this.selectedSpecie.danger = specie.danger;
    this.selectedSpecie.id = specie.id;
    this.selectedSpecie.mainPhoto = specie.url;
    this.selectedSpecie.type = specie.type;
    this.selectedSpecie.scientificName = specie.scientificName;
    this.visibleEdit = true;
  }

  showDialogCreated() {
    this.visibleCreate = true;
  }


  saveEditSpecie() {
    if (this.editDanger != null) {
      this.selectedSpecie.danger = this.editDanger.name;
    }
    if (this.editType != null) {
      this.selectedSpecie.type = this.editType.name;
    }
    if (this.file != null) {
      this.selectedSpecie.mainPhoto = this.file.name;
    }
    this.service.editSpecie(this.selectedSpecie).subscribe({
      next: resp => {
        this.onSearch();
        this.messageService.add({ severity: 'success', summary: 'Success', detail: resp.scientificName + ' was edited.' });
      }, error: error => {
        console.log(error)
        this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
      }
    });
    this.fileService.uploadImage(this.file).subscribe();

  }

  saveCretedSpecie() {
    if (this.editDanger != null) {
      this.createSpecie.danger = this.editDanger.name;
    } else {
      this.createSpecie.danger = 'Undefined';
    }
    if (this.editType != null) {
      this.createSpecie.type = this.editType.name;
    } else {
      this.createSpecie.type = 'Undefined';
    }
    if (this.file != null) {
      this.createSpecie.mainPhoto = this.file.name;
    }
    this.service.createSpecie(this.createSpecie).subscribe({
      next: resp => {
        this.onSearch();
        this.messageService.add({ severity: 'success', summary: 'Success', detail: resp.scientificName + ' was created.' });
      }, error: error => {
        console.log(error)
        this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
      }
    });
    this.fileService.uploadImage(this.file).subscribe();
  }

  onUpload($event: FileSelectEvent) {
    this.file = $event.files[0];
  }



}