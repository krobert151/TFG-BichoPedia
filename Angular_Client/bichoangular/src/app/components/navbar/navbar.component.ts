import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  items: MenuItem[] | undefined;
  
  ngOnInit() {
      this.items = [{
          items: [
              {label: 'Species', icon: 'pi pi-plus', routerLink: ['/species']}
          ]
      }];
  }
}