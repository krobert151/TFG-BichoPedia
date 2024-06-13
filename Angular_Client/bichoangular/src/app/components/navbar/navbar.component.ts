import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { UserService } from '../../services/user.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  items: MenuItem[] | undefined;
  constructor(private userService: UserService, private router: Router) { }


  ngOnInit() {
    this.items = [{
      label: 'BichoPedia',
      items: [
        { label: 'Species', icon: 'pi pi-book', routerLink: ['/species'] },
        { label: 'Encounters', icon: 'pi pi-mobile', routerLink: ['/encounters'] },
        { label: 'Articles', icon: 'pi pi-list', routerLink: ['/articles'] },
        { label: 'Users', icon: 'pi pi-user', routerLink: ['/users'] },
        {
          label: 'Log Out', icon: 'pi pi-sign-out', command: () => {
            this.userService.logout().subscribe(

            );
            this.router.navigate(['/'])
          }
        },
      ]
    }];
  }
}
