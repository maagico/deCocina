package es.cgarcia.decocina.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indica que el atributo es parte de un formulario.
 * 
 * @author @author Miguel Ángel Álvarez García
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface CampoForm  {

}
