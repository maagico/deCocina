package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.buscador.ImpuestoBuscador;
import es.cgarcia.decocina.admin.dao.IImpuestoAdminDao;
import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.model.Impuesto;

/**
 * Implementación del Dao para los Impuestos de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("impuestoAdminDao")
public class ImpuestoAdminDaoImpl extends GenericAdminDaoIbatisImpl<Impuesto> implements IImpuestoAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param impuesto Impuesto.
	 */
	@Autowired
    public ImpuestoAdminDaoImpl(SqlMapClient sqlMapClient, Impuesto impuesto) {
		
        super(sqlMapClient, impuesto);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Impuesto> findAllBusqueda(ImpuestoBuscador impuestoBuscador) {
		
		String nombre = impuestoBuscador.getNombre();
		
		StringBuffer sbNombre = new StringBuffer();
		
		sbNombre.append(' ');
		sbNombre.append(nombre);
		sbNombre.append(' ');
		
		String nombreEspacios = sbNombre.toString();
		String nombrePorcentaje = nombreEspacios.replaceAll(" ", "%");
		
		Paginacion paginacion = impuestoBuscador.getPaginacion();
		
		Long start = paginacion.getStart();
		Long limit = paginacion.getLimit();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nombre", nombrePorcentaje);
		parametros.put("start", start);
		parametros.put("limit", limit);
		
		List<Impuesto> impuesto = getSqlMapClientTemplate().queryForList("Impuesto.admin.findAllBusqueda", parametros);
		
		parametros.put("isCount", "isCount");
		
		Long count = (Long)getSqlMapClientTemplate().queryForObject("Impuesto.admin.countFindAllBusqueda", parametros);
		
		paginacion.setCount(count);
		
		return impuesto;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Impuesto> findAllByIdZona(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("Impuesto.admin.findAllByIdZona", parametros);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Impuesto> findAll(Boolean paraArticulo, Boolean paraZona) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("paraArticulo", paraArticulo);
		parametros.put("paraZona", paraZona);
		
		return getSqlMapClientTemplate().queryForList("Impuesto.admin.findAllImpuestos", parametros);

	}
}
