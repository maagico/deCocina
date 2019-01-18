/**
 * 
 */
package es.cgarcia.decocina.admin.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import es.cgarcia.decocina.admin.exception.DeCocinaAdminRuntimeException;
import es.cgarcia.decocina.admin.service.IIEStockAdminService;

/**
 * Controller para la importatción/exportación del Stock.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Controller
public class IEStockController extends AdminControllerBase{

	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(IEStockController.class);    
	
	/**
	 * Texto de la Miga de pan.
	 */
	private String textoMiga = "Exportar/Importar Stock";
	
	/**
	 * Servicio para las Herramientas.
	 */
	@Autowired
	private IIEStockAdminService ieStockAdminService;
	
	/**
	 * Redirige a la página de Control de Stock.
	 *  
	 * @return Redirige a la página de Control de Stock.
	 */
	@RequestMapping(value = "/admin/consola/herramienta/ie-stock-find", method = RequestMethod.GET)
	public String find(Model model){
		
		mostrarMiga(textoMiga, model);
		
		return "admin/consola/ie-stock/ver-ie-stock";
	}
	
	/**
	 * Exporta el stock y devuelve un excel.
	 * @param request HttpServletRequest.
	 * @param response HttpServletResponse.
	 * @param model Model.
	 * @return Exporta el stock y devuelve un excel.
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/consola/herramienta/exportar-stock", method = RequestMethod.POST)
	public byte[] exportarStock(HttpServletRequest request, 
								HttpServletResponse response, 
								Model model){
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=stock.xls");
		
		String rutaImagenes = request.getSession().getServletContext().getRealPath("/WEB-INF/img/articulos/");
		
		ByteArrayOutputStream baos = (ByteArrayOutputStream)ieStockAdminService.exportarStock(rutaImagenes);
		return baos.toByteArray();
	}
	
	@RequestMapping(value = "/admin/consola/herramienta/importar-stock", method = RequestMethod.POST)
	public String importarStock(@RequestParam("mfImagen") MultipartFile mfImagen,
						 		HttpServletRequest request,
						 		Model model ) throws IOException{
		
		byte[] bFichero = mfImagen.getBytes();
		
		try{
			
			ieStockAdminService.importar(bFichero);
		
			model.addAttribute("mensaje", "El excel se ha importado correctamente");
			
		}catch(DeCocinaAdminRuntimeException e){
			
			logger.error("Se ha producido un error importando el excel", e);
			model.addAttribute("mensajeError", "Se ha producido un error importando el excel");
			
		}
		
		return "admin/consola/ie-stock/ver-ie-stock";
	}
	
}
