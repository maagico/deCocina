package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.admin.form.ArticuloForm;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con un Artículo
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IArticuloAdminService extends IGenericService<Articulo>{
	
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
	 * Devuelve los últimos Artículos añadidos.
	 * 
	 * @param limit Limite de Artículos a mostrar.
	 * @return Listado de Artículos.
	 */
	List<Articulo> findAllUltimos(Long limit);
	
	/**
	 * Devuelve todas los Artículos filtrados por el Buscador.
	 * 
	 * @param categoriaArticuloBuscador CategoriaArticuloBuscador.
	 * @return Artículos filtradas.
	 */
	List<Articulo> findAllByBusqueda(CategoriaArticuloBuscador categoriaArticuloBuscador);
	
	/**
	 * Inserta el Artículo.
	 * 
	 * @param articulo Artículo.
	 */
	void insert(Articulo articulo);
	
	/**
	 * Modifica el Artículo.
	 * 
	 * @param articulo Artículo.
	 */
	Articulo update(Articulo articulo);
	
	/**
	 * Borra el Artículo y sus imagenes.
	 * 
	 * @param articulo Artículo aborrar.
	 * @param rutaPrivada Ruta a las Imágenes.
	 */
	void delete(Articulo articulo, String rutaPrivada);

	/**
	 * Modifica el Artículo.
	 * 
	 * @param articuloForm ArticuloForm.
	 * @return ArticuloForm.
	 */
	ArticuloForm update(ArticuloForm articuloForm);
}
