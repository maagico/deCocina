package es.cgarcia.decocina.admin.job;

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

import es.cgarcia.decocina.admin.util.FechaUtils;
import es.cgarcia.decocina.web.job.MailTimerBase;
import es.cgarcia.decocina.web.model.Pedido;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Timer para el envío del email de cambio de Estado.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class MailEstadoTimer extends MailTimerBase
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
	 * Envía un email con el cambio de Estado.
	 * @throws SchedulerException En caso de error.
	 * @throws IOException En caso de error.
	 * @throws TemplateException En caso de error.
	 */
	public void enviaMail(Pedido pedido) throws SchedulerException, IOException, TemplateException
	{
		String fechaCreacion = FechaUtils.format(pedido.getFechaCreacion());
		
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail_estado_body.html");
		
		Map<String, Object> datos = new HashMap<String, Object>();
		datos.put("pedido", pedido);
		datos.put("fechaCreacion", fechaCreacion);
		
		String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
		
		String subject = "Actualización del estado del pedido";
		
		String from = properties.getProperty("email.info");
		String to = pedido.getEmail();
		
		enviarMail(from, to, subject, body);
	}
}
