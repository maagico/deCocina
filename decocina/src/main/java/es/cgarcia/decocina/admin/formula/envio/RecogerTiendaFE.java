package es.cgarcia.decocina.admin.formula.envio;

import es.cgarcia.decocina.admin.formula.DatosFormula;
import es.cgarcia.decocina.admin.formula.FormulaBase;

/**
 * Fórmula que calcula el gasto de recoger el pedido en la tienda. 
 * Recoger el pedido en la tienda no tiene gastos. Así que se devuelve 0.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class RecogerTiendaFE extends FormulaBase
{
	/**
	 * Gasto por defecto a 0.
	 */
	private Double gasto = 0.0;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getGasto(DatosFormula datosFormula)
	{
		return gasto;
	}
}
