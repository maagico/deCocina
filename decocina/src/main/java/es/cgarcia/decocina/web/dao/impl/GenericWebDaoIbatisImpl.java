package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.ModelBase;

/**
 * Dao genérico para ibatis.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class GenericWebDaoIbatisImpl<T extends ModelBase> extends SqlMapClientDaoSupport implements IGenericDao<T>
{ 
	/**
	 * Cualquier clase que herede de ModelBase.
	 */
	protected T model;
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param model Model.
	 */
	public GenericWebDaoIbatisImpl(SqlMapClient sqlMapClient, T model) {
		 
		 setSqlMapClient(sqlMapClient);
		 this.model = model;
	 }
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(Long start, Long limit) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("start", start);
		parametros.put("limit", limit);
				
		Class<?> clazz = model.getClass();
		String simpleName= clazz.getSimpleName();
		
		StringBuffer sbStatementName = new StringBuffer();
		sbStatementName.append(simpleName);
		sbStatementName.append(".web.findAll");
		
		return getSqlMapClientTemplate().queryForList(sbStatementName.toString(), parametros);
	}	
	
	/**
	 * {@inheritDoc}
	 */
	
	public Long countFindAll(Long start, Long limit) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("start", start);
		parametros.put("limit", limit);
		parametros.put("isCount","isCount");
		
		Class<?> clazz = model.getClass();
		String simpleName= clazz.getSimpleName();
		
		StringBuffer sbStatementName = new StringBuffer();
		sbStatementName.append(simpleName);
		sbStatementName.append(".web.countFindAll");
		
		return (Long)getSqlMapClientTemplate().queryForObject(sbStatementName.toString(), parametros);
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public List<T> findAll(Paginacion paginacion) {
		
		Long start = paginacion.getStart(); 
		Long limit = paginacion.getLimit();
		
		return findAll(start, limit);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Long countFindAll(Paginacion paginacion){
		
		Long start = paginacion.getStart(); 
		Long limit = paginacion.getLimit();
		
		return countFindAll(start, limit);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		
		Class<?> clazz = model.getClass();
		String simpleName= clazz.getSimpleName();
		
		StringBuffer sbStatementName = new StringBuffer();
		sbStatementName.append(simpleName);
		sbStatementName.append(".web.findAll");
		
		return getSqlMapClientTemplate().queryForList(sbStatementName.toString());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long countFindAll() {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		Class<?> clazz = model.getClass();
		String simpleName= clazz.getSimpleName();
		
		StringBuffer sbStatementName = new StringBuffer();
		sbStatementName.append(simpleName);
		sbStatementName.append(".web.countFindAll");
		
		return (Long)getSqlMapClientTemplate().queryForObject(sbStatementName.toString(), parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T findById(Long id){
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		Class<?> clazz = model.getClass();
		String simpleName= clazz.getSimpleName();
		
		StringBuffer sbStatementName = new StringBuffer();
		sbStatementName.append(simpleName);
		sbStatementName.append(".web.findById");
		
		return (T)getSqlMapClientTemplate().queryForObject(sbStatementName.toString(), parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(T entity){
		
		Class<?> clazz = entity.getClass();
		String simpleName= clazz.getSimpleName();
		
		StringBuffer sbStatementName = new StringBuffer();
		sbStatementName.append(simpleName);
		sbStatementName.append(".web.insert");
		
		Long id = (Long)getSqlMapClientTemplate().insert(sbStatementName.toString(), entity);
		
		entity.setId(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public T update(T entity){
		
		Class<?> clazz = entity.getClass();
		String simpleName= clazz.getSimpleName();
		
		StringBuffer sbStatementName = new StringBuffer();
		sbStatementName.append(simpleName);
		sbStatementName.append(".web.update");
		
		getSqlMapClientTemplate().update(sbStatementName.toString(), entity);
		
		return entity;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(T entity){
		
		Class<?> clazz = entity.getClass();
		String simpleName= clazz.getSimpleName();
		
		StringBuffer sbStatementName = new StringBuffer();
		sbStatementName.append(simpleName);
		sbStatementName.append(".web.delete");
		
		getSqlMapClientTemplate().delete(sbStatementName.toString(), entity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(Long id){
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		Class<?> clazz = model.getClass();
		String simpleName= clazz.getSimpleName();
		
		StringBuffer sbStatementName = new StringBuffer();
		sbStatementName.append(simpleName);
		sbStatementName.append(".web.delete");
		
		getSqlMapClientTemplate().delete(sbStatementName.toString(), parametros);
	}
}
