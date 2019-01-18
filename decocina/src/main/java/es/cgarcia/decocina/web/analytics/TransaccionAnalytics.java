/**
 * 
 */
package es.cgarcia.decocina.web.analytics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Transacción de Analytics. En este caso un pedido realizado.
 * 
 * @author Miguel Ángel Álvarez García.
 *
 */
public class TransaccionAnalytics implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 515600262048925231L;

	/**
	 * Id del pedido.
	 */
	private String id;
	
	/**
	 * Precio total del pedido incluidos los gastos de envío y pago.
	 */
	private Double revenue;
	
	/**
	 * Precio de los gastos de envio.
	 */
	private Double shipping;
	
	/**
	 * Impuestos totales.
	 */
	private Double tax;

	/**
	 * Articulos pertenecientes a la transacción.
	 */
	private List<ArticuloAnalytics> articulosAnalytics = new ArrayList<ArticuloAnalytics>();
	
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
	 * Devuelve el valor del atributo revenue.
	 *
	 * @return revenue
	 */
	public Double getRevenue() {
		return revenue;
	}

	/**
	 * Fija el valor del atributo revenue.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	/**
	 * Devuelve el valor del atributo shipping.
	 *
	 * @return shipping
	 */
	public Double getShipping() {
		return shipping;
	}

	/**
	 * Fija el valor del atributo shipping.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setShipping(Double shipping) {
		this.shipping = shipping;
	}

	/**
	 * Devuelve el valor del atributo tax.
	 *
	 * @return tax
	 */
	public Double getTax() {
		return tax;
	}

	/**
	 * Fija el valor del atributo tax.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	/**
	 * Añade un Artículo a la transación.
	 * @param articulo Articulo a añadir.
	 */
	public void addArticuloAnalytics(ArticuloAnalytics articulo){
		articulosAnalytics.add(articulo);
	}
	
	/**
	 * Devuelve la lista de Artículos.
	 * @return Lista de Artículos.
	 */
	public List<ArticuloAnalytics> getArticulosAnalytics(){
		
		return articulosAnalytics;
	}
}
