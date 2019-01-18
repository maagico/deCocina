package es.cgarcia.decocina.web.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;


/**
 * Categorías de la Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Categoria extends ModelBase
{
	
	/** serialVersionUID. */
	private static final long serialVersionUID = -974483221935683740L;

	/**
	 * Articulos de la categoría.
	 */
	private List<Articulo> articulos;
	
	/**
	 * Meta descripción de la categoría.
	 */
	@NotEmpty
	@Size(max=255)
	private String metaDescripcion;

	/**
	 * Keywords de la categoría.
	 */
	@NotEmpty
	@Size(max=255)
	private String metaKeywords;
	
	/**
	 * Descripcion de la categoría.
	 */
	@Size(max=1024)
	private String descripcion;
	
	/**
	 * Nombre.
	 */
	@NotEmpty
	@Size(max=255)
	private String nombre;
	
	/** 
	 * Orden.
	 */
	@NotNull
	private Integer orden;
	
	/**
	 * URL amigable.
	 */
	private String urlAmigable;
	
	/**
	 * Indica si la categoría está activa.
	 */
	private Boolean activa;
	
	/**
	 * Id del padre de la categoría.
	 */
	private Long idPadre;
	
	/**
	 * Constructor.
	 */
	public Categoria(){
	}

	/**
	 * Devuelve el valor del atributo.
	 *
	 * @return El valor del atributo.
	 */
	public List<Articulo> getArticulos() {
		return articulos;
	}

	/**
	 * Fija el valor del atributo articulos.
	 *
	 * @param articulos Nuevo valor del atributo.
	 */
	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	/**
	 * Devuelve el valor del atributo metaDescripcion.
	 *
	 * @return El valor del atributo.
	 */
	public String getMetaDescripcion() {
		return metaDescripcion;
	}

	/**
	 * Fija el valor del atributo metaDescripcion.
	 *
	 * @param metaDescripcion Nuevo valor del atributo.
	 */
	public void setMetaDescripcion(String metaDescripcion) {
		this.metaDescripcion = metaDescripcion;
	}

	/**
	 * Devuelve el valor del atributo.
	 *
	 * @return El valor del atributo.
	 */
	public String getMetaKeywords() {
		return metaKeywords;
	}

	/**
	 * Fija el valor del atributo metaKeywords.
	 *
	 * @param metaKeywords Nuevo valor del atributo.
	 */
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	/**
	 * Devuelve el valor del atributo descripcion.
	 *
	 * @return El valor del atributo.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Fija el valor del atributo descripcion.
	 *
	 * @param descripcion Nuevo valor del atributo.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve el valor del atributo nombre.
	 *
	 * @return El valor del atributo.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Fija el valor del atributo nombre.
	 *
	 * @param nombre Nuevo valor del atributo.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el valor del atributo orden.
	 *
	 * @return El valor del atributo.
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * Fija el valor del atributo orden.
	 *
	 * @param orden Nuevo valor del atributo.
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * Devuelve el valor del atributo urlAmigable.
	 *
	 * @return El valor del atributo.
	 */
	public String getUrlAmigable() {
		return urlAmigable;
	}

	/**
	 * Fija el valor del atributo urlAmigable.
	 *
	 * @param urlAmigable Nuevo valor del atributo.
	 */
	public void setUrlAmigable(String urlAmigable) {
		this.urlAmigable = urlAmigable;
	}

	/**
	 * Devuelve el valor del atributo activa.
	 *
	 * @return El valor del atributo.
	 */
	public Boolean getActiva() {
		return activa;
	}

	/**
	 * Fija el valor del atributo activa.
	 *
	 * @param activa Nuevo valor del atributo.
	 */
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	/**
	 * Devuelve el valor del atributo idPadre.
	 *
	 * @return El valor del atributo.
	 */
	public Long getIdPadre() {
		return idPadre;
	}

	/**
	 * Fija el valor del atributo idPadre.
	 *
	 * @param idPadre Nuevo valor del atributo.
	 */
	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}
}
