package es.cgarcia.decocina.admin.util;

import javax.servlet.http.HttpServletRequest;


/**
 * Utilidades para las URLs.
 * 
 * @author Miguel Ángel Álvarez García
 */
public final class URLUtils 
{
	/**
	 * Constructor.
	 */
	private URLUtils(){	
	}
	
	/**
	 * Crea la URL de la categoría.
	 * 
	 * @param id Id de la categoría.
	 * @param nombre Nombre de la categoría.
	 * @return URL para la categoría.
	 */
	public static String crearUrlAmigableCategoria(long id, String nombre)
	{
		String nombreCaracteresCambiados = cambiarCaracteres(nombre);
		
		StringBuffer sbURLAmigable = new StringBuffer();
		
		sbURLAmigable.append(nombreCaracteresCambiados);
		sbURLAmigable.append("-c-");
		sbURLAmigable.append(id);
		
		String strURL = sbURLAmigable.toString();
		
		strURL = strURL.toLowerCase();
		
		return strURL;
	}
	
	/**
	 * Crea la URL del Artículo.
	 * 
	 * @param id Id del Artículo.
	 * @param nombre Nombre del Artículo.
	 * @return URL para el Artículo.
	 */
	public static String crearUrlAmigableProducto(Long id, String nombre) {
		
		String nombreCaracteresCambiados = cambiarCaracteres(nombre);
		
		StringBuffer sbURLAmigable = new StringBuffer();
		
		sbURLAmigable.append(nombreCaracteresCambiados);
		sbURLAmigable.append("-a-");
		sbURLAmigable.append(id);
		
		String strURL = sbURLAmigable.toString();
		
		strURL = strURL.toLowerCase();
		
		return strURL;
	}
	
	/**
	 * Cambia los caracteres de la cadena recibida.
	 * 
	 * @param cadena Cadena a la que se le cambiarán los caracteres.
	 * @return Cadena con los caracteres cambiados.
	 */
	public static String cambiarCaracteres(String cadena)
	{
		cadena = cadena.replaceAll("ü","-");
		cadena = cadena.replaceAll(":","-");
		cadena = cadena.replaceAll(",","-");
		cadena = cadena.replaceAll("\\(","-");
		cadena = cadena.replaceAll("\\)","-");
		cadena = cadena.replaceAll(",","-");
		cadena = cadena.replaceAll("/","-");
		cadena = cadena.replaceAll(" ","-");
		cadena = cadena.replaceAll("á","a");
		cadena = cadena.replaceAll("é","e");
		cadena = cadena.replaceAll("í","i");
		cadena = cadena.replaceAll("ó","o");
		cadena = cadena.replaceAll("ú","u");
		cadena = cadena.replaceAll("ñ","n");
		
		return cadena;
	}
	
	/**
	 * Cambia los caracteres de la cadena recibida.
	 * 
	 * @param cadena Cadena a la que se le cambiarán los caracteres.
	 * @return Cadena con los caracteres cambiados.
	 */
	public static String cambiarCaracteresImagen(String cadena)
	{
		cadena = cadena.replaceAll("ü","_");
		cadena = cadena.replaceAll(":","_");
		cadena = cadena.replaceAll(",","_");
		cadena = cadena.replaceAll("\\(","_");
		cadena = cadena.replaceAll("\\)","_");
		cadena = cadena.replaceAll(",","_");
		cadena = cadena.replaceAll("\\.","_");
		cadena = cadena.replaceAll("/","_");
		cadena = cadena.replaceAll(" ","_");
		cadena = cadena.replaceAll("á","a");
		cadena = cadena.replaceAll("é","e");
		cadena = cadena.replaceAll("í","i");
		cadena = cadena.replaceAll("ó","o");
		cadena = cadena.replaceAll("ú","u");
		cadena = cadena.replaceAll("ñ","n");
		
		return cadena;
	}
	
	/**
	 * Devuelve la URL del servidor.
	 * @param request Request.
	 */
	public static String getURL(HttpServletRequest request){
		
		Boolean isSecure = request.isSecure();
    	
	    	String protocolo = null;
	    	
	    	if(isSecure){
	    		
	    		protocolo = "https://";
	    	
	    	}else{
	    		
	    		protocolo = "http://";
	    	}
	    	
			String serverName = request.getServerName();
			int puerto = request.getServerPort();
			String contexto = request.getContextPath();
			
			StringBuilder sbURL = new StringBuilder();
			sbURL.append(protocolo);
			sbURL.append(serverName);
			
			if(puerto != 80){
				
				sbURL.append(':');
				sbURL.append(puerto);
			}
			
			sbURL.append(contexto);
			sbURL.append('/');
			
			return sbURL.toString();
		}
}
