package es.cgarcia.decocina.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.dao.IFormaEnvioWebDao;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.service.IFormaEnvioWebService;

/**
 * Contiene los métodos necesarios para trabajar con una Forma de Envío de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("formaEnvioWebService")
public class FormaEnvioWebServiceImpl extends GenericServiceImpl<FormaEnvio, IFormaEnvioWebDao> implements IFormaEnvioWebService{

	/**
	 * Constructor.
	 * 
	 * @param formaEnvioAdminDao Dao para las Formas de Envío.
	 */
	@Autowired
	public FormaEnvioWebServiceImpl(IFormaEnvioWebDao formaEnvioWebDao){
		
		super(formaEnvioWebDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FormaEnvio> findAllByIdZona(Long id) {
		
		return dao.findAllByIdZona(id);
	}
}