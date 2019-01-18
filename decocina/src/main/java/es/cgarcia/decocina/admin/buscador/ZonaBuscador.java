package es.cgarcia.decocina.admin.buscador;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Buscador de las Zonas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ZonaBuscador extends BuscadorBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -5558433772687849694L;
	
	/**
	 * Nombre del Fabricante a buscar.
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
