package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.buscador.ZonaBuscador;
import es.cgarcia.decocina.admin.dao.IZonaAdminDao;
import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.model.Zona;

/**
 * Implementación del Dao para la Zona de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("zonaAdminDao")
public class ZonaAdminDaoImpl extends GenericAdminDaoIbatisImpl<Zona> implements IZonaAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param zona Zona.
	 */
	@Autowired
    public ZonaAdminDaoImpl(SqlMapClient sqlMapClient, Zona zona) {
		
        super(sqlMapClient, zona);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Zona> findAllBusqueda(ZonaBuscador zonaBuscador) {
		
		String nombre = zonaBuscador.getNombre();
		
		StringBuffer sbNombre = new StringBuffer();
		
		sbNombre.append(' ');
		sbNombre.append(nombre);
		sbNombre.append(' ');
		
		String nombreEspacios = sbNombre.toString();
		String nombrePorcentaje = nombreEspacios.replaceAll(" ", "%");
		
		Paginacion paginacion = zonaBuscador.getPaginacion();
		
		Long start = paginacion.getStart();
		Long limit = paginacion.getLimit();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nombre", nombrePorcentaje);
		parametros.put("start", start);
		parametros.put("limit", limit);
		
		List<Zona> zonas = getSqlMapClientTemplate().queryForList("Zona.admin.findAllBusqueda", parametros);
		
		parametros.put("isCount", "isCount");
		
		Long count = (Long)getSqlMapClientTemplate().queryForObject("Zona.admin.countFindAllBusqueda", parametros);
		
		paginacion.setCount(count);
		
		return zonas;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Zona> findAllProvincias() {
		
		return (List<Zona>)getSqlMapClientTemplate().queryForList("Zona.admin.findAllProvincias");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void asignarPadre(Long idZonaHija, Long idZonaPadre) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("idZonaHija", idZonaHija);
		parametros.put("idZonaPadre", idZonaPadre);
		
		getSqlMapClientTemplate().update("Zona.admin.asignarPadre", parametros);
	}
}
