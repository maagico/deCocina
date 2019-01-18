package es.cgarcia.decocina.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Categoria;
import es.cgarcia.decocina.web.service.IArticuloWebService;
import es.cgarcia.decocina.web.service.ICategoriaWebService;


/**
 * Controller del Index. Lleva a la página de inicio.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller(value = "siteMapWebController")
public class SiteMapController extends WebControllerBase{
	
	/**
	 * Servicio para los Artículos.
	 */
	@Autowired
	private IArticuloWebService articuloWebService;
	
	/**
	 * Servicio para las Categorías.
	 */
	@Autowired
	private ICategoriaWebService categoriaWebService;
	
	/**
	 * Redirige al sitemap.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige al sitemap. 
	 */
	@RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
	public String siteap(HttpServletRequest request, 
						 Model model){
		
		//Recupera los Artículos para el sitemap.
		List<Articulo> articulos = articuloWebService.findAllSiteMap();
		model.addAttribute("articulos", articulos);
		
		List<Categoria> categorias = categoriaWebService.findAllSiteMap();
		model.addAttribute("categorias", categorias);
		
		String url = getURL(request);
		model.addAttribute("url", url);	
		
		return "web/sitemap/sitemap";
	}
}
