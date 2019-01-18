package es.cgarcia.decocina.web.model;

import org.springframework.stereotype.Component;

/**
 * Tipos de Estado de un Pedido.
 * 
 * @author Miguel Ángel Álvarez García
 */
@Component
public class Estado extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -7495612928808541897L;

	/**
	 * Nombre del Estado.
	 */
	private String nombre;
	
	/**
	 * Descripción del Estado.
	 */
	private String Descripcion;

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
	 * Devuelve el valor del atributo descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return Descripcion;
	}

	/**
	 * Fija el valor del atributo descripcion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
}
