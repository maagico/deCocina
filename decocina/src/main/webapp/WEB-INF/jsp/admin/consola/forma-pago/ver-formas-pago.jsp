<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>

<tiles:insertDefinition name="template-central-admin">
	
    <tiles:putAttribute name="body-admin">
			
			<div class="row">
				
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 search-bottom-padding">
					
					<form:form id = "buscadorForm" action = "forma-pago-search" modelAttribute="formaPagoBuscador" class="form-horizontal" role="search" acceptCharset="UTF-8" method="GET">
				    	
				    	<div class="input-group">
				        
				        	<form:hidden path="hayBusqueda"/>
				        	
				        	<form:input path = "nombre" class="form-control" placeholder="Búsqueda por nombre"/>
			            	
			            	<div class="input-group-btn">
			                	<a id = "buscar" href = "#" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></a>
							</div>
							
						</div>
						
				   </form:form>
				   	   
				</div>
				
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 search-bottom-padding">
					
					<c:if test = "${formaPagoBuscador.hayBusqueda}">	
					
						<a id = "deshacerBusqueda" href = "<c:url value = "/admin/consola/forma-pago/forma-pago-find"/>" class="btn btn-default">Deshacer búsqueda</a>	
					
					</c:if>
				
				</div>
				
			</div>
			
		 	<div class="row">
				
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		
					<table class="table table-condensed">
						<thead>
							<tr>
								<th>Id</th>	
					        	<th>Nombre</th>		
					        	<th></th>					        	
					      	</tr>
					    </thead>
					    <tbody>
					    
					    	<c:forEach var="formaPago" items="${formasPago}">
		
						    	<tr>
						        	<td>
						        		<c:out value = "${formaPago.id}"/>
						        	</td>
						        	<td>
						        		
						        		<a href = "<c:url value = "/admin/consola/forma-pago/forma-pago-modify?id=${formaPago.id}&"/><decocina:parametros busqueda="${formaPagoBuscador}"/>">
						        			<c:out value = "${formaPago.nombre}"/>	
						        		</a>
						        		
						        	</td>
						        	<td>
						        		
						        		<a href = "<c:url value = "/admin/consola/forma-pago/forma-pago-modify?id=${formaPago.id}&"/><decocina:parametros busqueda="${formaPagoBuscador}"/>" class="btn btn-default btn-sm">
						        			<i class="glyphicon glyphicon-edit"></i>
						        		</a>
						        		
						        	</td>
						        </tr>
						   
					        </c:forEach>
					        
					    </tbody>
					</table>
					
				</div>
				
			</div>
			
			<div class = "row">
				
				<div class="col-xs-2 col-sm-1 col-md-1 col-lg-1">
					
					<a href = "<c:url value = "/admin/consola/forma-pago/forma-pago-modify?"/><decocina:parametros busqueda="${formaPagoBuscador}"/>" class="btn btn-success">Crear</a>
					
				</div>
				
				<div class="col-xs-10 col-sm-11 col-md-11 col-lg-11 text-right">
					
					<decocina:paginacion url = "/admin/consola/forma-pago/forma-pago" busqueda="${formaPagoBuscador}"/>
					
				</div>
					
			</div>		        
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-admin">
	
		<script>
		
			$(document).ready(function() {
				
				$("#buscar").click(function(e){
					
					e.preventDefault();
					
					var nombre = $("#nombre").val();
					
					if(nombre.trim() != ""){
					
						$("#buscadorForm").submit();
					}
					
				});
				
			});
		
		</script>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>