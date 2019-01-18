package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.ZonaBuscador;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una Zona
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */

public interface IZonaAdminService extends IGenericService<Zona>{
	
	/**
	 * Devuelve una Zona partir de su id.
	 * @param id Id de la Zona.
	 * @return Zona con sus Impuestos y Formas de Envío y Pago asociadas.
	 */
	Zona findById(Long id);
	
	/**
	 * Devuelve las Zonas por los campos de búsqueda que hay en ZonaBuscador y también de la paginación.
	 * 
	 * @param zonaBuscador Contiene los campos de búsqueda y la paginación.
	 * @return Zonas filtrados por el nombre.
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
