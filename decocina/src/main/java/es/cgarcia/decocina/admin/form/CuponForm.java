package es.cgarcia.decocina.admin.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.CuponBuscador;
import es.cgarcia.decocina.web.model.Cupon;

/**
 * Clase que encapsula al Cupón y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class CuponForm extends FormBase{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7187514818152199954L;

	/**
	 * Buscador de Cupones.
	 */
	@ClaseForm
	private CuponBuscador cuponBuscador;
	
	/**
	 * Cupón.
	 */
	@Valid
	@ClaseForm
	private Cupon cupon = new Cupon();
	
	/**
	 * Constructor.
	 */
	public CuponForm(){
		
	}

	/**
	 * Constructor.
	 * 
	 * @param cupon Cupón.
	 */
	public CuponForm(Cupon cupon){
		this.cupon = cupon;
	}
	
	/**
	 * Devuelve el valor del atributo cuponBuscador.
	 *
	 * @return cuponBuscador
	 */
	public CuponBuscador getCuponBuscador() {
		return cuponBuscador;
	}

	/**
	 * Fija el valor del atributo cuponBuscador.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCuponBuscador(CuponBuscador cuponBuscador) {
		this.cuponBuscador = cuponBuscador;
	}

	/**
	 * Devuelve el valor del atributo cupon.
	 *
	 * @return cupon
	 */
	public Cupon getCupon() {
		return cupon;
	}

	/**
	 * Fija el valor del atributo cupon.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCupon(Cupon cupon) {
		this.cupon = cupon;
	}
}
