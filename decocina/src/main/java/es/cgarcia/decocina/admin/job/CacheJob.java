package es.cgarcia.decocina.admin.job;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.web.dao.IArticuloWebDao;
import es.cgarcia.decocina.web.dao.ICategoriaWebDao;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Categoria;
import es.cgarcia.decocina.web.util.Constantes;

/**
 * Job para el cacheo de la web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class CacheJob implements Job, InterruptableJob{
	
	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(CacheJob.class);
	
	/**
	 * Indica que se debe parar el trabajo.
	 */
	private Boolean parar = false;
	
	/**
	 * {@inheritDoc}
	 */
	 public void execute(JobExecutionContext context) throws JobExecutionException
	 {
		
		try{
			
			logger.info("Ejecutando trabajo.");
			
			JobDetail jobDetail = context.getJobDetail();
			JobDataMap jobDataMap = jobDetail.getJobDataMap();
			
			ApplicationContext applicationContext = (ApplicationContext)jobDataMap.get("applicationContext");
			String contexto = (String)jobDataMap.get("contexto");
			
			//Cacheamos el index.
			StringBuilder sbIndexURL = new StringBuilder();
			sbIndexURL.append(Constantes.LOCALHOST);
			sbIndexURL.append(contexto);

			cachearURL(sbIndexURL.toString());
			
			//Cacheamos las Categorías.
			ICategoriaWebDao categoriaWebDao = (ICategoriaWebDao)applicationContext.getBean("categoriaWebDao");
			
			List<Categoria> categorias = categoriaWebDao.findAll();
			
			for (Categoria categoria : categorias) {
				
				try{
					
					String urlAmigable = categoria.getUrlAmigable();
					
					StringBuilder sbURL = new StringBuilder();
					sbURL.append(Constantes.LOCALHOST);
					sbURL.append(contexto);
					sbURL.append(urlAmigable);
					
					cachearURL(sbURL.toString());
					
				} catch (IOException e) {
					
					logger.info("Error ejecutando trabajo.", e);
					throw new DeCocinaAdminRuntimeException("Error cacheando Categorías de la web", e);
					
				}
				
				if(parar){
					
					return;
				}
				
				//Paramos durante 750 milisegundos.
				Thread.sleep(750);
			}
			
			//Cacheamos los Artículos.
			IArticuloWebDao articuloWebDao = (IArticuloWebDao)applicationContext.getBean("articuloWebDao");
			
			List<Articulo> articulos = articuloWebDao.findAll();
			
			for (Articulo articulo : articulos) {
				
				try{
					
					String urlAmigable = articulo.getUrlAmigable();
					
					StringBuilder sbURL = new StringBuilder();
					sbURL.append(Constantes.LOCALHOST);
					sbURL.append(contexto);
					sbURL.append(urlAmigable);
					
					cachearURL(sbURL.toString());
					
				} catch (IOException e) {
					
					logger.info("Error ejecutando trabajo.", e);
					throw new DeCocinaAdminRuntimeException("Error cacheando Artículos de la web", e);
					
				}
				
				if(parar){
					
					return;
				}

				//Paramos durante 750 milisegundos.
				Thread.sleep(750);
			}
			
			logger.info("Web cacheada.");
			
		}catch(Exception e){
			
			logger.error("Error cachenado web.", e);
		}
	}	
	
	/**
	 * Cachea la URL.
	 * @param urlAmigable URL a cachear.
	 * @throws IOException En caso de error.
	 */
	public void cachearURL(String urlAmigable) throws IOException {
		
		URL url = new URL(urlAmigable);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "decocina.cache.0.1");

		int responseCode = con.getResponseCode();
		
		logger.info("Cacheando Artículo con url {} | código de retorno {}", urlAmigable, responseCode);
		
	}

	/**
	 *{@inheritDoc} 
	 */
	@Override
	public void interrupt() throws UnableToInterruptJobException {
	
		parar = true;
	}
}