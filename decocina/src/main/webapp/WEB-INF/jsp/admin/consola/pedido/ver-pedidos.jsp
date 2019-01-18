<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>

<tiles:insertDefinition name="template-central-admin">
	
    <tiles:putAttribute name="body-admin">
			
			<div class="row">
				
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 search-bottom-padding" style= "margin-top:10px">
					
					<form:form id = "buscadorForm" action = "pedido-search" modelAttribute="pedidoBuscador" class="form-horizontal" role="search" acceptCharset="UTF-8" method="GET">
				    	
				    		<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						    	<div class="form-group">
						    	
						        
						        	<form:hidden path="hayBusqueda"/>
						        	
						        	<form:input path = "nombre" class="form-control form-input-buscador" placeholder="Nombre y apellidos"/>
						        	<form:input path = "telefono" class="form-control" placeholder="Télefono"/>
					            	
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
								
								<a id = "buscar" href = "#" class="btn btn-default form-input-buscador"><i class="glyphicon glyphicon-search"></i></a>
								
								<c:if test = "${pedidoBuscador.hayBusqueda}">
								<a id = "deshacerBusqueda" href = "<c:url value = "/admin/consola/pedido/pedido-find"/>" class="btn btn-default"><i class="glyphicon glyphicon-remove-circle"></i></a>
								</c:if>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							</div>
				   </form:form>
				   	   
				</div>
				
			</div>
			
		 	<div class="row">
				
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		
					<table class="table table-condensed">
						<thead>
							<tr>
								<th>Id</th>	
					        	<th>Cliente</th>	
					        	<th>Importe</th>		
					            <th>Fecha</th>	    	
					      	</tr>
					    </thead>
					    <tbody>
					    
					    	<c:forEach var="pedido" items="${pedidos}">
		
						    	<tr>
						        	<td>
						        		<c:out value = "${pedido.id}"/>
						        	</td>
						        	<td>
						        		
						        		<a href = "javascript:goPost(${pedido.id});">
						        			<c:out value = "${pedido.nombreCliente}"/>
						        		</a>
						        		
						        	</td>
						        	<td>
						        		
						        		<c:out value = "${pedido.precioTotal}"/>€
						        		
						        	</td>
						        	
						        	<td>
						        		
						        		<fmt:formatDate type="date" pattern = "dd/MM/yyyy" value="${pedido.fechaCreacion.time}" />
						        	</td>
						        </tr>
						   
					        </c:forEach>
					        
					    </tbody>
					</table>
					
				</div>
				
			</div>
			
			<div class = "row">
				
				<div class="col-xs-2 col-sm-1 col-md-1 col-lg-1">
				</div>
				
				<div class="col-xs-10 col-sm-11 col-md-11 col-lg-11 text-right">
					
					<decocina:paginacionPost url = "pedido" busqueda="${pedidoBuscador}"/>
					
				</div>
					
			</div>		        
			
			<decocina:parametrosPost id = "pedidoModifyForm" name = "pedidoModifyForm" url = "pedido-modify" busqueda="${pedidoBuscador}"/>
			
			<decocina:goPost id = "pedidoModifyForm" name = "pedidoModifyForm"/>
			<decocina:goPostPaginacion id = "pedidoModifyForm" name = "pedidoModifyForm"/>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-admin">
	
		<script>
		
			$(document).ready(function() {
				
				$("#buscar").click(function(e){
					
					e.preventDefault();
					
					var nombre    = $("#nombre").val();
					var telefono  = $("#telefono").val();
					
					if(nombre.trim() != "" || telefono.trim() != ""){
					
						$("#buscadorForm").submit();
					}
					
				});
				
			});
		
		</script>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>