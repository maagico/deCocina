/**
 * 
 */
package es.cgarcia.decocina.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.cgarcia.decocina.admin.util.URLUtils;
import es.cgarcia.decocina.web.menu.CategoriaMenu;
import es.cgarcia.decocina.web.miga.MigasPan;
import es.cgarcia.decocina.web.session.MenuSession;
import es.cgarcia.decocina.web.util.Constantes;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;


/**
 * Controller Base del que deben heredar todos los Controllers de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 */
public class WebControllerBase{
	
	/**
	 * Menú de la aplicación.
	 */
	@Autowired
	private MenuSession menuSession;
	
	/**
	 * Muestra las migas de pan.
	 * @param migasPan Migas de pan a mostrar.
	 */
	protected void mostrarMigasPan(MigasPan migasPan, Model model){
		
		model.addAttribute("migasPan", migasPan);
	}
	
	/**
	 * Recuera el clienteWrapper de la sesión.
	 * @param request Request.
	 * @return Devuelve el ClienteWrapper de la sesión.
	 */
	protected ClienteWrapper getClienteWrapper(HttpServletRequest request){
		
		HttpSession sesion = request.getSession();
		ClienteWrapper clienteWrapper = (ClienteWrapper)sesion.getAttribute(Constantes.CLIENTE_WEB_LOGUEADO); 
		
		return clienteWrapper;
	}
	
	/**
	 * Devuelve las Categorías del Menú.
	 * @return Categorías del Menú.
	 */
	@ModelAttribute(value = "categoriasMenu")
	protected List<CategoriaMenu> getCategoriasMenu(){
		
		List<CategoriaMenu> categoriasMenu = menuSession.getCategoriasMenu();
		return categoriasMenu;
	}
	
	/**
     * Devuelve la URL completa. 
     * @param request Request.
     * @return La URL completa.
     */
    protected String getURL(HttpServletRequest request){
		
    		String url = URLUtils.getURL(request);
		
		return url;
	}
    
    /**
     * Devuelve la URL antes del forward.
     * 
	 * @param request Request.
	 * @return URL antes del forward.
	 */
	protected String getForwardURL(HttpServletRequest request) {
		
		String url = (String)request.getAttribute("javax.servlet.forward.request_uri"); 
		
		//Si tiene '/', la quitamos.
		Boolean contieneBarra = url.contains("/");
		
		if(contieneBarra){
			
			Integer posLastIndexOf = url.lastIndexOf('/') + 1;
			Integer length = url.length();
			
			url = url.substring(posLastIndexOf, length);
		}
		
		return url;
	}
}
