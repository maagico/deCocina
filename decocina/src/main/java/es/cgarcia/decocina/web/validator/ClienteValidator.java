package es.cgarcia.decocina.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.admin.util.ValidacionUtils;
import es.cgarcia.decocina.web.form.ClienteForm;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Direccion;
import es.cgarcia.decocina.web.model.Zona;
import es.cgarcia.decocina.web.service.IClienteWebService;

/**
 * Valida el formulario de un Artículo.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component(value="clienteWebValidator")
public class ClienteValidator implements Validator {
	
	/**
	 * Servicio paral los Clientes Web.
	 */
	@Autowired
	private IClienteWebService clienteWebService;
	
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
    
    	Boolean enPanelControl = clienteForm.getEnPanelControl();
    	
    	Cliente cliente = clienteForm.getCliente();
    		
		String email = cliente.getEmail();
		
		if("".equals(email)){
			
			errors.rejectValue("cliente.email", "campo.obligatorio");
			
		}else
		if(email.length() > 100){
			
			errors.rejectValue("cliente.email", "campo.texto.max", new Object[]{"100"},"");
			
		}else
		if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
		{
			errors.rejectValue("cliente.email", "campo.email.invalido");
			
		}else
		{
			
			//Comprobamos que el email no está en al Base de datos.
			Cliente clienteBD = clienteWebService.findByEmail(email);
			
			if(clienteBD != null){
				errors.rejectValue("cliente.email", "campo.email.existe");
			}
				
		}
		
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
		boolean hayErrorPassword = errors.hasFieldErrors("cliente.password");
	
    	if(!hayErrorPassword){
    		
    		//Validamos el password.
	    	String password = cliente.getPassword();
	    	if(!password.matches("[a-zA-Z0-9]{6,15}+"))
			{
	    		errors.rejectValue("cliente.password", "campo.password.invalido");
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
    	
    	if(!enPanelControl){
    		
    		Boolean esPolitica = clienteForm.getPolitica();
    	
    		if(ValidacionUtils.esNull(esPolitica) || !esPolitica ){
    		
    			errors.rejectValue("politica", "campo.obligatorio");
    		}
    	}
    }
}
