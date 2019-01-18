package es.cgarcia.decocina.admin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;


/**
 * Utilidades para las Fechas.
 * 
 * @author Miguel Ángel Álvarez García
 */
public final class FechaUtils 
{
	/**
	 * Constructor.
	 */
	private FechaUtils(){	
	}
	
	/**
	 * Formatea un Calendar a dd/MM/yyyy.
	 * 
	 * @param fecha Fecha a formatear.
	 * @return Fecha con formato dd/MM/yyyy.
	 */
	public static String format(Calendar fecha){
		
		String fechaFormateada = "";
		
		if(fecha != null){
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			fechaFormateada = sdf.format(fecha.getTime()); 
		}
		
		return fechaFormateada;
	}
	
	/**
	 * Convierte la fecha en un Calendar.
	 * 
	 * @param fecha Fecha a convertir en Calendar.
	 * @return Fecha convertida en Calendar.
	 * @throws DeCocinaAdminRuntimeException En caso de que la fecha tenga un formato incorrecto.
	 */
	public static Calendar parse(String fecha)
	{
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			formatter.setLenient(false);
			
			Date date = formatter.parse(fecha);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			
			return calendar;
			
		} catch (ParseException e) {
			
			throw new DeCocinaAdminRuntimeException("Formato de fecha incorrecto", e); 
		}
	}
}
