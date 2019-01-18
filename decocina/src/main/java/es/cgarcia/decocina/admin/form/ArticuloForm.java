package es.cgarcia.decocina.admin.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.CampoForm;
import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.web.model.Articulo;

/**
 * Clase que encapsula al Artículo y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ArticuloForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1140188326119442032L;

	/**
	 * Buscador de Categorías y Artículos.
	 */
	@ClaseForm
	private CategoriaArticuloBuscador categoriaArticuloBuscador;
	
	/**
	 * Artículo.
	 */
	@Valid
	@ClaseForm
	private Articulo articulo = new Articulo();
	
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
	 * Constructor.
	 */
	public ArticuloForm(){
	}
	
	/**
	 * Constructor.
	 * 
	 * @param articulo Artículo.
	 */
	public ArticuloForm(Articulo articulo){
		this.articulo = articulo;
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
	 * Devuelve el valor del atributo articulo.
	 *
	 * @return articulo
	 */
	public Articulo getArticulo() {
		return articulo;
	}

	/**
	 * Fija el valor del atributo articulo.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	/**
	 * Devuelve el valor del atributo path.
	 *
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Fija el valor del atributo path.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Devuelve el valor del atributo pathVolver.
	 *
	 * @return pathVolver
	 */
	public String getPathVolver() {
		return pathVolver;
	}

	/**
	 * Fija el valor del atributo pathVolver.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPathVolver(String pathVolver) {
		this.pathVolver = pathVolver;
	}
}
