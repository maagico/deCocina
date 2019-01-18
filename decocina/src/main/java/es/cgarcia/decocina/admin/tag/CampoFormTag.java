package es.cgarcia.decocina.admin.tag;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import es.cgarcia.decocina.admin.annotation.CampoForm;
import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.BuscadorBase;
import es.cgarcia.decocina.admin.form.FormBase;
import es.cgarcia.decocina.web.model.ModelBase;

/**
 * Tag que recupera el formulario recibido por parámetro y busca 
 * las anotaciones @CampoForm y @ClaseForm para añadir los campos.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class CampoFormTag extends SimpleTagSupport{

	/**
	 * Formulario del que se buscarán las anotaciones.
	 */
	private FormBase formulario = null; 
	
	/**
	 * Constructor.
	 */
	public CampoFormTag(){	
	}
	
	/**
	 * Devuelve el valor del atributo formulario.
	 *
	 * @return formulario
	 */
	public FormBase getFormulario() {
		return formulario;
	}

	/**
	 * Fija el valor del atributo formulario.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setFormulario(FormBase formulario) {
		this.formulario = formulario;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void doTag() throws JspException, IOException {
	    
		JspWriter out = getJspContext().getOut();
	    
		Class<?> clazz = formulario.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		try {
			
			//Buscamos en la superClase de FormBase.
			Class<?> classFormBase = clazz.getSuperclass();
			Field[] fieldsFormBase = classFormBase.getDeclaredFields();
			
			buscarAnotaciones(out, "", formulario, classFormBase, fieldsFormBase);
			buscarAnotaciones(out, "", formulario, clazz, fields);
		
		} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Busca las anotaciones y pinta el input.
	 * 
	 * @param out Writer.
	 * @param nombre Nombre del atributo que contiene la anotación.
	 * @param object Formulario.
	 * @param clazz Clase del formulario.
	 * @param fields Campos de la clase del formulario.
	 * @throws IllegalArgumentException En caso de error.
	 * @throws IllegalAccessException En caso de error.
	 * @throws IOException En caso de error.
	 */
	private void buscarAnotaciones(JspWriter out, String nombre, Object object, Class<?> clazz, Field[] fields) throws IllegalArgumentException, IllegalAccessException, IOException{
		
		for (Field field : fields) {
			
			field.setAccessible(true);
			
			boolean esAnotacionClaseForm = field.isAnnotationPresent(ClaseForm.class);
			boolean esAnotacionCampoForm = field.isAnnotationPresent(CampoForm.class);
			
			if(esAnotacionClaseForm){
				
				Object o = field.get(object);
				Class<?> c = o.getClass();
				Field[] fs = c.getDeclaredFields();
				
				String nam = field.getName();
				
				// Sólo se busca en la clase y su superclase.
				if(c.getSuperclass() == BuscadorBase.class || c.getSuperclass() == ModelBase.class){
					
					Class<?> csc = c.getSuperclass();
					Field[] fssc = csc.getDeclaredFields();
					
					buscarAnotaciones(out, nam, o, csc, fssc);
					
				}
				
				StringBuffer sbNombre = new StringBuffer();
				
				
				if(!"".equals(nombre)){
					
					sbNombre.append(nombre);
					sbNombre.append('.');
					sbNombre.append(nam);
					
				}else{
					
					sbNombre.append(nam);
				}
				
				buscarAnotaciones(out, sbNombre.toString(), o, c, fs);
			} 
			
			if(esAnotacionCampoForm){
				
				Object oValor = field.get(object);
				String valor = "";
				
				if(oValor != null){
					
					valor = oValor.toString();
				}
				
				pintarInput(out, nombre, field.getName(), valor);
			}
		}
	}
	
	/**
	 * Pinta el input.
	 * @param out Writer para pintar el tag.
	 * @param nombreClase Nombre de la clase.
	 * @param nombreAtributo Nombre del atributo.
	 * @param valor Valor.
	 * @throws IOException 
	 */
	private void pintarInput(JspWriter out, String nombreClase, String nombreAtributo, String valor) throws IOException{
		
		StringBuffer sbInput = new StringBuffer();
		
		sbInput.append("<input type = \"hidden\" ");
		
		sbInput.append("id = \"");
		
		if(!"".equals(nombreClase)){
			
			sbInput.append(nombreClase);
			sbInput.append('.');
		}
		
		sbInput.append(nombreAtributo);
		sbInput.append("\" ");
		
		sbInput.append("name = \"");
		
		if(!"".equals(nombreClase)){
			
			sbInput.append(nombreClase);
			sbInput.append('.');
		}
		
		sbInput.append(nombreAtributo);
		sbInput.append("\" ");
		
		sbInput.append("value = \"");
		
		sbInput.append(valor);
		sbInput.append("\">");
		
		out.println(sbInput.toString());
	}
	
}