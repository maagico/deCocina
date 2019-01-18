package es.cgarcia.decocina.admin.tag;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import es.cgarcia.decocina.admin.annotation.CampoForm;
import es.cgarcia.decocina.admin.annotation.ClaseForm;
import es.cgarcia.decocina.admin.buscador.BuscadorBase;
import es.cgarcia.decocina.admin.paginacion.Paginacion;

/**
 * Tag que recupera el formulario recibido por parámetro y busca 
 * las anotaciones @CampoForm para crear los parámetros de navegación.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class PaginacionTag extends SimpleTagSupport{

	/**
	 * URL del enlace.
	 */
	protected String url = null;
	
	/**
	 * Formulario del que se buscarán las anotaciones.
	 */
	protected BuscadorBase busqueda = null; 
	
	/**
	 * Indica si se debe mostrar el enlace 'anterior'.
	 */
	private Boolean hayAnterior = new Boolean(true);
	
	/**
	 * Indica si se debe mostrar el enlace 'siguiente'.
	 */
	private Boolean haySiguiente = new Boolean(true);
	
	/**
	 * Constructor.
	 */
	public PaginacionTag(){	
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
	 * Devuelve el valor del atributo hayAnterior.
	 *
	 * @return hayAnterior
	 */
	public boolean isHayAnterior() {
		return hayAnterior; 
	}

	/**
	 * Fija el valor del atributo hayAnterior.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setHayAnterior(boolean hayAnterior) {
		this.hayAnterior = hayAnterior;
	}

	/**
	 * Devuelve el valor del atributo haySiguiente.
	 *
	 * @return haySiguiente
	 */
	public boolean isHaySiguiente() {
		return haySiguiente;
	}

	/**
	 * Fija el valor del atributo haySiguiente.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setHaySiguiente(boolean haySiguiente) {
		this.haySiguiente = haySiguiente;
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
			Field[] fields = clazz.getDeclaredFields();
			
			String parametrosSuperAnterior = crearParametros(true, busqueda, fieldSuper, new StringBuffer());
			String parametrosAnterior = crearParametros(true, busqueda, fields, new StringBuffer());
			
			String parametrosSuperSiguiente = crearParametros(false, busqueda, fieldSuper, new StringBuffer());
			String parametrosSiguiente = crearParametros(false, busqueda, fields, new StringBuffer());
			
			StringBuffer sbAnterior = new StringBuffer();
			sbAnterior.append(parametrosSuperAnterior);
			sbAnterior.append(parametrosAnterior);
			
			
			StringBuffer sbSiguiente = new StringBuffer();
			sbSiguiente.append(parametrosSuperSiguiente);
			sbSiguiente.append(parametrosSiguiente);
			
			PageContext pageContext = (PageContext) getJspContext();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest(); 
			
			String contexto = request.getContextPath();
			
			if(hayAnterior){
				
				pintarParametros(out, contexto, true, sbAnterior.toString());
			}
			
			pintarInformacion(out, busqueda);
			
			if(haySiguiente){
				
				pintarParametros(out, contexto, false, sbSiguiente.toString());
			}
			
		} catch (IllegalAccessException  e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Crea los parámetros.
	 * 
	 * @param object Object.
	 * @param fields Campos.
	 * @return Devuelve los parámetros.
	 * @throws IllegalAccessException En caso de error.
	 */
	public String crearParametros(boolean anterior, Object object, Field[] fields, StringBuffer sbParametros) throws IllegalAccessException{
		
		for (Field field : fields) {
			
			field.setAccessible(true);
			
			boolean esAnotacionCampoForm = field.isAnnotationPresent(CampoForm.class);
			 
			if(esAnotacionCampoForm){
				
				String name = field.getName();
				Object o = field.get(object);
				
				sbParametros.append(name);
				sbParametros.append('=');
				sbParametros.append(o);
				sbParametros.append('&');
					
			}
			
			boolean esAnotacionClaseForm = field.isAnnotationPresent(ClaseForm.class);
			
			if(esAnotacionClaseForm){
				
				//Paginación.
				Object o = field.get(object);
				
				String name = field.getName();
				
				Paginacion paginacion = (Paginacion)o;
				
				Long start = paginacion.getStart();
				Long limit = paginacion.getLimit();
				Long count = paginacion.getCount();
				
				sbParametros.append(name);
				sbParametros.append('.');
				sbParametros.append("start");
				sbParametros.append('=');
				
				if(anterior){
					
					long resultado = start - limit;
					
					if(resultado < 0){
						
						hayAnterior = false;
					}
						
					sbParametros.append(resultado);
				}else
				{
					long resultado = start + limit;
					
					if(resultado >= count)
					{
						haySiguiente = false;
					}
					
					sbParametros.append(resultado);
				}
				
				sbParametros.append('&');
				
				sbParametros.append(name);
				sbParametros.append('.');
				sbParametros.append("limit");
				sbParametros.append('=');
				sbParametros.append(limit);
				sbParametros.append('&');
				
				sbParametros.append(name);
				sbParametros.append('.');
				sbParametros.append("count");
				sbParametros.append('=');
				sbParametros.append(count);
				sbParametros.append('&');
				
			}
		}
		
		return sbParametros.toString();
	}
	
	/**
	 * Pinta los parámetros < anterior y siguiente >. 
	 * 
	 * @param out Writer.
	 * @param parametrosSuper Parámetros de la superclase BuscadorBase.
	 * @param parametros Parámetros de la subclase.
	 * @throws IOException En caso de error.
	 */
	protected void pintarParametros(JspWriter out, String contexto, boolean anterior, String parametros) throws IOException{
		
		String resultado = quitarUltimoCaracter(parametros);
		
		String sufijo = "-find";
		
		Boolean hayBusqueda = busqueda.getHayBusqueda();
		
		if(hayBusqueda){
			
			sufijo = "-search";
		}
		
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append("<a href = \"");
		sbResultado.append(contexto);
		sbResultado.append(url);
		sbResultado.append(sufijo);
		sbResultado.append('?');
		sbResultado.append(resultado);
		sbResultado.append('"');
		sbResultado.append(" class = \"btn btn-default\">");
		
		if(anterior){
		
			sbResultado.append("<span class=\"glyphicon glyphicon-chevron-left\"></span>");
		}else{
			
			sbResultado.append("<span class=\"glyphicon glyphicon-chevron-right\"></span>");
		}
		
		sbResultado.append("</a>");

		
		out.print(sbResultado.toString());
	}
	
	/**
	 * Pinta la información '1 al 10 de 100'
	 * 
	 * @param out Writer.
	 * @param busqueda Busqueda de la que se sacará la información.
	 * @throws IOException En caso de error.
	 */
	private void pintarInformacion(JspWriter out, BuscadorBase busqueda) throws IOException {
		
		Paginacion paginacion = busqueda.getPaginacion();
		
		Long start = paginacion.getStart();
		Long limit = paginacion.getLimit();
		
		Long count = paginacion.getCount();
		
		StringBuffer sbInfo = new StringBuffer();
		sbInfo.append(" <span>");
		sbInfo.append(start + 1);
		sbInfo.append(" - ");
		
		Long max = start + limit;
		
		if(max > count){
			max = count;
		}
		
		sbInfo.append(max);
		sbInfo.append(" de ");
		sbInfo.append(count);
		sbInfo.append(" </span>");
		
		out.println(sbInfo.toString());
	}
	
	/**
	 * Quita el último caracter de la cadena.
	 * 
	 * @param cadena Cadena de la que se quitará el último caracter.
	 * @return Cadena sin el último caracter.
	 */
	protected String quitarUltimoCaracter(String cadena){
		
		String resultado = null;
		
		resultado = cadena.substring(0, cadena.length() - 1);
		
		return resultado;
	}
}