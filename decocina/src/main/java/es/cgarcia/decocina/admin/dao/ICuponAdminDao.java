package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.CuponBuscador;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Cupon;


/**
 * Interfaz del Dao para los Cupones de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface ICuponAdminDao extends IGenericDao<Cupon> {

	/**
	 * Devuelve los Cupones por los campos de búsqueda que hay en CuponBuscador y también de la paginación.
	 * 
	 * @param cuponBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Cupones filtrados por el nombre.
	 */
	List<Cupon> findAllBusqueda(CuponBuscador cuponBuscador);
}
