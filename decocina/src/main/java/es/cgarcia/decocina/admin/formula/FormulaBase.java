package es.cgarcia.decocina.admin.formula;

/**
 * Clase Base de la que deben heredar todas las fórmulas para calcular los gastos de envío o de pago.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public abstract class FormulaBase {
	
	/**
	 * Devuelve el gasto una vez aplicada la fórmula.
	 * 
	 * @param datosFormula Contiene los datos necesarios para calcular el gasto.
	 * @return Precio de los gastos de envío o pago.
	 */
	public abstract Double getGasto(DatosFormula datosFormula);
}
