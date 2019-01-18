package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.buscador.FormaPagoBuscador;
import es.cgarcia.decocina.admin.dao.IFormaPagoAdminDao;
import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.model.FormaPago;

/**
 * Implementación del Dao para las Formas de Pago de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("formaPagoAdminDao")
public class FormaPagoAdminDaoImpl extends GenericAdminDaoIbatisImpl<FormaPago> implements IFormaPagoAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param formulaEnvio Fórmula de Envío.
	 */
	@Autowired
    public FormaPagoAdminDaoImpl(SqlMapClient sqlMapClient, FormaPago formaPago) {
		
        super(sqlMapClient, formaPago);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaPago> findAllBusqueda(FormaPagoBuscador formaPagoBuscador) {
		
		String nombre = formaPagoBuscador.getNombre();
		
		StringBuffer sbNombre = new StringBuffer();
		
		sbNombre.append(' ');
		sbNombre.append(nombre);
		sbNombre.append(' ');
		
		String nombreEspacios = sbNombre.toString();
		String nombrePorcentaje = nombreEspacios.replaceAll(" ", "%");
		
		Paginacion paginacion = formaPagoBuscador.getPaginacion();
		
		Long start = paginacion.getStart();
		Long limit = paginacion.getLimit();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nombre", nombrePorcentaje);
		parametros.put("start", start);
		parametros.put("limit", limit);
		
		List<FormaPago> formasPago = getSqlMapClientTemplate().queryForList("FormaPago.admin.findAllBusqueda", parametros);
		
		parametros.put("isCount", "isCount");
		
		Long count = (Long)getSqlMapClientTemplate().queryForObject("FormaPago.admin.countFindAllBusqueda", parametros);
		
		paginacion.setCount(count);
		
		return formasPago;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaPago> findAllByIdZona(Long id) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		
		return getSqlMapClientTemplate().queryForList("FormaPago.admin.findAllByIdZona", parametros);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio) {
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id", id);
		parametros.put("idFormaEnvio", idFormaEnvio);
		
		return getSqlMapClientTemplate().queryForList("FormaPago.admin.findAllByIdZonaAndIdFormaEnvio", parametros);
	}
}
