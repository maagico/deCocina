package es.cgarcia.decocina.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import es.cgarcia.decocina.web.manager.CestaManager;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.util.Constantes;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;

/**
 * Crea el Cliente en la sesión.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ClienteInterceptor implements HandlerInterceptor {
	
    /**
     * {@inheritDoc}
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    	//Si es null creamos el Cliente de nuevo vacío como invitado con un Pedido y una cesta vacía.
    	HttpSession sesion = request.getSession();
    	ClienteWrapper clienteWrapper = (ClienteWrapper)sesion.getAttribute(Constantes.CLIENTE_WEB_LOGUEADO);
    	
    	if(clienteWrapper == null){
    		
    		Cliente cliente = new Cliente();
    		Pedido pedido = new Pedido();
    		
    		clienteWrapper = new ClienteWrapper(cliente, pedido);
       	clienteWrapper.setEsInvitado(true);
    		
    		sesion.setAttribute(Constantes.CLIENTE_WEB_LOGUEADO, clienteWrapper);
    	}

    	//Recuperamos el número de Artículos de la cesta y lo pasamos al Request.
    	PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
    	CestaManager cestaManager = pedidoWrapper.getCestaManager();
    	
    	Integer numeroArticulosCesta= cestaManager.getNumeroArticulos();
        
    	request.setAttribute("numeroArticulosCesta", numeroArticulosCesta);
    	
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
