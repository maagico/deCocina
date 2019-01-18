package es.cgarcia.decocina.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.dao.IAdministradorAdminDao;
import es.cgarcia.decocina.admin.model.Administrador;
import es.cgarcia.decocina.admin.service.IAdministradorAdminService;

/**
 * Contiene los métodos necesarios para trabajar con un Administrador de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("administradorAdminService")
public class AdministradorAdminServiceImpl extends GenericServiceImpl<Administrador, IAdministradorAdminDao> implements IAdministradorAdminService{

	/**
	 * Constructor.
	 * 
	 * @param administradorAdminDao Dao para el Administrador de la parte Admin.
	 */
	@Autowired
	public AdministradorAdminServiceImpl(IAdministradorAdminDao administradorAdminDao) {
		super(administradorAdminDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Administrador findByUserAndPassword(String usuario, String password){
		
		Administrador administrador = dao.findByUserAndPassword(usuario, password);
		
		return administrador;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Administrador findByClaveAndToken(String clave, String token) {
		
		Administrador administrador = dao.findByClaveAndToken(clave, token);
		
		return administrador;
	}
}
