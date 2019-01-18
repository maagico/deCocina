package es.cgarcia.decocina.admin.buscador;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Buscador de las Zonas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ZonaPadreBuscador extends BuscadorBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -5558433772687849694L;
	
	/**
	 * Párametro de búsqueda Id.
	 */
	@CampoForm
	private Long id;
	
	/**
	 * Nombre del Fabricante a buscar.
	 */
	@CampoForm
	private String nombre = "";
	
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
	 * Fija el valor del atributo nombre.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
