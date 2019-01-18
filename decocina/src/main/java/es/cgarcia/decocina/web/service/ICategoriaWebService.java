package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.menu.CategoriaMenu;
import es.cgarcia.decocina.web.model.Categoria;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una Categoría
 * de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface ICategoriaWebService extends IGenericService<Categoria>{

	/**
	 * Crea el Menu de las Categorías.
	 * @return Categorías.
	 */
	List<CategoriaMenu> crearCategoriasMenu();

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
