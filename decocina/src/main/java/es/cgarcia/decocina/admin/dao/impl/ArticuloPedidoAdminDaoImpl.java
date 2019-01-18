package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IArticuloPedidoAdminDao;
import es.cgarcia.decocina.web.model.ArticuloPedido;

/**
 * Implementación del Dao para los Pedidos Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("articuloPedidoAdminDao")
public class ArticuloPedidoAdminDaoImpl extends GenericAdminDaoIbatisImpl<ArticuloPedido> implements IArticuloPedidoAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param articuloPedido ArticuloPedido.
	 */
	@Autowired
    public ArticuloPedidoAdminDaoImpl(SqlMapClient sqlMapClient, ArticuloPedido articuloPedido) {
		
        super(sqlMapClient, articuloPedido);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ArticuloPedido> findByIdPedido(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("ArticuloPedido.admin.findByIdPedido", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAllByIdPedido(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		getSqlMapClientTemplate().delete("ArticuloPedido.admin.deleteAllByIdPedido", parametros);
	}
}
