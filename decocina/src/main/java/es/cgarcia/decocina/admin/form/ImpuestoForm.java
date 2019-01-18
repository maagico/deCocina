package es.cgarcia.decocina.admin.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.ImpuestoBuscador;
import es.cgarcia.decocina.web.model.Impuesto;

/**
 * Clase que encapsula al Impuesto y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ImpuestoForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1140188326119442032L;

	/**
	 * Buscador de impuestos.
	 */
	@ClaseForm
	private ImpuestoBuscador impuestoBuscador;
	
	/**
	 * Impuesto.
	 */
	@Valid
	@ClaseForm
	private Impuesto impuesto = new Impuesto();
	
	/**
	 * Constructor.
	 */
	public ImpuestoForm(){
	}
	
	/**
	 * Constructor.
	 * 
	 * @param impuesto Impuesto.
	 */
	public ImpuestoForm(Impuesto impuesto){
		this.impuesto = impuesto;
	}

	/**
	 * Devuelve el valor del atributo impuestoBuscador.
	 *
	 * @return impuestoBuscador
	 */
	public ImpuestoBuscador getImpuestoBuscador() {
		return impuestoBuscador;
	}

	/**
	 * Fija el valor del atributo impuestoBuscador.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setImpuestoBuscador(ImpuestoBuscador impuestoBuscador) {
		this.impuestoBuscador = impuestoBuscador;
	}

	/**
	 * Devuelve el valor del atributo impuesto.
	 *
	 * @return impuesto
	 */
	public Impuesto getImpuesto() {
		return impuesto;
	}

	/**
	 * Fija el valor del atributo impuesto.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}
}
