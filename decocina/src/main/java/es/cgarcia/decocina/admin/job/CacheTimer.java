/**
 * 
 */
package es.cgarcia.decocina.admin.job;

import java.util.Iterator;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;

/**
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class CacheTimer {
	
	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(CacheTimer.class);
	
	/**
	 * Nombre del trabajo.
	 */
	private static String NOMBRE_JOB = "cacheador";
	
	/**
	 * ApplicationContext que se le pasará al trabajo.
	 */
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * Ejecuta el trabajo que cachea la web.
	 */
	public void cacharWeb(String contexto){
		
		try{
			
			logger.info("Creando trabajo");
			
			String nombreJob = NOMBRE_JOB;
			
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			JobDetail jobDetail = new JobDetail(nombreJob, Scheduler.DEFAULT_GROUP, CacheJob.class);
			
			JobDataMap JobDataMap = jobDetail.getJobDataMap();
			
			JobDataMap.put("applicationContext", applicationContext);
			JobDataMap.put("contexto", contexto);
			
			StringBuffer sbTriggerName  = new StringBuffer("cacheador_");
			sbTriggerName.append(System.currentTimeMillis());
			sbTriggerName.append("_");
			sbTriggerName.append(Math.random());
			
			Trigger trigger = TriggerUtils.makeImmediateTrigger(sbTriggerName.toString(), 0, 10000);
			
			scheduler.scheduleJob(jobDetail, trigger );
			scheduler.start();
		
		}catch(Exception e){
			
			logger.error("Error creando trabajo.", e);
			throw new DeCocinaAdminRuntimeException("Error lanzando trabajo CacheJob", e);
		}
	}
	
	/**
	 * Comprueba si el trabajo está ejecutandose.
	 * @return True si el trabajo está activo, false lo contrario.
	 */
	public Boolean activo(){
		
		JobExecutionContext jobExecutionContext = buscarTrabajo(NOMBRE_JOB);
	
		Boolean activo = jobExecutionContext != null;
		
		return activo;
	}
	
	/**
	 * Borra y para el trabajo.
	 * @throws SchedulerException En caso de error.
	 */
	public void borrar(){
		
		try{
			
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.deleteJob(NOMBRE_JOB, Scheduler.DEFAULT_GROUP);
			scheduler.interrupt(NOMBRE_JOB, Scheduler.DEFAULT_GROUP);
			
		}catch(SchedulerException e){
			
			logger.error("Error borrando trabajo.", e);
			
			throw new DeCocinaAdminRuntimeException("Se ha producido un error borrando el trabajo.", e);
		}
	}
	
	/**
	 * Busca un trabajo activo.
	 * @param nombre Nombre del trabajo.
	 * @return JobExecutionContext si el trabajo existe, o null si no exíste.
	 */
	@SuppressWarnings("unchecked")
	private JobExecutionContext buscarTrabajo(String nombre){
		
		try {
			
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			List<JobExecutionContext> jobsExecutionContext = scheduler.getCurrentlyExecutingJobs();
			
			for (Iterator<JobExecutionContext> iterator = jobsExecutionContext.iterator(); iterator.hasNext();) {
				
				JobExecutionContext jobExecutionContext = iterator.next();
				JobDetail jobDetail = jobExecutionContext.getJobDetail();
				
				String nombreJob = jobDetail.getName();
				
				if(NOMBRE_JOB.equals(nombreJob)){
					
					return jobExecutionContext;
				}
			}
			
		} catch (SchedulerException e) {
			
			logger.error("Error borrando trabajo.", e);
			
			throw new DeCocinaAdminRuntimeException("Error buscando el trabajo", e);
		}
		
		return null;
	}
}
