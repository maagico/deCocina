package es.cgarcia.decocina.web.model;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;


/**
 * Zonas de la aplicación.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Zona extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 4991306363627696383L;

	/**
	 * Nombre de la Zona.
	 */
	@NotEmpty
	@Size(max=255)
	private String nombre;
	
	/**
	 * Zona padre de la Zona.
	 */
	private Zona zonaPadre;
	
	/**
	 * Indica si se deben heredar los impuestos, Formas de Envío y Pago de la Zona padre.
	 */
	private Boolean heredarFormas;
	
	/**
	 * Indica si está activa o no.
	 */
	private Boolean activa;

	/**
	 * Impuestos asociados a la Zona.
	 */
	private List<ZonaImpuesto> zonaImpuestos;
	
	/**
	 * Formas de Envío asociadas a la Zona.
	 */
	private List<ZonaFormaEnvio> zonaFormasEnvio;
	
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

	/**
	 * Devuelve el valor del atributo zonaPadre.
	 *
	 * @return zonaPadre
	 */
	public Zona getZonaPadre() {
		return zonaPadre;
	}

	/**
	 * Fija el valor del atributo zonaPadre.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setZonaPadre(Zona zonaPadre) {
		this.zonaPadre = zonaPadre;
	}

	/**
	 * Devuelve el valor del atributo heredarFormas.
	 *
	 * @return heredarFormas
	 */
	public Boolean getHeredarFormas() {
		return heredarFormas;
	}

	/**
	 * Fija el valor del atributo heredarFormas.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setHeredarFormas(Boolean heredarFormas) {
		this.heredarFormas = heredarFormas;
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
	 * Devuelve el valor del atributo zonaImpuestos.
	 *
	 * @return zonaImpuestos
	 */
	public List<ZonaImpuesto> getZonaImpuestos() {
		return zonaImpuestos;
	}

	/**
	 * Fija el valor del atributo zonaImpuestos.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setZonaImpuestos(List<ZonaImpuesto> zonaImpuestos) {
		this.zonaImpuestos = zonaImpuestos;
	}

	/**
	 * Devuelve el valor del atributo zonaFormasEnvio.
	 *
	 * @return zonaFormasEnvio
	 */
	public List<ZonaFormaEnvio> getZonaFormasEnvio() {
		return zonaFormasEnvio;
	}

	/**
	 * Fija el valor del atributo zonaFormasEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setZonaFormasEnvio(List<ZonaFormaEnvio> zonaFormasEnvio) {
		this.zonaFormasEnvio = zonaFormasEnvio;
	}
}
