/**
 * 
 */
package es.cgarcia.decocina.web.email;

import java.io.Serializable;
import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;

/**
 * Clase que envía un mail.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class Email implements Serializable{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -7933591341933129364L;
	
	/**
	 * ApplicationContext
	 */
	private ApplicationContext applicationContext;

	/**
	 * Fija el valor del atributo applicationContext.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Devuelve la sesión.
	 * @param applicationContext ApplicationContext.
	 * @return Sesión.
	 */
	private Session getSession(){
		
		Properties confProperties = (Properties)applicationContext.getBean("confProperties");
		
		final String email = (String)confProperties.get("email.info");
		final String password =  (String)confProperties.get("password.info");
		
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	
		Properties properties = getProperties();
	
		Session sesion = Session.getDefaultInstance(properties, new Authenticator(){
				
			protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(email, password);
			}
					
		});
		
		sesion.setDebug(false);
			
			
		return sesion;
	}
	
	/**
	 * Carga las propiedades.
	 * @return Propiedades.
	 */
	private Properties getProperties()
	{
		System.setProperty("mail.mime.charset","UTF-8");
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host","smtp.gmail.com");
		properties.setProperty("mail.smtp.port","465");
		properties.setProperty("mail.smtp.socketFactory.port","465");
		properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback","false");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.mime.charset", "UTF-8");
		
		return properties;
	}
	
	/**
	 * Envia el email.
	 * @param from From. 
	 * @param to To.
	 * @param subject Subject.
	 * @param body Body.
	 * @throws MessagingException En caso de error.
	 */
	public void enviarEmail(String from, String to, String subject, String body) throws MessagingException{
		
		Session sesion = getSession();
		
		MimeMessage message = new MimeMessage(sesion);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setContent(body,"text/html; charset=utf-8");
		
		//Transport.send(message);
	}
}
