package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;


/**
 * Interfaz del Dao para la relación entre Zona y Formas de Envío de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IZonaFormaEnvioWebDao extends IGenericDao<ZonaFormaEnvio> {

	/**
	 * Devuelve la relación entre una Zona y sus Formas de Envío.
	 *  
	 * @param id Id de la Zona.
	 * 
	 * @return Listado de ZonaFormaEnvio que contiene la relación entre una Zona y sus Formas de Envío.
	 */
	public List<ZonaFormaEnvio> findByIdZona(Long id);
}
