/**
 * 
 */
package es.cgarcia.decocina.admin.buscador;

import es.cgarcia.decocina.admin.annotation.CampoForm;

/**
 * Buscador de las Categorías y Artículos.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class CategoriaArticuloBuscador extends BuscadorBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -733079666208290413L;
	
	/**
	 * Id de la Categoría y del Artículo.
	 */
	@CampoForm
	private String idCatArt = "";
	
	/**
	 * Path.
	 */
	@CampoForm
	private String path;
	
	/**
	 * Nombre de la Categoría y de los Artículos a buscar.
	 */
	@CampoForm
	private String nombre = "";
	
	/**
	 * Indica que se viene desde la raíz(para una búsqueda por ejemplo).
	 */
	@CampoForm
	private Boolean desdeRaiz = false;
	
	/**
	 * Indica que se viene desde el index.
	 */
	@CampoForm
	private Boolean desdeInicio = false;

	/**
	 * Devuelve el valor del atributo idCatArt.
	 *
	 * @return idCatArt
	 */
	public String getIdCatArt() {
		return idCatArt;
	}

	/**
	 * Fija el valor del atributo idCatArt.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setIdCatArt(String idCatArt) {
		this.idCatArt = idCatArt;
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
	 * Devuelve el valor del atributo desdeRaiz.
	 *
	 * @return desdeRaiz
	 */
	public Boolean getDesdeRaiz() {
		return desdeRaiz;
	}
	
	/**
	 * Fija el valor del atributo desdeRaiz.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDesdeRaiz(Boolean desdeRaiz) {
		this.desdeRaiz = desdeRaiz;
	}

	/**
	 * Devuelve el valor del atributo desdeInicio.
	 *
	 * @return desdeInicio
	 */
	public Boolean getDesdeInicio() {
		return desdeInicio;
	}

	/**
	 * Fija el valor del atributo desdeInicio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDesdeInicio(Boolean desdeInicio) {
		this.desdeInicio = desdeInicio;
	}
	
}
