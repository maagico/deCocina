package es.cgarcia.decocina.admin.controller;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cgarcia.decocina.admin.buscador.FormaPagoBuscador;
import es.cgarcia.decocina.admin.buscador.ZonaBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.form.FormaPagoForm;
import es.cgarcia.decocina.admin.service.IFormaPagoAdminService;
import es.cgarcia.decocina.admin.validator.FormaPagoValidator;
import es.cgarcia.decocina.web.model.FormaPago;


/**
 * Controller de la Forma de Pago.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class FormaPagoController extends AdminControllerBase{
	
	/**
	 * Texto de la miga de pan.
	 */
	private final String miga = "Forma de Pago";
	
	/**
     * Servicio para las Formas de Pago de la parte Admin.
     */
    @Autowired
    private IFormaPagoAdminService formaPagoAdminService;
	
    /**
     * Validador.
     */
    @Autowired
    private FormaPagoValidator formaPagoValidator;
    
    /**
     * Busca las Formas de Pago por su nombre.
     * 
     * @param formaPagoBuscador Buscador de Formas de Pago.
     * @return Página que muestra las Formas de Pago encontradas.
     */
	@RequestMapping(value = "/admin/consola/forma-pago/forma-pago-search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(FormaPagoBuscador formaPagoBuscador,
					   	 Model model){
		
		mostrarMiga(miga, model);
		
		formaPagoBuscador.setHayBusqueda(true);
		
		List<FormaPago> formasPago = formaPagoAdminService.findAllBusqueda(formaPagoBuscador);
		
		model.addAttribute("formasPago", formasPago);
		
		return "admin/consola/forma-pago/ver-formas-pago";
	}
    
	/**
	 * Listado de Formas de Pago.
	 * 
	 * @param formaPagoBuscador Buscador de Formas de Pago.
	 * @param model Model.
	 * @return Página que muestra el listado de Formas de Pago.
	 */
	@RequestMapping(value = "/admin/consola/forma-pago/forma-pago-find", method = RequestMethod.GET)
	public String find(FormaPagoBuscador formaPagoBuscador,
					   Model model){
		
		mostrarMiga(miga, model);
		
		List<FormaPago> formasPago = formaPagoAdminService.findAllBusqueda(formaPagoBuscador);
		
		model.addAttribute("formasPago", formasPago);
		
		return "admin/consola/forma-pago/ver-formas-pago";
	}
	
	/**
	 * Listado de Formas de Pago en un Popup.
	 * @param zonaBuscador Buscador de Zonas.
	 * @param id Id de la zona.
	 * @param idFormaEnvio Id de la Forma de Envío.
	 * @param model Model.
	 * @return Página que muestra el listado de Zonas de Pago en un Popup.
	 */
	@RequestMapping(value = "/admin/consola/forma-pago/forma-pago-find-popup", method = RequestMethod.GET)
	public String popup(ZonaBuscador zonaBuscador,
						@RequestParam("id") Long id, 
						@RequestParam("forma_envio_id") Long idFormaEnvio, 
			   			Model model){
		
		model.addAttribute("id", id);
		model.addAttribute("idFormaEnvio", idFormaEnvio);
		
		//Recuperamos las Formas de Envío asigandas a la Zona y las Zonas de Pago asignadas a la Forma de Envío.
		List<FormaPago> formasPago = formaPagoAdminService.findAllByIdZonaAndIdFormaEnvio(id, idFormaEnvio);
		
		model.addAttribute("formasPago", formasPago);
		
		return "admin/consola/forma-pago/ver-formas-pago-popup";
	}
	
	/**
	 * Redirige a la página de modificación o creación de una Forma de Pago.
	 * 
	 * @param path Path.
	 * @param id Id de la Forma de Pago.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de una Forma de Pago.
	 */
	@RequestMapping(value = "/admin/consola/forma-pago/forma-pago-modify", method = RequestMethod.GET)
	public String modify(FormaPagoBuscador formaPagoBuscador,
						 @RequestParam(value = "id", required = false) Long id, 
						 Model model){
		
		mostrarMiga(miga, model);
		
		FormaPago formaPago = null;
		
		Boolean esCreacion = null;
		
		//Si no hay id es porque es una creación.
		if(id == null){
			
			esCreacion = true;
			
			formaPago = new FormaPago();
			
		}else{
			
			esCreacion = false;
			
			//Es una modificación de la Forma de Pago.
			formaPago = formaPagoAdminService.findById(id);
		}
		
		FormaPagoForm formaPagoForm = new FormaPagoForm(formaPago);
		
		formaPagoForm.setEsCreacion(esCreacion);
		
		formaPagoForm.setFormaPagoBuscador(formaPagoBuscador);
		
		model.addAttribute("formaPagoForm", formaPagoForm);
		
		model.addAttribute("formaPagoFormBuscador", formaPagoBuscador);
		
		return "admin/consola/forma-pago/editar-forma-pago";
	}
	
	/**
	 * Inserta una nueva Forma de Pago.
	 * 
	 * @param model Model.
	 * @param formaPagoForm Wrapper de la Forma de Pago con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de una Forma de Pago.
	 */
	@RequestMapping(value = "/admin/consola/forma-pago/forma-pago-insert", method = RequestMethod.POST)
	public String insert(@Valid FormaPagoForm formaPagoForm, 
						 BindingResult result,
						 HttpServletRequest request,
						 Model model ){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/forma-pago/editar-forma-pago";
		
		FormaPago formaPago = formaPagoForm.getFormaPago();
		
		FormaPagoBuscador formaPagoBuscador = formaPagoForm.getFormaPagoBuscador();
		
		model.addAttribute("formaPagoBuscador", formaPagoBuscador);
		
		BindException bindException = new BindException(result);
		formaPagoValidator.validate(formaPagoForm, bindException);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		formaPagoAdminService.insert(formaPago);
		
		formaPagoForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Forma de Pago creada correctamente");
		
		return vista;
	}
	
	/**
	 * Modifica el Fabricante.
	 * 
	 * @param model Model.
	 * @param formaPagoForm Wrapper de la Forma de Pago con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de una Forma de Pago.
	 */
	@RequestMapping(value = "/admin/consola/forma-pago/forma-pago-update", method = RequestMethod.POST)
	public String update(@Valid FormaPagoForm formaPagoForm, 
					 	 BindingResult result, 
						 HttpServletRequest request,
						 Model model){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/forma-pago/editar-forma-pago";

		FormaPago formaPago = formaPagoForm.getFormaPago();
		
		FormaPagoBuscador formaPagoBuscador = formaPagoForm.getFormaPagoBuscador();
		
		model.addAttribute("formaPagoBuscador", formaPagoBuscador);
		
		BindException bindException = new BindException(result);
		formaPagoValidator.validate(formaPagoForm, bindException);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		formaPagoAdminService.update(formaPago);
		
		formaPagoForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Forma de Pago modificada correctamente");
		
		return vista;
	}
	
	/**
	 * Elimina el Fabricante.
	 * 
	 * @param formaPagoForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página del listado de Fabricantes.
	 */
	@RequestMapping(value = "/admin/consola/forma-pago/forma-pago-delete", method = RequestMethod.POST)
	public String delete(@Valid FormaPagoForm formaPagoForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		mostrarMiga(miga, model);
		
		FormaPago formaPago = formaPagoForm.getFormaPago();
		
		Long id = formaPago.getId();
		
		try{

			redirectAttributes.addAttribute("id", id);
			
			FormaPagoBuscador formaPagoBuscador = formaPagoForm.getFormaPagoBuscador();
			
			redirectAttributes.addFlashAttribute("formaPagoBuscador", formaPagoBuscador);
			
			formaPagoAdminService.delete(formaPago);
		
			redirectAttributes.addFlashAttribute("mensaje", "Forma de Pago eliminada correctamente");
			
			
			String vista =  "redirect:/admin/consola/forma-pago/forma-pago-find";
			
			if(formaPagoBuscador.getHayBusqueda()){
				
				vista = "redirect:/admin/consola/forma-pago/forma-pago-search";
			}
			
			return vista;
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);	
			
			formaPagoForm.setEsCreacion(false);
		}
		
		model.addAttribute("id", id);
		
		FormaPagoBuscador formaPagoBuscador = formaPagoForm.getFormaPagoBuscador();
		
		model.addAttribute("formaPagoBuscador", formaPagoBuscador);
		
		return "admin/consola/forma-pago/editar-forma-pago";
	}
}
