/**
 * 
 */
package es.cgarcia.decocina.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.cgarcia.decocina.admin.service.IHerramientaAdminService;

/**
 * Controller de las Herramientas.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class HerramientaController extends AdminControllerBase{

	/**
	 * Texto de la Miga de pan.
	 */
	private String textoMiga = "Herramientas";
	
	/**
	 * Servicio para las Herramientas.
	 */
	@Autowired
	private IHerramientaAdminService herramientaAdminService;
	
	/**
	 * Redirige a la página de las herramientas.
	 *  
	 * @return Redirige a la página de Herramientas.
	 */
	@RequestMapping(value = "/admin/consola/herramienta/herramienta-find", method = RequestMethod.GET)
	public String find(Model model){
		
		mostrarMiga(textoMiga, model);
		
		Boolean activo = herramientaAdminService.cacheadorEstaActivo();
		model.addAttribute("activo", activo);
		
		return "admin/consola/herramienta/ver-herramientas";
	}
	
	/**
	 * Borra la caché de Ibatis.
	 * 
	 * @param request Request.
	 * @param model Model.
	 * @return Redirige a la página de Herramientas.
	 */
	@RequestMapping(value = "/admin/consola/herramienta/cache-ibatis-delete", method = RequestMethod.POST)
	public String borrarCacheIbatis(HttpServletRequest request,
						 			Model model){
		
		mostrarMiga(textoMiga, model);
		
		herramientaAdminService.borrarCacheIbatis();
		
		model.addAttribute("mensaje", "Caché de Ibatis borrada correctamente");
		
		Boolean activo = herramientaAdminService.cacheadorEstaActivo();
		model.addAttribute("activo", activo);
		
		return "admin/consola/herramienta/ver-herramientas";
	}
	
	/**
	 * Cachear Web. Recorre toda la Web para cachear las imágenes y querys.
	 * 
	 * @param request Request.
	 * @param model Model.
	 * @return Redirige a la página de Herramientas.
	 */
	
	@RequestMapping(value = "/admin/consola/herramienta/cachear-web", method = RequestMethod.POST)
	public String cachearWeb(HttpServletRequest request,
						 	 Model model){
		
		mostrarMiga(textoMiga, model);
		
		String contexto = request.getContextPath();
		
		Boolean activo = herramientaAdminService.cacheadorEstaActivo();
		
		if(!activo){
			
			herramientaAdminService.cachearWeb(contexto);
			activo = true;
		}
		
		model.addAttribute("activo", activo);
		
		model.addAttribute("mensaje", "Trabajo de cacheado ejecutándose");
		
		return "admin/consola/herramienta/ver-herramientas";
	}
	
	/**
	 * Cachear Web. Recorre toda la Web para cachear las imágenes y querys.
	 * 
	 * @param request Request.
	 * @param model Model.
	 * @return Redirige a la página de Herramientas.
	 */
	
	@RequestMapping(value = "/admin/consola/herramienta/cachear-web-delete", method = RequestMethod.POST)
	public String cachearWebDelete(HttpServletRequest request,
						 	 	  Model model){
		
		mostrarMiga(textoMiga, model);
		
		Boolean activo = herramientaAdminService.cacheadorEstaActivo();
		
		if(activo){
			
			herramientaAdminService.cacheadorBorrar();
		}
		
		model.addAttribute("activo", false);
		
		model.addAttribute("mensaje", "Trabajo de cacheado parado");
		
		return "admin/consola/herramienta/ver-herramientas";
	}
}
