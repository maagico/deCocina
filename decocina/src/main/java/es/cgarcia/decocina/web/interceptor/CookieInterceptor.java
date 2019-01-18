/**
 * 
 */
package es.cgarcia.decocina.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Interceptor para las cookies. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class CookieInterceptor implements HandlerInterceptor {
	
    /**
     * {@inheritDoc}
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	//Comprobamos si existe la cookie para la ley de cookies.
    	Boolean existeCookie = existeCookie(request);
    	
    	if(!existeCookie){
    	
    		Cookie cookie = new Cookie("lc", "true");
			cookie.setMaxAge(15778463);
			response.addCookie(cookie);
			
    		request.setAttribute("mostrarAvisoLeyCookies", true);
    	}
    	
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * {@inheritDoc}
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
    
    /**
     * Comprueba si existe la cookie de la ley de cookies.
     * @param request Request.
     * @return True si existe, false lo contrario.
     */
    
    private boolean existeCookie(HttpServletRequest request){
    	
    	Cookie[] cookies = request.getCookies();
    	
    	boolean buscar = true;
		boolean existe = false;
		
    	if(cookies == null){
    		
    		existe = false;
    	
    	}else{
    		
			for(int i = 0;i < cookies.length && buscar; i++){
				
				Cookie cookie = (Cookie)cookies[i];
				String nombreCookie = cookie.getName();
				
				if ("lc".equals(nombreCookie)){
					
					buscar = false;
					existe = true;
				}
			}
    	}
    	
		return existe;
    }
}
