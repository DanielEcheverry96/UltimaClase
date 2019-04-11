package com.hbt.semillero.servicios.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hbt.semillero.dto.LineaDTO;
import com.hbt.semillero.dto.MarcaDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidades.Comprador;
import com.hbt.semillero.entidades.Linea;
import com.hbt.semillero.entidades.Marca;
import com.hbt.semillero.entidades.Persona;
import com.hbt.semillero.entidades.Vendedor;
import com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal;

/**
 * EJB de consultas
 */
@Stateless
public class ConsultasEJB implements IConsultasEjbLocal {

	@PersistenceContext
	private EntityManager em;

	/**
	 * {@link com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#consultarMarcasExistentes()}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MarcaDTO> consultarMarcasExistentes() {
		List<Marca> marcas = em.createQuery("Select ma from Marca ma").getResultList();
		List<MarcaDTO> marcasRetorno = new ArrayList<>();
		for (Marca marca : marcas) {
			MarcaDTO marcaDto = construirMarcaDTO(marca);
			marcasRetorno.add(marcaDto);
		}
		return marcasRetorno;
	}

	/**
	 * {@link com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#consultarLineasPorMarca(Long)}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<LineaDTO> consultarLineasPorMarca(Long idMarca) {
		List<Linea> lineas = em
				.createQuery("Select ln from Linea ln JOIN FETCH ln.marca where ln.marca.idMarca=:idMarca ")
				.setParameter("idMarca", idMarca).getResultList();
		List<LineaDTO> lineasRetorno = new ArrayList<>();
		for (Linea linea : lineas) {
			LineaDTO lineaDTO = new LineaDTO();
			lineaDTO.setIdLinea(linea.getIdLinea());
			lineaDTO.setNombre(linea.getNombre());
			lineaDTO.setCilindraje(linea.getCilindraje());
			lineaDTO.setMarca(construirMarcaDTO(linea.getMarca()));
			lineasRetorno.add(lineaDTO);
		}
		return lineasRetorno;
	}

	/**
	 * {@link com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#consultarPersonas(String, String)}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonaDTO> consultarPersonas(String tipoID, String numID) {
		StringBuilder consulta = new StringBuilder("Select per FROM Persona per WHERE 1=1 ");
		Map<String, Object> parametros = new HashMap<>();
		if (tipoID != null) {
			consulta.append(" and per.numeroIdentificacion =:tipoID");
			parametros.put("tipoID", tipoID);
		}
		if (numID != null) {
			consulta.append("and per.numeroIdentificacion =:numID");
			parametros.put("numID", numID);
		}
		Query query = em.createQuery(consulta.toString());

		for (Entry<String, Object> entry : parametros.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue()).getResultList();
		}

		List<Persona> personas = query.getResultList();
		List<PersonaDTO> personasRetorno = new ArrayList<>();
		for (Persona persona : personas) {
			PersonaDTO personaDTO = new PersonaDTO();
			personaDTO.setNombres(persona.getNombres());
			personaDTO.setApellidos(persona.getApellidos());
			personaDTO.setNumeroIdentificacion(persona.getNumeroIdentificacion());
			personaDTO.setTipoIdentificacion(persona.getTipoIdentificacion());
			personaDTO.setNumeroTelefonico(persona.getNumeroTelefonico());
			personaDTO.setEdad(persona.getEdad());

			personasRetorno.add(personaDTO);
		}
		return personasRetorno;
	}

	/**
	 * {@link com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#crearPersona(PersonaDTO)}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ResultadoDTO crearPersona(PersonaDTO personaDTO) {
		try {
			Persona persona = asignarAtributosBasicos(personaDTO);
			em.persist(persona);
			if (personaDTO.isComprador()) {
				Comprador comprador = new Comprador();
				comprador.setPersona(persona);
				comprador.setFechaAfiliacion(Calendar.getInstance());
				em.persist(comprador);
			}
			if (personaDTO.isVendedor()) {
				Vendedor vendedor = new Vendedor();
				vendedor.setFechaIngreso(Calendar.getInstance());
				vendedor.setPersona(persona);
				em.persist(vendedor);
			}
		} catch (Exception e) {
			return new ResultadoDTO(false, e.getMessage());
		}

		return new ResultadoDTO(true, "Creado de forma exitosa");
	}

	/**
	 * Asigna los atributos básicos de la persona
	 * 
	 * @param persona
	 * @param personaDTO
	 */
	private Persona asignarAtributosBasicos(PersonaDTO personaDTO) {
		Persona persona = new Persona();
		persona.setNombres(personaDTO.getNombres());
		persona.setApellidos(personaDTO.getApellidos());
		persona.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
		persona.setTipoIdentificacion(personaDTO.getTipoIdentificacion());
		persona.setNumeroTelefonico(personaDTO.getNumeroTelefonico());
		persona.setEdad(personaDTO.getEdad());
		return persona;
	}

	/**
	 * Construye un DTO de MarcaDTO
	 * 
	 * @param marca
	 * @return
	 */
	private MarcaDTO construirMarcaDTO(Marca marca) {
		MarcaDTO marcaDto = new MarcaDTO();
		marcaDto.setIdMarca(marca.getIdMarca());
		marcaDto.setNombre(marca.getNombre());
		return marcaDto;
	}

}
