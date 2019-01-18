package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.Categoria;


/**
 * Interfaz del Dao para las Categorías de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface ICategoriaWebDao extends IGenericDao<Categoria> {
	
	/**
	 * Devuelve todas las Categorías del padre.
	 * @param idPadre Id del padre.
	 * @return Listado de Categorías del padre.
	 */
	List<Categoria> findByIdPadre(Long idPadre);

	/**
	 * Devuelve las Categorías para el SiteMap.
	 * @return Listado de Categorías para el SiteMap. 
	 */
	List<Categoria> findAllSiteMap();

	/**
	 * Devuelve una Categoría a partir de su URL amigable.
	 * @param url URL antes del forward. 
	 * @return Categoría a partir de su URL amigable.
	 */
	Categoria findByURL(String url);
}
