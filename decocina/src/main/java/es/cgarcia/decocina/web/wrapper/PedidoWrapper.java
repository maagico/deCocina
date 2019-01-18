/**
 * 
 */
package es.cgarcia.decocina.web.wrapper;

import es.cgarcia.decocina.web.manager.CestaManager;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.model.FormaPago;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.model.Zona;

/**
 * 
 * Wrapper del Pedido.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class PedidoWrapper extends WrapperBase{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1038645917139570039L;
	
	/**
	 * Zona del cliente, necesaria para recuperar las Formas de Envío.
	 */
	private Zona zona;
	
	/**
	 * Forma de Envío del Pedido.
	 */
	private FormaEnvio formaEnvio;
	
	/**
	 * Forma de Pago del Pedido.
	 */
	private FormaPago formaPago;
	
	/**
	 * Observaciones del Pedido.
	 */
	private String observaciones;
	
	/**
	 * Pedido.
	 */
	private Pedido pedido;
	
	/**
	 * Administrador de la Cesta.
	 */
	private CestaManager cestaManager;
	
	/**
	 * Constructor.
	 * 
	 * @param cliente Cliente.
	 */
	public PedidoWrapper(Pedido pedido, Cliente cliente) {
		this.pedido = pedido;
		this.cestaManager = new CestaManager(cliente);
	}

	/**
	 * Devuelve el valor del atributo zona.
	 *
	 * @return zona
	 */
	public Zona getZona() {
		return zona;
	}

	/**
	 * Fija el valor del atributo zona.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	/**
	 * Devuelve el valor del atributo formaEnvio.
	 *
	 * @return formaEnvio
	 */
	public FormaEnvio getFormaEnvio() {
		return formaEnvio;
	}

	/**
	 * Fija el valor del atributo formaEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaEnvio(FormaEnvio formaEnvio) {
		this.formaEnvio = formaEnvio;
	}

	/**
	 * Devuelve el valor del atributo formaPago.
	 *
	 * @return formaPago
	 */
	public FormaPago getFormaPago() {
		return formaPago;
	}

	/**
	 * Fija el valor del atributo formaPago.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaPago(FormaPago formaPago) {
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
	 * Devuelve el valor del atributo cestaManager.
	 *
	 * @return cestaManager
	 */
	public CestaManager getCestaManager() {
		return cestaManager;
	}

	/**
	 * Fija el valor del atributo cestaManager.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCestaManager(CestaManager cestaManager) {
		this.cestaManager = cestaManager;
	}

	/**
	 * Devuelve el precio total del Pedido, gastos de envío, pago y total de la Cesta.
	 *
	 * @return precio
	 */
	public Double getPrecio() {
		
		Double gastoFormaEnvio = 0D;
		Double gastoFormaPago = 0D;
		
		if(formaEnvio != null){
			
			gastoFormaEnvio = formaEnvio.getGasto();
			
			if(gastoFormaEnvio == null){
				
				gastoFormaEnvio = 0D;
			}
		}
		
		if(formaPago != null){
			
			gastoFormaPago = formaPago.getGasto();
			
			if(gastoFormaPago == null){
				
				gastoFormaPago = 0D;
			}
			
		} 
		
		Double subTotal = cestaManager.getSubTotal();
		
		return gastoFormaEnvio + gastoFormaPago + subTotal;
	}
}
