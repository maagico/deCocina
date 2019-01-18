package es.cgarcia.decocina.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cgarcia.decocina.admin.buscador.FabricanteBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.form.FabricanteForm;
import es.cgarcia.decocina.admin.service.IFabricanteAdminService;
import es.cgarcia.decocina.web.model.Fabricante;


/**
 * Controller del Fabricante.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class FabricanteController extends AdminControllerBase{
	
	/**
	 * Texto de la miga de pan.
	 */
	private final String miga = "Fabricantes";
	
	/**
     * Servicio para los Fabricantes de la parte Admin.
     */
    @Autowired
    private IFabricanteAdminService fabricanteAdminService;
	
    /**
     * Busca los Fabricantes por su nombre.
     * 
     * @param fabricanteBuscador Buscador de Fabricantes.
     * @return Página que muestra los Fabricantes encontrados.
     */
	@RequestMapping(value = "/admin/consola/fabricante/fabricante-search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(FabricanteBuscador fabricanteBuscador,
					   	 Model model){
		
		mostrarMiga(miga, model);
		
		fabricanteBuscador.setHayBusqueda(true);
		
		List<Fabricante> fabricantes = fabricanteAdminService.findAllBusqueda(fabricanteBuscador);
		
		model.addAttribute("fabricantes", fabricantes);
		
		return "admin/consola/fabricante/ver-fabricantes";
	}
    
	/**
	 * Listado de Fabricantes.
	 * 
	 * @param fabricanteBuscador Buscador de Fabricantes.
	 * @param model Model.
	 * @return Página que muestra el listado de Fabricantes.
	 */
	@RequestMapping(value = "/admin/consola/fabricante/fabricante-find", method = RequestMethod.GET)
	public String find(FabricanteBuscador fabricanteBuscador,
					   Model model){
		
		mostrarMiga(miga, model);
		
		List<Fabricante> fabricantes = fabricanteAdminService.findAllBusqueda(fabricanteBuscador);
		
		model.addAttribute("fabricantes", fabricantes);
		
		return "admin/consola/fabricante/ver-fabricantes";
	}
	
	/**
	 * Redirige a la página de modificación o creación de un Fabricante.
	 * 
	 * @param path Path.
	 * @param id Id del fabricante.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de un Fabricante.
	 */
	@RequestMapping(value = "/admin/consola/fabricante/fabricante-modify", method = RequestMethod.GET)
	public String modify(FabricanteBuscador fabricanteBuscador,
						 @RequestParam(value = "id", required = false) Long id, 
						 Model model){
		
		mostrarMiga(miga, model);
		
		Fabricante fabricante = null;
		
		Boolean esCreacion = null;
		
		//Si no hay id es porque es una creación.
		if(id == null){
			
			esCreacion = true;
			
			fabricante = new Fabricante();
			
		}else{
			
			esCreacion = false;
			
			//Es una modificación del Fabricante.
			fabricante = fabricanteAdminService.findById(id);
		}
		
		
		FabricanteForm fabricanteForm = new FabricanteForm(fabricante);
		
		fabricanteForm.setEsCreacion(esCreacion);
		
		fabricanteForm.setFabricanteBuscador(fabricanteBuscador);
		
		model.addAttribute("fabricanteForm", fabricanteForm);
		
		model.addAttribute("fabricanteBuscador", fabricanteBuscador);
		
		return "admin/consola/fabricante/editar-fabricante";
	}
	
	/**
	 * Inserta un nuevo Fabricante.
	 * 
	 * @param model Model.
	 * @param fabricanteForm Wrapper del Fabricante con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Fabricante.
	 */
	@RequestMapping(value = "/admin/consola/fabricante/fabricante-insert", method = RequestMethod.POST)
	public String insert(@Valid FabricanteForm fabricanteForm, 
						 BindingResult result,
						 HttpServletRequest request,
						 Model model ){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/fabricante/editar-fabricante";
		
		Fabricante fabricante = fabricanteForm.getFabricante();
		
		FabricanteBuscador fabricanteBuscador = fabricanteForm.getFabricanteBuscador();
		
		model.addAttribute("fabricanteBuscador", fabricanteBuscador);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		fabricanteAdminService.insert(fabricante);
		
		fabricanteForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Fabricante creado correctamente");
		
		return vista;
	}
	
	/**
	 * Modifica el Fabricante.
	 * 
	 * @param model Model.
	 * @param fabricanteForm Wrapper del Fabricante con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Fabricante.
	 */
	@RequestMapping(value = "/admin/consola/fabricante/fabricante-update", method = RequestMethod.POST)
	public String update(@Valid FabricanteForm fabricanteForm,
					 	 BindingResult result, 
						 HttpServletRequest request,
						 Model model){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/fabricante/editar-fabricante";

		Fabricante fabricante = fabricanteForm.getFabricante();
		
		FabricanteBuscador fabricanteBuscador = fabricanteForm.getFabricanteBuscador();
		
		model.addAttribute("fabricanteBuscador", fabricanteBuscador);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		fabricanteAdminService.update(fabricante);
		
		fabricanteForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Fabricante modificado correctamente");
		
		return vista;
	}
	
	/**
	 * Elimina el Fabricante.
	 * 
	 * @param fabricanteForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página del listado de Fabricantes.
	 */
	@RequestMapping(value = "/admin/consola/fabricante/fabricante-delete", method = RequestMethod.POST)
	public String delete(@Valid FabricanteForm fabricanteForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		mostrarMiga(miga, model);
		
		Fabricante fabricante = fabricanteForm.getFabricante();
		
		Long id = fabricante.getId();
		
		try{

			redirectAttributes.addAttribute("id", id);
			
			FabricanteBuscador fabricanteBuscador = fabricanteForm.getFabricanteBuscador();
			
			redirectAttributes.addFlashAttribute("fabricanteBuscador", fabricanteBuscador);
			
			fabricanteAdminService.delete(fabricante);
		
			redirectAttributes.addFlashAttribute("mensaje", "Fabricante eliminado correctamente");
			
			
			String vista =  "redirect:/admin/consola/fabricante/fabricante-find";
			
			if(fabricanteBuscador.getHayBusqueda()){
				
				vista = "redirect:/admin/consola/fabricante/fabricante-search";
			}
			
			return vista;
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);	
			
			fabricanteForm.setEsCreacion(false);
		}
		
		model.addAttribute("id", id);
		
		FabricanteBuscador fabricanteBuscador = fabricanteForm.getFabricanteBuscador();
		
		model.addAttribute("fabricanteBuscador", fabricanteBuscador);
		
		return "admin/consola/fabricante/editar-fabricante";
	}
}
