package es.cgarcia.decocina.web.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.ICategoriaWebDao;
import es.cgarcia.decocina.web.model.Categoria;

/**
 * Implementación del Dao para las Categorías de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Repository("categoriaWebDao")
public class CategoriaWebDaoImpl extends GenericWebDaoIbatisImpl<Categoria> implements ICategoriaWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param categoria Categoría.
	 */
	@Autowired
    public CategoriaWebDaoImpl(SqlMapClient sqlMapClient, Categoria categoria) {
		
        super(sqlMapClient, categoria);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Categoria findByURL(String url) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("url", url);
		
		return (Categoria)getSqlMapClientTemplate().queryForObject("Categoria.web.findByURL", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> findByIdPadre(Long idPadre) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idPadre);
		
		return getSqlMapClientTemplate().queryForList("Categoria.web.findByIdPadre", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> findAllSiteMap() {
		
		return getSqlMapClientTemplate().queryForList("Categoria.web.findAllSiteMap");
	}
}
