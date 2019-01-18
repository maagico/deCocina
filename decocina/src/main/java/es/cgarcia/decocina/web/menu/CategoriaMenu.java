/**
 * 
 */
package es.cgarcia.decocina.web.menu;

import java.util.ArrayList;
import java.util.List;


/**
 * Categoría del Menú.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class CategoriaMenu extends Menu{
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -835065652370080590L;
	
	/**
	 * Subcategorías.
	 */
	private List<SubcategoriaMenu> subCategorias = new ArrayList<SubcategoriaMenu>();

	/**
	 * Devuelve el valor del atributo subCategorias.
	 *
	 * @return subCategorias
	 */
	public List<SubcategoriaMenu> getSubCategorias() {
		return subCategorias;
	}

	/**
	 * Fija el valor del atributo subCategorias.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setSubCategorias(List<SubcategoriaMenu> subCategorias) {
		this.subCategorias = subCategorias;
	}
}
