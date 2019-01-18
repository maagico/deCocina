package es.cgarcia.decocina.web.dao;

import java.util.List;

import es.cgarcia.decocina.web.model.Articulo;


/**
 * Interfaz del Dao para los Artículos de la parte Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IArticuloWebDao extends IGenericDao<Articulo> {

	/**
	 * Devuelve los Artículos de una Categoría.
	 * 
	 * @param idCategoria Id de la Categoría de la que dependen los Artículos.
	 * @return Artículos de la Categoría.
	 */
	List<Articulo> findByIdCategoria(Long idCategoria);
	
	/**
	 * Busca los Artículos por su nombre.
	 * @param texto Texto a buscar.
	 * @return Articulos encontrados.
	 */
	List<Articulo> findByNombre(String texto);
	
	/**
	 * Devuelve los Articulos relacionados de una Categoría.
	 * @param idArticulo Id del Artículo que no se va a mostrar.
	 * @param idCategoria Id de la Categoría.
	 * @return Artículos de la Categoría.
	 */
	List<Articulo> findRelacionadosByIdCategoria(Long idArticulo, Long idCategoria);
	
	/**
	 * Devuelve las últimas novedades añadidas.
	 * 
	 * @param limit Límite de registros a mostrar
	 * @return Lista de artículos con las últimas novedades.
	 */
	List<Articulo> findNovedades(Integer limit);
	
	/**
	 * Devuelve los Ártículos más vendidos de la web si la Categoría es null o si no de la indicada.
	 * 
	 * @param idCategoria Id de la Categoría o null si es para el index.
	 * @param limit Límite de registros a mostrar
	 * @return Lista de artículos más vendidos.
	 */
	List<Articulo> findMasVendidos(Long idCategoria, Integer limit);

	/**
	 * Devuelve los Ártículos más vistos de la web si la Categoría es null o si no de la indicada.
	 * 
	 * @param idCategoria Id de la Categoría o null si es para el index.
	 * @param limit Límite de registros a mostrar
	 * @return Lista de artículos más vendidos.
	 */
	List<Articulo> findMasVistos(Long idCategoria, Integer limit);

	/**
	 * Devuelve los Artículos vendidos y relacionados.
	 * @param idArticulo Id del Artículo.
	 * @param numeroVentasRelacionadas Número de Artículos a mostrar.
	 * @return Listado de ventas relacionadas.
	 */
	List<Articulo> findVentasRelacionadas(Long idArticulo, Integer limit);
	
	/**
	 * Actualiza la cantidad del Artículo.
	 * 
	 * @param id Id del Artículo.
	 * @param cantidad Cantidad a actualizar.
	 */
	void actualizarCantidad(Long id, Integer cantidad);

	/**
	 * Actualiza el número de Artículos vendidos.
	 * 
	 * @param id Id del Artículo.
	 * @param cantidad Número de Artículos vendidos. 
	 */
	void actualizarVenta(Long id, Long cantidad);
	
	/**
	 * Devuelve todos los Artículos activas para el sitemap.
	 * @return Listado de Artículos.
	 */
	List<Articulo> findAllSiteMap();

	/**
	 * Devuelve un Articulo a partir de su URL amigable.
	 * @param clienteWrapper CLienteWrapper.
	 * @param url URL.
	 * @return Devuelve el artículo.
	 */
	Articulo findByURL(String url);
}
