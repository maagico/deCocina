package es.cgarcia.decocina.web.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import es.cgarcia.decocina.web.email.Email;

/**
 * Job para el envío de Emails.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class MailJob implements Job{
	
	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(MailJob.class);
	
	/**
	 * Clase que envía el email.
	 */
	
	/**
	 * Ejecuta el trabajo.
	 */
	 public void execute(JobExecutionContext context) throws JobExecutionException
	 {
		
		try{
			
			JobDetail jobDetail = context.getJobDetail();
			JobDataMap jobDataMap = jobDetail.getJobDataMap();
			
			ApplicationContext applicationContext = (ApplicationContext)jobDataMap.get("applicationContext");
			
			String from    = (String)jobDataMap.get("from");
			String to 	   = (String)jobDataMap.get("to");
			String subject = (String)jobDataMap.get("subject");
			String body    = (String)jobDataMap.get("body");
			Email email = new Email();
			 
			email.setApplicationContext(applicationContext);
			email.enviarEmail(from, to, subject, body);
			
			logger.info("Email enviado");
			
		}catch(Exception e){
			
			logger.error("Error enviando mail "+e);
		}
	}	
}