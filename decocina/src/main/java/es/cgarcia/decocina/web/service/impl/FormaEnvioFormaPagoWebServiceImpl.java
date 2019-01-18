package es.cgarcia.decocina.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.dao.IFormaEnvioFormaPagoWebDao;
import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;
import es.cgarcia.decocina.web.service.IFormaEnvioFormaPagoWebService;

/**
 * Contiene los métodos necesarios para trabajar con una FormaEnvioFormaPago de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("formaEnvioFormaPagoWebService")
public class FormaEnvioFormaPagoWebServiceImpl extends GenericServiceImpl<FormaEnvioFormaPago, IFormaEnvioFormaPagoWebDao> implements IFormaEnvioFormaPagoWebService{
	
	/**
	 * Constructor.
	 * 
	 * @param formaEnvioFormaPago Dao para la FormaEnvioFormaPago.
	 */
	@Autowired
	public FormaEnvioFormaPagoWebServiceImpl(IFormaEnvioFormaPagoWebDao formaEnvioFormaPago){
		
		super(formaEnvioFormaPago);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FormaEnvioFormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio) {
		
		return dao.findAllByIdZonaAndIdFormaEnvio(id, idFormaEnvio);
	}
}