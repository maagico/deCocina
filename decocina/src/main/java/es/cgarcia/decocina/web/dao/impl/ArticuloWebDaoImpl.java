package es.cgarcia.decocina.web.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.web.dao.IArticuloWebDao;
import es.cgarcia.decocina.web.model.Articulo;

/**
 * Implementación del Dao para las Categorías de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Repository("articuloWebDao")
public class ArticuloWebDaoImpl extends GenericWebDaoIbatisImpl<Articulo> implements IArticuloWebDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param articulo Artículo.
	 */
	@Autowired
    public ArticuloWebDaoImpl(SqlMapClient sqlMapClient, Articulo articulo) {
		
        super(sqlMapClient, articulo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Articulo findByURL(String url) {
		
		Calendar fecha = getFecha();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("url", url);
		parametros.put("fecha", fecha);
		
		return (Articulo)getSqlMapClientTemplate().queryForObject("Articulo.web.findByURL", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findByIdCategoria(Long idCategoria) {
		
		Calendar fecha = getFecha();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", idCategoria);
		parametros.put("fecha", fecha);
		
		return getSqlMapClientTemplate().queryForList("Articulo.web.findByIdCategoria", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findByNombre(String texto) {
		
		Calendar fecha = getFecha();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("texto", texto);
		parametros.put("fecha", fecha);
		
		return getSqlMapClientTemplate().queryForList("Articulo.web.findByNombre", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findRelacionadosByIdCategoria(Long idArticulo, Long idCategoria) {
		
		Calendar fecha = getFecha();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idArticulo", idArticulo);
		parametros.put("id", idCategoria);
		parametros.put("fecha", fecha);
		
		return getSqlMapClientTemplate().queryForList("Articulo.web.findRelacionadosByIdCategoria", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Articulo> findNovedades(Integer limit){
		
		Calendar fecha = getFecha();
		
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("limit", limit);
		parametros.put("fecha", fecha);
		
		return getSqlMapClientTemplate().queryForList("Articulo.web.findNovedades", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Articulo> findMasVendidos(Long idCategoria, Integer limit){
		
		Calendar fecha = getFecha();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCategoria", idCategoria);
		parametros.put("limit", limit);
		parametros.put("fecha", fecha);
		
		return getSqlMapClientTemplate().queryForList("Articulo.web.findMasVendidos", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Articulo> findVentasRelacionadas(Long idArticulo, Integer limit) {
		
		Calendar fecha = getFecha();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idArticulo", idArticulo);
		parametros.put("fecha", fecha);
		parametros.put("limit", limit);
		
		return getSqlMapClientTemplate().queryForList("Articulo.web.findVentasRelacionadas", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findMasVistos(Long idCategoria, Integer limit) {
		
		Calendar fecha = getFecha();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idCategoria", idCategoria);
		parametros.put("fecha", fecha);
		parametros.put("limit", limit);
		
		return getSqlMapClientTemplate().queryForList("Articulo.web.findMasVistos", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actualizarCantidad(Long id, Integer cantidad) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("cantidad", cantidad);
		
		getSqlMapClientTemplate().update("Articulo.web.actualizarCantidad", parametros);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actualizarVenta(Long id, Long cantidad) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("cantidad", cantidad);
		
		getSqlMapClientTemplate().update("Articulo.web.actualizarVenta", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Articulo> findAllSiteMap() {
		
		Calendar fecha = getFecha();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("fecha", fecha);
		
		return getSqlMapClientTemplate().queryForList("Articulo.web.findAllSiteMap", parametros);
	}
	
	/**
	 * Devuelve la fecha actual con las horas, minutos, segudos y milisegundos a 0.
	 * @return Fecha actual.
	 */
	private Calendar getFecha(){
		
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c;
	}
}
