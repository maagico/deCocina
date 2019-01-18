package es.cgarcia.decocina.web.model;

import org.springframework.stereotype.Component;

/**
 * Imagen de un artículo o de una categoría.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Imagen extends ModelBase
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8921814833771451957L;

	/**
	 * Id del Artículo.
	 */
	private Long idArticulo;
	
	/**
	 * Nombre alternativo de la imagen.
	 */
	private String alt;
		
	/**
	 * Nombre de la imagen.
	 */
	private String nombre;
	
	/**
	 * Ruta de la imagem.
	 */
	private String uri;
	
	/**
	 * Tipo de la imagen.
	 */
	private String tipo;
	
	/**
	 * Título de la imagen.
	 */
	private String title;
	
	/**
	 * Indica si es la imagen principal.
	 */
	private Boolean principal;
	
	/**
	 * Constructor.
	 */
	public Imagen() {
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
	 * Devuelve el valor del atributo alt.
	 * @return valor del atributo alt.
	 */
	public String getAlt(){
		
		return alt;
	}
	
	/**
	 * Fija el valor del atributo alt
	 * @param alt 
	 */
	public void setAlt(String alt)
	{
		this.alt = alt;
	}
	
	/**
	 * Devuelve el valor del atributo nombre.
	 * @return valor del atributo nombre.
	 */
	public String getNombre(){
		
		return nombre;
	}
	
	/**
	 * Fija el valor del atributo nombre.
	 * @param nombre Nombre de la imagen.
	 */
	public void setNombre(String nombre){
		
		this.nombre = nombre;
	}
	
	/**
	 * Devuelve el valor del atributo uri.
	 * @return valor del atributo uri.
	 */
	public String getUri(){
		
		return uri;
	}

	/**
	 * Fija el valor del atributo uri
	 * @param uri URI.
	 */
	public void setUri(String uri){
		
		this.uri = uri;
	}
	
	/**
	 * Devuelve el valor del atributo tipo.
	 * @return valor del atributo tipo.
	 */
	public String getTipo(){
		
		return tipo;
	}
	
	/**
	 * Fija el valor del atributo tipo.
	 * @param tipo Tipo de la imagen.
	 */
	public void setTipo(String tipo){
		
		this.tipo = tipo;
	}
	
	/**
	 * Devuelve el valor del atributo title.
	 * @return valor del atributo title.
	 */
	public String getTitle(){
		
		return title;
	}
	
	/**
	 * Fija el valor del atributo title.
	 * @param tipo Tipo de la imagen. 
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}

	/**
	 * Devuelve el valor del atributo principal.
	 *
	 * @return principal
	 */
	public Boolean getPrincipal() {
		return principal;
	}

	/**
	 * Fija el valor del atributo principal.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}
}
