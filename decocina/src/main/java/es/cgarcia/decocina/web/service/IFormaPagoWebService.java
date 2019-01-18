package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.model.FormaPago;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una Forma de Pago
 * de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaPagoWebService extends IGenericService<FormaPago>{
	
	/**
	 * Devuelve las Formas de Pago.
	 * 
	 * @param id Id de la Zona.
	 * @param idFormaEnvio Id de la Forma de Envio.
	 * @return Listado de Formas de Pago.
	 */
	List<FormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio);
}
