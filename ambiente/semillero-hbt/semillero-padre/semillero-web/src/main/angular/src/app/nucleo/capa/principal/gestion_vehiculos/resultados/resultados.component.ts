import { Component, Input, OnInit } from '@angular/core';

import { ConsultaVehiculosService } from '../../../../../nucleo/servicios/consulta-vehiculos-servicio/consulta.vehiculo.service'
import { VehiculoDTO } from '../modelo/vehiculoDTO';
import { PersonaServicioDTO } from '../modelo/personaServicioDTO';

@Component({
  selector: 'app-vehiculos-resultados',
  templateUrl: './resultados.component.html'
})
export class ResultadosComponent implements OnInit {
  @Input() public marca:string;
  @Input() public placa:string;
  
  public listaVehiculoDTO: VehiculoDTO[]; 
  public vehiculoDTO: VehiculoDTO; 
  
  public listaPersonasDTO: PersonaServicioDTO[]; 
  public personaDTO: PersonaServicioDTO; 
  
  constructor(private vehiculosService: ConsultaVehiculosService) { }

  ngOnInit() {
  }

  public consultarPersonas() {
	    let tipoID: string = '1';
	    let numID: string = '123';
	  	this.vehiculosService.consultarPersonas(tipoID, numID).subscribe(
	    	personas => {
		        this.listaPersonasDTO = personas;
		      },
		      error => {
		        console.log(error);
		      } 
	    );    
	    console.log('resultado servicio.... ' + this.listaPersonasDTO)
   }
   
   public listarVehiculos() {
	    this.listaVehiculoDTO = [];
	  	this.vehiculosService.consultarListaVehiculos().subscribe(
	    	veh => {
		        this.marca = veh.marca;
		      },
		      error => {
		        console.log(error);
		      } 
	    );    
   }
}

