package es.cgarcia.decocina.web.service;

import java.util.List;

import es.cgarcia.decocina.admin.paginacion.Paginacion;
import es.cgarcia.decocina.web.model.ModelBase;

/**
 * Interface que deben implementar todos los servicios. 
 * Contiene los metodo necesarios para trabajar con un Dao.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IGenericService<T extends ModelBase>{
	
	/**
	 * Devuelve todos las entidades.
	 * 
	 * @param start Inicio.
	 * @param limit Límite.
	 * @return Todas las entidades filtradas por los parámetros.
	 */
	List<T> findAll(Long start, Long limit);
	
	/**
	 * Devuelve el numero de entidades.
	 * 
	 * @param start Inicio.
	 * @param limit Límite.
	 * @return Número de resultados filtrados por los parámetros.
	 */
	Long countFindAll(Long start, Long limit);
	
	/**
	 * Devuelve todos los entidades.
	 * 
	 * @param paginacion Contiene los parámetros de paginación.
	 * @return Todos los resultados filtrados por los parámetros de paginación.
	 */
	List<T> findAll(Paginacion paginacion);
	
	/**
	 * Devuelve el número de entidades.
	 * 
	 * @param paginacion Contiene los parámetros de paginación.
	 * @return Número de resultados.
	 */
	Long countFindAll(Paginacion paginacion);
	
	/**
	 * Devuelve todas las entidades.
	 * 
	 * @return Devuelve todas las entidades.
	 */
	List<T> findAll();
	
	/**
	 * Devuelve el número de entidades.
	 * 
	 * @return Número de resultados.
	 */
	Long countFindAll();
	
	/**
	 * Devuelve la entidad por su id. 
	 * 
	 * @param id Id de la entidad.
	 * @return Devuelve la entidad.
	 */
	T findById(Long id); 
	
	/**
	 * Inserta una entidad.
	 * 
	 * @param entity Entidad a insertar.
	 */
	void insert(T entity);
	
	/**
	 * Modifica la entidad.
	 * 
	 * @param entity Entidad a modificar.
	 */
	T update(T entity); 
	
	/**
	 * Borra la entidad.
	 * 
	 * @param entity Entidad a borrar.
	 */
	void delete(T entity);
}
