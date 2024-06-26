import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../../services/article.service';
import { ArticleEspecieResponse } from '../../../models/article/articles-resoponses.module';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ArticleDetails } from '../../../models/article/article-details.module';
import { SpecieService } from '../../../services/specie.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SpecieNameResponse } from '../../../models/specie/species-names.module';
import { FileSelectEvent } from 'primeng/fileupload';
import { CreateArticle } from '../../../models/article/create-article.module';
import { FileService } from '../../../services/file.service';
import { EditArticle } from '../../../models/article/article-edit.module';

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
  articleDetails: ArticleDetails = {
    id: '',
    title: '',
    text: '',
    approved: false,
    type: '',
    archives: [],
    createdBy: '',
    specieId: ''
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
    this.initEditForm()
  }
  setTypes() {
    this.types = [
      { name: 'Info', code: 'INFO' },
      { name: 'Identification', code: 'IDENTIFICATION' },
      { name: 'Cares', code: 'CARES' }
    ];
  }
  ngOnInit() {
    this.setTypes();
    this.specieService.allSpeciesNames().subscribe(resp => {
      this.speciesNames = resp;
    });
    this.loadArticles();
  }


  initCreateForm() {
    this.createForm = this.fb.group({
      title: ['', Validators.required],
      text: ['', Validators.required],
      specie: [null, Validators.required],
      type: [null, Validators.required]
    });
  }
  initEditForm() {
    this.editForm = this.fb.group({
      text: [this.articleDetails.text, Validators.required],
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
            this.articleDetails.archives = this.articleDetails.archives.filter(e => e !== image)
            this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: image+' deleted' });
        },
        reject: () => {
            this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
        }
    });
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



  changeAproved(id: string) {
    this.specieService.changeArticleVisibility(id).subscribe(resp => {
      this.loadArticles();
      this.messageService.add({ severity: 'info', summary: resp.approved?'Denied':`Approved`});
    });
  }

  showCreateDialog() {
    this.visibleCreate = true;
  }


  showDetails(uuid: string) {
    this.articleService.getArticleDetails(uuid).subscribe(resp => {
      this.articleDetails = resp;
      this.editForm.patchValue({
        text: this.articleDetails.text,
        specie: this.speciesNames.find(t => t.id === this.articleDetails.specieId),
        type: this.types.find(t => t.code === this.articleDetails.type.toUpperCase())
      });
      this.visibleEdit = true;
    });
  }

  saveEditArticle(){
    if(this.editForm.valid){
      const formValues = this.editForm.value;
      const newArticle: EditArticle ={
        approved: this.articleDetails.approved,
        text: formValues.text,
        medias: this.articleDetails.archives,
        userId: '',
        specieId: formValues.specie?.id || '',
        type: formValues.type?.code || '',
      };
      if (this.uploadedFiles.length != 0) {
        this.uploadedFiles.forEach(file => {
          newArticle.medias.push(file.name);
        });
        this.uploadedFiles.forEach(x => {
          this.fileService.uploadImage(x).subscribe(
            resp => {
            }, error => {
              this.messageService.add({ severity: 'error', summary: 'Error', detail: `Failed to upload ${x.name}.` });
            }
          );
        });
        this.uploadedFiles = [];
      }
      this.articleService.editArticle(newArticle,this.articleDetails.id).subscribe(response=>{
        this.visibleEdit = false;
        this.loadArticles();
        this.messageService.add({ severity: 'success', summary: 'Added', detail: `${response.title} was edited.` });
        this.initEditForm();
      })
    }
    else {
      this.messageService.add({ severity: 'warn', summary: 'Warning', detail: 'Please fill out the form correctly.' });
    }
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
        medias: [],
        userId: id
      };
      if (this.uploadedFiles.length != 0) {
        this.uploadedFiles.forEach(file => {
          newArticle.medias.push(file.name);
        });
        this.uploadedFiles.forEach(x => {
          this.fileService.uploadImage(x).subscribe(
            resp => {
              this.messageService.add({ severity: 'success', summary: 'Success', detail: `${x} added.` });
            }
          );
        });
        this.uploadedFiles = [];
      }        
      this.articleService.createArticle(newArticle).subscribe(response => {
        this.visibleCreate = false;
        this.loadArticles();
        this.messageService.add({ severity: 'success', summary: 'Success', detail: `${response.title} added.` });
        this.initCreateForm();
      });
    } else {
      this.messageService.add({ severity: 'warn', summary: 'Warning', detail: 'Please fill out the form correctly.' });
    }
  }

}
