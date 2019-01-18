package es.cgarcia.decocina.admin.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import es.cgarcia.decocina.admin.form.ImagenForm;
import es.cgarcia.decocina.admin.util.ValidacionUtils;
import es.cgarcia.decocina.web.model.Articulo;

/**
 * Valida el formulario de una Imagen.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class ImagenValidator implements Validator {

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

    	ImagenForm imagenForm = (ImagenForm) target;
    	
		MultipartFile imagen = imagenForm.getMfImagen();
    	
    	if(!ValidacionUtils.esNull(imagen)){
    	
    		String nombreImagen = imagen.getOriginalFilename();
    	
    		if(ValidacionUtils.esVacio(nombreImagen)){
    			
    			errors.rejectValue("mfImagen", "campo.obligatorio");
    		}
    	}
    	    		
    }
}
