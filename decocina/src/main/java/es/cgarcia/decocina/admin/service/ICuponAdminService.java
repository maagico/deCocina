package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.CuponBuscador;
import es.cgarcia.decocina.web.model.Cupon;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Cupón
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface ICuponAdminService extends IGenericService<Cupon>{
	
	/**
	 * Devuelve los Cupones por los campos de búsqueda que hay en CuponBuscador y también de la paginación.
	 * 
	 * @param cuponBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Cupones filtrados por el nombre.
	 */
	List<Cupon> findAllBusqueda(CuponBuscador cuponBuscador);
}
