package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IArticuloPedidoWebDao;
import es.cgarcia.decocina.web.model.ArticuloPedido;

/**
 * Implementación del Dao para el ArticuloPedido Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("articuloPedidoWebDao")
public class ArticuloPedidoWebDaoImpl extends GenericWebDaoIbatisImpl<ArticuloPedido> implements IArticuloPedidoWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param articuloPedido ArticuloPedido.
	 */
	@Autowired
    public ArticuloPedidoWebDaoImpl(SqlMapClient sqlMapClient, ArticuloPedido articuloPedido) {
		
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
		
		return getSqlMapClientTemplate().queryForList("ArticuloPedido.web.findByIdPedido", parametros);
	}
}
