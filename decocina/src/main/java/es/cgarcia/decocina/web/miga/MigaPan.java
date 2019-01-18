/**
 * 
 */
package es.cgarcia.decocina.web.miga;

import java.io.Serializable;

/**
 * 
 * Miga de pan.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class MigaPan implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 8560654886997998321L;
	
	/**
	 * Texto de la Miga.
	 */
	
	private String texto;
	
	/**
	 * Enlace del Miga.
	 */
	private String enlace;

	/**
	 * Indica que es la última miga.
	 */
	private Boolean ultima;
	
	/**
	 * Devuelve el valor del atributo texto.
	 *
	 * @return texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Fija el valor del atributo texto.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Devuelve el valor del atributo enlace.
	 *
	 * @return enlace
	 */
	public String getEnlace() {
		return enlace;
	}

	/**
	 * Fija el valor del atributo enlace.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	/**
	 * Devuelve el valor del atributo ultima.
	 *
	 * @return ultima
	 */
	public Boolean getUltima() {
		return ultima;
	}

	/**
	 * Fija el valor del atributo ultima.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setUltima(Boolean ultima) {
		this.ultima = ultima;
	}
}
