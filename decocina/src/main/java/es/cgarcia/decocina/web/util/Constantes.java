package es.cgarcia.decocina.web.util;

/**
 * Constantes de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 */
public final class Constantes {

	/**
	 * Usuario administrador de la parte Admin.
	 */
	public static final String USUARIO_ADMIN_LOGUEADO = "USUARIO_ADMIN_LOGUEADO";
	
	/**
	 * Cliente de la parte Web.
	 */
	public static final String CLIENTE_WEB_LOGUEADO = "CLIENTE_WEB_LOGUEADO";
	
	/**
	 * Prefijo de la cookie de sesión.
	 */
	public static final String COOKIE_OI = "OI";
	
	/**
	 * Ruta pública de las imágenes.
	 */
	public static final String RUTA_PRIVADA_IMAGENES = "/WEB-INF/img/articulos/";
	
	/**
	 * Ruta privada de las imágenes.
	 */
	public static final String RUTA_PUBLICA_IMAGENES = "img/articulos/";
	
	/**
	 * Ruta privada de las imágenes de las que se ha hecho un pedido.
	 */
	public static final String RUTA_PRIVADA_IMAGENES_PEDIDO = "/WEB-INF/img/apedido/";
	
	/**
	 * Ruta privada de las imágenes de las que se ha hecho un pedido.
	 */
	public static final String RUTA_PUBLICA_IMAGENES_PEDIDO = "img/apedido/";

	/**
	 * Ancho de la imagen pequeña.
	 */
	public static final Integer ANCHO_IMAGEN_P = 200;
	
	/**
	 * Alto de la imagen pequeña.
	 */
	public static final Integer ALTO_IMAGEN_P = 200;

	/**
	 * Ancho de la imagen thumbnails.
	 */
	public static final Integer ANCHO_IMAGEN_T = 100;
	
	/**
	 * Alto de la imagen thumbnails.
	 */
	public static final Integer ALTO_IMAGEN_T = 100;

	/**
	 * Tipo de imagen G.
	 */
	public static final String TIPO_IMAGEN_G = "G";
	
	/**
	 * Tipo de imagen P.
	 */
	public static final String TIPO_IMAGEN_P = "P";
	
	/**
	 * Tipo de imagen T.
	 */
	public static final String TIPO_IMAGEN_T = "T";
	
	/**
	 * Parte del nombre del fichero de imagen G _g.
	 */
	public static final String TIPO_IMAGEN_FICHERO_G = "_g";
	
	/**
	 * Parte del nombre del fichero de imagen P _p.
	 */
	public static final String TIPO_IMAGEN_FICHERO_P = "_p";
	
	/**
	 * Parte del nombre del fichero de imagen T _t.
	 */
	public static final String TIPO_IMAGEN_FICHERO_T = "_t";
	
	/**
	 * Porcentaje.
	 */
	public static final Integer CUPON_PORCENTAJE = 0; 
	
	/**
	 * Importe fijo.
	 */
	public static final Integer CUPON_IMPORTE_FIJO = 1; 
	
	/**
	 * ID de la Forma de Pago para las transferencias.
	 */
	public static final Long ID_FORMA_PAGO_TRANSFERENCIA = 3L;
	
	/**
	 * ID de la Forma de Pago para Paypal.
	 */
	public static final Long ID_FORMA_PAGO_PAYPAL = 4L;

	/**
	 * Texto del País España.
	 */
	public static final String TEXTO_ESPAÑA = "España";

	/**
	 * Número de artículos en el index.
	 */
	public static final Integer NUMERO_NOVEDADES = 30;

	/**
	 * Número de Artículos más vendidos.
	 */
	public static final Integer NUMERO_MAS_VENDIDOS = 3;

	/**
	 * Número de Artículos más vistos.
	 */
	public static final Integer NUMERO_MAS_VISTOS = 3;
	
	/**
	 * Número de ventas relacionadas.
	 */
	public static final Integer NUMERO_VENTAS_RELACIONADAS = 3;
	
	/**
	 * Número de últimos Articulos añadidos en el Admin.
	 */
	public static Long NUMERO_ULTIMOS_ARTICULOS = 10L;
	
	/**
	 * Localhost.
	 */
	public static String LOCALHOST = "https://127.0.0.1";
	
	/**
	 * Constructor.
	 */
	private Constantes(){	
	}
}
