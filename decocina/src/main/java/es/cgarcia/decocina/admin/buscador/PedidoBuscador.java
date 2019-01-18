package es.cgarcia.decocina.admin.buscador;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Buscador de los Pedidos.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class PedidoBuscador extends BuscadorBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1492710317079540376L;

	/**
	 * Id del Cliente.
	 */
	@CampoForm
	private String idCliente = "";
	
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
	 * Devuelve el valor del atributo idCliente.
	 *
	 * @return idCliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * Fija el valor del atributo idCliente.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

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
