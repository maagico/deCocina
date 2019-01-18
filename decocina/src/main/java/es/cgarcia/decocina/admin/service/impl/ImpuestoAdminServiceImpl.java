package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.ImpuestoBuscador;
import es.cgarcia.decocina.admin.dao.IImpuestoAdminDao;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.service.IImpuestoAdminService;
import es.cgarcia.decocina.web.model.Impuesto;

/**
 * Contiene los métodos necesarios para trabajar con un Impuesto de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("impuestoAdminService")
public class ImpuestoAdminServiceImpl extends GenericServiceImpl<Impuesto, IImpuestoAdminDao> implements IImpuestoAdminService{

	/**
	 * Constructor.
	 * 
	 * @param impuestoAdminDao Dao para los Impuestos.
	 */
	@Autowired
	public ImpuestoAdminServiceImpl(IImpuestoAdminDao impuestoAdminDao){
		
		super(impuestoAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Impuesto> findAllBusqueda(ImpuestoBuscador impuestoBuscador){
		
		return dao.findAllBusqueda(impuestoBuscador);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Impuesto> findAllByIdZona(Long id){
		
		return dao.findAllByIdZona(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Impuesto> findAll(Boolean paraArticulo, Boolean paraZona) {
		
		return dao.findAll(paraArticulo, paraZona);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Impuesto impuesto){
	
		try{
			
			dao.delete(impuesto);
			
		}catch(DataIntegrityViolationException e){
		
			throw new DeCocinaAdminDIVRuntimeException("El impuesto tiene dependencias, no se puede borrar", e);
		}
	}
}