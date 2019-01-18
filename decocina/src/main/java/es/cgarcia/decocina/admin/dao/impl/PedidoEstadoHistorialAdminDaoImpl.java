package es.cgarcia.decocina.admin.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IPedidoEstadoHistorialAdminDao;
import es.cgarcia.decocina.web.model.Estado;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;

/**
 * Implementación del Dao para el PedidoEstadoHistorial Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("pedidoEstadoHistorialAdminDao")
public class PedidoEstadoHistorialAdminDaoImpl extends GenericAdminDaoIbatisImpl<PedidoEstadoHistorial> implements IPedidoEstadoHistorialAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param pedidoEstadoHistorial PedidoEstadoHistorial.
	 */
	@Autowired
    public PedidoEstadoHistorialAdminDaoImpl(SqlMapClient sqlMapClient, PedidoEstadoHistorial pedidoEstadoHistorial) {
		
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
		
		return getSqlMapClientTemplate().queryForList("PedidoEstadoHistorial.admin.findAllByIdPedido", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Pedido pedido) {
	
		Estado estado = pedido.getEstadoAdmin();
		Boolean notificarEstado = pedido.getNotificarEstado();
		String comentarioCambioEstado = pedido.getComentarioCambioEstado();
		
		PedidoEstadoHistorial pedidoEstadoHistorial = new PedidoEstadoHistorial();
		
		pedidoEstadoHistorial.setPedido(pedido);
		pedidoEstadoHistorial.setEstado(estado);
		pedidoEstadoHistorial.setClienteNotificado(notificarEstado);
		pedidoEstadoHistorial.setComentario(comentarioCambioEstado);
		pedidoEstadoHistorial.setFecha(Calendar.getInstance());
		
		insert(pedidoEstadoHistorial);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAllByIdPedido(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		getSqlMapClientTemplate().delete("PedidoEstadoHistorial.admin.deleteAllByIdPedido", parametros);
	}
}
