package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IFormaEnvioFormaPagoAdminDao;
import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;

/**
 * Implementación del Dao para las Formas de Pago asociadas a una Forma de Envío de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("formaEnvioFormaPagoAdminDao")
public class FormaEnvioFormaPagoAdminDaoImpl extends GenericAdminDaoIbatisImpl<FormaEnvioFormaPago> implements IFormaEnvioFormaPagoAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param formaEnvioFormaPago Formas de Pago asociadas a una Forma de Envío.
	 */
	@Autowired
    public FormaEnvioFormaPagoAdminDaoImpl(SqlMapClient sqlMapClient, FormaEnvioFormaPago formaEnvioFormaPago) {
		
        super(sqlMapClient, formaEnvioFormaPago);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaEnvioFormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("idFormaEnvio", idFormaEnvio);
		
		return getSqlMapClientTemplate().queryForList("FormaEnvioFormaPago.admin.findByIdZonaAndFormaEnvio", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteFormaEnvio(Long id, Long idZona) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("idZona", idZona);
		
		getSqlMapClientTemplate().delete("FormaEnvioFormaPago.admin.deleteFormaEnvio", parametros);
		
	}
}
