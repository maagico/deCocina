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

import es.cgarcia.decocina.admin.buscador.ImpuestoBuscador;
import es.cgarcia.decocina.admin.buscador.ZonaBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.form.ImpuestoForm;
import es.cgarcia.decocina.admin.service.IImpuestoAdminService;
import es.cgarcia.decocina.web.model.Impuesto;


/**
 * Controller del Impuesto.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class ImpuestoController extends AdminControllerBase{
	
	/**
	 * Texto de la miga de pan.
	 */
	private final String miga = "Impuestos";
	
	/**
     * Servicio para los Impuestos de la parte Admin.
     */
    @Autowired
    private IImpuestoAdminService impuestoAdminService;
	
    /**
     * Busca los impuestos por su nombre.
     * 
     * @param impuestoBuscador Buscador de impuestos.
     * @return Página que muestra los impuestos encontrados.
     */
	@RequestMapping(value = "/admin/consola/impuesto/impuesto-search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(ImpuestoBuscador impuestoBuscador,
					   	 Model model){
		
		mostrarMiga(miga, model);
		
		impuestoBuscador.setHayBusqueda(true);
		
		List<Impuesto> impuestos = impuestoAdminService.findAllBusqueda(impuestoBuscador);
		
		model.addAttribute("impuestos", impuestos);
		
		return "admin/consola/impuesto/ver-impuestos";
	}
    
	/**
	 * Listado de Impuestos.
	 * 
	 * @param impuestoBuscador Buscador de Impuestos.
	 * @param model Model.
	 * @return Página que muestra el listado de Impuestos.
	 */
	@RequestMapping(value = "/admin/consola/impuesto/impuesto-find", method = RequestMethod.GET)
	public String find(ImpuestoBuscador impuestoBuscador,
					   Model model){
		
		mostrarMiga(miga, model);
		
		List<Impuesto> impuestos = impuestoAdminService.findAllBusqueda(impuestoBuscador);
		
		model.addAttribute("impuestos", impuestos);
		
		return "admin/consola/impuesto/ver-impuestos";
	}
	
	/**
	 * Listado de Impuestos en un Popup.
	 * @param zonaBuscador Buscador de Zonas.
	 * @param id Id de la zona.
	 * @param model Model.
	 * @return Página que muestra el listado de Impuestos en un Popup.
	 */
	@RequestMapping(value = "/admin/consola/impuesto/impuesto-find-popup", method = RequestMethod.GET)
	public String popup(ZonaBuscador zonaBuscador,
						@RequestParam("id") Long id, 
			   			Model model){
		
		model.addAttribute("id", id);
		
		List<Impuesto> impuestos = impuestoAdminService.findAllByIdZona(id);
		
		model.addAttribute("impuestos", impuestos);
		
		return "admin/consola/impuesto/ver-impuestos-popup";
	}
	
	/**
	 * Redirige a la página de modificación o creación un Impuesto.
	 * 
	 * @param id Id del impuesto.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de un Impuesto.
	 */
	@RequestMapping(value = "/admin/consola/impuesto/impuesto-modify", method = RequestMethod.GET)
	public String modify(ImpuestoBuscador impuestoBuscador,
						 @RequestParam(value = "id", required = false) Long id, 
						 Model model){
		
		mostrarMiga(miga, model);
		
		Impuesto impuesto = null;
		
		Boolean esCreacion = null;
		
		//Si no hay id es porque es una creación.
		if(id == null){
			
			esCreacion = true;
			
			impuesto = new Impuesto();
			
		}else{
			
			esCreacion = false;
			
			//Es una modificación del Impuesto.
			impuesto = impuestoAdminService.findById(id);
		}
		
		
		ImpuestoForm impuestoForm = new ImpuestoForm(impuesto);
		
		impuestoForm.setEsCreacion(esCreacion);
		
		impuestoForm.setImpuestoBuscador(impuestoBuscador);
		
		model.addAttribute("impuestoForm", impuestoForm);
		
		model.addAttribute("impuestoBuscador", impuestoBuscador);
		
		return "admin/consola/impuesto/editar-impuesto";
	}
	
	/**
	 * Inserta una nuevo Impuesto.
	 * 
	 * @param model Model.
	 * @param impuestoForm Wrapper del Impuesto con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Impuesto.
	 */
	@RequestMapping(value = "/admin/consola/impuesto/impuesto-insert", method = RequestMethod.POST)
	public String insert(@Valid ImpuestoForm impuestoForm, 
						 BindingResult result,
						 HttpServletRequest request,
						 Model model){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/impuesto/editar-impuesto";
		
		Impuesto impuesto = impuestoForm.getImpuesto();
		
		ImpuestoBuscador impuestoBuscador = impuestoForm.getImpuestoBuscador();
		
		model.addAttribute("impuestoBuscador", impuestoBuscador);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		impuestoAdminService.insert(impuesto);
		
		impuestoForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Impuesto creado correctamente");
		
		return vista;
	}
	
	/**
	 * Modifica el Impuesto.
	 * 
	 * @param model Model.
	 * @param impuestoForm Wrapper del Impuesto con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Impuesto.
	 */
	@RequestMapping(value = "/admin/consola/impuesto/impuesto-update", method = RequestMethod.POST)
	public String update(@Valid ImpuestoForm impuestoForm,
					 	 BindingResult result, 
						 HttpServletRequest request,
						 Model model){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/impuesto/editar-impuesto";

		Impuesto impuesto = impuestoForm.getImpuesto();
		
		ImpuestoBuscador impuestoBuscador = impuestoForm.getImpuestoBuscador();
		
		model.addAttribute("impuestoBuscador", impuestoBuscador);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		impuestoAdminService.update(impuesto);
		
		impuestoForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Impuesto modificado correctamente");
		
		return vista;
	}
	
	/**
	 * Elimina el Impuesto.
	 * 
	 * @param impuestoForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página del listado de Impuestos.
	 */
	@RequestMapping(value = "/admin/consola/impuesto/impuesto-delete", method = RequestMethod.POST)
	public String delete(@Valid ImpuestoForm impuestoForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		mostrarMiga(miga, model);
		
		Impuesto impuesto = impuestoForm.getImpuesto();
		
		Long id = impuesto.getId();
		
		try{

			redirectAttributes.addAttribute("id", id);
			
			ImpuestoBuscador impuestoBuscador = impuestoForm.getImpuestoBuscador();
			
			redirectAttributes.addFlashAttribute("impuestoBuscador", impuestoBuscador);
			
			impuestoAdminService.delete(impuesto);
		
			redirectAttributes.addFlashAttribute("mensaje", "Impuesto eliminado correctamente");
			
			
			String vista =  "redirect:/admin/consola/impuesto/impuesto-find";
			
			if(impuestoBuscador.getHayBusqueda()){
				
				vista = "redirect:/admin/consola/impuesto/impuesto-search";
			}
			
			return vista;
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);	
			
			impuestoForm.setEsCreacion(false);
		}
		
		model.addAttribute("id", id);
		
		ImpuestoBuscador impuestoBuscador = impuestoForm.getImpuestoBuscador();
		
		model.addAttribute("impuestoBuscador", impuestoBuscador);
		
		return "admin/consola/impuesto/editar-impuesto";
	}
}
