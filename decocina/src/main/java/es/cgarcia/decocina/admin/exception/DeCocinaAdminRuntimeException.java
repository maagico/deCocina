package es.cgarcia.decocina.admin.exception;

/**
 * 
 * Excepción en la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class DeCocinaAdminRuntimeException extends RuntimeException{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 3205385258251876327L;

	/**
	 * Constructor.
	 * 
	 * @param mensaje Mensaje de error.
	 * @param cause Causa.
	 */
	public DeCocinaAdminRuntimeException(String mensaje, Throwable cause){
		
		super(mensaje, cause);
	}
}
