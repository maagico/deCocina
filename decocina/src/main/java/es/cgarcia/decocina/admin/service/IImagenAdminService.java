package es.cgarcia.decocina.admin.service;

import java.util.List;

import es.cgarcia.decocina.admin.form.ImagenForm;
import es.cgarcia.decocina.web.model.Imagen;
import es.cgarcia.decocina.web.service.IGenericService;

/**
 * Interfaz que contiene los metodos necesarios para trabajar con una Imagen
 * de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public interface IImagenAdminService extends IGenericService<Imagen>{

	/**
	 * Borra las imagenes por el id del Artículo.
	 * 
	 * @param id Id del articulo.
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
		
	/**
	 * Inserta la Imagen de un Artículo.
	 * 
	 * @param imagenForm ImagenForm.
	 * @param rutaPublica Ruta pública de las Imágenes.
	 * @param rutaGrabacion Ruta de grabacion de las Imágenes.
	 */
	 void insert(ImagenForm imagenForm, String rutaPublica, String rutaGrabacion);

	 /**
	  * Modifica la Imagen de un Artículo.
	  * 
	  * @param imagenForm ImagenForm.
	  * @param rutaPublica Ruta pública de las Imágenes.
	  * @param rutaGrabacion Ruta de grabacion de las Imágenes.
	  */
	void update(ImagenForm imagenForm, String rutaPublica, String rutaGrabacion);
	
	/**
	  * Borra la Imagen de un Artículo.
	  * 
	  * @param imagenForm ImagenForm.
	  * @param rutaPrivada Ruta privada de las de las Imágenes.
	  */
	void delete(ImagenForm imagenForm, String rutaPrivada);
}
