package es.cgarcia.decocina.admin.formula;

import java.io.Serializable;

/**
 * Contiene los datos necesarios para calcular los gastos de envío o pago.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class DatosFormula implements Serializable
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2971835344866390121L;

	/**
	 * Peso total de la cesta.
	 */
	private Double peso;

	/**
	 * Precio total de los Artículos de la cesta.
	 */
	private Double subTotal;

	/**
	 * Devuelve el valor del atributo peso.
	 *
	 * @return peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * Fija el valor del atributo peso.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	/**
	 * Devuelve el valor del atributo subTotal.
	 *
	 * @return subTotal
	 */
	public Double getSubTotal() {
		return subTotal;
	}

	/**
	 * Fija el valor del atributo subTotal.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
}
