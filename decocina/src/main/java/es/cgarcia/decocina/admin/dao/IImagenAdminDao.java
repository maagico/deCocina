package es.cgarcia.decocina.admin.dao;

import java.util.List;

import es.cgarcia.decocina.web.dao.IGenericDao;
import es.cgarcia.decocina.web.model.Imagen;


/**
 * Interfaz del Dao para las Imagenes de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IImagenAdminDao extends IGenericDao<Imagen> {

	/**
	 * Borra todos las imágenes del Artículo.
	 * 
	 * @param id Id del Artículo.
	 */
	void deleteByIdArticulo(Long id);

	/**
	 * Devuelve todas las Imágenes de un Artículo.
	 * @param id Id del Artículo.
	 * 
	 * @return Imágenes del Artículo. 
	 */
	List<Imagen> findByIdArticulo(Long id);

	/**
	 * Devuelve la Imagen de un principal del Artículo del tipo recibido por parámetro.
	 * @param id Id del Artículo.
	 * @param id tipo Tipo de la imagen. G, P, T.
	 * 
	 * @return Imagen del Artículo. 
	 */
	Imagen findPrincipalByIdArticulo(Long id, String tipo);
}
