package es.cgarcia.decocina.admin.form;

import javax.validation.Valid;

import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.ClienteBuscador;
import es.cgarcia.decocina.web.model.Cliente;

/**
 * Clase que encapsula al Cliente y que tiene los atributos necesarios 
 * para trabajar con formularios y sus búsquedas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ClienteForm extends FormBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 443884969390037565L;

	/**
	 * Buscador de Clientes.
	 */
	@ClaseForm
	private ClienteBuscador clienteBuscador;
	
	/**
	 * Cliente.
	 */
	@Valid
	@ClaseForm
	private Cliente cliente = new Cliente();
	
	/**
	 * Constructor.
	 */
	public ClienteForm(){
	}
	
	/**
	 * Constructor.
	 * 
	 * @param cliente Cliente.
	 */
	public ClienteForm(Cliente cliente){
		this.cliente = cliente;
	}

	/**
	 * Devuelve el valor del atributo clienteBuscador.
	 *
	 * @return clienteBuscador
	 */
	public ClienteBuscador getClienteBuscador() {
		return clienteBuscador;
	}

	/**
	 * Fija el valor del atributo clienteBuscador.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setClienteBuscador(ClienteBuscador clienteBuscador) {
		this.clienteBuscador = clienteBuscador;
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
}
