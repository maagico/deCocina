package es.cgarcia.decocina.admin.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

/**
 * Tag que recupera el formulario recibido por parámetro y busca 
 * las anotaciones @CampoForm para crear los parámetros de navegación.
 * 
 * Este Tag se debe utilizar sólo para las paginaciones dentro de un Popup.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class PaginacionPopupTag extends PaginacionTag{
	
	/**
	 * Variable Javascript que referencia al Dialog Modal que contiene los resultados que se están paginando.
	 */
	private String dialog;
	
	/**
	 * Parámetros fijos que se añadirán a la url.
	 */
	private String add;
	
	/**
	 * Constructor.
	 */
	public PaginacionPopupTag(){	
	}
	
	/**
	 * Devuelve el valor del atributo dialog.
	 *
	 * @return dialog
	 */
	public String getDialog() {
		return dialog;
	}

	/**
	 * Fija el valor del atributo dialog.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setDialog(String dialog) {
		this.dialog = dialog;
	}
	
	/**
	 * Devuelve el valor del atributo add.
	 *
	 * @return add
	 */
	public String getAdd() {
		return add;
	}

	/**
	 * Fija el valor del atributo add.
	 *
	 * @param El nuevo valor del atributo.
	 */
	public void setAdd(String add) {
		this.add = add;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doTag() throws JspException, IOException {
		
		super.doTag();
		
		JspWriter out = getJspContext().getOut();
		
		pintarScript(out);
	}
	
	/**
	 * Pinta el script que ejecutala llamada al controller.
	 * @param out Writer.
	 * @throws IOException 
	 */
	private void pintarScript(JspWriter out) throws IOException {
		
		StringBuffer sbScript = new StringBuffer();
		
		sbScript.append("<script>");
		sbScript.append("function go(url){");
		sbScript.append("var contenido = $('<div></div>').load(url, function(){");
		sbScript.append(dialog);
		sbScript.append(".setMessage(contenido)});}");
		sbScript.append("</script>");
		
		out.println(sbScript.toString());
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
		
		String sufijo = "-find-popup";
		
		Boolean hayBusqueda = busqueda.getHayBusqueda();
		
		if(hayBusqueda){
			
			sufijo = "-search-popup";
		}
		
		StringBuffer sbResultado = new StringBuffer();
		sbResultado.append("<a href = \"#\" ");
		sbResultado.append("onclick=\"");
		sbResultado.append("javascript:go('");
		sbResultado.append(contexto);
		sbResultado.append('/');
		sbResultado.append(url);
		sbResultado.append(sufijo);
		sbResultado.append('?');
		sbResultado.append(add);
		sbResultado.append(resultado);
		sbResultado.append("');\" ");
		sbResultado.append(" class = \"btn btn-default\">");
		
		if(anterior){
			
			sbResultado.append("<span class=\"glyphicon glyphicon-chevron-left\"></span>");
		}else{
			
			sbResultado.append("<span class=\"glyphicon glyphicon-chevron-right\"></span>");
		}
		
		sbResultado.append("</a>");
		
		
		out.print(sbResultado.toString());
	}
}