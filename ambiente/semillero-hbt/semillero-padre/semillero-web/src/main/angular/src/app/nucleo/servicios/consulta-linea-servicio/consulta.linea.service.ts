import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from '../template.service';
import { LineaServicioDTO } from '../../capa/principal/gestion-vehiculos/modelo/lineaServicioDTO';

@Injectable({
    providedIn: 'root'
})
export class ConsultaLineasService extends AbstractService {

    /**
   * Constructor
   */
  constructor(injector: Injector) {
    super(injector);
  }

  public consultarLineas(): Observable<LineaServicioDTO[]> {
    return this.get<LineaServicioDTO[]>('/semillero-servicios', '/ConsultasRest/consultarLineas');
  }

}
