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
						
						<c:if test = "${formaPagoForm.esCreacion}">
							Creando Forma de Pago
						</c:if>
						
						<c:if test = "${!formaPagoForm.esCreacion}">
							Modificando Forma de Pago
						</c:if>
						
					</div>
					
				</div>
				
			</div>
			
			<div class="row">
	
				<div class ="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
					<c:if test = "${formaPagoForm.esCreacion}">
						
						<c:set var="accion" value="forma-pago-insert" />
					
					</c:if>
					
					<c:if test = "${!formaPagoForm.esCreacion}">
						
						<c:set var="accion" value="forma-pago-update" />
					
					</c:if>
					
					<form:form id = "formaPagoForm" action = "${accion}" modelAttribute="formaPagoForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
					
						<decocina:input formulario="${formaPagoForm}"/>
						
						<spring:hasBindErrors name="formaPagoForm">
		                        
							<c:if test="${errors.hasFieldErrors('formaPago.nombre')}">
								<c:set var="nombre" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('formaPago.descripcion')}">
								<c:set var="descripcion" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('formaPago.formula')}">
								<c:set var="formula" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('formaPago.nombreBoton')}">
								<c:set var="nombreBoton" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('formaPago.url')}">
								<c:set var="url" value="has-error" />
							</c:if>
						
                        </spring:hasBindErrors>
					
                        <div class="form-group <c:out value = "${nombre}"/>" >
                           	<form:label path="formaPago.nombre" class="col-md-3 control-label">Nombre (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="formaPago.nombre" class="form-control" placeholder="Nombre" maxlength="255"/>
                            	<form:errors path="formaPago.nombre" class="form-error"/>
                           	</div>
                       	</div>
                         
                       	<div class="form-group <c:out value = "${descripcion}"/>">
                           	<form:label path="formaPago.descripcion" class="col-md-3 control-label">Descripción</form:label>
                        	<div class="col-md-9">
                               	<form:textarea path="formaPago.descripcion" class="form-control" placeholder="Descripción" rows="4" maxlength="255"/>
                               	<form:errors path="formaPago.descripcion" class="form-error"/>
                           	</div>
                        </div>
                       	
                       	<div class="form-group <c:out value = "${formula}"/>">
                           	<form:label path="formaPago.formula" class="col-md-3 control-label">Fómula (*)</form:label>
                        	<div class="col-md-9">
                               	<form:input path="formaPago.formula" class="form-control" placeholder="Fórmula" maxlength="1024"/>
                               	<form:errors path="formaPago.formula" class="form-error"/>
                           	</div>
                        </div>
                       	
                       	<div class="form-group">
                           	<form:label path="formaPago.comentario" class="col-md-3 control-label">Comentario</form:label>
                        	<div class="col-md-9">
                               	<form:textarea path="formaPago.comentario" class="form-control" placeholder="Comentario" rows="6" maxlength="2048"/>
                               	<form:errors path="formaPago.comentario" class="form-error"/>
                           	</div>
                        </div>
                       	
                       	<div class="form-group">
                           	<label for="formaPago.pasoAdicional1" class="col-md-3 control-label">Paso adicional</label>
                           	<div class="col-md-9">
                                <form:checkbox path="formaPago.pasoAdicional"/>
                           	</div>
                       	</div>
                        
                        <div class="form-group <c:out value = "${url}"/>">
                           	<form:label path="formaPago.url" class="col-md-3 control-label">URL</form:label>
                        	<div class="col-md-9">
                               	<form:input path="formaPago.url" class="form-control" placeholder="URL" maxlength="255"/>
                               	<form:errors path="formaPago.url" class="form-error"/>
                           	</div>
                        </div>
                        
                        <div class="form-group <c:out value = "${nombreBoton}"/>">
                           	<form:label path="formaPago.nombreBoton" class="col-md-3 control-label">Nombre del botón</form:label>
                        	<div class="col-md-9">
                               	<form:input path="formaPago.nombreBoton" class="form-control" placeholder="Nombre del botón" maxlength="255"/>
                               	<form:errors path="formaPago.nombreBoton" class="form-error"/>
                           	</div>
                        </div>
                       
                       	<div class="form-group">
                           	<label for="formaPago.test1" class="col-md-3 control-label">Test</label>
                           	<div class="col-md-9">
                                <form:checkbox path="formaPago.test"/>
                           	</div>
                       	</div>
                       	
                        <div class="form-group">
                            
                        	<div class="col-md-offset-2 col-xs-2 col-sm-1 col-md-2 col-lg-2">
								
								
								<c:if test = "${!formaPagoBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/forma-pago/forma-pago-find?"/><decocina:parametros busqueda='${formaPagoBuscador}'/>" class="btn btn-primary">Volver</a>
									
								</c:if>
								
								<c:if test = "${formaPagoBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/forma-pago/forma-pago-search?"/><decocina:parametros busqueda="${formaPagoBuscador}"/>" class="btn btn-primary">Volver</a>
								
								</c:if>
								
							</div>
							
							<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
								
								<c:if test = "${formaPagoForm.esCreacion}">
								
									<a id = "accion" href = "#" class="btn btn-success text-right">Crear</a>	
								
								</c:if>
								
								<c:if test = "${!formaPagoForm.esCreacion}">
									
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
						
					$("#formaPagoForm").submit();
						
				});
				
				$("#eliminar").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Forma de Pago</h4>',
			            message: 'Se eliminará la Forma de Pago ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Eliminar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#formaPagoForm").attr("action", "forma-pago-delete");
								$("#formaPagoForm").submit();
			                }
			            }
			        });
					
				});
				
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>