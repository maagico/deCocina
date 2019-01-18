package es.cgarcia.decocina.admin.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import es.cgarcia.decocina.web.model.ModelBase;

/**
 * Administrador de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class Administrador extends ModelBase{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 3001706925554327581L;

	/**
	 * Id alternativo.
	 */
	private String idAlt;
	
	/**
	 * Usuario administrador.
	 */
	@NotEmpty
	private String usuario;
	
	/**
	 * Passsword del administrador.
	 */
	@NotEmpty
	private String password;

	/**
	 * Indica si el usuario ha pulsado el check para recordar la sesión.
	 */
	private Boolean recuerdame;
	
	/**
	 * Token para la cookie.
	 */
	private String token;
	
	/**
	 * Devuelve el valor del atributo idAlt.
	 *
	 * @return El valor del atributo.
	 */
	public String getIdAlt() {
		return idAlt;
	}

	/**
	 * Fija el valor del atributo idAlt.
	 *
	 * @param idAlt Nuevo valor del atributo.
	 */
	public void setIdAlt(String idAlt) {
		this.idAlt = idAlt;
	}

	/**
	 * Devuelve el valor del atributo usuario.
	 * 
	 * @return Usuario.
	 */
	public String getUsuario(){
		
		return usuario;
	}
	
	/**
	 * Fija el valor del atributo usuario.
	 *
	 * @param usuario Usuario.
	 */
	public void setUsuario(String usuario){
		
		this.usuario = usuario;
	}
	
	/**
	 * Fija el valor del atributo password.
	 * 
	 * @return Password.
	 */
	public String getPassword(){
		
		return password;
	}
	
	/**
	 * Fija el valor del atributo password.
	 * 
	 * @param password Password.
	 */
	public void setPassword(String password){
		
		this.password = password;
	}

	/**
	 * Devuelve el valor del atributo recuerdame.
	 * 
	 * @return Recuerdame.
	 */
	public Boolean getRecuerdame() {
		
		return recuerdame;
	}

	/**
	 * Fija el valor del atributo recuerdame.
	 * 
	 * @param recuerdame Recuerdame.
	 */
	public void setRecuerdame(Boolean recuerdame) {
		
		this.recuerdame = recuerdame;
	}
	
	/**
	 * Devuelve el valor del atributo token.
	 *
	 * @return El valor del atributo.
	 */
	public String getToken() {
		
		return token;
	}

	/**
	 * Fija el valor del atributo token.
	 *
	 * @param token Nuevo valor del atributo.
	 */
	public void setToken(String token) {
		
		this.token = token;
	}
}
