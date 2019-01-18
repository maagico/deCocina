package es.cgarcia.decocina.web.model;

import org.springframework.stereotype.Component;


/**
 * Cliente para el cambio de Password.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class ClienteCambiarPassword extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -3496548827786826700L;

	/**
	 * Token.
	 */
	private String token;
	
	/**
	 * EL nuevo password.
	 */
	private String nuevoPassword;
	
	/**
	 * El nuevo password repetido.
	 */
	private String nuevoPasswordConfirmacion;
	
	/**
	 * Constructor.
	 */
	public ClienteCambiarPassword(){
	}
	
	/**
	 * Devuelve el valor del atributo token.
	 *
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Fija el valor del atributo token.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setToken(String token) {
		this.token = token;
	}


	/**
	 * Devuelve el valor del atributo nuevoPassword.
	 *
	 * @return nuevoPassword
	 */
	public String getNuevoPassword() {
		return nuevoPassword;
	}

	/**
	 * Fija el valor del atributo nuevoPassword.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNuevoPassword(String nuevoPassword) {
		this.nuevoPassword = nuevoPassword;
	}

	/**
	 * Devuelve el valor del atributo nuevoPasswordConfirmacion.
	 *
	 * @return nuevoPasswordConfirmacion
	 */
	public String getNuevoPasswordConfirmacion() {
		return nuevoPasswordConfirmacion;
	}

	/**
	 * Fija el valor del atributo nuevoPasswordConfirmacion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNuevoPasswordConfirmacion(String nuevoPasswordConfirmacion) {
		this.nuevoPasswordConfirmacion = nuevoPasswordConfirmacion;
	}
	
}
