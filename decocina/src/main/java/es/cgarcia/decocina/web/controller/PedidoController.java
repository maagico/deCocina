/**
 * 
 */
package es.cgarcia.decocina.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cgarcia.decocina.admin.controller.CategoriaController;
import es.cgarcia.decocina.web.analytics.TransaccionAnalytics;
import es.cgarcia.decocina.web.exception.DeCocinaWebRuntimeException;
import es.cgarcia.decocina.web.form.PedidoForm;
import es.cgarcia.decocina.web.manager.CestaManager;
import es.cgarcia.decocina.web.miga.MigaPan;
import es.cgarcia.decocina.web.miga.MigasPan;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.model.FormaPago;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.service.ICestaWebService;
import es.cgarcia.decocina.web.service.IPedidoWebService;
import es.cgarcia.decocina.web.util.Constantes;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;

/**
 * Controller para los Pedidos.
 * 
 * @author Miguel Ángel Álvarez García
 */
@Controller
public class PedidoController extends WebControllerBase{

	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(CategoriaController.class); 
	
	/**
	 * Servicio para los pedidos.
	 */
	@Autowired
	private IPedidoWebService pedidoWebService;
	
	/**
	 * Servicio para la Cesta.
	 */
	@Autowired
	private ICestaWebService cestaWebService;
	
	/**
	 * Redirige a la pantalla para la realización de un Pedido.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la pantalla para la realización de un Pedido.
	 */
	@RequestMapping(value = "/cliente/realizar-pedido", method = RequestMethod.GET)
	public String realizarPedido(HttpServletRequest request, 
						 	     Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan inicioMigaPan = new MigaPan();
		inicioMigaPan.setTexto("Inicio");
		inicioMigaPan.setEnlace("/");
		
		migasPan.addMiga(inicioMigaPan);
		
		MigaPan rpMigaPan = new MigaPan();
		rpMigaPan.setTexto("Realizar pedido");
		//rpMigaPan.setEnlace("/cliente/realizar-pedido");
		
		migasPan.addMiga(rpMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		//Pasamos el clienteWrapper al model.
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		model.addAttribute("clienteWrapper", clienteWrapper);
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		
		Integer numeroArticulos = cestaManager.getNumeroArticulos();
		
		if(numeroArticulos.equals(0)){
			
			return "redirect:/ver-cesta";
		}
		
		//Devuelve la Zona con todas las Formas de Envío y Pago y Gastos.
		Zona zona = pedidoWebService.cargarZona(clienteWrapper);
		model.addAttribute("zona", zona);
	
		if(!zona.getActiva()){
			
			return "redirect:/cliente/no-enviamos-a-provincia";
		}
		
		//Creamos el Form.
		PedidoForm pedidoForm = new PedidoForm();
		
		//Recuperamos la Forma de Envío
		FormaEnvio formaEnvio = pedidoWrapper.getFormaEnvio();
		
		if(formaEnvio != null){
		
			//Le pasamos la Forma de Envío al Form.
			Long idFormaEnvio = formaEnvio.getId();
			pedidoForm.setFormaEnvio(idFormaEnvio);
		}
		
		//Recuperamos la Forma de Pago.
		FormaPago formaPago = pedidoWrapper.getFormaPago();
		
		if(formaPago != null){
			
			//Le pasamos la Forma de Pago al Form.
			Long idFormaPago = formaPago.getId();
			pedidoForm.setFormaPago(idFormaPago);
		}
		
		//Recuperamos las observaciones.
		String observaciones = pedidoWrapper.getObservaciones();
		
		//Pasamos las observaciones al Form.
		pedidoForm.setObservaciones(observaciones);
		
		model.addAttribute("pedidoForm", pedidoForm);
		
		return "web/cliente/realizar-pedido/realizar-pedido";
	} 
	
	/**
	 * Graba los datos del pedido en PedidoWrapper y redirige a la pantalla para la confirmación de un Pedido.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la pantalla para la confirmación de un Pedido.
	 */
	@RequestMapping(value = "/cliente/confirmar-pedido", method = {RequestMethod.GET})
	public String confirmarPedido(HttpServletRequest request, 
							   	  Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan inicioMigaPan = new MigaPan();
		inicioMigaPan.setTexto("Inicio");
		inicioMigaPan.setEnlace("/");
		
		migasPan.addMiga(inicioMigaPan);
		
		MigaPan rpMigaPan = new MigaPan();
		rpMigaPan.setTexto("Realizar pedido");
		rpMigaPan.setEnlace("/cliente/realizar-pedido");
		
		migasPan.addMiga(rpMigaPan);
		
		MigaPan cpMigaPan = new MigaPan();
		cpMigaPan.setTexto("Confirmar pedido");
		//cpMigaPan.setEnlace("/cliente/confirmar-pedido");
		
		migasPan.addMiga(cpMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		model.addAttribute("clienteWrapper", clienteWrapper);
		
		//Recuperamos la URL del siguiente paso, que depende de la forma de pago.
		String urlSiguiente = pedidoWebService.getURLSiguiente(clienteWrapper);
		
		model.addAttribute("urlSiguiente", urlSiguiente);
		
		return "web/cliente/realizar-pedido/confirmar-pedido";
	}
	
	/**
	 * Graba los datos del pedido en PedidoWrapper y redirige a la pantalla para la confirmación de un Pedido.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la pantalla para la confirmación de un Pedido.
	 */
	@RequestMapping(value = "/cliente/confirmar-pedido", method = {RequestMethod.POST})
	public String confirmarPedido(PedidoForm pedidoForm, 
							   	  HttpServletRequest request, 
							   	  Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan inicioMigaPan = new MigaPan();
		inicioMigaPan.setTexto("Inicio");
		inicioMigaPan.setEnlace("/");
		
		migasPan.addMiga(inicioMigaPan);
		
		MigaPan rpMigaPan = new MigaPan();
		rpMigaPan.setTexto("Realizar pedido");
		rpMigaPan.setEnlace("/cliente/realizar-pedido");
		
		migasPan.addMiga(rpMigaPan);
		
		MigaPan cpMigaPan = new MigaPan();
		cpMigaPan.setTexto("Confirmar pedido");
		//cpMigaPan.setEnlace("/cliente/confirmar-pedido");
		
		migasPan.addMiga(cpMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		pedidoWebService.confirmarPedido(pedidoForm, clienteWrapper);
		
		model.addAttribute("clienteWrapper", clienteWrapper);
		
		return "redirect:/cliente/confirmar-pedido";
	}
	
	/**
	 * Graba los datos del pedido en PedidoWrapper y redirige a la pantalla para la confirmación de un Pedido.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la pantalla para la confirmación de un Pedido.
	 */
	@RequestMapping(value = "/cliente/finalizar-pedido", method = {RequestMethod.GET})
	public String finalizarPedido(@RequestParam(value = "token", required = false) String token,
								  @RequestParam(value = "PayerID", required = false) String payerId,
								  HttpServletRequest request, 
							   	  Model model,
							   	  RedirectAttributes redirectAttributes){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		String rutaPublicaDestino = Constantes.RUTA_PUBLICA_IMAGENES_PEDIDO;
		
		String rutaPrivadaOrigen = Constantes.RUTA_PRIVADA_IMAGENES;
		String rutaPrivadaDestino = Constantes.RUTA_PRIVADA_IMAGENES_PEDIDO;
		
		String rutaPrivadaOrigenReal = request.getSession().getServletContext().getRealPath(rutaPrivadaOrigen);
		String rutaPrivadaDestinoReal = request.getSession().getServletContext().getRealPath(rutaPrivadaDestino);
		
		HttpSession session = request.getSession();
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		FormaPago formaPago = pedidoWrapper.getFormaPago();
		Long idFormaPago = formaPago.getId();
		
		session.setAttribute("idFormaPagoPedido", idFormaPago);
		
		try{
			
			String host = getURL(request);
			
			TransaccionAnalytics transaccionAnalytics = pedidoWebService.finalizarPedido(clienteWrapper, rutaPublicaDestino, rutaPrivadaOrigenReal, rutaPrivadaDestinoReal, host, token, payerId);
			redirectAttributes.addFlashAttribute("transaccionAnalytics", transaccionAnalytics);
			
		}catch(DeCocinaWebRuntimeException e){
			
			redirectAttributes.addFlashAttribute("msj_error", "Se ha producido un error realizando el pedido, por favor inténtalo más tarde.");
			logger.debug("Se ha producido un error llamando a Paypal(finalizar pedido).", e);
		
			return "redirect:/cliente/confirmar-pedido";
		}
		
		return "redirect:/cliente/pedido-finalizado";
	}
	
	/**
	 * Finaliza el pedido. Es una página que viene de un redirect para que al recargar la página no cree el pedido de nuevo.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la pantalla para la confirmación de un Pedido.
	 */
	@RequestMapping(value = "/cliente/pedido-finalizado", method = {RequestMethod.GET})
	public String pedidoFinalizado(HttpServletRequest request, 
							   	  Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan inicioMigaPan = new MigaPan();
		inicioMigaPan.setTexto("Pedido finalizado");
		//inicioMigaPan.setEnlace("/cliente/pedido-finalizado");
		
		migasPan.addMiga(inicioMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		return "web/cliente/realizar-pedido/pedido-finalizado";
	}
	
	/**
	 * Carga los datos en paypal y redirige al pago.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model.
	 * @param redirectAttributes RedirectAttributes.
	 * @return Redirige a la pantalla de Paypal.
	 */
	@RequestMapping(value = "/cliente/pago-paypal", method = {RequestMethod.GET})
	public String pagoPaypal(HttpServletRequest request, 
							 Model model,
							 RedirectAttributes redirectAttributes){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		
		try{
			
			String urlServidor = getURL(request);
			String urlSiguiente = pedidoWebService.pagarPaypal(pedidoWrapper, urlServidor);
			
			if(urlSiguiente != null){
				
				StringBuffer urlRedirect = new StringBuffer();
				urlRedirect.append("redirect:");
				urlRedirect.append(urlSiguiente);
				
				return urlRedirect.toString();
			}
			
		}catch(DeCocinaWebRuntimeException e){
			
			redirectAttributes.addFlashAttribute("msj_error", "Se ha producido un error realizando el pedido, por favor inténtalo más tarde.");
			logger.debug("Se ha producido un error llamando a Paypal.", e);
		}
		
		return "redirect:/cliente/confirmar-pedido";
	}
	
	/**
	 * Asigna la Forma de Envío al Pedido.
	 * 
	 * @param idFormaEnvio Id de la Forma de Envio.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return JSON vacío.
	 */
	@RequestMapping(value = "/cliente/seleccionar-forma-envio", method = {RequestMethod.POST})
	public void seleccionarFormaEnvio(@RequestParam("idFormaEnvio") Long idFormaEnvio,
													  HttpServletRequest request, 
							   	  					  Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		cestaWebService.seleccionarFormaEnvio(clienteWrapper, idFormaEnvio);
	}
	
	/**
	 * Asigna la Forma de Pago al Pedido y devuelve si esta Forma tiene un paso adicional.
	 * 
	 * @param idFormaEnvio Id de la Forma de Pago.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return JSON que indica si la Forma de Pago tiene un paso adicional.
	 */
	@RequestMapping(value = "/cliente/seleccionar-forma-pago", method = {RequestMethod.POST})
	public @ResponseBody String seleccionarFormaPago(@RequestParam("idFormaPago") Long idFormaPago,
													  HttpServletRequest request, 
							   	  					  Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		String resultado = cestaWebService.seleccionarFormaPago(clienteWrapper, idFormaPago);
		
		return resultado;
	}
	
	/**
	 * Asigna la Forma de Pago al Pedido al Realizar el Pedido.
	 * 
	 * @param idFormaEnvio Id de la Forma de Pago.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return JSON que indica si la Forma de Pago tiene un paso adicional.
	 */
	@RequestMapping(value = "/cliente/seleccionar-forma-pago-rp", method = {RequestMethod.POST})
	public @ResponseBody String seleccionarFormaPagoRP(@RequestParam("idFormaPago") Long idFormaPago,
													  HttpServletRequest request, 
							   	  					  Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		String resultado = cestaWebService.seleccionarFormaPagoRP(clienteWrapper, idFormaPago);
		
		return resultado;
	}
	
	/**
	 * Redirige a la pantalla que indica que no se envían pedidos a esa Provincia.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la pantalla que indica que no se envían pedidos a esa Provincia
	 */
	@RequestMapping(value = "/cliente/no-enviamos-a-provincia", method = RequestMethod.GET)
	public String noEnviamosAprovincia(HttpServletRequest request, 
						 	     		Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan inicioMigaPan = new MigaPan();
		inicioMigaPan.setTexto("Inicio");
		inicioMigaPan.setEnlace("/");
		
		migasPan.addMiga(inicioMigaPan);
		
		//MigaPan rpMigaPan = new MigaPan();
		//rpMigaPan.setTexto("Lo sentimos");
		//rpMigaPan.setEnlace("/cliente/realizar-pedido");
		
		//migasPan.addMiga(rpMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		//Pasamos el clienteWrapper al model.
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		model.addAttribute("clienteWrapper", clienteWrapper);
		
		//Devuelve la Zona con todas las Formas de Envío y Pago y Gastos.
		Zona zona = pedidoWebService.cargarZona(clienteWrapper);
		model.addAttribute("zona", zona);
		
		return "web/cliente/realizar-pedido/no-enviamos-a-provincia";
	} 
}
