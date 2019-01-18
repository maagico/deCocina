package es.cgarcia.decocina.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.web.exception.DeCocinaWebRuntimeException;
import es.cgarcia.decocina.web.form.ClienteCambiarPasswordForm;
import es.cgarcia.decocina.web.form.ClienteForm;
import es.cgarcia.decocina.web.form.ClienteLoginForm;
import es.cgarcia.decocina.web.form.ClienteRecuperarPasswordForm;
import es.cgarcia.decocina.web.manager.CestaManager;
import es.cgarcia.decocina.web.miga.MigaPan;
import es.cgarcia.decocina.web.miga.MigasPan;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.ClienteCambiarPassword;
import es.cgarcia.decocina.web.model.ClienteLogin;
import es.cgarcia.decocina.web.model.ClienteRecuperarPassword;
import es.cgarcia.decocina.web.model.Direccion;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.service.ICestaWebService;
import es.cgarcia.decocina.web.service.IClienteWebService;
import es.cgarcia.decocina.web.service.IZonaWebService;
import es.cgarcia.decocina.web.util.Constantes;
import es.cgarcia.decocina.web.validator.ClienteCambiarPasswordValidator;
import es.cgarcia.decocina.web.validator.ClienteValidator;
import es.cgarcia.decocina.web.wrapper.ArticuloCestaWrapper;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;


/**
 * Controller de Sesión.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class SesionController extends WebControllerBase{
	
	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(SesionController.class);    
	
	/**
     * Localizador de mensajes.
     */
    @Autowired
    private MessageSource messageSource;
    
	/**
     * Servicio para los Clientes.
     */
    @Autowired
    private IClienteWebService clienteWebService;
    
	/**
     * Servicio para las Zonas.
     */
    @Autowired
    private IZonaWebService zonaWebService;
   
    /**
     * Servicio para la cesta.
     */
    @Autowired
    private ICestaWebService cestaWebService;
    
    /**
     * Validador del Cliente.
     */
    @Autowired
    private ClienteValidator clienteValidator;
    
    /**
     * Validador del cambio de Password.
     */
    @Autowired
    private ClienteCambiarPasswordValidator clienteCambiarPasswordValidator;
    
    /**
     * Url siguiente al alta.
     */
    private static String URL_SIGUIENTE = "urlSiguiente";
    
	/**
	 * Redirige a la página de acceso a la web. Inicio de sesión o alta de Cliente.
	 * 
	 * @param f Indica que se viene de la página realizar pedido y al no estar logueado se ha redirigido a iniciar-sesion.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige al index. 
	 */
	@RequestMapping(value = "/iniciar-sesion", method = RequestMethod.GET)
	public String acceso(@RequestParam(value = "f", required=false) String from,
						 HttpServletRequest request, 
						 Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan isMigaPan = new MigaPan();
		isMigaPan.setTexto("Iniciar sesión");
		//isMigaPan.setEnlace("/iniciar-sesion");
		
		migasPan.addMiga(isMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		HttpSession sesion = request.getSession();
		ClienteWrapper clienteWrapper = (ClienteWrapper)sesion.getAttribute(Constantes.CLIENTE_WEB_LOGUEADO);
		
		//Si no es invitado le mandamos al panel de control.
		if(!clienteWrapper.getEsInvitado()){
			
			return "redirect:/cliente/mi-cuenta";
		}
		
		//Mandamos los dos formularios.
		
		//Login de accceso.
		ClienteLogin clienteLogin = new ClienteLogin();
		ClienteLoginForm clienteLoginForm = new ClienteLoginForm(clienteLogin);
		
		if(from != null){
			
			clienteLoginForm.setFrom(true);
			
		}
		
		model.addAttribute("clienteLoginForm", clienteLoginForm);
		
		//Alta del Cliente.
		Cliente cliente = new Cliente();
		ClienteForm clienteForm = new ClienteForm(cliente);
		
		if(from != null){
			
			clienteForm.setFrom(true);
			
		}

		model.addAttribute("clienteForm", clienteForm);
		
		return "web/cliente/acceso/acceso";
	}
	
	/**
	 * Loguea al usuario y redirige a la página del panel 
	 * de control o al primer paso de hacer un pedido.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige al index. 
	 */
	@RequestMapping(value = "/login-cliente", method = RequestMethod.POST)
	public String loginCliente(@Valid ClienteLoginForm clienteLoginForm,
							   BindingResult result,
							   HttpServletRequest request, 
							   Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan isMigaPan = new MigaPan();
		isMigaPan.setTexto("Iniciar sesión");
		//isMigaPan.setEnlace("/iniciar-sesion");
		
		migasPan.addMiga(isMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		Boolean from = clienteLoginForm.getFrom();
		
		//Para el formulario de alta, vale con que vaya vacío.
		ClienteForm clienteForm = new ClienteForm(new Cliente());
		
		clienteForm.setFrom(from);
		
		model.addAttribute("clienteForm", clienteForm);
		
		if(result.hasErrors()){
			
			return "web/cliente/acceso/acceso";
		}
		
		ClienteLogin clienteLogin = clienteLoginForm.getClienteLogin();
		
		try{
			
			String email = clienteLogin.getEmail();
			String password = clienteLogin.getPassword();
			
			Cliente cliente = clienteWebService.findByEmailAndPassword(email, password);
		
			if(cliente != null){
				
				
				//Recuperamos la sesión y añadimos el Cliente.
				HttpSession sesion = request.getSession();
				
				ClienteWrapper clienteWrapper = (ClienteWrapper)sesion.getAttribute(Constantes.CLIENTE_WEB_LOGUEADO);
				clienteWrapper.setEsInvitado(false);
				clienteWrapper.setCliente(cliente);
				
				sesion.setAttribute(Constantes.CLIENTE_WEB_LOGUEADO, clienteWrapper);
				
				//Recuperamos la Zona del usuario creado y la asignamos a la sesion.
				Direccion direccion = cliente.getDireccion();
				Zona zona = direccion.getZona();
				
				Long idZona = zona.getId();
				
				Zona zonaBD = zonaWebService.findById(idZona);
				
				PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
				pedidoWrapper.setZona(zonaBD);
				
				CestaManager cestaManager = pedidoWrapper.getCestaManager();
				
				//Añadimos a la cesta el CLiente logueado para que se pueda recuperar el id del cliente.
				cestaManager.setCliente(cliente);
				
				//Recuperamos todos los Artículos de la Cesta para insertarlos en BD.
				List<ArticuloCestaWrapper> articulosCestaWrapper = cestaManager.getCesta();
		
				//Añadimos los Artículos a la Cesta en BD.
				cestaWebService.insert(articulosCestaWrapper);
				//Borramos la Cesta desde el Manager.
				cestaManager.vaciarCesta();
				
				Long idCliente = cliente.getId();
				
				//Recuperamos todos los articulos del Cesta BD y se los pasamos al Manager.
				cestaWebService.addArticulosCestaManager(clienteWrapper, idCliente, cestaManager);
				
				Integer numeroArticulos = cestaManager.getNumeroArticulos();
				
				if(from != null && from && numeroArticulos > 0){
					
					return "redirect:/cliente/realizar-pedido";
				}else{
					
					return "redirect:/cliente/mi-cuenta";
				}
				
			}
			
		}catch(DeCocinaAdminRuntimeException e){
			
			logger.error("Se ha producido un error creando el Cliente", e);
		}
		
		clienteLogin.setPassword("");
		
		String mensajeError = messageSource.getMessage("error.login", null, null);

        model.addAttribute("error", mensajeError);
		
		return "web/cliente/acceso/acceso";
	}
	
	/**
	 * Redirige a la página de recuperación del password.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la página de recuperación del password. 
	 */
	@RequestMapping(value = "/recuperar-password", method = RequestMethod.GET)
	public String recuperarPassword(HttpServletRequest request, 
						 			Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan rpMigaPan = new MigaPan();
		rpMigaPan.setTexto("Recuperar contraseña");
		//rpMigaPan.setEnlace("/recuperar-password");
		
		migasPan.addMiga(rpMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		ClienteRecuperarPassword clienteRecuperarPassword = new ClienteRecuperarPassword();
		
		ClienteRecuperarPasswordForm clienteRecuperarPasswordForm = new ClienteRecuperarPasswordForm(clienteRecuperarPassword);
		model.addAttribute("clienteRecuperarPasswordForm", clienteRecuperarPasswordForm);
		
		return "web/cliente/acceso/recuperar-password";
	}
	
	/**
	 * Comprueba si existe el email y si existe manda un correo para recuperar la contraseña.
	 *  
	 * @param clienteRecuperarPasswordForm ClienteRecuperarPasswordForm.
	 * @param result Result.
	 * @param request Request.
	 * @param model Model.
	 * @return Redirige a la pantalla que indica que se ha enviado un email de recuperación.
	 */
	@RequestMapping(value = "/recuperar-password", method = RequestMethod.POST)
	public String recuperarPassword(@Valid ClienteRecuperarPasswordForm clienteRecuperarPasswordForm,
								    BindingResult result,
								    HttpServletRequest request, 
								    Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan rpMigaPan = new MigaPan();
		rpMigaPan.setTexto("Recuperar contraseña");
		//rpMigaPan.setEnlace("/recuperar-password");
		
		migasPan.addMiga(rpMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		if(result.hasErrors()){
			
			return "web/cliente/acceso/recuperar-password";
		}
		
		ClienteRecuperarPassword clienteRecuperarPassword = clienteRecuperarPasswordForm.getClienteRecuperarPassword();
		
		String email = clienteRecuperarPassword.getEmail();
		
		try{
			
			String host = getURL(request);
			
			clienteWebService.recuperarPassword(email, host);
		
		}catch(DeCocinaWebRuntimeException e){
			
			model.addAttribute("error","El email introducido no corresponde a ningún cliente");
			
			return "web/cliente/acceso/recuperar-password";
			
		}
		
		return "web/cliente/acceso/email-recuperacion";
	}
	
	/**
	 * Redirige a la página de cambio del password.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la página de cambio del password. 
	 */
	@RequestMapping(value = "/cambiar-password", method = RequestMethod.GET)
	public String cambiarPassword(HttpServletRequest request, 
						 		  Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		String token = request.getParameter("token");
		
		String url = null;
		
		if(token == null || token.equals("")){
			
			MigaPan ecMigaPan = new MigaPan();
			ecMigaPan.setTexto("Enlace caducado");
			
			migasPan.addMiga(ecMigaPan);
			
			url =  "web/cliente/acceso/enlace-caducado";
			
		}else{
			
			Boolean correcto = false;
			
			try{
				
				 correcto = clienteWebService.comprobarToken(token);
				 
			}catch(DeCocinaWebRuntimeException e){
				
				correcto = false;
			}
			
			if(correcto){
				
				MigaPan ccMigaPan = new MigaPan();
				ccMigaPan.setTexto("Introduce la nueva contraseña");
				
				migasPan.addMiga(ccMigaPan);
				
				ClienteCambiarPassword clienteCambiarPassword = new ClienteCambiarPassword();
				clienteCambiarPassword.setToken(token);
				
				ClienteCambiarPasswordForm clienteCambiarPasswordForm = new ClienteCambiarPasswordForm(clienteCambiarPassword);
				model.addAttribute("clienteCambiarPasswordForm", clienteCambiarPasswordForm);
				
				url = "web/cliente/acceso/cambiar-password";
				
			}else{
				
				MigaPan ecMigaPan = new MigaPan();
				ecMigaPan.setTexto("Enlace caducado");
				
				migasPan.addMiga(ecMigaPan);
				
				url = "web/cliente/acceso/enlace-caducado";
			}
		}
		
		mostrarMigasPan(migasPan, model);
		
		return url;
	}
	
	/**
	 * Comprueba si existe el email y si existe manda un correo para recuperar la contraseña.
	 *  
	 * @param clienteRecuperarPasswordForm ClienteRecuperarPasswordForm.
	 * @param result Result.
	 * @param request Request.
	 * @param model Model.
	 * @return Redirige a la pantalla que indica que se han modificado los datos correctamente.
	 */
	@RequestMapping(value = "/cambiar-password", method = RequestMethod.POST)
	public String cambiarPassword(@Valid ClienteCambiarPasswordForm clienteCambiarPasswordForm,
								  BindingResult result,
								  HttpServletRequest request, 
								  Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		BindException bindException = new BindException(result);
		clienteCambiarPasswordValidator.validate(clienteCambiarPasswordForm, bindException);
		
		String url = null;
		
		if(result.hasErrors()){
			
			MigaPan ccMigaPan = new MigaPan();
			ccMigaPan.setTexto("Cambiar contraseña");
			
			migasPan.addMiga(ccMigaPan);
			
			url = "web/cliente/acceso/cambiar-password";
			
		}else{
			
			MigaPan ccMigaPan = new MigaPan();
			ccMigaPan.setTexto("Contraseña cambiada correctamente");
			
			migasPan.addMiga(ccMigaPan);
			
			ClienteCambiarPassword clienteCambiarPassword = clienteCambiarPasswordForm.getClienteCambiarPassword();
			
			String token = clienteCambiarPassword.getToken();
			String nuevoPassword = clienteCambiarPassword.getNuevoPassword();
			
			try{
				
				clienteWebService.updatePassword(token, nuevoPassword);
			
			}catch(DeCocinaWebRuntimeException e){
				
				MigaPan ecMigaPan = new MigaPan();
				ecMigaPan.setTexto("Enlace caducado");
				
				migasPan.addMiga(ecMigaPan);
				
				url = "web/cliente/acceso/enlace-caducado";
			}
			
			url = "web/cliente/acceso/password-cambiada";
		}
		
		mostrarMigasPan(migasPan, model);
		
		return url;
	}
	
	/**
	 * Redirige a la página de acceso a la web. Inicio de sesión o alta de Cliente.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige al index. 
	 */
	@RequestMapping(value = "/alta-cliente", method = RequestMethod.POST)
	public String alta(@Valid ClienteForm clienteForm,
					   BindingResult result,
					   HttpServletRequest request, 
					   Model model){
				
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan isMigaPan = new MigaPan();
		isMigaPan.setTexto("Iniciar sesión");
		isMigaPan.setEnlace("/iniciar-sesion");
		
		migasPan.addMiga(isMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		//Enviamos el formulario de acceso vacío.
		ClienteLogin clienteLogin = new ClienteLogin();
		ClienteLoginForm clienteLoginForm = new ClienteLoginForm(clienteLogin);
		
		Boolean from = clienteForm.getFrom();
		
		clienteLoginForm.setFrom(from);
		
		model.addAttribute("clienteLoginForm", clienteLoginForm);
		
		Cliente cliente = clienteForm.getCliente();
		
		BindException bindException = new BindException(result);
		clienteValidator.validate(clienteForm, bindException);
		
		if(result.hasErrors()){
			
			return "web/cliente/acceso/acceso";
		}
		
		try{
			
			String host = getURL(request);
			
			clienteWebService.crearCliente(cliente, host);
		
			//Recuperamos la sesión y añadimos el Cliente.
			HttpSession sesion = request.getSession();
			
			ClienteWrapper clienteWrapper = (ClienteWrapper)sesion.getAttribute(Constantes.CLIENTE_WEB_LOGUEADO);
			clienteWrapper.setEsInvitado(false);
			clienteWrapper.setCliente(cliente);
			
			//Recuperamos la Zona del usuario creado y la asignamos a la sesion.
			Direccion direccion = cliente.getDireccion();
			Zona zona = direccion.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaBD = zonaWebService.findById(idZona);
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			pedidoWrapper.setZona(zonaBD);
			
			CestaManager cestaManager = pedidoWrapper.getCestaManager();
			
			//Añadimos a la cesta el CLiente logueado para que se pueda recuperar el id del cliente.
			cestaManager.setCliente(cliente);
			
			//Recuperamos todos los Artículos de la Cesta para insertarlos en BD.
			List<ArticuloCestaWrapper> articulosCestaWrapper = cestaManager.getCesta();
	
			//Añadimos los Artículos a la Cesta en BD.
			cestaWebService.insert(articulosCestaWrapper);
			//Borramos la Cesta desde el Manager.
			cestaManager.vaciarCesta();
			
			Long idCliente = cliente.getId();
			
			//Recuperamos todos los articulos del Cesta BD y se los pasamos al Manager.
			cestaWebService.addArticulosCestaManager(clienteWrapper, idCliente, cestaManager);
			
			Integer numeroArticulos = cestaManager.getNumeroArticulos();
			
			//Se redirige a una página intermedia para ponerla como objetivo de alta en analytics.
			if(from != null && from && numeroArticulos > 0){
				
				sesion.setAttribute(URL_SIGUIENTE, "/cliente/realizar-pedido");
				
			}else{
				
				sesion.setAttribute(URL_SIGUIENTE, "/cliente/mi-cuenta");
				
			}
			
			return "redirect:/cliente/alta-success";
			
		}catch(DeCocinaAdminRuntimeException e){
			
			logger.error("Se ha producido un error creando el Cliente", e);
		}
		
		return "web/cliente/acceso/acceso";
	}
	
	/**
	 * Redirige a la página de finalización de alta de un cliente.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige al index. 
	 */
	@RequestMapping(value = "/cliente/alta-success", method = RequestMethod.GET)
	public String altaSuccess(HttpServletRequest request, 
							  Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan bcMigaPan = new MigaPan();
		bcMigaPan.setTexto("Bienvenido a decocina");
		
		migasPan.addMiga(bcMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		HttpSession sesion = request.getSession();
		String urlSiguiente = (String)sesion.getAttribute(URL_SIGUIENTE);
		
		if(urlSiguiente == null || urlSiguiente.equals("")){
			
			sesion.setAttribute(URL_SIGUIENTE, "/cliente/mi-cuenta");
		}
		
		return "web/cliente/acceso/alta-success";
	}
	
	/**
	 * Desloguea al usuario y redirige a la página de inicio.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige al index. 
	 */
	@RequestMapping(value = "/cliente/salir", method = RequestMethod.GET)
	public String salir(@Valid ClienteLogin clienteLogin,
					    BindingResult result,
					    HttpServletRequest request, 
					    Model model){
		
		//Cerramos la sesión y redirigimos al Index.
		HttpSession sesion = request.getSession();
		sesion.setAttribute(Constantes.CLIENTE_WEB_LOGUEADO, null);
		
		sesion.invalidate();
		
		return "redirect:/";
	}
	
	/**
	 * Comprueba en el alta del Cliente que el email no existe en la base de datos.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige al index. 
	 */
	@RequestMapping(value = "/existe-email", method = RequestMethod.POST)
	public @ResponseBody String existeEmail(@RequestParam("email") String email){
				
		String resultadoJSON = clienteWebService.existeByEmail(email);
		
		return resultadoJSON;
	}
	
	/**
	 * Devuelve todas las Provincias de España.
	 * @return Provincias de España.
	 */
	@ModelAttribute(value = "provincias")
	public List<Zona> findByAllProvincias(){
		
		return zonaWebService.findAllProvinciasActivas();
	}
}
