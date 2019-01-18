package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.model.FormaEnvio;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una Forma de Envío
 * de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaEnvioWebService extends IGenericService<FormaEnvio>{

	/**
	 * Devuelve las Zonas de Envío asignadas a la Zona.
	 * @param id Id de la Zona.
	 * @return Formas de Envío asignadas a la Zona.
	 */
	List<FormaEnvio> findAllByIdZona(Long id);
}
