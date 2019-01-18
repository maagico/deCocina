package es.cgarcia.decocina.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.web.form.ClienteCambiarPasswordForm;
import es.cgarcia.decocina.web.model.ClienteCambiarPassword;

/**
 * Valida el formulario del cambio de password.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component(value="cambiarPasswordWebValidator")
public class ClienteCambiarPasswordValidator implements Validator {
	
    /**
     * {@inheritDoc}
     */
    public boolean supports(Class<?> clazz) {

        return ClienteCambiarPasswordValidator.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void validate(Object target, Errors errors) {

	    	ClienteCambiarPasswordForm clienteCambiarPasswordForm = (ClienteCambiarPasswordForm) target;
	    
	    	ClienteCambiarPassword clientePassword = clienteCambiarPasswordForm.getClienteCambiarPassword();
	    	
	    	String nuevoPassword = clientePassword.getNuevoPassword();
	    	String nuevoPasswordConfirmacion = clientePassword.getNuevoPasswordConfirmacion();
	    	
	    	Boolean nuevoPasswordCorrecto = true;
	    	
	    	//Validamos el nuevo password.
	    	if(!nuevoPassword.matches("[a-zA-Z0-9]{6,15}+"))
			{
	    		errors.rejectValue("clienteCambiarPassword.nuevoPassword", "campo.password.invalido");
	    		
	    		nuevoPasswordCorrecto = false;
			}
	    	
	    	//Validamos la confirmación del nuevo password.
	    	if(!nuevoPasswordConfirmacion.matches("[a-zA-Z0-9]{6,15}+"))
			{
	    		errors.rejectValue("clienteCambiarPassword.nuevoPasswordConfirmacion", "campo.password.invalido");
	    		
	    		nuevoPasswordCorrecto = false;
			}
	    	
	    	if(nuevoPasswordCorrecto){
	    		
	    		if(!nuevoPassword.equals(nuevoPasswordConfirmacion)){
	    			
	    			errors.rejectValue("clienteCambiarPassword.nuevoPasswordConfirmacion", "campo.password.confirmacion.distinto");
	    		}
	    	}
    }
    
}
