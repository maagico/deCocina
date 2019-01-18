package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.FormaPago;


/**
 * Interfaz del Dao para las Formas de Pago de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaPagoWebDao extends IGenericDao<FormaPago> {
	
	/**
	 * Devuelve la relación entre una Zona de Envío y sus Formas de Pago.
	 * 
	 * @param id Id de la Zona.
	 * @param idFormaEnvio Id de la Forma de Envio.
	 * @return Listado de FormaEnvioFormaPago que contiene las relaciones entre Formas de Envío y sus Formas de Pago.
	 */
	List<FormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio);
}
