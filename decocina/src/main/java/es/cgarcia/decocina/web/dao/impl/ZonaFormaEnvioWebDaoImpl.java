package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IZonaFormaEnvioWebDao;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;

/**
 * Implementación del Dao para la ZonaImpuesto de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("zonaImpuestoWebDao")
public class ZonaFormaEnvioWebDaoImpl extends GenericWebDaoIbatisImpl<ZonaFormaEnvio> implements IZonaFormaEnvioWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param zonaFormaEnvio ZonaFormaEnvio.
	 */
	@Autowired
    public ZonaFormaEnvioWebDaoImpl(SqlMapClient sqlMapClient, ZonaFormaEnvio zonaFormaEnvio) {
		
        super(sqlMapClient, zonaFormaEnvio);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ZonaFormaEnvio> findByIdZona(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("ZonaFormaEnvio.web.findByIdZona", parametros);
	}
}
