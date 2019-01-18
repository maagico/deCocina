package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IPedidoWebDao;
import es.cgarcia.decocina.web.model.Pedido;

/**
 * Implementación del Dao para los Pedidos Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("pedidoWebDao")
public class PedidoWebDaoImpl extends GenericWebDaoIbatisImpl<Pedido> implements IPedidoWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param pedido Pedido.
	 */
	@Autowired
    public PedidoWebDaoImpl(SqlMapClient sqlMapClient, Pedido pedido) {
		
        super(sqlMapClient, pedido);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEstadoHistorial(Long idPedido, Long idPedidoEstadoHistorial) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idPedido", idPedido);
		parametros.put("idPedidoEstadoHistorial", idPedidoEstadoHistorial);
		
		getSqlMapClientTemplate().update("Pedido.web.updateEstadoHistorial", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findAllByIdCliente(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("Pedido.web.findAllByIdCliente", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pedido findByIdClienteAndIdPedido(Long idCliente, Long idPedido) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCliente", idCliente);
		parametros.put("idPedido", idPedido);
		
		return (Pedido)getSqlMapClientTemplate().queryForObject("Pedido.web.findByIdClienteAndIdPedido", parametros);
	}
}
