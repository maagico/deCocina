/**
 * 
 */
package es.cgarcia.decocina.web.menu;

import java.io.Serializable;

/**
 * Menú de la aplicación.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class Menu implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 8016746118738234127L;

	/**
	 * Id de la Categoría.
	 */
	private Long id;
	
	/**
	 * Nombre de la Categoría.
	 */
	private String nombre;
	
	/**
	 * Indica si está activa.
	 */
	private Boolean activa;
	
	/**
	 * URL amigable.
	 */
	private String urlAmigable;
	
	/**
	 * Devuelve el valor del atributo id.
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Fija el valor del atributo id.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Devuelve el valor del atributo activa.
	 *
	 * @return activa
	 */
	public Boolean getActiva() {
		return activa;
	}

	/**
	 * Fija el valor del atributo activa.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setActiva(Boolean activa) {
		this.activa = activa;
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
	 * Devuelve el valor del atributo urlAmigable.
	 *
	 * @return urlAmigable
	 */
	public String getUrlAmigable() {
		return urlAmigable;
	}

	/**
	 * Fija el valor del atributo urlAmigable.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setUrlAmigable(String urlAmigable) {
		this.urlAmigable = urlAmigable;
	}
}
