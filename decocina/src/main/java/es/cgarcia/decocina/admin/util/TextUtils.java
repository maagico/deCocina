/**
 * 
 */
package es.cgarcia.decocina.admin.util;

/**
 * Utilidades para trabajar con texto.
 * 
 * @author Miguel Ángel Álvarez García
 */
public final class TextUtils {

	/**
	 * Constructor.
	 */
	private TextUtils(){
	}
	
	/**
	 * Añade el Token al texto.
	 * @param texto Texto al que sele añadirá el token.
	 * @param token Token a añadir.
	 * @return Texto con el Token añadido.
	 */
	public static String addToken(String texto, String token)
	{
		StringBuffer sbResultado = new StringBuffer();
		
		int comienzoDolar = texto.lastIndexOf('$');
		
		if(comienzoDolar != -1)
		{	
			String comienzoToken = texto.substring(0, comienzoDolar);
			String finalToken = texto.substring(comienzoDolar + 4);
		
			sbResultado.append(comienzoToken);
			sbResultado.append(token);
			sbResultado.append(finalToken);
		}
		
		return sbResultado.toString();
	}
}
