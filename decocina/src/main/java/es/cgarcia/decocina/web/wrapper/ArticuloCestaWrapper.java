/**
 * 
 */
package es.cgarcia.decocina.web.wrapper;

import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Cliente;

/**
 * Artículo de la Cesta.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ArticuloCestaWrapper extends WrapperBase{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6572744611176023351L;

	/**
	 * Cliente.
	 */
	private Cliente cliente;
	
	/**
	 * Artículo de la cesta.
	 */
	private Articulo articulo;
	
	/**
	 * Cantidad del Artículo.
	 */
	private Integer cantidad; 
	
	/**
	 * Constructor.
	 * 
	 * @param cliente Cliente.
	 * @param articulo Artículo.
	 * @param cantidad Cantidad del Artículo.
	 */
	public ArticuloCestaWrapper(Cliente cliente, Articulo articulo, Integer cantidad){
		
		this.cliente = cliente;
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

	/**
	 * Devuelve el valor del atributo cliente.
	 *
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Fija el valor del atributo cliente.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	 * Devuelve el precio de todos los Artículos.
	 *
	 * @return precio Artículo x cantidad.
	 */
	public Double getPrecio() {
		
		Double precio = articulo.getPrecio();
		return precio * cantidad;
	}
	
	/**
	 * Devuelve el prrecio del Artículo.
	 * @return Precio del Artículo.
	 */
	public Double getPrecioArticulo(){
		
		Double precio = articulo.getPrecio();
		return precio;
	}
	
	/**
	 * Devuelve el peso de todos los Artículos.
	 * @return peso Artículo X peso.
	 */
	public Double getPeso(){
		Double peso = articulo.getPeso();
		return peso + cantidad;
	}
}

