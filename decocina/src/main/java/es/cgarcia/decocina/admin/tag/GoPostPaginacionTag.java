package es.cgarcia.decocina.admin.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Crea un script para viajar por el POST desde un la paginación.
 * 
 * @author Miguel Ángel Álvarez García
 *
 */
public class GoPostPaginacionTag extends SimpleTagSupport{

	/**
	 * Id del Form.
	 */
	private String id;
	
	/**
	 * Name del Form.
	 */
	private String name;
	
	/**
	 * Constructor.
	 */
	public GoPostPaginacionTag(){	
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
	 * {@inheritDoc}
	 */
	public void doTag() throws JspException, IOException {
	    
		JspWriter out = getJspContext().getOut();
		
		StringBuffer sbScript = new StringBuffer();
		sbScript.append("<script>\n");
		sbScript.append("function goPostPaginacion(action, start){\n");
		sbScript.append("	$(\"#");
		sbScript.append(id);
		sbScript.append("\").attr('action', action);\n");
		sbScript.append("	$(\"#param\\\\.start\").val(start);\n");
		sbScript.append("	$(\"#");
		sbScript.append(name);
		sbScript.append("\").submit();\n");
		sbScript.append("}");
		sbScript.append("</script>");
		
		out.println(sbScript.toString());
		
	}
}