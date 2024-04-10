import { Component, Input } from '@angular/core';
import { Article } from '../../../models/specie-details/specie-details.module';
import { GalleriaModule } from 'primeng/galleria';

@Component({
  selector: 'app-specie-article',
  templateUrl: './specie-article.component.html',
  styleUrl: './specie-article.component.css'
})
export class SpecieArticleComponent {


  @Input({required:true}) article!:Article;

  getPhoto(photo:string,width:number,height:number){
    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`
  }

  getPhotoNonScaled(photo:string){
    return `http://localhost:8080/download/${photo}`
  }

}
