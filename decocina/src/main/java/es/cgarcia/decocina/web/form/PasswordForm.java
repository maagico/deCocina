package es.cgarcia.decocina.web.form;

import es.cgarcia.decocina.admin.form.FormBase;
import es.cgarcia.decocina.web.model.ClientePassword;

/**
 * Formulario para el cambio de Password.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class PasswordForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6800126894396077140L;
	
	/**
	 * Cliente que contiene el password.
	 */
	private ClientePassword clientePassword;
	
	/**
	 * Constructor.
	 */
	public PasswordForm() {
	}
	
	/**
	 * Constructor.
	 * @param clientePassword ClientePassword.
	 */
	public PasswordForm(ClientePassword clientePassword) {
		this.clientePassword = clientePassword;
	}

	/**
	 * Devuelve el valor del atributo clientePassword.
	 *
	 * @return clientePassword
	 */
	public ClientePassword getClientePassword() {
		return clientePassword;
	}

	/**
	 * Fija el valor del atributo clientePassword.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setClientePassword(ClientePassword clientePassword) {
		this.clientePassword = clientePassword;
	}
}
