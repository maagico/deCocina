package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.web.model.ArticuloPedido;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un ArticuloPedido
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IArticuloPedidoAdminService extends IGenericService<ArticuloPedido>{
	
	/**
	 * Devuelve los Artículos de un Pedido.
	 * @param id Id del Pedido.
	 * @return Artículos del Pedido.
	 */
	List<ArticuloPedido> findByIdPedido(Long id);
}
