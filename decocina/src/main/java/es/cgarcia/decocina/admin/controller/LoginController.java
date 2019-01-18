package es.cgarcia.decocina.admin.controller;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.admin.model.Administrador;
import es.cgarcia.decocina.admin.service.IAdministradorAdminService;
import es.cgarcia.decocina.admin.service.IArticuloAdminService;
import es.cgarcia.decocina.admin.service.IPedidoAdminService;
import es.cgarcia.decocina.admin.util.CookieUtils;
import es.cgarcia.decocina.admin.validator.AdministradorValidator;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.util.Constantes;
import es.cgarcia.decocina.web.util.MD5Utils;


/**
 * Controller del login de la administración.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class LoginController extends AdminControllerBase{

	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(LoginController.class);    

	/**
	 * Inicio.
	 */
	private String miga = "Inicio";
	
	/**
     * Localizador de mensajes.
     */
    @Autowired
    private MessageSource messageSource;
    
    /**
     * Validador del formulario.
     */
    @Autowired
    private AdministradorValidator administradorValidator;
    
    /**
     * Servicio para los administradores de la parte Admin.
     */
    @Autowired
    private IAdministradorAdminService administradorAdminService;
    
    /**
     * Servicio para los Artículos de la parte Admin.
     */
    @Autowired
    private IArticuloAdminService articuloAdminService;
    
    /**
     * Servicio para los Pedidos de la parte Admin.
     */
    @Autowired
    private IPedidoAdminService pedidoAdminService;
	
    /**
	 * Redirige a la página de login de la administración. 
	 * Si el usuario ya está logueado se le lleva a la página de inicio.
	 * 
	 * @return Página de login. 
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request){
		
		HttpSession session = request.getSession(true);
		Administrador administrador = (Administrador) session.getAttribute(Constantes.USUARIO_ADMIN_LOGUEADO);
        
		boolean logueadoPorCookie = false;
		
		if(administrador == null){
			
			//Se comprueba si hay cookie de 'recuerdame'.
			//Cookie[] cookies = request.getCookies();
			
			//Se comprueba si hay cookie de 'recuerdame' y si es así y coinciden los campos se hace el autologin.
			logueadoPorCookie = false;//CookieUtils.autoLoginCookieSession(session, administradorAdminService, cookies);
		}
		
		if(administrador != null || logueadoPorCookie){
			
			return "redirect:/admin/consola/inicio";
		
		}else{
			
			return "admin/login/login";
		}
	}
	
	
	/**
	 * Valida el formulario de login. Y si el usuario y el password son correctos y 
	 * ha chequeado 'recuerdame' se crea la cookie.
	 * 
	 * @param model Model.
	 * @param administrador Administrador con los valores del formulario.
	 * @param result BindingResult.
	 * @param redirectAttributes Atributos que se envían en caso de redicrección.
	 * @param request Request.
	 * @param response Response.
	 * @return Vuelve a la página de login en caso de error o en caso de login 
	 * correcto a la página de inicio. 
	 */
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String validar(Model model, 
						  @Valid Administrador administrador, 
						  BindingResult result, 
						  RedirectAttributes redirectAttributes, 
						  HttpServletRequest request,
						  HttpServletResponse response){
		
		logger.debug("Validando usuario Administrador.");
		
		BindException bindException = new BindException(result);
        administradorValidator.validate(administrador, bindException);
		
		if (result.hasErrors()) {
			
            return "admin/login/login";

        } else {

        	//Recuperamos el usuario y el password del formulario.
            String usuario = administrador.getUsuario();
            String password = administrador.getPassword();

            // Recuperamos el administrador de la base de datos y comprobamos si el usuario y el password son correctos.
            Administrador administradorBD = administradorAdminService.findByUserAndPassword(usuario, MD5Utils.MD5(password));
            
            // No es correcto.
            if (administradorBD == null) {

                String mensajeError = messageSource.getMessage("error.login", null, null);

                model.addAttribute("error", mensajeError);

                return "admin/login/login";
                
            //El password y la contraseña con correctas.
            } else {
            	
            	//Fijamos la contraseña a vacía para que no viaje por la sesión.
            	administradorBD.setPassword("");
            	
                HttpSession session = request.getSession(true);
                session.setAttribute(Constantes.USUARIO_ADMIN_LOGUEADO, administradorBD);

                //Si ha chequeado 'recuerdame' se crea la cookie.
                Boolean recuerdame = false;//administrador.getRecuerdame();
                
                //Por error sólo vale para un ordenador. Hay que añadir una tabla de tokens.
                if(recuerdame){
                	
                	String idAlt = administradorBD.getIdAlt();
                	
                	String token = UUID.randomUUID().toString();
                	
                	administradorBD.setToken(MD5Utils.MD5(token));
                	
                	CookieUtils.crearCookieSesion(response, idAlt, token);
                	
                	administradorAdminService.update(administradorBD);
                }
                
                // Redirect a la página de inicio.
                return "redirect:/admin/consola/inicio";
            }

        }
	}
	
	/**
	 * Redirige a la página de inicio de la administración.
	 * 
	 * @return Página de inicio. 
	 */
	@RequestMapping(value = "/admin/consola/inicio", method = RequestMethod.GET)
	public String inicio(Model model){
		
		mostrarMiga(miga, "/admin/consola/inicio", model);
		
		//Recuperamos los últimos 10 Artículos añadidos.
		List<Articulo> articulos = articuloAdminService.findAllUltimos(Constantes.NUMERO_ULTIMOS_ARTICULOS);
		model.addAttribute("articulos", articulos);
		
		CategoriaArticuloBuscador categoriaArticuloBuscador = new CategoriaArticuloBuscador();
		categoriaArticuloBuscador.setPath("-1");
		categoriaArticuloBuscador.setDesdeInicio(true);
		
		model.addAttribute("categoriaArticuloBuscador", categoriaArticuloBuscador);
		model.addAttribute("desdeInicio", true);
		
		//Recuperamos el numero de pedidos de este mes.
		Calendar fecha = Calendar.getInstance();
		Long numeroPedidos = pedidoAdminService.findNumeroPedidosMes(fecha);
		
		model.addAttribute("numeroPedidos", numeroPedidos);
		
		Double sumaTotalMes = pedidoAdminService.findSumaImportePedidosMes(fecha);
		model.addAttribute("sumaTotalMes", sumaTotalMes);
		
		return "admin/consola/inicio/inicio";
	}
	
	/**
	 * Redirige a la página de login de la administración.
	 * 
	 * @param request Request.
	 * @param response Response.
	 * @return Página de login. 
	 */
	@RequestMapping(value = "/admin/consola/salir", method = RequestMethod.GET)
	public String salir(HttpServletRequest request, HttpServletResponse response){
		
		//Borramos la sesión.
		HttpSession sesion = request.getSession();
		sesion.invalidate();
		
		//Recuperamos las cookies.
		Cookie[] cookies = request.getCookies();
		
		//Borramos la cookie de 'recuerdame' si existe.
		CookieUtils.borrarCookieSesion(response, cookies);
		
		// Redirect a la página de inicio.
        return "redirect:/admin/login";
	}
	
	/**
     * Crea y devuelve un objeto Administrador para el formulario.
     * 
     * @return Objeto Administrador.
     */
    @ModelAttribute(value = "administrador")
    public Administrador getUsuario() {

        Administrador administrador = new Administrador();

        return administrador;

    } 
}
