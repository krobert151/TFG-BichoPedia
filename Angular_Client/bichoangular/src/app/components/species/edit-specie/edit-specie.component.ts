import { Component } from '@angular/core';
import { SpecieItemResponse } from '../../../models/specie/specie.module';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SpecieService } from '../../../services/specie.service';
import { SpecieUpdate } from '../../../models/update-specie/update-specie.module';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-edit-specie',
  templateUrl: './edit-specie.component.html',
  styleUrls: ['./edit-specie.component.css']
})
export class EditSpecieComponent {

  item: SpecieItemResponse | null = null;
  file?: File;

  dangers: string[] = [
    'CR',
    'EN',
    'EW',
    'EX',
    'LC',
    'NT',
    'VU'
  ]

  form = new FormGroup({
    scientificName: new FormControl('', Validators.required), // Initial value is empty
    danger: new FormControl('', Validators.required), // Add danger control
    type: new FormControl('', Validators.required), // Initial value is empty
  });
  constructor(private route: ActivatedRoute, private service: SpecieService,
    private router: Router,
    private snackBar: MatSnackBar) {}

  onFilechange(event: any) {
    console.log(event.target.files[0])
    this.file = event.target.files[0]
  }

  ngOnInit(): void {
    const itemParam = this.route.snapshot.paramMap.get('item');
    if (itemParam) {
      this.item = JSON.parse(itemParam);
      // Set initial values for scientificName and type
      this.form.patchValue({
        scientificName: this.item!.scientificName,
        type: this.item!.type
      });
    }
  }

  edit() {
    if (this.item) {
      const specieToUpdate: SpecieUpdate = {
        id: this.item.id,
        scientificName: this.form.get('scientificName')!.value!,
        danger: this.form.get('danger')!.value!, // Get danger value from form
        mainPhoto: this.file?.name.toString()!,
        type: this.form.get('type')!.value!,
      };
      console.log(specieToUpdate);
      this.service.editSpecie(specieToUpdate).subscribe(
        () => {
          this.snackBar.open('Specie updated successfully', 'Close', { duration: 3000 });
          // Navegar a la página anterior
          this.router.navigate(['/species']);
        },
        (error) => {
          this.snackBar.open('Failed to update specie', 'Close', { duration: 3000 });
          console.error(error); // Imprimir el error en la consola para fines de depuración
        }
      );      console.log(specieToUpdate);

    }
  }

}
