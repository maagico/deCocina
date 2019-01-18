package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.FormaEnvio;


/**
 * Interfaz del Dao para las Formas de Envío de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaEnvioWebDao extends IGenericDao<FormaEnvio> {

	/**
	 * Devuelve las Zonas de Envío asignadas a la Zona.
	 * @param id Id de la Zona.
	 * @return Formas de Envío asignadas a la Zona.
	 */
	List<FormaEnvio> findAllByIdZona(Long id);
}
