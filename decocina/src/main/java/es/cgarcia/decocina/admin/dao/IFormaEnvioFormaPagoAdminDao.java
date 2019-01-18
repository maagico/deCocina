package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.FormaEnvioFormaPago;


/**
 * Interfaz del Dao para la relación entre las Zona de Envío y sus Formas de Pago en la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaEnvioFormaPagoAdminDao extends IGenericDao<FormaEnvioFormaPago> {
	
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
