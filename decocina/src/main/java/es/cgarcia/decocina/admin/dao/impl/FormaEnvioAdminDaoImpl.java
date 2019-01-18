package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.buscador.FormaEnvioBuscador;
import es.cgarcia.decocina.admin.dao.IFormaEnvioAdminDao;
import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.model.FormaEnvio;

/**
 * Implementación del Dao para las Formas de Envío de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("formaEnvioAdminDao")
public class FormaEnvioAdminDaoImpl extends GenericAdminDaoIbatisImpl<FormaEnvio> implements IFormaEnvioAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param fabricante Fabricante.
	 */
	@Autowired
    public FormaEnvioAdminDaoImpl(SqlMapClient sqlMapClient, FormaEnvio formaEnvio) {
		
        super(sqlMapClient, formaEnvio);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaEnvio> findAllBusqueda(FormaEnvioBuscador formaEnvioBuscador) {
		
		String nombre = formaEnvioBuscador.getNombre();
		
		StringBuffer sbNombre = new StringBuffer();
		
		sbNombre.append(' ');
		sbNombre.append(nombre);
		sbNombre.append(' ');
		
		String nombreEspacios = sbNombre.toString();
		String nombrePorcentaje = nombreEspacios.replaceAll(" ", "%");
		
		Paginacion paginacion = formaEnvioBuscador.getPaginacion();
		
		Long start = paginacion.getStart();
		Long limit = paginacion.getLimit();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nombre", nombrePorcentaje);
		parametros.put("start", start);
		parametros.put("limit", limit);
		
		List<FormaEnvio> formasEnvio = getSqlMapClientTemplate().queryForList("FormaEnvio.admin.findAllBusqueda", parametros);
		
		parametros.put("isCount", "isCount");
		
		Long count = (Long)getSqlMapClientTemplate().queryForObject("FormaEnvio.admin.countFindAllBusqueda", parametros);
		
		paginacion.setCount(count);
		
		return formasEnvio;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaEnvio> findAllByIdZona(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("FormaEnvio.admin.findAllByIdZona", parametros);
	}
}
