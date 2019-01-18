package es.cgarcia.decocina.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.dao.IEstadoAdminDao;
import es.cgarcia.decocina.admin.service.IEstadoAdminService;
import es.cgarcia.decocina.web.model.Estado;

/**
 * Contiene los métodos necesarios para trabajar con un Pedido de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("estadoAdminService")
public class EstadoAdminServiceImpl extends GenericServiceImpl<Estado, IEstadoAdminDao> implements IEstadoAdminService{

	/**
	 * Constructor.
	 * 
	 * @param pedidoAdminDao Dao para los Estados.
	 */
	@Autowired
	public EstadoAdminServiceImpl(IEstadoAdminDao estadoAdminDao){
		
		super(estadoAdminDao);
	}
}