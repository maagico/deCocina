<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			El enlace ha caducado - deCocina
		</title>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="meta-web">
	 	
	 	<meta name="viewport" content="width=device-width; initial-scale=1, user-scalable=yes"/>
	 	
	 	<meta name="robots" content="noindex,nofollow"/>
	
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
	
	<div class="row">
	
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
			<div class="jumbotron">
			
				<div class="container">
		    		
		    		<h3>El enlace ha caducado</h3>
			    		    
			   		<p>
			   			Por favor, vuelve a solicitar la <a href = "<c:url value = "recuperar-password"/>">recuperación de la contraseña</a>.
			   		</p>
		   			
		   		</div>
		   	</div>
		   	
		</div>
	
	</div>
			
 	</tiles:putAttribute>
	
</tiles:insertDefinition>