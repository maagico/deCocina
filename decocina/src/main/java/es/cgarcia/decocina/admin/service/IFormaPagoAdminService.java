package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.FormaPagoBuscador;
import es.cgarcia.decocina.web.model.FormaPago;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los métodos necesarios para trabajar con una Forma de Pago
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaPagoAdminService extends IGenericService<FormaPago>{
	
	/**
	 * Devuelve las Formas de Pago por los campos de búsqueda que hay en FormaPagoBuscador y también de la paginación.
	 * 
	 * @param formaPagoBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Formas de Pago filtradas por el nombre.
	 */
	List<FormaPago> findAllBusqueda(FormaPagoBuscador formaPagoBuscador);

	/**
	 * Devuelve las Formas de Envío no añadidas a la Zona.  
	 * @param id Id de la Zona.
	 * @param idFormaEnvio Id de la Forma de Envío.
	 * @return Formas de Envío no añadidas de la zona.
	 */
	List<FormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio);
}
