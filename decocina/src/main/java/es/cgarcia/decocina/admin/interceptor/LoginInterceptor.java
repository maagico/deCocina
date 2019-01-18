package es.cgarcia.decocina.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import es.cgarcia.decocina.admin.model.Administrador;
import es.cgarcia.decocina.web.util.Constantes;

/**
 * Comprueba si el usuario está logueado para entrar a la administración. 
 * Comprueba la sesión y la cookie. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    
    /**
     * {@inheritDoc}
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
    {
        HttpSession session = request.getSession();

        Administrador administrador = (Administrador) session.getAttribute(Constantes.USUARIO_ADMIN_LOGUEADO);

        boolean logueadoPorCookie = false;
        
        if (administrador == null) {

        	//Recuperamos las cookies de decocina.
			//Cookie[] cookies = request.getCookies();
			
			//Se comprueba si hay cookie de 'recuerdame' y si es así y coinciden los campos se hace el autologin.
			logueadoPorCookie = false;//CookieUtils.autoLoginCookieSession(session, administradorAdminService, cookies);
			
			if(logueadoPorCookie)
			{
				logger.info("Usuario logueado por cookie, redirigiendo al inicio.");
				
				return true;
			}
        	
        	logger.info("Usuario no logueado, redirigiendo al login.");
        	
            String contexto = request.getContextPath();

            StringBuffer sbRutaRedirect = new StringBuffer();
            sbRutaRedirect.append(contexto);
            sbRutaRedirect.append("/admin/login");

            response.sendRedirect(sbRutaRedirect.toString());

            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception 
    {
    }

    /**
     * {@inheritDoc}
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception 
    {
    }

}
