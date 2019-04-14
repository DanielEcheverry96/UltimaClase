import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from '../template.service';
import { MarcaServicioDTO } from '../../capa/principal/gestion-vehiculos/modelo/marcaServicioDTO';

@Injectable({
    providedIn: 'root'
})
export class ConsultaMarcasService extends AbstractService {

    /**
   * Constructor
   */
  constructor(injector: Injector) {
    super(injector);
  }

  public consultarMarcas(): Observable<MarcaServicioDTO[]> {
    return this.get<MarcaServicioDTO[]>('/semillero-servicios', '/ConsultasRest/consultarMarcas');
  }

}
