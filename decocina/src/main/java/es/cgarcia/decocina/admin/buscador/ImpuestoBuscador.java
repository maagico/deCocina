package es.cgarcia.decocina.admin.buscador;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Buscador de los Impuestos.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ImpuestoBuscador extends BuscadorBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 270713867420127301L;
	
	/**
	 * Nombre del impuesto a buscar.
	 */
	@CampoForm
	private String nombre = "";

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
}
