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
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Timer para el envio de email de bienvenida.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class MailBienvenidaTimer extends MailTimerBase
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
	 * Envía el email de bienvenida.
	 * 
	 * @param to A quien se dirige el email.
	 * @throws SchedulerException En caos de error.
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public void enviarMail(Cliente cliente, String host) throws SchedulerException, IOException, TemplateException
	{
		String nombre = cliente.getNombre();
		String to = cliente.getEmail();
		
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail_alta_cliente_body.html");
		
		Map<String, Object> datos = new HashMap<String, Object>();
		datos.put("host", host);
		datos.put("nombre", nombre);
		datos.put("email", to);
		
		String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
		
		String from = (String)properties.get("email.info");
		
		enviarMail(from, to, "Bienvenido a deCocina.es", body);
	}
}
