/**
 * 
 */
package es.cgarcia.decocina.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import es.cgarcia.decocina.admin.service.ICategoriaAdminService;
import es.cgarcia.decocina.admin.util.URLUtils;
import es.cgarcia.decocina.web.miga.MigaPan;
import es.cgarcia.decocina.web.miga.MigasPan;

/**
 * Controller Base del que deben heredar todos los Controllers de la parte Admin.
 * 
 * @author Miguel Ángel Álvarez García
 */
public class AdminControllerBase{

	/**
     * Servicio para las Categorías de la parte Admin.
     */
    @Autowired
    private ICategoriaAdminService categoriaAdminService;
    
	/**
	 * Muestra la miga.
	 * 
	 * @param texto Texto a mostrar.
	 * @param model Model.
	 */
	protected void mostrarMiga(String texto, Model model){
		
		MigaPan miga = new MigaPan();
		miga.setTexto(texto);
		model.addAttribute("miga", miga);
	}
	
	/**
	 * Muestra la miga.
	 * 
	 * @param texto Texto a mostrar.
	 * @param enlace Enlace de la miga.
	 * @param model Model.
	 */
	protected void mostrarMiga(String texto, String enlace, Model model){
		
		MigaPan miga = new MigaPan();
		miga.setTexto(texto);
		miga.setEnlace(enlace);
		
		model.addAttribute("miga", miga);
	}
	
	/**
	 * Muestra las migas de pan y la ategoría.
	 * @param model Model.
	 * @param path Path.
	 */
	protected void mostrarMigasPan(Model model, String path) {
		
		//Recuperamos las migas de pan.
		MigasPan categoriasMigasPan = categoriaAdminService.findByPath(path);
		
		model.addAttribute("categoriasMigasPan", categoriasMigasPan.getMigasPan());
	}
	
	/**
     * Devuelve la URL completa. 
     * @param request Request.
     * @return La URL completa.
     */
    protected String getURL(HttpServletRequest request){
		
    	String url = URLUtils.getURL(request);
		
		return url;
	}
}
