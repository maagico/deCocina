package es.cgarcia.decocina.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.web.form.PasswordForm;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.ClientePassword;
import es.cgarcia.decocina.web.service.IClienteWebService;
import es.cgarcia.decocina.web.util.MD5Utils;

/**
 * Valida el formulario de un Artículo.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component(value="passwordWebValidator")
public class PasswordValidator implements Validator {
	
	/**
	 * Servicio paral los Clientes Web.
	 */
	@Autowired
	private IClienteWebService clienteWebService;
	
    /**
     * {@inheritDoc}
     */
    public boolean supports(Class<?> clazz) {

        return ClientePassword.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void validate(Object target, Errors errors) {

    	PasswordForm passwordForm = (PasswordForm) target;
    
    	ClientePassword clientePassword = passwordForm.getClientePassword();
    	
    	Long idCliente = clientePassword.getId();
    	
    	String password = clientePassword.getPassword();
    	
    	String nuevoPassword = clientePassword.getNuevoPassword();
    	String nuevoPasswordConfirmacion = clientePassword.getNuevoPasswordConfirmacion();
    	
    	//Validamos el nuevo password.
    	if(!password.matches("[a-zA-Z0-9]{6,15}+"))
		{
    		errors.rejectValue("clientePassword.password", "campo.password.invalido");
    	}else
    	{
    		//Comprobamos que el password actual es igual al que hay en la base de datos.
    		String passwordMD5 = MD5Utils.MD5(password);
    		Cliente cliente = clienteWebService.findById(idCliente);
    		
    		String passwordMD5BD = cliente.getPassword();
    		
    		if(!passwordMD5.equals(passwordMD5BD)){
    			
    			errors.rejectValue("clientePassword.password", "campo.password.no.coincide");
    		}
    	}
    	
    	Boolean nuevoPasswordCorrecto = true;
    	
    	//Validamos el nuevo password.
    	if(!nuevoPassword.matches("[a-zA-Z0-9]{6,15}+"))
		{
    		errors.rejectValue("clientePassword.nuevoPassword", "campo.password.invalido");
    		
    		nuevoPasswordCorrecto = false;
		}
    	
    	//Validamos el nuevo password.
    	if(!nuevoPasswordConfirmacion.matches("[a-zA-Z0-9]{6,15}+"))
		{
    		errors.rejectValue("clientePassword.nuevoPasswordConfirmacion", "campo.password.invalido");
    		
    		nuevoPasswordCorrecto = false;
		}
    	
    	if(nuevoPasswordCorrecto){
    		
    		if(!nuevoPassword.equals(nuevoPasswordConfirmacion)){
    			
    			errors.rejectValue("clientePassword.nuevoPasswordConfirmacion", "campo.password.confirmacion.distinto");
    		}
    	}
    }
    
}
