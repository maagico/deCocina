package es.cgarcia.decocina.admin.dao;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Cesta;


/**
 * Interfaz del Dao para las Cestas de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface ICestaAdminDao extends IGenericDao<Cesta> {
	
	/**
	 * Borra la Cesta de un Cliente.
	 * @param idCliente Id del Cliente.
	 */
	void deleteByIdCliente(Long idCliente);

	/**
	 * Borrar un Artículo de todas las cestas que lo contengan.
	 * @param idArticulo Id del Artículo a borrar.
	 */
	void deleteByIdArticulo(Long idArticulo);
}