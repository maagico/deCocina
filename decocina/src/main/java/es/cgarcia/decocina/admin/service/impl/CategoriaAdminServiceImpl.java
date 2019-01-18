package es.cgarcia.decocina.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.admin.dao.ICategoriaAdminDao;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.service.ICategoriaAdminService;
import es.cgarcia.decocina.admin.util.CategoriaUtils;
import es.cgarcia.decocina.admin.util.URLUtils;
import es.cgarcia.decocina.web.miga.MigaPan;
import es.cgarcia.decocina.web.miga.MigasPan;
import es.cgarcia.decocina.web.model.Categoria;

/**
 * Contiene los métodos necesarios para trabajar con un Administrador de la parte Admin. 
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Service("categoriaAdminService")
public class CategoriaAdminServiceImpl extends GenericServiceImpl<Categoria, ICategoriaAdminDao> implements ICategoriaAdminService{
	
	/**
	 * Constructor.
	 * 
	 * @param categoriaAdminDao Dao para las categorías.
	 */
	@Autowired
	public CategoriaAdminServiceImpl(ICategoriaAdminDao categoriaAdminDao){
		
		super(categoriaAdminDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Categoria> findByIdPadre(Long idPadre) {
		
		return dao.findByIdPadre(idPadre);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MigasPan findByPath(String path) {
		
		MigasPan migasPan = new MigasPan();
		
		List<Long> lnIds = CategoriaUtils.getPath(path);
		
		for (Long id : lnIds) {
			
			if(id.equals(-1L)){
				
				MigaPan migaPan = new MigaPan();
				
				migaPan.setTexto("Categorías y Artículos");
				migaPan.setEnlace("-1");

				migasPan.addMiga(migaPan);
				
			}else{
				
				
				Categoria categoria = dao.findById(id);
				
				String nombre = categoria.getNombre();
				
				MigaPan migaPan = new MigaPan();
				migaPan.setTexto(nombre);

				String resultadoPath = CategoriaUtils.getPath(id, path);
				
				migaPan.setEnlace(resultadoPath);
				
				migasPan.addMiga(migaPan);
			}
		}
		
		return migasPan;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Categoria> findByNombre(String nombre) {
		
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
	public List<Categoria> findAllByBusqueda(CategoriaArticuloBuscador categoriaArticuloBuscador){
		
		String strId = categoriaArticuloBuscador.getIdCatArt();
		String nombre = categoriaArticuloBuscador.getNombre();
		
		List<Categoria> categorias = null;
		
		if(strId != null && !strId.equals("")){
			
			Long id = Long.parseLong(strId);
			
			Categoria categoria = dao.findById(id);
			
			if(categoria != null){
			
				categorias = new ArrayList<Categoria>();
				categorias.add(categoria);
			}
			
		}else{
			
			categorias = findByNombre(nombre);
		}
		
		return categorias;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getProfundidad(Long id) {
		
		int profundidad = 1;
		
		if(id != null && id != -1){
			
			Categoria categoria = dao.findById(id);
			
			Long idPadre = categoria.getIdPadre();
			
			if(idPadre == null)
			{
				profundidad = 2;
			}else{
				
				profundidad = 3;
			}
		}
		
		return profundidad;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(Categoria categoria){
		
		Long idPadre = categoria.getIdPadre();
		
		if(idPadre == -1){
			
			idPadre = null;
		}
		
		categoria.setIdPadre(idPadre);
		
		categoria.setUrlAmigable("");
		
		dao.insert(categoria);
		
		Long id = categoria.getId();
		String nombre = categoria.getNombre();
		
		String urlAmigable = URLUtils.crearUrlAmigableCategoria(id, nombre);
		
		categoria.setUrlAmigable(urlAmigable);
		
		dao.update(categoria);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Categoria update(Categoria categoria){
		
		Long id = categoria.getId();
		String nombre = categoria.getNombre();
		
		String urlAmigable = URLUtils.crearUrlAmigableCategoria(id, nombre);
		
		categoria.setUrlAmigable(urlAmigable);
		
		return dao.update(categoria);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {
		
		try{
			
			Categoria categoria = dao.findById(id);
		
			dao.delete(categoria);
			
		}catch(DataIntegrityViolationException e){
			
			throw new DeCocinaAdminDIVRuntimeException("La categoría tiene dependencias, no se puede borrar", e);
		}
	}
}