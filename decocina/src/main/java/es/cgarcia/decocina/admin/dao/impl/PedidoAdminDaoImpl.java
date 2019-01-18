package es.cgarcia.decocina.admin.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.buscador.PedidoBuscador;
import es.cgarcia.decocina.admin.dao.IPedidoAdminDao;
import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.model.Pedido;

/**
 * Implementación del Dao para los Pedidos Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("pedidoAdminDao")
public class PedidoAdminDaoImpl extends GenericAdminDaoIbatisImpl<Pedido> implements IPedidoAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param pedido Pedido.
	 */
	@Autowired
    public PedidoAdminDaoImpl(SqlMapClient sqlMapClient, Pedido pedido) {
		
        super(sqlMapClient, pedido);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findAllBusqueda(PedidoBuscador pedidoBuscador) {
		
		String strIdPedido = pedidoBuscador.getIdCliente(); 
		Long idPedido = null;
		
		if(strIdPedido != null && !strIdPedido.equals("")){
			
			idPedido = Long.valueOf(strIdPedido);
		}
		
		String nombre = pedidoBuscador.getNombre();
		
		StringBuffer sbNombre = new StringBuffer();
		
		sbNombre.append(' ');
		sbNombre.append(nombre);
		sbNombre.append(' ');
		
		String nombreEspacios = sbNombre.toString();
		String nombrePorcentaje = nombreEspacios.replaceAll(" ", "%");
		
		String apellidos = pedidoBuscador.getApellidos();
		
		StringBuffer sbApellidos = new StringBuffer();
		
		sbApellidos.append(' ');
		sbApellidos.append(apellidos);
		sbApellidos.append(' ');
		
		String apellidosEspacios = sbApellidos.toString();
		String apellidosPorcentaje = apellidosEspacios.replaceAll(" ", "%");
		
		String telefono = pedidoBuscador.getTelefono();
		
		Paginacion paginacion = pedidoBuscador.getPaginacion();
		
		Long start = paginacion.getStart();
		Long limit = paginacion.getLimit();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		if(strIdPedido != null){
			
			parametros.put("idPedido", idPedido);
		}
		parametros.put("nombre", nombrePorcentaje);
		parametros.put("apellidos", apellidosPorcentaje);
		parametros.put("telefono", telefono);
		
		parametros.put("start", start);
		parametros.put("limit", limit);
		
		List<Pedido> pedidos = getSqlMapClientTemplate().queryForList("Pedido.admin.findAllBusqueda", parametros);
		
		parametros.put("isCount", "isCount");
		
		Long count = (Long)getSqlMapClientTemplate().queryForObject("Pedido.admin.countFindAllBusqueda", parametros);
		
		paginacion.setCount(count);
		
		return pedidos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long findNumeroPedidosMes(Calendar fecha) {
		
		Integer mes = fecha.get(Calendar.MONTH);
		Integer anyo = fecha.get(Calendar.YEAR);
		
		mes += 1;
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("mes", mes);
		parametros.put("anyo", anyo);
		
		return (Long)getSqlMapClientTemplate().queryForObject("Pedido.admin.findNumeroPedidosMes", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double findSumaImportePedidosMes(Calendar fecha) {
		
		Integer mes = fecha.get(Calendar.MONTH);
		Integer anyo = fecha.get(Calendar.YEAR);
		
		mes += 1;
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("mes", mes);
		parametros.put("anyo", anyo);
		
		return (Double)getSqlMapClientTemplate().queryForObject("Pedido.admin.findSumaImportePedidosMes", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findAllByIdCliente(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("Pedido.admin.findAllByIdCliente", parametros);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertEstado(Pedido pedido) {
		
		getSqlMapClientTemplate().insert("Pedido.admin.insertEstado", pedido);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void borrarDatosPersonales(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		getSqlMapClientTemplate().update("Pedido.admin.borrarDatosPersonales", parametros);
	}
}
