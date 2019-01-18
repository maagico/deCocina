package es.cgarcia.decocina.web.job;

import java.io.Serializable;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Timer para en el envío de Emails.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class MailTimerBase implements Serializable
{	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2308540886734936499L;
	
	/**
	 * ApplicationContext que se le pasará al trabajo.
	 */
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * Envia un Email inmediatamente.
	 * 
	 * @param from From.
	 * @param to To.
	 * @param subject Subject.
	 * @param body Body.
	 * @throws SchedulerException En caso de error.
	 */
	protected void enviarMail( String from, String to, String subject, String body) throws SchedulerException
	{
		StringBuffer sbJobName  = new StringBuffer("mail_");
		sbJobName.append(System.currentTimeMillis());
		sbJobName.append("_");
		sbJobName.append(Math.random());
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		JobDetail jobDetail = new JobDetail(sbJobName.toString(), Scheduler.DEFAULT_GROUP, MailJob.class);
		
		JobDataMap JobDataMap = jobDetail.getJobDataMap();
		
		JobDataMap.put("applicationContext", applicationContext);
		
		JobDataMap.put("from", from);
		JobDataMap.put("to", to);
		JobDataMap.put("subject", subject);
		JobDataMap.put("body", body);
		
		StringBuffer sbTriggerName  = new StringBuffer("mail_");
		sbTriggerName.append(System.currentTimeMillis());
		sbTriggerName.append("_");
		sbTriggerName.append(Math.random());
		
		Trigger trigger = TriggerUtils.makeImmediateTrigger(sbTriggerName.toString(), 0, 10000);
		
		scheduler.scheduleJob(jobDetail, trigger );
		scheduler.start();
	}
}
