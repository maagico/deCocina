package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.buscador.ClienteBuscador;
import es.cgarcia.decocina.admin.dao.IClienteAdminDao;
import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.model.Cliente;

/**
 * Implementación del Dao para el Cliente de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("clienteAdminDao")
public class ClienteAdminDaoImpl extends GenericAdminDaoIbatisImpl<Cliente> implements IClienteAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param cliente Cliente.
	 */
	@Autowired
    public ClienteAdminDaoImpl(SqlMapClient sqlMapClient, Cliente cliente) {
		
        super(sqlMapClient, cliente);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAllBusqueda(ClienteBuscador clienteBuscador) {
		
		String nombre = clienteBuscador.getNombre();
		
		StringBuffer sbNombre = new StringBuffer();
		
		sbNombre.append(' ');
		sbNombre.append(nombre);
		sbNombre.append(' ');
		
		String nombreEspacios = sbNombre.toString();
		String nombrePorcentaje = nombreEspacios.replaceAll(" ", "%");
		
		
		String apellidos = clienteBuscador.getApellidos();
		
		StringBuffer sbApellidos = new StringBuffer();
		
		sbApellidos.append(' ');
		sbApellidos.append(apellidos);
		sbApellidos.append(' ');
		
		String apellidosEspacios = sbApellidos.toString();
		String apellidosPorcentaje = apellidosEspacios.replaceAll(" ", "%");
		
		String telefono = clienteBuscador.getTelefono();
		
		Paginacion paginacion = clienteBuscador.getPaginacion();
		
		Long start = paginacion.getStart();
		Long limit = paginacion.getLimit();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nombre", nombrePorcentaje);
		parametros.put("apellidos", apellidosPorcentaje);
		parametros.put("telefono", telefono);
		
		parametros.put("start", start);
		parametros.put("limit", limit);
		
		List<Cliente> clientes = getSqlMapClientTemplate().queryForList("Cliente.admin.findAllBusqueda", parametros);
		
		parametros.put("isCount", "isCount");
		
		Long count = (Long)getSqlMapClientTemplate().queryForObject("Cliente.admin.countFindAllBusqueda", parametros);
		
		paginacion.setCount(count);
		
		return clientes;
	}
}
