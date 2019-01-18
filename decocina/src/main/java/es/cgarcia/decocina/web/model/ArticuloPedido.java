package es.cgarcia.decocina.web.model;

import org.springframework.stereotype.Controller;


/**
 * Artículos de un Pedido realizado.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class ArticuloPedido extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7944175871581538263L;

	/**
	 * Pedido.
	 */
	private Pedido pedido;
	
	/**
	 * Artículo.
	 */
	private Articulo articulo;
	
	/**
	 * Nombre del Artículo.
	 */
	private String nombre;
	
	/**
	 * Precio.
	 */
	private String precio;
	
	/**
	 * IVA.
	 */
	private String iva;
	
	/**
	 * Cantidad.
	 */
	private String cantidad;
	
	/**
	 * PrecioTotal. Número de Artículos por la cantidad.
	 */
	private String precioTotal;

	/**
	 * Uri de la imagen. Se guarda una copia en img/apedido/.
	 */
	private String uri;
	
	/**
	 * Devuelve el valor del atributo pedido.
	 *
	 * @return pedido
	 */
	public Pedido getPedido() {
		return pedido;
	}

	/**
	 * Fija el valor del atributo pedido.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	/**
	 * Devuelve el valor del atributo articulo.
	 *
	 * @return articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}

	/**
	 * Fija el valor del atributo articulo.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	/**
	 * Devuelve el valor del atributo nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Fija el valor del atributo nombre.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el valor del atributo precio.
	 *
	 * @return precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * Fija el valor del atributo precio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve el valor del atributo iva.
	 *
	 * @return iva
	 */
	public String getIva() {
		return iva;
	}

	/**
	 * Fija el valor del atributo iva.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setIva(String iva) {
		this.iva = iva;
	}

	/**
	 * Devuelve el valor del atributo cantidad.
	 *
	 * @return cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * Fija el valor del atributo cantidad.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Devuelve el valor del atributo precioTotal.
	 *
	 * @return precioTotal
	 */
	public String getPrecioTotal() {
		return precioTotal;
	}

	/**
	 * Fija el valor del atributo precioTotal.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrecioTotal(String precioTotal) {
		this.precioTotal = precioTotal;
	}

	/**
	 * Devuelve el valor del atributo uri.
	 *
	 * @return uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Fija el valor del atributo uri.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
}
