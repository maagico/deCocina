package es.cgarcia.decocina.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.service.impl.GenericServiceImpl;
import es.cgarcia.decocina.web.dao.ICategoriaWebDao;
import es.cgarcia.decocina.web.menu.CategoriaMenu;
import es.cgarcia.decocina.web.menu.SubcategoriaMenu;
import es.cgarcia.decocina.web.model.Categoria;
import es.cgarcia.decocina.web.service.ICategoriaWebService;

/**
 * Contiene los métodos necesarios para trabajar con un Administrador de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("categoriaWebService")
public class CategoriaWebServiceImpl extends GenericServiceImpl<Categoria, ICategoriaWebDao> implements ICategoriaWebService{

	/**
	 * Constructor.
	 * @param dao CategoriaWebDao.
	 */
	@Autowired
	public CategoriaWebServiceImpl(ICategoriaWebDao categoriaWebDao) {
		super(categoriaWebDao);
	}

	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public List<CategoriaMenu> crearCategoriasMenu() {
		
		List<CategoriaMenu> categoriasMenu = new ArrayList<CategoriaMenu>();
		
		List<Categoria> categorias = dao.findByIdPadre(null);
		
		for (Categoria categoria : categorias) {
			
			Long id = categoria.getId();
			Boolean activa = categoria.getActiva();
			String nombre = categoria.getNombre();
			String urlAmigable = categoria.getUrlAmigable();
			
			CategoriaMenu categoriaMenu = new CategoriaMenu();
			
			categoriaMenu.setId(id);
			categoriaMenu.setNombre(nombre);
			categoriaMenu.setActiva(activa);
			categoriaMenu.setUrlAmigable(urlAmigable);
			
			//Añadimos la categoría al List
			categoriasMenu.add(categoriaMenu);
			
			List<SubcategoriaMenu> subCategoriasMenu = categoriaMenu.getSubCategorias();
			
			//Recuperamos las subcategorias.
			List<Categoria> subCategorias = dao.findByIdPadre(id);
			
			for (Categoria subCategoria : subCategorias) {
				
				Long idSC = subCategoria.getId();
				Boolean activaSC = subCategoria.getActiva();
				String nombreSC = subCategoria.getNombre();
				String urlAmigableSC = subCategoria.getUrlAmigable();
				
				SubcategoriaMenu subcategoriaMenu = new SubcategoriaMenu();
				subcategoriaMenu.setId(idSC);
				subcategoriaMenu.setNombre(nombreSC);
				subcategoriaMenu.setActiva(activaSC);
				subcategoriaMenu.setUrlAmigable(urlAmigableSC);
				
				subCategoriasMenu.add(subcategoriaMenu);
				
			}
		}
		
		return categoriasMenu;
	}

	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public Categoria findByURL(String url) {
		
		return dao.findByURL(url);
	}
	
	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public List<Categoria> findAllSiteMap() {
		
		return dao.findAllSiteMap();
	}
}