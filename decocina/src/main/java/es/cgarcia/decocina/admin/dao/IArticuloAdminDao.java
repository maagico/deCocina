package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Articulo;


/**
 * Interfaz del Dao para los Artículos de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IArticuloAdminDao extends IGenericDao<Articulo> {

	/**
	 * Devuelve los Artículos de una Categoría.
	 * 
	 * @param idCategoria Id de la Categoría de la que dependen los Artículos.
	 * @return Artículos de la Categoría.
	 */
	List<Articulo> findByIdCategoria(Long idCategoria);
	
	/**
	 * Devuelve todas los Artículos por el nombre recibido por parámetro.
	 * 
	 * @param nombre Nombre de los Artículos.
	 * @return Artículos filtrados por el nombre.
	 */
	List<Articulo> findByNombre(String nombre);

	/**
	 * Devuelve los Artículos que tengan imagen para la exportación.
	 * @return Listado Artículos para la exportación.
	 */
	List<Articulo> findAllExport();
	
	/**
	 * @param id Id del Artículo en caso de modificación.
	 * @param nombre Nombre del Artículo a buscar.
	 * @return True si existe el articulo por el nombre.
	 */
	Boolean existeArticulo(Long id, String nombre);

	/**
	 * Modifica el estado de visibilidad de los Artículos de la Categoría.
	 * 
	 * @param idCategoria Id de la Categoría.
	 * @param activo Estado de visibilidad del Artículo.
	 * @return Número de Artículos modificados. 
	 */
	Integer cambiarEstadoActivaByIdCategoria(Long idCategoria, Boolean activo);

	/**
	 * Devuelve los últimos Artículos añadidos.
	 * 
	 * @param limit Limite de Artículos a mostrar.
	 * @return Listado de Artículos.
	 */
	List<Articulo> findAllUltimos(Long limit);

	/**
	 * Modifica la cantidad del Artículo y si está activo.
	 * @param id Id del Artículo.
	 * @param cantidad Cantidad.
	 * @param activo Activo.
	 * @return Numero de Artículos modificados.
	 */
	Integer updateFromImport(Integer id, Integer cantidad, Boolean activo);
}
