package es.cgarcia.decocina.web.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.form.FormBase;
import es.cgarcia.decocina.web.model.ClienteRecuperarPassword;

/**
 * Formulario para el cambio de Password.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ClienteRecuperarPasswordForm extends FormBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6606979266211070160L;
	
	/**
	 * Cliente que contiene el password.
	 */
	@Valid
	private ClienteRecuperarPassword clienteRecuperarPassword;
	
	/**
	 * Constructor.
	 */
	public ClienteRecuperarPasswordForm() {
	}
	
	/**
	 * Constructor.
	 * @param clienteRecuperarPassword ClienteRecuperarPassword.
	 */
	public ClienteRecuperarPasswordForm(ClienteRecuperarPassword clienteRecuperarPassword) {
		this.clienteRecuperarPassword = clienteRecuperarPassword;
	}

	/**
	 * Devuelve el valor del atributo clienteRecuperarPassword.
	 *
	 * @return clienteRecuperarPassword
	 */
	public ClienteRecuperarPassword getClienteRecuperarPassword() {
		return clienteRecuperarPassword;
	}

	/**
	 * Fija el valor del atributo clienteRecuperarPassword.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setClienteRecuperarPassword(
			ClienteRecuperarPassword clienteRecuperarPassword) {
		this.clienteRecuperarPassword = clienteRecuperarPassword;
	}
}
