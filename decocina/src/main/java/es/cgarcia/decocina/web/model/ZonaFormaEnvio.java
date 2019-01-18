package es.cgarcia.decocina.web.model;

import org.springframework.stereotype.Component;


/**
 * Relación entre Zonas y Envíos. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class ZonaFormaEnvio extends ModelBase
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7008948243924526414L;

	/**
	 * Zona.
	 */
	private Zona zona;
	
	/**
	 * Forma de Envío.
	 */
	private FormaEnvio formaEnvio;
	
	/**
	 * Orden.
	 */
	private Long orden;

	/**
	 * Devuelve el valor del atributo zona.
	 *
	 * @return zona
	 */
	public Zona getZona() {
		return zona;
	}

	/**
	 * Fija el valor del atributo zona.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}

	/**
	 * Devuelve el valor del atributo formaEnvio.
	 *
	 * @return formaEnvio
	 */
	public FormaEnvio getFormaEnvio() {
		return formaEnvio;
	}

	/**
	 * Fija el valor del atributo formaEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaEnvio(FormaEnvio formaEnvio) {
		this.formaEnvio = formaEnvio;
	}

	

	/**
	 * Devuelve el valor del atributo orden.
	 *
	 * @return orden
	 */
	public Long getOrden() {
		return orden;
	}

	/**
	 * Fija el valor del atributo orden.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setOrden(Long orden) {
		this.orden = orden;
	}
}

	