package es.cgarcia.decocina.web.job;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Pedido;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Timer para el envío del email al usuario y a ventas al crear un Pedido.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class MailPedidoTimer extends MailTimerBase
{	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2308540886734936499L;
	
	/**
	 * Propiedades de configuración.
	 */
	@Resource(name = "confProperties")
	private Properties properties;
	
	/**
	 * freeMarkerConfigurer.
	 */
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	/**
	 * Enviar el email de nuevo Pedido al Cliente y a Ventas.
	 * @param pedido Pedido.
	 * @param sbImpuestosPlantilla Impuestos.
	 * @param fechaCreacion Fecha de creación.
	 * @param host Host. http://www.decocina.es o https://www.decocina.es. Con contexto o sin el.
	 * @throws SchedulerException SchedulerException.
	 * @throws IOException IOException.
	 * @throws TemplateException TemplateException.
	 */
	public void enviarMail(Pedido pedido, StringBuffer sbImpuestosPlantilla, String fechaCreacion, String host) throws SchedulerException, IOException, TemplateException
	{
		Cliente cliente = pedido.getCliente();
		
		String to = cliente.getEmail();
		
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail_pedido_body.html");
		
		Map<String, Object> datos = new HashMap<String, Object>();
		datos.put("host", host);
		datos.put("pedido", pedido);
		datos.put("impuestosPlantilla", sbImpuestosPlantilla.toString());
		datos.put("fechaCreacion", fechaCreacion);
		
		//Hace que se oculte el email y el teléfono del Cliente.
		datos.put("paraVentas", false);
				
		String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
		
		String from = properties.getProperty("email.info");
		
		enviarMail(from, to, "Nuevo pedido - decocina.es.", body);
		
		String toVentas = properties.getProperty("email.ventas");
		
		//Hace que se muestre el email y el teléfono del Cliente.
		datos.put("paraVentas", true);
		
		String bodyVentas = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
		
		enviarMail(from, toVentas, "Nuevo pedido - decocina.es.", bodyVentas);
	}
}
