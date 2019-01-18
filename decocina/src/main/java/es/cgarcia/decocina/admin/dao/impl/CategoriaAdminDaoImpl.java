package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.ICategoriaAdminDao;
import es.cgarcia.decocina.web.model.Categoria;

/**
 * Implementación del Dao para las Categorías de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Repository("categoriaAdminDao")
public class CategoriaAdminDaoImpl extends GenericAdminDaoIbatisImpl<Categoria> implements ICategoriaAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param categoria Categoría.
	 */
	@Autowired
    public CategoriaAdminDaoImpl(SqlMapClient sqlMapClient, Categoria categoria) {
		
        super(sqlMapClient, categoria);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> findByIdPadre(Long idPadre) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idPadre);
		
		return getSqlMapClientTemplate().queryForList("Categoria.admin.findByIdPadre", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> findByNombre(String nombre) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nombre", nombre);
		
		return getSqlMapClientTemplate().queryForList("Categoria.admin.findByNombre", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer cambiarEstadoActivaByIdPadre(Long id, Boolean activa) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("activa", activa);
		parametros.put("idPadre", id);
		
		return getSqlMapClientTemplate().update("Categoria.admin.cambiarEstadoActivaByIdPadre", parametros);
		
	}
}
