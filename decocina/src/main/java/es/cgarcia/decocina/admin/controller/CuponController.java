package es.cgarcia.decocina.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cgarcia.decocina.admin.buscador.CuponBuscador;
import es.cgarcia.decocina.admin.exception.DeCocinaAdminDIVRuntimeException;
import es.cgarcia.decocina.admin.form.CuponForm;
import es.cgarcia.decocina.admin.service.ICuponAdminService;
import es.cgarcia.decocina.admin.validator.CuponValidator;
import es.cgarcia.decocina.web.model.Cupon;


/**
 * Controller del Fabricante.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class CuponController extends AdminControllerBase{
	
	/**
	 * Texto de la miga de pan.
	 */
	private final String miga = "Cupones";
    
    /**
     * Servicio para los Cupones de la parte Admin.
     */
    @Autowired
    private ICuponAdminService cuponAdminService;
    
    /**
     * Validador.
     */
    @Autowired
    private CuponValidator cuponValidator;
    
    /**
     * Busca los Cupones por su nombre.
     * 
     * @param cuponBuscador Buscador de Cupones.
     * @return Página que muestra los Cupones encontrados.
     */
	@RequestMapping(value = "/admin/consola/cupon/cupon-search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(CuponBuscador cuponBuscador,
					   	 Model model){
		
		mostrarMiga(miga, model);
		
		cuponBuscador.setHayBusqueda(true);
		
		List<Cupon> cupones = cuponAdminService.findAllBusqueda(cuponBuscador);
		
		model.addAttribute("cupones", cupones);
		
		return "admin/consola/cupon/ver-cupones";
	}
    
	/**
	 * Listado de Fabricantes.
	 * 
	 * @param cuponBuscador Buscador de Cupones.
	 * @param model Model.
	 * @return Página que muestra el listado de Fabricantes.
	 */
	@RequestMapping(value = "/admin/consola/cupon/cupon-find", method = RequestMethod.GET)
	public String find(CuponBuscador cuponBuscador,
					   Model model){
		
		mostrarMiga(miga, model);
		
		List<Cupon> cupones = cuponAdminService.findAllBusqueda(cuponBuscador);
		
		model.addAttribute("cupones", cupones);
		
		return "admin/consola/cupon/ver-cupones";
	}
	
	/**
	 * Redirige a la página de modificación o creación de un Fabricante.
	 * 
	 * @param path Path.
	 * @param id Id del fabricante.
	 * @param model Model.
	 * @return Página que muestra la edición o creación de un Fabricante.
	 */
	@RequestMapping(value = "/admin/consola/cupon/cupon-modify", method = RequestMethod.GET)
	public String modify(CuponBuscador cuponBuscador,
						 @RequestParam(value = "id", required = false) Long id, 
						 Model model){
		
		mostrarMiga(miga, model);
		
		Cupon cupon = null;
		
		Boolean esCreacion = null;
		
		//Si no hay id es porque es una creación.
		if(id == null){
			
			esCreacion = true;
			
			cupon = new Cupon();
			
		}else{
			
			esCreacion = false;
			
			//Es una modificación del Cupón.
			cupon = cuponAdminService.findById(id);
		}
		
		CuponForm cuponForm = new CuponForm(cupon);
		
		cuponForm.setEsCreacion(esCreacion);
		
		cuponForm.setCuponBuscador(cuponBuscador);
		
		model.addAttribute("cuponForm", cuponForm);
		
		model.addAttribute("cuponBuscador", cuponBuscador);
		
		return "admin/consola/cupon/editar-cupon";
	}
	
	/**
	 * Inserta un nuevo Cupón.
	 * 
	 * @param model Model.
	 * @param cuponForm Wrapper del Cupón con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Cupón.
	 */
	@RequestMapping(value = "/admin/consola/cupon/cupon-insert", method = RequestMethod.POST)
	public String insert(@Valid CuponForm cuponForm, 
						 BindingResult result,
						 HttpServletRequest request,
						 Model model ){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/cupon/editar-cupon";
		
		Cupon cupon = cuponForm.getCupon();
		
		CuponBuscador cuponBuscador = cuponForm.getCuponBuscador();
		
		model.addAttribute("cuponBuscador", cuponBuscador);
		
		BindException bindException = new BindException(result);
        cuponValidator.validate(cuponForm, bindException);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		cuponAdminService.insert(cupon);
		
		cuponForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Cupón creado correctamente");
		
		return vista;
	}
	
	/**
	 * Modifica el Cupón.
	 * 
	 * @param model Model.
	 * @param cuponForm Wrapper del Cupón con los campos a insertar.
	 * @param result BindingResult.
	 * @param redirectAttributes RedirectAttributes.
	 * @param request HttpServletRequest.
	 * @return Redirige a la página de edición de un Fabricante.
	 */
	@RequestMapping(value = "/admin/consola/cupon/cupon-update", method = RequestMethod.POST)
	public String update(@Valid CuponForm cuponForm,
					 	 BindingResult result, 
						 HttpServletRequest request,
						 Model model){
		
		mostrarMiga(miga, model);
		
		String vista = "admin/consola/cupon/editar-cupon";

		Cupon cupon = cuponForm.getCupon();
		
		CuponBuscador cuponBuscador = cuponForm.getCuponBuscador();
		
		model.addAttribute("cuponBuscador", cuponBuscador);
		
		BindException bindException = new BindException(result);
        cuponValidator.validate(cuponForm, bindException);
		
		if(result.hasErrors()){
			
			return vista;
		}
		
		cuponAdminService.update(cupon);
		
		cuponForm.setEsCreacion(false);
		
		model.addAttribute("mensaje", "Cupón modificado correctamente");
		
		return vista;
	}
	
	/**
	 * Elimina el cupón.
	 * 
	 * @param fabricanteForm Formulario.
	 * @param result Result.
	 * @param request Request.
	 * @param redirectAttributes RedirectAttributes.
	 * @param model Model.
	 * @return Redirect a la página del listado de Fabricantes.
	 */
	@RequestMapping(value = "/admin/consola/cupon/cupon-delete", method = RequestMethod.POST)
	public String delete(@Valid CuponForm cuponForm,
						 BindingResult result, 
						 HttpServletRequest request,
						 RedirectAttributes redirectAttributes,
						 Model model){
		
		mostrarMiga(miga, model);
		
		Cupon cupon = cuponForm.getCupon();
		
		Long id = cupon.getId();
		
		try{

			redirectAttributes.addAttribute("id", id);
			
			CuponBuscador cuponBuscador = cuponForm.getCuponBuscador();
			
			redirectAttributes.addFlashAttribute("cuponBuscador", cuponBuscador);
			
			cuponAdminService.delete(cupon);
		
			redirectAttributes.addFlashAttribute("mensaje", "Cupón eliminado correctamente");
			
			
			String vista =  "redirect:/admin/consola/cupon/cupon-find";
			
			if(cuponBuscador.getHayBusqueda()){
				
				vista = "redirect:/admin/consola/cupon/cupon-search";
			}
			
			return vista;
			
		}catch(DeCocinaAdminDIVRuntimeException e){
			
			String mensaje = e.getMessage();
			
			model.addAttribute("mensajeError", mensaje);	
			
			cuponForm.setEsCreacion(false);
		}
		
		model.addAttribute("id", id);
		
		CuponBuscador cuponBuscador = cuponForm.getCuponBuscador();
		
		model.addAttribute("cuponBuscador", cuponBuscador);
		
		return "admin/consola/cupon/editar-cupon";
	}
}
