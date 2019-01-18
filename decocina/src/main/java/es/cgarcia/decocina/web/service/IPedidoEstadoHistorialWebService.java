package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un PedidoEstadoHistorial
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IPedidoEstadoHistorialWebService extends IGenericService<PedidoEstadoHistorial>{
	
	/**
	 * Devuelve todos los Estados de un Pedido.
	 * @param id Id del Pedido.
	 * @return Estados del Pedido.
	 */
	List<PedidoEstadoHistorial> findAllByIdPedido(Long id);
}
