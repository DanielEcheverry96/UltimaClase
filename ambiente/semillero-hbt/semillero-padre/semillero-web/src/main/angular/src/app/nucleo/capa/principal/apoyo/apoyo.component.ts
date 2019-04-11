import { Component, OnInit } from '@angular/core';
import { PersonaDTO } from  './modelo/PersonaDTO' 

@Component({
  selector: 'app-apoyo',
  templateUrl: './apoyo.component.html'
})
export class ApoyoComponent implements OnInit {
  
  public mostrarFormulario:boolean;
  
  public persona: PersonaDTO;
  public personas: PersonaDTO[];
  
  constructor() { }

  public ngOnInit() {
  	this.mostrarFormulario = true;
  	this.persona = {
        id: '0',
		nombre: '',
		apellido: '',
		tipoIdentificacion: '',
		numeroIdentificacion: '',
		mayorEdad:false,
		sexo:'',
    };
    this.personas = [];
  }

  mostrar(){
  	this.mostrarFormulario = true;
  }
  
  ocultar(){
  	this.mostrarFormulario = false;
  }
  
  public guardar() {
  	console.log('guardando....' + this.persona.nombre);
    this.personas.push(this.persona);
    console.log('cantidad de personas....' + this.personas.length);
    
  }
  public borrar(persona: PersonaDTO) {
  	console.log('borrando....');
    this.personas = this.personas.filter(p => p.nombre !== persona.nombre);
     
  }
  
  
}
