package es.cgarcia.decocina.admin.controller;

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

import es.cgarcia.decocina.admin.buscador.ClienteBuscador;
import es.cgarcia.decocina.admin.buscador.PedidoBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.form.ClienteForm;
import es.cgarcia.decocina.admin.service.IClienteAdminService;
import es.cgarcia.decocina.admin.service.IZonaAdminService;
import es.cgarcia.decocina.admin.validator.ClienteValidator;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Zona;


/**
 * Controller del Cliente.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class ClienteController extends AdminControllerBase{
	
	/**
	 * Texto de la miga de pan.
	 */
	private final String miga = "Clientes";
    
    /**
     * Servicio para los Clientes de la parte Admin.
     */
    @Autowired
    private IClienteAdminService clienteAdminService;
    
    /**
     * Servicio para las Zonas de la parte Admin.
     */
    @Autowired
    private IZonaAdminService zonaAdminService;
	
    /**
     * Validador del Cliente.
     */
    @Autowired
    private ClienteValidator clienteValidator;
    /**
     * Busca los Clientes por su nombre.
     * 
     * @param clienteBuscador Buscador de Clientes.
     * @return Página que muestra los Clientes encontrados.
     */
	@RequestMapping(value = "/admin/consola/cliente/cliente-search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(ClienteBuscador clienteBuscador,
					   	 Model model){
		
		mostrarMiga(miga, model);
		
		clienteBuscador.setHayBusqueda(true);
		
		List<Cliente> clientes = clienteAdminService.findAllBusqueda(clienteBuscador);
		
		model.addAttribute("clientes", clientes);
		
		return "admin/consola/cliente/ver-clientes";
	}
    
	/**
	 * Listado de Clientes.
	 * 
	 * @param clienteBuscador Buscador de Clientes.
	 * @param model Model.
	 * @return Página que muestra el listado de Clientes.
	 */
	@RequestMapping(value = "/admin/consola/cliente/cliente-find", method = { RequestMethod.GET, RequestMethod.POST })
	public String find(ClienteBuscador clienteBuscador,
					   Model model){
		
		mostrarMiga(miga, model);
		
		List<Cliente> clientes = clienteAdminService.findAllBusqueda(clienteBuscador);
		
		model.addAttribute("clientes", clientes);
		
		return "admin/consola/cliente/ver-clientes";
	}
	
	/**
	 * Redirige a la página de modificación o creación de un Cliente.
	 * 
	 * @param path Path.
	 * @param id Id del cliente.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de un Cliente.
	 */
	@RequestMapping(value = "/admin/consola/cliente/cliente-modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(ClienteBuscador clienteBuscador,
						 @RequestParam(value = "id", required = false) Long id, 
						 Model model){
		
		mostrarMiga(miga, model);
		
		Cliente cliente = null;
		
		Boolean esCreacion = null;
		
		//Si no hay id es porque es una creación.
		if(id == null){
			
			esCreacion = true;
			
			cliente = new Cliente();
			
		}else{
			
			esCreacion = false;
			
			//Es una modificación del Cliente.
			cliente = clienteAdminService.findById(id);
		}
		
		PedidoBuscador pedidoBuscador = new PedidoBuscador();
		pedidoBuscador.setIdCliente(String.valueOf(id));
		
		model.addAttribute("pedidoBuscador", pedidoBuscador);
		
		ClienteForm clienteForm = new ClienteForm(cliente);
		
		clienteForm.setEsCreacion(esCreacion);
		
		clienteForm.setClienteBuscador(clienteBuscador);
		
		model.addAttribute("clienteForm", clienteForm);
		
		model.addAttribute("clienteBuscador", clienteBuscador);
		
		return "admin/consola/cliente/editar-cliente";
	}
	
	/**
	 * Modifica el Cliente.
	 * 
	 * @param model Model.
	 * @param clienteForm Wrapper del Cliente con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Cliente.
	 */
	@RequestMapping(value = "/admin/consola/cliente/cliente-update", method = RequestMethod.POST)
	public String update(@Valid ClienteForm clienteForm,
					 	 BindingResult result, 
						 HttpServletRequest request,
						 Model model){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/cliente/editar-cliente";

		Cliente cliente = clienteForm.getCliente();
		
		ClienteBuscador clienteBuscador = clienteForm.getClienteBuscador();
		
		model.addAttribute("clienteBuscador", clienteBuscador);
		
		PedidoBuscador pedidoBuscador = new PedidoBuscador();
		pedidoBuscador.setIdCliente(String.valueOf(cliente.getId()));
		
		model.addAttribute("pedidoBuscador", pedidoBuscador);
		
		BindException bindException = new BindException(result);
		clienteValidator.validate(clienteForm, bindException);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		clienteAdminService.update(cliente);
		
		clienteForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Cliente modificado correctamente");
		
		return vista;
	}
	
	/**
	 * Elimina al Cliente y sus dependencias.
	 * 
	 * @param clienteForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página del listado de Clientes.
	 */
	@RequestMapping(value = "/admin/consola/cliente/cliente-delete", method = RequestMethod.POST)
	public String delete(@Valid ClienteForm clienteForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		mostrarMiga(miga, model);
		
		Cliente cliente = clienteForm.getCliente();
		
		Long id = cliente.getId();
		
		try{

			redirectAttributes.addAttribute("id", id);
			
			ClienteBuscador clienteBuscador = clienteForm.getClienteBuscador();
			
			redirectAttributes.addFlashAttribute("clienteBuscador", clienteBuscador);
			
			clienteAdminService.delete(cliente);
		
			redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado correctamente");
			
			
			String vista =  "redirect:/admin/consola/cliente/cliente-find";
			
			if(clienteBuscador.getHayBusqueda()){
				
				vista = "redirect:/admin/consola/cliente/cliente-search";
			}
			
			return vista;
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);	
			
			clienteForm.setEsCreacion(false);
		}
		
		model.addAttribute("id", id);
		
		ClienteBuscador clienteBuscador = clienteForm.getClienteBuscador();
		
		model.addAttribute("clienteBuscador", clienteBuscador);
		
		return "admin/consola/cliente/editar-cliente";
	}
	
	/**
	 * Devuelve todas las Provincias de España.
	 * @return Provincias de España.
	 */
	@ModelAttribute(value = "provincias")
	public List<Zona> findByAllProvincias(){
		
		return zonaAdminService.findAllProvincias();
	}
}
