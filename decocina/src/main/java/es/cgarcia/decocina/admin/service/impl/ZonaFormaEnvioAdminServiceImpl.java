package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.dao.IZonaFormaEnvioAdminDao;
import es.cgarcia.decocina.admin.service.IZonaFormaEnvioAdminService;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;

/**
 * Contiene los métodos necesarios para trabajar con una ZonaFormaEnvio de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("zonaFormaEnvioAdminService")
public class ZonaFormaEnvioAdminServiceImpl extends GenericServiceImpl<ZonaFormaEnvio, IZonaFormaEnvioAdminDao> implements IZonaFormaEnvioAdminService{

	/**
	 * Constructor.
	 * 
	 * @param zonaFormaEnvioAdminDao Dao para la ZonaFormaEnvio.
	 */
	@Autowired
	public ZonaFormaEnvioAdminServiceImpl(IZonaFormaEnvioAdminDao zonaFormaEnvioAdminDao){
		
		super(zonaFormaEnvioAdminDao);
	}

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public List<ZonaFormaEnvio> findByIdZona(Long id) {
		
		return dao.findByIdZona(id);
	}
}