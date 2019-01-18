package es.cgarcia.decocina.web.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.admin.formula.DatosFormula;
import es.cgarcia.decocina.admin.formula.FormulaLoader;
import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.admin.util.FechaUtils;
import es.cgarcia.decocina.admin.util.FormatUtils;
import es.cgarcia.decocina.admin.util.ImagenUtils;
import es.cgarcia.decocina.admin.util.TextUtils;
import es.cgarcia.decocina.web.analytics.ArticuloAnalytics;
import es.cgarcia.decocina.web.analytics.TransaccionAnalytics;
import es.cgarcia.decocina.web.dao.IArticuloPedidoWebDao;
import es.cgarcia.decocina.web.dao.IArticuloWebDao;
import es.cgarcia.decocina.web.dao.ICestaWebDao;
import es.cgarcia.decocina.web.dao.IFormaEnvioWebDao;
import es.cgarcia.decocina.web.dao.IFormaPagoWebDao;
import es.cgarcia.decocina.web.dao.IPedidoEstadoHistorialWebDao;
import es.cgarcia.decocina.web.dao.IPedidoWebDao;
import es.cgarcia.decocina.web.exception.DeCocinaWebRuntimeException;
import es.cgarcia.decocina.web.form.PedidoForm;
import es.cgarcia.decocina.web.job.MailPedidoTimer;
import es.cgarcia.decocina.web.job.MailStockTimer;
import es.cgarcia.decocina.web.manager.CestaManager;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.ArticuloPedido;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Direccion;
import es.cgarcia.decocina.web.model.Estado;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;
import es.cgarcia.decocina.web.model.FormaPago;
import es.cgarcia.decocina.web.model.Impuesto;
import es.cgarcia.decocina.web.model.ImpuestoTotal;
import es.cgarcia.decocina.web.model.Pedido;
import es.cgarcia.decocina.web.model.PedidoEstadoHistorial;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;
import es.cgarcia.decocina.web.service.IPedidoWebService;
import es.cgarcia.decocina.web.service.IZonaWebService;
import es.cgarcia.decocina.web.util.Constantes;
import es.cgarcia.decocina.web.wrapper.ArticuloCestaWrapper;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;
import freemarker.template.TemplateException;
import urn.ebay.api.PayPalAPI.DoAuthorizationReq;
import urn.ebay.api.PayPalAPI.DoAuthorizationRequestType;
import urn.ebay.api.PayPalAPI.DoCaptureReq;
import urn.ebay.api.PayPalAPI.DoCaptureRequestType;
import urn.ebay.api.PayPalAPI.DoCaptureResponseType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CompleteCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.ItemCategoryType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

/**
 * Contiene los métodos necesarios para trabajar con un Pedido de la parte Web. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("pedidoWebService")
public class PedidoWebServiceImpl extends GenericServiceImpl<Pedido, IPedidoWebDao> implements IPedidoWebService{

	/**
	 * Propiedades de configuración.
	 */
	@Resource(name = "confProperties")
	private Properties properties;
	
	/**
	 * Servicio para las Zonas de la parte Web.
	 */
	@Autowired
	private IZonaWebService zonaWebService;
	
	/**
	 * Dao para la Forma de Envío.
	 */
	@Autowired
	private IFormaEnvioWebDao formaEnvioWebDao;
	
	/**
	 * Dao para la Forma de Pago.
	 */
	@Autowired
	private IFormaPagoWebDao formaPagoWebDao;
	
	/**
	 * Dao para los estados de un Pedido.
	 */
	@Autowired
	private IPedidoEstadoHistorialWebDao pedidoEstadoHistorialWebDao;
	
	/**
	 * Dao para los Artículos.
	 */
	@Autowired
	private IArticuloWebDao articuloWebDao;
	
	/**
	 * Dao para los Artículos de un Pedido.
	 */
	@Autowired
	private IArticuloPedidoWebDao articuloPedidoWebDao;
	
	/**
	 * Dao para la Cesta.
	 */
	@Autowired
	private ICestaWebDao cestaWebDao;
	
	/**
	 * Cargador de las Fórmulas.
	 */
	@Autowired
	private FormulaLoader formulaLoader;
	
	/**
	 * Envío de email al usuario cuando se crea el Pedido.
	 */
	@Autowired
	private MailPedidoTimer mailPedidoTimer;
	
	/**
	 * Envío de stock.
	 */
	@Autowired
	private MailStockTimer mailStockTimer;
	
	/**
	 * Constructor.
	 * 
	 * @param pedidoWebDao Dao para los Pedidos.
	 */
	@Autowired
	public PedidoWebServiceImpl(IPedidoWebDao pedidoWebDao){
		
		super(pedidoWebDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zona cargarZona(ClienteWrapper clienteWrapper) {
		
		Cliente cliente = clienteWrapper.getCliente();
		Direccion direccion = cliente.getDireccion();
		Zona zona = direccion.getZona();
		
		Long idZona = zona.getId();
		
		Zona zonaBD = zonaWebService.findById(idZona);
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		Double subTotal = cestaManager.getSubTotal();
		
		DatosFormula datosFormula = new DatosFormula();
		datosFormula.setSubTotal(subTotal);
		
		//Recuperamos las Formas de Envío y Pago y calculamos los gastos.
		List<ZonaFormaEnvio> zonaFormasEnvio = zonaBD.getZonaFormasEnvio();
		
		for (ZonaFormaEnvio zonaFormaEnvio : zonaFormasEnvio) {
			
			FormaEnvio formaEnvio = zonaFormaEnvio.getFormaEnvio();
			
			//Fórmula de Envío.
			String formulaEnvio = formaEnvio.getFormula();
			
			//Calculamos el Gasto.
			Double gastoFormaEnvio = formulaLoader.aplicarFormula(formulaEnvio, datosFormula);
			
			//Aplicamos el Gasto a la Forma de Envío.
			formaEnvio.setGasto(gastoFormaEnvio);
			
			//Recuperamos las Formas de Pago.
			List<FormaEnvioFormaPago> formaEnvioFormasPago = formaEnvio.getFormaEnvioFormasPago();
			
			for (FormaEnvioFormaPago formaEnvioFormaPago : formaEnvioFormasPago) {
				
				FormaPago formaPago = formaEnvioFormaPago.getFormaPago();
				
				//Fórmula de pago.
				String formulaPago = formaPago.getFormula();
				
				//Descripción de la forma de pago.
				String descripcion = formaPago.getDescripcion();
				
				//Gasto de la Forma de Pago.
				Double gastoFormaPago = formulaLoader.aplicarFormula(formulaPago, datosFormula);
				
				//Se añade el Gasto a la Forma de Pago. 
				formaPago.setGasto(gastoFormaPago);
				
				//Descripción con el Token(Gasto de la Forma de Pago) añadido.
				String descripcionConToken = TextUtils.addToken(descripcion, FormatUtils.formatDouble(gastoFormaPago));
				
				//Añadimos la descripción con el Token(Gasto de la Forma de Pago).
				formaPago.setDescripcion(descripcionConToken);
			}
		}
		
		return zonaBD;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void confirmarPedido(PedidoForm pedidoForm,
								ClienteWrapper clienteWrapper) {
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		Double subTotal = cestaManager.getSubTotal();
		
		DatosFormula datosFormula = new DatosFormula();
		datosFormula.setSubTotal(subTotal);
		
		Long idFormaEnvio = pedidoForm.getFormaEnvio();
		
		//Recuperamos la Forma de Envío.
		FormaEnvio formaEnvio = formaEnvioWebDao.findById(idFormaEnvio);
		
		//Fórmula de Envío.
		String formulaEnvio = formaEnvio.getFormula();
		
		//Calculamos el Gasto.
		Double gastoFormaEnvio = formulaLoader.aplicarFormula(formulaEnvio, datosFormula);
		
		//Aplicamos el Gasto a la Forma de Envío.
		formaEnvio.setGasto(gastoFormaEnvio);
		
		//Añadimos la Forma de Envio al PedidoWrapper.
		pedidoWrapper.setFormaEnvio(formaEnvio);
		
		//Id de la Forma de Pago.
		Long idFormaPago = pedidoForm.getFormaPago();
		
		//Recuperamos la Forma de Envío.
		FormaPago formaPago = formaPagoWebDao.findById(idFormaPago);
		
		//Fórmula de Pago.
		String formulaPago = formaPago.getFormula();
		
		//Descripción de la forma de pago.
		String descripcion = formaPago.getDescripcion();
		
		//Calculamos el Gasto.
		Double gastoFormaPago = formulaLoader.aplicarFormula(formulaPago, datosFormula);
		
		//Se añade el Gasto a la Forma de Pago. 
		formaPago.setGasto(gastoFormaPago);
		
		//Descripción con el Token(Gasto de la Forma de Pago) añadido.
		String descripcionConToken = TextUtils.addToken(descripcion, FormatUtils.formatDouble(gastoFormaPago));
		
		//Añadimos la descripción con el Token(Gasto de la Forma de Pago).
		formaPago.setDescripcion(descripcionConToken);
		
		//Añadimos la Forma de Pago al PedidoWrapper.
		pedidoWrapper.setFormaPago(formaPago);
				
		String observaciones = pedidoForm.getObservaciones();
		
		pedidoWrapper.setObservaciones(observaciones);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getURLSiguiente(ClienteWrapper clienteWrapper) {
		
		String url = "finalizar-pedido";
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		
		FormaPago formaPago = pedidoWrapper.getFormaPago();
		
		Boolean pasoAdicional = formaPago.getPasoAdicional();
	
		if(pasoAdicional){
			
			url = formaPago.getUrl();
		}
		
		return url;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransaccionAnalytics finalizarPedido(ClienteWrapper clienteWrapper, String rutaPublicaDestino, String rutaPrivadaOrigenReal, String rutaPrivadaDestinoReal, String host, String token, String payerId) {
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		
		Cliente cliente = clienteWrapper.getCliente();
		
		String nombreCliente = cliente.getNombre();
		String apellidosCliente = cliente.getApellidos();
		
		StringBuffer sbNombreApellidosCliente = new StringBuffer(nombreCliente);
		sbNombreApellidosCliente.append(' ');
		sbNombreApellidosCliente.append(apellidosCliente);
		
		Pedido pedido = new Pedido();
		
		pedido.setCliente(cliente);
		pedido.setNombreCliente(sbNombreApellidosCliente.toString());
		
		pedido.setPais(Constantes.TEXTO_ESPAÑA);
		
		Direccion direccion = cliente.getDireccion();
		
		String calle = direccion.getCalle();
		pedido.setCalle(calle);
		
		Zona zona = direccion.getZona();
	
		Long idZona = zona.getId();
		
		Zona zonaBD = zonaWebService.findById(idZona);
		
		String nombreZona = zonaBD.getNombre();
		pedido.setZona(nombreZona);
		
		String poblacion = direccion.getPoblacion();
		pedido.setPoblacion(poblacion);
		
		String codigoPostal = direccion.getCodigoPostal();
		pedido.setCodigoPostal(codigoPostal);
		
		String telefono = cliente.getTelefono();
		pedido.setTelefono(telefono);
		
		String email = cliente.getEmail();
		pedido.setEmail(email);
		
		FormaPago formaPago = pedidoWrapper.getFormaPago();
		
		Long idFormaPago = formaPago.getId();
		
		if(idFormaPago.equals(Constantes.ID_FORMA_PAGO_PAYPAL)){
			
			finalizarPedidoPaypal(clienteWrapper, token, payerId);
		}
		
		String nombreFormaPago = formaPago.getNombre();
		pedido.setNombreFormaPago(nombreFormaPago);
		
		String precioFormaPago = FormatUtils.formatDouble(formaPago.getGasto());
		pedido.setPrecioFormaPago(precioFormaPago);
		
		String descripcionFormaPago = formaPago.getDescripcion();
		pedido.setDescripcionFormaPago(descripcionFormaPago);
		
		String comentarioFormaPago = formaPago.getComentario();
		pedido.setComentarioFormaPago(comentarioFormaPago);
		
		FormaEnvio formaEnvio = pedidoWrapper.getFormaEnvio();
		
		String nombreFormaEnvio = formaEnvio.getNombre();
		pedido.setNombreFormaEnvio(nombreFormaEnvio);
		
		String descripcionFormaEnvio = formaEnvio.getDescripcion();
		pedido.setDescripcionFormaEnvio(descripcionFormaEnvio);
		
		String precioFormaEnvio = FormatUtils.formatDouble(formaEnvio.getGasto());
		pedido.setPrecioFormaEnvio(precioFormaEnvio);
		
		Calendar fecha = Calendar.getInstance();
	
		pedido.setFechaCreacion(fecha);
		pedido.setFechaModificacion(fecha);
		
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		List<ImpuestoTotal> impuestosTotales = cestaManager.getImpuestosTotales();
		
		StringBuffer sbImpuestos = new StringBuffer();
		StringBuffer sbImpuestosDireccion = new StringBuffer();
		
		//Sólo para la plantilla Freemarker y no para la base de datos.
		StringBuffer sbImpuestosPlantilla = new StringBuffer();
		
		Double totalImpuestos = 0D;
		
		for (ImpuestoTotal impuestoTotal : impuestosTotales) {
			
			Impuesto impuesto = impuestoTotal.getImpuesto();
			Double valor = impuesto.getValor();
			
			Double total = impuestoTotal.getTotal();
			
			totalImpuestos += total;
			
			sbImpuestos.append(FormatUtils.formatDouble(valor));
			sbImpuestos.append('%');
			sbImpuestos.append(" IVA: ");
			sbImpuestos.append(FormatUtils.formatDouble(total));
			sbImpuestos.append('|');
			
			sbImpuestosDireccion.append(FormatUtils.formatDouble(valor));
			sbImpuestosDireccion.append('|');
			
			//Plantilla sólo para Freemarker.
			sbImpuestosPlantilla.append("<span class = \"margen-derecha\">(");
			sbImpuestosPlantilla.append(FormatUtils.formatDouble(valor));
			sbImpuestosPlantilla.append('%');
			sbImpuestosPlantilla.append(" IVA: ");
			sbImpuestosPlantilla.append(FormatUtils.formatDouble(total));
			sbImpuestosPlantilla.append(")</span>");
			sbImpuestosPlantilla.append("<BR>");
		}
		
		pedido.setIvaTotal(sbImpuestos.toString());
		pedido.setIvaDireccion(sbImpuestosDireccion.toString());
		
		String precioTotal  = FormatUtils.formatDouble(pedidoWrapper.getPrecio());
		pedido.setPrecioTotal(precioTotal);
		
		pedido.setComentario(null);
		
		String precioTotalCesta = FormatUtils.formatDouble(cestaManager.getSubTotal());
		pedido.setPrecioTotalCesta(precioTotalCesta);
		
		String pesoFormaEnvio = String.valueOf(cestaManager.getPeso());
		pedido.setPesoFormaEnvio(pesoFormaEnvio);
		
		String observaciones = pedidoWrapper.getObservaciones();
		pedido.setObservaciones(observaciones);
		
		pedido.setIvaDireccion(sbImpuestosDireccion.toString());
		
		dao.insert(pedido);

		Long idPedido = pedido.getId();
		
		String strIdPedido = String.valueOf(idPedido); 
		
		//Transacción de un pedido de analytics.
		TransaccionAnalytics transaccionAnalytics = new TransaccionAnalytics();
		transaccionAnalytics.setId(strIdPedido);
		transaccionAnalytics.setRevenue(FormatUtils.redondearDouble(pedidoWrapper.getPrecio()));
		transaccionAnalytics.setShipping(FormatUtils.redondearDouble(formaEnvio.getGasto()));
		transaccionAnalytics.setTax(FormatUtils.redondearDouble(totalImpuestos));
		
		//Se necesita el id del pedido. Hay que insertar el pedido antes y luego crear el estado.
		PedidoEstadoHistorial pedidoHistorialEstado = new PedidoEstadoHistorial();
		pedidoHistorialEstado.setPedido(pedido);
		
		//Procesado.
		Estado estado = new Estado();
		estado.setId(2L);
		
		pedidoHistorialEstado.setEstado(estado);
		pedidoHistorialEstado.setFecha(Calendar.getInstance());
		pedidoHistorialEstado.setClienteNotificado(true);
		pedidoHistorialEstado.setComentario(null);
		
		pedidoEstadoHistorialWebDao.insert(pedidoHistorialEstado);
		
		Long idPedidoHistorialEstado = pedidoHistorialEstado.getId();
		
		pedido.setEstadoHistorial(pedidoHistorialEstado);
		
		dao.updateEstadoHistorial(idPedido, idPedidoHistorialEstado);
		
		List<ArticuloCestaWrapper> articulosCestaWrapper = cestaManager.getCesta();
		
		List<ArticuloPedido> articulosPedido = new ArrayList<ArticuloPedido>();
		
		for (ArticuloCestaWrapper articuloCestaWrapper : articulosCestaWrapper) {
			
			Articulo articulo = articuloCestaWrapper.getArticulo();
			
			ArticuloPedido articuloPedido = new ArticuloPedido();
			
			articuloPedido.setPedido(pedido);
			articuloPedido.setArticulo(articulo);
			
			String nombre = articulo.getNombre();
			articuloPedido.setNombre(nombre);
			
			String nombreImagen = articulo.getImagenT().getNombre();
			
			StringBuffer sbUri = new StringBuffer();
			sbUri.append(rutaPublicaDestino);
			sbUri.append(nombreImagen);
			
			articuloPedido.setUri(sbUri.toString());
			
			Double precio = articulo.getPrecio();
			articuloPedido.setPrecio(FormatUtils.formatDouble(precio));
			
			Impuesto impuesto = articulo.getImpuesto();
			Double iva = impuesto.getValor();
			articuloPedido.setIva(FormatUtils.formatDouble(iva));
			
			Integer cantidad = articuloCestaWrapper.getCantidad();
			articuloPedido.setCantidad(String.valueOf(cantidad));
			
			Double precioTotalArticulo = articuloCestaWrapper.getPrecio();
			articuloPedido.setPrecioTotal(FormatUtils.formatDouble(precioTotalArticulo));
			
			articuloPedidoWebDao.insert(articuloPedido);
			
			articulosPedido.add(articuloPedido);
			
			StringBuffer sbRutaImagenPublicaOrigen = new StringBuffer();
			sbRutaImagenPublicaOrigen.append(rutaPrivadaOrigenReal);
			sbRutaImagenPublicaOrigen.append('/');
			sbRutaImagenPublicaOrigen.append(nombreImagen);
			
			StringBuffer sbRutaImagenPublicaDestino = new StringBuffer();
			sbRutaImagenPublicaDestino.append(rutaPrivadaDestinoReal);
			sbRutaImagenPublicaDestino.append('/');
			sbRutaImagenPublicaDestino.append(nombreImagen);
			
			File origen = new File(sbRutaImagenPublicaOrigen.toString());
			File destino = new File(sbRutaImagenPublicaDestino.toString());
			
			//Grabamos la imagen en img/apedido.
			ImagenUtils.copiarImagen(origen, destino);
			
			//Restamos la cantidad.
			Integer cantidadRestada = articulo.getCantidad() - Integer.parseInt(articuloPedido.getCantidad());
			
			if(cantidadRestada < 0){
				
				cantidadRestada = 0;
			}
			
			Long id = articulo.getId();
			
			//Actualizamos la cantidad.
			articuloWebDao.actualizarCantidad(id, cantidadRestada);
			
			//Actualizamos el número de Artículos vendidos.
			Long ventas = articulo.getVentas();
			
			if(ventas == null){
				
				ventas = 0L;
			}
			
			//Número de ventas del Artículo más el número de Artículos de la cesta.
			articuloWebDao.actualizarVenta(id, ventas + cantidad);
			
			//Si hay menos de 5 enviamos email de stock.
			if(cantidadRestada <= 5){
				
				try{
					
					mailStockTimer.enviarMail(nombre, cantidadRestada);
				
				} catch (SchedulerException | IOException | TemplateException e) {
					
					throw new DeCocinaAdminRuntimeException("Error enviando email de stock.", e);
				}
			}
			
			ArticuloAnalytics articuloAnalytics = new ArticuloAnalytics();
			//EL id hace referencia al id del pedido y no al id del Artículo.
			articuloAnalytics.setId(strIdPedido);
			articuloAnalytics.setName(nombre);
			articuloAnalytics.setPrice(FormatUtils.redondearDouble(precio));
			articuloAnalytics.setQuantity(cantidad);
			
			transaccionAnalytics.addArticuloAnalytics(articuloAnalytics);
		}
		
		String fechaCreacion = FechaUtils.format(pedido.getFechaCreacion());
				
		pedido.setArticulosPedido(articulosPedido);
		
		try {
			
			//Enviamos el email del Pedido al Cliente y a Ventas. 
			mailPedidoTimer.enviarMail(pedido, sbImpuestosPlantilla, fechaCreacion, host);
		
		} catch (SchedulerException | IOException | TemplateException e) {
			
			throw new DeCocinaAdminRuntimeException("Error enviando email de Pedido.", e);
		}
		
		//Borramos la cesta y ponemos las formas de Envío y Pago a null.
		cestaManager.vaciarCesta();
		
		//Borramos la cesta de la Base de datos.
		Long idCliente = cliente.getId();
		cestaWebDao.deleteByIdCliente(idCliente);
		
		pedidoWrapper.setFormaEnvio(null);
		pedidoWrapper.setFormaPago(null);
		
		return transaccionAnalytics;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Pedido> findAllByIdCliente(Long id) {
		
		return dao.findAllByIdCliente(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pedido findByIdClienteAndIdPedido(Long idCliente, Long idPedido) {
		
		return dao.findByIdClienteAndIdPedido(idCliente, idPedido);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String pagarPaypal(PedidoWrapper pedidoWrapper, String url) throws DeCocinaWebRuntimeException {
		
		FormaPago formaPago = pedidoWrapper.getFormaPago(); 
		Long idFormaPago = formaPago.getId();
		
		FormaPago formaPagoBD = formaPagoWebDao.findById(idFormaPago);
		Boolean test = formaPagoBD.getTest();
		
		String desa = "";
		
		if(test){
			
			desa = ".desa";
		}
		
		Map<String, String> configurationMap = crearConfiguracionPayPal(pedidoWrapper, desa);
		
		StringBuffer sbReturnURLProperty = new StringBuffer();
		sbReturnURLProperty.append("paypal.returnURL");
		sbReturnURLProperty.append(desa);
		
		StringBuffer sbCancelURLProperty = new StringBuffer();
		sbCancelURLProperty.append("paypal.cancelURL");
		sbCancelURLProperty.append(desa);
		
		String returnURL = properties.getProperty(sbReturnURLProperty.toString());
		String cancelURL = properties.getProperty(sbCancelURLProperty.toString());
		
		StringBuilder sbReturnURL = new StringBuilder();
		sbReturnURL.append(url);
		sbReturnURL.append(returnURL);
		
		StringBuilder sbCancelURL = new StringBuilder();
		sbCancelURL.append(url);
		sbCancelURL.append(cancelURL);
		
		SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetailsType = new SetExpressCheckoutRequestDetailsType();
		
		List<PaymentDetailsType> paymentsDetailsType = crearDetallePayPal(pedidoWrapper);
		
		setExpressCheckoutRequestDetailsType.setPaymentDetails(paymentsDetailsType);
		
		setExpressCheckoutRequestDetailsType.setReturnURL(sbReturnURL.toString());
		setExpressCheckoutRequestDetailsType.setCancelURL(sbCancelURL.toString());
		
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
		SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
		setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(setExpressCheckoutRequestDetailsType);
		
		SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
		
		expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);
		
		StringBuffer sbURLPaypal = new StringBuffer();
		sbURLPaypal.append("paypal.url");
		sbURLPaypal.append(desa);
		
		String urlPaypal = properties.getProperty(sbURLPaypal.toString());
		
		try{
			
			SetExpressCheckoutResponseType setExpressCheckoutResponseType = service.setExpressCheckout(expressCheckoutReq);
			
			if(setExpressCheckoutResponseType != null){
				
				if (setExpressCheckoutResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					
					String token = setExpressCheckoutResponseType.getToken();
					
					StringBuffer sbURL = new StringBuffer();
					sbURL.append(urlPaypal);
					sbURL.append(token);
					
					return sbURL.toString();
					
				}else{
					
					throw new Exception("Se ha producido un error llamando a paypal.");
				}
			}
			
		}catch(Exception e){
			
			throw new DeCocinaWebRuntimeException("Se ha producido un error llamando a paypal.", e);
		}
		
		return null; 
	}
	
	/**
	 * Finaliza el pedido al pagar con Paypal.
	 * @param clienteWrapper ClienteWrapper.
	 */
	private void finalizarPedidoPaypal(ClienteWrapper clienteWrapper, String token, String payerId) {
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		
		FormaPago formaPago = pedidoWrapper.getFormaPago(); 
		Long idFormaPago = formaPago.getId();
		
		FormaPago formaPagoBD = formaPagoWebDao.findById(idFormaPago);
		Boolean test = formaPagoBD.getTest();
		
		String desa = "";
		
		if(test){
			
			desa = ".desa";
		}
		
		Map<String, String> configurationMap = crearConfiguracionPayPal(pedidoWrapper, desa);
		
		PayPalAPIInterfaceServiceService payPalAPIInterfaceServiceService = new PayPalAPIInterfaceServiceService(configurationMap);
		
		DoExpressCheckoutPaymentRequestType doCheckoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();
		DoExpressCheckoutPaymentRequestDetailsType doExpressCheckoutPaymentRequestDetailsType = new DoExpressCheckoutPaymentRequestDetailsType();
		
		doExpressCheckoutPaymentRequestDetailsType.setToken(token);
		doExpressCheckoutPaymentRequestDetailsType.setPayerID(payerId);
		
		List<PaymentDetailsType> paymentsDetailsType = crearDetallePayPal(pedidoWrapper);
		
		doExpressCheckoutPaymentRequestDetailsType.setPaymentDetails(paymentsDetailsType);
		
		doCheckoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(doExpressCheckoutPaymentRequestDetailsType);
		DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
		doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doCheckoutPaymentRequestType);
		DoExpressCheckoutPaymentResponseType doCheckoutPaymentResponseType = null;
				
		try{
			
			doCheckoutPaymentResponseType = payPalAPIInterfaceServiceService.doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
		
		}catch(Exception e){
			
			throw new DeCocinaWebRuntimeException("Se ha producido un error llamando a paypal doExpressCheckoutPayment(finalizar pago)", e);
		}
		
		PaymentDetailsType paymentDetailsType = paymentsDetailsType.get(0);
		BasicAmountType orderTotalBAT = paymentDetailsType.getOrderTotal();
		
		if(doCheckoutPaymentResponseType != null) {
			
			if(doCheckoutPaymentResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
				
				DoAuthorizationReq doAuthorizationReq = new DoAuthorizationReq();
				String transactionID = doCheckoutPaymentResponseType.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo().get(0).getTransactionID();
				
				DoAuthorizationRequestType doAuthorizationRequestType = new DoAuthorizationRequestType(transactionID, orderTotalBAT);
				doAuthorizationReq.setDoAuthorizationRequest(doAuthorizationRequestType);
				
				DoCaptureReq doCaptureReq = new DoCaptureReq();
				DoCaptureRequestType doCaptureRequestType = new DoCaptureRequestType(transactionID, orderTotalBAT, CompleteCodeType.COMPLETE);
				doCaptureReq.setDoCaptureRequest(doCaptureRequestType);
				
				try{
					
					DoCaptureResponseType doCaptureResponseType = payPalAPIInterfaceServiceService.doCapture(doCaptureReq);
					
					if(!doCaptureResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) 
					{
						throw new Exception("Se ha producido un error llamando a paypal doCapture(finalizar pago).");
					}
					
				}catch(Exception e)
				{
					throw new DeCocinaWebRuntimeException("Se ha producido un error llamando a paypal(finalizar pago)", e);
				}
				
			}else{
				
				throw new DeCocinaWebRuntimeException("Se ha producido un error llamando a paypal(finalizar pago)");
			}
		}
	}
	
	/**
	 * Crea el detalle del pedido en Paypal.
	 * @param pedidoWrapper PedidoWrapper.
	 * @return SetExpressCheckoutRequestDetailsType SetExpressCheckoutRequestDetailsType.
	 */
	private List<PaymentDetailsType> crearDetallePayPal(PedidoWrapper pedidoWrapper) {
		
		List<PaymentDetailsItemType> paymentsDetailsItemType = new ArrayList<PaymentDetailsItemType>();
		
		CestaManager cestaManager = pedidoWrapper.getCestaManager();
		List<ArticuloCestaWrapper> articulos = cestaManager.getCesta();
		
		for(ArticuloCestaWrapper articuloCestaWrapper : articulos)
		{
			Articulo articulo = articuloCestaWrapper.getArticulo();
			
			String nombre = articulo.getNombre();
			String precioArticulo = String.valueOf(FormatUtils.redondearDouble(articuloCestaWrapper.getPrecioArticulo()));
			int cantidad = articuloCestaWrapper.getCantidad();
			
			PaymentDetailsItemType paymentDetailsItemType = new PaymentDetailsItemType();
			BasicAmountType basicAmountType = new BasicAmountType();
			basicAmountType.setCurrencyID(CurrencyCodeType.EUR);
			basicAmountType.setValue(precioArticulo);
			
			paymentDetailsItemType.setQuantity(cantidad);
			paymentDetailsItemType.setName(nombre);
			paymentDetailsItemType.setAmount(basicAmountType);
			paymentDetailsItemType.setItemCategory(ItemCategoryType.PHYSICAL);
			paymentsDetailsItemType.add(paymentDetailsItemType);
		}

		List<PaymentDetailsType> paymentsDetailsType = new ArrayList<PaymentDetailsType>();
		PaymentDetailsType paymentDetailsType = new PaymentDetailsType();
		paymentDetailsType.setPaymentDetailsItem(paymentsDetailsItemType);
		paymentDetailsType.setPaymentAction(PaymentActionCodeType.ORDER);
		
		FormaEnvio formaEnvio = pedidoWrapper.getFormaEnvio(); 
		
		Double gastoFormaEnvio = formaEnvio.getGasto();
		
		String precioFormaEnvio = String.valueOf(gastoFormaEnvio);
		BasicAmountType shippingTotal = new BasicAmountType();
		shippingTotal.setValue(precioFormaEnvio);
		shippingTotal.setCurrencyID(CurrencyCodeType.EUR);
		paymentDetailsType.setShippingTotal(shippingTotal);
		
		//Precio de todos los artículos de la cesta.
		Double precioTotalArticulos = cestaManager.getSubTotal();
		
		String strPrecioTotalArticulos = String.valueOf(FormatUtils.redondearDouble(precioTotalArticulos)); 
		BasicAmountType itemsTotal = new BasicAmountType();
		itemsTotal.setValue(strPrecioTotalArticulos);
		itemsTotal.setCurrencyID(CurrencyCodeType.EUR);
		paymentDetailsType.setItemTotal(itemsTotal);
		
		//Precio del total del pedido con gastos de envio, pago y descuento del cupón.
		Double precioTotalPedido = pedidoWrapper.getPrecio();
		
		String strPrecioTotalPedido = String.valueOf(FormatUtils.redondearDouble(precioTotalPedido));
		
		BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setValue(strPrecioTotalPedido);
		orderTotal.setCurrencyID(CurrencyCodeType.EUR);
		paymentDetailsType.setOrderTotal(orderTotal);
		
		paymentsDetailsType.add(paymentDetailsType);

		return paymentsDetailsType;
	}
	
	/**
	 * Crea la configuración de Paypal en un Map.
	 * @param pedidoWrapper PedidoWrapper.
	 * @param desa Indica que esta en desarrollo.
	 * @return Map con la configuracion de Paypal.
	 */
	private Map<String,String> crearConfiguracionPayPal(PedidoWrapper pedidoWrapper, String desa){
		
		StringBuffer sbUserName = new StringBuffer();
		sbUserName.append("paypal.username");
		sbUserName.append(desa);
		
		StringBuffer sbPassword = new StringBuffer();
		sbPassword.append("paypal.password");
		sbPassword.append(desa);
		
		StringBuffer sbFirma = new StringBuffer();
		sbFirma.append("paypal.firma");
		sbFirma.append(desa);
		
		StringBuffer sbModo = new StringBuffer();
		sbModo.append("paypal.modo");
		sbModo.append(desa);
		
		Map<String,String> configurationMap =  new HashMap<String, String>();
		configurationMap.put("acct1.UserName", properties.getProperty(sbUserName.toString()));
		configurationMap.put("acct1.Password", properties.getProperty(sbPassword.toString()));
		configurationMap.put("acct1.Signature", properties.getProperty(sbFirma.toString()));
		configurationMap.put("mode", properties.getProperty(sbModo.toString()));
		
		return configurationMap;
	}
}