package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.ICestaAdminDao;
import es.cgarcia.decocina.web.model.Cesta;

/**
 * Implementación del Dao para la Cesta de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("cestaAdminDao")
public class CestaAdminDaoImpl extends GenericAdminDaoIbatisImpl<Cesta> implements ICestaAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param zona Zona.
	 */
	@Autowired
    public CestaAdminDaoImpl(SqlMapClient sqlMapClient, Cesta cesta) {
		
        super(sqlMapClient, cesta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByIdCliente(Long idCliente) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCliente", idCliente);
		
		getSqlMapClientTemplate().delete("Cesta.admin.deleteByIdCliente", parametros);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void deleteByIdArticulo(Long idArticulo) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idArticulo", idArticulo);
		
		getSqlMapClientTemplate().delete("Cesta.admin.deleteByIdArticulo", parametros);
	}
}
