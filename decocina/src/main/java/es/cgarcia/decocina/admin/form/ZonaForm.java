package es.cgarcia.decocina.admin.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.ZonaBuscador;
import es.cgarcia.decocina.web.model.Zona;

/**
 * Clase que encapsula a la Zona y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ZonaForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -7071855058100084729L;

	/**
	 * Buscador de Zonas.
	 */
	@ClaseForm
	private ZonaBuscador zonaBuscador;
	
	/**
	 * Fabricante.
	 */
	@Valid
	@ClaseForm
	private Zona zona = new Zona();
	
	/**
	 * Constructor.
	 */
	public ZonaForm(){
	}
	
	/**
	 * Constructor.
	 * @param zona Zona.
	 */
	public ZonaForm(Zona zona){
		this.zona = zona;
	}

	/**
	 * Devuelve el valor del atributo zonaBuscador.
	 *
	 * @return zonaBuscador
	 */
	public ZonaBuscador getZonaBuscador() {
		return zonaBuscador;
	}

	/**
	 * Fija el valor del atributo zonaBuscador.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setZonaBuscador(ZonaBuscador zonaBuscador) {
		this.zonaBuscador = zonaBuscador;
	}

	/**
	 * Devuelve el valor del atributo zona.
	 *
	 * @return zona
	 */
	public Zona getZona() {
		return zona;
	}

	/**
	 * Fija el valor del atributo zona.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	
}
