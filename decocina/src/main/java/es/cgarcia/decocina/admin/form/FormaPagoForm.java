package es.cgarcia.decocina.admin.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.FormaPagoBuscador;
import es.cgarcia.decocina.web.model.FormaPago;

/**
 * Clase que encapsula a FormaPago y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class FormaPagoForm extends FormBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6420596005459183299L;

	/**
	 * Buscador de Fórmulas de Envío.
	 */
	@ClaseForm
	private FormaPagoBuscador formaPagoBuscador;
	
	/**
	 * Fabricante.
	 */
	@Valid
	@ClaseForm
	private FormaPago formaPago = new FormaPago();
	
	/**
	 * Constructor.
	 */
	public FormaPagoForm(){	
	}

	/**
	 * Constructor.
	 * 
	 * @param formaPago Forma de Pago.
	 */
	public FormaPagoForm(FormaPago formaPago){	
		this.formaPago = formaPago;
	}

	/**
	 * Devuelve el valor del atributo formaPagoBuscador.
	 *
	 * @return formaPagoBuscador
	 */
	public FormaPagoBuscador getFormaPagoBuscador() {
		return formaPagoBuscador;
	}

	/**
	 * Fija el valor del atributo formaPagoBuscador.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaPagoBuscador(FormaPagoBuscador formaPagoBuscador) {
		this.formaPagoBuscador = formaPagoBuscador;
	}

	/**
	 * Devuelve el valor del atributo formaPago.
	 *
	 * @return formaPago
	 */
	public FormaPago getFormaPago() {
		return formaPago;
	}

	/**
	 * Fija el valor del atributo formaPago.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}
}
