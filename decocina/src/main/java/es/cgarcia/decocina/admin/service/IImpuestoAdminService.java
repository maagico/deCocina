package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.ImpuestoBuscador;
import es.cgarcia.decocina.web.model.Impuesto;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Impuesto
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IImpuestoAdminService extends IGenericService<Impuesto>{
	
	/**
	 * Devuelve los Impuestos por los campos de búsqueda que hay en ImpuestoBuscador y también de la paginación.
	 * 
	 * @param impuestoBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Impuestos filtrados por el nombre.
	 */
	List<Impuesto> findAllBusqueda(ImpuestoBuscador impuestoBuscador);

	/**
	 * Devuelve los Impuestos que no estén añadidos en la Zona.
	 * 
	 * @param id Id de la Zona.
	 * @return Impuestos que no estén ya añadidos en la Zona.
	 */
	List<Impuesto> findAllByIdZona(Long id);
	
	/**
	 * Devuelve todos los Impuestos.
	 *  
	 * @param paraArticulo Indica que se van a devolver los impuestos de los Artículos.
	 * @param paraZona Indica que se van a devolver los impuestos de las Zonas.
	 * @return Lista de Impuestos encontrados.
	 */
	List<Impuesto> findAll(Boolean paraArticulo, Boolean paraZona);
	
}
