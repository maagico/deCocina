package es.cgarcia.decocina.admin.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.admin.model.Administrador;

/**
 * Valida el formulario del login de administración.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class AdministradorValidator implements Validator {

    /**
     * {@inheritDoc}
     */
    public boolean supports(Class<?> clazz) {

        return Administrador.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void validate(Object target, Errors errors) {

    	Administrador administrador = (Administrador) target;
    	
    	String usuario = administrador.getUsuario();
    	String password = administrador.getPassword();
    	
    	//El usuario no puede tener más de 15 caracteres.
    	if(usuario.length() > 15)
    	{
    		errors.rejectValue("usuario", "usuario.numero.max.caracteres");
    	}
    	
    	//Comprobamos que no existe un error previo. Si lo hay no hacemos la validación.
    	boolean hayErrorPassword = errors.hasFieldErrors("password");
    	
    	//El password debe ser mayor de 8 caracteres y menor de 15
    	if(!hayErrorPassword && (password.length() < 8 || password.length() > 15))
    	{
    		errors.rejectValue("password", "password.numero.max.caracteres");
    	}
    	
        // boolean hayErrorEdad = errors.hasFieldErrors("edad");
        
    }
}
