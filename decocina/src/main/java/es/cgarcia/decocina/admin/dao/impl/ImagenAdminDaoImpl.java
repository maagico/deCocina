package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.dao.IImagenAdminDao;
import es.cgarcia.decocina.web.model.Imagen;

/**
 * Implementación del Dao para los Imágenes de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("imagenAdminDao")
public class ImagenAdminDaoImpl extends GenericAdminDaoIbatisImpl<Imagen> implements IImagenAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param imagen ImImagenpuesto.
	 */
	@Autowired
    public ImagenAdminDaoImpl(SqlMapClient sqlMapClient, Imagen imagen) {
		
        super(sqlMapClient, imagen);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByIdArticulo(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id",id);
		
		getSqlMapClientTemplate().delete("Imagen.admin.deleteByIdArticulo", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Imagen> findByIdArticulo(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("Imagen.admin.findByIdArticulo", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Imagen findPrincipalByIdArticulo(Long id, String tipo) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("tipo", tipo);	
		
		return (Imagen)getSqlMapClientTemplate().queryForObject("Imagen.admin.findPrincipalByIdArticulo", parametros);
	}
}
