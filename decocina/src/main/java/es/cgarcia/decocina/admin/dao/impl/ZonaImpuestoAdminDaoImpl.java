package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IZonaImpuestoAdminDao;
import es.cgarcia.decocina.web.model.ZonaImpuesto;

/**
 * Implementación del Dao para la ZonaFormaEnvio de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("zonaFormaEnvioAdminDao")
public class ZonaImpuestoAdminDaoImpl extends GenericAdminDaoIbatisImpl<ZonaImpuesto> implements IZonaImpuestoAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param zonaImpuesto ZonaImpuesto.
	 */
	@Autowired
    public ZonaImpuestoAdminDaoImpl(SqlMapClient sqlMapClient, ZonaImpuesto zonaImpuesto) {
		
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
		
		return getSqlMapClientTemplate().queryForList("ZonaImpuesto.admin.findByIdZona", parametros);
	}
}
