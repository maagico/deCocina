package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.ClienteBuscador;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Cliente;


/**
 * Interfaz del Dao para los Clientes de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IClienteAdminDao extends IGenericDao<Cliente> {

	/**
	 * Devuelve las Direcciones por los campos de búsqueda que hay en DireccionBuscador y también de la paginación.
	 * 
	 * @param clienteBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Clientes filtrados por el nombre.
	 */
	List<Cliente> findAllBusqueda(ClienteBuscador clienteBuscador);
}
