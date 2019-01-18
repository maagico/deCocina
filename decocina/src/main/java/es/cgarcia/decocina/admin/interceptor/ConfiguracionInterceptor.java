package es.cgarcia.decocina.admin.interceptor;

import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Configuración de la aplicación.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ConfiguracionInterceptor implements HandlerInterceptor {
	
    /**
	 * Propiedades de configuración.
	 */
	@Resource(name = "confProperties")
	private Properties properties;
	
    /**
     * {@inheritDoc}
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	String versionJS = properties.getProperty("version.js");
    	request.setAttribute("versionJS", versionJS);

    	String versionCSS = properties.getProperty("version.css");
    	request.setAttribute("versionCSS", versionCSS);
    	
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
