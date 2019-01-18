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

import es.cgarcia.decocina.admin.buscador.FormaEnvioBuscador;
import es.cgarcia.decocina.admin.buscador.ZonaBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.form.FormaEnvioForm;
import es.cgarcia.decocina.admin.service.IFormaEnvioAdminService;
import es.cgarcia.decocina.web.model.FormaEnvio;


/**
 * Controller de la Forma de Envío.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class FormaEnvioController extends AdminControllerBase{
	
	/**
	 * Texto de la miga de pan.
	 */
	private final String miga = "Forma de Envío";
	
	/**
     * Servicio para las Formas de Envío de la parte Admin.
     */
    @Autowired
    private IFormaEnvioAdminService formaEnvioAdminService;
	
    /**
     * Busca las Formas de Envío por su nombre.
     * 
     * @param formaEnvioBuscador Buscador de Formas de Envío.
     * @return Página que muestra las Formas de Envío encontrados.
     */
	@RequestMapping(value = "/admin/consola/forma-envio/forma-envio-search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(FormaEnvioBuscador formaEnvioBuscador,
					   	 Model model){
		
		mostrarMiga(miga, model);
		
		formaEnvioBuscador.setHayBusqueda(true);
		
		List<FormaEnvio> formasEnvio = formaEnvioAdminService.findAllBusqueda(formaEnvioBuscador);
		
		model.addAttribute("formasEnvio", formasEnvio);
		
		return "admin/consola/forma-envio/ver-formas-envio";
	}
    
	/**
	 * Listado de Fabricantes.
	 * 
	 * @param formaEnvioBuscador Buscador de Formas de Envío.
	 * @param model Model.
	 * @return Página que muestra el listado de Formas de Envío.
	 */
	@RequestMapping(value = "/admin/consola/forma-envio/forma-envio-find", method = RequestMethod.GET)
	public String find(FormaEnvioBuscador formaEnvioBuscador,
					   Model model){
		
		mostrarMiga(miga, model);
		
		List<FormaEnvio> formasEnvio = formaEnvioAdminService.findAllBusqueda(formaEnvioBuscador);
		
		model.addAttribute("formasEnvio", formasEnvio);
		
		return "admin/consola/forma-envio/ver-formas-envio";
	}
	
	/**
	 * Listado de Zonas de Envío en un Popup.
	 * @param zonaBuscador Buscador de Zonas.
	 * @param id Id de la zona.
	 * @param model Model.
	 * @return Página que muestra el listado de Zonas de Envío en un Popup.
	 */
	@RequestMapping(value = "/admin/consola/forma-envio/forma-envio-find-popup", method = RequestMethod.GET)
	public String popup(ZonaBuscador zonaBuscador,
						@RequestParam("id") Long id, 
			   			Model model){
		
		model.addAttribute("id", id);
		
		//Recuperamos las Formas de Envío asigandas a la Zona y las Zonas de Pago asignadas a la Forma de Envío.
		List<FormaEnvio> formasEnvio = formaEnvioAdminService.findAllByIdZona(id);
		
		model.addAttribute("formasEnvio", formasEnvio);
		
		return "admin/consola/forma-envio/ver-formas-envio-popup";
	}
	
	/**
	 * Redirige a la página de modificación o creación de una Forma de Envío.
	 * 
	 * @param path Path.
	 * @param id Id de la Forma de Envío.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de una Forma de Envío.
	 */
	@RequestMapping(value = "/admin/consola/forma-envio/forma-envio-modify", method = RequestMethod.GET)
	public String modify(FormaEnvioBuscador formaEnvioBuscador,
						 @RequestParam(value = "id", required = false) Long id, 
						 Model model){
		
		mostrarMiga(miga, model);
		
		FormaEnvio formaEnvio = null;
		
		Boolean esCreacion = null;
		
		//Si no hay id es porque es una creación.
		if(id == null){
			
			esCreacion = true;
			
			formaEnvio = new FormaEnvio();
			
		}else{
			
			esCreacion = false;
			
			//Es una modificación de la Forma de Envío.
			formaEnvio = formaEnvioAdminService.findById(id);
		}
		
		
		FormaEnvioForm formaEnvioForm = new FormaEnvioForm(formaEnvio);
		
		formaEnvioForm.setEsCreacion(esCreacion);
		
		formaEnvioForm.setFormaEnvioBuscador(formaEnvioBuscador);
		
		model.addAttribute("formaEnvioForm", formaEnvioForm);
		
		model.addAttribute("formaEnvioBuscador", formaEnvioBuscador);
		
		return "admin/consola/forma-envio/editar-forma-envio";
	}
	
	/**
	 * Inserta una nueva Forma de Envío.
	 * 
	 * @param model Model.
	 * @param fabricanteForm Wrapper de la Forma de Envío con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de una Forma de Envío.
	 */
	@RequestMapping(value = "/admin/consola/forma-envio/forma-envio-insert", method = RequestMethod.POST)
	public String insert(@Valid FormaEnvioForm formaEnvioForm, 
						 BindingResult result,
						 HttpServletRequest request,
						 Model model ){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/forma-envio/editar-forma-envio";
		
		FormaEnvio formaEnvio = formaEnvioForm.getFormaEnvio();
		
		FormaEnvioBuscador formaEnvioBuscador = formaEnvioForm.getFormaEnvioBuscador();
		
		model.addAttribute("formaEnvioBuscador", formaEnvioBuscador);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		formaEnvioAdminService.insert(formaEnvio);
		
		formaEnvioForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Forma de Envío creada correctamente");
		
		return vista;
	}
	
	/**
	 * Modifica la Forma de Envío.
	 * 
	 * @param model Model.
	 * @param formaEnvioForm Wrapper de la Forma de Envío con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de una Forma de Envío.
	 */
	@RequestMapping(value = "/admin/consola/forma-envio/forma-envio-update", method = RequestMethod.POST)
	public String update(@Valid FormaEnvioForm formaEnvioForm,
					 	 BindingResult result, 
						 HttpServletRequest request,
						 Model model){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/forma-envio/editar-forma-envio";

		FormaEnvio formaEnvio = formaEnvioForm.getFormaEnvio();
		
		FormaEnvioBuscador formaEnvioBuscador = formaEnvioForm.getFormaEnvioBuscador();
		
		model.addAttribute("formaEnvioBuscador", formaEnvioBuscador);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		formaEnvioAdminService.update(formaEnvio);
		
		formaEnvioForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Forma de Envío modificada correctamente");
		
		return vista;
	}
	
	/**
	 * Elimina la Forma de Envío.
	 * 
	 * @param formaEnvioForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página del listado de Formas de Envío.
	 */
	@RequestMapping(value = "/admin/consola/forma-envio/forma-envio-delete", method = RequestMethod.POST)
	public String delete(@Valid FormaEnvioForm formaEnvioForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		mostrarMiga(miga, model);
		
		FormaEnvio formaEnvio = formaEnvioForm.getFormaEnvio();
		
		Long id = formaEnvio.getId();
		
		try{

			redirectAttributes.addAttribute("id", id);
			
			FormaEnvioBuscador formaEnvioBuscador = formaEnvioForm.getFormaEnvioBuscador();
			
			redirectAttributes.addFlashAttribute("formaEnvioBuscador", formaEnvioBuscador);
			
			formaEnvioAdminService.delete(formaEnvio);
		
			redirectAttributes.addFlashAttribute("mensaje", "Forma de Envío eliminada correctamente");
			
			String vista =  "redirect:/admin/consola/forma-envio/forma-envio-find";
			
			if(formaEnvioBuscador.getHayBusqueda()){
				
				vista = "redirect:/admin/consola/forma-envio/forma-envio-search";
			}
			
			return vista;
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);	
			
			formaEnvioForm.setEsCreacion(false);
		}
		
		model.addAttribute("id", id);
		
		FormaEnvioBuscador formaEnvioBuscador = formaEnvioForm.getFormaEnvioBuscador();
		
		model.addAttribute("formaEnvioBuscador", formaEnvioBuscador);
		
		return "admin/consola/forma-envio/editar-forma-envio";
	}
}
