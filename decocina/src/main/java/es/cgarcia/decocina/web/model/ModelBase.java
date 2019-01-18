package es.cgarcia.decocina.web.model;

import java.io.Serializable;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Base de la que deben heredar todos los objetos que se correspondan con el modelo de datos.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public class ModelBase implements Serializable
{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6426518147986752532L;
	
	/**
	 * Id del modelo.
	 */
	@CampoForm
	protected Long id;
	
	/**
	 * Fija el valor del atributo id.
	 *
	 * @param id Nuevo valor del atributo.
	 */
	public void setId(Long id)
	{
		this.id = id;
	}
	
	/**
	 * Devuelve el valor del atributo id.
	 * 
	 * @return valor del atributo id.
	 */
	public Long getId() 
	{
		return id;
	}
}
