package es.cgarcia.decocina.admin.buscador;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Buscador de los Clientes.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ClienteBuscador extends BuscadorBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 3515073518844694765L;
	
	/**
	 * Nombre del Cliente.
	 */
	@CampoForm
	private String nombre = "";
	
	/**
	 * Apellidos del Cliente.
	 */
	@CampoForm
	private String apellidos = "";
	
	/**
	 * Teléfono del Cliente.
	 */
	@CampoForm
	private String telefono = "";

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
}
