package es.cgarcia.decocina.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.dao.IFormaEnvioFormaPagoWebDao;
import es.cgarcia.decocina.web.dao.IZonaFormaEnvioWebDao;
import es.cgarcia.decocina.web.dao.IZonaImpuestoWebDao;
import es.cgarcia.decocina.web.dao.IZonaWebDao;
import es.cgarcia.decocina.web.model.FormaEnvio;
import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.model.ZonaFormaEnvio;
import es.cgarcia.decocina.web.model.ZonaImpuesto;
import es.cgarcia.decocina.web.service.IZonaWebService;

/**
 * Contiene los métodos necesarios para trabajar con una Zona de la parte Web. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("zonaWebService")
public class ZonaWebServiceImpl extends GenericServiceImpl<Zona, IZonaWebDao> implements IZonaWebService{

	/**
	 * Dao para los Impuestos.
	 */
	@Autowired
	private IZonaImpuestoWebDao zonaImpuestoWebDao;
	
	/**
	 * Dao para las Formas de Envío.
	 */
	@Autowired
	private IZonaFormaEnvioWebDao zonaFormaEnvioWebDao;
	
	/**
	 * Dao para las Formas de Pago.
	 */
	@Autowired
	private IFormaEnvioFormaPagoWebDao formaEnvioFormaPagoWebDao;
	
	/**
	 * Constructor.
	 * 
	 * @param zonaWebDao Dao para las Zonas.
	 */
	@Autowired
	public ZonaWebServiceImpl(IZonaWebDao zonaWebDao){
		
		super(zonaWebDao);
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
	public List<Zona> findAllProvinciasActivas() {
		
		return dao.findAllProvinciasActivas();
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
		List<ZonaImpuesto> zonaImpuestos = zonaImpuestoWebDao.findByIdZona(id);
		
		zona.setZonaImpuestos(zonaImpuestos);
		
		//Recuperamos las Formas de Envío asociadas a la Zona.
		List<ZonaFormaEnvio> zonaFormasEnvio = zonaFormaEnvioWebDao.findByIdZona(id);
		
		zona.setZonaFormasEnvio(zonaFormasEnvio);
		
		for (ZonaFormaEnvio zonaFormaEnvio : zonaFormasEnvio) {
			
			FormaEnvio formaEnvio = zonaFormaEnvio.getFormaEnvio();
			
			Long idFormaEnvio = formaEnvio.getId();
			
			//Recuperamos las Formas de Pago asociadas a la Forma de Envío.
			List<FormaEnvioFormaPago> formaEnvioFormasPago = formaEnvioFormaPagoWebDao.findAllByIdZonaAndIdFormaEnvio(id, idFormaEnvio);
			
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
}