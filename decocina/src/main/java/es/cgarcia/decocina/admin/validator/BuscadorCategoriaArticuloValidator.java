package es.cgarcia.decocina.admin.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.admin.buscador.CategoriaArticuloBuscador;
import es.cgarcia.decocina.admin.util.ValidacionUtils;

/**
 * Valida el formulario del buscador de las Categorías y Artículos.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class BuscadorCategoriaArticuloValidator implements Validator {
	
    /**
     * {@inheritDoc}
     */
    public boolean supports(Class<?> clazz) {

        return CategoriaArticuloBuscador.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public void validate(Object target, Errors errors) {
    	
    	CategoriaArticuloBuscador categoriaArticuloBuscador = (CategoriaArticuloBuscador) target;
    	
    	String idCatArt = categoriaArticuloBuscador.getIdCatArt();
    	
    	String nombre = categoriaArticuloBuscador.getNombre();
    	
    	if(!ValidacionUtils.esNumerico(idCatArt) && "".equals(nombre)){
    		
    		errors.rejectValue("idCatArt", "campo.numerico");
    	}
    }
}
