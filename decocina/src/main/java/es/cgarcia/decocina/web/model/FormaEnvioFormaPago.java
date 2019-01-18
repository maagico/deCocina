package es.cgarcia.decocina.web.model;

import org.springframework.stereotype.Component;


/**
 * Relaciona una Forma de Envío con las Formas de Pago.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class FormaEnvioFormaPago extends ModelBase
{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 339763687562706143L;

	/**
	 * Zona.
	 */
	private Zona zona;
	
	/**
	 * Forma de Envio.
	 */
	private FormaEnvio formaEnvio;
	
	/**
	 * Forma de Pago.
	 */
	private FormaPago formaPago;
	
	/**
	 * Orden.
	 */
	private long orden;

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
	 * Devuelve el valor del atributo orden.
	 *
	 * @return orden
	 */
	public long getOrden() {
		return orden;
	}

	/**
	 * Fija el valor del atributo orden.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setOrden(long orden) {
		this.orden = orden;
	}
}
