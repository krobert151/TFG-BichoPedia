import { Component, OnInit,  ViewChild} from '@angular/core';
import {MatPaginator } from '@angular/material/paginator';

import { SpecieItemResponse } from '../../../models/specie/specie.module';

import { Router } from '@angular/router';
import { SpecieService } from '../../../services/specie.service';
import { MultiSelectChangeEvent, MultiSelectModule, MultiSelectSelectAllChangeEvent } from 'primeng/multiselect';



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

})
export class SpecieComponent implements OnInit {

  search = '?search='

  list: SpecieItemResponse[] = [];
  selectAll = false;

  dangers!:Danger[];
  selectDangers!: Danger[];
  searchDangers: string = '';


  types!:Type[];
  selcetTypes!:Type[];
  searchTypes: string = '';

  scName: string | undefined;

  setDangers(){
    this.dangers =[
      {name:'CR',code:'CR'},
      {name:'EN',code:'EN'},
      {name:'EW',code:'EW'},
      {name:'EX',code:'EX'},
      {name:'LC',code:'LC'},
      {name:'NT',code:'NT'},
      {name:'VU',code:'VU'},
    ]
  }


  setTypes(){
    this.types =[
      {name:'Arachnid',code:'Arachnid'},
      {name:'Amphibian',code:'Amphibian'},
      {name:'Bird',code:'Bird'},
      {name:'Lizzard',code:'Lizzard'},
      {name:'Snake',code:'Snake'},
      {name:'Mammal',code:'Mammal'},
    ]
  }

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  constructor(
    private router: Router,
    private service: SpecieService  ) {
  }

  
  delete(id:string){
    this.service.deleteSpecie(id).subscribe();
    this.onSearch();

  }

  searchByScientifName(){
    return this.search+'scientificName:*'+this.scName+'*';
  }

    
  typeOnChange(event: MultiSelectChangeEvent) {

    if(this.selcetTypes.length == 0){
      this.searchTypes = ''
    }
    if(this.selcetTypes.length == 1){
      this.searchTypes = `( type:${this.selcetTypes[0].name} )`
    }
    if(this.selcetTypes.length > 1){
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
    if(this.selectDangers.length == 0){
      this.searchDangers = ''
    }
    if(this.selectDangers.length == 1){
      this.searchDangers = `( type:${this.selectDangers[0].name} )`
    }
    if(this.selectDangers.length > 1){
      this.searchDangers = '(';

      this.selectDangers.forEach(type => {
        this.searchDangers = this.searchDangers.concat(` danger:${type.name} OR`);
      });
      
      this.searchDangers = this.searchDangers.slice(0, -3); 
      this.searchDangers = this.searchDangers.concat(' )');
      
    }
    console.log(this.searchDangers);
    this.onSearch()  }

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
    }else{
      if (this.searchTypes !== '') {
        keyword += `${this.search}${this.searchTypes}`;
        if (this.searchDangers !== '') {
          keyword += ` AND ${this.searchDangers}`;
        }
      }else{
        if (this.searchDangers !== '') {
          keyword += `${this.search}${this.searchDangers}`;
        }
      }
    }


    this.fetchSpecies(keyword);
}


  fetchSpecies(keyword: string): void {
    this.service.allSpecies(keyword).subscribe((x) => {
      this.list = x;
    });
  }

  editSpecie(item: SpecieItemResponse): void {
    this.router.navigate(['/species/edit', { item: JSON.stringify(item) }]);
  }
  getPhoto(photo:string,width:number,height:number){
    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`
  }






}