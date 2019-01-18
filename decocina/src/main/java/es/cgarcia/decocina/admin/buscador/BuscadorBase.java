package es.cgarcia.decocina.admin.buscador;

import java.io.Serializable;

import es.cgarcia.decocina.admin.annotation.CampoForm;
import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.paginacion.Paginacion;

/**
 * Buscador base del que deben heredar todos los buscadores.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class BuscadorBase implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 5787223228003285251L;

	/**
	 * Indica si se está realizando una búsqueda.
	 */
	@CampoForm
	private Boolean hayBusqueda = new Boolean(false);
	
	/**
	 * Parámetros de la paginación. 
	 */
	@ClaseForm
	private Paginacion paginacion = new Paginacion();
	
	/**
	 * Devuelve el valor del atributo hayBusqueda.
	 *
	 * @return hayBusqueda
	 */
	public Boolean getHayBusqueda() {
		return hayBusqueda;
	}

	/**
	 * Fija el valor del atributo hayBusqueda.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setHayBusqueda(Boolean hayBusqueda) {
		this.hayBusqueda = hayBusqueda;
	}

	/**
	 * Devuelve el valor del atributo paginacion.
	 *
	 * @return paginacion
	 */
	public Paginacion getPaginacion() {
		return paginacion;
	}

	/**
	 * Fija el valor del atributo paginacion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPaginacion(Paginacion paginacion) {
		this.paginacion = paginacion;
	}
}
