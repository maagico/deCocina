package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.FormaPagoBuscador;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.FormaPago;


/**
 * Interfaz del Dao para las Formas de Pago de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaPagoAdminDao extends IGenericDao<FormaPago> {

	/**
	 * Devuelve las Formas de Pago por los campos de búsqueda que hay en FormulaEnvioBuscador y también de la paginación.
	 * 
	 * @param FormulaEnvioBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Formas de Pago filtradas por el nombre.
	 */
	List<FormaPago> findAllBusqueda(FormaPagoBuscador formaPagoBuscador);

	/**
	 * Devuelve las Formas de Envío no añadidas a la Zona.  
	 * @param id Id de la Zona.
	 * @return Formas de Envio no añadidas de la Zona.
	 */
	List<FormaPago> findAllByIdZona(Long id);

	/**
	 * Devuelve las Formas de Envío no añadidas a la Zona.  
	 * @param id Id de la Zona.
	 * @param idFormaEnvio Id de la Forma de Envío.
	 * @return Formas de Envío no añadidas de la zona.
	 */
	List<FormaPago> findAllByIdZonaAndIdFormaEnvio(Long id, Long idFormaEnvio);
}