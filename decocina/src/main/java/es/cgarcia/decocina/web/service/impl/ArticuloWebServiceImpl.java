package es.cgarcia.decocina.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.annotation.ProfileExecution;
import es.cgarcia.decocina.web.dao.IArticuloWebDao;
import es.cgarcia.decocina.web.dao.IZonaImpuestoWebDao;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Impuesto;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.model.ZonaImpuesto;
import es.cgarcia.decocina.web.service.IArticuloWebService;
import es.cgarcia.decocina.web.service.IZonaWebService;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;
import es.cgarcia.decocina.web.wrapper.PedidoWrapper;

/**
 * Contiene los métodos necesarios para trabajar con un Artículo de la parte Web. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

@Service("articuloWebService")
public class ArticuloWebServiceImpl extends GenericServiceImpl<Articulo, IArticuloWebDao> implements IArticuloWebService{

	/**
	 * Servicio para las Zonas.
	 */
	@Autowired
	private IZonaWebService zonaWebService;
	
	/**
	 * Dao para las relaciones entre Zonas e Impuestos.
	 */
	@Autowired
	private IZonaImpuestoWebDao zonaImpuestoWebDao;
	
	/**
	 * Constructor.
	 * 
	 * @param articuloWebDao Dao para los Artículos.
	 */
	@Autowired
	public ArticuloWebServiceImpl(IArticuloWebDao articuloWebDao){
		
		super(articuloWebDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Articulo findById(ClienteWrapper clienteWrapper, Long id) {
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		Articulo articulo = dao.findById(id);
		
		if(!esInvitado){
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaPadre = zonaWebService.findById(idZona);
			
			Long idZonaPadre = zonaPadre.getId();
			
			ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
			
			articulo = calcularIva(articulo, zona, zonaImpuestoBD);
		}
		
		return articulo;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Articulo findByURL(ClienteWrapper clienteWrapper, String url) {
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		Articulo articulo = dao.findByURL(url);
		
		if(!esInvitado && articulo != null){
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaPadre = zonaWebService.findById(idZona);
			
			Long idZonaPadre = zonaPadre.getId();
			
			ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
			
			articulo = calcularIva(articulo, zona, zonaImpuestoBD);
		}
		
		return articulo;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findByIdCategoria(ClienteWrapper clienteWrapper, Long idCategoria) {
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		List<Articulo> articulos = dao.findByIdCategoria(idCategoria);
		
		if(!esInvitado){
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaPadre = zonaWebService.findById(idZona);
			
			Long idZonaPadre = zonaPadre.getId();
			
			ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
			
			articulos = calcularIva(articulos, zona, zonaImpuestoBD);
		}
		
		return articulos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findByNombre(ClienteWrapper clienteWrapper, String texto) {
		
		List<Articulo> articulos = null;
		
		if(texto != null && !texto.equals("")){
			
			texto = texto.replace('<', ' ');
			texto = texto.replace('\'', ' ');
		
			Integer tam = texto.length() - 1;
			
			if(texto.charAt(tam) == 's')
			{
				texto = texto.substring(0, tam);
			}
			
			StringBuilder sbTexto = new StringBuilder();
			sbTexto.append(' ');
			sbTexto.append(texto);
			sbTexto.append(' ');
			
			texto = sbTexto.toString();
			
			texto = texto.replaceAll(" ", "%");
			
			articulos = dao.findByNombre(texto);
		}
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		if(!esInvitado){
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaPadre = zonaWebService.findById(idZona);
			
			Long idZonaPadre = zonaPadre.getId();
			
			ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
			
			articulos = calcularIva(articulos, zona, zonaImpuestoBD);
		}
		
		return articulos;	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findRelacionadosByIdCategoria(ClienteWrapper clienteWrapper, Long idArticulo, Long idCategoria) {
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		List<Articulo> articulos = dao.findRelacionadosByIdCategoria(idArticulo, idCategoria);
		
		if(!esInvitado){
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaPadre = zonaWebService.findById(idZona);
			
			Long idZonaPadre = zonaPadre.getId();
			
			ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
			
			articulos = calcularIva(articulos, zona, zonaImpuestoBD);
		}
		
		return articulos;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findNovedades(ClienteWrapper clienteWrapper, Integer limit) {
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		List<Articulo> articulos = dao.findNovedades(limit);
		
		if(!esInvitado){
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaPadre = zonaWebService.findById(idZona);
			
			Long idZonaPadre = zonaPadre.getId();
			
			ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
			
			articulos = calcularIva(articulos, zona, zonaImpuestoBD);
		}
			
		return articulos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@ProfileExecution
	public List<Articulo> findMasVendidos(ClienteWrapper clienteWrapper, Long idCategoria, Integer limit) {
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		List<Articulo> articulos =  dao.findMasVendidos(idCategoria, limit);
	
		if(!esInvitado){
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaPadre = zonaWebService.findById(idZona);
			
			Long idZonaPadre = zonaPadre.getId();
			
			ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
			
			articulos = calcularIva(articulos, zona, zonaImpuestoBD);
		}
		
		return articulos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findMasVistos(ClienteWrapper clienteWrapper, Long idCategoria, Integer limit) {
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		List<Articulo> articulos =  dao.findMasVistos(idCategoria, limit);
		
		if(!esInvitado){
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaPadre = zonaWebService.findById(idZona);
			
			Long idZonaPadre = zonaPadre.getId();
			
			ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
			
			articulos = calcularIva(articulos, zona, zonaImpuestoBD);
		}
		
		return articulos;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findVentasRelacionadas(ClienteWrapper clienteWrapper, Long idArticulo, Integer limit) {
		
		Boolean esInvitado = clienteWrapper.getEsInvitado();
		
		List<Articulo> articulos =  dao.findVentasRelacionadas(idArticulo, limit);
		
		if(!esInvitado){
			
			PedidoWrapper pedidoWrapper = clienteWrapper.getPedidoWrapper();
			
			Zona zona = pedidoWrapper.getZona();
			
			Long idZona = zona.getId();
			
			Zona zonaPadre = zonaWebService.findById(idZona);
			
			Long idZonaPadre = zonaPadre.getId();
			
			ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
			
			articulos = calcularIva(articulos, zona, zonaImpuestoBD);
		}
		
		return articulos;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findAllSiteMap() {
		
		return dao.findAllSiteMap();
	}
	
	/**
	 * Comprueba si el iva del Artículo está en la zona. Si no está quita el iva del Artículo y aplica el de la Zona.
	 * @param articulos Artículos.
	 * @param zona Zona.
	 * @param zonaImpuestoMasPrioridad ZonaImpuesto con más prioridad.
	 * @return Articulos con el iva calculado dependiendo del iva de la Zona.
	 */
	private List<Articulo> calcularIva(List<Articulo> articulos, Zona zona, ZonaImpuesto zonaImpuestoMasPrioridad) {
		
		Long idZona = zona.getId();
		
		Zona zonaPadre = zonaWebService.findById(idZona);
		
		Long idZonaPadre = zonaPadre.getId();
		
		ZonaImpuesto zonaImpuestoBD = zonaImpuestoWebDao.findImpuestoMasPrioridad(idZonaPadre);
		
		for (Articulo articulo : articulos) {
			
			calcularIva(articulo, zonaPadre, zonaImpuestoBD);
		}
		
		return articulos;
	}
	
	/**
	 * Comprueba si el iva del Artículo está en la zona. Si no está quita el iva del Artículo y aplica el de la Zona.
	 * @param articulo Artículo.
	 * @param zona Zona.
	 * @param zonaImpuestoMasPrioridad ZonaImpuesto con más prioridad.
	 * @return Articulo.
	 */
	private Articulo calcularIva(Articulo articulo, Zona zona, ZonaImpuesto zonaImpuestoMasPrioridad) {
		
		Boolean encontrado = false;
		
		Impuesto impuestoArticulo = articulo.getImpuesto();
		
		Long idImpuesto = impuestoArticulo.getId();
		
		List<ZonaImpuesto> zonasImpuesto = zona.getZonaImpuestos();
		
		for (ZonaImpuesto zonaImpuesto : zonasImpuesto) {
			
			Impuesto impuestoZona = zonaImpuesto.getImpuesto();
			Long idImpuestoZona = impuestoZona.getId();
			
			if(idImpuestoZona.equals(idImpuesto)){
				
				encontrado = true;
			}
		}
		
		//No tiene el mismo iva. Quitamos el iva del Artículo.
		if(!encontrado){
			
			Double iva = impuestoArticulo.getValor();
			Double precio = articulo.getPrecio();
			
			//Si el iva a quitar es 0 no hace falta quitarlo.
			if(!iva.equals(0d)){
				
				//Quitamos el iva.
				precio = precio / ((iva / 100) + 1);
				
				//Añadimos el iva de la Zona. Recuperamos el iva con más prioridad(menos es más) para la Zona.
				Impuesto impuestoBD = zonaImpuestoMasPrioridad.getImpuesto();
				
				Double impuestoMasPrioridad = impuestoBD.getValor();
				
				precio = precio * ((impuestoMasPrioridad / 100) + 1);
				
				articulo.setPrecio(precio);
				articulo.setImpuesto(impuestoBD);
			}
		}
		
		return articulo;
	}
}