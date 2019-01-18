package es.cgarcia.decocina.admin.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.FabricanteBuscador;
import es.cgarcia.decocina.web.model.Fabricante;

/**
 * Clase que encapsula al Fabricante y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class FabricanteForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 443884969390037565L;

	/**
	 * Buscador de Fabricantes.
	 */
	@ClaseForm
	private FabricanteBuscador fabricanteBuscador;
	
	/**
	 * Fabricante.
	 */
	@Valid
	@ClaseForm
	private Fabricante fabricante = new Fabricante();
	
	/**
	 * Constructor.
	 */
	public FabricanteForm(){
		
	}
	/**
	 * Constructor.
	 * 
	 * @param fabricante Fabricante.
	 */
	public FabricanteForm(Fabricante fabricante){
		this.fabricante = fabricante;
	}
	
	/**
	 * Devuelve el valor del atributo fabricanteBuscador.
	 *
	 * @return fabricanteBuscador
	 */
	public FabricanteBuscador getFabricanteBuscador() {
		return fabricanteBuscador;
	}
	
	/**
	 * Fija el valor del atributo fabricanteBuscador.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFabricanteBuscador(FabricanteBuscador fabricanteBuscador) {
		this.fabricanteBuscador = fabricanteBuscador;
	}
	
	/**
	 * Devuelve el valor del atributo fabricante.
	 *
	 * @return fabricante
	 */
	public Fabricante getFabricante() {
		return fabricante;
	}
	
	/**
	 * Fija el valor del atributo fabricante.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
}
