package es.cgarcia.decocina.admin.buscador;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Buscador de los Fabricantes.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class FabricanteBuscador extends BuscadorBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 3515073518844694765L;
	
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
