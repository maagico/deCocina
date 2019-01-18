<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			Panel de control - deCocina
		</title>
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
		
		<div class="form-horizontal">
			
			<div class="panel panel-default">
			
	        	<div class="panel-heading">
	        		<h3 class="panel-title">Cuenta</h3>
	        	</div>
	        	
	        	<div class="panel-body panel-custom">
	            	<a href="<c:url value= "/cliente/ver-datos-personales"/>">Ver o modificar mis datos personales</a>
	            </div>
	            
	            <div class="panel-body panel-custom">
	            	<a href="<c:url value= "/cliente/ver-password"/>">Cambiar contraseña</a>
				</div>
		 	</div>
		
			<c:if test="${fn:length(pedidos) > 0}">
				
				<div class="panel panel-default">
				
		        	<div class="panel-heading">
		        		<h3 class="panel-title">Pedidos realizados</h3>
		        	</div>
		        	
		        	<c:forEach var = "pedido" items = "${pedidos}">
		        		
		        		<div class="panel-body panel-custom">
			        		<a href="<c:url value= "/cliente/ver-pedido/id/${pedido.id}"/>">
			            		<span>	
			            			<fmt:formatDate type="date"  pattern = "dd/MM/yyyy" value="${pedido.fechaCreacion.time}" />
								</span>
								<BR>
			            		<span>
			            			<c:out value = "${pedido.nombreCliente}"/>, España <c:out value = "${pedido.zona}"/> <c:out value = "${pedido.poblacion}"/>.
			            		</span>
			            	</a>
		        		</div>
		        	</c:forEach>
		            
				</div>
				
			</c:if>
		</div>
		
 	</tiles:putAttribute>
	
</tiles:insertDefinition>