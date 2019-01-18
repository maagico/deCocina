package es.cgarcia.decocina.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import es.cgarcia.decocina.web.util.Constantes;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;

/**
 * Interceptor de Login de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * {@inheritDoc}
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        ClienteWrapper clienteWrapper = (ClienteWrapper) session.getAttribute(Constantes.CLIENTE_WEB_LOGUEADO);

        Boolean esInvitado = clienteWrapper == null || clienteWrapper.getEsInvitado();
        
        if (esInvitado) {
        	
            String contexto = request.getContextPath();

            StringBuffer sbRutaRedirect = new StringBuffer();
            sbRutaRedirect.append(contexto);
            sbRutaRedirect.append("/iniciar-sesion?f=rp");
            
            response.sendRedirect(sbRutaRedirect.toString());
            
            return false;
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

}
