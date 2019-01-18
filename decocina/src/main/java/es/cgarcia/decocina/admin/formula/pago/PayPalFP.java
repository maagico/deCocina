package es.cgarcia.decocina.admin.formula.pago;

import es.cgarcia.decocina.admin.formula.DatosFormula;
import es.cgarcia.decocina.admin.formula.FormulaBase;

/**
 * Fórmula que calcula el gasto del pago por PayPal. 
 * Pagar con PayPal no tiene gastos.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class PayPalFP extends FormulaBase
{
	/**
	 * Precio del gasto del pago en la tienda.
	 */
	private Double gasto = 0.0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getGasto(DatosFormula datosFormula) {
		
		return gasto;
	}
}
