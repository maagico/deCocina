package es.cgarcia.decocina.admin.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.FormaEnvioBuscador;
import es.cgarcia.decocina.web.model.FormaEnvio;

/**
 * Clase que encapsula a la Forma de Envío y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class FormaEnvioForm extends FormBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -7522347642916118239L;

	/**
	 * Buscador de Formas de Envío.
	 */
	@ClaseForm
	private FormaEnvioBuscador formaEnvioBuscador;
	
	/**
	 * Fabricante.
	 */
	@Valid
	@ClaseForm
	private FormaEnvio formaEnvio = new FormaEnvio();
	
	/**
	 * Constructor.
	 */
	public FormaEnvioForm(){
	}
	
	/**
	 * Constructor.
	 */
	public FormaEnvioForm(FormaEnvio formaEnvio){
		this.formaEnvio = formaEnvio;
	}

	/**
	 * Devuelve el valor del atributo formaEnvioBuscador.
	 *
	 * @return formaEnvioBuscador
	 */
	public FormaEnvioBuscador getFormaEnvioBuscador() {
		return formaEnvioBuscador;
	}

	/**
	 * Fija el valor del atributo formaEnvioBuscador.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaEnvioBuscador(FormaEnvioBuscador formaEnvioBuscador) {
		this.formaEnvioBuscador = formaEnvioBuscador;
	}

	/**
	 * Devuelve el valor del atributo formaEnvio.
	 *
	 * @return formaEnvio
	 */
	public FormaEnvio getFormaEnvio() {
		return formaEnvio;
	}

	/**
	 * Fija el valor del atributo formaEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormaEnvio(FormaEnvio formaEnvio) {
		this.formaEnvio = formaEnvio;
	}
}
