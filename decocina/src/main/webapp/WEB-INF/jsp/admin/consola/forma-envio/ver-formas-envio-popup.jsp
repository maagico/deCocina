<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>

<tiles:insertDefinition name="template-popup-admin">
	
    <tiles:putAttribute name="body-admin">
			
			<div class="row">
				
				<div class="col-xs-12 col-sm-9 col-md-7 col-lg-6">
		
					<table class="table table-condensed">
						<thead>
							<tr>
								<th>Id</th>	
					        	<th>Nombre</th>							        	
					      	</tr>
					    </thead>
					    <tbody>
					    
					    	<c:forEach var="formaEnvio" items="${formasEnvio}">
		
						    	<tr>
						        	<td>
						        		<c:out value = "${formaEnvio.id}"/>
						        	</td>
						        	<td>
						        	
						        		<a href = "<c:url value = "/admin/consola/zona/zona-forma-envio-insert?zona_id=${id}&forma_envio_id=${formaEnvio.id}&"/><decocina:parametros busqueda="${zonaBuscador}"/>">
						        			<c:out value = "${formaEnvio.nombre}"/>
						        		</a>
						        		
						        	</td>
						        </tr>
						   
					        </c:forEach>
					        
					    </tbody>
					</table>
					
				</div>
				
			</div>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>