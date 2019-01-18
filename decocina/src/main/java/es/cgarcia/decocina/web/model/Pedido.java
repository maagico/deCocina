package es.cgarcia.decocina.web.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

/**
 * Pedido del CLiente.
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Pedido extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 991978091202721028L;

	/**
	 * Artículos del Pedido.
	 */
	private List<ArticuloPedido> articulosPedido = new ArrayList<ArticuloPedido>();
	
	/**
	 * Cliente.
	 */
	private Cliente cliente;
	
	/**
	 * Nombre del Cliente.
	 */
	private String nombreCliente;
 	
	/**
	 * Nombre de la empresa.
	 */
	private String nombreEmpresa;
	
	/**
 	 *País.
 	 */
 	private String pais;
 	
 	/**
 	 * Zona.
 	 */
 	private String zona;
 	
 	/**
 	 * Población.
 	 */
 	private String poblacion;
 	
 	/**
 	 * Código postal.
 	 */
 	private String codigoPostal;
 	
 	/**
 	 * Teléfono.
 	 */
 	private String telefono;
 	
 	/**
 	 * Email.
 	 */
 	private String email;
 	
 	/**
 	 * Nombre de la Forma de Pago.
 	 */
 	private String nombreFormaPago;
 	
 	/**
 	 * Precio de la Forma de Pago.
 	 */
 	private String precioFormaPago;
 	
 	/**
 	 * Descripción de la Forma de Pago.
 	 */
 	private String descripcionFormaPago;
 	
 	/**
 	 * Comentario de la Forma de Pago.
 	 */
 	private String comentarioFormaPago;
 	
 	/**
 	 * Nombre de la Forma de Envío.
 	 */
 	private String nombreFormaEnvio;
 	
 	/**
 	 * Descripción de la Forma de Envío.
 	 */
 	private String descripcionFormaEnvio;
 	
 	/**
 	 * Precio de la Forma de Envío.
 	 */
 	private String precioFormaEnvio;
 	
 	/**
 	 * Fecha de creación.
 	 */
 	@DateTimeFormat(pattern = "dd/MM/yyyy")
 	private Calendar fechaCreacion;
 	
 	/**
 	 * Fecha de modificacón.
 	 */
 	private Calendar fechaModificacion;
	
 	/**
 	 * Iva total.
 	 */
 	private String ivaTotal;
	
 	/**
 	 * Precio total.
 	 */
 	private String precioTotal;
	
 	/**
 	 * Comentario.
 	 */
 	private String comentario;
 	
 	/**
 	 * Pedido estado historial.
 	 */
 	private PedidoEstadoHistorial estadoHistorial;
 	
 	/**
 	 * Precio total de la cesta.
 	 */
 	private String precioTotalCesta;
 	
 	/**
 	 * Peso de la Forma de Envío.
 	 */
 	private String pesoFormaEnvio;
 	
 	/**
 	 * Calle.
 	 */
 	private String calle;
 	
 	/**
 	 * Iva de la Zona.
 	 */
 	private String ivaDireccion;
 	
 	/**
 	 * Cupón.
 	 */
 	private String cupon;
 	
 	/**
 	 * Descuento.
 	 */
 	private String descuento;
 	
 	/**
 	 * Pocentaje de descuento.
 	 */
 	private String porcentajeDescuento;
 	
 	/**
 	 * Observaciones.
 	 */
 	private String observaciones;
 	
 	/**
	 * Indica si se debe notificar el cambio de Estado al cliente.
	 */
	private Boolean notificarEstado;
	
	/**
	 * Nuevo Estado.
	 */
	private Estado estadoAdmin;
	
	/**
	 * Comentario sobre el cambio de Estado.
	 */
	private String comentarioCambioEstado;
	
	/**
	 * Borrado lógico del Pedido.
	 */
	private Boolean borrado;
	
	/**
	 * Devuelve el valor del atributo articulosPedido.
	 *
	 * @return articulosPedido
	 */
	public List<ArticuloPedido> getArticulosPedido() {
		return articulosPedido;
	}

	/**
	 * Fija el valor del atributo articulosPedido.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setArticulosPedido(List<ArticuloPedido> articulosPedido) {
		this.articulosPedido = articulosPedido;
	}

	/**
	 * Devuelve el valor del atributo cliente.
	 *
	 * @return cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Fija el valor del atributo cliente.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Devuelve el valor del atributo nombreCliente.
	 *
	 * @return nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Fija el valor del atributo nombreCliente.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	/**
	 * Devuelve el valor del atributo nombreEmpresa.
	 *
	 * @return nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Fija el valor del atributo nombreEmpresa.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Devuelve el valor del atributo pais.
	 *
	 * @return pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Fija el valor del atributo pais.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Devuelve el valor del atributo zona.
	 *
	 * @return zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * Fija el valor del atributo zona.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * Devuelve el valor del atributo poblacion.
	 *
	 * @return poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * Fija el valor del atributo poblacion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * Devuelve el valor del atributo codigoPostal.
	 *
	 * @return codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Fija el valor del atributo codigoPostal.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Devuelve el valor del atributo telefono.
	 *
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Fija el valor del atributo telefono.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Devuelve el valor del atributo email.
	 *
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Fija el valor del atributo email.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve el valor del atributo nombreFormaPago.
	 *
	 * @return nombreFormaPago
	 */
	public String getNombreFormaPago() {
		return nombreFormaPago;
	}

	/**
	 * Fija el valor del atributo nombreFormaPago.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNombreFormaPago(String nombreFormaPago) {
		this.nombreFormaPago = nombreFormaPago;
	}

	/**
	 * Devuelve el valor del atributo precioFormaPago.
	 *
	 * @return precioFormaPago
	 */
	public String getPrecioFormaPago() {
		return precioFormaPago;
	}

	/**
	 * Fija el valor del atributo precioFormaPago.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrecioFormaPago(String precioFormaPago) {
		this.precioFormaPago = precioFormaPago;
	}

	/**
	 * Devuelve el valor del atributo descripcionFormaPago.
	 *
	 * @return descripcionFormaPago
	 */
	public String getDescripcionFormaPago() {
		return descripcionFormaPago;
	}

	/**
	 * Fija el valor del atributo descripcionFormaPago.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDescripcionFormaPago(String descripcionFormaPago) {
		this.descripcionFormaPago = descripcionFormaPago;
	}

	/**
	 * Devuelve el valor del atributo comentarioFormaPago.
	 *
	 * @return comentarioFormaPago
	 */
	public String getComentarioFormaPago() {
		return comentarioFormaPago;
	}

	/**
	 * Fija el valor del atributo comentarioFormaPago.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setComentarioFormaPago(String comentarioFormaPago) {
		this.comentarioFormaPago = comentarioFormaPago;
	}

	/**
	 * Devuelve el valor del atributo nombreFormaEnvio.
	 *
	 * @return nombreFormaEnvio
	 */
	public String getNombreFormaEnvio() {
		return nombreFormaEnvio;
	}

	/**
	 * Fija el valor del atributo nombreFormaEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNombreFormaEnvio(String nombreFormaEnvio) {
		this.nombreFormaEnvio = nombreFormaEnvio;
	}

	/**
	 * Devuelve el valor del atributo descripcionFormaEnvio.
	 *
	 * @return descripcionFormaEnvio
	 */
	public String getDescripcionFormaEnvio() {
		return descripcionFormaEnvio;
	}

	/**
	 * Fija el valor del atributo descripcionFormaEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDescripcionFormaEnvio(String descripcionFormaEnvio) {
		this.descripcionFormaEnvio = descripcionFormaEnvio;
	}

	/**
	 * Devuelve el valor del atributo precioFormaEnvio.
	 *
	 * @return precioFormaEnvio
	 */
	public String getPrecioFormaEnvio() {
		return precioFormaEnvio;
	}

	/**
	 * Fija el valor del atributo precioFormaEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrecioFormaEnvio(String precioFormaEnvio) {
		this.precioFormaEnvio = precioFormaEnvio;
	}

	/**
	 * Devuelve el valor del atributo fechaCreacion.
	 *
	 * @return fechaCreacion
	 */
	public Calendar getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * Fija el valor del atributo fechaCreacion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFechaCreacion(Calendar fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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
	 * Devuelve el valor del atributo ivaTotal.
	 *
	 * @return ivaTotal
	 */
	public String getIvaTotal() {
		return ivaTotal;
	}

	/**
	 * Fija el valor del atributo ivaTotal.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setIvaTotal(String ivaTotal) {
		this.ivaTotal = ivaTotal;
	}

	/**
	 * Devuelve el valor del atributo precioTotal.
	 *
	 * @return precioTotal
	 */
	public String getPrecioTotal() {
		return precioTotal;
	}

	/**
	 * Fija el valor del atributo precioTotal.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrecioTotal(String precioTotal) {
		this.precioTotal = precioTotal;
	}

	/**
	 * Devuelve el valor del atributo comentario.
	 *
	 * @return comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * Fija el valor del atributo comentario.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Devuelve el valor del atributo estadoHistorial.
	 *
	 * @return estadoHistorial
	 */
	public PedidoEstadoHistorial getEstadoHistorial() {
		return estadoHistorial;
	}

	/**
	 * Fija el valor del atributo estadoHistorial.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setEstadoHistorial(PedidoEstadoHistorial estadoHistorial) {
		this.estadoHistorial = estadoHistorial;
	}

	/**
	 * Devuelve el valor del atributo precioTotalCesta.
	 *
	 * @return precioTotalCesta
	 */
	public String getPrecioTotalCesta() {
		return precioTotalCesta;
	}

	/**
	 * Fija el valor del atributo precioTotalCesta.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPrecioTotalCesta(String precioTotalCesta) {
		this.precioTotalCesta = precioTotalCesta;
	}

	/**
	 * Devuelve el valor del atributo pesoFormaEnvio.
	 *
	 * @return pesoFormaEnvio
	 */
	public String getPesoFormaEnvio() {
		return pesoFormaEnvio;
	}

	/**
	 * Fija el valor del atributo pesoFormaEnvio.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPesoFormaEnvio(String pesoFormaEnvio) {
		this.pesoFormaEnvio = pesoFormaEnvio;
	}

	/**
	 * Devuelve el valor del atributo calle.
	 *
	 * @return calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Fija el valor del atributo calle.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Devuelve el valor del atributo ivaDireccion.
	 *
	 * @return ivaDireccion
	 */
	public String getIvaDireccion() {
		return ivaDireccion;
	}

	/**
	 * Fija el valor del atributo ivaDireccion.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setIvaDireccion(String ivaDireccion) {
		this.ivaDireccion = ivaDireccion;
	}

	/**
	 * Devuelve el valor del atributo cupon.
	 *
	 * @return cupon
	 */
	public String getCupon() {
		return cupon;
	}

	/**
	 * Fija el valor del atributo cupon.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCupon(String cupon) {
		this.cupon = cupon;
	}

	/**
	 * Devuelve el valor del atributo descuento.
	 *
	 * @return descuento
	 */
	public String getDescuento() {
		return descuento;
	}

	/**
	 * Fija el valor del atributo descuento.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	/**
	 * Devuelve el valor del atributo porcentajeDescuento.
	 *
	 * @return porcentajeDescuento
	 */
	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	/**
	 * Fija el valor del atributo porcentajeDescuento.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	/**
	 * Devuelve el valor del atributo observaciones.
	 *
	 * @return observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Fija el valor del atributo observaciones.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Devuelve el valor del atributo notificarEstado.
	 *
	 * @return notificarEstado
	 */
	public Boolean getNotificarEstado() {
		return notificarEstado;
	}

	/**
	 * Fija el valor del atributo notificarEstado.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setNotificarEstado(Boolean notificarEstado) {
		this.notificarEstado = notificarEstado;
	}

	/**
	 * Devuelve el valor del atributo estadoAdmin.
	 *
	 * @return estadoAdmin
	 */
	public Estado getEstadoAdmin() {
		return estadoAdmin;
	}

	/**
	 * Fija el valor del atributo estadoAdmin.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setEstadoAdmin(Estado estadoAdmin) {
		this.estadoAdmin = estadoAdmin;
	}

	/**
	 * Devuelve el valor del atributo comentarioCambioEstado.
	 *
	 * @return comentarioCambioEstado
	 */
	public String getComentarioCambioEstado() {
		return comentarioCambioEstado;
	}

	/**
	 * Fija el valor del atributo comentarioCambioEstado.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setComentarioCambioEstado(String comentarioCambioEstado) {
		this.comentarioCambioEstado = comentarioCambioEstado;
	}

	/**
	 * Devuelve el valor del atributo borrado.
	 *
	 * @return borrado
	 */
	public Boolean getBorrado() {
		return borrado;
	}

	/**
	 * Fija el valor del atributo borrado.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}
}
