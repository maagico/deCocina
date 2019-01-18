package es.cgarcia.decocina.admin.dao;

import java.util.Calendar;
import java.util.List;

import es.cgarcia.decocina.admin.buscador.PedidoBuscador;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Pedido;

/**
 * Interfaz del Dao para los Pedidos de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IPedidoAdminDao extends IGenericDao<Pedido> {

	/**
	 * Devuelve los Pedidos por los campos de búsqueda que hay en PedidoBuscador y también de la paginación.
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
	 * Devuelve el número de pedidos realizados el mes actual.
	 * 
	 * @param fecha Fecha de la que se recuperá el mes.
	 * @return Número de pedidos realizados el mes actual.
	 */
	Double findSumaImportePedidosMes(Calendar fecha);
	
	/**
	 * Inserta el nuevo estado al Pedido.
	 * @param pedido Pedido, contiene el nuevo estado.
	 */
	void insertEstado(Pedido pedido);

	/**
	 * Devuelve todos los Pedidos de un Cliente.
	 * @param id Id del Cliente. 
	 * @return Pedidos del Cliente.
	 */
	List<Pedido> findAllByIdCliente(Long id);

	/**
	 * Borra los datos personales de un Pedido.
	 * @param id Id del Pedido.
	 */
	void borrarDatosPersonales(Long id);
}
