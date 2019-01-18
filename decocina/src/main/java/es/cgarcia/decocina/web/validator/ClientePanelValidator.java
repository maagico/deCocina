package es.cgarcia.decocina.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.admin.util.ValidacionUtils;
import es.cgarcia.decocina.web.form.ClienteForm;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Direccion;
import es.cgarcia.decocina.web.model.Zona;

/**
 * Valida el formulario de un Artículo.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component(value="clientePanelWebValidator")
public class ClientePanelValidator implements Validator {
	
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
    	
    	//Comprobamos que no existe un error previo. Si lo hay no hacemos la validación.
    	boolean hayErrorTelefono = errors.hasFieldErrors("cliente.telefono");
    	
    	if(!hayErrorTelefono){
    		
    		//Validamos el teléfono.
	    	String telefono = cliente.getTelefono();
	    	if(!telefono.matches("[0-9]{8,12}+"))
			{
	    		errors.rejectValue("cliente.telefono", "campo.telefono.invalido");
			}
    	}
    	
    	//Comprobamos que no existe un error previo. Si lo hay no hacemos la validación.
    	boolean hayErrorCP = errors.hasFieldErrors("cliente.direccion.codigoPostal");
    	
    	if(!hayErrorCP){
    		Direccion direccion = cliente.getDireccion();
    		String codigoPostal = direccion.getCodigoPostal();
    		
    		
    		if(!codigoPostal.matches("(0[1-9]|5[0-2]|[0-4][0-9])[0-9]{3}$"))
			{
	    		errors.rejectValue("cliente.direccion.codigoPostal", "campo.codigo.postal.invalido");
			}
    	}
    	
    	//Validamos la Provincia.
    	Direccion direccion = cliente.getDireccion();
    	
    	Zona zona = direccion.getZona();
    	
    	Long idZona =zona.getId();
    	
    	if(ValidacionUtils.esNull(idZona)){
    		
    		errors.rejectValue("cliente.direccion.zona", "campo.obligatorio");
    	}
    }
}
