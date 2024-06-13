import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { UserItemResponse } from '../../../models/user/user-item-response.module';
import { ConfirmationService, MessageService } from 'primeng/api';
import { FileService } from '../../../services/file.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserDetailsResponse } from '../../../models/user/user-details-response.module';
import { UpdateUserPermisions } from '../../../models/user/update-permisions.module';
import { CreateUser } from '../../../models/user/new-user.module';
import { FileSelectEvent } from 'primeng/fileupload';
import { UpdateUserInfo } from '../../../models/user/update-user-info.module';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css'],
  providers: [ConfirmationService, MessageService]
})
export class UserTableComponent implements OnInit {


  file!: File | null;



  userPermissionsModal: boolean = false;
  userEditVisible: boolean = false
  userDetailsVisible: boolean = false;
  userCreateVisible: boolean = false;

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

  roles: string[] = ['ADMIN', 'WRITER']

  usersResponses!: UserItemResponse[];
  userPermissionsForm!: FormGroup;
  userCreateForm!: FormGroup;
  emailEdit!: string;
  idEdit!: string;
  photoName!: string;

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
    this.initUserCreateForm();
  }

  initUserPermissionsForm() {
    this.userPermissionsForm = this.fb.group({
      accountNonExpired: [this.userDetailResponse.accountNonExpired, Validators.required],
      accountNonLocked: [this.userDetailResponse.accountNonLocked, Validators.required],
      credentialsNonExpired: [this.userDetailResponse.credentialsNonExpired, Validators.required],
      enabled: [this.userDetailResponse.enabled, Validators.required]
    });
  }
  initUserCreateForm() {
    this.userCreateForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      roles: [[], Validators.required]
    })
  }
  search() {
    this.userService.getAllUsers('').subscribe({
      next: resp => {
        this.usersResponses = resp;
      }, error: error => {
        console.log(error)
        this.messageService.add({ severity: 'error', summary: 'Warning', detail: error.error.message });
      }
    });
  }

  changePermissions(user: UserItemResponse, role: string) {
    let roles = { roles: [...user.roles] };
    if (!roles.roles.includes(role)) {
      this.confirmationService.confirm({
        message: `Do you want to add ${role} permissions to this User?`,
        header: `Add ${role} permission`,
        icon: 'pi pi-info-circle',
        acceptButtonStyleClass: "p-button-text p-button-text",
        rejectButtonStyleClass: "p-button-danger p-button-text",
        acceptIcon: "none",
        rejectIcon: "none",
        accept: () => {
          roles.roles.push(role);
          this.userService.updateRoles(user.id, roles.roles).subscribe({
            next: resp => {
              this.messageService.add({ severity: 'info', summary: 'Added', detail: `${user.username} is now a ${role}` });
              this.search();
            }, error: error => {
              console.log(error)
              this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
            }
          });
        },
        reject: () => {
          this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
        }
      });
    } else {
      const index = roles.roles.indexOf(role);
      if (index > -1) {
        this.confirmationService.confirm({
          message: `Do you want to remove ${role} permissions to this User?`,
          header: `Remove ${role} permission`,
          icon: 'pi pi-info-circle',
          acceptButtonStyleClass: "p-button-text p-button-text",
          rejectButtonStyleClass: "p-button-danger p-button-text",
          acceptIcon: "none",
          rejectIcon: "none",
          accept: () => {
            roles.roles.splice(index, 1);
            this.userService.updateRoles(user.id, roles.roles).subscribe({
              next: resp => {
                this.messageService.add({ severity: 'info', summary: 'Removed', detail: `${user.username} is not a ${role}` });
                this.search();
              }, error: error => {
                console.log(error)
                this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
              }
            });
          },
          reject: () => {
            this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
          }
        });
      }
    }

  }

  showUserDetails(id: string) {
    this.userService.getUserDetails(id).subscribe({
      next: resp => {
        this.userDetailResponse = resp;
        this.userDetailsVisible = true;
      }, error: error => {
        console.log(error)
        this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
      }
    })
  }
  showUserCreate() {
    this.userCreateVisible = true
  }

  deleteUser(id: string) {
    this.confirmationService.confirm({
      message: `Do you want to delete this User?`,
      header: `Delete User`,
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass: "p-button-danger p-button-text",
      rejectButtonStyleClass: "p-button-text p-button-text",
      acceptIcon: "none",
      rejectIcon: "none",
      accept: () => {
        this.userService.deleteUser(id).subscribe({
          next: resp => {
            this.messageService.add({ severity: 'info', summary: 'Deleted', detail: 'user deleted' });
            this.search();
          }, error: error => {
            console.log(error)
            this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
          }
        })
      },
      reject: () => {
        this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
      }
    });
  }

  showPermissionsOptions(id: string) {
    this.userService.getUserDetails(id).subscribe({
      next: resp => {
        this.userDetailResponse = resp;
        this.userPermissionsForm.patchValue({
          accountNonExpired: this.userDetailResponse.accountNonExpired,
          accountNonLocked: this.userDetailResponse.accountNonLocked,
          credentialsNonExpired: this.userDetailResponse.credentialsNonExpired,
          enabled: this.userDetailResponse.enabled
        });
        this.userPermissionsModal = true;
      }, error: error => {
        console.log(error)
        this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
      }
    },);
  }
  saveUserCreate() {
    if (this.userCreateForm.valid) {
      const formValue = this.userCreateForm.value;
      const userCreate: CreateUser = {
        username: formValue.username,
        email: formValue.email,
        password: formValue.password,
        roles: ['USER'].concat(formValue.roles),
        profilePhoto: this.file?.name ?? ''
      }
      this.userService.createUser(userCreate).subscribe({
        next: resp => {
          if (this.file != null) {
            this.fileService.uploadImage(this.file);
          }
          this.messageService.add({ severity: 'success', summary: 'Success', detail: `${resp.username} created.` });
          this.userCreateVisible = false;
          this.search();
        },
        error: error => {
          console.log(error)
          this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
        }
      });
    }
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

      this.userService.updateUserPermissions(this.userDetailResponse.id, permissions).subscribe({
        next: response => {
          this.userPermissionsModal = false;
          this.search();
          this.messageService.add({ severity: 'success', summary: 'Success', detail: `${response.username}'s permissions updated.` });
        }, error: error => {
          console.log(error)
          this.messageService.add({ severity: 'warn', summary: 'Warning', detail: error.error.message });
        }
      });
    } else {
      this.messageService.add({ severity: 'warn', summary: 'Warning', detail: 'Please fill out the form correctly.' });
    }
  }
  editUserDetails(user: UserItemResponse) {
    this.emailEdit = user.email;
    this.idEdit = user.id;
    this.photoName = user.profilePhoto
    this.userEditVisible = true
  }
  onUpload($event: FileSelectEvent) {
    this.file = $event.files[0];
  }

  saveUserEdit(id: string) {
    const updateUser: UpdateUserInfo = {
      email: this.emailEdit,
      photo: this.file?.name ?? this.photoName
    }

    this.userService.updateUserInfo(id, updateUser).subscribe(resp => {
      this.messageService.add({ severity: 'success', summary: 'Success', detail: `user updated.` });
      this.fileService.uploadImage(this.file).subscribe(() => {
        this.file = null;
      });
      this.userEditVisible = false;
      this.emailEdit = ''
      this.search()
    })
  }


  getPhoto(photo: string, width: number, height: number) {
    return `http://localhost:8080/download/${photo}/scaled?width=${width}&height=${height}`;
  }
}
