package es.cgarcia.decocina.web.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilidades para convertir un texto a MD5.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public final class MD5Utils 
{
	/**
	 * Logger.
	 */
	private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);    
	
	/**
	 * Convierte un texto a MD5.
	 * 
	 * @param texto Texto a convertir.
	 * @return Texto convertido a MD5.
	 */
	public static String MD5(String texto) {	    
	    
		try{
	    	
	    	MessageDigest md = MessageDigest.getInstance("MD5");
	        StringBuffer sb = new StringBuffer();
	        
	        byte buf[] = texto.getBytes();
	        byte[] md5 = md.digest(buf);
	        
	       	for( int i = 0 ; i < md5.length ; i++ ) {
	       		
	       		String tmpStr = "0" + Integer.toHexString( (0xff & md5[i]));
	       		sb.append(tmpStr.substring(tmpStr.length()-2));
	       	}   
	       	
	       	return sb.toString();
		
	    }catch (Exception e)
		{
			logger.error("Error creando MD5", e);
		}
	    
		return texto;
	}
	
	/**
	 * Constructor.
	 */
	private MD5Utils(){
	}
}
