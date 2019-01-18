package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.CuponBuscador;
import es.cgarcia.decocina.admin.dao.ICuponAdminDao;
import es.cgarcia.decocina.admin.service.ICuponAdminService;
import es.cgarcia.decocina.web.model.Cupon;

/**
 * Contiene los métodos necesarios para trabajar con un Cupón de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("cuponAdminService")
public class CuponAdminServiceImpl extends GenericServiceImpl<Cupon, ICuponAdminDao> implements ICuponAdminService{

	/**
	 * Constructor.
	 * 
	 * @param cuponAdminDao Dao para los Cupones.
	 */
	@Autowired
	public CuponAdminServiceImpl(ICuponAdminDao cuponAdminDao){
		
		super(cuponAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Cupon> findAllBusqueda(CuponBuscador cuponBuscador) {
		
		return dao.findAllBusqueda(cuponBuscador);
	}
}