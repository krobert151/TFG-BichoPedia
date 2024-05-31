import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../../services/article.service';
import { ArticleEspecieResponse } from '../../../models/articles-resoponses/articles-resoponses.module';
import { TableRowCollapseEvent, TableRowExpandEvent } from 'primeng/table';
import { MessageService } from 'primeng/api';
import { ArticleDetails } from '../../../models/article-details/article-details.module';

@Component({
  selector: 'app-article-table',
  templateUrl: './article-table.component.html',
  styleUrl: './article-table.component.css',
  providers: [MessageService]

})
export class ArticleTableComponent implements OnInit {



  showDetails(_t31: any) {
    throw new Error('Method not implemented.');
  }

  articleResponses!: ArticleEspecieResponse[];

  expandedRows: any[] = [];

  constructor(private articleService: ArticleService, private messageService: MessageService) { }

  ngOnInit() {
    this.loadArticles();
  }
  loadArticles() {
    this.articleService.allArticles().subscribe(resp => {
      this.articleResponses = resp
    })
  }

  approve(id: string) {
    this.articleService.approve(id).subscribe(resp => {
      this.loadArticles();
      this.messageAdd()
    })
  }

  deny(id: string) {
    this.articleService.deny(id).subscribe(resp => {
      this.loadArticles();
      this.messageAdd()
    })
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

