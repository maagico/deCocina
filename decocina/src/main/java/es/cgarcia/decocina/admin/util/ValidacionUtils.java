package es.cgarcia.decocina.admin.util;

/**
 * Utilidades para las URLs.
 * 
 * @author Miguel Ángel Álvarez García
 */
public final class ValidacionUtils 
{
	/**
	 * Constructor.
	 */
	private ValidacionUtils(){	
	}
	
	/**
	 * Comprueba si el objeto es null.
	 * @param o Objeto a comprobar.
	 * 
	 * @return true si hay error, false lo contrario.
	 */
	public static Boolean esNull(Object o){
		
		if(o == null)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Comprueba si el texto es null o vacío.
	 * @param o Objeto a comprobar.
	 * 
	 * @return true si hay error, false lo contrario.
	 */
	public static Boolean esVacio(String cadena){
		
		if(cadena == null || "".equals(cadena))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Comprueba si el texto es inferior al tamaño indicado.
	 * 
	 * @param cadena Cadena a comprobar.
	 * @param tam Tamaño máximo.
	 * @return True si está dentro del tamaño máximo.
	 */
	public static Boolean validarTam(String cadena, Integer tam){
		
		if(cadena == null || cadena.length() > tam)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Valida si el Double es correcto. Es correcto si  
	 * tiene la parte entera menor o igual de 6 y la parte decimal menor o igual que 2.
	 * 
	 * @param numero Número a validar.
	 * @return Error de validacion o null si la validación es correcta.
	 */
	public static Boolean validarTam(Double numero, Integer tamEntera, Integer tamDecimal)
	{
		String strNumero = String.valueOf(numero);
		String[] parte = strNumero.split("\\.");
		
		String entera = parte[0];
		String decimal = parte[1];
		
		if(entera.length() > tamEntera || decimal.length() > tamDecimal){
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Comprueba si el String recibido es un número.
	 * @param numero Número a comprobar.
	 * @return True si es un número válido.
	 */
	public static Boolean esNumerico(String numero){
	
		try{
			
			Long.parseLong(numero);
		
		}catch(NumberFormatException e){
			
			return false;
		}
		
		return true;
	}
}
