package es.cgarcia.decocina.admin.formula.pago;

import es.cgarcia.decocina.admin.formula.DatosFormula;
import es.cgarcia.decocina.admin.formula.FormulaBase;

/**
 * Fórmula que calcula el gasto del pago contrareembolso. 
 * Pagar con contrareembolso tiene un gasto adicional de 3€.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ContrareembolsoFP extends FormulaBase
{
	/**
	 * Precio del gasto del pago con contrareembolso.
	 */
	private Double gasto = 3.0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getGasto(DatosFormula datosFormula) {
		
		return gasto;
	}
}
