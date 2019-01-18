/**
 * 
 */
package es.cgarcia.decocina.web.session;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.cgarcia.decocina.web.menu.CategoriaMenu;
import es.cgarcia.decocina.web.service.ICategoriaWebService;

/**
 * 
 * Contiene el Menú de la Aplicación Web.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class MenuSession {
 
	/**
	 * Menú de la aplicación web. Categorias, subcategorías.
	 */
	private List<CategoriaMenu> categoriasMenu;
	
	/**
	 * Servicio para las Categorías.
	 */
	@Autowired
	private ICategoriaWebService categoriaWebService;
	
	/**
	 * Carga en memoria el Menú.
	 */
	@PostConstruct
	public void init(){
		
		categoriasMenu = categoriaWebService.crearCategoriasMenu();
	}

	/**
	 * Devuelve el valor del atributo categoriasMenu.
	 *
	 * @return categoriasMenu
	 */
	public List<CategoriaMenu> getCategoriasMenu() {
		return categoriasMenu;
	}

	/**
	 * Fija el valor del atributo categoriasMenu.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setCategoriasMenu(List<CategoriaMenu> categoriasMenu) {
		this.categoriasMenu = categoriasMenu;
	}
}
