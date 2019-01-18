package es.cgarcia.decocina.admin.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.cgarcia.decocina.admin.model.Administrador;
import es.cgarcia.decocina.admin.service.IAdministradorAdminService;
import es.cgarcia.decocina.web.util.Constantes;
import es.cgarcia.decocina.web.util.MD5Utils;

/**
 * Utilidades para las cookies de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public final class CookieUtils 
{
	/**
	 * Constructor.
	 */
	private CookieUtils(){
	}
	
	/**
	 * Crea la cookie de sesion.
	 * 
	 * @param response Response.
	 * @param idAlt Id alternativo.
	 * @param token Token.
	 */
	public static void crearCookieSesion(HttpServletResponse response, String idAlt, String token){
		
		//Creamos la cookie.
    	Cookie cookie = new Cookie(idAlt, token);
    	cookie.setMaxAge(60 * 60 * 24 * 365);
    	//cookie.setSecure(true);
    	
    	//Añadimos la cookie.
    	response.addCookie(cookie);
	}
	/**
	 * Comprueba si el usuario tiene una cookie de sesion('recuerdame') y si la 
	 * tiene y coinciden los campos se hace el autologin.
	 * 
	 * @param session Sesión.
	 * @param administradorAdminService Servicio para el Administrador.
	 * @param cookies Cookie de deCocina.
	 * @return True si se ha logueado usando la cookie, false lo contrario.
	 */
	public static boolean autoLoginCookieSession(HttpSession session, IAdministradorAdminService administradorAdminService, Cookie[] cookies){
		
		boolean logueadoPorCookie = false;
		
		if(cookies != null)
		{
			for (Cookie cookie : cookies) {
				
				String clave = cookie.getName();
				String token = cookie.getValue();
					
				boolean existeOI = clave.startsWith(Constantes.COOKIE_OI);
				
				if(existeOI){
					
					String tokenMD5 = MD5Utils.MD5(token);
					
					// Recuperamos el administrador de la base de datos y comprobamos si los valores de la cookie coinciden.
					Administrador administradorBD = administradorAdminService.findByClaveAndToken(clave, tokenMD5);
					
					if(administradorBD != null)
					{
						//Password a vacío para que no viaje en la sesión. 
						administradorBD.setPassword("");
						
						session.setAttribute(Constantes.USUARIO_ADMIN_LOGUEADO, administradorBD);
						
						logueadoPorCookie = true;
					}
				}
			}
		}
		
		return logueadoPorCookie;
	}
	
	
	/**
	 * Busca la cookie de sesion('recuerdame') y la borra.
	 * 
	 * @param response Response.
	 * @param cookies Cookies Cookies de decocina.
	 */
	public static void borrarCookieSesion(HttpServletResponse response, Cookie[] cookies){
		
		if(cookies != null){
			
			for (Cookie cookie : cookies) {
				
				String clave = cookie.getName();
					
				boolean existeOI = clave.startsWith(Constantes.COOKIE_OI);
				
				if(existeOI){
					
					cookie.setPath("/decocina/admin/");
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}
}
