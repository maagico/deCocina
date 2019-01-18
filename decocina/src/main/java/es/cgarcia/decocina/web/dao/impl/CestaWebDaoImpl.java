package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.ICestaWebDao;
import es.cgarcia.decocina.web.model.Cesta;

/**
 * Implementación del Dao para la Zona de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("cestaWebDao")
public class CestaWebDaoImpl extends GenericWebDaoIbatisImpl<Cesta> implements ICestaWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param zona Zona.
	 */
	@Autowired
    public CestaWebDaoImpl(SqlMapClient sqlMapClient, Cesta cesta) {
		
        super(sqlMapClient, cesta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actualizarCantidad(Long idCliente, Long idArticulo, Integer cantidad) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCliente", idCliente);
		parametros.put("idArticulo", idArticulo);
		parametros.put("cantidad", cantidad);
		
		getSqlMapClientTemplate().update("Cesta.web.actualizarCantidad", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override 
	public Cesta findByIdClienteAndIdArticulo(Long idCliente, Long idArticulo) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCliente", idCliente);
		parametros.put("idArticulo", idArticulo);
		
		return (Cesta)getSqlMapClientTemplate().queryForObject("Cesta.web.findByIdClienteAndIdArticulo", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Cesta> findByIdCliente(Long idCliente) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCliente", idCliente);
		
		return getSqlMapClientTemplate().queryForList("Cesta.web.findByIdCliente", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByIdClienteAndIdArticulo(Long idCliente, Long idArticulo) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCliente", idCliente);
		parametros.put("idArticulo", idArticulo);
		
		getSqlMapClientTemplate().delete("Cesta.web.deleteByIdClienteAndIdArticulo", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByIdCliente(Long idCliente) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCliente", idCliente);
		
		getSqlMapClientTemplate().delete("Cesta.web.deleteByIdCliente", parametros);
		
	}
}
