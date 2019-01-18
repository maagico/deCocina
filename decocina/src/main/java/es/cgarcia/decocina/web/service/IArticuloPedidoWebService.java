package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.model.ArticuloPedido;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un ArticuloPedido
 * de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IArticuloPedidoWebService extends IGenericService<ArticuloPedido>{
	
	/**
	 * Devuelve los Artículos de un Pedido.
	 * @param id Id del Pedido.
	 * @return Artículos del Pedido.
	 */
	List<ArticuloPedido> findByIdPedido(Long id);
}
