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
							
						<c:if test = "${impuestoForm.esCreacion}">
							Creando Impuesto
						</c:if>
						
						<c:if test = "${!impuestoForm.esCreacion}">
							Modificando Impuesto
						</c:if>
							
					</div>
					
				</div>
				
			</div>
			
			<div class="row">
	
				<div class ="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
					<c:if test = "${impuestoForm.esCreacion}">
						
						<c:set var="accion" value="impuesto-insert" />
					
					</c:if>
					
					<c:if test = "${!impuestoForm.esCreacion}">
						
						<c:set var="accion" value="impuesto-update" />
					
					</c:if>
					
					<form:form id = "impuestoForm" action = "${accion}" modelAttribute="impuestoForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
					
						<decocina:input formulario="${impuestoForm}"/>
						
						<spring:hasBindErrors name="impuestoForm">
		                        
							<c:if test="${errors.hasFieldErrors('impuesto.nombre')}">
								<c:set var="nombre" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('impuesto.valor')}">
								<c:set var="valor" value="has-error" />
							</c:if>
						
                        </spring:hasBindErrors>
					
                        <div class="form-group <c:out value = "${nombre}"/>" >
                           	<form:label path = "impuesto.nombre" class="col-md-3 control-label">Nombre (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="impuesto.nombre" class="form-control" placeholder="Nombre" maxlength="99"/>
                            	<form:errors path="impuesto.nombre" class="form-error"/>
                           	</div>
                       	</div>
                       	
                       	<div class="form-group <c:out value = "${valor}"/>" >
                           	<form:label path = "impuesto.valor" class="col-md-3 control-label">Valor (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="impuesto.valor" class="form-control" placeholder="Valor"/>
                            	<form:errors path="impuesto.valor" class="form-error"/>
                           	</div>
                       	</div>
                         
                       	<div class="form-group">
                           	<form:label path = "impuesto.descripcion" class="col-md-3 control-label">Descripción</form:label>
                        	<div class="col-md-9">
                               	<form:textarea path="impuesto.descripcion" class="form-control" placeholder="Descripción" rows="4" maxlength="255"/>
                               	<form:errors path="impuesto.descripcion" class="form-error"/>
                           	</div>
                        </div>
                       	
                       	<div class="form-group">
                           	<label for="impuesto.paraArticulo1" class="col-md-3 control-label">Para Artículo</label>
                           	<div class="col-md-9">
                                <form:checkbox path="impuesto.paraArticulo"/>
                           	</div>
                       	</div>
                       	
                       	<div class="form-group">
                           	<label for="impuesto.paraZona1" class="col-md-3 control-label">Para Zona</label>
                           	<div class="col-md-9">
                                <form:checkbox path="impuesto.paraZona"/>
                           	</div>
                       	</div>
                       	 
                        <div class="form-group">
                            
                        	<div class="col-md-offset-2 col-xs-2 col-sm-1 col-md-2 col-lg-2">
								
								
								<c:if test = "${!impuestoBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/impuesto/impuesto-find?"/><decocina:parametros busqueda='${impuestoBuscador}'/>" class="btn btn-primary">Volver</a>
									
								</c:if>
								
								<c:if test = "${impuestoBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/impuesto/impuesto-search?"/><decocina:parametros busqueda="${impuestoBuscador}"/>" class="btn btn-primary">Volver</a>
								
								</c:if>
								
							</div>
							
							<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
								
								<c:if test = "${impuestoForm.esCreacion}">
								
									<a id = "accion" href = "#" class="btn btn-success text-right">Crear</a>	
								
								</c:if>
								
								<c:if test = "${!impuestoForm.esCreacion}">
									
									<a id = "eliminar" href = "#" class="btn btn-danger">Eliminar</a>
									<a id = "accion" href = "#"  class="btn btn-success text-right">Modificar</a>
								
								</c:if>
								
							</div>
					
					 	</div>
					                       	 
					</form:form>
				   	   
				</div>
			</div>
			
			<div id = "modal_advertencia_eliminar" class="modal fade">
				
				<div class="modal-dialog">
			    	
			    	<div class="modal-content">
			      		
			      		<div class="modal-header">
			        		
			        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        		<h4 class="modal-title">Impuesto</h4>
			      		
			      		</div>
			      
			      		<div class="modal-body">
			        		
			        		<p>Se eliminará el Impuesto. ¿Continuar? </p>
			      		
			      		</div>
			      
			      		<div class="modal-footer">
			        		
			        		<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			        		<button id = "eliminar_modal" type="button" class="btn btn-primary">Eliminar</button>
			      		
			      		</div>
			    	
			    	</div>
			  	
			  	</div>
			
			</div>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-admin">
	
		<script>
		
			$(document).ready(function() {
				
				$("#accion").click(function(e){
						
					e.preventDefault();
						
					$("#impuestoForm").submit();
						
				});
				
				$("#eliminar").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Impuesto</h4>',
			            message: 'Se eliminará el Impuesto ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Eliminar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#impuestoForm").attr("action", "impuesto-delete");
								$("#impuestoForm").submit();
			                }
			            }
			        });
					
				});
				
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>