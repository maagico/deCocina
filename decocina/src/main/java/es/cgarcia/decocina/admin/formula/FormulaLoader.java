/**
 * 
 */
package es.cgarcia.decocina.admin.formula;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import es.cgarcia.decocina.web.controller.SesionController;


/**
 * Aplica la Fórmula y recupera el Gasto.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class FormulaLoader {

	/**
	 * Logger.
	 */
	private Logger logger = LoggerFactory.getLogger(SesionController.class);    
	
	/**
	 * Gasto por defecto a 8€.
	 */
	private Double gastoPorDefecto = 8D;
	
	/**
	 * Aplica la Fórmula.
	 * @param clase Representa la Clase que contiene la Fórmula.
	 * @param datosFormula Contiene lso datos necesarios para aplicar la Fórmula.
	 * @return Gasto una vez aplicada la Fórmula.
	 */
	public Double aplicarFormula(String clase, DatosFormula datosFormula){
		
		Double resultado = gastoPorDefecto;
		
		try{
			
			Class<?> clazz = Class.forName(clase);
			FormulaBase formulaBase = (FormulaBase)clazz.newInstance();
			
			resultado = formulaBase.getGasto(datosFormula);
			
		}catch(Exception e){
			
			logger.error("Se ha producido un error calculando el Gasto", e);
		}
		
		return resultado;
	}
}
