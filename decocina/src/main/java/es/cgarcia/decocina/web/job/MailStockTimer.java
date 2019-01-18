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

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Timer para el envío del email de stock.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class MailStockTimer extends MailTimerBase
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
	 * Envia un email indicando el stock disponible de un Artículo.
	 * @param nombre Nombre del Artículo.
	 * @param cantidad Cantidad.
	 * @throws SchedulerException En caso de error.
	 * @throws IOException En caso de error.
	 * @throws TemplateException En caso de error.
	 */
	public void enviarMail(String nombre, Integer cantidad) throws SchedulerException, IOException, TemplateException
	{
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail_stock_body.txt");
		
		Map<String, Object> datos = new HashMap<String, Object>();
		datos.put("nombre", nombre);
		datos.put("cantidad", cantidad);
		
		String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
		
		String from = properties.getProperty("email.info");
		String to = properties.getProperty("email.stock");
		
		enviarMail(from, to, body, body);
	}
}
