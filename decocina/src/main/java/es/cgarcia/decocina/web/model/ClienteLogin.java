package es.cgarcia.decocina.web.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;


/**
 * Cliente para el Login. Contiene sólo email y password.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class ClienteLogin extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7684169791177886894L;
	
	/**
	 * Email.
	 */
	@NotEmpty
	@Size(max=100)
	private String email;
	
	/**
	 * Password.
	 */
	@NotEmpty
	@Size(max=15)
	private String password;

	/**
	 * Devuelve el valor del atributo email.
	 *
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Fija el valor del atributo email.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setEmail(String email) {
		this.email = email;
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
}
