package es.cgarcia.decocina.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.dao.IArticuloPedidoWebDao;
import es.cgarcia.decocina.web.model.ArticuloPedido;
import es.cgarcia.decocina.web.service.IArticuloPedidoWebService;

/**
 * Contiene los métodos necesarios para trabajar con un ArticuloPedido de la parte Web. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("articuloPedidoWebService")
public class ArticuloPedidoWebServiceImpl extends GenericServiceImpl<ArticuloPedido, IArticuloPedidoWebDao> implements IArticuloPedidoWebService{

	/**
	 * Constructor.
	 * 
	 * @param articuloPedidoWebDao Dao para el ArticuloPedido.
	 */
	@Autowired
	public ArticuloPedidoWebServiceImpl(IArticuloPedidoWebDao articuloPedidoWebDao){
		
		super(articuloPedidoWebDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticuloPedido> findByIdPedido(Long id) {
		return dao.findByIdPedido(id);
	}
}