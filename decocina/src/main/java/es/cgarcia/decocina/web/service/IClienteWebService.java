package es.cgarcia.decocina.web.service;

import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.ClientePassword;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Cliente
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IClienteWebService extends IGenericService<Cliente>{
	
	/**
	 * Devulve un Cliente por su email.
	 * 
	 * @param email Email del Cliente.
	 * @return JSON con el resultado. 
	 */
	String existeByEmail(String email);
	
	/**
	 * Devulve un Cliente por su email.
	 * 
	 * @param email Email del Cliente.
	 * @return Cliente encontrado a partir del email. 
	 */
	Cliente findByEmail(String email);

	/**
	 * Crea un nuevo Cliente.
	 * @param cliente Cliente a crear.
	 * @param host Host del servidor.
	 */
	void crearCliente(Cliente cliente, String host);

	/**
	 * Actualiza los datos del Cliente.
	 * @param clienteWrapper ClienteWrapper.
	 * @param cliente Cliente.
	 */
	Cliente update(ClienteWrapper clienteWrapper, Cliente cliente); 
	
	/**
	 * Devuelve un Cliente por su email y su password.
	 * 
	 * @param email Email del usuario.
	 * @param password Password del usuario.
	 * @return Cliente si el emaily el passowrd son correctos.
	 */
	Cliente findByEmailAndPassword(String email, String password);

	/**
	 * Modifica el password.
	 * @param clientePassword ClientePassword.
	 */
	void updatePassword(ClientePassword clientePassword);

	/**
	 * Envía email de recuperación de la constraseña.
	 * @param email Email del Cliente.
	 * @param host Host.
	 */
	void recuperarPassword(String email, String host);

	/**
	 * Cambia el password.
	 * @param token Token.
	 * @param nuevoPassword Nuevo password.
	 */
	void updatePassword(String token, String nuevoPassword);

	/**
	 * Comprueba si el token no ha caducado.
	 * @param token Token.
	 * @return True si es correcto o false si ha caducado. 
	 */
	Boolean comprobarToken(String token);
}
