package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.ZonaBuscador;
import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Zona;


/**
 * Interfaz del Dao para las Zonas de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IZonaAdminDao extends IGenericDao<Zona> {

	/**
	 * Devuelve las Zonas por los campos de búsqueda que hay en ZonaBuscador y también de la paginación.
	 * 
	 * @param zonaBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Zonas filtradas por el nombre.
	 */
	List<Zona> findAllBusqueda(ZonaBuscador zonaBuscador);

	/**
	 * Devuelve las Provincias de España para los combos.
	 * 
	 * @return Todas las Provincias de España para los combos.
	 */
	List<Zona> findAllProvincias();
	
	/**
	 * Asigna la Zona padre a la Zona hija.
	 * 
	 * @param idZonaHija Id de la Zona hija.
	 * @param idZonaPadre Id de la Zona padre.
	 */
	void asignarPadre(Long idZonaHija, Long idZonaPadre);
}
