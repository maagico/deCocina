package es.cgarcia.decocina.admin.dao;

import es.cgarcia.decocina.admin.model.Administrador;
import es.cgarcia.decocina.web.dao.IGenericDao;


/**
 * Interfaz para el Administrador de la parte admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IAdministradorAdminDao extends IGenericDao<Administrador> {

	/**
	 * Busca un Administrador por su usuario y password.
	 * 
	 * @param usuario Usuario.
	 * @param password Password.
	 * @return Devuelve un Administrador si el usuario y password coinciden o null si no.
	 */
	Administrador findByUserAndPassword(String usuario, String password);

	/**
	 * Busca un Administrador por la cookie de sesión('recuerdame').
	 * 
	 * @param clave Clave de la cookie.
	 * @param token Token de la cookie.
	 * @return Devuelve un Administrador si la clave y valor del cookir coinciden.
	 */
	Administrador findByClaveAndToken(String clave, String token); 
}
