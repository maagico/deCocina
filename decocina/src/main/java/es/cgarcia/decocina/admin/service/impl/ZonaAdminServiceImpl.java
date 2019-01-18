package es.cgarcia.decocina.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.ZonaBuscador;
import es.cgarcia.decocina.admin.dao.IFormaEnvioFormaPagoAdminDao;
import es.cgarcia.decocina.admin.dao.IZonaAdminDao;
import es.cgarcia.decocina.admin.dao.IZonaFormaEnvioAdminDao;
import es.cgarcia.decocina.admin.dao.IZonaImpuestoAdminDao;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.service.IZonaAdminService;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;
import es.cgarcia.decocina.web.model.ZonaImpuesto;

/**
 * Contiene los métodos necesarios para trabajar con una Zona de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("zonaAdminService")
public class ZonaAdminServiceImpl extends GenericServiceImpl<Zona, IZonaAdminDao> implements IZonaAdminService{

	/**
	 * Dao para los Impuestos.
	 */
	@Autowired
	private IZonaImpuestoAdminDao zonaImpuestoADminDao;
	
	/**
	 * Dao para las Formas de Envío.
	 */
	@Autowired
	private IZonaFormaEnvioAdminDao zonaFormaEnvioAdminDao;
	
	/**
	 * Dao para las Formas de Pago.
	 */
	@Autowired
	private IFormaEnvioFormaPagoAdminDao formaEnvioFormaPagoAdminDao;
	
	/**
	 * Constructor.
	 * 
	 * @param zonaAdminDao Dao para las Zonas.
	 */
	@Autowired
	public ZonaAdminServiceImpl(IZonaAdminDao zonaAdminDao){
		
		super(zonaAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zona> findAllBusqueda(ZonaBuscador zonaBuscador) {
		
		return dao.findAllBusqueda(zonaBuscador);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zona> findAllProvincias() {
		
		return dao.findAllProvincias();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void asignarPadre(Long idZonaHija, Long idZonaPadre) {
		
		dao.asignarPadre(idZonaHija,idZonaPadre);
		
		if(idZonaPadre == null){
			
			Zona zona = dao.findById(idZonaHija);
			zona.setHeredarFormas(false);
			
			dao.update(zona);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zona findById(Long id) {
		
		Zona zonaOrigen = dao.findById(id);
		
		Zona zona = dao.findById(id);
		
		boolean buscar = true;
		boolean encontrado = false;
		
		while(zona != null && buscar)
		{
			Boolean heredarFormas = zona.getHeredarFormas();
			
			if(heredarFormas)
			{		
				Zona zonaPadre = zona.getZonaPadre();
				
				if(zonaPadre  != null)
				{
					long idPadre = zona.getZonaPadre().getId();
					zona = dao.findById(idPadre);
				}else
				{
					buscar = false;
					encontrado = true;
				}
			}else
			{
				buscar = false;
				encontrado = true;
			}
		}
		
		id = zona.getId();
		
		//Recuperamos los Impuestos asignados a la Zona.
		List<ZonaImpuesto> zonaImpuestos = zonaImpuestoADminDao.findByIdZona(id);
		
		zona.setZonaImpuestos(zonaImpuestos);
		
		//Recuperamos las Formas de Envío asociadas a la Zona.
		List<ZonaFormaEnvio> zonaFormasEnvio = zonaFormaEnvioAdminDao.findByIdZona(id);
		
		zona.setZonaFormasEnvio(zonaFormasEnvio);
		
		for (ZonaFormaEnvio zonaFormaEnvio : zonaFormasEnvio) {
			
			FormaEnvio formaEnvio = zonaFormaEnvio.getFormaEnvio();
			
			Long idFormaEnvio = formaEnvio.getId();
			
			//Recuperamos las Formas de Pago asociadas a la Forma de Envío.
			List<FormaEnvioFormaPago> formaEnvioFormasPago = formaEnvioFormaPagoAdminDao.findAllByIdZonaAndIdFormaEnvio(id, idFormaEnvio);
			
			formaEnvio.setFormaEnvioFormasPago(formaEnvioFormasPago);			
			
		}
		
		if(encontrado){
				
			Long idZonaOrigen = zonaOrigen.getId();
			String nombreOrigen = zonaOrigen.getNombre();
			Boolean heredarFormasOrigen = zonaOrigen.getHeredarFormas();
			Zona zonaPadreOrigen = zonaOrigen.getZonaPadre();
			
			zona.setId(idZonaOrigen);
			zona.setNombre(nombreOrigen);
			zona.setHeredarFormas(heredarFormasOrigen);
			zona.setZonaPadre(zonaPadreOrigen);
		}
		
		return zona;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(Zona zona){
	
		try{
			
			dao.delete(zona);
			
		}catch(DataIntegrityViolationException e){
		
			throw new DeCocinaAdminDIVRuntimeException("La Zona tiene dependencias, no se puede borrar", e);
		}
	}
}