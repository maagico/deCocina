package es.cgarcia.decocina.web.exception;

/**
 * 
 * Excepción en la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class DeCocinaWebRuntimeException extends RuntimeException{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 3205385258251876327L;

	/**
	 * Constructor.
	 * 
	 * @param mensaje Mensaje de error.
	 */
	public DeCocinaWebRuntimeException(String mensaje){
		
		super(mensaje);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param mensaje Mensaje de error.
	 * @param cause Causa.
	 */
	public DeCocinaWebRuntimeException(String mensaje, Throwable cause){
		
		super(mensaje, cause);
	}
}
