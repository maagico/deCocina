package es.cgarcia.decocina.web.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;


/**
 * Forma de Pago del pedido.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class FormaPago extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -8543736364530262437L;

	/**
	 * Nombre de la Fórmula de Envío. 
	 */
	@NotEmpty
	@Size(max=99)
	private String nombre;
	
	/**
	 * Descripción de la Fórmula de Envío.
	 */
	@Size(max=255)
	private String descripcion;
	
	/**
	 * String que contiene la clase de la Fórmula de Envío.
	 */
	@NotEmpty
	@Size(max=1024)
	private String formula;
	
	/**
	 * Comentario de la Forma de Pago.
	 */
	@Size(max=2048)
	private String comentario;
	
	/**
	 * Gasto una vez aplicada la Fórmula.
	 */
	private Double gasto;
	
	/**
	 * Indica que la Forma de Pago implica un paso adicional a la hora de pagar.
	 */
	@NotNull
	private Boolean pasoAdicional;
	
	/**
	 * URL en caso de que haya siguiente paso.
	 */
	@Size(max=255)
	private String url;
	
	/**
	 * Nombre del botón.
	 */
	@NotEmpty
	@Size(max=255)
	private String nombreBoton;
	
	/**
	 * Indica si la forma de pago está en modo Test.
	 */
	private Boolean test;
	
	/**
	 * Constructor.
	 */
	public FormaPago(){
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

	/**
	 * Devuelve el valor del atributo formula.
	 *
	 * @return formula
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * Fija el valor del atributo formula.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}

	/**
	 * Devuelve el valor del atributo comentario.
	 *
	 * @return comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Fija el valor del atributo comentario.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Devuelve el valor del atributo gasto.
	 *
	 * @return gasto
	 */
	public Double getGasto() {
		return gasto;
	}

	/**
	 * Fija el valor del atributo gasto.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setGasto(Double gasto) {
		this.gasto = gasto;
	}

	/**
	 * Devuelve el valor del atributo pasoAdicional.
	 *
	 * @return pasoAdicional
	 */
	public Boolean getPasoAdicional() {
		return pasoAdicional;
	}

	/**
	 * Fija el valor del atributo pasoAdicional.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPasoAdicional(Boolean pasoAdicional) {
		this.pasoAdicional = pasoAdicional;
	}
	
	/**
	 * Devuelve el valor del atributo url.
	 *
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Fija el valor del atributo url.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Devuelve el valor del atributo nombreBoton.
	 *
	 * @return nombreBoton
	 */
	public String getNombreBoton() {
		return nombreBoton;
	}

	/**
	 * Fija el valor del atributo nombreBoton.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNombreBoton(String nombreBoton) {
		this.nombreBoton = nombreBoton;
	}

	/**
	 * Devuelve el valor del atributo test.
	 *
	 * @return test
	 */
	public Boolean getTest() {
		return test;
	}

	/**
	 * Fija el valor del atributo test.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setTest(Boolean test) {
		this.test = test;
	}
}
