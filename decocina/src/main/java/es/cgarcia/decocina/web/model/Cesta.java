package es.cgarcia.decocina.web.model;

import org.springframework.stereotype.Component;

/**
 * Cesta del Cliente.
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Cesta extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1337664108065275984L;

	/**
	 * Cliente.
	 */
	private Cliente cliente;
	
	/**
	 * Artículo.
	 */
	private Articulo articulo;
	
	/**
	 * Cantidad del Artículo.
	 */
	private int cantidad;

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
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Fija el valor del atributo cantidad.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
