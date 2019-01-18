package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.model.Zona;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una Zona
 * de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IZonaWebService extends IGenericService<Zona>{
	
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
