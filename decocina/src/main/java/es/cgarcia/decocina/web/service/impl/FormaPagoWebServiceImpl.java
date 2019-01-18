package es.cgarcia.decocina.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.dao.IFormaPagoWebDao;
import es.cgarcia.decocina.web.model.FormaPago;
import es.cgarcia.decocina.web.service.IFormaPagoWebService;

/**
 * Contiene los métodos necesarios para trabajar con una Forma de Pago de la parte Web. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("formaPagoWebService")
public class FormaPagoWebServiceImpl extends GenericServiceImpl<FormaPago, IFormaPagoWebDao> implements IFormaPagoWebService{

	/**
	 * Constructor.
	 * 
	 * @param formaPagoWebDao Dao para las Formas de Pago.
	 */
	@Autowired
	public FormaPagoWebServiceImpl(IFormaPagoWebDao formaPagoWebDao){
		
		super(formaPagoWebDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio) {
		
		return dao.findAllByIdZonaAndIdFormaEnvio(id, idFormaEnvio);
	}

	
}