import { Component, OnInit } from '@angular/core';
import { SpecieDetailsResponse } from '../../../models/specie-details/specie-details.module';
import { ActivatedRoute } from '@angular/router';
import { SpecieService } from '../../../services/specie.service';

@Component({
  selector: 'app-specie-details-info',
  templateUrl: './specie-details-info.component.html',
  styleUrl: './specie-details-info.component.css'
})
export class SpecieDetailsInfoComponent implements OnInit {


  id!:string|null;
  specie!:SpecieDetailsResponse;

  constructor(private route: ActivatedRoute, private service: SpecieService){};

  ngOnInit(): void {
    this.id=this.route.snapshot.paramMap.get('id');
    if(this.id!=null){
      this.service.findById(this.id).subscribe(resp=>{
        this.specie=resp;
      })
    }
  }
  getPhoto(photo:string,width:number,height:number){
    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`
  }

}
