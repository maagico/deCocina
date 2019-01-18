package es.cgarcia.decocina.web.model;

import org.springframework.stereotype.Component;


/**
 * Cliente para el cambio de Password.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class ClientePassword extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -3626006921974087267L;
	
	/**
	 * EL password actual.
	 */
	private String password;
	
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
	public ClientePassword(){
	}

	/**
	 * Devuelve el valor del atributo password.
	 *
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Fija el valor del atributo password.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPassword(String password) {
		this.password = password;
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
