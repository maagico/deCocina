package es.cgarcia.decocina.web.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/**
 * Dirección del Cliente.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Direccion extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -2811405468206685355L;

	/**
	 * Calle, número, piso y letra.
	 */
	@NotEmpty
	@Size(max=150)
	private String calle;
	
	/**
	 * Código de postal.
	 */
	@NotEmpty
	@Size(max=5)
	private String codigoPostal;
	
	/**
	 * Zona (provincia) de la dirección.
	 */
	private Zona zona;
	
	/**
	 * Población.
	 */
	@NotEmpty
	@Size(max=100)
	private String poblacion;

	/**
	 * Devuelve el valor del atributo calle.
	 *
	 * @return calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Fija el valor del atributo calle.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Devuelve el valor del atributo codigoPostal.
	 *
	 * @return codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Fija el valor del atributo codigoPostal.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

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
	 * Devuelve el valor del atributo poblacion.
	 *
	 * @return poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * Fija el valor del atributo poblacion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
}
