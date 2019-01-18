package es.cgarcia.decocina.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.dao.IPedidoEstadoHistorialWebDao;
import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;
import es.cgarcia.decocina.web.service.IPedidoEstadoHistorialWebService;

/**
 * Contiene los métodos necesarios para trabajar con un PedidoEstadoHistorial de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("pedidoEstadoHistorialWebService")
public class PedidoEstadoHistorialWebServiceImpl extends GenericServiceImpl<PedidoEstadoHistorial, IPedidoEstadoHistorialWebDao> implements IPedidoEstadoHistorialWebService{

	/**
	 * Constructor.
	 * 
	 * @param pedidoEstadoHistorialWebDao Dao para el PedidoEstadoHistorial.
	 */
	@Autowired
	public PedidoEstadoHistorialWebServiceImpl(IPedidoEstadoHistorialWebDao pedidoEstadoHistorialWebDao){
		
		super(pedidoEstadoHistorialWebDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PedidoEstadoHistorial> findAllByIdPedido(Long id) {
		
		return dao.findAllByIdPedido(id);
	}
}