package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.FormaPagoBuscador;
import es.cgarcia.decocina.admin.dao.IFormaPagoAdminDao;
import es.cgarcia.decocina.admin.service.IFormaPagoAdminService;
import es.cgarcia.decocina.web.model.FormaPago;

/**
 * Contiene los métodos necesarios para trabajar con una Forma de Pago de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("formaPagoAdminService")
public class FormaPagoAdminServiceImpl extends GenericServiceImpl<FormaPago, IFormaPagoAdminDao> implements IFormaPagoAdminService{

	/**
	 * Constructor.
	 * 
	 * @param formaPagoAdminDao Dao para las Formas de Pago.
	 */
	@Autowired
	public FormaPagoAdminServiceImpl(IFormaPagoAdminDao formaPagoAdminDao){
		
		super(formaPagoAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FormaPago> findAllBusqueda(FormaPagoBuscador formaPagoBuscador) {
		
		return dao.findAllBusqueda(formaPagoBuscador);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<FormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio){
		
		return dao.findAllByIdZonaAndIdFormaEnvio(id, idFormaEnvio);
	}
	
}