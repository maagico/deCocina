package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.analytics.TransaccionAnalytics;
import es.cgarcia.decocina.web.form.PedidoForm;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Pedido
 * de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IPedidoWebService extends IGenericService<Pedido>{

	/**
	 * Carga la Zona cargada con las Formas de Envío y Pago y subiendo si hereda hasta el Padre.
	 * @param clienteWrapper ClienteWrapper.
	 * @return Zona cargada con las Formas de Envío y Pago y subiendo si hereda hasta el Padre.
	 */
	Zona cargarZona(ClienteWrapper clienteWrapper);

	/**
	 * Añade las Formas de Envío y Pago y las observaciones a PedidoWrapper. 
	 * @param pedidoForm PedidoForm.
	 * @param clienteWrapper ClienteWrapper.
	 */
	void confirmarPedido(PedidoForm pedidoForm, ClienteWrapper clienteWrapper);

	/**
	 * Devuelve la URL del siguiente paso, que depende de la forma de pago. 
	 * @param clienteWrapper ClienteWrapper.
	 * @return Devuelve la URL del siguiente paso, que depende de la forma de pago. 
	 */
	String getURLSiguiente(ClienteWrapper clienteWrapper);

	/**
	 * Crea todos los pasos necesarios para crear y finalizar un pedido.
	 * @param clienteWrapper ClienteWrapper.
	 * @param rutaPublicaDestino Ruta pública destino de la imagen.
	 * @param rutaPrivadaOrigenReal Ruta origen de lectura real de la imagen.
	 * @param rutaPrivadaDestinoReal Ruta destino de escritura real de destino.
	 * @param host Host. http://www.decocina.es o https.
	 * @param token Token de Paypal.
	 * @param payerId PayerId de Paypal.
	 * @return Transacción para analytics. Contiene el Pedido y sus Artículos.
	 */
	TransaccionAnalytics finalizarPedido(ClienteWrapper clienteWrapper, String rutaPublicaDestino, String rutaPrivadaOrigenReal, String rutaPrivadaDestinoReal, String host, String token, String payerId);

	/**
	 * Devuelve todos los pedidos de un Cliente.
	 * @param id Id del Cliente.
	 * @return Pedidos del Cliente.
	 */
	List<Pedido> findAllByIdCliente(Long id);

	/**
	 * Devuelve el Pedido.
	 * @param idCliente Id del Cliente.
	 * @param idPedido Id del Pedido.
	 * @return Pedido.
	 */
	Pedido findByIdClienteAndIdPedido(Long idCliente, Long idPedido);

	/**
	 * Hace la llamada a Paypal y si todo ha ido correctamente devuelve la url con el token.
	 * @param pedidoWrapper PedidoWrapper.
	 * @param url Url del servidor.
	 * @return Url de acceso a Paypal con el token incluido.
	 */
	String pagarPaypal(PedidoWrapper pedidoWrapper, String url);

}
