import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { UserItemResponse } from '../../../models/user/user-item-response.module';
import { ConfirmationService, MessageService } from 'primeng/api';
import { FileService } from '../../../services/file.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserDetailsResponse } from '../../../models/user/user-details-response.module';
import { UpdateUserPermisions } from '../../../models/user/update-permisions.module';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css'],
  providers: [ConfirmationService, MessageService]
})
export class UserTableComponent implements OnInit {
  userPermissionsModal: boolean = false;

  userDetailResponse: UserDetailsResponse = {
    id: '',
    username: '',
    email: '',
    profilePhoto: '',
    roles: [],
    encounters: [],
    articles: [],
    savedLists: [],
    level: 0,
    exp: 0,
    percentExp: 0,
    accountNonExpired: false,
    accountNonLocked: false,
    credentialsNonExpired: false,
    enabled: false,
    createdAt: '',
    old: '',
    passwordExpiredAt: ''
  };

  usersResponses!: UserItemResponse[];
  userPermissionsForm!: FormGroup;

  constructor(
    private userService: UserService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private fileService: FileService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.search();
    this.initUserPermissionsForm();
  }

  initUserPermissionsForm() {
    this.userPermissionsForm = this.fb.group({
      accountNonExpired: [this.userDetailResponse.accountNonExpired, Validators.required],
      accountNonLocked: [this.userDetailResponse.accountNonLocked, Validators.required],
      credentialsNonExpired: [this.userDetailResponse.credentialsNonExpired, Validators.required],
      enabled: [this.userDetailResponse.enabled, Validators.required]
    });
  }

  search() {
    this.userService.getAllUsers('').subscribe(resp => {
      this.usersResponses = resp;
    });
  }

  changePermissions(user: UserItemResponse, role: string) {
    let roles = { roles: [...user.roles] };

    if (!roles.roles.includes(role)) {
      roles.roles.push(role);
    } else {
      const index = roles.roles.indexOf(role);
      if (index > -1) {
        roles.roles.splice(index, 1);
      }
    }

    this.userService.updateRoles(user.id, roles.roles).subscribe(resp => {
      this.search();
    });
  }

  showPermissionsOptions(id: string) {
    this.userService.getUserDetails(id).subscribe(resp => {
      this.userDetailResponse = resp;
      this.userPermissionsForm.patchValue({
        accountNonExpired: this.userDetailResponse.accountNonExpired,
        accountNonLocked: this.userDetailResponse.accountNonLocked,
        credentialsNonExpired: this.userDetailResponse.credentialsNonExpired,
        enabled: this.userDetailResponse.enabled
      });
      this.userPermissionsModal = true;
    });
  }

  saveUserPermissions() {
    if (this.userPermissionsForm.valid) {
      const formValues = this.userPermissionsForm.value;
      const permissions: UpdateUserPermisions = {
        accountNonExpired: formValues.accountNonExpired,
        accountNonLocked: formValues.accountNonLocked,
        credentialsNonExpired: formValues.credentialsNonExpired,
        enabled: formValues.enabled
      };
  
      this.userService.updateUserPermissions(this.userDetailResponse.id, permissions).subscribe(response => {
        this.userPermissionsModal = false;
        this.search();
        this.messageService.add({ severity: 'success', summary: 'Success', detail: `${response.username}'s permissions updated.` });
      });
    } else {
      this.messageService.add({ severity: 'warn', summary: 'Warning', detail: 'Please fill out the form correctly.' });
    }
  }

  getPhoto(photo: string, width: number, height: number) {
    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`;
  }
}
