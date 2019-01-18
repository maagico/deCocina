package es.cgarcia.decocina.admin.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import es.cgarcia.decocina.admin.buscador.BuscadorBase;

/**
 * Tag para volver al listado por POST.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class VolverPostTag extends SimpleTagSupport{

	/**
	 * URL del enlace.
	 */
	protected String url = null;
	
	/**
	 * Formulario del que se buscarán las anotaciones.
	 */
	protected BuscadorBase busqueda = null; 
	
	/**
	 * Constructor.
	 */
	public VolverPostTag(){	
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
	 * {@inheritDoc}
	 */
	public void doTag() throws JspException, IOException {
	    
		JspWriter out = getJspContext().getOut();
	    
		PageContext pageContext = (PageContext) getJspContext();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest(); 
			
		String contexto = request.getContextPath();
			
		pintarParametros(out, contexto);
		
	}
	
	/**
	 * Pinta los parámetros < anterior y siguiente >. 
	 * 
	 * @param out Writer.
	 * @param contexto Contexto de la aplicación. 
	 * @throws IOException En caso de error.
	 */
	protected void pintarParametros(JspWriter out, String contexto) throws IOException{
		
		String sufijo = "-find";
		
		Boolean hayBusqueda = busqueda.getHayBusqueda();
		
		if(hayBusqueda){
			
			sufijo = "-search";
		}
		
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append("<a href = \"javascript:goPostVolver('");
		sbResultado.append(url);
		sbResultado.append(sufijo);
		sbResultado.append("');\"");
		sbResultado.append(" class = \"btn btn-primary\">");
		sbResultado.append("Volver");
		sbResultado.append("</a>");
		
		out.print(sbResultado.toString());
	}
}