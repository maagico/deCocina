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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cgarcia.decocina.admin.buscador.PedidoBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.service.IArticuloPedidoAdminService;
import es.cgarcia.decocina.admin.service.IEstadoAdminService;
import es.cgarcia.decocina.admin.service.IPedidoAdminService;
import es.cgarcia.decocina.admin.service.IPedidoEstadoHistorialAdminService;
import es.cgarcia.decocina.admin.validator.PedidoValidator;
import es.cgarcia.decocina.web.form.PedidoForm;
import es.cgarcia.decocina.web.model.ArticuloPedido;
import es.cgarcia.decocina.web.model.Estado;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;


/**
 * Controller del Pedido.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller(value= "pedidoAdminController")
public class PedidoController extends AdminControllerBase{
	
	/**
	 * Texto de la miga de pan.
	 */
	private final String miga = "Pedidos";
    
    /**
     * Servicio para los Pedidos de la parte Admin.
     */
    @Autowired
    private IPedidoAdminService pedidoAdminService;
    
    /**
     * Servicio para los ArticuloPedido de la parte Admin.
     */
    @Autowired
    private IArticuloPedidoAdminService articuloPedidoAdminService;
   
    /**
     * Servicio para los Estados de la parte Admin.
     */
    @Autowired
    private IEstadoAdminService estadoAdminService;
    
    /**
     * Servicio para los Estados del pedido de la parte Admin.
     */
    @Autowired
    private IPedidoEstadoHistorialAdminService pedidoEstadoHistorialAdminService;
    
    /**
     * Validador.
     */
    @Autowired
    private PedidoValidator pedidoValidator;
    
    /**
     * Busca los Pedidos por su nombre.
     * 
     * @param clienteBuscador Buscador de Pedidos.
     * @return Página que muestra los Pedidos encontrados.
     */
	@RequestMapping(value = "/admin/consola/pedido/pedido-search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(PedidoBuscador pedidoBuscador,
					   	 Model model){
		
		mostrarMiga(miga, model);
		
		pedidoBuscador.setHayBusqueda(true);
		
		List<Pedido> pedidos = pedidoAdminService.findAllBusqueda(pedidoBuscador);
		
		model.addAttribute("pedidos", pedidos);
		
		return "admin/consola/pedido/ver-pedidos";
	}
    
	/**
	 * Listado de Pedidos.
	 * 
	 * @param pedidoBuscador Buscador de Pedidos.
	 * @param model Model.
	 * @return Página que muestra el listado de Pedidos.
	 */
	@RequestMapping(value = "/admin/consola/pedido/pedido-find", method = { RequestMethod.GET, RequestMethod.POST })
	public String find(PedidoBuscador pedidoBuscador,
					   Model model){
		
		mostrarMiga(miga, model);
		
		List<Pedido> pedidos = pedidoAdminService.findAllBusqueda(pedidoBuscador);
		
		model.addAttribute("pedidos", pedidos);
		
		return "admin/consola/pedido/ver-pedidos";
	}
	
	/**
	 * Redirige a la página de modificación de un Pedido.
	 * 
	 * @param path Path.
	 * @param id Id del Pedido.
	 * @param model Model.
	 * @return Página que muestra la edición de un Pedido.
	 */
	@RequestMapping(value = "/admin/consola/pedido/pedido-modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(PedidoBuscador pedidoBuscador,
						 @RequestParam(value = "id", required = false) Long id, 
						 Model model){
		
		mostrarMiga(miga, model);
		
		Pedido pedido = null;
		
		Boolean esCreacion = null;
			
		esCreacion = false;
			
		pedido = pedidoAdminService.findById(id);
		
		pedido.setNotificarEstado(true);
		
		String ivaTotal = pedido.getIvaTotal();
		String[] ivasTotal = ivaTotal.split("\\|");
		
		model.addAttribute("ivasTotal", ivasTotal);
		
		PedidoForm pedidoForm = new PedidoForm(pedido);
		
		pedidoForm.setEsCreacion(esCreacion);
		
		pedidoForm.setPedidoBuscador(pedidoBuscador);
		
		model.addAttribute("pedidoForm", pedidoForm);
		
		model.addAttribute("pedidoBuscador", pedidoBuscador);
		
		List<ArticuloPedido> articulosPedido = articuloPedidoAdminService.findByIdPedido(id);
		
		model.addAttribute("articulos", articulosPedido);
		
		List<Estado> estados = estadoAdminService.findAll();
		
		model.addAttribute("estados", estados);
		
		List<PedidoEstadoHistorial> pedidoEstadosHistorial = pedidoEstadoHistorialAdminService.findAllByIdPedido(id);
		
		model.addAttribute("pedidoEstadosHistorial", pedidoEstadosHistorial);
		
		return "admin/consola/pedido/editar-pedido";
	}
	
	/**
	 * Añade un nuevo estado al Pedido.
	 * 
	 * @param cuponForm Wrapper del Cupón con los campos a insertar.
	 * @param result BindingResult.
	 * @param request HttpServletRequest.
	 * @param model Model.
	 * @return Redirige a la página de edición de un Fabricante.
	 */
	@RequestMapping(value = "/admin/consola/pedido/pedido-add-nuevo-estado", method = RequestMethod.POST)
	public String addNuevoEstado(@Valid PedidoForm pedidoForm,
							 	 BindingResult result, 
								 HttpServletRequest request,
								 Model model){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/pedido/editar-pedido";

		Pedido pedido = pedidoForm.getPedido();
		
		Long idPedido = pedido.getId();
		
		PedidoBuscador pedidoBuscador = pedidoForm.getPedidoBuscador();
		
		model.addAttribute("pedidoForm", pedidoForm);
		
		pedidoForm.setPedidoBuscador(pedidoBuscador);
		
		model.addAttribute("pedidoBuscador", pedidoBuscador);
		
		BindException bindException = new BindException(result);
		pedidoValidator.validate(pedidoForm, bindException);
		
		Pedido pedidoBD = pedidoAdminService.findById(idPedido);
		
		//Pasamos los campos del Estado al PedidoBD.
		
		Boolean notificarEstado = pedido.getNotificarEstado();
		Estado estadoAdmin = pedido.getEstadoAdmin();
		String comentarioCambioEstado = pedido.getComentarioCambioEstado();
		
		pedidoBD.setNotificarEstado(notificarEstado);
		pedidoBD.setEstadoAdmin(estadoAdmin);
		pedidoBD.setComentarioCambioEstado(comentarioCambioEstado);
		
		pedidoForm.setPedido(pedidoBD);
		
		List<ArticuloPedido> articulosPedido = articuloPedidoAdminService.findByIdPedido(idPedido);
		
		model.addAttribute("articulos", articulosPedido);
		
		List<Estado> estados = estadoAdminService.findAll();
		
		model.addAttribute("estados", estados);
		
		List<PedidoEstadoHistorial> pedidoEstadosHistorial = pedidoEstadoHistorialAdminService.findAllByIdPedido(idPedido);
		
		model.addAttribute("pedidoEstadosHistorial", pedidoEstadosHistorial);
		
		pedidoForm.setEsCreacion(false);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		//Pasamos los datos del PedidoBD al Pedido para el envío del email del cambio de estado(si es necesario).
		String email = pedidoBD.getEmail();
		Calendar fechaCreacion = pedidoBD.getFechaCreacion(); 
		
		pedido.setId(idPedido);
		pedido.setEmail(email);
		pedido.setFechaCreacion(fechaCreacion);
		
		//Añade el estado.
		pedidoEstadoHistorialAdminService.insert(pedido);
		
		//Ponemos los campos a null.
		pedidoBD.setNotificarEstado(true);
		pedidoBD.setEstadoAdmin(null);
		pedidoBD.setComentarioCambioEstado(null);
		
		pedidoEstadosHistorial = pedidoEstadoHistorialAdminService.findAllByIdPedido(idPedido);
		
		model.addAttribute("pedidoEstadosHistorial", pedidoEstadosHistorial);
		
		model.addAttribute("mensaje", "Estado añadido correctamente");
		
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
	@RequestMapping(value = "/admin/consola/pedido/pedido-delete", method = RequestMethod.POST)
	public String delete(PedidoForm pedidoForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		mostrarMiga(miga, model);
		
		Pedido pedido = pedidoForm.getPedido();
		
		Long id = pedido.getId();
		
		try{

			redirectAttributes.addAttribute("id", id);
			
			PedidoBuscador pedidoBuscador = pedidoForm.getPedidoBuscador();
			
			redirectAttributes.addFlashAttribute("pedidoBuscador", pedidoBuscador);
			
			pedidoAdminService.delete(pedido);
		
			redirectAttributes.addFlashAttribute("mensaje", "Pedido eliminado correctamente");
			
			
			String vista =  "redirect:/admin/consola/pedido/pedido-find";
			
			if(pedidoBuscador.getHayBusqueda()){
				
				vista = "redirect:/admin/consola/pedido/pedido-search";
			}
			
			return vista;
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);	
			
			pedidoForm.setEsCreacion(false);
		}
		
		model.addAttribute("id", id);
		
		PedidoBuscador pedidoBuscador = pedidoForm.getPedidoBuscador();
		
		model.addAttribute("pedidoBuscador", pedidoBuscador);
		
		return "admin/consola/pedido/editar-pedido";
	}
}
