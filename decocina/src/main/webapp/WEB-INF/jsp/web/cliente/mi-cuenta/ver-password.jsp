<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>


<tiles:insertDefinition name="template-central-web">

	<tiles:putAttribute name="title-web">
		<title>
			Modificar la contraseña - deCocina
		</title>
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
		
		<div class = "row">
					
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
				
				<c:if test = "${mensaje != null}">
					
					<div class="alert alert-success" role="alert">
						<c:out value = "${mensaje}"/>
					</div>
					
				</c:if>
				
				<c:if test = "${mensajeError != null}">
					
					<div class="alert alert-danger" role="alert">
						<c:out value = "${mensajeError}"/>
					</div>
					
				</c:if>
				
			</div>
			
		</div>
		
		<div class="jumbotron">
			
			<div class="container">
				
				<div class="row">
		
					<div class ="col-xs-12 col-sm-12 col-md-11 col-lg-11">
						
						<h3>Cambia tu contraseña</h3>
						
						<p>
						
							<form:form id = "passwordForm" action = "modificar-password" modelAttribute="passwordForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
								
								<spring:hasBindErrors name="passwordForm">
				                        
									<c:if test="${errors.hasFieldErrors('clientePassword.password')}">
										<c:set var="password" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('clientePassword.nuevoPassword')}">
										<c:set var="nuevoPassword" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('clientePassword.nuevoPasswordConfirmacion')}">
										<c:set var="nuevoPasswordConfirmacion" value="has-error" />
									</c:if>
								
			                       </spring:hasBindErrors>
							
			                       <div class="form-group <c:out value = "${password}"/>" >
			                          	<form:label path="clientePassword.password" class="col-md-3 control-label">Contraseña actual (*)</form:label>
			                          	<div class="col-md-9">
			                           	<form:input type = "password" path="clientePassword.password" class="form-control" placeholder="Contraseña" maxlength="15"/>
			                           	<form:errors path="clientePassword.password" class="form-error"/>
			                          	</div>
			                      	</div>
			                        
			                      	<div class="form-group <c:out value = "${nuevoPassword}"/>">
			                          	<form:label path="clientePassword.nuevoPassword" class="col-md-3 control-label">Nueva contraseña (*)</form:label>
			                       	<div class="col-md-9">
			                              	<form:input type = "password" path="clientePassword.nuevoPassword" class="form-control" placeholder="Nueva contraseña" maxlength="15"/>
			                              	<form:errors path="clientePassword.nuevoPassword" class="form-error"/>
			                          	</div>
			                       </div>
			                   
			                       <div class="form-group <c:out value = "${nuevoPasswordConfirmacion}"/>">
			                          	<form:label path="clientePassword.nuevoPasswordConfirmacion" class="col-md-3 control-label">Confirma la contraseña (*)</form:label>
			                       	<div class="col-md-9">
			                              	<form:input type = "password" path="clientePassword.nuevoPasswordConfirmacion" class="form-control" placeholder="Confirme contraseña" maxlength="15"/>
			                              	<form:errors path="clientePassword.nuevoPasswordConfirmacion" class="form-error"/>
			                          	</div>
			                       </div>
			                      	 
			                       <div class="form-group">
			                           
			                       	<div class="col-md-offset-2 col-xs-2 col-sm-1 col-md-2 col-lg-2">
										
										<a href = "<c:url value = "/cliente/mi-cuenta"/>" class="btn btn-primary">Volver</a>
																	
									</div>
									
									<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
										
										<a id = "accion" href = "#"  class="btn btn-success text-right">Modificar</a>
										
									</div>
							
							 	</div>
							                       	 
							</form:form>
					   	  
						</p>
					   	   
					</div>
				</div>
				
			</div>
			
		</div>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-web">
	
		<script>
		
			$(document).ready(function() {
				
				$("#accion").click(function(e){
						
					e.preventDefault();
						
					$("#passwordForm").submit();
						
				});
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>