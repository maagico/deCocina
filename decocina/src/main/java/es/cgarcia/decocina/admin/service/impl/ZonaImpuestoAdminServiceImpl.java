package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.dao.IZonaImpuestoAdminDao;
import es.cgarcia.decocina.admin.service.IZonaImpuestoAdminService;
import es.cgarcia.decocina.web.model.ZonaImpuesto;

/**
 * Contiene los métodos necesarios para trabajar con una ZonaImpuesto de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("zonaImpuestoAdminService")
public class ZonaImpuestoAdminServiceImpl extends GenericServiceImpl<ZonaImpuesto, IZonaImpuestoAdminDao> implements IZonaImpuestoAdminService{

	/**
	 * Constructor.
	 * 
	 * @param zonaAdminDao Dao para las Zonas.
	 */
	@Autowired
	public ZonaImpuestoAdminServiceImpl(IZonaImpuestoAdminDao zonaImpuestoAdminDao){
		
		super(zonaImpuestoAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ZonaImpuesto> findByIdZona(Long id) {
		
		return dao.findByIdZona(id);
	}
}