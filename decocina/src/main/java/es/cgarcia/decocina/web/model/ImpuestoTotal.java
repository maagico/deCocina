/**
 * 
 */
package es.cgarcia.decocina.web.model;

/**
 * Valor total del Impuesto de los Artículos de la Cesta.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ImpuestoTotal extends ModelBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -3023985841498604670L;

	/**
	 * Impuesto.
	 */
	private Impuesto impuesto;
	
	/**
	 * Total del Impuesto.
	 */
	private Double total;

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
	 * Devuelve el valor del atributo total.
	 *
	 * @return total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * Fija el valor del atributo total.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
