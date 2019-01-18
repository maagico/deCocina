package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;


/**
 * Interfaz del Dao para la relación entre Zona y Formas de Envío de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IZonaFormaEnvioAdminDao extends IGenericDao<ZonaFormaEnvio> {

	/**
	 * Devuelve la relación entre una Zona y sus Formas de Envío.
	 *  
	 * @param id Id de la Zona.
	 * 
	 * @return Listado de ZonaFormaEnvio que contiene la relación entre una Zona y sus Formas de Envío.
	 */
	public List<ZonaFormaEnvio> findByIdZona(Long id);

	/**
	 * Borra la relación entre una Zona y una Forma de Envío.
	 * 
	 * @param id ID de la Forma de Envío.
	 * @param idZona Id de la Zona.
	 */
	public void deleteFormaEnvio(Long id, Long idZona);
	
}
