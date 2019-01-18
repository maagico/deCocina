package es.cgarcia.decocina.web.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.form.FormBase;
import es.cgarcia.decocina.web.model.Cliente;

/**
 * Clase que encapsula al Cliente y que tiene los atributos necesarios 
 * para trabajar con formularios.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ClienteForm extends FormBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -1602480733644810012L;
	
	/**
	 * Cliente.
	 */
	@Valid
	@ClaseForm
	private Cliente cliente = new Cliente();
	
	/**
	 * Indica que se viene de la página de realizar pedido.
	 */
	private Boolean from;
	
	/**
	 * Política de privacidad.
	 */
	private Boolean politica;
	
	/**
	 * Indica si se está en el panel de control.
	 * En ese caso no se validará el email el password y la política de privacidad.
	 */
	private Boolean enPanelControl = false;
	
	/**
	 * Constructor.
	 */
	public ClienteForm(){
	}
	
	/**
	 * Constructor.
	 * 
	 * @param cliente Cliente.
	 */
	public ClienteForm(Cliente cliente){
		this.cliente = cliente;
	}
	
	/**
	 * Devuelve el valor del atributo from.
	 *
	 * @return from
	 */
	public Boolean getFrom() {
		return from;
	}

	/**
	 * Fija el valor del atributo from.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFrom(Boolean from) {
		this.from = from;
	}

	/**
	 * Devuelve el valor del atributo cliente.
	 *
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Fija el valor del atributo cliente.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Devuelve el valor del atributo politica.
	 *
	 * @return politica
	 */
	public Boolean getPolitica() {
		return politica;
	}

	/**
	 * Fija el valor del atributo politica.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPolitica(Boolean politica) {
		this.politica = politica;
	}

	/**
	 * Devuelve el valor del atributo enPanelControl.
	 *
	 * @return enPanelControl
	 */
	public Boolean getEnPanelControl() {
		return enPanelControl;
	}

	/**
	 * Fija el valor del atributo enPanelControl.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setEnPanelControl(Boolean enPanelControl) {
		this.enPanelControl = enPanelControl;
	}
}
