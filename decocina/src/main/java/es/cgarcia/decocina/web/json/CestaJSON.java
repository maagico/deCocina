/**
 * 
 */
package es.cgarcia.decocina.web.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Cesta para las peticiones AJAX.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class CestaJSON extends JSONBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1890137537676495504L;
	
	/**
	 * Gasto de la Forma de Envío.
	 */
	private String gastoFormaEnvio;
	
	/**
	 * Gasto de la Forma de Pago.
	 */
	private String gastoFormaPago;
	
	/**
	 * Precio de los Artículos. 
	 */
	private String subTotal;
	
	/**
	 * Precio total de la cesta. Con los Gastos de Envío y Pago. 
	 */
	private String precioTotal;
	
	/**
	 * Artículos de la cesta.
	 */
	private List<ArticuloJSON> articulos = new ArrayList<ArticuloJSON>();
	
	/**
	 * Devuelve el valor del atributo gastoFormaEnvio.
	 *
	 * @return gastoFormaEnvio
	 */
	public String getGastoFormaEnvio() {
		return gastoFormaEnvio;
	}

	/**
	 * Fija el valor del atributo gastoFormaEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setGastoFormaEnvio(String gastoFormaEnvio) {
		this.gastoFormaEnvio = gastoFormaEnvio;
	}

	/**
	 * Devuelve el valor del atributo gastoFormaPago.
	 *
	 * @return gastoFormaPago
	 */
	public String getGastoFormaPago() {
		return gastoFormaPago;
	}

	/**
	 * Fija el valor del atributo gastoFormaPago.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setGastoFormaPago(String gastoFormaPago) {
		this.gastoFormaPago = gastoFormaPago;
	}
	
	/**
	 * Devuelve el valor del atributo subTotal.
	 *
	 * @return subTotal
	 */
	public String getSubTotal() {
		return subTotal;
	}

	/**
	 * Fija el valor del atributo subTotal.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * Devuelve el valor del atributo precioTotal.
	 *
	 * @return precio
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
	 * Devuelve el valor del atributo articulos.
	 *
	 * @return articulos
	 */
	public List<ArticuloJSON> getArticulos() {
		return articulos;
	}

	/**
	 * Fija el valor del atributo articulos.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setArticulos(List<ArticuloJSON> articulos) {
		this.articulos = articulos;
	}
	
	/**
	 * Añade un Artículo.
	 * @param articuloJSON ArticuloJSON.
	 */
	public void addArticulo(ArticuloJSON articuloJSON){
		articulos.add(articuloJSON);
	}
}
