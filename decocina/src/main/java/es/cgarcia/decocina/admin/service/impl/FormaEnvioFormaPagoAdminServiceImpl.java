package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.dao.IFormaEnvioFormaPagoAdminDao;
import es.cgarcia.decocina.admin.dao.IZonaFormaEnvioAdminDao;
import es.cgarcia.decocina.admin.service.IFormaEnvioFormaPagoAdminService;
import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;

/**
 * Contiene los métodos necesarios para trabajar con una FormaEnvioFormaPago de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("formaEnvioFormaPagoAdminService")
public class FormaEnvioFormaPagoAdminServiceImpl extends GenericServiceImpl<FormaEnvioFormaPago, IFormaEnvioFormaPagoAdminDao> implements IFormaEnvioFormaPagoAdminService{

	/**
	 * Dao para la ZonaFormaEnvío(Relación entre Zonas y Formas de Envío).
	 */
	@Autowired
	private IZonaFormaEnvioAdminDao zonaFormaEnvioAdminDao;
	
	/**
	 * Constructor.
	 * 
	 * @param formaEnvioFormaPago Dao para la FormaEnvioFormaPago.
	 */
	@Autowired
	public FormaEnvioFormaPagoAdminServiceImpl(IFormaEnvioFormaPagoAdminDao formaEnvioFormaPago){
		
		super(formaEnvioFormaPago);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FormaEnvioFormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio) {
		
		return dao.findAllByIdZonaAndIdFormaEnvio(id, idFormaEnvio);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteFormaEnvio(Long id, Long idZona) 
	{
		// Borramos la relacion de Formas de Envío con sus Formas de Pago.
		dao.deleteFormaEnvio(id, idZona);
		
		//Borramos la relación entre Zonas y  Formas de Envío.
		zonaFormaEnvioAdminDao.deleteFormaEnvio(id, idZona);
	}
}