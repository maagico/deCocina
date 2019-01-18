package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IPedidoEstadoHistorialWebDao;
import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;

/**
 * Implementación del Dao para el PedidoEstadoHistorial Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("pedidoEstadoHistorialWebDao")
public class PedidoEstadoHistorialWebDaoImpl extends GenericWebDaoIbatisImpl<PedidoEstadoHistorial> implements IPedidoEstadoHistorialWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param pedidoEstadoHistorial PedidoEstadoHistorial.
	 */
	@Autowired
    public PedidoEstadoHistorialWebDaoImpl(SqlMapClient sqlMapClient, PedidoEstadoHistorial pedidoEstadoHistorial) {
		
        super(sqlMapClient, pedidoEstadoHistorial);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoEstadoHistorial> findAllByIdPedido(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("PedidoEstadoHistorial.web.findAllByIdPedido", parametros);
	}
}
