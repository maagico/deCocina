package es.cgarcia.decocina.admin.exception;

/**
 * 
 * Excepción de integridad de base de datos (DataIntegrityViolationException).
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class DeCocinaAdminDIVRuntimeException extends RuntimeException{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2558045443783621614L;

	/**
	 * Constructor.
	 * 
	 * @param mensaje Mensaje de error.
	 * @param cause Causa.
	 */
	public DeCocinaAdminDIVRuntimeException(String mensaje, Throwable cause){
		
		super(mensaje, cause);
	}
}
