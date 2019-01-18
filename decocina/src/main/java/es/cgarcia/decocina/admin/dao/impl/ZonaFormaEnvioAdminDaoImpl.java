package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IZonaFormaEnvioAdminDao;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;

/**
 * Implementación del Dao para la ZonaImpuesto de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("zonaImpuestoAdminDao")
public class ZonaFormaEnvioAdminDaoImpl extends GenericAdminDaoIbatisImpl<ZonaFormaEnvio> implements IZonaFormaEnvioAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param zonaFormaEnvio ZonaFormaEnvio.
	 */
	@Autowired
    public ZonaFormaEnvioAdminDaoImpl(SqlMapClient sqlMapClient, ZonaFormaEnvio zonaFormaEnvio) {
		
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
		
		return getSqlMapClientTemplate().queryForList("ZonaFormaEnvio.admin.findByIdZona", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteFormaEnvio(Long id, Long idZona) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idFormaEnvio", id);
		parametros.put("idZona", idZona);
		getSqlMapClientTemplate().delete("ZonaFormaEnvio.admin.delete", parametros);
	}
}
