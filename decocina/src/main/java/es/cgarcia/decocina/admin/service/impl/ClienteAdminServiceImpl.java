package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.ClienteBuscador;
import es.cgarcia.decocina.admin.dao.ICestaAdminDao;
import es.cgarcia.decocina.admin.dao.IClienteAdminDao;
import es.cgarcia.decocina.admin.dao.IDireccionAdminDao;
import es.cgarcia.decocina.admin.dao.IPedidoAdminDao;
import es.cgarcia.decocina.admin.service.IClienteAdminService;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Direccion;
import es.cgarcia.decocina.web.model.Pedido;

/**
 * Contiene los métodos necesarios para trabajar con un CLiente de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("clienteAdminService")
public class ClienteAdminServiceImpl extends GenericServiceImpl<Cliente, IClienteAdminDao> implements IClienteAdminService{

	/**
	 * Dao para los Pedidos del Cliente.
	 */
	@Autowired
	private IPedidoAdminDao pedidoAdminDao;
	
	/**
	 * Dao para las Direcciones del Cliente.
	 */
	@Autowired
	private IDireccionAdminDao direccionAdminDao;
	
	/**
	 * Dao para la Cesta del Cliente.
	 */
	@Autowired
	private ICestaAdminDao cestaAdminDao;
	
	/**
	 * Constructor.
	 * 
	 * @param ClienteAdminDao Dao para los Clientes.
	 */
	@Autowired
	public ClienteAdminServiceImpl(IClienteAdminDao clienteAdminDao){
		
		super(clienteAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Cliente> findAllBusqueda(ClienteBuscador clienteBuscador) {
		
		return dao.findAllBusqueda(clienteBuscador);
	}
	
	/**
	 * Borra el Cliente.
	 * 
	 * @param cliente Cliente a borrar.
	 */
	public void delete(Cliente cliente){
		
		Long idCliente = cliente.getId();
		
		//Recuperamos todos los Pedidos.
		List<Pedido> pedidos = pedidoAdminDao.findAllByIdCliente(idCliente);
		
		//Por cada pedido borramos sus datos personales(anonimizamos) y añadimos el Cliente(un cliente dummy) con id 1 al pedido.
		for (Pedido pedido : pedidos) {
			
			Long idPedido = pedido.getId();
			
			pedidoAdminDao.borrarDatosPersonales(idPedido);
			
		}
		
		//Borramos la Cesta.
		cestaAdminDao.deleteByIdCliente(idCliente);
		
		//Borramos la Dirección del Cliente.
		Direccion direccion = cliente.getDireccion();
		
		direccionAdminDao.delete(direccion);
		
		//Borramos el cliente.
		dao.delete(cliente);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente update(Cliente cliente){
		
		dao.update(cliente);
		
		Direccion direccion = cliente.getDireccion();
		
		direccionAdminDao.update(direccion);
		
		return cliente;
	}
}