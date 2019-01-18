package es.cgarcia.decocina.web.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IEstadoWebDao;
import es.cgarcia.decocina.web.model.Estado;

/**
 * Implementación del Dao para los Pedidos Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("estadoWebDao")
public class EstadoWebDaoImpl extends GenericWebDaoIbatisImpl<Estado> implements IEstadoWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param estado Estado.
	 */
	@Autowired
    public EstadoWebDaoImpl(SqlMapClient sqlMapClient, Estado estado) {
		
        super(sqlMapClient, estado);
	}
}
