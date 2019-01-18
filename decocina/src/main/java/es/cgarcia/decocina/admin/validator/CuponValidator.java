package es.cgarcia.decocina.admin.validator;

import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.admin.form.CuponForm;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Cupon;
import es.cgarcia.decocina.web.util.Constantes;

/**
 * Valida el formulario de un Cupón.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class CuponValidator implements Validator {
	
    /**
     * {@inheritDoc}
     */
    public boolean supports(Class<?> clazz) {

        return Articulo.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void validate(Object target, Errors errors) {

    	
		CuponForm cuponForm = (CuponForm)target;
		
		Cupon cupon = cuponForm.getCupon();
		
		Double descuento = cupon.getDescuento();
		Double precioMinimo = cupon.getPrecioMinimo();
		Integer tipo = cupon.getTipoImporte();
		
		Calendar fechaDesde = cupon.getFechaDesde();
		Calendar fechaHasta = cupon.getFechaHasta(); 
		
		boolean hayErrorDescuento = errors.hasFieldErrors("cupon.descuento");
		boolean hayErrorPrecioMinimo = errors.hasFieldErrors("cupon.precioMinimo");
		boolean hayErrorTipo = errors.hasFieldErrors("cupon.tipoImporte");
		
		if(!hayErrorDescuento && !hayErrorPrecioMinimo && !hayErrorTipo){
			
			if(precioMinimo != null){
				
				if(precioMinimo < descuento && tipo == Constantes.CUPON_IMPORTE_FIJO){
			
					errors.rejectValue("cupon.precioMinimo", "campo.cupon.precioMinimo.min");
				}
			}
		}
		
		boolean hayErrorFechaDesde = errors.hasFieldErrors("cupon.fechaDesde");
		boolean hayErrorFechaHasta = errors.hasFieldErrors("cupon.fechaHasta");
		
		if(!hayErrorFechaDesde && fechaDesde != null &&  !hayErrorFechaHasta && fechaHasta != null){
			
			if(fechaDesde.after(fechaHasta)){
				
				errors.rejectValue("cupon.fechaDesde", "campo.cupon.fechaDesde.after");
			}
			
			if(fechaHasta.before(fechaDesde)){
				
				errors.rejectValue("cupon.fechaHasta", "campo.cupon.fechaHasta.before");
			}
		}
    }
}
