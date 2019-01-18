/**
 * 
 */
package es.cgarcia.decocina.web.json;

/**
 * Artículo JSON para la Cesta.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ArticuloJSON extends JSONBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -3255646563511196412L;
	
	/**
	 * Id del Artículo.
	 */
	private Long id;
	
	/**
	 * Precio del Artículo.
	 */
	private String precio;
	
	/**
	 * Número de Artículos.
	 */
	private Integer cantidad;
	
	/**
	 * Precio del Artículo por la cantidad.
	 */
	private String precioTotal;
	
	/**
	 * Devuelve el valor del atributo id.
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Fija el valor del atributo id.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Devuelve el valor del atributo cantidad.
	 *
	 * @return cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * Fija el valor del atributo cantidad.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCantidad(Integer cantidad) {
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
	
	
}
