package es.cgarcia.decocina.admin.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import es.cgarcia.decocina.admin.buscador.FabricanteBuscador;
import es.cgarcia.decocina.admin.dao.IFabricanteAdminDao;
import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.model.Fabricante;

/**
 * Implementación del Dao para el Fabricante de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *  
 */
@Repository("fabricanteAdminDao")
public class FabricanteAdminDaoImpl extends GenericAdminDaoIbatisImpl<Fabricante> implements IFabricanteAdminDao{
	
	/**
	 * Constructor.
	 * 
	 * @param sqlMapClient SqlMapClient.
	 * @param fabricante Fabricante.
	 */
	@Autowired
    public FabricanteAdminDaoImpl(SqlMapClient sqlMapClient, Fabricante fabricante) {
		
        super(sqlMapClient, fabricante);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Fabricante> findAllBusqueda(FabricanteBuscador fabricanteBuscador) {
		
		String nombre = fabricanteBuscador.getNombre();
		
		StringBuffer sbNombre = new StringBuffer();
		
		sbNombre.append(' ');
		sbNombre.append(nombre);
		sbNombre.append(' ');
		
		String nombreEspacios = sbNombre.toString();
		String nombrePorcentaje = nombreEspacios.replaceAll(" ", "%");
		
		Paginacion paginacion = fabricanteBuscador.getPaginacion();
		
		Long start = paginacion.getStart();
		Long limit = paginacion.getLimit();
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("nombre", nombrePorcentaje);
		parametros.put("start", start);
		parametros.put("limit", limit);
		
		List<Fabricante> fabricantes = getSqlMapClientTemplate().queryForList("Fabricante.admin.findAllBusqueda", parametros);
		
		parametros.put("isCount", "isCount");
		
		Long count = (Long)getSqlMapClientTemplate().queryForObject("Fabricante.admin.countFindAllBusqueda", parametros);
		
		paginacion.setCount(count);
		
		return fabricantes;
	}
}
