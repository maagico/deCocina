package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IZonaImpuestoWebDao;
import es.cgarcia.decocina.web.model.ZonaImpuesto;

/**
 * Implementación del Dao para la ZonaFormaEnvio de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("zonaFormaEnvioWebDao")
public class ZonaImpuestoWebDaoImpl extends GenericWebDaoIbatisImpl<ZonaImpuesto> implements IZonaImpuestoWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param zonaImpuesto ZonaImpuesto.
	 */
	@Autowired
    public ZonaImpuestoWebDaoImpl(SqlMapClient sqlMapClient, ZonaImpuesto zonaImpuesto) {
		
        super(sqlMapClient, zonaImpuesto);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ZonaImpuesto> findByIdZona(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("ZonaImpuesto.web.findByIdZona", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double findIvaMasPrioridad(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return (Double)getSqlMapClientTemplate().queryForObject("ZonaImpuesto.web.findIvaMasPrioridad", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ZonaImpuesto findImpuestoMasPrioridad(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return (ZonaImpuesto)getSqlMapClientTemplate().queryForObject("ZonaImpuesto.web.findImpuestoMasPrioridad", parametros);
	}
}
