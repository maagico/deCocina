package es.cgarcia.decocina.admin.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.admin.dao.IArticuloAdminDao;
import es.cgarcia.decocina.admin.dao.ICestaAdminDao;
import es.cgarcia.decocina.admin.dao.IImagenAdminDao;
import es.cgarcia.decocina.admin.form.ArticuloForm;
import es.cgarcia.decocina.admin.service.IArticuloAdminService;
import es.cgarcia.decocina.admin.util.ImagenUtils;
import es.cgarcia.decocina.admin.util.URLUtils;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Imagen;

/**
 * Contiene los métodos necesarios para trabajar con un Artículo de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("articuloAdminService")
public class ArticuloAdminServiceImpl extends GenericServiceImpl<Articulo, IArticuloAdminDao> implements IArticuloAdminService{

	/**
	 * Servicio para las Imágenes.
	 */
	@Autowired
	private IImagenAdminDao imagenAdminDao;
	
	/**
	 * Servicio para la Cesta.
	 */
	@Autowired
	private ICestaAdminDao cestaAdminDao;
	
	/**
	 * Constructor.
	 * 
	 * @param articuloAdminDao Dao para los Artículos.
	 */
	@Autowired
	public ArticuloAdminServiceImpl(IArticuloAdminDao articuloAdminDao){
		
		super(articuloAdminDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findByIdCategoria(Long idCategoria) {
		
		return dao.findByIdCategoria(idCategoria);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findByNombre(String nombre) {
		
		StringBuffer sbNombre = new StringBuffer();
		
		sbNombre.append(' ');
		sbNombre.append(nombre);
		sbNombre.append(' ');
		
		String nombreEspacios = sbNombre.toString();
		String nombrePorcentaje = nombreEspacios.replaceAll(" ", "%");
		
		return dao.findByNombre(nombrePorcentaje);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Articulo> findAllUltimos(Long limit) {
		
		return dao.findAllUltimos(limit);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Articulo> findAllByBusqueda(CategoriaArticuloBuscador categoriaArticuloBuscador){
		
		String strId = categoriaArticuloBuscador.getIdCatArt();
		String nombre = categoriaArticuloBuscador.getNombre();
		
		List<Articulo> articulos = null;
		
		if(strId != null && !strId.equals("")){
			
			Long id = Long.parseLong(strId);
			
			Articulo articulo = dao.findById(id);
			
			if(articulo != null){
				
				articulos = new ArrayList<Articulo>();
				articulos.add(articulo);
			}
			
		}else{
			
			articulos = findByNombre(nombre);
		}
		
		return articulos;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void insert(Articulo articulo)
	{
		//Fecha de modificación del Artículo.
		articulo.setFechaModificacion(Calendar.getInstance());
		
		//Visitas a 0.
		articulo.setVisitas(0L);
	
		//Insertamos para conseguir el Id para la URL amigable.
		dao.insert(articulo);
		
		Long id = articulo.getId();
		String nombre = articulo.getNombre();
		
		//URL amigable.
		String urlAmigable = URLUtils.crearUrlAmigableProducto(id, nombre);
		articulo.setUrlAmigable(urlAmigable);
		
		// Modificamos el Artículo con la URL amigable.
		dao.update(articulo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ArticuloForm update(ArticuloForm articuloForm){
		
		Articulo articulo = articuloForm.getArticulo();
		
		Long id = articulo.getId();
		String nombre = articulo.getNombre();
		
		//URL amigable.
		String urlAmigable = URLUtils.crearUrlAmigableProducto(id, nombre);
		articulo.setUrlAmigable(urlAmigable);
		
		dao.update(articulo);
		
		// Hay que volver a cargar por las Imágenes.
		Articulo articuloBD = dao.findById(id);
		
		articuloForm.setArticulo(articuloBD);
		
		return articuloForm;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(Articulo articulo, String rutaPrivada){
		
		Long id = articulo.getId();
	
		//Borramos el Artículo de las cestas que lo contengan.
		cestaAdminDao.deleteByIdArticulo(id);
		
		//Borramos todas las Imágenes del articulo en BD.
		imagenAdminDao.deleteByIdArticulo(id);
		
		//Borramos el Artículo.
		dao.delete(articulo);
		
		//Recuperamos las Imagenes para borrarlas físicamente posteriormente.
		List<Imagen> imagenes = imagenAdminDao.findByIdArticulo(id);
	
		//Borramos todas las imágenes físicamente.
		for (Imagen imagen : imagenes) {
			
			StringBuffer sbFichero = new StringBuffer();
			sbFichero.append(rutaPrivada);
			sbFichero.append(imagen.getNombre());
			
			ImagenUtils.borrarImagen(sbFichero.toString());
		}
	}
}