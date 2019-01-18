package es.cgarcia.decocina.admin.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.cgarcia.decocina.admin.dao.IArticuloAdminDao;
import es.cgarcia.decocina.admin.dao.IImagenAdminDao;
import es.cgarcia.decocina.admin.form.ImagenForm;
import es.cgarcia.decocina.admin.service.IImagenAdminService;
import es.cgarcia.decocina.admin.util.ImagenUtils;
import es.cgarcia.decocina.admin.util.URLUtils;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Imagen;
import es.cgarcia.decocina.web.util.Constantes;

/**
 * Contiene los métodos necesarios para trabajar con una Imagen de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("imagenAdminService")
public class ImagenAdminServiceImpl extends GenericServiceImpl<Imagen, IImagenAdminDao> implements IImagenAdminService{

	/**
	 * Dao de un Artículo de la parte Admin.
	 */
	@Autowired
	private IArticuloAdminDao articuloAdminDao;
	
	/**
	 * Dao de una Imagen de la parte Admin.
	 */
	@Autowired
	private IImagenAdminDao imagenAdminDao;
	
	/**
	 * Constructor.
	 * 
	 * @param iImagenAdminDao Dao para la Imagen.
	 */
	@Autowired
	public ImagenAdminServiceImpl(IImagenAdminDao iImagenAdminDao){
		
		super(iImagenAdminDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteByIdArticulo(Long id) {
		
		dao.deleteByIdArticulo(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Imagen> findByIdArticulo(Long id) {
		
		return dao.findByIdArticulo(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Imagen findPrincipalByIdArticulo(Long id, String tipo){
		
		return dao.findPrincipalByIdArticulo(id, tipo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(ImagenForm imagenForm, String rutaPublica, String rutaGrabacion) {
		
		Long idArticulo = imagenForm.getIdArticulo();
		
		Articulo articulo = articuloAdminDao.findById(idArticulo);
		
		String nombre = articulo.getNombre();
		
		//Añadimos hasta 4 dígitos aleatorios para que no se cachee. 
		int numeroCache = (int)(Math.random()*(9999 - 1) + 1); 
		
		StringBuffer sbNombre = new StringBuffer();
		sbNombre.append(nombre);
		sbNombre.append('_');
		sbNombre.append(numeroCache);
		
		nombre = sbNombre.toString();
		
		String uriAmigable = URLUtils.cambiarCaracteresImagen(nombre);
		
		MultipartFile mfImagen = imagenForm.getMfImagen();
		String mfNombre = mfImagen.getOriginalFilename();
	
		//Creamos el nombre de la imagen grande.
		StringBuffer sbFicheroG = new StringBuffer();
		sbFicheroG.append(uriAmigable);
		//La imagen con tamaño G no tiene el indicador '_g'
		//sbFicheroG.append(Constantes.TIPO_IMAGEN_FICHERO_G);
		sbFicheroG.append(ImagenUtils.getExtension(mfNombre));
		
		//Creamos la ruta pública con la imagen grande.
		StringBuffer sbUriFicheroG = new StringBuffer();
		sbUriFicheroG.append(rutaPublica);
		sbUriFicheroG.append(sbFicheroG);
		
		//Insertamos la imagen grande.
		Imagen imagenG = insertarImagen(idArticulo, nombre, Constantes.TIPO_IMAGEN_G, true, sbFicheroG.toString(), sbUriFicheroG.toString());
		imagenForm.setImagen(imagenG);
		
		//Creamos el nombre de la imagen pequeña.
		StringBuffer sbFicheroP = new StringBuffer();
		sbFicheroP.append(uriAmigable);
		sbFicheroP.append(Constantes.TIPO_IMAGEN_FICHERO_P);
		sbFicheroP.append(ImagenUtils.getExtension(mfNombre));
		
		//Creamos la ruta pública con la imagen pequeña.
		StringBuffer sbUriFicheroP = new StringBuffer();
		sbUriFicheroP.append(rutaPublica);
		sbUriFicheroP.append(sbFicheroP);
		
		//Insertamos la imagen pequeña.
		insertarImagen(idArticulo, nombre, Constantes.TIPO_IMAGEN_P, true, sbFicheroP.toString(), sbUriFicheroP.toString());
		
		//Creamos el nombre de la imagen thumbnail.
		StringBuffer sbFicheroT = new StringBuffer();
		sbFicheroT.append(uriAmigable);
		sbFicheroT.append(Constantes.TIPO_IMAGEN_FICHERO_T);
		sbFicheroT.append(ImagenUtils.getExtension(mfNombre));
		
		//Creamos la ruta pública con la imagen thumbnail.
		StringBuffer sbUriFicheroT = new StringBuffer();
		sbUriFicheroT.append(rutaPublica);
		sbUriFicheroT.append(sbFicheroT);
		
		//Insertamos la imagen thumbnail.
		insertarImagen(idArticulo, nombre, Constantes.TIPO_IMAGEN_T, true, sbFicheroT.toString(), sbUriFicheroT.toString());
		
		rutaGrabacion.concat(sbUriFicheroG.toString());
		
		//Si todo ha ido bien grabamos físicamente las imágenes.
		ImagenUtils.copiarImagen(mfImagen, rutaGrabacion.concat(File.separator).concat(sbFicheroG.toString()));
		ImagenUtils.cambiaTamYCopiaImagen(mfImagen, Constantes.ANCHO_IMAGEN_P, Constantes.ALTO_IMAGEN_P, rutaGrabacion.concat(File.separator).concat(sbFicheroP.toString()));
		ImagenUtils.cambiaTamYCopiaImagen(mfImagen, Constantes.ANCHO_IMAGEN_T, Constantes.ALTO_IMAGEN_T, rutaGrabacion.concat(File.separator).concat(sbFicheroT.toString()));	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(ImagenForm imagenForm, String rutaPublica, String rutaGrabacion) {
	
		//Primero borramos todas las Imágenes, tanto en BD como físicamente.
		delete(imagenForm, rutaGrabacion);
		
		//Insertamos las imagenes nuevas.
		insert(imagenForm, rutaPublica, rutaGrabacion);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(ImagenForm imagenForm, String rutaPrivada) {
		
		Long idArticulo = imagenForm.getIdArticulo();
		
		//Recuperamos las Imagenes para borrarlas físicamente posteriormente.
		List<Imagen> imagenes = imagenAdminDao.findByIdArticulo(idArticulo);
				
		//Borramos todas las Imágenes del articulo en BD.
		imagenAdminDao.deleteByIdArticulo(idArticulo);
	
		//Borramos todas las imágenes físicamente.
		for (Imagen imagen : imagenes) {
			
			StringBuffer sbFichero = new StringBuffer();
			sbFichero.append(rutaPrivada);
			sbFichero.append(imagen.getNombre());
			
			ImagenUtils.borrarImagen(sbFichero.toString());
		}
		
		//La visibilidad del Artículo al no tener imagen se desactiva.
		
		Articulo articulo = articuloAdminDao.findById(idArticulo);
		articulo.setActivo(false);
		
		articuloAdminDao.update(articulo);
	}
	
	/**
	 * Inserta una imagen.
	 * 
	 * @param idArticulo Id del Artículo.
	 * @param nombre Nombre de la imagen (Title y Alt).
	 * @param tipo Tipo de la imagen G, P o T.
	 * @param principal Indica si es la imagen principal del artículo.
	 * @param nombreFichero Nombre del fichero 'imagen.jpg'
	 * @param uriFichero Ruta pública del fichero.
	 * 
	 * @return Imagen insertada.
	 */
	private Imagen insertarImagen(Long idArticulo, String nombre, String tipo, boolean principal, String nombreFichero, String uriFichero){
		
		Imagen imagen = new Imagen();
		
		imagen.setIdArticulo(idArticulo);
		imagen.setAlt(nombre);
		imagen.setNombre(nombreFichero);
		imagen.setTitle(nombre);
		imagen.setTipo(tipo);
		imagen.setPrincipal(principal);
		imagen.setUri(uriFichero);
		
		imagenAdminDao.insert(imagen);
		
		return imagen;
	}
}