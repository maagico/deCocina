/**
 * 
 */
package es.cgarcia.decocina.web.wrapper;

import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Pedido;

/**
 * Wrapper del Cliente.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ClienteWrapper extends WrapperBase{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -3232808734298490480L;
	
	/**
	 * Cliente.
	 */
	private Cliente cliente;
	
	/**
	 * Indica que el Cliente es un invitado(no está logueado).
	 */
	private Boolean esInvitado = true;
	
	/**
	 * Wrapper del pedido.
	 */
	private PedidoWrapper pedidoWrapper;

	/**
	 * Constructor.
	 * @param cliente Cliente.
	 * @param zonaAdminService Servicio para las Zonas.
	 */
	
	public ClienteWrapper(Cliente cliente, Pedido pedido){
		
		this.setCliente(cliente);
		this.pedidoWrapper = new PedidoWrapper(pedido, cliente);
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
	 * Devuelve el valor del atributo esInvitado.
	 *
	 * @return esInvitado
	 */
	public Boolean getEsInvitado() {
		return esInvitado;
	}

	/**
	 * Fija el valor del atributo esInvitado.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setEsInvitado(Boolean esInvitado) {
		this.esInvitado = esInvitado;
	}
	
	/**
	 * Devuelve el valor del atributo pedidoWrapper.
	 *
	 * @return pedidoWrapper
	 */
	public PedidoWrapper getPedidoWrapper() {
		return pedidoWrapper;
	}

	/**
	 * Fija el valor del atributo pedidoWrapper.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setPedidoWrapper(PedidoWrapper pedidoWrapper) {
		this.pedidoWrapper = pedidoWrapper;
	}
}
