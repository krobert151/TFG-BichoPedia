import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { UserItemResponse } from '../../../models/user/user-item-response.module';
import { ConfirmationService, MessageService } from 'primeng/api';
import { FileService } from '../../../services/file.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrl: './user-table.component.css',
  providers: [ConfirmationService,MessageService]
})
export class UserTableComponent implements OnInit{
  userPermisionsModal: boolean=false;


  
  usersResponses!: UserItemResponse[];

  constructor(
    private userService:UserService, 
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private fileService: FileService,
    private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.search()
  }

  search(){
    this.userService.getAllUsers('').subscribe(resp=>{
      this.usersResponses=resp;
    })
  }
  changePermisions(user: UserItemResponse, role:string) {
    let roles = { roles: [...user.roles] }; 
    

    if (!roles.roles.includes(role)) {
      roles.roles.push(role);
      console.log('lo pone')

    }else{

        const index = roles.roles.indexOf(role);
      if (index > -1) {
        roles.roles.splice(index, 1);
        console.log('lo quita')
      }
    }
  
  this.userService.updateRoles(user.id,roles.roles).subscribe(resp=>{
    this.search()
  })

  }
  
  showPermisionsOptions() {
    this.userPermisionsModal=true;
  }

  getPhoto(photo: string, width: number, height: number) {
    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`
  }
}
