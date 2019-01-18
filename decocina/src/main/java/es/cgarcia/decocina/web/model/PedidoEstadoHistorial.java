package es.cgarcia.decocina.web.model;

import java.util.Calendar;

import org.springframework.stereotype.Component;


/**
 * Estado del Pedido.
 *  
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class PedidoEstadoHistorial extends ModelBase
{
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -3234460577284415120L;

	/**
	 * Pedido.
	 */
	private Pedido pedido;
	
	/**
	 * Estado.
	 */
	private Estado estado;
	
	/**
	 * Fecha.
	 */
	private Calendar fecha;
	
	/**
	 * Indica si el cliente ha sido notificado.
	 */
	private Boolean clienteNotificado;
	
	/**
	 * Comentario del cambio de estado.
	 */
	private String comentario;

	/**
	 * Devuelve el valor del atributo pedido.
	 *
	 * @return pedido
	 */
	public Pedido getPedido() {
		return pedido;
	}

	/**
	 * Fija el valor del atributo pedido.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	/**
	 * Devuelve el valor del atributo estado.
	 *
	 * @return estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Fija el valor del atributo estado.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
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
	 * Devuelve el valor del atributo clienteNotificado.
	 *
	 * @return clienteNotificado
	 */
	public Boolean getClienteNotificado() {
		return clienteNotificado;
	}

	/**
	 * Fija el valor del atributo clienteNotificado.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setClienteNotificado(Boolean clienteNotificado) {
		this.clienteNotificado = clienteNotificado;
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
}
