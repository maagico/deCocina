package es.cgarcia.decocina.admin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.cgarcia.decocina.admin.dao.IArticuloAdminDao;
import es.cgarcia.decocina.admin.form.ArticuloForm;
import es.cgarcia.decocina.admin.util.ValidacionUtils;
import es.cgarcia.decocina.web.model.Articulo;
import es.cgarcia.decocina.web.model.Impuesto;

/**
 * Valida el formulario de un Artículo.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
@Component
public class ArticuloValidator implements Validator {

	/**
	 * Dao de un Artículo de la parte Admin.
	 */
	@Autowired
	private IArticuloAdminDao articuloAdminDao;
	
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

    	ArticuloForm articuloForm = (ArticuloForm) target;
    
    	Articulo articulo = articuloForm.getArticulo();
    	
    	//Comprobamos que no está el nombre repetido en otro Artículo.
    	Long id = articulo.getId();
    	String nombre = articulo.getNombre();
    	
    	Boolean existe = articuloAdminDao.existeArticulo(id, nombre);
    	
    	if(existe){
    		errors.rejectValue("articulo.nombre", "campo.nombre.articulo.repetido");
    	}
    	
    	//Comprobamos que se ha introducido el Impuesto.
    	Impuesto impuesto = articulo.getImpuesto();
    	
    	Long idImpuesto = impuesto.getId();
    	
    	if(ValidacionUtils.esNull(idImpuesto)){
    		
    		errors.rejectValue("articulo.impuesto", "campo.obligatorio");
    	}
    		
    }
}
