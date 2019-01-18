package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IArticuloAdminDao;
import es.cgarcia.decocina.web.model.Articulo;

/**
 * Implementación del Dao para las Categorías de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Repository("articuloAdminDao")
public class ArticuloAdminDaoImpl extends GenericAdminDaoIbatisImpl<Articulo> implements IArticuloAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param articulo Artículo.
	 */
	@Autowired
    public ArticuloAdminDaoImpl(SqlMapClient sqlMapClient, Articulo articulo) {
		
        super(sqlMapClient, articulo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findByIdCategoria(Long idCategoria) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idCategoria);
		
		return getSqlMapClientTemplate().queryForList("Articulo.admin.findByIdCategoria", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findByNombre(String nombre) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombre", nombre);
		
		return getSqlMapClientTemplate().queryForList("Articulo.admin.findByNombre", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Articulo> findAllUltimos(Long limit) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("limit", limit);
		
		return getSqlMapClientTemplate().queryForList("Articulo.admin.findAllUltimos", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Articulo> findAllExport() {
	
		return getSqlMapClientTemplate().queryForList("Articulo.admin.findAllExport");
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean existeArticulo(Long id, String nombre) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("nombre", nombre);
		
		Articulo articulo = (Articulo)getSqlMapClientTemplate().queryForObject("Articulo.admin.existeArticulo", parametros);
		return articulo != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer cambiarEstadoActivaByIdCategoria(Long idCategoria, Boolean activo) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("activo", activo);
		parametros.put("idCategoria", idCategoria);
		
		return getSqlMapClientTemplate().update("Articulo.admin.cambiarEstadoActivaByIdCategoria", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer updateFromImport(Integer id, Integer cantidad, Boolean activo) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("cantidad", cantidad);
		parametros.put("activo", activo);
		
		return getSqlMapClientTemplate().update("Articulo.admin.updateFromImport", parametros);
	}
}
