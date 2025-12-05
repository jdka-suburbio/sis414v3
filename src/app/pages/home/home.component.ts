import { Component, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MenuComponent } from '../../commons/menu/menu.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MenuComponent, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  private http = inject(HttpClient);
  private baseUrl = 'https://designed-rank-unscowling.ngrok-free.dev';

  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'ngrok-skip-browser-warning': 'true'
  });

  items: any = [];

  // Para crear/editar
  showForm = false;
  editing = false;
  formData = { id: null, name: '', description: '' };

  constructor() {
    this.getCareers();
  }

  getCareers() {
    this.http.get(this.baseUrl + "/career", { headers: this.headers })
      .subscribe(data => this.items = data);
  }

  remove(id: any) {
    this.http.delete(this.baseUrl + "/career/" + id, { headers: this.headers })
      .subscribe(() => this.getCareers());
  }

  // ABRIR FORMULARIO PARA AGREGAR
  openCreate() {
    this.editing = false;
    this.formData = { id: null, name: '', description: '' };
    this.showForm = true;
  }

  // ABRIR FORMULARIO PARA EDITAR
  openEdit(item: any) {
    this.editing = true;
    this.formData = {
      id: item.id,
      name: item.name,
      description: item.description
    };
    this.showForm = true;
  }

  // GUARDAR CREAR/EDITAR
  save() {
    const body = {
      name: this.formData.name,
      description: this.formData.description
    };

    if (this.editing) {
      // EDITAR
      this.http.put(this.baseUrl + "/career/" + this.formData.id, body, { headers: this.headers })
        .subscribe(() => {
          this.showForm = false;
          this.getCareers();
        });

    } else {
      // CREAR
      this.http.post(this.baseUrl + "/career", body, { headers: this.headers })
        .subscribe(() => {
          this.showForm = false;
          this.getCareers();
        });
    }
  }
}
