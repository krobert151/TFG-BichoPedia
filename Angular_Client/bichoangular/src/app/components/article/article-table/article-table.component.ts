import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../../services/article.service';
import { ArticleEspecieResponse } from '../../../models/articles-resoponses/articles-resoponses.module';
import { TableRowCollapseEvent, TableRowExpandEvent } from 'primeng/table';
import { MessageService } from 'primeng/api';
import { ArticleDetails } from '../../../models/article-details/article-details.module';
import { SpecieService } from '../../../services/specie.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SpecieNameResponse } from '../../../models/specie/species-names.module';
import { FileSelectEvent } from 'primeng/fileupload';
import { CreateArticle } from '../../../models/create-article/create-article.module';

interface Type {
  name: string;
  code: string;
}

@Component({
  selector: 'app-article-table',
  templateUrl: './article-table.component.html',
  styleUrl: './article-table.component.css',
  providers: [MessageService]
})
export class ArticleTableComponent implements OnInit {
showDetails(arg0: any) {
throw new Error('Method not implemented.');
}
  types!: Type[];

  setTypes() {
    this.types = [
      { name: 'Info', code: 'INFO' },
      { name: 'Identification', code: 'IDENTIFICATION' },
      { name: 'Cares', code: 'CARES' }
    ];
  }

  visibleCreate: boolean = false;
  createForm!: FormGroup;
  uploadedFiles: File[] = [];
  articleResponses!: ArticleEspecieResponse[];
  speciesNames!: SpecieNameResponse[];
  expandedRows: any[] = [];

  constructor(
    private articleService: ArticleService,
    private specieService: SpecieService,
    private messageService: MessageService,
    private fb: FormBuilder
  ) {
    this.initCreateForm();
  }

  initCreateForm() {
    this.createForm = this.fb.group({
      title: new FormControl<string>('', Validators.required),
      text: new FormControl<string>('', Validators.required),
      specie: new FormControl<SpecieNameResponse | null>(null, Validators.required),
      type: new FormControl<Type | null>(null, Validators.required)
    });
  }

  ngOnInit() {
    this.setTypes();
    this.specieService.allSpeciesNames().subscribe(resp => {
      this.speciesNames = resp;
    });
    this.loadArticles();
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
        medias: ['']
        ,
        userId: id
      };

      this.articleService.createArticle(newArticle).subscribe(
        response => {
          this.visibleCreate = false;
          this.loadArticles();
          this.messageAdd();
        
      });
    } else {
      this.messageService.add({ severity: 'warn', summary: 'Warning', detail: 'Please fill out the form correctly.' });
    }
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
