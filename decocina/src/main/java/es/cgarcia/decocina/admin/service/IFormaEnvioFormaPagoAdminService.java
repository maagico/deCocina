package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una ZonaFormaEnvio
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaEnvioFormaPagoAdminService extends IGenericService<FormaEnvioFormaPago>{
	
	/**
	 * Devuelve la relación entre una Zona de Envío y sus Formas de Pago.
	 * 
	 * @param id Id de la Zona.
	 * @param idFormaEnvio Id de la Forma de Envio.
	 * @return Listado de FormaEnvioFormaPago que contiene las relaciones entre Formas de Envío y sus Formas de Pago.
	 */
	List<FormaEnvioFormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio);

	/**
	 * Borra las Formas de Envío y las Formas de Pago asociadas al Envío.
	 * 
	 * @param id Id de la Forma de Envóo.
	 * @param idZona Id de la Zona.
	 */
	void deleteFormaEnvio(Long id, Long idZona);
}
