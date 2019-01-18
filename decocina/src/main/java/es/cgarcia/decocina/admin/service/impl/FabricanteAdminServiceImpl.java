package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.FabricanteBuscador;
import es.cgarcia.decocina.admin.dao.IFabricanteAdminDao;
import es.cgarcia.decocina.admin.service.IFabricanteAdminService;
import es.cgarcia.decocina.web.model.Fabricante;

/**
 * Contiene los métodos necesarios para trabajar con un Fabricante de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("fabricanteAdminService")
public class FabricanteAdminServiceImpl extends GenericServiceImpl<Fabricante, IFabricanteAdminDao> implements IFabricanteAdminService{

	/**
	 * Constructor.
	 * 
	 * @param fabricanteAdminDao Dao para los fabricantes.
	 */
	@Autowired
	public FabricanteAdminServiceImpl(IFabricanteAdminDao fabricanteAdminDao){
		
		super(fabricanteAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Fabricante> findAllBusqueda(FabricanteBuscador fabricanteBuscador) {
		
		return dao.findAllBusqueda(fabricanteBuscador);
	}
}