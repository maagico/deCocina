/**
 * 
 */
package es.cgarcia.decocina.web.analytics;

import java.io.Serializable;

/**
 * Artículo perteneciente a una transacción(un pedido) de analytics.
 * 
 * @author Miguel Ángel Álvarez García.
 *
 */
public class ArticuloAnalytics implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6316155924404686139L;

	/**
	 * Id de la transacción a la que pertenece el Artículo.
	 */
	private String id;
	
	/**
	 * Nombre del Artículo.
	 */
	private String name;
	
	/**
	 * Precio del Artículo.
	 */
	private Double price;
	
	/**
	 * Cantidad de Artículos.
	 */
	private Integer quantity;

	/**
	 * Devuelve el valor del atributo id.
	 *
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Fija el valor del atributo id.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Devuelve el valor del atributo name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Fija el valor del atributo name.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve el valor del atributo price.
	 *
	 * @return price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Fija el valor del atributo price.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * Devuelve el valor del atributo quantity.
	 *
	 * @return quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Fija el valor del atributo quantity.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
