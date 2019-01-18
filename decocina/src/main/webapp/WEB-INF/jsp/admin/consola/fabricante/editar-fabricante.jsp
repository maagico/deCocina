<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>


<tiles:insertDefinition name="template-central-admin">

    <tiles:putAttribute name="body-admin">
			
			<div class = "row">
				
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
					
					<div class="alert alert-info" role="alert">
							
						<c:if test = "${fabricanteForm.esCreacion}">
							Creando Fabricante
						</c:if>
						
						<c:if test = "${!fabricanteForm.esCreacion}">
							Modificando Fabricante
						</c:if>
						
					</div>
						
				</div>
				
			</div>
			
			<div class="row">
	
				<div class ="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
					<c:if test = "${fabricanteForm.esCreacion}">
						
						<c:set var="accion" value="fabricante-insert" />
					
					</c:if>
					
					<c:if test = "${!fabricanteForm.esCreacion}">
						
						<c:set var="accion" value="fabricante-update" />
					
					</c:if>
					
					<form:form id = "fabricanteForm" action = "${accion}" modelAttribute="fabricanteForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
					
						<decocina:input formulario="${fabricanteForm}"/>
						
						<spring:hasBindErrors name="fabricanteForm">
		                        
							<c:if test="${errors.hasFieldErrors('fabricante.nombre')}">
								<c:set var="nombre" value="has-error" />
							</c:if>
						
                        </spring:hasBindErrors>
					
                        <div class="form-group <c:out value = "${nombre}"/>" >
                           	<form:label path="fabricante.nombre" class="col-md-3 control-label">Nombre (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="fabricante.nombre" class="form-control" placeholder="Nombre" maxlength="99"/>
                            	<form:errors path="fabricante.nombre" class="form-error"/>
                           	</div>
                       	</div>
                         
                       	<div class="form-group">
                           	<form:label path="fabricante.descripcion" class="col-md-3 control-label">Descripción</form:label>
                        	<div class="col-md-9">
                               	<form:textarea path="fabricante.descripcion" class="form-control" placeholder="Descripción" rows="4" maxlength="255"/>
                               	<form:errors path="fabricante.descripcion" class="form-error"/>
                           	</div>
                        </div>
                       	 
                        <div class="form-group">
                            
                        	<div class="col-md-offset-2 col-xs-2 col-sm-1 col-md-2 col-lg-2">
								
								
								<c:if test = "${!fabricanteBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/fabricante/fabricante-find?"/><decocina:parametros busqueda='${fabricanteBuscador}'/>" class="btn btn-primary">Volver</a>
									
								</c:if>
								
								<c:if test = "${fabricanteBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/fabricante/fabricante-search?"/><decocina:parametros busqueda="${fabricanteBuscador}"/>" class="btn btn-primary">Volver</a>
								
								</c:if>
								
							</div>
							
							<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
								
								<c:if test = "${fabricanteForm.esCreacion}">
								
									<a id = "accion" href = "#" class="btn btn-success text-right">Crear</a>	
								
								</c:if>
								
								<c:if test = "${!fabricanteForm.esCreacion}">
									
									<a id = "eliminar" href = "#" class="btn btn-danger">Eliminar</a>
									<a id = "accion" href = "#"  class="btn btn-success text-right">Modificar</a>
								
								</c:if>
								
							</div>
					
					 	</div>
					                       	 
					</form:form>
				   	   
				</div>
			</div>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-admin">
	
		<script>
		
			$(document).ready(function() {
				
				$("#accion").click(function(e){
						
					e.preventDefault();
						
					$("#fabricanteForm").submit();
						
				});
				
				$("#eliminar").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Fabricante</h4>',
			            message: 'Se eliminará el Fabricante ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Eliminar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#fabricanteForm").attr("action", "fabricante-delete");
								$("#fabricanteForm").submit();
			                }
			            }
			        });
					
				});
				
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>