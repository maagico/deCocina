package es.cgarcia.decocina.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.form.CategoriaForm;
import es.cgarcia.decocina.admin.service.IArticuloAdminService;
import es.cgarcia.decocina.admin.service.ICategoriaAdminService;
import es.cgarcia.decocina.admin.util.CategoriaUtils;
import es.cgarcia.decocina.admin.validator.BuscadorCategoriaArticuloValidator;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Categoria;
import es.cgarcia.decocina.web.session.MenuSession;


/**
 * Controller de las Categorías.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class CategoriaController extends AdminControllerBase{

	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(CategoriaController.class);    
	
	/**
     * Servicio para las Categorías de la parte Admin.
     */
    @Autowired
    private ICategoriaAdminService categoriaAdminService;
    
    /**
     * Servicio para los Artículos de la parte Admin.
     */
    @Autowired
    private IArticuloAdminService articuloAdminService;
	
    /**
     * Validador del buscador de las Categorías y Artículos.
     */
    @Autowired
    private BuscadorCategoriaArticuloValidator buscadorCategoriaArticuloValidator;
    
    /**
     * Contiene el Menú con todas las Categorías. 
     * Se debe actualizar cuando se actualiza una Categoría.
     */
    @Autowired
    private MenuSession menuSession;
    
    /**
     * Busca las categorías y artículos por su nombre.
     * 
     * @param categoriaArticuloBuscador Buscador de categorías y artículos.
     * @param path Path.
     * @param result BindingResult.
     * @param model Model.
     * @return Página que muestra las categorías y artículos encontrados.
     */
	@RequestMapping(value = "/admin/consola/categoria/categoria-search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(@Valid CategoriaArticuloBuscador categoriaArticuloBuscador,
						 BindingResult result,		   	 
						 @RequestParam("path") String path,
					   	 Model model){
		
		mostrarMigasPan(model, path);
		
		String vista = "admin/consola/categoria/ver-categorias";
		
		BindException bindException = new BindException(result);
		buscadorCategoriaArticuloValidator.validate(categoriaArticuloBuscador, bindException);
		
		if(result.hasErrors()){
			
			//Se carga en el Model los datos necesarios para hacer el return.
			find(categoriaArticuloBuscador, path, model);
			
			return vista;
		}
		
		model.addAttribute("esRaiz", true);
		model.addAttribute("path", "-1");
		
		categoriaArticuloBuscador.setPath("-1");
		categoriaArticuloBuscador.setHayBusqueda(true);
		categoriaArticuloBuscador.setDesdeRaiz(true);
		
		List<Categoria> categorias = categoriaAdminService.findAllByBusqueda(categoriaArticuloBuscador);
		model.addAttribute("categorias", categorias);
		
		List<Articulo> articulos = articuloAdminService.findAllByBusqueda(categoriaArticuloBuscador);
		model.addAttribute("articulos", articulos);
		
		model.addAttribute("busqueda", true);
		
		return vista;
	}
    
	/**
	 * Listado de categorías por path, del que se recupera el id de la Categoría padre.
	 * 
	 * @param path Path.
	 * @param model Model.
	 * @return Página que muestra el listado de categorías.
	 */
	@RequestMapping(value = "/admin/consola/categoria/categoria-find", method = RequestMethod.GET)
	public String find(CategoriaArticuloBuscador categoriaArticuloBuscador,
					   @RequestParam("path") String path, 
					   Model model){
		
		mostrarMigasPan(model, path);
		
		Long lnIdPadre = CategoriaUtils.getId(path);
		
		logger.debug("Recuperando categorías con id padre de categoría {}.", lnIdPadre);
		
		List<Categoria> categorias = categoriaAdminService.findByIdPadre(lnIdPadre);
		
		model.addAttribute("categorias", categorias);
		
		model.addAttribute("path", path);
		
		String pathVolver = CategoriaUtils.quitarId(path);
		
		model.addAttribute("pathVolver", pathVolver);
		
		String vista = "admin/consola/categoria/ver-categorias";
		
		if(lnIdPadre == null){
			
			model.addAttribute("esRaiz", true);
			categoriaArticuloBuscador.setPath("-1");
			
		}else{
			
			categoriaArticuloBuscador.setPath(pathVolver);
		}
		
		int profundidad = categoriaAdminService.getProfundidad(lnIdPadre);
		
		if(profundidad == 3){
			
			List<Articulo> articulos = articuloAdminService.findByIdCategoria(lnIdPadre);
			
			Long idCategoria = CategoriaUtils.getId(path);
			
			model.addAttribute("idCategoria", idCategoria);
			
			model.addAttribute("articulos", articulos);
			
			model.addAttribute("profundidadArticulo", true);
			
			vista = "admin/consola/articulo/ver-articulos";
		}
		
		model.addAttribute("profundidad", profundidad);
		
		categoriaArticuloBuscador.setDesdeRaiz(false);
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		return vista;
	}

	/**
	 * Redirige a la página de modificación o creación una Categoría.
	 * 
	 * @param path Path.
	 * @param idCategoria Id de la categoría.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de una categoría.
	 */
	@RequestMapping(value = "/admin/consola/categoria/categoria-modify", method = RequestMethod.GET)
	public String modify(CategoriaArticuloBuscador categoriaArticuloBuscador,
						 @RequestParam("path") String path, 
						 @RequestParam(value = "cat_id", required = false) Long idCategoria, 
						 Model model){
		
		mostrarMigasPan(model, path);
		
		Categoria categoria = null;
		
		String pathVolver = null;
		
		Boolean esCreacion = null;
		
		Boolean activa = null;
		
		//Si no hay categoría es porque es una creación.
		if(idCategoria == null){
			
			esCreacion = true;
			
			pathVolver = path;
			
			categoria = new Categoria();
			
			Long idPadre = CategoriaUtils.getPadre(path);
			
			categoria.setIdPadre(idPadre);
			
		}else{
			
			esCreacion = false;
			
			pathVolver = CategoriaUtils.quitarId(path);
			
			//Es una modificación de la categoría.
			categoria = categoriaAdminService.findById(idCategoria);
			
			activa = categoria.getActiva();
			
			Long idPadre = CategoriaUtils.getPadre(path);
			
			categoria.setIdPadre(idPadre);
			
			//Recuperamos las Categorías padre.
			List<Categoria> categoriasPadre = categoriaAdminService.findByIdPadre(null);
			model.addAttribute("categoriasPadre", categoriasPadre);
		}
		
		int profundidad = CategoriaUtils.getProfundidad(path);
		
		if(profundidad == 3){
			
			model.addAttribute("profundidadArticulo", true);
		}
		
		model.addAttribute("profundidad", profundidad);
		
		CategoriaForm categoriaForm = new CategoriaForm(categoria);
		categoriaForm.setPath(path);
		categoriaForm.setPathVolver(pathVolver);
		categoriaForm.setEsCreacion(esCreacion);
		
		categoriaArticuloBuscador.setPath(pathVolver);
		
		categoriaForm.setCategoriaArticuloBuscador(categoriaArticuloBuscador);
		categoriaForm.setActiva(activa);
		
		model.addAttribute("categoriaForm", categoriaForm);
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		return "admin/consola/categoria/editar-categoria";
	}
	
	/**
	 * Inserta una nueva Categoría.
	 * 
	 * @param model Model.
	 * @param categoriaForm Wrapper de la categoría con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de una categoría.
	 */
	@RequestMapping(value = "/admin/consola/categoria/categoria-insert", method = RequestMethod.POST)
	public String insert(@Valid CategoriaForm categoriaForm, 
						 BindingResult result,
						 HttpServletRequest request,
						 Model model ){
		
		String path = categoriaForm.getPath();
		
		mostrarMigasPan(model, path);
		
		String vista = "admin/consola/categoria/editar-categoria";
		
		Categoria categoria = categoriaForm.getCategoria();
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = categoriaForm.getCategoriaArticuloBuscador();
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		if(result.hasErrors()){
			
			return vista;
		}

		categoriaAdminService.insert(categoria);
		
		//Recargamos de nuevo el Menú que contiene las Categorías.
		menuSession.init();
		
		categoriaForm.setEsCreacion(false);
		
		Long lnIdPadre = CategoriaUtils.getPadre(path);
		
		int profundidad = categoriaAdminService.getProfundidad(lnIdPadre);
		//Profundidad más el id de la categoría que se acaba de crear.
		model.addAttribute("profundidad", profundidad + 1);
		
		//Recuperamos las Categorías padre.
		List<Categoria> categoriasPadre = categoriaAdminService.findByIdPadre(null);
		model.addAttribute("categoriasPadre", categoriasPadre);
		
		model.addAttribute("mensaje", "Categoría creada correctamente");
				
		return vista;
	}
	
	/**
	 * Modifica la Categoría.
	 * 
	 * @param model Model.
	 * @param categoriaForm Wrapper de la categoría con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de una categoría.
	 */
	@RequestMapping(value = "/admin/consola/categoria/categoria-update", method = RequestMethod.POST)
	public String update(@Valid CategoriaForm categoriaForm,
					 	 BindingResult result, 
						 HttpServletRequest request,
						 Model model){
		
		String path = categoriaForm.getPath();
		
		mostrarMigasPan(model, path);
		
		String vista = "admin/consola/categoria/editar-categoria";

		Categoria categoria = categoriaForm.getCategoria();
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = categoriaForm.getCategoriaArticuloBuscador();
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		//Recuperamos las Categorías padre.
		List<Categoria> categoriasPadre = categoriaAdminService.findByIdPadre(null);
		model.addAttribute("categoriasPadre", categoriasPadre);
		
		Long lnIdPadre = CategoriaUtils.getPadre(path);
		
		int profundidad = categoriaAdminService.getProfundidad(lnIdPadre);
		
		model.addAttribute("profundidad", profundidad);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		categoriaAdminService.update(categoria);
		
		//Recargamos el nuevo el Menú que contiene las Categorías.
		menuSession.init();
		
		categoriaForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Categoría modificada correctamente");
		
		return vista;
	}
	
	/**
	 * Elimina la categoría.
	 * 
	 * @param categoriaForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página de la categoría padre.
	 */
	@RequestMapping(value = "/admin/consola/categoria/categoria-delete", method = RequestMethod.POST)
	public String delete(@Valid CategoriaForm categoriaForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		String path = categoriaForm.getPath();
		
		mostrarMigasPan(model, path);
	
		Categoria categoria = categoriaForm.getCategoria();
		
		String pathVolver = categoriaForm.getPathVolver();
		
		Long idCategoria = categoria.getId();
		
		try{
			
			redirectAttributes.addAttribute("path", pathVolver);
			redirectAttributes.addAttribute("idCategoria", idCategoria);
			
			CategoriaArticuloBuscador categoriaArticuloBuscador = categoriaForm.getCategoriaArticuloBuscador();
			
			redirectAttributes.addFlashAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
			
			categoriaAdminService.delete(idCategoria);
			
			//Recargamos el nuevo el Menú que contiene las Categorías.
			menuSession.init();
			
			redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada correctamente");
			
			return "redirect:/admin/consola/categoria/categoria-find?path={path}";
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);	
			
			categoriaForm.setEsCreacion(false);
		}
		
		model.addAttribute("path", pathVolver);
		model.addAttribute("idCategoria", idCategoria);
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = categoriaForm.getCategoriaArticuloBuscador();
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		return "admin/consola/categoria/editar-categoria";
	}
}
