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
public class ParametrosPostTag extends SimpleTagSupport{

	/**
	 * Id del Form.
	 */
	private String id;
	
	/**
	 * Name del Form.
	 */
	private String name;
	
	/**
	 * URL del action.
	 */
	private String url;
	
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
	public ParametrosPostTag(){	
	}
	
	/**
	 * Devuelve el valor del atributo id.
	 *
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Fija el valor del atributo id.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Devuelve el valor del atributo name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Fija el valor del atributo name.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve el valor del atributo url.
	 *
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Fija el valor del atributo url.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setUrl(String url) {
		this.url = url;
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
		
			StringBuffer sbFormInit = new StringBuffer();
			sbFormInit.append("<form id =\"");
			sbFormInit.append(id);
			sbFormInit.append("\" ");
			sbFormInit.append("name =\"");
			sbFormInit.append(name);
			sbFormInit.append("\" ");
			sbFormInit.append("action =\"");
			sbFormInit.append(url);
			sbFormInit.append("\" ");
			sbFormInit.append("method = \"POST\">");
			out.println(sbFormInit.toString());
			
			boolean esExcluido = esParametroExcluido("id", excluir);
			
			if(!esExcluido){
				
				StringBuffer sbId = new StringBuffer();
				sbId.append("<input type = \"hidden\" id = \"param.id\" name = \"id\" value = \"\"/>");
				out.println(sbId.toString());
			}
			
			Class<?> superClass = clazz.getSuperclass();
			Field[] fieldSuper = superClass.getDeclaredFields();
			
			String parametrosSuper = crearParametros(busqueda, null, excluir, fieldSuper, new StringBuffer());
			
			Field[] fields = clazz.getDeclaredFields();
			String parametros = crearParametros(busqueda, null, excluir, fields, new StringBuffer());
		
			pintarParametros(out, parametrosSuper, parametros);
			
			StringBuffer sbFormEnd = new StringBuffer();
			sbFormEnd.append("</form>");
			out.println(sbFormEnd.toString());
			
		} catch (IllegalAccessException  e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Crea los parámetros.
	 * 
	 * @param object Object.
	 * @param nombre Nombre.
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
				
					sbParametros.append("<input type = \"hidden\" id = \"");
					//Para diferenciar del id del buscador se añade param.
					sbParametros.append("param.");
					sbParametros.append(name);
					sbParametros.append("\" ");
					
					if(nombre != null){
						
						
						sbParametros.append(" name = \"");
						sbParametros.append(nombre);
						sbParametros.append(".");
					}else{
						
						sbParametros.append(" name = \"");
					}
					
					sbParametros.append(name);
					sbParametros.append("\" ");
					sbParametros.append("value = \"");
					sbParametros.append(o);
					sbParametros.append("\"/>\n");
					
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
		
		out.println(sbResultado.toString());	
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