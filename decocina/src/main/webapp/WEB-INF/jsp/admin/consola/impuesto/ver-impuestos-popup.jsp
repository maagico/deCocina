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
					    
					    	<c:forEach var="impuesto" items="${impuestos}">
		
						    	<tr>
						        	<td>
						        		<c:out value = "${impuesto.id}"/>
						        	</td>
						        	<td>
						        	
						        		<a href = "<c:url value = "/admin/consola/zona/zona-impuesto-insert?zona_id=${id}&impuesto_id=${impuesto.id}&"/><decocina:parametros busqueda="${zonaBuscador}"/>">
						        			<c:out value = "${impuesto.nombre}"/>
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