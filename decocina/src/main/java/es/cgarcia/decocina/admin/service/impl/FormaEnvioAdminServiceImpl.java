package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.FormaEnvioBuscador;
import es.cgarcia.decocina.admin.dao.IFormaEnvioAdminDao;
import es.cgarcia.decocina.admin.service.IFormaEnvioAdminService;
import es.cgarcia.decocina.web.model.FormaEnvio;

/**
 * Contiene los métodos necesarios para trabajar con una Forma de Envío de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("formaEnvioAdminService")
public class FormaEnvioAdminServiceImpl extends GenericServiceImpl<FormaEnvio, IFormaEnvioAdminDao> implements IFormaEnvioAdminService{

	/**
	 * Constructor.
	 * 
	 * @param formaEnvioAdminDao Dao para las Formas de Envío.
	 */
	@Autowired
	public FormaEnvioAdminServiceImpl(IFormaEnvioAdminDao formaEnvioAdminDao){
		
		super(formaEnvioAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FormaEnvio> findAllBusqueda(FormaEnvioBuscador formaEnvioBuscador) {
		
		return dao.findAllBusqueda(formaEnvioBuscador);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FormaEnvio> findAllByIdZona(Long id) {
		return dao.findAllByIdZona(id);
	}
}