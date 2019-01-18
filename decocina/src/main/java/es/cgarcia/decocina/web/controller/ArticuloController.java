package es.cgarcia.decocina.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.cgarcia.decocina.web.miga.MigaPan;
import es.cgarcia.decocina.web.miga.MigasPan;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Categoria;
import es.cgarcia.decocina.web.service.IArticuloWebService;
import es.cgarcia.decocina.web.service.ICategoriaWebService;
import es.cgarcia.decocina.web.util.Constantes;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;


/**
 * Controller del Index. Lleva a la página de inicio.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller(value = "articuloWebController")
public class ArticuloController extends WebControllerBase{

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
	 * Redirige al index.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige al index. 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, 
						Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		//Recuperamos los últimos añadidos.
		List<Articulo> articulos = articuloWebService.findNovedades(clienteWrapper, Constantes.NUMERO_NOVEDADES);
		model.addAttribute("articulos", articulos);
		
		//Mostramos la miga de pan.
		mostrarMiga(null, null, model);
		
		//Mostramos los más vendidos y más vistos.
		mostrarArticulosMas(clienteWrapper, null, null, model);
				
		return "web/index/index";
	}
	
	/**
	 * Redirige a la página del detalle de un Artículo o a una Categoría.
	 * 
	 * @param request HttpServletRequest.
	 * @param response HttpServletResponse.
	 * @param model Model
	 * @return Redirige a la página del detalle de un Artículo o a una Categoría. 
	 */
	@RequestMapping(value = "/{url:.+}", method = RequestMethod.GET)
	public String articuloOCategoria(@PathVariable(value="url") String url, 
									HttpServletRequest request,
									HttpServletResponse response, 
									Model model) throws IOException{
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		//Recuperamos el Artículo por su URL.
		Articulo articulo = articuloWebService.findByURL(clienteWrapper, url);
		
		if(articulo != null){
		
			return articuloDetalle(articulo, request, response, model);
		}
		
		//Recuperamos la Categoría por su URL.
		Categoria categoria = categoriaWebService.findByURL(url);
		
		if(categoria != null){
			
			return articulosPorCategoria(categoria, request, response, model);
		}
		
		//Si no existe el Artículo o la Categoría enviamos un 404.
		if(articulo == null && categoria == null){
			
			response.setCharacterEncoding("UTF-8");
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			
			return null;
		}
		
		return null;
	}
	
	/**
	 * Muestra el detalle de un Artículo.
	 * 
	 * @param id Id del Artículo.
	 * @param request HttpServletRequest.
	 * @param model Model.
	 * @return Redirige a la página del detalle del Artículo.
	 */
	@RequestMapping(value = "/articulo-url-corta", method = RequestMethod.GET)
	public String articuloUrlCorta(@RequestParam("id") Long id, 
								   HttpServletRequest request,
								   HttpServletResponse response,
								   Model model) throws IOException{
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		if(id == null || id.equals("")){
			
			response.setCharacterEncoding("UTF-8");
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			
			return null;
		}
		
		//Recuperamos el Artículo.
		Articulo articulo = articuloWebService.findById(clienteWrapper, id);
		
		if(articulo != null){
			
			String url = getURL(request);
			String urlArticulo = articulo.getUrlAmigable();
			
			response.setCharacterEncoding("UTF-8");
			
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			
			StringBuilder sbURL = new StringBuilder();
			sbURL.append(url);
			sbURL.append(urlArticulo);
			
			response.setHeader("Location", sbURL.toString());
			
			return null;
			
		}else{
			
			response.setCharacterEncoding("UTF-8");
			
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			
			return null;
		}
		
	}
	
	/**
	 * Muestra el detalle de un Artículo.
	 * 
	 * @param articulo Artículo.
	 * @param request HttpServletRequest.
	 * @param response HttpServletResponse.
	 * @param model Model.
	 * @return Redirige a la página del detalle del Artículo.
	 */
	public String articuloDetalle(Articulo articulo, 
								  HttpServletRequest request,
								  HttpServletResponse response,
								  Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		model.addAttribute("articulo", articulo);
		
		Long id = articulo.getId();
		
		//Recuperamos la Categoría del Artículo.
		Categoria categoria = articulo.getCategoria();
		Long idCategoria = categoria.getId();
		
		//Mostramos la miga de pan.
		mostrarMiga(categoria, articulo, model);
		
		//Mostramos los más vendidos, los más vistos y ventas relacionadas.
		mostrarArticulosMas(clienteWrapper, idCategoria, id, model);
		
		//Recuperamos los Artículos relacionados.
		List<Articulo> articulosRelacionados = articuloWebService.findRelacionadosByIdCategoria(clienteWrapper, id, idCategoria);
		model.addAttribute("articulosRelacionados", articulosRelacionados);
		
		String urlCompleta = getURL(request);
		model.addAttribute("urlCompleta", urlCompleta);
		
		model.addAttribute("enArticuloDetalle", true);
		
		return "web/articulo/articulo-detalle";
	}
	
	/**
	 * Muestra los Artículos de la Categoría.
	 * 
	 * @param categoria Categoría.
	 * @param request HttpServletRequest.
	 * @param request HttpServletResponse.
	 * @param model Model.
	 * @return Redirige a la página de Artículos por Categoría.
	 */ 
	public String articulosPorCategoria(Categoria categoria,
										HttpServletRequest request, 
										HttpServletResponse response,
										Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
	
		Long idCategoria = categoria.getId();
		
		model.addAttribute("categoria", categoria);
		
		//Recuperamos los Artículos de la Categoría.
		List<Articulo> articulos = articuloWebService.findByIdCategoria(clienteWrapper, idCategoria);
		model.addAttribute("articulos", articulos);
		
		//Mostramos la miga de pan.
		mostrarMiga(categoria, null, model);
		
		//Mostramos los más vendidos, más vistos y ventas relacionadas.
		mostrarArticulosMas(clienteWrapper, idCategoria, null, model);
		
		return "web/articulo/articulos-por-categoria";
	}	
	
	/**
	 * Muestra el detalle de un Artículo.
	 * 
	 * @param id Id del Artículo.
	 * @param request HttpServletRequest.
	 * @param model Model.
	 * @return Redirige a la página del detalle del Artículo.
	 */
	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public String buscar(@RequestParam("q") String texto, 
						 HttpServletRequest request,
						 HttpServletResponse response,
						 Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		migasPan.addMiga(iMigaPan);
		
		MigaPan rbMigaPan = new MigaPan();
		rbMigaPan.setTexto("Resultados de la búsqueda");
		migasPan.addMiga(rbMigaPan);
		
		model.addAttribute("migasPan", migasPan);
		
		List<Articulo> articulos = articuloWebService.findByNombre(clienteWrapper, texto);
		model.addAttribute("articulos", articulos);
		
		//Mostramos los más vendidos, más vistos y ventas relacionadas.
		mostrarArticulosMas(clienteWrapper, null, null, model);
		
		model.addAttribute("esBusqueda", true);
		
		return "web/articulo/articulos-por-categoria";
	}
	/**
	 * Muestra la miga de pan.
	 * 
	 * @param categoria Categoría
	 * @param articulo Artículo
	 * @param contexto Contexto de la Aplicación.
	 * @param model Model.
	 */
	private void mostrarMiga(Categoria categoria, Articulo articulo, Model model) {
	
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		if(categoria != null){
			
			String textoCategoria = categoria.getNombre();
			String urlAmigableCategoria = categoria.getUrlAmigable();
		
			MigaPan categoriaMigaPan = new MigaPan();
			categoriaMigaPan.setTexto(textoCategoria);
			
			if(articulo != null){
				categoriaMigaPan.setEnlace(urlAmigableCategoria);
			}
			
			migasPan.addMiga(categoriaMigaPan);
		}
		
		if(articulo != null){
			
			String textoArticulo = articulo.getNombre();
			//String urlAmigableArticulo = articulo.getUrlAmigable();
			
			MigaPan aMigaPan = new MigaPan();
			aMigaPan.setTexto(textoArticulo);
			//aMigaPan.setEnlace(urlAmigableArticulo);
			aMigaPan.setUltima(true);
			
			migasPan.addMiga(aMigaPan);
			
		}
		
		model.addAttribute("migasPan", migasPan);
	}
	
	/**
	 * Carga los más vendidos y más vistos y los mete en el Model.
	 * @param clienteWrapper ClienteWrapper clienteWrapper.
	 * @param idCategoria Id de la Categoría.
	 * @param idArticulo Id del Artículo.
	 * @param model Model.
	 */
	private void mostrarArticulosMas(ClienteWrapper clienteWrapper, Long idCategoria, Long idArticulo, Model model){
		
		//Recuperamos los más vendidos.
		List<Articulo> articulosMasVendidos = articuloWebService.findMasVendidos(clienteWrapper, idCategoria, Constantes.NUMERO_MAS_VENDIDOS);
		model.addAttribute("articulosMasVendidos", articulosMasVendidos);
		
		//Recuperamos los más vistos.
		List<Articulo> articulosMasVistos = articuloWebService.findMasVistos(clienteWrapper, idCategoria,  Constantes.NUMERO_MAS_VISTOS);
		model.addAttribute("articulosMasVistos", articulosMasVistos);
		
		//Sólo se muestra en el detalle del Artículo.
		if(idArticulo != null){
			
			//Recuperamos las ventas relacionadas.
			List<Articulo> articulosVentasRelacionadas = articuloWebService.findVentasRelacionadas(clienteWrapper,  idArticulo, Constantes.NUMERO_VENTAS_RELACIONADAS);
			model.addAttribute("articulosVentasRelacionadas", articulosVentasRelacionadas);
		}
	}
}
