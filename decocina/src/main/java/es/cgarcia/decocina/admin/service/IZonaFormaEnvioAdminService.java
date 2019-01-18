package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.web.model.ZonaFormaEnvio;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una ZonaFormaEnvio
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IZonaFormaEnvioAdminService extends IGenericService<ZonaFormaEnvio>{
	
	/**
	 * Devuelve las ZonaFormaEnvio a partir del id de Zona.
	 * 
	 * @param id Id de la Zona.
	 * @return Listado de ZonaImpuesto de la Zona.
	 */
	public List<ZonaFormaEnvio> findByIdZona(Long id);
}
