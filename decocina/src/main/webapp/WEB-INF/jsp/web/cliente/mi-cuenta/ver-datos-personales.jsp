<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>


<tiles:insertDefinition name="template-central-web">

	<tiles:putAttribute name="title-web">
		<title>
			Datos personales - deCocina
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
					
					<div class ="col-xs-12 col-sm-12 col-md-10 col-lg-10">
						
						<h3>Modifica tus datos personales</h3>
						
						<p>
						
							<form:form id = "clienteForm" action = "modificar-datos-personales" modelAttribute="clienteForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
								
								<spring:hasBindErrors name="clienteForm">
				                        
									<c:if test="${errors.hasFieldErrors('cliente.nombre')}">
										<c:set var="nombre" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('cliente.apellidos')}">
										<c:set var="apellidos" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('cliente.email')}">
										<c:set var="email" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('cliente.telefono')}">
										<c:set var="telefono" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('cliente.direccion.calle')}">
										<c:set var="calle" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('cliente.direccion.zona')}">
										<c:set var="provincia" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('cliente.direccion.codigoPostal')}">
										<c:set var="codigoPostal" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('cliente.direccion.poblacion')}">
										<c:set var="poblacion" value="has-error" />
									</c:if>
								
			                       </spring:hasBindErrors>
							
			                       <div class="form-group <c:out value = "${nombre}"/>" >
			                          	<form:label path="cliente.nombre" class="col-md-3 control-label">Nombre (*)</form:label>
			                          	<div class="col-md-9">
			                           	<form:input path="cliente.nombre" class="form-control" placeholder="Nombre" maxlength="100"/>
			                           	<form:errors path="cliente.nombre" class="form-error"/>
			                          	</div>
			                      	</div>
			                        
			                      	<div class="form-group <c:out value = "${apellidos}"/>">
			                          	<form:label path="cliente.apellidos" class="col-md-3 control-label">Apellidos (*)</form:label>
			                       	<div class="col-md-9">
			                              	<form:input path="cliente.apellidos" class="form-control" placeholder="Apellidos" maxlength="100"/>
			                              	<form:errors path="cliente.apellidos" class="form-error"/>
			                          	</div>
			                       </div>
			                   
			                       <div class="form-group <c:out value = "${telefono}"/>">
			                          	<form:label path="cliente.telefono" class="col-md-3 control-label">Teléfono (*)</form:label>
			                       	<div class="col-md-9">
			                              	<form:input path="cliente.telefono" class="form-control" placeholder="Teléfono" maxlength="50"/>
			                              	<form:errors path="cliente.telefono" class="form-error"/>
			                          	</div>
			                       </div>
			                       
			                       <div class="form-group <c:out value = "${calle}"/>">
			                          	<form:label path="cliente.direccion.calle" class="col-md-3 control-label">Calle (*)</form:label>
			                       	<div class="col-md-9">
			                              	<form:input path="cliente.direccion.calle" class="form-control" placeholder="Calle, número, piso y letra " maxlength="150"/>
			                              	<form:errors path="cliente.direccion.calle" class="form-error"/>
			                          	</div>
			                       </div>
			                       
			                       <div class="form-group <c:out value = "${codigoPostal}"/>">
			                          	<form:label path="cliente.direccion.codigoPostal" class="col-md-3 control-label">Código postal (*)</form:label>
			                       	<div class="col-md-9">
			                              	<form:input path="cliente.direccion.codigoPostal" class="form-control" placeholder="Código postal" maxlength="5"/>
			                              	<form:errors path="cliente.direccion.codigoPostal" class="form-error"/>
			                          	</div>
			                       </div>
			                       
			                        <div class="form-group <c:out value = "${provincia}"/>">
			                          	<form:label path="cliente.direccion.zona.id" class="col-md-3 control-label">Provincia (*)</form:label>
			                       	<div class="col-md-9">
			                           	<form:select path="cliente.direccion.zona.id" class = "form-control">
										   <form:option value="" label="Selecciona una provincia..."/>
										   <form:options items="${provincias}" itemValue="id" itemLabel="nombre"/>
										</form:select> 
										<form:errors path="cliente.direccion.zona" class="form-error"/>
			                          	</div>
			                       </div>
			                       
			                      
			                       <div class="form-group <c:out value = "${codigoPostal}"/>">
			                          	<form:label path="cliente.direccion.poblacion" class="col-md-3 control-label">Población (*)</form:label>
			                       	<div class="col-md-9">
			                              	<form:input path="cliente.direccion.poblacion" class="form-control" placeholder="Población" maxlength="100"/>
			                              	<form:errors path="cliente.direccion.poblacion" class="form-error"/>
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
	
		
		<script src="<c:url value = "/js/jquery.validate.min.js"/>"></script>
		<script src="<c:url value = "/js/validaciones.form.alta.panel.js"/>"></script>
		<script src="<c:url value = "/js/bootstrap-dialog.min.js"/>"></script>
		
		<script>
		
			$(document).ready(function() {
				
				$("#accion").click(function(e){
						
					e.preventDefault();
						
					$("#clienteForm").submit();
						
				});
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>