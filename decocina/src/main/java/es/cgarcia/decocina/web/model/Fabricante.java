package es.cgarcia.decocina.web.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;


/**
 * Fabricante de los artículos.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Fabricante extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -2423256648836319461L;

	/**
	 * Nombre del Fabricante. 
	 */
	@NotEmpty
	@Size(max=99)
	private String nombre;
	
	/**
	 * Descripción del Fabricante.
	 */
	@Size(max=255)
	private String descripcion;

	/**
	 * Constructor.
	 */
	public Fabricante(){	
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
	 * Devuelve el valor del atributo descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Fija el valor del atributo descripcion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
