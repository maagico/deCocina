package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IFormaPagoWebDao;
import es.cgarcia.decocina.web.model.FormaPago;

/**
 * Implementación del Dao para las Formas de Pago de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("formaPagoWebDao")
public class FormaPagoWebDaoImpl extends GenericWebDaoIbatisImpl<FormaPago> implements IFormaPagoWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param formaPago FormaPago.
	 */
	@Autowired
    public FormaPagoWebDaoImpl(SqlMapClient sqlMapClient, FormaPago formaPago) {
		
        super(sqlMapClient, formaPago);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("idFormaEnvio", idFormaEnvio);
		
		return getSqlMapClientTemplate().queryForList("FormaPago.web.findAllByIdZonaAndIdFormaEnvio", parametros);
	}
}
