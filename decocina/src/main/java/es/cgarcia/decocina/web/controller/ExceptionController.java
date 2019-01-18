/**
 * 
 */
package es.cgarcia.decocina.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.cgarcia.decocina.admin.model.Administrador;
import es.cgarcia.decocina.web.menu.CategoriaMenu;
import es.cgarcia.decocina.web.miga.MigaPan;
import es.cgarcia.decocina.web.miga.MigasPan;
import es.cgarcia.decocina.web.util.Constantes;

/**
 * @author Miguel Ángel Álvarez García.
 *
 */
@Controller
@ControllerAdvice
public class ExceptionController extends WebControllerBase{
	
	/**
	 * Captura todas las excepciones.
	 * 
	 * @param request Request.
	 * @param e Excepción.
	 * @return Redirige a la página de error.
	 */
	@ExceptionHandler(Exception.class)
	public String handleError(HttpServletRequest request, Exception e) {
		
		StringBuffer sbRequestURL = request.getRequestURL();
		String requestURL = sbRequestURL.toString();
		
		if(requestURL.contains("/admin/")){
			
			return "admin/consola/estatico/error";
			
		}else{
			
			MigasPan migasPan = new MigasPan();
			
			MigaPan iMigaPan = new MigaPan();
			iMigaPan.setTexto("Inicio");
			iMigaPan.setEnlace("/");
			
			migasPan.addMiga(iMigaPan);
			
			MigaPan cuMigaPan = new MigaPan();
			cuMigaPan.setTexto("Se ha producido un error");
			
			migasPan.addMiga(cuMigaPan);
			
			request.setAttribute("migasPan", migasPan);
			
			List<CategoriaMenu> categoriasMenu = getCategoriasMenu();
			request.setAttribute("categoriasMenu", categoriasMenu);
			
			return "web/estatico/error";
		}
	}
	
	/**
	 * Redirige a la página de error 404. Página no encontrada.
	 *
	 * @model Model.
	 * @return Redirige a la página de error 404. Página no encontrada.
	 */
	@RequestMapping(value = "/error-404", method = RequestMethod.GET)
	public String error404(HttpServletRequest request, Model model){
		
		String requestURI = (String)request.getAttribute("javax.servlet.forward.request_uri");
		
		if(requestURI.contains("/admin/")){
			
			HttpSession session = request.getSession();
			Administrador administrador = (Administrador) session.getAttribute(Constantes.USUARIO_ADMIN_LOGUEADO);

			if(administrador != null){
				
				return "admin/consola/estatico/error404";
				
			}else{
				
				return "admin/consola/estatico/error404-sin-sesion";
			}
			
		}else{
			
			MigasPan migasPan = new MigasPan();
		
			MigaPan iMigaPan = new MigaPan();
			iMigaPan.setTexto("Inicio");
			iMigaPan.setEnlace("/");
			
			migasPan.addMiga(iMigaPan);
			
			MigaPan peMigaPan = new MigaPan();
			peMigaPan.setTexto("Página no encontrada");
		
			migasPan.addMiga(peMigaPan);
		
			model.addAttribute("migasPan", migasPan);
		}
		
		return "web/estatico/error404";
	}
}
