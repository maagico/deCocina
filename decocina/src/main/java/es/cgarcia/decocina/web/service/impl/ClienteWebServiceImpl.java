package es.cgarcia.decocina.web.service.impl;

import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.dao.IDireccionWebDao;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.dao.IClienteWebDao;
import es.cgarcia.decocina.web.exception.DeCocinaWebRuntimeException;
import es.cgarcia.decocina.web.job.MailBienvenidaTimer;
import es.cgarcia.decocina.web.job.MailRecuperarPasswordTimer;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.ClientePassword;
import es.cgarcia.decocina.web.model.Direccion;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.service.IClienteWebService;
import es.cgarcia.decocina.web.service.IZonaWebService;
import es.cgarcia.decocina.web.util.MD5Utils;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;

/**
 * Contiene los métodos necesarios para trabajar con un Cliente de la parte Web. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("clienteWebService")
public class ClienteWebServiceImpl extends GenericServiceImpl<Cliente, IClienteWebDao> implements IClienteWebService{
	
	/**
	 * Servicio para la Zona.
	 */
	@Autowired
	private IZonaWebService zonaWebService;
	
	/**
	 * Dao para la Dirección.
	 */
	@Autowired
	private IDireccionWebDao direccionWebDao;
	
	/**
	 * Timer para el email de bienvenida.
	 */
	@Autowired
	private MailBienvenidaTimer mailBienvenidaTimer;
	
	/**
	 * Timer para el email de recuperación de contraseña.
	 */
	@Autowired
	private MailRecuperarPasswordTimer mailRecuperarPasswordTimer;
	
	/**
	 * Propiedades de configuración.
	 */
	@Resource(name = "confProperties")
	private Properties properties;
	
	/**
	 * Constructor.
	 * 
	 * @param clienteWebDao Dao para los Clientes.
	 */
	@Autowired
	public ClienteWebServiceImpl(IClienteWebDao clienteWebDao){
		
		super(clienteWebDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String existeByEmail(String email) {
		
		String resultado = "{\"existe\":\"false\"}";
		
		Cliente cliente = findByEmail(email);
		
		if(cliente != null){
			
			resultado = "{\"existe\":\"true\"}";
		}
		
		return resultado;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente findByEmail(String email) {
		
		return dao.findByEmail(email);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente findByEmailAndPassword(String email, String password) {
		
		String passwordMD5 = MD5Utils.MD5(password);
		return dao.findByEmailAndPassword(email, passwordMD5);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void crearCliente(Cliente cliente, String host){
		
		try{
			
			Direccion direccion = cliente.getDireccion();
			direccionWebDao.insert(direccion);
			
			//Creamos el MD5 del password y se lo pasamos al cliente.
			String password = cliente.getPassword();
			String passswordMD5 = MD5Utils.MD5(password);
			
			cliente.setPassword(passswordMD5);
			
			dao.insert(cliente);
			
			//Lo ponemos vacío para que no viaje por la sesión.
			cliente.setPassword("");
			
			//Una vez creado el cliente se le envia el mail de bievnvenida.
			mailBienvenidaTimer.enviarMail(cliente, host);
			
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando cliente.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Cliente update(ClienteWrapper clienteWrapper, Cliente cliente){
		
		Direccion direccion = cliente.getDireccion();
		direccionWebDao.update(direccion);
		
		dao.update(cliente);
		
		clienteWrapper.setCliente(cliente);
		
		Zona zona = direccion.getZona();
		Long idZona = zona.getId();
		
		Zona zonaBD = zonaWebService.findById(idZona);
		
		PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
		
		pedidoWrapper.setZona(zonaBD);
		
		return cliente;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePassword(ClientePassword clientePassword) {
	
		String password = clientePassword.getNuevoPassword();
		Long idCliente = clientePassword.getId();
		
		String passwordMD5 = MD5Utils.MD5(password);
		
		dao.updatePassword(passwordMD5, idCliente);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePassword(String token, String nuevoPassword) {
		
		try{
			
			comprobarToken(token);
			
		}catch(DeCocinaWebRuntimeException e){
			
			throw e;
		}
		
		String nuevoPasswordMD5 = MD5Utils.MD5(nuevoPassword);
		
		dao.updatePassword(token, nuevoPasswordMD5);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void recuperarPassword(String email, String host) {
		
		Cliente cliente = dao.findByEmail(email);
		
		if(cliente == null){
			
			throw new DeCocinaWebRuntimeException("No existe ningun usuario con ese email.");
		}
		
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		
		Long idCliente = cliente.getId();
		Calendar fecha = Calendar.getInstance();
		
		dao.updateToken(idCliente, token, fecha);
		
		String urlCambiarPassword = properties.getProperty("url.cambiar.password");
		
		StringBuilder sbUrlCambiarPassword = new StringBuilder();
		sbUrlCambiarPassword.append(host);
		sbUrlCambiarPassword.append(urlCambiarPassword);
		sbUrlCambiarPassword.append(token);
		
		String urlRecuperarPassword = properties.getProperty("url.recuperar.password");
		
		StringBuilder sbUrlRecuperarPassword = new StringBuilder();
		sbUrlRecuperarPassword.append(host);
		sbUrlRecuperarPassword.append(urlRecuperarPassword);
		
		try{
			
			mailRecuperarPasswordTimer.enviarMailRecuperarPassword(email, sbUrlCambiarPassword.toString(), sbUrlRecuperarPassword.toString(), host);
		
		}catch(Exception e){
			
			throw new DeCocinaAdminRuntimeException("Error creando mail de recuperación de contraseña.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean comprobarToken(String token) {
		
		Cliente cliente = dao.findByToken(token);
		
		if(cliente != null){
			
			Calendar fechaActual = Calendar.getInstance();
			Calendar fechaRP = cliente.getFechaRP();
			
			DateTime dtFechaActual = new DateTime(fechaActual.getTime());
			DateTime dtFechaRP = new DateTime(fechaRP.getTime());
			
			Minutes minutes = Minutes.minutesBetween(dtFechaRP, dtFechaActual);
			Integer minutos = minutes.getMinutes();
			
			if(minutos > 20){
				
				throw new DeCocinaWebRuntimeException("El token ha caducado.");
			}
		}
		
		return cliente != null;
	}
}