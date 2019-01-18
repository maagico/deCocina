package es.cgarcia.decocina.web.model;

import java.util.Calendar;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * Cupones de descuento.
 * 
 * @author Miguel Ángel Álvarez García
 */
@Component
public class Cupon extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6401312334667742513L;

	/**
	 * Nombre del Cupón.
	 */
	@NotEmpty
	@Size(max=255)
	private String nombre;
	
	/**
	 * Descuento.
	 */
	@Digits(integer=6, fraction=2)
	@NotNull
	private Double descuento;
	
	/**
	 * Válido desde.
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar fechaDesde;
	
	/**
	 * Válido hasta.
	 */
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Calendar fechaHasta;
	
	/**
	 * Precio mínimo del pedido para aceptar el Cupón.
	 */
	@Digits(integer=6, fraction=2)
	private Double precioMinimo;
	
	/**
	 * Numero de usos de Cupón.
	 */
	private Integer numeroUsos;
	
	/**
	 * Tipo del importe. 0 Porcentaje. 1 Importe fijo.
	 */
	@NotNull
	private Integer tipoImporte;
	
	/**
	 * Descripción del Cupón.
	 */
	private String descripcion;
	
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
	 * Devuelve el valor del atributo descuento.
	 *
	 * @return descuento
	 */
	public Double getDescuento() {
		return descuento;
	}

	/**
	 * Fija el valor del atributo descuento.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	/**
	 * Devuelve el valor del atributo fechaDesde.
	 *
	 * @return fechaDesde
	 */
	public Calendar getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Fija el valor del atributo fechaDesde.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFechaDesde(Calendar fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Devuelve el valor del atributo fechaHasta.
	 *
	 * @return fechaHasta
	 */
	public Calendar getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Fija el valor del atributo fechaHasta.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFechaHasta(Calendar fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Devuelve el valor del atributo precioMinimo.
	 *
	 * @return precioMinimo
	 */
	public Double getPrecioMinimo() {
		return precioMinimo;
	}

	/**
	 * Fija el valor del atributo precioMinimo.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrecioMinimo(Double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	/**
	 * Devuelve el valor del atributo numeroUsos.
	 *
	 * @return numeroUsos
	 */
	public Integer getNumeroUsos() {
		return numeroUsos;
	}

	/**
	 * Fija el valor del atributo numeroUsos.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNumeroUsos(Integer numeroUsos) {
		this.numeroUsos = numeroUsos;
	}

	/**
	 * Devuelve el valor del atributo tipoImporte.
	 *
	 * @return tipoImporte
	 */
	public Integer getTipoImporte() {
		return tipoImporte;
	}

	/**
	 * Fija el valor del atributo tipoImporte.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setTipoImporte(Integer tipoImporte) {
		this.tipoImporte = tipoImporte;
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
}
