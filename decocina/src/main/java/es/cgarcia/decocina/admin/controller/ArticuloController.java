package es.cgarcia.decocina.admin.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.admin.form.ArticuloForm;
import es.cgarcia.decocina.admin.service.IArticuloAdminService;
import es.cgarcia.decocina.admin.service.IFabricanteAdminService;
import es.cgarcia.decocina.admin.service.IImpuestoAdminService;
import es.cgarcia.decocina.admin.util.CategoriaUtils;
import es.cgarcia.decocina.admin.validator.ArticuloValidator;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Categoria;
import es.cgarcia.decocina.web.model.Fabricante;
import es.cgarcia.decocina.web.model.Impuesto;
import es.cgarcia.decocina.web.util.Constantes;


/**
 * Controller del Artículo.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class ArticuloController extends AdminControllerBase{
	
	/**
     * Servicio para los Artículos de la parte Admin.
     */
    @Autowired
    private IArticuloAdminService articuloAdminService;
    
    /**
     * Servicio para los Fabricantes de la parte Admin.
     */
    @Autowired
    private IFabricanteAdminService fabricanteAdminService;
    
    /**
     * Servicio para los Impuestos de la parte Admin.
     */
    @Autowired
    private IImpuestoAdminService impuestoAdminService;
    
    /**
     * Validador.
     */
    @Autowired
    private ArticuloValidator articuloValidator;
    
	/**
	 * Redirige a la página de modificación o creación de un Artículo.
	 * 
	 * @param path Path.
	 * @param id Id del Artículo.
	 * @param idCategoria Id de la categoría.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de un Artículo.
	 */
	@RequestMapping(value = "/admin/consola/articulo/articulo-modify", method = RequestMethod.GET)
	public String modify(CategoriaArticuloBuscador categoriaArticuloBuscador,
						 @RequestParam(value = "id" ,required = false) Long id,
						 @RequestParam(value = "path") String path,
						 @RequestParam(value = "cat_id", required = false) Long idCategoria,
						 Model model){
		
		mostrarMigasPan(model, path);
		
		Articulo articulo = null;
		
		Boolean esCreacion = null;
		
		String pathVolver = null;
		
		//Si no hay id es porque es una creación.
		if(id == null){
			
			esCreacion = true;
			
			pathVolver = path;
			
			articulo = new Articulo();
			
			Categoria categoria = new Categoria();
			categoria.setId(idCategoria);
			
			articulo.setFecha(Calendar.getInstance());
			
			articulo.setCategoria(categoria);
			
			articulo.setOrden(1);
			articulo.setVisitas(0L);
			
		}else{
			
			esCreacion = false;
			
			pathVolver = CategoriaUtils.quitarId(path);
			
			//Es una modificación del Artículo.
			articulo = articuloAdminService.findById(id);
		}
		
		ArticuloForm articuloForm = new ArticuloForm(articulo);
		
		articulo = articuloForm.getArticulo();
		
		articuloForm.setPath(path);
		articuloForm.setPathVolver(pathVolver);
		articuloForm.setEsCreacion(esCreacion);
		
		articuloForm.setCategoriaArticuloBuscador(categoriaArticuloBuscador);
		
		model.addAttribute("articuloForm", articuloForm);
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		return "admin/consola/articulo/editar-articulo";
	}
	
	/**
	 * Inserta un nuevo Artículo.
	 * 
	 * @param model Model.
	 * @param articuloForm Wrapper del Artículo con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de creación de un Artículo.
	 */
	@RequestMapping(value = "/admin/consola/articulo/articulo-insert", method = RequestMethod.POST)
	public String insert(@Valid ArticuloForm articuloForm, 
						 BindingResult result,
						 HttpServletRequest request,
						 Model model){
		
		String path = articuloForm.getPath();
		
		mostrarMigasPan(model, path);
		
		String vista = "admin/consola/articulo/editar-articulo";
		
		Articulo articulo = articuloForm.getArticulo();
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = articuloForm.getCategoriaArticuloBuscador();
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		BindException bindException = new BindException(result);
        articuloValidator.validate(articuloForm, bindException);
		
		if(result.hasErrors()){
			
			return vista;
		}
	
		articulo.setVentas(0l);
		articuloAdminService.insert(articulo);
		
		articuloForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Artículo creado correctamente");
				
		return vista;
	}
	
	/**
	 * Modifica el Artículo.
	 * 
	 * @param model Model.
	 * @param articuloForm Wrapper del Artículo con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Artículo.
	 */
	@RequestMapping(value = "/admin/consola/articulo/articulo-update", method = RequestMethod.POST)
	public String update(@Valid ArticuloForm articuloForm, 
						 BindingResult result, 
						 HttpServletRequest request,
						 Model model){
		
		String path = articuloForm.getPath();
		
		mostrarMigasPan(model, path);
		
		String vista = "admin/consola/articulo/editar-articulo";
		
		Articulo articulo = articuloForm.getArticulo();
		
		Calendar ahora = Calendar.getInstance();
		articulo.setFechaModificacion(ahora);
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = articuloForm.getCategoriaArticuloBuscador();
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		BindException bindException = new BindException(result);
        articuloValidator.validate(articuloForm, bindException);
        
		if(result.hasErrors()){
			
			return vista;
		}
		
		try{
			
			articuloAdminService.update(articuloForm);
			model.addAttribute("mensaje", "Artículo modificado correctamente");
			
		}catch(DeCocinaAdminRuntimeException e){
			
			model.addAttribute("mensajeError", "Se ha producido un error grabando la imagen");
		}
		
		articuloForm.setEsCreacion(false);
		
		return vista;
	}
	
	/**
	 * Elimina el Artículo.
	 * 
	 * @param impuestoForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página del listado de Impuestos.
	 */
	@RequestMapping(value = "/admin/consola/articulo/articulo-delete", method = RequestMethod.POST)
	public String delete(@Valid ArticuloForm articuloForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		Articulo articulo = articuloForm.getArticulo();
		
		Long id = articulo.getId();
		
		try{

			redirectAttributes.addAttribute("id", id);
			
			CategoriaArticuloBuscador categoriaArticuloBuscador = articuloForm.getCategoriaArticuloBuscador();
			
			redirectAttributes.addFlashAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
			
			String rutaPrivada = request.getSession().getServletContext().getRealPath(Constantes.RUTA_PRIVADA_IMAGENES);
			
			articuloAdminService.delete(articulo, rutaPrivada);
		
			redirectAttributes.addAttribute("path", categoriaArticuloBuscador.getPath());
			redirectAttributes.addFlashAttribute("mensaje", "Artículo eliminado correctamente");
			
			String vista =  "redirect:/admin/consola/categoria/categoria-find?path={path}";
			
			if(categoriaArticuloBuscador.getHayBusqueda()){
				
				vista = "redirect:/admin/consola/categoria/categoria-search";
			}
			
			if(categoriaArticuloBuscador.getDesdeInicio()){
				
				vista = "redirect:/admin/consola/inicio";
			}
			
			return vista;
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);	
			
			articuloForm.setEsCreacion(false);
		}
		
		model.addAttribute("id", id);
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = articuloForm.getCategoriaArticuloBuscador();
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		return "admin/consola/articulo/editar-articulo";
	}
	
	/**
	 * Devuelve los todos los Fabricantes.
	 * 
	 * @return Listado de Fabricantes.
	 */
	@ModelAttribute(value = "fabricantes")
	public List<Fabricante> findAllFabricantes(){
		
		List<Fabricante> fabricantes = fabricanteAdminService.findAll();
		
		return fabricantes;
	}
	
	/**
	 * Devuelve los todos los Impuestos.
	 * 
	 * @return Listado de Impuestos.
	 */
	@ModelAttribute(value = "impuestos")
	public List<Impuesto> findAllImpuestos(){
		
		List<Impuesto> impuestos = impuestoAdminService.findAll(true, null);
		
		return impuestos;
	}
}
