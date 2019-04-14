package com.hbt.semillero.dto;

import java.io.Serializable;



import com.hbt.semillero.entidades.Linea;

public class VehiculoDTO implements Serializable{


	private Long idVehiculo;

	private int modelo;

	private String placa;

	private LineaDTO linea;

	public Long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public int getModelo() {
		return modelo;
	}

	public void setModelo(int modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public LineaDTO getLinea() {
		return linea;
	}

	public void setLinea(LineaDTO linea) {
		this.linea = linea;
	}
	
	
}
