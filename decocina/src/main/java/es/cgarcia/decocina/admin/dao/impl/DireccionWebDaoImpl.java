package es.cgarcia.decocina.admin.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IDireccionWebDao;
import es.cgarcia.decocina.web.dao.impl.GenericWebDaoIbatisImpl;
import es.cgarcia.decocina.web.model.Direccion;

/**
 * Implementación del Dao para la Dirección de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("direccionWebDao")
public class DireccionWebDaoImpl extends GenericWebDaoIbatisImpl<Direccion> implements IDireccionWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param direccion Direccion.
	 */
	@Autowired
    public DireccionWebDaoImpl(SqlMapClient sqlMapClient, Direccion direccion) {
		
        super(sqlMapClient, direccion);
	}
}
