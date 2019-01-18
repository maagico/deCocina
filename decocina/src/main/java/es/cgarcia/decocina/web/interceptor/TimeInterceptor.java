/**
 * 
 */
package es.cgarcia.decocina.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import es.cgarcia.decocina.admin.controller.CategoriaController;

/**
 * Interceptor que calcula el tiempo de ejecución de un Controller.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class TimeInterceptor extends HandlerInterceptorAdapter {
 
	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(CategoriaController.class);   
    
	/**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            				 HttpServletResponse response, 
            				 Object handler) throws Exception {
    	
        Long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        
        return true;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public void postHandle(HttpServletRequest request,
    					   HttpServletResponse response, Object handler,
    					   ModelAndView modelAndView) throws Exception {
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            					HttpServletResponse response, 
            					Object handler, 
            					Exception ex) throws Exception {
    	
        Long start = (Long) request.getAttribute("start");
        
        Long tiempoEjecucion = (System.currentTimeMillis() - start);
        
        String url = request.getRequestURL().toString();
        
        logger.info("URL {} ejecutada en {} ms. ", url, tiempoEjecucion);
        
    }

}
