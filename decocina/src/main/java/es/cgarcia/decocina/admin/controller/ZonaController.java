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

import es.cgarcia.decocina.admin.buscador.ZonaBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.form.ZonaForm;
import es.cgarcia.decocina.admin.service.IFormaEnvioFormaPagoAdminService;
import es.cgarcia.decocina.admin.service.IZonaAdminService;
import es.cgarcia.decocina.admin.service.IZonaFormaEnvioAdminService;
import es.cgarcia.decocina.admin.service.IZonaImpuestoAdminService;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;
import es.cgarcia.decocina.web.model.FormaPago;
import es.cgarcia.decocina.web.model.Impuesto;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;
import es.cgarcia.decocina.web.model.ZonaImpuesto;


/**
 * Controller de la Zona.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class ZonaController extends AdminControllerBase{
	
	/**
	 * Texto de la miga de pan.
	 */
	private final String miga = "Zonas";

	/**
     * Servicio para las Zonas de la parte Admin.
     */
    @Autowired
    private IZonaAdminService zonaAdminService;
    
    /**
     * Servicio para la ZonaImpuesto de la parte Admin.
     */
    @Autowired
    private IZonaImpuestoAdminService zonaImpuestoAdminService;
    
    /**
     * Servicio para la ZonaImpuesto de la parte Admin.
     */
    @Autowired
    private IZonaFormaEnvioAdminService zonaFormaEnvioAdminService;
    
    /**
     * Servicio para la ZonaImpuesto de la parte Admin.
     */
    @Autowired
    private IFormaEnvioFormaPagoAdminService formaEnvioFormaPagoAdminService;
    
    /**
     * Busca las Zonas por su nombre.
     * 
     * @param zonaBuscador Buscador de Zonas.
     * @return Página que muestra las Zonas encontradas.
     */
	@RequestMapping(value = "/admin/consola/zona/zona-search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(ZonaBuscador zonaBuscador,
					   	 Model model){
		
		mostrarMiga(miga, model);
		
		zonaBuscador.setHayBusqueda(true);
		
		List<Zona> zonas = zonaAdminService.findAllBusqueda(zonaBuscador);
		
		model.addAttribute("zonas", zonas);
		
		return "admin/consola/zona/ver-zonas";
	}
    
	/**
	 * Listado de Zonas.
	 * 
	 * @param zonaBuscador Buscador de Zonas.
	 * @param model Model.
	 * @return Página que muestra el listado de Zonas.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-find", method = RequestMethod.GET)
	public String find(ZonaBuscador zonaBuscador,
					   Model model){
		
		mostrarMiga(miga, model);
		
		List<Zona> zonas = zonaAdminService.findAllBusqueda(zonaBuscador);
		
		model.addAttribute("zonas", zonas);
		
		return "admin/consola/zona/ver-zonas";
	}
	
	/**
	 * Listado de Impuestos en un Popup.
	 * @param zonaBuscador Buscador de Zonas.
	 * @param id Id de la zona.
	 * @param model Model.
	 * @return Página que muestra el listado de Impuestos en un Popup.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-find-popup", method = RequestMethod.GET)
	public String popup(ZonaBuscador zonaBuscador,
						@RequestParam(value = "id", required = false) Long id, 
			   			Model model){
		
		
		model.addAttribute("id", id);
		
		List<Zona> zonas = zonaAdminService.findAllBusqueda(zonaBuscador);
		
		model.addAttribute("zonas", zonas);
		
		model.addAttribute("zonaBuscador", zonaBuscador);
		
		return "admin/consola/zona/ver-zonas-popup";
	}
	
	/**
	 * Redirige a la página de modificación o creación de una Zona.
	 * 
	 * @param zonaBuscador Buscador de la Zona.
	 * @param id Id de la Zona.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-modify", method = RequestMethod.GET)
	public String modify(ZonaBuscador zonaBuscador,
						 @RequestParam(value = "id", required = false) Long id, 
						 Model model){
		
		mostrarMiga(miga, model);
		
		Zona zona = null;
		
		Boolean esCreacion = null;
		
		//Si no hay id es porque es una creación.
		if(id == null){
			
			esCreacion = true;
			
			zona = new Zona();
			
		}else{
			
			esCreacion = false;
			
			//Es una modificación de la Zona.
			zona = zonaAdminService.findById(id);
		}
		
		ZonaForm zonaForm = new ZonaForm(zona);
		
		zonaForm.setEsCreacion(esCreacion);
		
		zonaForm.setZonaBuscador(zonaBuscador);
		
		model.addAttribute("zonaForm", zonaForm);
		
		model.addAttribute("zonaBuscador", zonaBuscador);
		
		return "admin/consola/zona/editar-zona";
	}
	
	/**
	 * Inserta una nueva Zona.
	 * 
	 * @param model Model.
	 * @param zonaForm Wrapper de la Zona con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-insert", method = RequestMethod.POST)
	public String insert(@Valid ZonaForm zonaForm, 
						 BindingResult result,
						 HttpServletRequest request,
						 Model model ){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/zona/editar-zona";
		
		Zona zona = zonaForm.getZona();
		
		ZonaBuscador zonaBuscador = zonaForm.getZonaBuscador();
		
		model.addAttribute("zonaBuscador", zonaBuscador);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		Boolean heredarFormas = zona.getHeredarFormas();
		
		if(heredarFormas == null){
			
			zona.setHeredarFormas(false);
		}
		
		zonaAdminService.insert(zona);
		
		zonaForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Zona creada correctamente");
		
		return vista;
	}
	
	/**
	 * Relaciona un Impuesto con una Zona.
	 * 
	 * @param idImpuesto Id del impuesto.
	 * @param idZona Id de la Zona.
	 * @param zonaBuscador Buscador de la Zona.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirige a la Página de Modificación de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-impuesto-insert", method = RequestMethod.GET)
	public String insertImpuesto(@RequestParam("impuesto_id") Long idImpuesto,
								 @RequestParam("zona_id") Long idZona,
								 ZonaBuscador zonaBuscador, 
						 	     RedirectAttributes redirectAttributes,
						 	     Model model){
		
		Zona zona = new Zona();
		zona.setId(idZona);
		
		Impuesto impuesto = new Impuesto();
		impuesto.setId(idImpuesto);
		
		ZonaImpuesto zonaImpuesto = new ZonaImpuesto();
		zonaImpuesto.setZona(zona);
		zonaImpuesto.setImpuesto(impuesto);
		zonaImpuesto.setPrioridad(0);
		
		zonaImpuestoAdminService.insert(zonaImpuesto);
		
		redirectAttributes.addAttribute("idZona", idZona);
		redirectAttributes.addFlashAttribute("mensaje", "Impuesto añadido correctamente");
		redirectAttributes.addFlashAttribute("zonaBuscador", zonaBuscador);
		
		
		return "redirect:/admin/consola/zona/zona-modify?id={idZona}";
	}
	
	/**
	 * Relaciona una Forma de Envío con una Zona.
	 * 
	 * @param idImpuesto Id del impuesto.
	 * @param idZona Id de la Zona.
	 * @param zonaBuscador Buscador de la Zona.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirige a la Página de Modificación de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-forma-envio-insert", method = RequestMethod.GET)
	public String insertFormaEnvio(@RequestParam("forma_envio_id") Long idFormaEnvio,
								   @RequestParam("zona_id") Long idZona,
								   ZonaBuscador zonaBuscador, 
						 	       RedirectAttributes redirectAttributes,
						 	       Model model){
		
		Zona zona = new Zona();
		zona.setId(idZona);
		
		FormaEnvio formaEnvio = new FormaEnvio();
		formaEnvio.setId(idFormaEnvio);
		
		ZonaFormaEnvio zonaEnvio = new ZonaFormaEnvio();
		zonaEnvio.setZona(zona);
		zonaEnvio.setFormaEnvio(formaEnvio);
		zonaEnvio.setOrden(0L);
		
		zonaFormaEnvioAdminService.insert(zonaEnvio);
		
		redirectAttributes.addAttribute("idZona", idZona);
		redirectAttributes.addFlashAttribute("mensaje", "Forma de Envío añadida correctamente");
		redirectAttributes.addFlashAttribute("zonaBuscador", zonaBuscador);
		
		return "redirect:/admin/consola/zona/zona-modify?id={idZona}";
	}

	/**
	 * Borra una Forma de Envío y sus Formas de Pago deuna Zona en concreto.
	 * 
	 * @param id Id de la Forma de Envío.
	 * @param idZona Id de la Zona.
	 * @param zonaBuscador Buscador de la Zona.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirige a la Página de Modificación de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-forma-envio-delete", method = RequestMethod.GET)
	public String deleteFormaEnvio(@RequestParam("id") Long id,
								  @RequestParam("zona_id") Long idZona,
								  ZonaBuscador zonaBuscador, 
						 	      RedirectAttributes redirectAttributes,
						 	      Model model){
		
		formaEnvioFormaPagoAdminService.deleteFormaEnvio(id, idZona);
		
		redirectAttributes.addAttribute("idZona", idZona);
		redirectAttributes.addFlashAttribute("mensaje", "Forma de Pago borrada correctamente");
		redirectAttributes.addFlashAttribute("zonaBuscador", zonaBuscador);
		
		return "redirect:/admin/consola/zona/zona-modify?id={idZona}";
	}
	
	/**
	 * Relaciona una Forma de Pago con una Forma de Envío y una Zona.
	 * 
	 * @param idZona Id de la Zona.
	 * @param idFormaEnvio Id de la Forma de Envío.
	 * @param idFormaPago Id de la Forma de Pago.
	 * @param zonaBuscador ZonaBuscador.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirige a la Página de Modificación de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-forma-pago-insert", method = RequestMethod.GET)
	public String insertFormaPago(@RequestParam("zona_id") Long idZona,
								  @RequestParam("forma_envio_id") Long idFormaEnvio,
								  @RequestParam("forma_pago_id") Long idFormaPago,
								  ZonaBuscador zonaBuscador, 
						 	      RedirectAttributes redirectAttributes,
						 	      Model model){
		
		Zona zona = new Zona();
		zona.setId(idZona);
		
		FormaEnvio formaEnvio = new FormaEnvio();
		formaEnvio.setId(idFormaEnvio);
		
		FormaPago formaPago = new FormaPago();
		formaPago.setId(idFormaPago);
		
		FormaEnvioFormaPago formaEnvioFormaPago = new FormaEnvioFormaPago();
		formaEnvioFormaPago.setZona(zona);
		formaEnvioFormaPago.setFormaEnvio(formaEnvio);
		formaEnvioFormaPago.setFormaPago(formaPago);
		formaEnvioFormaPago.setOrden(0);
		
		formaEnvioFormaPagoAdminService.insert(formaEnvioFormaPago);
		
		redirectAttributes.addAttribute("idZona", idZona);
		redirectAttributes.addFlashAttribute("mensaje", "Forma de Pago añadida correctamente");
		redirectAttributes.addFlashAttribute("zonaBuscador", zonaBuscador);
		
		return "redirect:/admin/consola/zona/zona-modify?id={idZona}";
	}
	
	/**
	 * Borra una Forma de Pago que estaba relacionada con un Envío y una Zona.
	 * 
	 * @param id Id de la Forma de Pago.
	 * @param idZona Id de la Zona.
	 * @param zonaBuscador Buscador de la Zona.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirige a la Página de Modificación de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-forma-pago-delete", method = RequestMethod.GET)
	public String deleteFormaPago(@RequestParam("id") Long id,
								  @RequestParam("zona_id") Long idZona,
								  ZonaBuscador zonaBuscador, 
						 	      RedirectAttributes redirectAttributes,
						 	      Model model){
		
		FormaEnvioFormaPago formaEnvioFormaPago = new FormaEnvioFormaPago();
		formaEnvioFormaPago.setId(id);
		
		formaEnvioFormaPagoAdminService.delete(formaEnvioFormaPago);
		
		redirectAttributes.addAttribute("idZona", idZona);
		redirectAttributes.addFlashAttribute("mensaje", "Forma de Pago borrada correctamente");
		redirectAttributes.addFlashAttribute("zonaBuscador", zonaBuscador);
		
		return "redirect:/admin/consola/zona/zona-modify?id={idZona}";
	}
	
	/**
	 * Relaciona un Impuesto con una Zona.
	 * 
	 * @param id Id de la zona hija.
	 * @param idZona Id de la Zona que será la Padre.
	 * @param zonaBuscador Buscador de la Zona.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirige a la Página de Modificación de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-padre-insert", method = RequestMethod.GET)
	public String insertPadre(@RequestParam("id") Long idZonaHija,
							  @RequestParam(value = "zona_id", required = false) Long idZonaPadre,
							  ZonaBuscador zonaBuscador, 
						 	  RedirectAttributes redirectAttributes,
						 	  Model model){
		
		
		zonaAdminService.asignarPadre(idZonaHija, idZonaPadre);
		
		redirectAttributes.addAttribute("id", idZonaPadre);
		redirectAttributes.addAttribute("idZona", idZonaHija);
		
		String mensaje = "Padre asignado correctamente";
		
		if(idZonaPadre == null){
			
			mensaje = "Padre desasignado correctamente";
		}
		
		redirectAttributes.addFlashAttribute("mensaje", mensaje);
		redirectAttributes.addFlashAttribute("zonaBuscador", zonaBuscador);
		
		return "redirect:/admin/consola/zona/zona-modify?id={idZona}";
	}
	
	/**
	 * Borra un Impuesto que estaba relacionado con una Zona.
	 * 
	 * @param idImpuesto Id del impuesto.
	 * @param idZona Id de la Zona.
	 * @param zonaBuscador Buscador de la Zona.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirige a la Página de Modificación de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-impuesto-delete", method = RequestMethod.GET)
	public String deleteImpuesto(@RequestParam("id") Long id,
								 @RequestParam("zona_id") Long idZona,
								 ZonaBuscador zonaBuscador, 
						 	     RedirectAttributes redirectAttributes,
						 	     Model model){
		
		ZonaImpuesto zonaImpuesto = new ZonaImpuesto();
		zonaImpuesto.setId(id);
		
		zonaImpuestoAdminService.delete(zonaImpuesto);
		
		redirectAttributes.addAttribute("idZona", idZona);
		redirectAttributes.addFlashAttribute("mensaje", "Impuesto borrado correctamente");
		redirectAttributes.addFlashAttribute("zonaBuscador", zonaBuscador);
		
		return "redirect:/admin/consola/zona/zona-modify?id={idZona}";
	}
	
	/**
	 * Modifica la Zona.
	 * 
	 * @param model Model.
	 * @param zonaForm Wrapper de la Zona con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de una Zona.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-update", method = RequestMethod.POST)
	public String update(@Valid ZonaForm zonaForm,
					 	 BindingResult result, 
						 HttpServletRequest request,
						 Model model){
		
		String vista = "admin/consola/zona/editar-zona";
		
		mostrarMiga(miga, model);
		
		Zona zona = zonaForm.getZona();
		
		Long id = zona.getId();
		
		//Cargamos los Impuestos asignados a esta Zona.
		List<ZonaImpuesto> zonasImpuesto = zonaImpuestoAdminService.findByIdZona(id);
		model.addAttribute("zonasImpuesto", zonasImpuesto);
		
		ZonaBuscador zonaBuscador = zonaForm.getZonaBuscador();
		
		model.addAttribute("zonaBuscador", zonaBuscador);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		Boolean heredarFormas = zona.getHeredarFormas();
		
		if(heredarFormas == null){
			
			zona.setHeredarFormas(false);
		}
		
		zonaAdminService.update(zona);
		
		//Cargamos de nuevo para que cargue el Padre.
		Zona zonaBD = zonaAdminService.findById(id);
		zonaForm.setZona(zonaBD);
		zonaForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Zona modificada correctamente");
		
		return vista;
	}
	
	/**
	 * Elimina la Zona.
	 * 
	 * @param zonaForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página del listado de Zonas.
	 */
	@RequestMapping(value = "/admin/consola/zona/zona-delete", method = RequestMethod.POST)
	public String delete(@Valid ZonaForm zonaForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		mostrarMiga(miga, model);
		
		Zona zona = zonaForm.getZona();
		
		Long id = zona.getId();
		
		try{

			redirectAttributes.addAttribute("id", id);
			
			ZonaBuscador zonaBuscador = zonaForm.getZonaBuscador();
			
			redirectAttributes.addFlashAttribute("zonaBuscador", zonaBuscador);
			
			zonaAdminService.delete(zona);
		
			redirectAttributes.addFlashAttribute("mensaje", "Zona eliminada correctamente");
			
			String vista =  "redirect:/admin/consola/zona/zona-find";
			
			if(zonaBuscador.getHayBusqueda()){
				
				vista = "redirect:/admin/consola/zona/zona-search";
			}
			
			return vista;
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);
		
			zona = zonaAdminService.findById(id);
			
			model.addAttribute("zona", zona);
			
			ZonaBuscador zonaBuscador = zonaForm.getZonaBuscador();
			zonaForm.setZonaBuscador(zonaBuscador);
			zonaForm.setEsCreacion(false);
			zonaForm.setZona(zona);
			
			model.addAttribute("zonaBuscador", zonaBuscador);
			model.addAttribute("zonaForm", zonaForm);
		
			model.addAttribute("id", id);
		}
		
		return "admin/consola/zona/editar-zona";
	}
}
