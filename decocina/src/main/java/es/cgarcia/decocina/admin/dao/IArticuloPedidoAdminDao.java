package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.ArticuloPedido;

/**
 * Interfaz del Dao para el ArticuloPedido de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IArticuloPedidoAdminDao extends IGenericDao<ArticuloPedido> {

	/**
	 * Devuelve los Artículos del Pedido.
	 * @param id Id del Pedido.
	 * @return Artículos del Pedido.
	 */
	List<ArticuloPedido> findByIdPedido(Long id);

	/**
	 * Borra los Artículos del pedido.
	 * 
	 * @param id Id del Pedido.
	 */
	void deleteAllByIdPedido(Long id);
}
