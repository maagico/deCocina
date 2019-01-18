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
					    
					    	<c:forEach var="zona" items="${zonas}">
		
						    	<tr>
						        	<td>
						        		<c:out value = "${zona.id}"/>
						        	</td>
						        	<td>
						        	
						        		<a href = "<c:url value = "/admin/consola/zona/zona-padre-insert?zona_id=${zona.id}&id=${id}&"/><decocina:parametros busqueda="${zonaBuscador}"/>">
						        			<c:out value = "${zona.nombre}"/>
						        		</a>
						        		
						        	</td>
						        </tr>
						   
					        </c:forEach>
					        
					    </tbody>
					</table>
					
				</div>
				
			</div>
			
			<div class = "row">			
				
				<div class="col-xs-12 col-sm-9 col-md-7 col-lg-6 text-right">
					
					<decocina:paginacionPopup dialog = "dialogZonas" url = "admin/consola/zona/zona" busqueda="${zonaBuscador}" add = "id=${id}&"/>
					
				</div>
			
			</div>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>