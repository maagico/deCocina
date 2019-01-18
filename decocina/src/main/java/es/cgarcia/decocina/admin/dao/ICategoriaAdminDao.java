package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Categoria;


/**
 * Interfaz del Dao para las Categorías de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface ICategoriaAdminDao extends IGenericDao<Categoria> {

	/**
	 * Devuelve todas las Categorías a partir del id del padre o 
	 * todas las categorías padre si el id es null.
	 * 
	 * @param idPadre Id del padre del que dependen las Categorías resultantes o 
	 * null para devolver las categorias padre. 
	 * @return Categorías encontradas.
	 */
	List<Categoria> findByIdPadre(Long idPadre);

	/**
	 * Devuelve todas las Categorias por el nombre recibido por parámetro.
	 * 
	 * @param nombre Nombre de las Categorias.
	 * @return Categorias filtradas por el nombre.
	 */
	List<Categoria> findByNombre(String nombre);

	/**
	 * Cambia el estado de las Categorías hijas. 
	 * 
	 * @param id Id de la Categoría padre.
	 * @param activa Estado al que se cambiarán las Cátegorias hijas.
	 * @return Número de CAtegorías modificadas.
	 */
	Integer cambiarEstadoActivaByIdPadre(Long id, Boolean activa);
}
