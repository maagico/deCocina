package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IFormaEnvioWebDao;
import es.cgarcia.decocina.web.model.FormaEnvio;

/**
 * Implementación del Dao para las Formas de Envío de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("formaEnvioWebDao")
public class FormaEnvioWebDaoImpl extends GenericWebDaoIbatisImpl<FormaEnvio> implements IFormaEnvioWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param fabricante Fabricante.
	 */
	@Autowired
    public FormaEnvioWebDaoImpl(SqlMapClient sqlMapClient, FormaEnvio formaEnvio) {
		
        super(sqlMapClient, formaEnvio);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaEnvio> findAllByIdZona(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("FormaEnvio.web.findAllByIdZona", parametros);
	}
}
