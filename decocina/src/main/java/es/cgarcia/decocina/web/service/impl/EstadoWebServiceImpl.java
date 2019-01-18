package es.cgarcia.decocina.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.dao.IEstadoWebDao;
import es.cgarcia.decocina.web.model.Estado;
import es.cgarcia.decocina.web.service.IEstadoWebService;

/**
 * Contiene los métodos necesarios para trabajar con un Estado de la parte Web. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("estadoWebService")
public class EstadoWebServiceImpl extends GenericServiceImpl<Estado, IEstadoWebDao> implements IEstadoWebService{

	/**
	 * Constructor.
	 * 
	 * @param estadoWebDao Dao para los Estados.
	 */
	@Autowired
	public EstadoWebServiceImpl(IEstadoWebDao estadoWebDao){
		
		super(estadoWebDao);
	}
}