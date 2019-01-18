package es.cgarcia.decocina.admin.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.CampoForm;
import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.web.model.Categoria;

/**
 * Clase que encapsula a la Categoría y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class CategoriaForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 443884969390037565L;

	/**
	 * Buscador de categorías y artículos.
	 */
	@ClaseForm
	private CategoriaArticuloBuscador categoriaArticuloBuscador;
	
	/**
	 * Categoría.
	 */
	@Valid
	@ClaseForm
	private Categoria categoria = new Categoria();

	/**
	 * Path que contiene los ids de las Categorías.
	 */
	@CampoForm
	private String path;
	
	/**
	 * Path que contiene los ids de las Categorías menos el último.
	 */
	@CampoForm
	private String pathVolver;
	
	/**
	 * Indica el estado de la Categoría la primera vez que se entra a modificación.
	 */
	@CampoForm
	private Boolean activa;
	
	/**
	 * Constructor.
	 */
	public CategoriaForm(){
		
	}
	/**
	 * Constructor.
	 * 
	 * @param categoria Categoría.
	 */
	public CategoriaForm(Categoria categoria){
		this.categoria = categoria;
	}
	
	/**
	 * Devuelve el valor del atributo categoriaArticuloBuscador.
	 *
	 * @return categoriaArticuloBuscador
	 */
	public CategoriaArticuloBuscador getCategoriaArticuloBuscador() {
		return categoriaArticuloBuscador;
	}

	/**
	 * Fija el valor del atributo categoriaArticuloBuscador.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCategoriaArticuloBuscador(CategoriaArticuloBuscador categoriaArticuloBuscador) {
		this.categoriaArticuloBuscador = categoriaArticuloBuscador;
	}
	
	/**
	 * Devuelve el valor del atributo categoria.
	 * 
	 * @return El valor del atributo.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Fija el valor del atributo categoria.
	 * 
	 * @param categoria Nuevo valor del atributo.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * Devuelve el valor del atributo path.
	 * 
	 * @return El valor del atributo.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Fija el valor del atributo path. 
	 * @param path Nuevo valor del atributo.
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * Devuelve el valor del atributo pathVolver.
	 * 
	 * @return El valor del atributo.
	 */
	public String getPathVolver() {
		return pathVolver;
	}

	/**
	 * Fija el valor del atributo pathVolver. 
	 * 
	 * @param path Nuevo valor del atributo.
	 */
	public void setPathVolver(String pathVolver) {
		this.pathVolver = pathVolver;
	}

	/**
	 * Devuelve el valor del atributo activa.
	 *
	 * @return activa
	 */
	public Boolean getActiva() {
		return activa;
	}

	/**
	 * Fija el valor del atributo activa.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
}
