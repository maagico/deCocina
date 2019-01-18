package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;


/**
 * Interfaz del Dao para el PedidoEstadoHistorial de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IPedidoEstadoHistorialWebDao extends IGenericDao<PedidoEstadoHistorial> {
		
	/**
	 * {@inheritDoc}
	 */
	List<PedidoEstadoHistorial> findAllByIdPedido(Long id);
}
