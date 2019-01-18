package es.cgarcia.decocina.admin.form;

import java.io.Serializable;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Clase de la que deben heredar todos los Forms.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class FormBase implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 456532721519978809L;
	
	/**
	 * Indica si se está creando o es una modificación.
	 */
	@CampoForm
	private Boolean esCreacion;
	
	/**
	 * Devuelve el valor del atributo esCreacion.
	 *
	 * @return esCreacion
	 */
	public Boolean getEsCreacion() {
		return esCreacion;
	}
	
	/**
	 * Fija el valor del atributo esCreacion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setEsCreacion(Boolean esCreacion) {
		this.esCreacion = esCreacion;
	}
}
