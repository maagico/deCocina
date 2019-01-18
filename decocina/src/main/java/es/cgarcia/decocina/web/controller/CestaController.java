package es.cgarcia.decocina.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.cgarcia.decocina.web.manager.CestaManager;
import es.cgarcia.decocina.web.miga.MigaPan;
import es.cgarcia.decocina.web.miga.MigasPan;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.model.FormaPago;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.service.ICestaWebService;
import es.cgarcia.decocina.web.service.IZonaWebService;
import es.cgarcia.decocina.web.wrapper.ArticuloCestaWrapper;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;

/**
 * Controller de la Cesta del Cliente.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class CestaController extends WebControllerBase{

	/**
	 * Servicio para la Cesta.
	 */
	@Autowired
	private ICestaWebService cestaWebService;
	
	/**
	 * Servicio para las Zonas.
	 */
	@Autowired
	private IZonaWebService zonaWebService;
	
	/**
	 * Añade un Artículo a la Cesta.
	 * 
	 * @param id Id del Artículo.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la Cesta. 
	 */
	@RequestMapping(value = "/add-articulo-cesta", method = RequestMethod.GET)
	public String addArticuloCesta(@RequestParam("id") Long id, 
								   HttpServletRequest request, 
						   		   Model model){
		
		MigasPan migasPan = crearMigasPan();
		
		mostrarMigasPan(migasPan, model);
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		clienteWrapper = cestaWebService.addArticulo(clienteWrapper, id, 1);
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		List<ArticuloCestaWrapper> articulos = cestaManager.getCesta();
		
		model.addAttribute("articulos", articulos);
		
		return "redirect:/ver-cesta";
	}
	
	/**
	 * Redirige a la cesta.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Redirige a la Cesta y muestra los Artículos. 
	 */
	@RequestMapping(value = "/ver-cesta", method = RequestMethod.GET)
	public String verCesta(HttpServletRequest request, 
						   Model model){
		
		MigasPan migasPan = crearMigasPan();
		
		mostrarMigasPan(migasPan, model);
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		model.addAttribute("clienteWrapper", clienteWrapper);
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		model.addAttribute("pedidoWrapper", pedidoWrapper);
		
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		
		List<ArticuloCestaWrapper> articulos = cestaManager.getCesta();
		
		model.addAttribute("articulos", articulos);
		
		model.addAttribute("mostrarCesta", false);
		
		List<Zona> zonas = zonaWebService.findAllProvinciasActivas();
		model.addAttribute("zonas", zonas);
		
		Zona zona = pedidoWrapper.getZona();
		
		if(zona != null && zona.getId() != null){
			
			Long idZona = zona.getId();
			
			List<FormaEnvio> formasEnvio = cestaWebService.recuperarFormasEnvio(idZona);
			model.addAttribute("formasEnvio", formasEnvio);
			
			FormaEnvio formaEnvio = pedidoWrapper.getFormaEnvio();
			
			if(formaEnvio != null){
				
				Long idFormaEnvio = formaEnvio.getId();
				
				List<FormaPago> formasPago = cestaWebService.recuperarFormasPago(clienteWrapper, idFormaEnvio);
				model.addAttribute("formasPago", formasPago);
			}
		}
		
		return "web/ver-cesta/ver-cesta";
	}
	
	/**
	 * Método Ajax que actualiza la cantidad de un Artículo.
	 * 
	 * @param id Id del Artículo.
	 * @param cantidad Cantidad a actualizar.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Devuelve el JSON con los datos necesarios. 
	 */
	@RequestMapping(value = "/actualizar-cantidad", method = RequestMethod.POST)
	public @ResponseBody String actualizarCantidad(@RequestParam("id") Long id, 
												   @RequestParam("cantidad") Integer cantidad, 
												   HttpServletRequest request, 
						   			 			   Model model){
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		String resultado = cestaWebService.actualizarCantidad(clienteWrapper, id, cantidad);
		
		return resultado;
	}
	
	/**
	 * Método Ajax que elimina un Artículo de la cesta.
	 * 
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Devuelve el JSON con los datos necesarios. 
	 */
	@RequestMapping(value = "/eliminar-articulo", method = RequestMethod.POST)
	public @ResponseBody String eliminarArticulo(@RequestParam("id") Long id, 
												 HttpServletRequest request, 
						   			 			 Model model){
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		String resultado = cestaWebService.eliminarArticulo(clienteWrapper, id);
		
		return resultado;
	}
	
	/**
	 * Método Ajax que devuelve las Formas de Envío de una Zona.
	 * 
	 * @param idZona Id de la Zona.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Devuelve el JSON con los datos necesarios. 
	 */
	@RequestMapping(value = "/recuperar-formas-envio", method = RequestMethod.POST)
	public @ResponseBody String recuperarFormasEnvio(@RequestParam("idZona") Long idZona,
												   	 HttpServletRequest request, 
												   	 Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		String resultado = cestaWebService.recuperarFormasEnvioJSON(clienteWrapper, idZona);
		
		return resultado;
	}
	
	/**
	 * Método Ajax que devuelve las Formas de Pago de una Zona y su Forma de Envío.
	 * 
	 * @param idZona Id de la Zona.
	 * @param idFormaEnvio Id de la Forma de Envío.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Devuelve el JSON con los datos necesarios. 
	 */
	@RequestMapping(value = "/recuperar-formas-pago", method = RequestMethod.POST)
	public @ResponseBody String recuperarFormasPago(@RequestParam("idFormaEnvio") Long idFormaEnvio,
												   	HttpServletRequest request, 
												   	Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		String resultado = cestaWebService.recuperarFormasPagoJSON(clienteWrapper, idFormaEnvio);
		
		return resultado;
	}
	
	/**
	 * Método Ajax que devuelve el gasto de una Forma de Envío de una Zona.
	 * 
	 * @param idZona Id de la Forma de Envio.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Devuelve el JSON con los datos necesarios. 
	 */
	@RequestMapping(value = "/recuperar-gasto-forma-envio", method = RequestMethod.POST)
	public @ResponseBody String recuperarGastoFormaEnvio(@RequestParam("idFormaEnvio") Long idFormaEnvio,
												   		 HttpServletRequest request, 
												   		 Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		String resultado = cestaWebService.recuperarGastoFormaEnvio(clienteWrapper, idFormaEnvio);
		
		return resultado;
	}
	
	/**
	 * Método Ajax que devuelve el gasto de una Forma de Pago de una Forma de Envío.
	 * 
	 * @param idZona Id de la Forma de Pago.
	 * @param request HttpServletRequest.
	 * @param model Model
	 * @return Devuelve el JSON con los datos necesarios. 
	 */
	@RequestMapping(value = "/recuperar-gasto-forma-pago", method = RequestMethod.POST)
	public @ResponseBody String recuperarGastoFormaPago(@RequestParam("idFormaPago") Long idFormaPago,
												   		HttpServletRequest request, 
												   		Model model){
		
		ClienteWrapper clienteWrapper = getClienteWrapper(request);
		
		String resultado = cestaWebService.recuperarGastoFormaPago(clienteWrapper, idFormaPago);
		
		return resultado;
	}
	
	/**
	 * Crea las Migas de Pan.
	 * @return Devuelve las Migas de Pan.
	 */
	private MigasPan crearMigasPan()
	{
		MigasPan migasPan = new MigasPan();
		
		MigaPan inicioMigaPan = new MigaPan();
		inicioMigaPan.setTexto("Inicio");
		inicioMigaPan.setEnlace("/");
		
		migasPan.addMiga(inicioMigaPan);
		
		MigaPan CestaMigaPan = new MigaPan();
		CestaMigaPan.setTexto("Mi cesta");
		//CestaMigaPan.setEnlace("/ver-cesta");
		
		migasPan.addMiga(CestaMigaPan);
		
		return migasPan; 
	}
}
