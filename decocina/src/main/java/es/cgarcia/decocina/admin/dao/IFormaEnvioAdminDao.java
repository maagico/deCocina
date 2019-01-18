package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.FormaEnvioBuscador;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.FormaEnvio;


/**
 * Interfaz del Dao para las Formas de Envío de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IFormaEnvioAdminDao extends IGenericDao<FormaEnvio> {

	/**
	 * Devuelve las Formas de Envío por los campos de búsqueda que hay en FormaEnvioBuscador y también de la paginación.
	 * 
	 * @param formaEnvioBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Formas de Envío filtrados por el nombre.
	 */
	List<FormaEnvio> findAllBusqueda(FormaEnvioBuscador formaEnvioBuscador);

	/**
	 * Devuelve las Zonas de Envío asignadas a la Zona.
	 * @param id Id de la Zona.
	 * @return Formas de Envío asignadas a la Zona.
	 */
	List<FormaEnvio> findAllByIdZona(Long id);
}
