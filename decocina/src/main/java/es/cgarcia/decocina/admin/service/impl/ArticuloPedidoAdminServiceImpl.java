package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.dao.IArticuloPedidoAdminDao;
import es.cgarcia.decocina.admin.service.IArticuloPedidoAdminService;
import es.cgarcia.decocina.web.model.ArticuloPedido;

/**
 * Contiene los métodos necesarios para trabajar con un ArticuloPedido de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("articuloPedidoAdminService")
public class ArticuloPedidoAdminServiceImpl extends GenericServiceImpl<ArticuloPedido, IArticuloPedidoAdminDao> implements IArticuloPedidoAdminService{

	/**
	 * Constructor.
	 * 
	 * @param articuloPedidoAdminDao Dao para los ArticuloPedido.
	 */
	@Autowired
	public ArticuloPedidoAdminServiceImpl(IArticuloPedidoAdminDao articuloPedidoAdminDao){
		
		super(articuloPedidoAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ArticuloPedido> findByIdPedido(Long id) {
		return dao.findByIdPedido(id);
	}
}