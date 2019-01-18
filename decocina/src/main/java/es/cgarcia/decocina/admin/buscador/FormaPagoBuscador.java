package es.cgarcia.decocina.admin.buscador;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Buscador de las Formas de Pago.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class FormaPagoBuscador extends BuscadorBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6046563155780599251L;
	
	/**
	 * Nombre de la Forma de Pago.
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
