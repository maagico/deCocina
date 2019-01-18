package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.ArticuloPedido;


/**
 * Interfaz del Dao para el ArticuloPedido de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IArticuloPedidoWebDao extends IGenericDao<ArticuloPedido> {

	/**
	 * Devuelve los Artículos del Pedido.
	 * @param id Id del Pedido.
	 * @return Artículos del Pedido.
	 */
	List<ArticuloPedido> findByIdPedido(Long id);
}
