package es.cgarcia.decocina.web.model;

import java.util.Calendar;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * Artículo de la tienda.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */ 
@Component
public class Articulo extends ModelBase 
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1744990303634355743L;
	
	/**
	 * Nombre del artículo.
	 */
	@NotEmpty
	@Size(max=150)
	private String nombre;
	
	/**
	 * Meta descripción del artículo.
	 */
	@NotEmpty
	@Size(max=256)
	private String metaDescripcion;

	/**
	 * Keywords del artículo.
	 */
	@NotEmpty
	@Size(max=256)
	private String metaKeywords;
	
	/**
	 * URL amigable del articulo.
	 */
	private String urlAmigable;
	
	/**
	 * Descripcion larga del artículo.
	 */
	private String descripcion;
	
	/**
	 * Fabricante del artículo.
	 */
	private Fabricante fabricante;
	
	/**
	 * Categoría del artículo.
	 */
	private Categoria categoria;
	
	/**
	 * Precio del artículo.
	 */
	@Digits(integer=6, fraction=2)
	@NotNull
	private Double precio;
	
	/**
	 * Oferta del precio del artículo.
	 */
	@Digits(integer=6, fraction=2)
	private Double oferta;
	
	/**
	 * Peso del artículo.
	 */
	@Digits(integer=6, fraction=2)
	@NotNull
	private Double peso;
	
	/**
	 * Impuesto de artículo.
	 */
	private Impuesto impuesto;
	
	/**
	 * Descripción corta del artículo.
	 */
	@NotEmpty
	@Size(max=1024)
	private String descripcionCorta;
	
	/**
	 * Cantidad de artículos.
	 */
	@NotNull
	private Integer cantidad;

	/**
	 * Estado del artículo.
	 */	
	private Boolean activo;
	
	/**
	 * Orden del artículo 
	 */
	private Integer orden;
	
	/**
	 * Indica que el artículo es destacado.
	 */
	private Boolean destacado;
	
	/**
	 * Visitas del artículo.
	 */
	private Long visitas;
	
	/**
	 * Número de Artículos vendidos.
	 */
	private Long ventas;
	
	/**
	 * Fecha de creacion del artículo.
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar fecha;
	
	/**
	 * Fecha de modificación del artículo.
	 */
	private Calendar fechaModificacion;
	
	/**
	 * Id de twitter.
	 */
	private Long idTwitter; 
	
	/**
	 * Imagen grande.
	 */
	private Imagen imagenG;
	
	/**
	 * Imagen pequeña.
	 */
	private Imagen imagenP;
	
	/**
	 * Imagen thumbnail.
	 */
	private Imagen imagenT;
	
	/**
	 * Constructor.
	 */
	public Articulo(){
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
	 * Devuelve el valor del atributo metaDescripcion.
	 *
	 * @return metaDescripcion
	 */
	public String getMetaDescripcion() {
		return metaDescripcion;
	}

	/**
	 * Fija el valor del atributo metaDescripcion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setMetaDescripcion(String metaDescripcion) {
		this.metaDescripcion = metaDescripcion;
	}

	/**
	 * Devuelve el valor del atributo metaKeywords.
	 *
	 * @return metaKeywords
	 */
	public String getMetaKeywords() {
		return metaKeywords;
	}

	/**
	 * Fija el valor del atributo metaKeywords.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	/**
	 * Devuelve el valor del atributo urlAmigable.
	 *
	 * @return urlAmigable
	 */
	public String getUrlAmigable() {
		return urlAmigable;
	}

	/**
	 * Fija el valor del atributo urlAmigable.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setUrlAmigable(String urlAmigable) {
		this.urlAmigable = urlAmigable;
	}

	/**
	 * Devuelve el valor del atributo descripcion.
	 *
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Fija el valor del atributo descripcion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	/**
	 * Devuelve el valor del atributo categoria.
	 *
	 * @return categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Fija el valor del atributo categoria.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * Devuelve el valor del atributo precio.
	 *
	 * @return precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * Fija el valor del atributo precio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve el valor del atributo oferta.
	 *
	 * @return oferta
	 */
	public Double getOferta() {
		return oferta;
	}

	/**
	 * Fija el valor del atributo oferta.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setOferta(Double oferta) {
		this.oferta = oferta;
	}

	/**
	 * Devuelve el valor del atributo peso.
	 *
	 * @return peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * Fija el valor del atributo peso.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	/**
	 * Devuelve el valor del atributo impuesto.
	 *
	 * @return impuesto
	 */
	public Impuesto getImpuesto() {
		return impuesto;
	}

	/**
	 * Fija el valor del atributo impuesto.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}

	/**
	 * Devuelve el valor del atributo descripcionCorta.
	 *
	 * @return descripcionCorta
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * Fija el valor del atributo descripcionCorta.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	/**
	 * Devuelve el valor del atributo cantidad.
	 *
	 * @return cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * Fija el valor del atributo cantidad.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Devuelve el valor del atributo activo.
	 *
	 * @return activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Fija el valor del atributo activo.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Devuelve el valor del atributo orden.
	 *
	 * @return orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * Fija el valor del atributo orden.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * Devuelve el valor del atributo destacado.
	 *
	 * @return destacado
	 */
	public Boolean getDestacado() {
		return destacado;
	}

	/**
	 * Fija el valor del atributo destacado.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDestacado(Boolean destacado) {
		this.destacado = destacado;
	}

	/**
	 * Devuelve el valor del atributo visitas.
	 *
	 * @return visitas
	 */
	public Long getVisitas() {
		return visitas;
	}

	/**
	 * Fija el valor del atributo visitas.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setVisitas(Long visitas) {
		this.visitas = visitas;
	}
	
	/**
	 * Devuelve el valor del atributo ventas.
	 *
	 * @return ventas
	 */
	public Long getVentas() {
		return ventas;
	}

	/**
	 * Fija el valor del atributo ventas.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setVentas(Long ventas) {
		this.ventas = ventas;
	}

	/**
	 * Devuelve el valor del atributo fecha.
	 *
	 * @return fecha
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/**
	 * Fija el valor del atributo fecha.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve el valor del atributo fechaModificacion.
	 *
	 * @return fechaModificacion
	 */
	public Calendar getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Fija el valor del atributo fechaModificacion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFechaModificacion(Calendar fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Devuelve el valor del atributo idTwitter.
	 *
	 * @return idTwitter
	 */
	public Long getIdTwitter() {
		return idTwitter;
	}

	/**
	 * Fija el valor del atributo idTwitter.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setIdTwitter(Long idTwitter) {
		this.idTwitter = idTwitter;
	}
	
	/**
	 * Devuelve el valor del atributo imagenG.
	 *
	 * @return imagenG
	 */
	public Imagen getImagenG() {
		return imagenG;
	}

	/**
	 * Fija el valor del atributo imagenG.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setImagenG(Imagen imagenG) {
		this.imagenG = imagenG;
	}

	/**
	 * Devuelve el valor del atributo imagenP.
	 *
	 * @return imagenP
	 */
	public Imagen getImagenP() {
		return imagenP;
	}

	/**
	 * Fija el valor del atributo imagenP.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setImagenP(Imagen imagenP) {
		this.imagenP = imagenP;
	}

	/**
	 * Devuelve el valor del atributo imagenT.
	 *
	 * @return imagenT
	 */
	public Imagen getImagenT() {
		return imagenT;
	}

	/**
	 * Fija el valor del atributo imagenT.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setImagenT(Imagen imagenT) {
		this.imagenT = imagenT;
	}
}
