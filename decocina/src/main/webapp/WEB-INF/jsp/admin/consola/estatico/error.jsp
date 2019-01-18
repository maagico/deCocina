<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="template-central-admin">
	
	<tiles:putAttribute name="title-admin">
		<title>
			Se ha producido un error - decocina
		</title>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body-admin">
			
		<div class="row">
			<div class= "container">	
				
				<div class="jumbotron">
			
					<h3>Lo sentimos, se ha producido un error cargando la página.</h3>
						
			   	</div>
			   		
			</div>
		   	
		</div>
		
	</tiles:putAttribute>
	
</tiles:insertDefinition>
