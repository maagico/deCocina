package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.web.miga.MigasPan;
import es.cgarcia.decocina.web.model.Categoria;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una Categoría
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface ICategoriaAdminService extends IGenericService<Categoria>{
	
	/**
	 * Devuelve todas las Categorías a partir del id del padre o 
	 * todas las Categorías padre si el id es null.
	 * 
	 * @param idPadre Id del padre del que dependen las Categorías resultantes o 
	 * null para devolver las Categorias padre. 
	 * @return Categorías hijas.
	 */
	List<Categoria> findByIdPadre(Long idPadre);
	
	/**
	 * Devuelve todas las Categorías por el nombre recibido por parámetro.
	 * 
	 * @param nombre Nombre de las Categorías.
	 * @return Categorias filtradas por el nombre.
	 */
	List<Categoria> findByNombre(String nombre);
	
	/**
	 * Devuelve todas las Categorías filtradas por el Buscador.
	 * 
	 * @param categoriaArticuloBuscador CategoriaArticuloBuscador.
	 * @return Categorias filtradas.
	 */
	List<Categoria> findAllByBusqueda(CategoriaArticuloBuscador categoriaArticuloBuscador);
	
	/**
	 * Devuelve todas las Categorías incluidas en el path menos la raíz.
	 * 
	 * @param path Path del que se recuperarán las Categorías.
	 * @return Categorias del path.
	 */
	MigasPan findByPath(String path);
	
	/**
	 * Devuelve la profundidad de una Categoría.
	 * 
	 * @param id Id de la Categoría.
	 * @return Profundidad de la Categoría.
	 */
	int getProfundidad(Long id);
	
	/**
	 * Borra la Categoría recibida por parámetro si no tiene dependencias.
	 * 
	 * @param id Id de la Categoría a borrar. 
	 */
	void delete(Long id);
}
