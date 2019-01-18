package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.ClienteBuscador;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Cliente
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IClienteAdminService extends IGenericService<Cliente>{
	
	/**
	 * Devuelve los CLientes por los campos de búsqueda que hay en ClienteBuscador y también de la paginación.
	 * 
	 * @param clienteBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Clientes filtrados por el nombre.
	 */
	List<Cliente> findAllBusqueda(ClienteBuscador clienteBuscador);
	
	/**
	 * Borra el Cliente.
	 * 
	 * @param cliente Cliente a borrar.
	 */
	void delete(Cliente cliente);
}
