package es.cgarcia.decocina.admin.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.PedidoBuscador;
import es.cgarcia.decocina.admin.dao.IArticuloPedidoAdminDao;
import es.cgarcia.decocina.admin.dao.IPedidoAdminDao;
import es.cgarcia.decocina.admin.dao.IPedidoEstadoHistorialAdminDao;
import es.cgarcia.decocina.admin.service.IPedidoAdminService;
import es.cgarcia.decocina.web.model.Pedido;

/**
 * Contiene los métodos necesarios para trabajar con un Pedido de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("pedidoAdminService")
public class PedidoAdminServiceImpl extends GenericServiceImpl<Pedido, IPedidoAdminDao> implements IPedidoAdminService{

	/**
	 * Dao para los Estados de un Pedido.
	 */
	@Autowired
	private IPedidoEstadoHistorialAdminDao pedidoEstadoHistorialAdminDao;
	
	/**
	 * Dao para los Artículos de un Pedido.
	 */
	@Autowired
	private IArticuloPedidoAdminDao articuloPedidoAdminDao;
	
	/**
	 * Constructor.
	 * 
	 * @param pedidoAdminDao Dao para los Pedidos.
	 */
	@Autowired
	public PedidoAdminServiceImpl(IPedidoAdminDao pedidoAdminDao){
		
		super(pedidoAdminDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Pedido> findAllBusqueda(PedidoBuscador pedidoBuscador) {
		
		return dao.findAllBusqueda(pedidoBuscador);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long findNumeroPedidosMes(Calendar fecha) {
		
		return dao.findNumeroPedidosMes(fecha);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double findSumaImportePedidosMes(Calendar fecha) {
		
		return dao.findSumaImportePedidosMes(fecha);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(Pedido pedido){
		
		Long idPedido = pedido.getId();
		
		//Borramos los Estados.
		pedidoEstadoHistorialAdminDao.deleteAllByIdPedido(idPedido);
		
		//Borramos los Artículos relacionados del Pedido.
		articuloPedidoAdminDao.deleteAllByIdPedido(idPedido);
		
		dao.delete(pedido);
	}
}