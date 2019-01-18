package es.cgarcia.decocina.admin.paginacion;

import java.io.Serializable;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Contiene la paginación de los resultados.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class Paginacion implements Serializable
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2965516632912317497L;

	/**
	 * Constructor.
	 */
	public Paginacion(){	
	}
	
	/**
	 * Inicio.
	 */
	@CampoForm
	private Long start  = 0L;
	
	/**
	 *	Límite. 
	 */
	@CampoForm
	private Long limit = 10L;
	
	/**
	 * Número de registros.
	 */
	@CampoForm
	private Long count = 0L;

	/**
	 * Devuelve el valor del atributo start.
	 *
	 * @return start
	 */
	public Long getStart() {
		return start;
	}

	/**
	 * Fija el valor del atributo start.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setStart(Long start) {
		this.start = start;
	}

	/**
	 * Devuelve el valor del atributo limit.
	 *
	 * @return limit
	 */
	public Long getLimit() {
		return limit;
	}

	/**
	 * Fija el valor del atributo limit.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setLimit(Long limit) {
		this.limit = limit;
	}

	/**
	 * Devuelve el valor del atributo count.
	 *
	 * @return count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * Fija el valor del atributo count.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCount(Long count) {
		this.count = count;
	}
}
