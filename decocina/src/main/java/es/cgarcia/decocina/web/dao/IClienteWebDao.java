package es.cgarcia.decocina.web.dao;

import java.util.Calendar;

import es.cgarcia.decocina.web.model.Cliente;


/**
 * Interfaz del Dao para los Clientes de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IClienteWebDao extends IGenericDao<Cliente> {

	/**
	 * Devulve un Cliente por su email.
	 * 
	 * @param email Email del Cliente.
	 * @return Cliente encontrado a partir del email. 
	 */
	Cliente findByEmail(String email);

	/**
	 * Devuelve un Cliente por su email y su password.
	 * 
	 * @param email Email del usuario.
	 * @param password Password del usuario.
	 * @return Cliente si el emaily el passowrd son correctos.
	 */
	Cliente findByEmailAndPassword(String email, String password);

	/**
	 * Modifica el PAssword del Cliente.
	 * @param password Nuevo Password.
	 * @param id Id del Cliente.
	 */
	void updatePassword(String password, Long id);

	/**
	 * Inserta el Token de recuperación de password.
	 * @param idCliente Id del Cliente.
	 * @param token Token.
	 * @param fecha Fecha de inserción del Token.
	 */
	void updateToken(Long idCliente, String token, Calendar fecha);

	/**
	 * Cambia el password.
	 * @param token Token.
	 * @param nuevoPassword Nuevo password.
	 */
	void updatePassword(String token, String nuevoPassword);

	/**
	 * Devuelve un Cliente por su token.
	 * @param token Token.
	 * @return Cliente o null si no existe el token en ningún Cliente.
	 */
	Cliente findByToken(String token);
}
