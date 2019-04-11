package com.hbt.semillero.servicios.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.LineaDTO;
import com.hbt.semillero.dto.MarcaDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.ResultadoDTO;

/**
 * Expone los métodos del EJB ConsultasEJB
 * 
 * @author Johnny Soto
 *
 */
@Local
public interface IConsultasEjbLocal {

	/**
	 * Consulta las marcas existentes en el sistema.
	 * 
	 * @return
	 */
	public List<MarcaDTO> consultarMarcasExistentes();

	/**
	 * Consulta una lista de todas líneas asociadas a una marca registrada en el
	 * sistema.
	 */
	public List<LineaDTO> consultarLineasPorMarca(Long idMarca);

	/**
	 * Consulta las personas que cumplan con los criterios ingresados.
	 * 
	 * @param tipoID
	 * @param numID
	 * @return
	 */
	public List<PersonaDTO> consultarPersonas(String tipoID, String numID);

	/**
	 * Crea las personas en sus diferentes roles dentro del sistema.
	 * 
	 * @param personaDTO
	 * @return
	 */
	public ResultadoDTO crearPersona(PersonaDTO personaDTO);

}
