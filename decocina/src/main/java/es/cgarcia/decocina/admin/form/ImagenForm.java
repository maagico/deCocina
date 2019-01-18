package es.cgarcia.decocina.admin.form;

import org.springframework.web.multipart.MultipartFile;

import es.cgarcia.decocina.admin.annotation.CampoForm;
import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.web.model.Imagen;

/**
 * Clase que encapsula a la Imagen y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ImagenForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 443884969390037565L;
	
	/**
	 * Buscador de Categorías y Artículos.
	 */
	@ClaseForm
	private CategoriaArticuloBuscador categoriaArticuloBuscador;
	
	/**
	 * Imagen.
	 */
	private Imagen imagen = new Imagen();
	
	/**
	 * Id de la Categoría del Artículo.
	 */
	@CampoForm
	private Long idCategoria;
	
	/**
	 * Id del Artículo.
	 */
	@CampoForm
	private Long idArticulo;
	
	/**
	 * MultipartFile.
	 */
	private MultipartFile mfImagen;
	
	/**
	 * Constructor.
	 */
	public ImagenForm(){
	}
	
	/**
	 * Constructor.
	 * @param imagen Imagen.
	 */
	public ImagenForm(Imagen imagen){
		this.imagen = imagen;
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
	 * Devuelve el valor del atributo imagen.
	 *
	 * @return imagen
	 */
	public Imagen getImagen() {
		return imagen;
	}

	/**
	 * Fija el valor del atributo imagen.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	/**
	 * Devuelve el valor del atributo idCategoria.
	 *
	 * @return idCategoria
	 */
	public Long getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Fija el valor del atributo idCategoria.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * Devuelve el valor del atributo idArticulo.
	 *
	 * @return idArticulo
	 */
	public Long getIdArticulo() {
		return idArticulo;
	}

	/**
	 * Fija el valor del atributo idArticulo.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}

	/**
	 * Devuelve el valor del atributo mfImagen.
	 *
	 * @return mfImagen
	 */
	public MultipartFile getMfImagen() {
		return mfImagen;
	}

	/**
	 * Fija el valor del atributo mfImagen.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setMfImagen(MultipartFile mfImagen) {
		this.mfImagen = mfImagen;
	}
}
