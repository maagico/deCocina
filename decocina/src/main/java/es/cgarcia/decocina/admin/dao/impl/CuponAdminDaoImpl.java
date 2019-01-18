package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.buscador.CuponBuscador;
import es.cgarcia.decocina.admin.dao.ICuponAdminDao;
import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.model.Cupon;

/**
 * Implementación del Dao para el Cupón de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("cuponAdminDao")
public class CuponAdminDaoImpl extends GenericAdminDaoIbatisImpl<Cupon> implements ICuponAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param cupon Cupon.
	 */
	@Autowired
    public CuponAdminDaoImpl(SqlMapClient sqlMapClient, Cupon cupon) {
		
        super(sqlMapClient, cupon);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Cupon> findAllBusqueda(CuponBuscador cuponBuscador) {
		
		String nombre = cuponBuscador.getNombre();
		
		StringBuffer sbNombre = new StringBuffer();
		
		sbNombre.append(' ');
		sbNombre.append(nombre);
		sbNombre.append(' ');
		
		String nombreEspacios = sbNombre.toString();
		String nombrePorcentaje = nombreEspacios.replaceAll(" ", "%");
		
		Paginacion paginacion = cuponBuscador.getPaginacion();
		
		Long start = paginacion.getStart();
		Long limit = paginacion.getLimit();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nombre", nombrePorcentaje);
		parametros.put("start", start);
		parametros.put("limit", limit);
		
		List<Cupon> cupones = getSqlMapClientTemplate().queryForList("Cupon.admin.findAllBusqueda", parametros);
		
		parametros.put("isCount", "isCount");
		
		Long count = (Long)getSqlMapClientTemplate().queryForObject("Cupon.admin.countFindAllBusqueda", parametros);
		
		paginacion.setCount(count);
		
		return cupones;
	}
}
