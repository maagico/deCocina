package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.FabricanteBuscador;
import es.cgarcia.decocina.web.model.Fabricante;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Fabricante
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFabricanteAdminService extends IGenericService<Fabricante>{
	
	/**
	 * Devuelve los Fabricantes por los campos de búsqueda que hay en FabricanteBuscador y también de la paginación.
	 * 
	 * @param fabricanteBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Fabricantes filtrados por el nombre.
	 */
	List<Fabricante> findAllBusqueda(FabricanteBuscador fabricanteBuscador);
}
