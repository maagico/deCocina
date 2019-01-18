package es.cgarcia.decocina.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.job.CacheTimer;
import es.cgarcia.decocina.admin.service.IHerramientaAdminService;

/**
 * Contiene los métodos necesarios para trabajar con un las Herramientas de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("herramientaAdminService")
public class HerramientaAdminServiceImpl implements IHerramientaAdminService{
	
	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(HerramientaAdminServiceImpl.class);
	
	/**
	 * sqlMapClient.
	 */
	@Autowired
	private SqlMapClientFactoryBean sqlMapClientFactoryBean;
	
	/**
	 * Timer para el cacheo de la web.
	 */
	@Autowired
	private CacheTimer cacheTimer;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void borrarCacheIbatis() {
		
		SqlMapClient sqlMapClient = sqlMapClientFactoryBean.getObject();
		sqlMapClient.flushDataCache();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean cacheadorEstaActivo(){
		
		return cacheTimer.activo();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void cacheadorBorrar(){
		
		cacheTimer.borrar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cachearWeb(String contexto) {
		
		logger.info("Contexto '{}'",contexto);
		
		if("".equals(contexto)){
			
			contexto = "/";
		}
		
		StringBuilder sbContexto = new StringBuilder();
		sbContexto.append(contexto);
		
		char ultimoCaracter = contexto.charAt(contexto.length() - 1);
		
		if(ultimoCaracter != '/'){
			
			sbContexto.append('/');
		}
		
		logger.info("Contexto añadido '{}'", sbContexto.toString());
		
		cacheTimer.cacharWeb(sbContexto.toString());
	}
}