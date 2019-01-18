package es.cgarcia.decocina.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.admin.form.ImagenForm;
import es.cgarcia.decocina.admin.service.IArticuloAdminService;
import es.cgarcia.decocina.admin.service.IImagenAdminService;
import es.cgarcia.decocina.admin.validator.ImagenValidator;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Imagen;
import es.cgarcia.decocina.web.util.Constantes;


/**
 * Controller de la Imagen.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class ImagenController extends AdminControllerBase{
	
	/**
     * Servicio para los Fabricantes de la parte Admin.
     */
    @Autowired
    private IImagenAdminService imagenAdminService;
    
    /**
     * Servicio para los Artículos de la parte Admin.
     */
    @Autowired
    private IArticuloAdminService articuloAdminService;
    
    /**
     * Validador.
     */
    @Autowired
    private ImagenValidator imagenValidator;
    
	/**
	 * Listado de Imágenes.
	 * 
	 * @param categoriaArticuloBuscador Buscador de categorías y artículos.
	 * @param model Model.
	 * @return Página que muestra el listado de imágenes.
	 */
	@RequestMapping(value = "/admin/consola/imagen/imagen-find", method = RequestMethod.GET)
	public String find(CategoriaArticuloBuscador categoriaArticuloBuscador,
					   @RequestParam("art_id") Long idArticulo,
					   @RequestParam("cat_id") Long idCategoria,
					   @RequestParam("path") String path, 
					   Model model){
		
		mostrarMigasPan(model, path);

		Articulo articulo = articuloAdminService.findById(idArticulo);
		model.addAttribute("articulo", articulo);
		
		Imagen imagen = imagenAdminService.findPrincipalByIdArticulo(idArticulo, Constantes.TIPO_IMAGEN_G);
		
		model.addAttribute("imagen", imagen);
		
		model.addAttribute("idArticulo", idArticulo);
		model.addAttribute("idCategoria", idCategoria);
		
		return "admin/consola/imagen/editar-imagen";
	}
	
	/**
	 * Redirige a la página de modificación o creación de una Imagen.
	 * 
	 * @param path Path.
	 * @param id Id de la imagen.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de una imagen.
	 */
	@RequestMapping(value = "/admin/consola/imagen/imagen-modify", method = RequestMethod.GET)
	public String modify(CategoriaArticuloBuscador categoriaArticuloBuscador,
						 @RequestParam(value = "id", required = false) Long id, 
						 @RequestParam("art_id") Long idArticulo,
						 @RequestParam("cat_id") Long idCategoria,
						 @RequestParam("path") String path, 
						 Model model){
		
		mostrarMigasPan(model, path);
		
		Imagen imagen = null;
		
		Boolean esCreacion = null;
		
		//Si no hay id es porque es una creación.
		if(id == null){
			
			esCreacion = true;
			
			imagen = new Imagen();
			
		}else{
			
			esCreacion = false;
			
			//Es una modificación del Fabricante.
			imagen = imagenAdminService.findById(id);
		}
		
		ImagenForm imagenForm = new ImagenForm(imagen);
		
		imagenForm.setEsCreacion(esCreacion);
		
		imagenForm.setCategoriaArticuloBuscador(categoriaArticuloBuscador);
		
		imagenForm.setIdArticulo(idArticulo);
		imagenForm.setIdCategoria(idCategoria);
		
		model.addAttribute("imagenForm", imagenForm);
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		Articulo articulo = articuloAdminService.findById(idArticulo);
		model.addAttribute("articulo", articulo);
		
		return "admin/consola/imagen/editar-imagen";
	}
	
	/**
	 * Inserta una nueva Imagen.
	 * 
	 * @param model Model.
	 * @param imagenForm Wrapper de la Imagen con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Fabricante.
	 */
	@RequestMapping(value = "/admin/consola/imagen/imagen-insert", method = RequestMethod.POST)
	public String insert(@Valid ImagenForm imagenForm, 
						 BindingResult result,
						 @RequestParam("mfImagen") MultipartFile mfImagen,
						 HttpServletRequest request,
						 Model model ){
		
		String vista = "admin/consola/imagen/editar-imagen";
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = imagenForm.getCategoriaArticuloBuscador();
				
		String path = categoriaArticuloBuscador.getPath();
		
		mostrarMigasPan(model, path);
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		BindException bindException = new BindException(result);
        imagenValidator.validate(imagenForm, bindException);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		imagenForm.setMfImagen(mfImagen);
		
		String rutaPublica = Constantes.RUTA_PUBLICA_IMAGENES;
		String rutaPrivada = Constantes.RUTA_PRIVADA_IMAGENES;
		
		String rutaGrabacion = request.getSession().getServletContext().getRealPath(rutaPrivada);
		
		imagenAdminService.insert(imagenForm, rutaPublica, rutaGrabacion);
	
		imagenForm.setEsCreacion(false);
		
		model.addAttribute("imagenForm", imagenForm);
		
		model.addAttribute("mensaje", "Imagen creada correctamente");
		
		return vista;
	}
	
	/**
	 * Modifica la Imagen.
	 * 
	 * @param model Model.
	 * @param imagenForm Wrapper de la Imagen con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Fabricante.
	 */
	@RequestMapping(value = "/admin/consola/imagen/imagen-update", method = RequestMethod.POST)
	public String update(@Valid ImagenForm imagenForm,
					 	 BindingResult result, 
					 	 @RequestParam("mfImagen") MultipartFile mfImagen,
						 HttpServletRequest request,
						 Model model){
		
		String vista = "admin/consola/imagen/editar-imagen";
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = imagenForm.getCategoriaArticuloBuscador();
		
		String path = categoriaArticuloBuscador.getPath();
		
		mostrarMigasPan(model, path);
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		BindException bindException = new BindException(result);
        imagenValidator.validate(imagenForm, bindException);
		
        Long idArticulo = imagenForm.getIdArticulo();
        
        Articulo articulo = articuloAdminService.findById(idArticulo);
		model.addAttribute("articulo", articulo);
        
		if(result.hasErrors()){
			
			//Enviamos la imagen anterior.
			Imagen imagen = imagenAdminService.findPrincipalByIdArticulo(idArticulo, Constantes.TIPO_IMAGEN_G);
			imagenForm.setImagen(imagen);
			
			return vista;
		}
		
		imagenForm.setMfImagen(mfImagen);
		
		String rutaPublica = Constantes.RUTA_PUBLICA_IMAGENES;
		String rutaPrivada = Constantes.RUTA_PRIVADA_IMAGENES;

		String rutaGrabacion = request.getSession().getServletContext().getRealPath(rutaPrivada);
		
		imagenAdminService.update(imagenForm, rutaPublica, rutaGrabacion);
		
		imagenForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Imagen modificada correctamente");
		
		return vista;
	}
	
	/**
	 * Elimina el Fabricante.
	 * 
	 * @param imagenForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página del listado de Fabricantes.
	 */
	@RequestMapping(value = "/admin/consola/imagen/imagen-delete", method = RequestMethod.POST)
	public String delete(@Valid ImagenForm imagenForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		Imagen imagen = imagenForm.getImagen();
		
		Long id = imagen.getId();
		
		String rutaPrivada = Constantes.RUTA_PRIVADA_IMAGENES;
		String rutaGrabacion = request.getSession().getServletContext().getRealPath(rutaPrivada);
		
		imagenAdminService.delete(imagenForm, rutaGrabacion);
		
		imagenForm.setEsCreacion(true);
		
		model.addAttribute("id", id);
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = imagenForm.getCategoriaArticuloBuscador();
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		
		return "admin/consola/imagen/editar-imagen";
	}
}
