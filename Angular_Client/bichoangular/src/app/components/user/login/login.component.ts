import { Component } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private userService:UserService){}

  profileLogin= new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })


  login(){
    this.userService.LoginResponse(this.profileLogin.value.username!,this.profileLogin.value.password!).subscribe(p=>{
      localStorage.setItem('TOKEN',p.token)
      localStorage.setItem('USER_ID',p.id)
      if (localStorage.getItem('TOKEN') != undefined)
        window.location.href = "http://localhost:4200/home-page"
    })
  }

}
