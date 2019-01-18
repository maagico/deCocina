/**
 * 
 */
package es.cgarcia.decocina.web.form;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.PedidoBuscador;
import es.cgarcia.decocina.admin.form.FormBase;
import es.cgarcia.decocina.web.model.Pedido;

/**
 * Formulario para el Pedido. Forma de Envío y Pago.
 * @author Miguel Ángel Álvarez García
 *
 */
public class PedidoForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -2186470497896318612L;
	
	/**
	 * Pedido.
	 */
	private Pedido pedido;
	
	/**
	 * Buscador de Pedidos.
	 */
	@ClaseForm
	private PedidoBuscador pedidoBuscador;
	
	/**
	 * Id de la Forma de pago.
	 */
	private Long FormaEnvio;
	
	/**
	 * Id de la Forma de Envío.
	 */
	private Long formaPago;

	/**
	 * Observaciones del Pedido.
	 */
	private String observaciones;
	
	
	/**
	 * Constructor.
	 */
	public PedidoForm(){
	}
	
	/**
	 * Constructor.
	 * 
	 * @param pedido Pedido.
	 */
	public PedidoForm(Pedido pedido){
		this.pedido = pedido;
	}
	
	/**
	 * Devuelve el valor del atributo pedido.
	 *
	 * @return pedido
	 */
	public Pedido getPedido() {
		return pedido;
	}

	/**
	 * Fija el valor del atributo pedido.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	/**
	 * Devuelve el valor del atributo pedidoBuscador.
	 *
	 * @return pedidoBuscador
	 */
	public PedidoBuscador getPedidoBuscador() {
		return pedidoBuscador;
	}

	/**
	 * Fija el valor del atributo pedidoBuscador.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPedidoBuscador(PedidoBuscador pedidoBuscador) {
		this.pedidoBuscador = pedidoBuscador;
	}

	/**
	 * Devuelve el valor del atributo formaEnvio.
	 *
	 * @return formaEnvio
	 */
	public Long getFormaEnvio() {
		return FormaEnvio;
	}

	/**
	 * Fija el valor del atributo formaEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaEnvio(Long formaEnvio) {
		FormaEnvio = formaEnvio;
	}

	/**
	 * Devuelve el valor del atributo formaPago.
	 *
	 * @return formaPago
	 */
	public Long getFormaPago() {
		return formaPago;
	}

	/**
	 * Fija el valor del atributo formaPago.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaPago(Long formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * Devuelve el valor del atributo observaciones.
	 *
	 * @return observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Fija el valor del atributo observaciones.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
