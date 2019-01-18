package es.cgarcia.decocina.admin.service;

import java.util.Calendar;
import java.util.List;

import es.cgarcia.decocina.admin.buscador.PedidoBuscador;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Pedido
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IPedidoAdminService extends IGenericService<Pedido>{

	/**
	 * Devuelve los Pedidos por los campos de búsqueda que hay en PedidosBuscador y también de la paginación.
	 * 
	 * @param pedidoBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Pedidos filtrados por el nombre.
	 */
	List<Pedido> findAllBusqueda(PedidoBuscador pedidoBuscador);
	
	/**
	 * Devuelve el número de pedidos realizados el mes actual.
	 * 
	 * @param fecha Fecha de la que se recuperá el mes.
	 * @return Número de pedidos realizados el mes actual.
	 */
	Long findNumeroPedidosMes(Calendar fecha);

	/**
	 * Devuelve el importe total de los pedidos realizados en el mes.
	 * @param fecha Fecha actual.
	 * @return Importe total de los pedidos realizados en el mes.
	 */
	Double findSumaImportePedidosMes(Calendar fecha);
	
	/**
	 * Borra el Pedido.
	 * @param pedido Pedido a borrar.
	 */
	void delete(Pedido pedido);
}
