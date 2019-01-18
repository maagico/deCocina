package es.cgarcia.decocina.admin.tag;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import es.cgarcia.decocina.admin.annotation.CampoForm;
import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.BuscadorBase;

/**
 * Tag que recupera el formulario recibido por parámetro y busca 
 * las anotaciones @CampoForm para crear los parámetros de navegación.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class ParametrosTag extends SimpleTagSupport{

	/**
	 * Formulario del que se buscarán las anotaciones.
	 */
	private BuscadorBase busqueda = null; 
	
	/**
	 * Parametros que se van a excluir.
	 */
	private String excluir;
	
	/**
	 * Constructor.
	 */
	public ParametrosTag(){	
	}
	
	/**
	 * Devuelve el valor del atributo busqueda.
	 *
	 * @return busqueda
	 */
	public BuscadorBase getBusqueda() {
		return busqueda;
	}

	/**
	 * Fija el valor del atributo busqueda.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setBusqueda(BuscadorBase busqueda) {
		this.busqueda = busqueda;
	}
	
	/**
	 * Devuelve el valor del atributo excluir.
	 *
	 * @return excluir
	 */
	public String getExcluir() {
		return excluir;
	}

	/**
	 * Fija el valor del atributo excluir.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setExcluir(String excluir) {
		this.excluir = excluir;
	}

	/**
	 * {@inheritDoc}
	 */
	public void doTag() throws JspException, IOException {
	    
		JspWriter out = getJspContext().getOut();
	    
		Class<?> clazz = busqueda.getClass();
		
		try {
		
			Class<?> superClass = clazz.getSuperclass();
			Field[] fieldSuper = superClass.getDeclaredFields();
			
			String parametrosSuper = crearParametros(busqueda, null, excluir, fieldSuper, new StringBuffer());
			
			Field[] fields = clazz.getDeclaredFields();
			String parametros = crearParametros(busqueda, null, excluir, fields, new StringBuffer());
		
			pintarParametros(out, parametrosSuper, parametros);
			
		} catch (IllegalAccessException  e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Crea los parámetros.
	 * 
	 * @param object Object.
	 * * @param nombre Nombre.
	 * @param fields Campos.
	 * @return Devuelve los parámetros.
	 * @throws IllegalArgumentException En caso de error.
	 * @throws IllegalAccessException En caso de error.
	 */
	public String crearParametros(Object object, String nombre, String excluir, Field[] fields, StringBuffer sbParametros) throws IllegalAccessException{
		
		for (Field field : fields) {
			
			field.setAccessible(true);
			
			boolean esAnotacionCampoForm = field.isAnnotationPresent(CampoForm.class);
			 
			if(esAnotacionCampoForm){
				
				String name = field.getName();
				Object o = field.get(object);
				
				boolean esExcluido = esParametroExcluido(name, excluir);
				
				if(!esExcluido){
				
					if(nombre != null){
						
						sbParametros.append(nombre);
						sbParametros.append(".");
					}
					
					sbParametros.append(name);
					sbParametros.append('=');
					sbParametros.append(o);
					sbParametros.append('&');
					
				}
			}
			
			boolean esAnotacionClaseForm = field.isAnnotationPresent(ClaseForm.class);
			
			if(esAnotacionClaseForm){
				
				//Paginación.
				Object o = field.get(object);
				
				Class<?> clazz = o.getClass();
				
				Field[] fieldClass = clazz.getDeclaredFields();
				
				crearParametros(o, field.getName(), excluir, fieldClass, sbParametros);
			}
		}
		
		return sbParametros.toString();
	}
	
	/**
	 * Pinta los parámetros. 
	 * 
	 * @param out Writer.
	 * @param parametrosSuper Parámetros del a suberclase BuscadorBase.
	 * @param parametros Parámetros de la subclase.
	 * @throws IOException En caso de error.
	 */
	private void pintarParametros(JspWriter out, String parametrosSuper, String parametros) throws IOException{
		
		StringBuffer sbResultado = new StringBuffer();
		
		if(!"".equals(parametrosSuper))
		{
			sbResultado.append(parametrosSuper);
		}
		
		if(!"".equals(parametros))
		{
			sbResultado.append(parametros);
		}
		
		String resultado = quitarUltimoCaracter(sbResultado.toString());
		
		out.print(resultado);
	}
	
	/**
	 * Quita el último caracter de la cadena.
	 * 
	 * @param cadena Cadena de la que se quitará el último caracter.
	 * @return Cadena sin el último caracter.
	 */
	private String quitarUltimoCaracter(String cadena){
		
		String resultado = null;
		
		resultado = cadena.substring(0, cadena.length() - 1);
		
		return resultado;
	}
	
	/**
	 * Comprueba si un parámetro está excluido.
	 * 
	 * @param nombre Nombre del parametro que se va a comprobar.
	 * @param excluidos Lista de excluidos.
	 * @return true si está excluido, false lo contrario.
	 */
	private boolean esParametroExcluido(String nombre, String excluidos){
		
		if(excluidos != null){
			
			String[] arrExcluidos = excluidos.split(",");
			
			for (String nombreExcluido : arrExcluidos) {
				
				if(nombreExcluido.equals(nombre)){
					
					return true;
				}
			}
		}
		
		return false;
	}
}