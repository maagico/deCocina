package es.cgarcia.decocina.admin.buscador;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Buscador de las Formas de Envío.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class FormaEnvioBuscador extends BuscadorBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7490506476260394747L;
	
	/**
	 * Nombre de las Formas de Envío a buscar.
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
