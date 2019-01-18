package es.cgarcia.decocina.admin.formula.envio;

import es.cgarcia.decocina.admin.formula.DatosFormula;
import es.cgarcia.decocina.admin.formula.FormulaBase;

/**
 * Calcula los gastos de envío de la mensajería urgente.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class MensajeriaUrgenteFE extends FormulaBase
{	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getGasto(DatosFormula datosFormula) {
		
		Double precioTotalCesta = datosFormula.getSubTotal();
		
		Double importe = 5.5;
		
		if(precioTotalCesta <= 30){
			
			importe = 5.5;
		}else
		if(precioTotalCesta >= 30 && precioTotalCesta <= 60){
			
			importe = 4.50;
		}else
		if(precioTotalCesta >= 60 && precioTotalCesta <= 90){
			
			importe = 2.50;
		}else
		if(precioTotalCesta >= 90 && precioTotalCesta <= 110){
			
			importe = 1.50;
		}else
		if(precioTotalCesta >= 110){
			
			importe = 0d;
		}
		
		return importe;
	}
}
