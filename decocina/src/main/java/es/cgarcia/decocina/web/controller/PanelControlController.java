package es.cgarcia.decocina.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.cgarcia.decocina.web.form.ClienteForm;
import es.cgarcia.decocina.web.form.PasswordForm;
import es.cgarcia.decocina.web.manager.CestaManager;
import es.cgarcia.decocina.web.miga.MigaPan;
import es.cgarcia.decocina.web.miga.MigasPan;
import es.cgarcia.decocina.web.model.ArticuloPedido;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.ClientePassword;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.service.IArticuloPedidoWebService;
import es.cgarcia.decocina.web.service.ICestaWebService;
import es.cgarcia.decocina.web.service.IClienteWebService;
import es.cgarcia.decocina.web.service.IPedidoEstadoHistorialWebService;
import es.cgarcia.decocina.web.service.IPedidoWebService;
import es.cgarcia.decocina.web.service.IZonaWebService;
import es.cgarcia.decocina.web.validator.ClientePanelValidator;
import es.cgarcia.decocina.web.validator.PasswordValidator;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;


/**
 * Controller del panel de Control.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class PanelControlController extends WebControllerBase{
	
	/**
     * Servicio para los Clientes.
     */
    @Autowired
    private IClienteWebService clienteWebService;
    
    /**
     * Servicio para los Pedidos.
     */
    @Autowired
    private IPedidoWebService pedidoWebService;
    
    /**
     * Servicio para los ArticuloPedido de la parte Web.
     */
    @Autowired
    private IArticuloPedidoWebService articuloPedidoWebService;
    
    /**
     * Servicio para los PedidoEstadoHistorial de la parte Web.
     */
    @Autowired
    private IPedidoEstadoHistorialWebService pedidoEstadoHistorialWebService; 
    
	/**
     * Servicio para los Clientes.
     */
    @Autowired
    private IZonaWebService zonaWebService;
	
    /**
     * Servicio para la Cesta.
     */
    @Autowired
    private ICestaWebService cestaWebService;
    
    /**
     * Validador del Cliente.
     */
    @Autowired
    private ClientePanelValidator clientePanelValidator;
    
    /**
     * Validador del Password.
     */
    @Autowired
    private PasswordValidator passwordValidator;
    
	/**
	 * Redirige al Panel de Control de Cliente.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige al Panel de Control del Cliente. 
	 */
	@RequestMapping(value = "/cliente/mi-cuenta", method = RequestMethod.GET)
	public String panelControl(HttpServletRequest request, 
						 	   Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan mcMigaPan = new MigaPan();
		mcMigaPan.setTexto("Mi cuenta");
		//mcMigaPan.setEnlace("/cliente/mi-cuenta");
		
		migasPan.addMiga(mcMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		Cliente cliente = clienteWrapper.getCliente();
		
		Long id = cliente.getId();
		
		List<Pedido> pedidos = pedidoWebService.findAllByIdCliente(id);
		
		model.addAttribute("pedidos", pedidos);
		
		return "web/cliente/mi-cuenta/mi-cuenta";
	}
	
	/**
	 * Redirige a los datos personales del Cliente.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a los datos personales del Cliente. 
	 */
	@RequestMapping(value = "/cliente/ver-datos-personales", method = RequestMethod.GET)
	public String verDatosPersonales(HttpServletRequest request, 
						 	   		 Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan mcMigaPan = new MigaPan();
		mcMigaPan.setTexto("Mi cuenta");
		mcMigaPan.setEnlace("/cliente/mi-cuenta");
		
		migasPan.addMiga(mcMigaPan);
		
		MigaPan vdMigaPan = new MigaPan();
		vdMigaPan.setTexto("Ver o modificar los datos personales");
		//vdMigaPan.setEnlace("/cliente/ver-datos-personales");
		
		migasPan.addMiga(vdMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		Cliente cliente = clienteWrapper.getCliente();
		
		Long id = cliente.getId();
		
		Cliente clienteBD = clienteWebService.findById(id);
		
		ClienteForm clienteForm = new ClienteForm(clienteBD);
		
		model.addAttribute("clienteForm", clienteForm);
		
		List<Zona> zonas = zonaWebService.findAllProvincias();
		model.addAttribute("provincias", zonas);
		
		return "web/cliente/mi-cuenta/ver-datos-personales";
	}
	
	/**
	 * Modifica el Cliente.
	 * 
	 * @param model Model.
	 * @param clienteForm Wrapper del Cliente con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de los Datos Personales.
	 */
	@RequestMapping(value = "/cliente/modificar-datos-personales", method = RequestMethod.POST)
	public String updateDatosPersonales(@Valid ClienteForm clienteForm,
					 	 				BindingResult result, 
					 	 				HttpServletRequest request,
					 	 				Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		Cliente clienteSesion = clienteWrapper.getCliente();
		
		Long idClienteSesion = clienteSesion.getId();
		Long idDireccionSesion = clienteSesion.getDireccion().getId();
		String emailSesion = clienteSesion.getEmail();
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan mcMigaPan = new MigaPan();
		mcMigaPan.setTexto("Mi cuenta");
		mcMigaPan.setEnlace("/cliente/mi-cuenta");
		
		migasPan.addMiga(mcMigaPan);
		
		MigaPan vdMigaPan = new MigaPan();
		vdMigaPan.setTexto("Ver o modificar los datos personales");
		//vdMigaPan.setEnlace("/cliente/ver-datos-personales");
		
		migasPan.addMiga(vdMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		String vista = "web/cliente/mi-cuenta/ver-datos-personales";

		List<Zona> zonas = zonaWebService.findAllProvincias();
		model.addAttribute("provincias", zonas);
		
		//Esta hará que no se valide el email, contraseña y política de privacidad.
		clienteForm.setEnPanelControl(true);
		
		Cliente cliente = clienteForm.getCliente();
		
		BindException bindException = new BindException(result);
		clientePanelValidator.validate(clienteForm, bindException);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		cliente.setId(idClienteSesion);
		cliente.setEmail(emailSesion);
		cliente.getDireccion().setId(idDireccionSesion);
		
		clienteWebService.update(clienteWrapper, cliente);
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		
		//Vaciamos la cesta y volvemos a cargar por si se ha cambiado de provincia y el iva es distinto.
		cestaManager.vaciarCesta();
		
		Long idCliente = cliente.getId();
		
		//Recuperamos todos los articulos del Cesta BD y se los pasamos al Manager.
		cestaWebService.addArticulosCestaManager(clienteWrapper, idCliente, cestaManager);
		
		model.addAttribute("mensaje", "Datos personales modificados correctamente");
		
		return vista;
	}
	
	/**
	 * Redirige a la página que permite el cambio de la contraseña. 
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la página que permite el cambio de la contraseña. 
	 */
	@RequestMapping(value = "/cliente/ver-password", method = RequestMethod.GET)
	public String verPassword(HttpServletRequest request, 
						 	  Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan mcMigaPan = new MigaPan();
		mcMigaPan.setTexto("Mi cuenta");
		mcMigaPan.setEnlace("/cliente/mi-cuenta");
		
		migasPan.addMiga(mcMigaPan);
		
		MigaPan vdMigaPan = new MigaPan();
		vdMigaPan.setTexto("Cambiar contraseña");
		//vdMigaPan.setEnlace("/cliente/ver-password");
		
		migasPan.addMiga(vdMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		ClientePassword clientePassword = new ClientePassword();
		
		PasswordForm passwordForm = new PasswordForm(clientePassword);
		
		model.addAttribute("passwordForm", passwordForm);
		
		return "web/cliente/mi-cuenta/ver-password";
	}
	
	/**
	 * Modifica el Password.
	 * 
	 * @param model Model.
	 * @param passwordForm Formulario con los campos a modificar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición del Password.
	 */
	@RequestMapping(value = "/cliente/modificar-password", method = RequestMethod.POST)
	public String updatePassword(@Valid PasswordForm passwordForm,
					 	 		 BindingResult result, 
					 	 		 HttpServletRequest request,
					 	 		 Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		Cliente cliente = clienteWrapper.getCliente();
		
		Long idCliente = cliente.getId();
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan mcMigaPan = new MigaPan();
		mcMigaPan.setTexto("Mi cuenta");
		mcMigaPan.setEnlace("/cliente/mi-cuenta");
		
		MigaPan vdMigaPan = new MigaPan();
		vdMigaPan.setTexto("Cambiar contraseña");
		//vdMigaPan.setEnlace("/cliente/modificar-password");
		
		migasPan.addMiga(mcMigaPan);
		migasPan.addMiga(vdMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		ClientePassword clientePassword = passwordForm.getClientePassword();
		clientePassword.setId(idCliente);
		
		model.addAttribute("passwordForm", passwordForm);
		
		String vista = "web/cliente/mi-cuenta/ver-password";
		
		BindException bindException = new BindException(result);
		passwordValidator.validate(passwordForm, bindException);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		clienteWebService.updatePassword(clientePassword);
		
		clientePassword.setPassword("");
		clientePassword.setNuevoPassword("");
		clientePassword.setNuevoPasswordConfirmacion("");
		
		model.addAttribute("mensaje", "Constraseña modificada correctamente");
		
		return vista;
	}
	
	/**
	 * Redirige a la página que permite el pedido. 
	 * 
	 * @param id Id del Pedido.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la página que permite ver Pedido. 
	 */
	@RequestMapping(value = "/cliente/ver-pedido/id/{id}", method = RequestMethod.GET)
	public String verPedido(@PathVariable("id") Long idPedido,
							HttpServletRequest request,
						 	Model model){
				
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan mcMigaPan = new MigaPan();
		mcMigaPan.setTexto("Mi cuenta");
		mcMigaPan.setEnlace("/cliente/mi-cuenta");
		
		migasPan.addMiga(mcMigaPan);
		
		MigaPan vpMigaPan = new MigaPan();
		vpMigaPan.setTexto("Pedido");
	
		migasPan.addMiga(vpMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		Cliente cliente = clienteWrapper.getCliente();
		
		Long idCliente = cliente.getId();
		
		Pedido pedido = pedidoWebService.findByIdClienteAndIdPedido(idCliente, idPedido);
		
		model.addAttribute("pedido", pedido);
		
		String ivaTotal = pedido.getIvaTotal();
		String[] ivasTotal = ivaTotal.split("\\|");
		
		model.addAttribute("ivasTotal", ivasTotal);
		
		List<ArticuloPedido> articulosPedido = articuloPedidoWebService.findByIdPedido(idPedido);
		
		model.addAttribute("articulos", articulosPedido);
		
		List<PedidoEstadoHistorial> pedidoEstadosHistorial = pedidoEstadoHistorialWebService.findAllByIdPedido(idPedido);
		
		model.addAttribute("pedidoEstadosHistorial", pedidoEstadosHistorial);
		
		return "web/cliente/mi-cuenta/ver-pedido";
	}
}
