package es.cgarcia.decocina.admin.formula.envio;

import es.cgarcia.decocina.admin.formula.DatosFormula;
import es.cgarcia.decocina.admin.formula.FormulaBase;

/**
 * Calcula los gastos de envío de la mensajería urgente para Baleares.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class MensajeriaUrgenteBalearesFE extends FormulaBase
{	
	/**
	 * Gastos de envío para Baleares.
	 */
	private Double gasto = 15.0;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getGasto(DatosFormula datosFormula) {
		
		return gasto;
		
	}
}
