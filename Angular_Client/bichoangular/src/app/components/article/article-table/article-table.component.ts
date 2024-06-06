import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../../services/article.service';
import { ArticleEspecieResponse } from '../../../models/articles-resoponses/articles-resoponses.module';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ArticleDetails } from '../../../models/article-details/article-details.module';
import { SpecieService } from '../../../services/specie.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SpecieNameResponse } from '../../../models/specie/species-names.module';
import { FileSelectEvent } from 'primeng/fileupload';
import { CreateArticle } from '../../../models/create-article/create-article.module';
import { FileService } from '../../../services/file.service';

interface Type {
  name: string;
  code: string;
}

@Component({
  selector: 'app-article-table',
  templateUrl: './article-table.component.html',
  styleUrl: './article-table.component.css',
  providers: [ConfirmationService,MessageService]
})
export class ArticleTableComponent implements OnInit {


  visibleEdit: boolean = false;
  visibleCreate: boolean = false;
  types!: Type[];
  createForm!: FormGroup;
  editForm!: FormGroup;
  images: any[] | undefined;

  responsiveOptions: any[] = [
      {
          breakpoint: '1024px',
          numVisible: 5
      },
      {
          breakpoint: '768px',
          numVisible: 3
      },
      {
          breakpoint: '560px',
          numVisible: 1
      }
  ];
  articlesEdit: ArticleDetails = {
    id: '',
    title: '',
    text: '',
    approved: false,
    type: '',
    archives: [],
    createdBy: ''
  };
  uploadedFiles: File[] = [];
  articleResponses!: ArticleEspecieResponse[];
  speciesNames!: SpecieNameResponse[];
  expandedRows: any[] = [];

  constructor(
    private confirmationService: ConfirmationService,
    private articleService: ArticleService,
    private specieService: SpecieService,
    private messageService: MessageService,
    private fileService: FileService,
    private fb: FormBuilder
  ) {
    this.initCreateForm();
  }



  delImage(image:string) {
    this.confirmationService.confirm({
        message: `Do you want to delete ${image} ?`,
        header: `Delete ${image}`,
        icon: 'pi pi-info-circle',
        acceptButtonStyleClass:"p-button-danger p-button-text",
        rejectButtonStyleClass:"p-button-text p-button-text",
        acceptIcon:"none",
        rejectIcon:"none",

        accept: () => {
            this.articlesEdit.archives = this.articlesEdit.archives.filter(e => e !== image)
            this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: image+' deleted' });
        },
        reject: () => {
            this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
        }
    });
  }

  initCreateForm() {
    this.createForm = this.fb.group({
      title: ['', Validators.required],
      text: ['', Validators.required],
      specie: [null, Validators.required],
      type: [null, Validators.required]
    });
  }
  getPhoto(photo: string) {
    return `http://localhost:8080/download/${photo}`
  }
  getPhotoScaled(photo: string, width: number, height: number) {
    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`
  }
  ngOnInit() {
    this.setTypes();
    this.specieService.allSpeciesNames().subscribe(resp => {
      this.speciesNames = resp;
    });
    this.loadArticles();
  }

  setTypes() {
    this.types = [
      { name: 'Info', code: 'INFO' },
      { name: 'Identification', code: 'IDENTIFICATION' },
      { name: 'Cares', code: 'CARES' }
    ];
  }

  loadArticles() {
    this.articleService.allArticles().subscribe(resp => {
      this.articleResponses = resp;
    });
  }

  onUpload($event: FileSelectEvent) {
    this.uploadedFiles = $event.currentFiles;
  }

  getIcon(boo: boolean) {
    return boo ? 'pi pi-times' : 'pi pi-check';
  }

  showCreateDialog() {
    this.visibleCreate = true;
  }

  changeAproved(id: string) {
    this.specieService.changeArticleVisibility(id).subscribe(resp => {
      this.loadArticles();
      this.messageAdd();
    });
  }

  saveCreatedArticle() {
    if (this.createForm.valid) {
      const formValues = this.createForm.value;
      const id: string = localStorage.getItem('USER_ID') ?? '';
      const newArticle: CreateArticle = {
        title: formValues.title,
        text: formValues.text,
        specieId: formValues.specie?.id || '',
        type: formValues.type?.code || '',
        medias: [''],
        userId: id
      };

      

      this.articleService.createArticle(newArticle).subscribe(response => {
        this.visibleCreate = false;
        this.loadArticles();
        this.messageAdd();
      });
    } else {
      this.messageService.add({ severity: 'warn', summary: 'Warning', detail: 'Please fill out the form correctly.' });
    }
  }

  showDetails(uuid: string) {
    this.articleService.getArticleDetails(uuid).subscribe(resp => {
      this.articlesEdit = resp;
      this.editForm = this.fb.group({
        approved: [this.articlesEdit.approved, Validators.required],
        text: [this.articlesEdit.text, Validators.required],
        specie: [null, Validators.required],
        type: [{ name: this.articlesEdit.type, code: this.articlesEdit.type.toUpperCase() }, Validators.required]
      });
      this.visibleEdit = true;
    });
  }

  messageAdd() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: `Article added.` });
  }

  messageEdit() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: `Article edited.` });
  }

  messageRemoved() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: `Article deleted.` });
  }
}
