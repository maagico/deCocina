package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;


/**
 * Interfaz del Dao para el PedidoEstadoHistorial de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IPedidoEstadoHistorialAdminDao extends IGenericDao<PedidoEstadoHistorial> {

	/**
	 * {@inheritDoc}
	 */
	List<PedidoEstadoHistorial> findAllByIdPedido(Long id);

	/**
	 * Inserta el Estado contenido en el Pedido.
	 * @param pedido Pedido.
	 */
	void insert(Pedido pedido);

	/**
	 * Borra todos los Estados del Pedido.
	 * @param id Id del Pedido.
	 */
	void deleteAllByIdPedido(Long id);
}
