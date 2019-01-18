package es.cgarcia.decocina.web.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/**
 * 
 * Impuesto de la Zona o Artículo.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Impuesto extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6077871150524620756L;

	/**
	 * Nomnbre del impuesto.
	 */
	@NotEmpty
	@Size(max=255)
	private String nombre;
	
	/**
	 * Valor del impuesto.
	 */
	@Digits(integer=6, fraction=2)
	@NotNull
	private Double valor;
	
	/**
	 * Descripción del impuesto. 
	 */
	private String descripcion;
	
	/**
	 * Indica que el Impuesto es para un Artículo.
	 */
	private Boolean paraArticulo;
	
	/**
	 * Indica que el Impuesto es para una Zona
	 */
	private Boolean paraZona;
	
	/**
	 * Constructor.
	 */
	public Impuesto(){
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
	 * Devuelve el valor del atributo valor.
	 *
	 * @return valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * Fija el valor del atributo valor.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setValor(Double valor) {
		this.valor = valor;
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

	/**
	 * Devuelve el valor del atributo paraArticulo.
	 *
	 * @return paraArticulo
	 */
	public Boolean getParaArticulo() {
		return paraArticulo;
	}

	/**
	 * Fija el valor del atributo paraArticulo.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setParaArticulo(Boolean paraArticulo) {
		this.paraArticulo = paraArticulo;
	}

	/**
	 * Devuelve el valor del atributo paraZona.
	 *
	 * @return paraZona
	 */
	public Boolean getParaZona() {
		return paraZona;
	}

	/**
	 * Fija el valor del atributo paraZona.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setParaZona(Boolean paraZona) {
		this.paraZona = paraZona;
	}
}