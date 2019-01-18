package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.ZonaImpuesto;


/**
 * Interfaz del Dao para la relación entre Zona e Impuestos de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IZonaImpuestoAdminDao extends IGenericDao<ZonaImpuesto> {

	/**
	 * Devuelve la relación entre una Zona y sus Impuestos.
	 *  
	 * @param id Id de la Zona.
	 * 
	 * @return Listado de ZonaImpuesto que contiene la relación entre una Zona y sus Impuestos.
	 */
	public List<ZonaImpuesto> findByIdZona(Long id);
	
}
