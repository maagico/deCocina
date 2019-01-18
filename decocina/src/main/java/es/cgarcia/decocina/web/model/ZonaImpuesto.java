package es.cgarcia.decocina.web.model;

import org.springframework.stereotype.Component;


/**
 * Relación entre Zonas e Impuestos.
 *  
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class ZonaImpuesto extends ModelBase
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3537512583193318119L;

	/**
	 * Zona.
	 */
	private Zona zona;
	
	/**
	 * Impuesto.
	 */
	private Impuesto impuesto;
	
	/**
	 * Prioridad.
	 */
	private Integer prioridad;

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
	 * Devuelve el valor del atributo impuesto.
	 *
	 * @return impuesto
	 */
	public Impuesto getImpuesto() {
		return impuesto;
	}

	/**
	 * Fija el valor del atributo impuesto.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}

	/**
	 * Devuelve el valor del atributo prioridad.
	 *
	 * @return prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}

	/**
	 * Fija el valor del atributo prioridad.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
}
