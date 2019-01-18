package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una ZonaFormaEnvio
 * de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaEnvioFormaPagoWebService extends IGenericService<FormaEnvioFormaPago>{
	
	/**
	 * Devuelve la relación entre una Zona de Envío y sus Formas de Pago.
	 * 
	 * @param id Id de la Zona.
	 * @param idFormaEnvio Id de la Forma de Envio.
	 * @return Listado de FormaEnvioFormaPago que contiene las relaciones entre Formas de Envío y sus Formas de Pago.
	 */
	List<FormaEnvioFormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio);
}
