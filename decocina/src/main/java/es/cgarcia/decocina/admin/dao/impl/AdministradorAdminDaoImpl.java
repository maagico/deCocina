package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IAdministradorAdminDao;
import es.cgarcia.decocina.admin.model.Administrador;

/**
 * Implementación del Dao para el Administrador de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Repository("administradorAdminDao")
public class AdministradorAdminDaoImpl extends GenericAdminDaoIbatisImpl<Administrador> implements IAdministradorAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param administrador Administrador.
	 */
	@Autowired
    public AdministradorAdminDaoImpl(SqlMapClient sqlMapClient, Administrador administrador) {
		
        super(sqlMapClient, administrador);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Administrador findByUserAndPassword(String usuario, String password) {
		 
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("usuario", usuario);
		parametros.put("password", password);
		
		return (Administrador)getSqlMapClientTemplate().queryForObject("Administrador.admin.findByUserAndPass", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Administrador findByClaveAndToken(String clave, String token) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idAlt", clave);
		parametros.put("token", token);
		
		return (Administrador)getSqlMapClientTemplate().queryForObject("Administrador.admin.findByClaveAndToken", parametros);
	}
	
}
