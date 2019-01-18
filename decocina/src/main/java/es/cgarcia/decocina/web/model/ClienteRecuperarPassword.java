package es.cgarcia.decocina.web.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/**
 * Cliente para recuperar la contraseña. Contiene sólo el email.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class ClienteRecuperarPassword extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -7028647618507605610L;

	/**
	 * Email.
	 */
	@NotEmpty
	@Size(max=100)
	@Email
	private String email;

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
}
