package es.cgarcia.decocina.admin.service;

import es.cgarcia.decocina.admin.model.Administrador;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Administrador
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IAdministradorAdminService extends IGenericService<Administrador>{

	/**
	 * Busca un Administrador por su usuario y password.
	 * 
	 * @param usuario Usuario.
	 * @param password Password.
	 * @return Devuelve un Administrador si el usuario y password coinciden o null si no.
	 */
	Administrador findByUserAndPassword(String usuario, String password);

	/**
	 * Busca un Administrador por la cookie.
	 * 
	 * @param clave Clave de la cookie.
	 * @param valor token de la cookie.
	 * @return Devuelve un Administrador si la clave y valor del cookir coinciden.
	 */
	Administrador findByClaveAndToken(String clave, String token);
}
