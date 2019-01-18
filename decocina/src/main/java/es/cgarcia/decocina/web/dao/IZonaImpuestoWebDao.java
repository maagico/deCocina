package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.ZonaImpuesto;


/**
 * Interfaz del Dao para la relación entre Zona e Impuestos de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IZonaImpuestoWebDao extends IGenericDao<ZonaImpuesto> {

	/**
	 * Devuelve la relación entre una Zona y sus Impuestos.
	 *  
	 * @param id Id de la Zona.
	 * 
	 * @return Listado de ZonaImpuesto que contiene la relación entre una Zona y sus Impuestos.
	 */
	public List<ZonaImpuesto> findByIdZona(Long id);

	/**
	 * Devuelve el iva para la Zona con más prioridad (menos es más).
	 * @param id Id de la Zona.
	 * @return Iva para la Zona con más prioridad (menos es más).
	 */
	public Double findIvaMasPrioridad(Long id);

	/**
	 * Devuelve el Impuesto para la Zona con más prioridad (menos es más).
	 * @param id de la Zona.
	 * @return Impuesto para la Zona con más prioridad (menos es más).
	 */
	public ZonaImpuesto findImpuestoMasPrioridad(Long id);
	
}
