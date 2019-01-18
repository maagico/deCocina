package es.cgarcia.decocina.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.admin.formula.DatosFormula;
import es.cgarcia.decocina.admin.formula.FormulaLoader;
import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.admin.util.FormatUtils;
import es.cgarcia.decocina.web.dao.ICestaWebDao;
import es.cgarcia.decocina.web.json.ArticuloJSON;
import es.cgarcia.decocina.web.json.CestaJSON;
import es.cgarcia.decocina.web.manager.CestaManager;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Cesta;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;
import es.cgarcia.decocina.web.model.FormaPago;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;
import es.cgarcia.decocina.web.service.IArticuloWebService;
import es.cgarcia.decocina.web.service.ICestaWebService;
import es.cgarcia.decocina.web.service.IFormaPagoWebService;
import es.cgarcia.decocina.web.service.IZonaWebService;
import es.cgarcia.decocina.web.wrapper.ArticuloCestaWrapper;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;
import freemarker.template.Template;

/**
 * Contiene los métodos necesarios para trabajar con una Cesta de la parte Web. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("cestaWebService")
public class CestaWebServiceImpl extends GenericServiceImpl<Cesta, ICestaWebDao> implements ICestaWebService{

	/**
	 * Servicio para los Artículos.
	 */
	@Autowired
	private IArticuloWebService articuloWebService;
	
	/**
	 * Servicio para las Formas de Envío y Pago.
	 */
	@Autowired
	private IFormaPagoWebService formaPagoWebService;
	
	/**
	 * Servicio para las Zonas de la parte Web.
	 */
	@Autowired
	private IZonaWebService zonaWebService;
	
	/**
	 * Cargador de las Fórmulas.
	 */
	@Autowired
	private FormulaLoader formulaLoader;
	
	/**
	 * FreeMarkerConfigurer.
	 */
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	/**
	 * Constructor.
	 * 
	 * @param cestaWebDao Dao para las Cestas.
	 */
	@Autowired
	public CestaWebServiceImpl(ICestaWebDao cestaWebDao){
		
		super(cestaWebDao);
	}

	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public ClienteWrapper addArticulo(ClienteWrapper clienteWrapper, Long idArticulo, Integer cantidad) {
		
		Articulo articulo = articuloWebService.findById(clienteWrapper, idArticulo);
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		cestaManager.addArticulo(articulo, cantidad);
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		Cliente cliente = clienteWrapper.getCliente();
		
		Long idCliente = cliente.getId();
		
		if(!esInvitado){
			
			//Añadimos a la CestaBD o si existe actualizamos la cantidad.
			Cesta cestaBD = dao.findByIdClienteAndIdArticulo(idCliente, idArticulo);
			
			//No existe lo añadimos a la BD.
			if(cestaBD == null){
			
				cestaBD = new Cesta();
				cestaBD.setCliente(cliente);
				cestaBD.setArticulo(articulo);
				cestaBD.setCantidad(cantidad);
				dao.insert(cestaBD);
			
			}else{
				
				//Existe, actualizamos la cantidad.
				dao.actualizarCantidad(idCliente, idArticulo,cantidad);
			}
				
		}
		
		return clienteWrapper;
	}

	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public String actualizarCantidad(ClienteWrapper clienteWrapper, Long id, Integer cantidad) {
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		
		Articulo articulo = articuloWebService.findById(clienteWrapper, id);
	
		cestaManager.actualizarCantidadArticulo(articulo, cantidad);
		
		Cliente cliente = clienteWrapper.getCliente();
		Long idCliente = cliente.getId();
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		if(!esInvitado){
			
			dao.actualizarCantidad(idCliente, id, cantidad);
		}
		
		Double subTotal = cestaManager.getSubTotal();
		
		DatosFormula datosFormula = new DatosFormula();
		datosFormula.setSubTotal(subTotal);
		
		FormaEnvio formaEnvio = pedidoWrapper.getFormaEnvio();
		
		if(formaEnvio != null){
			
			//Fórmula de Envío.
			String formulaEnvio = formaEnvio.getFormula();
			
			if(formulaEnvio != null){
				
				//Calculamos el Gasto.
				Double gastoFormaEnvio = formulaLoader.aplicarFormula(formulaEnvio, datosFormula);
			
				//Aplicamos el Gasto a la Forma de Envío.
				formaEnvio.setGasto(gastoFormaEnvio);
			}
		}
		
		String JSON = "{}";
		
		try{
			
			CestaJSON cestaJSON = pedidoWrapperToCestaJSON(pedidoWrapper);
			
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("cesta.json");
		
			Map<String, Object> datos = new HashMap<String, Object>();
			datos.put("cestaJSON", cestaJSON);
		
			JSON = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
		
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando plantilla JSON para actualizar la cesta.", e);
		}
		
		return JSON;
	}

	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public String eliminarArticulo(ClienteWrapper clienteWrapper, Long id) {
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		
		Articulo articulo = articuloWebService.findById(clienteWrapper, id);
	
		cestaManager.eliminarArticulo(articulo);
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		if(!esInvitado){
			
			Cliente cliente = clienteWrapper.getCliente();
			Long idCliente = cliente.getId();
	 
			//Eliminamos de la cestaBD.
			dao.deleteByIdClienteAndIdArticulo(idCliente, id);
		}
		
		Double subTotal = cestaManager.getSubTotal();
		
		DatosFormula datosFormula = new DatosFormula();
		datosFormula.setSubTotal(subTotal);
		
		FormaEnvio formaEnvio = pedidoWrapper.getFormaEnvio();
		
		if(formaEnvio != null){
			
			//Fórmula de Envío.
			String formulaEnvio = formaEnvio.getFormula();
			
			if(formulaEnvio != null){
				
				//Calculamos el Gasto.
				Double gastoFormaEnvio = formulaLoader.aplicarFormula(formulaEnvio, datosFormula);
			
				//Aplicamos el Gasto a la Forma de Envío.
				formaEnvio.setGasto(gastoFormaEnvio);
			}
		}
		
		String JSON = "{}";
		
		try{
			
			CestaJSON cestaJSON = pedidoWrapperToCestaJSON(pedidoWrapper);
			
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("cesta.json");
		
			Map<String, Object> datos = new HashMap<String, Object>();
			datos.put("eliminar", String.valueOf(id));
			datos.put("cestaJSON", cestaJSON);
		
			JSON = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
		
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando plantilla JSON para actualizar la cesta.", e);
		}
		
		return JSON;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String recuperarFormasEnvioJSON(ClienteWrapper clienteWrapper, Long idZona) {
		
		String JSON = "{}";
		
		try{
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			if(idZona == null){
				
				pedidoWrapper.setFormaEnvio(null);
				pedidoWrapper.setFormaPago(null);
				
			}else
			{
				Zona zonaBD = zonaWebService.findById(idZona);
				
				//Recuperamos las Formas de Envío y Pago y calculamos los gastos.
				List<ZonaFormaEnvio> zonaFormasEnvio = zonaBD.getZonaFormasEnvio();
				
				pedidoWrapper.setZona(zonaBD);
				
				pedidoWrapper.setFormaEnvio(null);
				pedidoWrapper.setFormaPago(null);
				
				CestaManager cestaManager = pedidoWrapper.getCestaManager();
				Double subTotal = cestaManager.getSubTotal();
				
				DatosFormula datosFormula = new DatosFormula();
				datosFormula.setSubTotal(subTotal);
				
				
				for (ZonaFormaEnvio zonaFormaEnvio : zonaFormasEnvio) {
					
					FormaEnvio formaEnvio = zonaFormaEnvio.getFormaEnvio();
					
					//Fórmula de Envío.
					String formulaEnvio = formaEnvio.getFormula();
					
					//Calculamos el Gasto.
					Double gastoFormaEnvio = formulaLoader.aplicarFormula(formulaEnvio, datosFormula);
					
					//Aplicamos el Gasto a la Forma de Envío.
					formaEnvio.setGasto(gastoFormaEnvio);
				}
				
				Template template = freeMarkerConfigurer.getConfiguration().getTemplate("cesta_formas_envio.json");
			
				Map<String, Object> datos = new HashMap<String, Object>();
				datos.put("zonaFormasEnvio", zonaFormasEnvio);
			
				JSON = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
			}
			
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando plantilla JSON para recuperar las Formas de Envío.", e);
		}
		
		return JSON;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FormaEnvio> recuperarFormasEnvio(Long idZona) {
		
		Zona zonaBD = zonaWebService.findById(idZona);
		
		List<ZonaFormaEnvio> zonaFormasEnvio = zonaBD.getZonaFormasEnvio();
		
		List<FormaEnvio> formasEnvio = new ArrayList<FormaEnvio>();
		
		for (ZonaFormaEnvio zonaFormaEnvio : zonaFormasEnvio) {
			
			FormaEnvio formaEnvio = zonaFormaEnvio.getFormaEnvio();
			
			formasEnvio.add(formaEnvio);
		}
		
		return formasEnvio;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String recuperarFormasPagoJSON(ClienteWrapper clienteWrapper, Long idFormaEnvio) {
		
		String JSON = "{}";
		
		try{
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaBD = zonaWebService.findById(idZona);
			
			//Recuperamos las Formas de Envío y Pago y calculamos los gastos.
			List<ZonaFormaEnvio> zonaFormasEnvio = zonaBD.getZonaFormasEnvio();
			
			List<FormaPago> formasPago = new ArrayList<FormaPago>();
			
			for (ZonaFormaEnvio zonaFormaEnvio : zonaFormasEnvio) {
				
				FormaEnvio formaEnvio = zonaFormaEnvio.getFormaEnvio();
				
				if(formaEnvio.getId().equals(idFormaEnvio)){
					
					List<FormaEnvioFormaPago> formasEnvioFormasPago = formaEnvio.getFormaEnvioFormasPago();
					
					for (FormaEnvioFormaPago formaEnvioFormaPago : formasEnvioFormasPago) {
						
						formasPago.add(formaEnvioFormaPago.getFormaPago());
					}					
				}
			}
			
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("cesta_formas_pago.json");
			
			Map<String, Object> datos = new HashMap<String, Object>();
			datos.put("formasPago", formasPago);
			
			JSON = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
			
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando plantilla JSON para recuperar las Formas de Pago.", e);
		}
		
		return JSON;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FormaPago> recuperarFormasPago(ClienteWrapper clienteWrapper, Long idFormaEnvio) {
			
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		
		Zona zona = pedidoWrapper.getZona();
		
		Long idZona = zona.getId();
		
		Zona zonaBD = zonaWebService.findById(idZona);
		
		//Recuperamos las Formas de Envío y Pago y calculamos los gastos.
		List<ZonaFormaEnvio> zonaFormasEnvio = zonaBD.getZonaFormasEnvio();
		
		List<FormaPago> formasPago = new ArrayList<FormaPago>();
		
		for (ZonaFormaEnvio zonaFormaEnvio : zonaFormasEnvio) {
			
			FormaEnvio formaEnvio = zonaFormaEnvio.getFormaEnvio();
			
			if(formaEnvio.getId().equals(idFormaEnvio)){
				
				List<FormaEnvioFormaPago> formasEnvioFormasPago = formaEnvio.getFormaEnvioFormasPago();
				
				for (FormaEnvioFormaPago formaEnvioFormaPago : formasEnvioFormasPago) {
					
					formasPago.add(formaEnvioFormaPago.getFormaPago());
				}					
			}
		}
		
		return formasPago;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String recuperarGastoFormaEnvio(ClienteWrapper clienteWrapper, Long idFormaEnvio) {
		
		String JSON = "{}";
		
		try{
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			if(idFormaEnvio == null){
				
				pedidoWrapper.setFormaEnvio(null);
				pedidoWrapper.setFormaPago(null);
				
			}else
			{
				
				Zona zona = pedidoWrapper.getZona();
				
				//Recuperamos las Formas de Envío y Pago y calculamos los gastos.
				List<ZonaFormaEnvio> zonaFormasEnvio = zona.getZonaFormasEnvio();
				
				CestaManager cestaManager = pedidoWrapper.getCestaManager();
				Double subTotal = cestaManager.getSubTotal();
				
				DatosFormula datosFormula = new DatosFormula();
				datosFormula.setSubTotal(subTotal);
				
				boolean buscar = true;
				
				for(int i = 0; i < zonaFormasEnvio.size() && buscar; i++){
					
					ZonaFormaEnvio zonaFormaEnvio = zonaFormasEnvio.get(i);
					
					FormaEnvio formaEnvio = zonaFormaEnvio.getFormaEnvio();
					
					if(idFormaEnvio.equals(formaEnvio.getId())){
						
						//Fórmula de Envío.
						String formulaEnvio = formaEnvio.getFormula();
						
						//Calculamos el Gasto.
						Double gastoFormaEnvio = formulaLoader.aplicarFormula(formulaEnvio, datosFormula);
						
						//Aplicamos el Gasto a la Forma de Envío.
						formaEnvio.setGasto(gastoFormaEnvio);
						
						//Añadimos la Forma de Envío seleccionada al Pedido.
						pedidoWrapper.setFormaEnvio(formaEnvio);
						
						Double total = pedidoWrapper.getPrecio();
						
						buscar = false;
						
						Template template = freeMarkerConfigurer.getConfiguration().getTemplate("cesta_gasto_forma_envio.json");
						
						Map<String, Object> datos = new HashMap<String, Object>();
						datos.put("gasto", FormatUtils.formatDouble(gastoFormaEnvio));
						datos.put("total", FormatUtils.formatDouble(total));
						
						JSON = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
						
					}
				}
			}
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando plantilla JSON para recuperar el Gasto de la Forma de Envío.", e);
		}
		
		return JSON;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String recuperarGastoFormaPago(ClienteWrapper clienteWrapper, Long idFormaPago) {
		
		String JSON = "{}";
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();

		try{
			
			if(idFormaPago != null){
				
				CestaManager cestaManager = pedidoWrapper.getCestaManager();
				Double subTotal = cestaManager.getSubTotal();
				
				DatosFormula datosFormula = new DatosFormula();
				datosFormula.setSubTotal(subTotal);
				
				
				FormaPago formaPago = formaPagoWebService.findById(idFormaPago);
				
				//Fórmula de Pago.
				String formulaPago = formaPago.getFormula();
				
				//Calculamos el Gasto.
				Double gastoFormaPago = formulaLoader.aplicarFormula(formulaPago, datosFormula);
				
				//Aplicamos el Gasto a la Forma de Pago.
				formaPago.setGasto(gastoFormaPago);
				
				//Añadimos la Forma de Pago seleccionada al Pedido.
				pedidoWrapper.setFormaPago(formaPago);
				
				Double total = pedidoWrapper.getPrecio();
				
				Template template = freeMarkerConfigurer.getConfiguration().getTemplate("cesta_gasto_forma_pago.json");
				
				Map<String, Object> datos = new HashMap<String, Object>();
				datos.put("gasto", FormatUtils.formatDouble(gastoFormaPago));
				datos.put("total", FormatUtils.formatDouble(total));
				
				JSON = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
			}
		
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando plantilla JSON para recuperar el Gasto de la Forma de Pago.", e);
		}
		
		return JSON;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void seleccionarFormaEnvio(ClienteWrapper clienteWrapper, Long idFormaEnvio) {
		
		try{
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			if(idFormaEnvio == null){
				
				pedidoWrapper.setFormaEnvio(null);
				pedidoWrapper.setFormaPago(null);
				
			}else
			{
				
				CestaManager cestaManager = pedidoWrapper.getCestaManager();
				Double subTotal = cestaManager.getSubTotal();
				
				DatosFormula datosFormula = new DatosFormula();
				datosFormula.setSubTotal(subTotal);
				
				Zona zona = pedidoWrapper.getZona();
				
				//Recuperamos las Formas de Envío y Pago y calculamos los gastos.
				List<ZonaFormaEnvio> zonaFormasEnvio = zona.getZonaFormasEnvio();
				
				boolean buscar = true;
				
				for(int i = 0; i < zonaFormasEnvio.size() && buscar; i++){
					
					ZonaFormaEnvio zonaFormaEnvio = zonaFormasEnvio.get(i);
					
					FormaEnvio formaEnvio = zonaFormaEnvio.getFormaEnvio();
					
					if(idFormaEnvio.equals(formaEnvio.getId())){
						
						//Fórmula de Envío.
						String formulaEnvio = formaEnvio.getFormula();
						
						//Calculamos el Gasto.
						Double gastoFormaEnvio = formulaLoader.aplicarFormula(formulaEnvio, datosFormula);
						
						//Aplicamos el Gasto a la Forma de Envío.
						formaEnvio.setGasto(gastoFormaEnvio);
						
						//Añadimos la Forma de Envío seleccionada al Pedido.
						pedidoWrapper.setFormaEnvio(formaEnvio);
						pedidoWrapper.setFormaPago(null);
						
						buscar = false;
					}
				}
			}
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error seleccionando la Forma de Envío.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String seleccionarFormaPago(ClienteWrapper clienteWrapper, Long idFormaPago) {
		
		String JSON = "{}";
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();

		try{
			if(idFormaPago == null){
			
				pedidoWrapper.setFormaPago(null);
				
			}else
			{		
				CestaManager cestaManager = pedidoWrapper.getCestaManager();
				Double subTotal = cestaManager.getSubTotal();
				
				DatosFormula datosFormula = new DatosFormula();
				datosFormula.setSubTotal(subTotal);
				
				FormaEnvio formaEnvio = pedidoWrapper.getFormaEnvio();
				
				if(formaEnvio != null)
				{
					List<FormaEnvioFormaPago> formasEnvioFormasPago = formaEnvio.getFormaEnvioFormasPago();
					
					boolean buscar = true;
					
					for(int i = 0; i < formasEnvioFormasPago.size() && buscar; i++){
						
						FormaEnvioFormaPago formaEnvioFormaPago = formasEnvioFormasPago.get(i);
						
						FormaPago formaPago = formaEnvioFormaPago.getFormaPago();
						
						if(formaPago.getId().equals(idFormaPago)){
							
							//Fórmula de Pago.
							String formulaPago = formaPago.getFormula();
							
							//Calculamos el Gasto.
							Double gastoFormaPago = formulaLoader.aplicarFormula(formulaPago, datosFormula);
							
							//Aplicamos el Gasto a la Forma de Pago.
							formaPago.setGasto(gastoFormaPago);
							
							//Añadimos la Forma de Pago seleccionada al Pedido.
							pedidoWrapper.setFormaPago(formaPago);
							
							Boolean pasoAdicional = formaPago.getPasoAdicional();
							String nombreBoton = formaPago.getNombreBoton();
							
							buscar = false;
							
							Template template = freeMarkerConfigurer.getConfiguration().getTemplate("seleccionar_forma_pago.json");
							
							Map<String, Object> datos = new HashMap<String, Object>();
							
							datos.put("pasoAdicional", pasoAdicional);
							datos.put("nombreBoton", nombreBoton);
							
							JSON = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
						}
					}
				}
			}
		
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando plantilla JSON para recuperar el Gasto de la Forma de Pago.", e);
		}
		
		return JSON;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String seleccionarFormaPagoRP(ClienteWrapper clienteWrapper, Long idFormaPago) {
	
		String JSON = "{}";
		
		if(idFormaPago == null || idFormaPago.equals("")){
			
			return JSON;
		}
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();

		try{
			
			FormaPago formaPago = formaPagoWebService.findById(idFormaPago);
			
			if(formaPago == null){
				
				return JSON;
			}
			
			CestaManager cestaManager = pedidoWrapper.getCestaManager();
			Double subTotal = cestaManager.getSubTotal();
			
			DatosFormula datosFormula = new DatosFormula();
			datosFormula.setSubTotal(subTotal);
			
			//Fórmula de Pago.
			String formulaPago = formaPago.getFormula();
			
			//Calculamos el Gasto.
			Double gastoFormaPago = formulaLoader.aplicarFormula(formulaPago, datosFormula);
			
			//Aplicamos el Gasto a la Forma de Pago.
			formaPago.setGasto(gastoFormaPago);
			
			//Añadimos la Forma de Pago seleccionada al Pedido.
			pedidoWrapper.setFormaPago(formaPago);
			
			Boolean pasoAdicional = formaPago.getPasoAdicional();
			String nombreBoton = formaPago.getNombreBoton();
			
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("seleccionar_forma_pago.json");
			
			Map<String, Object> datos = new HashMap<String, Object>();
			
			datos.put("pasoAdicional", pasoAdicional);
			datos.put("nombreBoton", nombreBoton);
			
			JSON = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
		
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando plantilla JSON para recuperar el Gasto de la Forma de Pago.", e);
		}
		
		return JSON;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(List<ArticuloCestaWrapper> articulosCestaWrapper) {
		
		for (ArticuloCestaWrapper articuloCestaWrapper : articulosCestaWrapper) {
			
			Cliente cliente = articuloCestaWrapper.getCliente();
			Articulo articulo = articuloCestaWrapper.getArticulo();
			Integer cantidad = articuloCestaWrapper.getCantidad();
			
			Cesta cesta = new Cesta();
			cesta.setCliente(cliente);
			cesta.setArticulo(articulo);
			cesta.setCantidad(cantidad);
			
			Long idArticulo = articulo.getId();
			Long idCliente = cliente.getId();
			
			//Comrobamos si existe el Artículo en la cesta. 
			Cesta cestaBD = dao.findByIdClienteAndIdArticulo(idCliente, idArticulo);
			
			// Si no existe lo insertamos.
			if(cestaBD == null)
			{
				dao.insert(cesta);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addArticulosCestaManager(ClienteWrapper clienteWrapper, Long idCliente, CestaManager cestaManager) {
		
		List<Cesta> cestas = dao.findByIdCliente(idCliente);
		
		for (Cesta cesta : cestas) {
			
			Articulo articulo = cesta.getArticulo();
			Long idArticulo = articulo.getId();
			
			Articulo articuloBD = articuloWebService.findById(clienteWrapper, idArticulo);
			
			Integer cantidad = cesta.getCantidad();
			
			cestaManager.addArticulo(articuloBD, cantidad);
		}
	}
	
	/**
	 * Convierte un pedidoWrapper a CestaJSON.
	 * @param pedidoWrapper PedidoWrapper.
	 * @return PedidoWrapper a CestaJSON.
	 */
	private CestaJSON pedidoWrapperToCestaJSON(PedidoWrapper pedidoWrapper) {
		
		CestaJSON cestaJSON = new CestaJSON();
		
		//Precio total de la cesta, Gastos de Envío , pago y Artículos.
		String precioTotalCesta = FormatUtils.formatDouble(pedidoWrapper.getPrecio());
		
		cestaJSON.setPrecioTotal(precioTotalCesta);
		
		
		FormaEnvio formaEnvio = pedidoWrapper.getFormaEnvio();
		
		if(formaEnvio != null){
			
			Double gasto = formaEnvio.getGasto();
		
			String gastoFormaEnvio = "";
		
			if(gasto != null){
				
				gastoFormaEnvio = FormatUtils.formatDouble(gasto);
			}
		
			cestaJSON.setGastoFormaEnvio(gastoFormaEnvio);
		}
		
		/*
		FormaPago formaPago =  pedidoWrapper.getFormaPago();
		String gastoFormaPago = FormatUtils.formatDouble(formaPago.getGasto());
		*/
		
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		
		String subTotal = FormatUtils.formatDouble(cestaManager.getSubTotal());
		
		cestaJSON.setSubTotal(subTotal);
		
		List<ArticuloCestaWrapper> articulosCestaWrapper = cestaManager.getCesta();
		
		for (ArticuloCestaWrapper articuloCestaWrapper : articulosCestaWrapper) {
			
			Articulo articulo = articuloCestaWrapper.getArticulo();
			
			Long id = articulo.getId();
			
			String precio = FormatUtils.formatDouble(articulo.getPrecio());
			Integer cantidad = articuloCestaWrapper.getCantidad();
			
			//Total, Artículo x Cantidad.
			String precioTotal = FormatUtils.formatDouble(articuloCestaWrapper.getPrecio());
			
			ArticuloJSON articuloJSON = new ArticuloJSON();
			
			articuloJSON.setId(id);
			articuloJSON.setPrecio(precio);
			articuloJSON.setCantidad(cantidad);
			articuloJSON.setPrecioTotal(precioTotal);
			
			cestaJSON.addArticulo(articuloJSON);
		}
		
		return cestaJSON;
	}
}