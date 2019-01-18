package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un PedidoEstadoHistorial
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IPedidoEstadoHistorialAdminService extends IGenericService<PedidoEstadoHistorial>{
	
	/**
	 * Devuelve todos los Estados de un Pedido.
	 * @param id Id del Pedido.
	 * @return Estados del Pedido.
	 */
	List<PedidoEstadoHistorial> findAllByIdPedido(Long id);
	
	/**
	 * Inserta el nuevo Estado contenido en el Pedido.
	 * @param pedido Pedido.
	 */
	void insert(Pedido pedido);
}
