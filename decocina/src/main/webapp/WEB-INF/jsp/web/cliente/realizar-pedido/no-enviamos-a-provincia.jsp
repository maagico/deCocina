<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<tiles:insertDefinition name="template-central-web">
		
	<tiles:putAttribute name="title-web">
		<title>
			Lo sentimos, pero actualmente no enviamos pedidos a <c:out value = "${zona.nombre}"/> 
		</title>
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
		
		<div class="row">
			
			<div class= "container">	
				
				<div class="jumbotron">
			
					<div class="container">
				
						<h3>
							Lo sentimos, pero actualmente no enviamos pedidos a <c:out value = "${zona.nombre}"/> 
						</h3>
						
					</div>
					
				</div>
				
			</div>
			
		</div>
		
 	</tiles:putAttribute>
	
</tiles:insertDefinition>