import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { ConsultaMarcasService } from '../../../../servicios/consulta-marca-servicio/consulta.marca.service';
import { MarcaServicioDTO } from '../modelo/marcaServicioDTO';
import { ConsultaLineasService } from '../../../../servicios/consulta-linea-servicio/consulta.linea.service';
import { LineaServicioDTO } from '../modelo/lineaServicioDTO';
import { ConsultaVehiculosService } from 'src/app/nucleo/servicios/consulta-vehiculo-servicio/consulta.vehiculo.service';
import { VehiculoDTO } from '../modelo/vehiculoDTO';

@Component({
  selector: 'app-filtros',
  templateUrl: './filtros.component.html',
  styleUrls: ['./filtros.component.css']
})
export class FiltrosComponent implements OnInit {

  public listaMarcasDTO: MarcaServicioDTO[];
  public listaLineasDTO: LineaServicioDTO[];
  public listaVehiculosDTO: VehiculoDTO[];
  public linea: string;
  public marca: string;


  // Mandar datos al padre
  @Output() VehiculosEmitter = new EventEmitter();


  constructor(
    private consultaMarcasService: ConsultaMarcasService,
    private consultaLineasService: ConsultaLineasService,
    private consultaVehiculoService: ConsultaVehiculosService
    ) { }

  ngOnInit() {
    this.consultarMarcas();
  }

  public consultarMarcas() {
    this.consultaMarcasService.consultarMarcas().subscribe(
      marcas => {
        this.listaMarcasDTO = marcas;
      },
      error => {
        console.log('Marcas error ->', error);
      }
    );
    console.log('resultado servicio marcas...', this.listaMarcasDTO);
  }

  public consultarLineas() {
    this.consultaLineasService.consultarLineas().subscribe(
      lineas => {
        this.listaLineasDTO = lineas;
      },
      error => {
        console.log('Lineas error ->', error);
      }
    );
    console.log('resultado servicio lineas...', this.listaLineasDTO);
  }

  consultarVehiculos() {
    this.consultaVehiculoService.consultarVehiculos(this.linea).subscribe(
      vehiculos => {
        this.listaVehiculosDTO = vehiculos;
        this.enviarVehiculos();
      },
      error => {
        console.log('Vehiculos error ->', error);
      }
    );
    console.log('resultado servicio vehiculos...', this.listaVehiculosDTO);
  }

  enviarVehiculos() {
    this.VehiculosEmitter.emit(this.listaVehiculosDTO);
  }
}
