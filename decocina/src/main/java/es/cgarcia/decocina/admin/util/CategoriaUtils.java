package es.cgarcia.decocina.admin.util;

import java.util.ArrayList;
import java.util.List;


/**
 * Utilidades para trabajar con los ids del path.
 * 
 * @author Miguel Ángel Álvarez García
 */
public final class CategoriaUtils { 
	
	/**
	 * Constructor.
	 */
	private CategoriaUtils(){
	}
	
	/**
	 * Devuelve la profundidad del path.
	 * 
	 * @param path Path del se buscará la profundidad.
	 * @return Profundidad del path.
	 */
	public static int getProfundidad(String path){
		
		String ids[] = path.split("_");
		
		return ids.length;
	}
	
	/**
	 * Devuelve el padre de la última categoría del path.
	 * 
	 * @param path Path con los ids de las categorías.
	 * @return La categoría padre del ultimo id.
	 */
	public static Long getPadre(String path){
		
		String id = path;
		 
		String ids[] = path.split("_");
		
		if(ids.length > 1){
			
			id = ids[ids.length - 1];
		
		}else{
			
			id = ids[0];
		}
		
		Long lnId = Long.valueOf(id);
		
		return lnId;
	}
	
	/**
	 * Devuelve el último id del path, si este es -1 devuelve null.
	 * 
	 * @param path Path con los ids de las categorías.
	 * @return El último id del path, si este es -1 devuelve null.
	 */
	public static Long getId(String path){
		
		String ids[] = path.split("_");
		
		String strId = ids[ids.length - 1];
		
		Long lnId = Long.valueOf(strId);
		
		if(lnId == -1){
			
			lnId = null;
		}
		
		return lnId;
	}
	
	/**
	 * Quita el último id del path.
	 * 
	 * @param path Path con los ids de las categorías.
	 * @return Path sin el último id.
	 */
	public static String quitarId(String path){
		
		int lastIndexOf = path.lastIndexOf("_");
		
		if( lastIndexOf != -1){
			
			path = path.substring(0, lastIndexOf);
		}
		
		return path;
	}
	
	/**
	 * Convierte el path en un List de Long.
	 * @param path Path a convertir.
	 * @return Path convertido a un List de Long.
	 */
	public static List<Long> getPath(String path){
		
		String ids[] = path.split("_");
		
		List<Long> lsIds = new ArrayList<Long>();
		
		for (String id : ids) {
			
			Long lnId = Long.valueOf(id);
			
			lsIds.add(lnId);
		}
		
		return lsIds;
	}
	
	/**
	 * Recorre y crea el path hasta encontrar el id.
	 * @param id Id hasta.
	 * @param path Path.
	 * @return Recorre y crea el path hasta encontrar el id.
	 */
	public static String getPath(Long id, String path){
		
		String ids[] = path.split("_");
		
		String resultado = "";
		
		for (String idPath : ids) {
			
			Long lnIdPath = Long.parseLong(idPath);
			
			resultado += idPath;
			
			if(id.equals(lnIdPath)){
				
				return resultado;
				
			}else{
				
				resultado += "_";
			}
		}
		
		return resultado;
	}
}
