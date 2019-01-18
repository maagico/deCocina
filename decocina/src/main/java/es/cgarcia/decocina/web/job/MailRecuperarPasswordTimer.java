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
 * Timer para el envío del email para la recuperación de la contraseña.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class MailRecuperarPasswordTimer extends MailTimerBase
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
	 * Envía el email de recuperación de contraseña.
	 * 
	 * @param email Email del Cliente.
	 * @param urlCambiarPassord Url para solicitar el cambio de password.
	 * @param urlRecuperarPassword Url de recuperación.
	 * @throws SchedulerException En caso de error.
	 * @throws IOException En caso de error.
	 * @throws TemplateException En caso de error.
	 */
	public void enviarMailRecuperarPassword(String email, String urlCambiarPassord, String urlRecuperarPassword, String host) throws SchedulerException, IOException, TemplateException
	{
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail_recuperar_password_body.html");
		
		Map<String, Object> datos = new HashMap<String, Object>();
		datos.put("host", host);
		datos.put("email", email);
		datos.put("urlCambiarPassord", urlCambiarPassord);
		datos.put("urlRecuperarPassword", urlRecuperarPassword);
		
		String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, datos);
		
		String from = (String)properties.get("email.info");
		
		enviarMail(from, email, "Recuperación de contraseña", body);
	}
}
