package es.cgarcia.decocina.admin.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.admin.form.FormaPagoForm;
import es.cgarcia.decocina.admin.util.ValidacionUtils;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.FormaPago;

/**
 * Valida el formulario de una Forma de Pago.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class FormaPagoValidator implements Validator {
	
    /**
     * {@inheritDoc}
     */
    public boolean supports(Class<?> clazz) {

        return Cliente.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void validate(Object target, Errors errors) {

    	FormaPagoForm pedidoForm = (FormaPagoForm) target;
    
    	FormaPago formaPago = pedidoForm.getFormaPago();
    	
    	Boolean pasoAdicional = formaPago.getPasoAdicional();
    	
    	if(pasoAdicional){
    		
    		String url = formaPago.getUrl();
    		
    		if(ValidacionUtils.esVacio(url)){
    	    		
    			errors.rejectValue("formaPago.url", "campo.obligatorio");
    	    }	
    	}
    }
}
