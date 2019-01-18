package es.cgarcia.decocina.admin.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.admin.util.ValidacionUtils;
import es.cgarcia.decocina.web.form.PedidoForm;
import es.cgarcia.decocina.web.model.Cliente;
import es.cgarcia.decocina.web.model.Estado;
import es.cgarcia.decocina.web.model.Pedido;

/**
 * Valida el formulario de un Pedido.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class PedidoValidator implements Validator {
	
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

    	PedidoForm pedidoForm = (PedidoForm) target;
    
    	Pedido pedido = pedidoForm.getPedido();
    	
    	Estado estado = pedido.getEstadoAdmin();
    	
    	Long idEstado = estado.getId();
    	
    	if(ValidacionUtils.esNull(idEstado)){
    		
    		errors.rejectValue("pedido.estadoAdmin", "campo.obligatorio");
    	}
    }
}
