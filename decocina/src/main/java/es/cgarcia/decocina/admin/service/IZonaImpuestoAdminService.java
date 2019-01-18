package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.web.model.ZonaImpuesto;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una ZonaImpuesto
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IZonaImpuestoAdminService extends IGenericService<ZonaImpuesto>{
	
	/**
	 * Devuelve las ZonaImpuesto a partir del id de Zona.
	 * 
	 * @param id Id de la Zona.
	 * @return Listado de ZonaImpuesto de la Zona.
	 */
	public List<ZonaImpuesto> findByIdZona(Long id);
}
