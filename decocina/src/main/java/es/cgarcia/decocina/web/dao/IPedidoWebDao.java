package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.Pedido;


/**
 * Interfaz del Dao para las Pedidos de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IPedidoWebDao extends IGenericDao<Pedido> {

	/**
	 * Asigna un Estado al Pedido.
	 * @param idPedido Id del Pedido.
	 * @param idPedidoEstadoHistorial Id del Estado.
	 */
	void updateEstadoHistorial(Long idPedido, Long idPedidoEstadoHistorial);

	/**
	 * Devuelve todos los pedidos de un Cliente.
	 * @param id Id del Cliente.
	 * @return Pedidos del Cliente.
	 */
	List<Pedido> findAllByIdCliente(Long id);

	/**
	 * Devuelve el Pedido.
	 * @param idCliente Id del CLiente.
	 * @param idPedido Id del Pedido.
	 * @return Pedido.
	 */
	Pedido findByIdClienteAndIdPedido(Long idCliente, Long idPedido);
}
