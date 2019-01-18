package es.cgarcia.decocina.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/**
 * Forma de Envío del Pedido.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class FormaEnvio extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 5352385154288396013L;

	/**
	 * Nombre de la Forma de Envío. 
	 */
	@NotEmpty
	@Size(max=255)
	private String nombre;
	
	/**
	 * Descripción de la Forma de Envío. 
	 */
	@Size(max=255)
	private String descripcion;

	/**
	 * String que contiene la clase de la Fórmula de Pago.
	 */
	@NotEmpty
	@Size(max=255)
	private String formula;
	
	/**
	 * Gasto una vez aplicada la Fórmula.
	 */
	private Double gasto;
	
	/**
	 * Formas de Fago de la Forma de Envío.
	 */
	private List<FormaEnvioFormaPago> formaEnvioFormasPago = new ArrayList<FormaEnvioFormaPago>();
	
	/**
	 * Constructor.
	 */
	public FormaEnvio(){	
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
	 * Devuelve el valor del atributo formaEnvioFormasPago.
	 *
	 * @return formaEnvioFormasPago
	 */
	public List<FormaEnvioFormaPago> getFormaEnvioFormasPago() {
		return formaEnvioFormasPago;
	}

	/**
	 * Fija el valor del atributo formaEnvioFormasPago.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaEnvioFormasPago(
			List<FormaEnvioFormaPago> formaEnvioFormasPago) {
		this.formaEnvioFormasPago = formaEnvioFormasPago;
	}
}
