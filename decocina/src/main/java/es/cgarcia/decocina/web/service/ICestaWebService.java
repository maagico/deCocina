package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.manager.CestaManager;
import es.cgarcia.decocina.web.model.Cesta;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.model.FormaPago;
import es.cgarcia.decocina.web.wrapper.ArticuloCestaWrapper;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una Cesta
 * de la parte Web.
 * O
 * @author Miguel Ángel Álvarez García
 *
 */

public interface ICestaWebService extends IGenericService<Cesta>{

	/**
	 * Añade un Artículo a la Cesta.
	 * @param clienteWrapper Wrapper del Cliente.
	 * @param idArticulo Id del Artículo a añadir.
	 * @param cantidad Cantidad a añadir. 
	 * @return ClienteWrapper.
	 */
	ClienteWrapper addArticulo(ClienteWrapper clienteWrapper, Long idArticulo, Integer cantidad);

	/**
	 * Actualiza la cantidad del Artículo en la Cesta.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param id Id del Artículo.
	 * @param cantidad Cantidad a modificar.
	 * @return JSON con el resultado.
	 */
	String actualizarCantidad(ClienteWrapper clienteWrapper, Long id, Integer cantidad);

	/**
	 * Elimina un Artículo de la Cesta.
	 * @param clienteWrapper ClienteWrapper.
	 * @param id Id del Artículo a eliminar. 
	 * @return JSON con el resultado.
	 */
	String eliminarArticulo(ClienteWrapper clienteWrapper, Long id);

	/**
	 * Devuelve las Formas de Envío de la Zona.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param idZona Id de la Zona.
	 * @return Devuelve las Formas de Envío de la Zona.
	 */
	String recuperarFormasEnvioJSON(ClienteWrapper clienteWrapper, Long idZona);
	
	/**
	 * Devuelve las Formas de Envío de la Zona.
	 * 
	 * @param idZona Id de la Zona.
	 * @return Devuelve las Formas de Envío de la Zona.
	 */
	List<FormaEnvio> recuperarFormasEnvio(Long idZona);
	
	/**
	 * Devuelve las Formas de Pago de la Zona y de la Forma de Envío.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param idFormaEnvio Id de la Forma de Envío.
	 * @return Devuelve las Formas de Pago de la Forma de Envío.
	 */
	String recuperarFormasPagoJSON(ClienteWrapper clienteWrapper, Long idFormaEnvio);
	
	/**
	 * Devuelve las Formas de Pago de la Zona y de la Forma de Envío.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param idFormaEnvio Id de la Forma de Envío.
	 * @return Devuelve las Formas de Pago de la Forma de Envío.
	 */
	List<FormaPago> recuperarFormasPago(ClienteWrapper clienteWrapper, Long idFormaEnvio);

	/**
	 * Devuelve el gasto de la Forma de Envío.
	 * @param clienteWrapper ClienteWrapper.
	 * @param idFormaEnvio Id de la Forma de Envío.
	 * @return Gasto de la Forma de Envío. 
	 */
	String recuperarGastoFormaEnvio(ClienteWrapper clienteWrapper, Long idFormaEnvio);
	
	/**
	 * Devuelve el gasto de la Forma de Pago.
	 * @param clienteWrapper ClienteWrapper.
	 * @param idFormaPago Id de la Forma de Pago.
	 * @return Gasto de la Forma de Pago. 
	 */
	String recuperarGastoFormaPago(ClienteWrapper clienteWrapper, Long idFormaPago);
	
	/**
	 * Selecciona la Forma de Envío.
	 * @param clienteWrapper ClienteWrapper.
	 * @param idFormaEnvio Id de la Forma de Envío.
	 */
	void seleccionarFormaEnvio(ClienteWrapper clienteWrapper, Long idFormaEnvio);
	
	/**
	 * Selecciona la Forma de Pago.
	 * @param clienteWrapper ClienteWrapper.
	 * @param idFormaPago Id de la Forma de Pago.
	 * @return Devuelve un JSON que indica si la Forma de Pago implica un paso adicional a la hora de pagar.
	 */
	String seleccionarFormaPago(ClienteWrapper clienteWrapper, Long idFormaPago);

	/**
	 * Selecciona la Forma de Pago desde Realizar Pedido.
	 * @param clienteWrapper ClienteWrapper.
	 * @param idFormaPago Id de la Forma de Pago.
	 * @return Devuelve un JSON que indica si la Forma de Pago implica un paso adicional a la hora de pagar.
	 */
	String seleccionarFormaPagoRP(ClienteWrapper clienteWrapper, Long idFormaPago);
	
	/**
	 * Añade los Articulos a la base de datos.
	 * @param articulosCestaWrapper Artículos de la cesta.
	 */
	void insert(List<ArticuloCestaWrapper> articulosCestaWrapper);

	/**
	 * Recupera todos los Articulos de la Cesta y los pasa al Manager.
	 * @param clienteWrapper ClienteWrapper.
	 * @param idCliente Id del Cliente.
	 * @param cestaManager CestaManager.
	 */
	void addArticulosCestaManager(ClienteWrapper clienteWrapper, Long idCliente, CestaManager cestaManager);
}
