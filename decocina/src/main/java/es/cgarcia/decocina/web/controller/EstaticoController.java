package es.cgarcia.decocina.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.cgarcia.decocina.web.miga.MigaPan;
import es.cgarcia.decocina.web.miga.MigasPan;

/**
 * Controller del contenido estático.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class EstaticoController extends WebControllerBase{

	/**
	 * Redirige a la página de ¿Quiénes somos?.
	 *
	 * @model Model.
	 * @return Redirige a la página de ¿Quiénes somos?.
	 */
	@RequestMapping(value = "/quienes-somos", method = RequestMethod.GET)
	public String quienesSomos(Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan qsMigaPan = new MigaPan();
		qsMigaPan.setTexto("¿Quiénes somos?");
		
		migasPan.addMiga(qsMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		return "web/estatico/quienes-somos";
	}
	
	/**
	 * Redirige a la página de ¿Dónde estamos?.
	 *
	 * @model Model.
	 * @return Redirige a la página de ¿Dónde estamos?.
	 */
	@RequestMapping(value = "/donde-estamos", method = RequestMethod.GET)
	public String dondeEstamos(Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan deMigaPan = new MigaPan();
		deMigaPan.setTexto("¿Dónde estamos?");
		
		migasPan.addMiga(deMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		return "web/estatico/donde-estamos";
	}
	
	/**
	 * Redirige a la página de protección de datos.
	 *
	 * @model Model.
	 * @return Redirige a la página de protección de datos.
	 */
	@RequestMapping(value = "/proteccion-datos", method = RequestMethod.GET)
	public String proteccionDatos(Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan pdMigaPan = new MigaPan();
		pdMigaPan.setTexto("Protección de datos");
		
		migasPan.addMiga(pdMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		return "web/estatico/proteccion-datos";
	}
	
	/**
	 * Redirige a la página de envios y devoluciones.
	 *
	 * @model Model.
	 * @return Redirige a la página de envíos y devoluciones.
	 */
	@RequestMapping(value = "/envios-devoluciones", method = RequestMethod.GET)
	public String seguridadPagos(Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan edMigaPan = new MigaPan();
		edMigaPan.setTexto("Envíos y devoluciones");
		
		migasPan.addMiga(edMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		return "web/estatico/envios-devoluciones";
	}
	
	/**
	 * Redirige a la página de las condiciones de uso.
	 *
	 * @model Model.
	 * @return Redirige a la página de las condiciones de uso.
	 */
	@RequestMapping(value = "/condiciones-uso", method = RequestMethod.GET)
	public String condicionesUso(Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan cuMigaPan = new MigaPan();
		cuMigaPan.setTexto("Condiciones de uso");
		
		migasPan.addMiga(cuMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		return "web/estatico/condiciones-uso";
	}
	
	/**
	 * Redirige a la página de la ley de cookies.
	 *
	 * @model Model.
	 * @return Redirige a la página de la ley de cookies.
	 */
	@RequestMapping(value = "/ley-cookies", method = RequestMethod.GET)
	public String leyCookies(Model model){
		
		MigasPan migasPan = new MigasPan();
		
		MigaPan iMigaPan = new MigaPan();
		iMigaPan.setTexto("Inicio");
		iMigaPan.setEnlace("/");
		
		migasPan.addMiga(iMigaPan);
		
		MigaPan cuMigaPan = new MigaPan();
		cuMigaPan.setTexto("Ley de cookies");
		
		migasPan.addMiga(cuMigaPan);
		
		mostrarMigasPan(migasPan, model);
		
		return "web/estatico/ley-cookies";
	}
	
	/**
	 * Redirige a la página de la política de privacidad.
	 *
	 * @model Model.
	 * @return Redirige a la página de la política de privacidad.
	 */
	@RequestMapping(value = "/politica-privacidad", method = RequestMethod.GET)
	public String politicaPrivacidad(Model model){
		
		return "web/estatico/politica-privacidad";
	}
	
	/**
	 * Redirige al archivo robots.txt.
	 *
	 * @model Model.
	 * @return Redirige al archivo robots.txt.
	 */
	@RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
	public String robots(Model model){
		
		return "web/estatico/robots";
	}
}
