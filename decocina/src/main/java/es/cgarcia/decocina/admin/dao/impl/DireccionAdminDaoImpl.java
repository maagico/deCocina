package es.cgarcia.decocina.admin.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IDireccionAdminDao;
import es.cgarcia.decocina.web.model.Direccion;

/**
 * Implementación del Dao para la Dirección de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("direccionAdminDao")
public class DireccionAdminDaoImpl extends GenericAdminDaoIbatisImpl<Direccion> implements IDireccionAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param direccion Direccion.
	 */
	@Autowired
    public DireccionAdminDaoImpl(SqlMapClient sqlMapClient, Direccion direccion) {
		
        super(sqlMapClient, direccion);
	}
}
