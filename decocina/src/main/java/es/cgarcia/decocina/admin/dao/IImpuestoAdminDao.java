package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.ImpuestoBuscador;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Impuesto;


/**
 * Interfaz del Dao para los Impuestos de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IImpuestoAdminDao extends IGenericDao<Impuesto> {

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
	public List<Impuesto> findAll(Boolean paraArticulo, Boolean paraZona);
}
