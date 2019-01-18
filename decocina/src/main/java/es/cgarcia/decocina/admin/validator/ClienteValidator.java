package es.cgarcia.decocina.admin.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.admin.form.ClienteForm;
import es.cgarcia.decocina.admin.util.ValidacionUtils;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Direccion;
import es.cgarcia.decocina.web.model.Zona;

/**
 * Valida el formulario de un Cliente.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class ClienteValidator implements Validator {
	
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

    	ClienteForm clienteForm = (ClienteForm) target;
    
    	Cliente cliente = clienteForm.getCliente();
    	
    	Direccion direccion = cliente.getDireccion();
    	
    	Zona zona = direccion.getZona();
    	
    	Long idZona =zona.getId();
    	
    	if(ValidacionUtils.esNull(idZona)){
    		
    		errors.rejectValue("cliente.direccion.zona", "campo.obligatorio");
    	}
    		
    }
}
