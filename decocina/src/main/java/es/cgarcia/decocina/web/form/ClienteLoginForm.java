package es.cgarcia.decocina.web.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.form.FormBase;
import es.cgarcia.decocina.web.model.ClienteLogin;

/**
 * Clase que encapsula al ClienteLogin y que tiene los atributos necesarios 
 * para trabajar con formularios.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ClienteLoginForm extends FormBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -1602480733644810012L;
	
	/**
	 * Indica que se viene de la página de realizar pedido.
	 */
	private Boolean from;
	
	/**
	 * Cliente.
	 */
	@Valid
	@ClaseForm
	private ClienteLogin clienteLogin = new ClienteLogin();

	/**
	 * Constructor.
	 */
	public ClienteLoginForm(){	
	}
	
	/**
	 * Constructor.
	 * 
	 * @param clienteLogin ClienteLogin.
	 */
	public ClienteLoginForm(ClienteLogin clienteLogin){
		
		this.clienteLogin = clienteLogin;
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
	 * Devuelve el valor del atributo clienteLogin.
	 *
	 * @return clienteLogin
	 */
	public ClienteLogin getClienteLogin() {
		return clienteLogin;
	}

	/**
	 * Fija el valor del atributo clienteLogin.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setClienteLogin(ClienteLogin clienteLogin) {
		this.clienteLogin = clienteLogin;
	}
}
