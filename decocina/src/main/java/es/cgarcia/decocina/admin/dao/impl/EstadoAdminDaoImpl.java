package es.cgarcia.decocina.admin.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IEstadoAdminDao;
import es.cgarcia.decocina.web.model.Estado;

/**
 * Implementación del Dao para los Estados Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("estadoAdminDao")
public class EstadoAdminDaoImpl extends GenericAdminDaoIbatisImpl<Estado> implements IEstadoAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param pedido Pedido.
	 */
	@Autowired
    public EstadoAdminDaoImpl(SqlMapClient sqlMapClient, Estado estado) {
		
        super(sqlMapClient, estado);
	}
}
