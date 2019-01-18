package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.Zona;


/**
 * Interfaz del Dao para las Zonas de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IZonaWebDao extends IGenericDao<Zona> {

	/**
	 * Devuelve las Provincias de España para los combos.
	 * 
	 * @return Todas las Provincias de España para los combos.
	 */
	List<Zona> findAllProvincias();
	
	/**
	 * Devuelve las Provincias activas de España para los combos.
	 * 
	 * @return Todas las Provincias activas de España para los combos.
	 */
	List<Zona> findAllProvinciasActivas();
}
