package es.cgarcia.decocina.web.form;

import es.cgarcia.decocina.admin.form.FormBase;
import es.cgarcia.decocina.web.model.ClienteCambiarPassword;

/**
 * Formulario para el cambio de Password.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ClienteCambiarPasswordForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6800126894396077140L;
	
	/**
	 * Cliente que contiene el password.
	 */
	private ClienteCambiarPassword clienteCambiarPassword;
	
	/**
	 * Constructor.
	 */
	public ClienteCambiarPasswordForm() {
	}
	
	/**
	 * Constructor.
	 * @param clientePassword ClientePassword.
	 */
	public ClienteCambiarPasswordForm(ClienteCambiarPassword clienteCambiarPassword) {
		this.clienteCambiarPassword = clienteCambiarPassword;
	}

	/**
	 * Devuelve el valor del atributo clienteCambiarPassword.
	 *
	 * @return clienteCambiarPassword
	 */
	public ClienteCambiarPassword getClienteCambiarPassword() {
		return clienteCambiarPassword;
	}

	/**
	 * Fija el valor del atributo clienteCambiarPassword.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setClienteCambiarPassword(
			ClienteCambiarPassword clienteCambiarPassword) {
		this.clienteCambiarPassword = clienteCambiarPassword;
	}
}
