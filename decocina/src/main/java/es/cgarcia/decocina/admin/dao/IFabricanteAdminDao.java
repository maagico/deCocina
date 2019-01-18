package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.FabricanteBuscador;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Fabricante;


/**
 * Interfaz del Dao para los Fabricantes de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFabricanteAdminDao extends IGenericDao<Fabricante> {

	/**
	 * Devuelve los Fabricantes por los campos de búsqueda que hay en FabricanteBuscador y también de la paginación.
	 * 
	 * @param fabricanteBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Fabricantes filtrados por el nombre.
	 */
	List<Fabricante> findAllBusqueda(FabricanteBuscador fabricanteBuscador);
}
