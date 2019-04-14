import { MarcaServicioDTO } from './marcaServicioDTO';

export interface LineaServicioDTO {
    //Los datos son iguales que el DTO en Java
    idLinea: number;
    nombre: string;
    cilindraje: string;
    marca: MarcaServicioDTO;
}
