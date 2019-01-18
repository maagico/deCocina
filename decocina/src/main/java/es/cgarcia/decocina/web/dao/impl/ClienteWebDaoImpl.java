package es.cgarcia.decocina.web.dao.impl;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IClienteWebDao;
import es.cgarcia.decocina.web.model.Cliente;

/**
 * Implementación del Dao para el Cliente de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("clienteWebDao")
public class ClienteWebDaoImpl extends GenericWebDaoIbatisImpl<Cliente> implements IClienteWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param cliente Cliente.
	 */
	@Autowired
    public ClienteWebDaoImpl(SqlMapClient sqlMapClient, Cliente cliente) {
		
        super(sqlMapClient, cliente);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente findByEmail(String email) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("email", email);
		
		return (Cliente)getSqlMapClientTemplate().queryForObject("Cliente.web.findByEmail", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente findByEmailAndPassword(String email, String password) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("email", email);
		parametros.put("password", password);
		
		return (Cliente)getSqlMapClientTemplate().queryForObject("Cliente.web.findByEmailAndPass", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePassword(String password, Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("password", password);
		parametros.put("id", id);
		
		getSqlMapClientTemplate().update("Cliente.web.updatePassword", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateToken(Long idCliente, String token, Calendar fecha) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idCliente);
		parametros.put("token", token);
		parametros.put("fecha", fecha);
		
		getSqlMapClientTemplate().update("Cliente.web.updateToken", parametros);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePassword(String token, String nuevoPassword) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("token", token);
		parametros.put("password", nuevoPassword);
		
		getSqlMapClientTemplate().update("Cliente.web.updatePasswordToken", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente findByToken(String token) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("token", token);
		
		return (Cliente)getSqlMapClientTemplate().queryForObject("Cliente.web.findByToken", parametros);
		
	}
}
