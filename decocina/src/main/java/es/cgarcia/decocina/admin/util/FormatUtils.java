/**
 * 
 */
package es.cgarcia.decocina.admin.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Utilidades para el formato.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public final class FormatUtils {
	
	/**
	 * Constructor.
	 */
	private FormatUtils(){
	}
	
	/**
	 * Redondea un double a dos decimales.
	 * @param valor El double a redondear.
	 * @return El valor redondeado.
	 */
	public static Double redondearDouble(Double valor){
		
		DecimalFormatSymbols ds = new DecimalFormatSymbols();
		
		ds.setDecimalSeparator('.');
		ds.setGroupingSeparator('.');
		
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		
		decimalFormat.setGroupingUsed(false);
		decimalFormat.setGroupingSize(3);
		decimalFormat.setDecimalFormatSymbols(ds);
		decimalFormat.setMinimumIntegerDigits(1);
		
		return Double.parseDouble(decimalFormat.format(valor));
	}
	
	/**
	 * Convierte el Double a String con formato redondeado.
	 * @param valor Valor a convertir.
	 * @return Devuelve el Double a String con formato redondeado.
	 */
	public static String formatDouble(Double valor){
		 
		DecimalFormatSymbols ds = new DecimalFormatSymbols();
		ds.setDecimalSeparator(',');
		ds.setGroupingSeparator('.');
		
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		
		decimalFormat.setGroupingUsed(true);
		decimalFormat.setGroupingSize(3);
		decimalFormat.setDecimalFormatSymbols(ds);
		decimalFormat.setMinimumIntegerDigits(1);
		
		return decimalFormat.format(valor);
	 }
}
