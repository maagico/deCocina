package es.cgarcia.decocina.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IZonaWebDao;
import es.cgarcia.decocina.web.model.Zona;

/**
 * Implementación del Dao para la Zona de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("zonaWebDao")
public class ZonaWebDaoImpl extends GenericWebDaoIbatisImpl<Zona> implements IZonaWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param zona Zona.
	 */
	@Autowired
    public ZonaWebDaoImpl(SqlMapClient sqlMapClient, Zona zona) {
		
        super(sqlMapClient, zona);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Zona> findAllProvincias() {
		
		return (List<Zona>)getSqlMapClientTemplate().queryForList("Zona.web.findAllProvincias");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Zona> findAllProvinciasActivas() {
		
		return (List<Zona>)getSqlMapClientTemplate().queryForList("Zona.web.findAllProvinciasActivas");
	}
}
