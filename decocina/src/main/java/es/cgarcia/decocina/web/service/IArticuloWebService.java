package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.wrapper.ClienteWrapper;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Artículo de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IArticuloWebService extends IGenericService<Articulo>{
	
	/**
	 * Devuelve un Artículo por su id.
	 * 
	 * @param clienteWrapper CLienteWrapper.
	 * @param id Id del Artículo.
	 * @return Devuelve un Artículo por su id..
	 */
	Articulo findById(ClienteWrapper clienteWrapper, Long id);
	
	/**
	 * Devuelve los Artículos de una Categoría.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param idCategoria Id de la Categoría de la que dependen los Artículos.
	 * @return Artículos de la Categoría.
	 */
	List<Articulo> findByIdCategoria(ClienteWrapper clienteWrapper, Long idCategoria);
	
	/**
	 * Devuelve los Artículos relacionados de una Categoría.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param idCategoria Id de la Categoría de la que dependen los Artículos.
	 * @return Artículos de la Categoría.
	 */
	List<Articulo> findRelacionadosByIdCategoria(ClienteWrapper clienteWrapper, Long idArticulo, Long idCategoria);
	
	/**
	 * Devuelve las últimas novedades añadidas.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param limit Límite de registros a mostrar
	 * @return Lista de artículos con las últimas novedades.
	 */
	List<Articulo> findNovedades(ClienteWrapper clienteWrapper, Integer limit);

	/**
	 * Devuelve los artículos más vendidos de la web si la Categoría es null o si no de la indicada.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param idCategoria Id de la Categoría o null si es para el index.
	 * @param limit Límite de registros a mostrar
	 * @return Lista de artículos más vendidos.
	 */
	
	List<Articulo> findMasVendidos(ClienteWrapper clienteWrapper, Long idCategoria, Integer limit);

	/**
	 * Devuelve los artículos más visitados de la web si la Categoría es null o si no de la indicada.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param idCategoria Id de la Categoría o null si es para el index.
	 * @param limit Límite de registros a mostrar
	 * @return Lista de Ártículos más vendidos.
	 */
	List<Articulo> findMasVistos(ClienteWrapper clienteWrapper, Long idCategoria, Integer limit);

	/**
	 * Devuelve los Artículos vendidos y relacionados.
	 * @param clienteWrapper ClienteWrapper.
	 * @param idArticulo Id del Artículo.
	 * @param numeroVentasRelacionadas Número de Artículos a mostrar.
	 * @return Listado de ventas relacionadas.
	 */
	List<Articulo> findVentasRelacionadas(ClienteWrapper clienteWrapper, Long idArticulo, Integer limit);
	
	/**
	 * Busca Artículos por su nombre.
	 * 
	 * @param clienteWrapper ClienteWrapper.
	 * @param texto Nombre del Artículo a buscar.
	 * @return Lista de Artículos encontrados.
	 */
	List<Articulo> findByNombre(ClienteWrapper clienteWrapper, String texto);

	/**
	 * Devuelve todos los Artículos activas para el sitemap.
	 * @return Listado de Artículos.
	 */
	List<Articulo> findAllSiteMap();

	/**
	 * Devuelve un Articulo a partir de su URL amigable.
	 * @param clienteWrapper ClienteWrapper.
	 * @param url URL.
	 * @return Devuelve el artículo.
	 */
	Articulo findByURL(ClienteWrapper clienteWrapper, String url);
}
