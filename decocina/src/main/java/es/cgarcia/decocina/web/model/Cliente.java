package es.cgarcia.decocina.web.model;

import java.util.Calendar;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;


/**
 * Cliente.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Cliente extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1920621942149031737L;

	/**
	 * Nombre.
	 */
	@NotEmpty
	@Size(max=100)
	private String nombre;
	
	/**
	 * Apellidos.
	 */
	@NotEmpty
	@Size(max=100)
	private String apellidos;
	
	/**
	 * Email.
	 */
	private String email;
	
	/**
	 * Teléfono.
	 */
	@NotEmpty
	@Size(max=12)
	private String telefono;
	
	/**
	 * Password.
	 */
	private String password;
	
	/**
	 * Dirección.
	 */
	@Valid
	private Direccion direccion;

	/**
	 * Token de recuperación de contraseña.
	 */
	private String tokenRP;
	
	/**
	 * Fecha de creacion del Token para recuperar el password.
	 */
	private Calendar fechaRP;
	
	/**
	 * Indica un borrado lógico.
	 */
	private Boolean Borrado;
	
	/**
	 * Devuelve el valor del atributo nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Fija el valor del atributo nombre.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el valor del atributo apellidos.
	 *
	 * @return apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Fija el valor del atributo apellidos.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

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
	 * Devuelve el valor del atributo telefono.
	 *
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Fija el valor del atributo telefono.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	 * Devuelve el valor del atributo direccion.
	 *
	 * @return direccion
	 */
	public Direccion getDireccion() {
		return direccion;
	}

	/**
	 * Fija el valor del atributo direccion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	/**
	 * Devuelve el valor del atributo tokenRP.
	 *
	 * @return tokenRP
	 */
	public String getTokenRP() {
		return tokenRP;
	}

	/**
	 * Fija el valor del atributo tokenRP.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setTokenRP(String tokenRP) {
		this.tokenRP = tokenRP;
	}

	/**
	 * Devuelve el valor del atributo fechaRP.
	 *
	 * @return fechaRP
	 */
	public Calendar getFechaRP() {
		return fechaRP;
	}

	/**
	 * Fija el valor del atributo fechaRP.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFechaRP(Calendar fechaRP) {
		this.fechaRP = fechaRP;
	}

	/**
	 * Devuelve el valor del atributo borrado.
	 *
	 * @return borrado
	 */
	public Boolean getBorrado() {
		return Borrado;
	}

	/**
	 * Fija el valor del atributo borrado.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setBorrado(Boolean borrado) {
		Borrado = borrado;
	}
}
