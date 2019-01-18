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
							
						<c:if test = "${categoriaForm.esCreacion}">
							Creando Categoría
						</c:if>
						
						<c:if test = "${!categoriaForm.esCreacion}">
							Modificando Categoría
						</c:if>
						
					</div>
					
				</div>
				
			</div>
			
			<div class="row">
	
				<div class ="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
					<c:if test = "${categoriaForm.esCreacion}">
						
						<c:set var="accion" value="categoria-insert" />
					
					</c:if>
					
					<c:if test = "${!categoriaForm.esCreacion}">
						
						<c:set var="accion" value="categoria-update" />
					
					</c:if>
					
					<form:form id = "categoriaForm" action = "${accion}" modelAttribute="categoriaForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
					
						<decocina:input formulario="${categoriaForm}"/>
						
						<spring:hasBindErrors name="categoriaForm">
		                        
							<c:if test="${errors.hasFieldErrors('categoria.nombre')}">
								<c:set var="nombre" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('categoria.metaDescripcion')}">
								<c:set var="metaDescripcion" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('categoria.metaKeywords')}">
								<c:set var="metaKeywords" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('categoria.orden')}">
								<c:set var="orden" value="has-error" />
							</c:if>
						
                        </spring:hasBindErrors>
					
                        <div class="form-group <c:out value = "${nombre}"/>" >
                           	<form:label path="categoria.nombre" class="col-md-3 control-label">Nombre (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path = "categoria.nombre" class="form-control" placeholder="Nombre" maxlength="255"/>
                            	<form:errors path="categoria.nombre" class = "form-error"/>
                           	</div>
                       	</div>
                        
                        <div class="form-group <c:out value = "${metaDescripcion}"/>">
                           	<form:label path="categoria.metaDescripcion" class="col-md-3 control-label">Meta description (*)</form:label>
                        	<div class="col-md-9">
                               	<form:textarea path="categoria.metaDescripcion" class="form-control" placeholder="Meta Descripción" rows="4" maxlength="255"/>
                               	<form:errors path="categoria.metaDescripcion" class ="form-error"/>
                           	</div>
                        </div>
                         
                       	<div class="form-group <c:out value = "${metaKeywords}"/>">
                           	<form:label path="categoria.metaKeywords" class="col-md-3 control-label">Meta keywords (*)</form:label>
                        	<div class="col-md-9">
                               	<form:textarea path="categoria.metaKeywords" class="form-control" placeholder="Keywords" rows="4" maxlength="255"/>
                               	<form:errors path="categoria.metaKeywords" class ="form-error"/>
                           	</div>
                        </div>
                        
                        <div class="form-group">
                           	<form:label path="categoria.descripcion" class="col-md-3 control-label">Descripción</form:label>
                        		<div class="col-md-9">
                               	<form:textarea path="categoria.descripcion" class="form-control" placeholder="Descripción" rows="4" maxlength="1024"/>
                           	</div>
                        </div>
                         
                       	<div class="form-group <c:out value = "${orden}"/>">
                           	<form:label path="categoria.orden" class="col-md-3 control-label">Orden (*)</form:label>
                           	<div class="col-md-9">
                               	<form:input path = "categoria.orden" class="form-control" placeholder="Orden"/>
                               	<form:errors path="categoria.orden" class ="form-error"/>
                           	</div>
                       	</div>
                       	
                       	<c:if test = "${categoriaForm.esCreacion}">
                       		
                       		<form:hidden path="categoria.idPadre"/>
                       		
                       	</c:if>
                       	
                       	<c:if test = "${!categoriaForm.esCreacion}">
                       	
	                       	<div class="form-group">
	                           	<label for="nombre" class="col-md-3 control-label">URL</label>
	                           	<div class="col-md-9">
	                            	<form:input path = "categoria.urlAmigable" class="form-control" readonly="true"/>
	                           	</div>
	                       	</div>
	                    
	                    </c:if>
                       	
                       	<c:if test = "${!categoriaForm.esCreacion && profundidad > 2}">
                        	
                        	<div class="form-group">
                           		<form:label path="categoria.idPadre" class="col-md-3 control-label">Categoría padre</form:label>
                        		<div class="col-md-9">
	                            	<form:select path="categoria.idPadre" class = "form-control">
										<form:options items="${categoriasPadre}" itemValue="id" itemLabel="nombre"/>
									</form:select>   	
                           		</div>
                           	</div>
                        	
                        </c:if>
                       	
                       	<div class="form-group">
                           	<label for="categoria.activa1" class="col-md-3 control-label">Visible</label>
                           	<div class="col-md-9">
                                <form:checkbox path="categoria.activa"/>
                           	</div>
                       	</div>
                       	 
                        <div class="form-group">
                            
                        	<div class="col-md-offset-2 col-xs-2 col-sm-1 col-md-2 col-lg-2">
								
								<c:if test = "${!categoriaArticuloBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/categoria/categoria-find?"/><decocina:parametros busqueda='${categoriaArticuloBuscador}'/>" class="btn btn-primary">Volver</a>
									
								</c:if>
								
								<c:if test = "${categoriaArticuloBuscador.hayBusqueda}">
								
									<c:if test="${categoriaForm.pathVolver == '-1'}">
									
										<a href = "<c:url value = "/admin/consola/categoria/categoria-search?"/><decocina:parametros busqueda="${categoriaArticuloBuscador}"/>" class="btn btn-primary">Volver</a>
										
									</c:if>
									
									<c:if test="${categoriaForm.pathVolver != '-1'}">
									
										<a href = "<c:url value = "/admin/consola/categoria/categoria-find?"/><decocina:parametros busqueda="${categoriaArticuloBuscador}"/>" class="btn btn-primary">Volver</a>
										
									</c:if>
								
								</c:if>
								
							</div>
							
							<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
								
								<c:if test = "${categoriaForm.esCreacion}">
								
									<a id = "accion" href = "#" class="btn btn-success text-right">Crear</a>	
								
								</c:if>
								
								<c:if test = "${!categoriaForm.esCreacion}">
									
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
				
				<c:if test = "${categoriaForm.esCreacion}">
				
					$("#accion").click(function(e){
						
						e.preventDefault();
						
						$("#categoriaForm").submit();
						
					});
			
				</c:if>
			
				$("#accion").click(function(e){
					
					e.preventDefault();
					
					<c:if test = "${categoriaForm.esCreacion == false}">
						
						$("#categoriaForm").attr("action", "categoria-update");
						$("#categoriaForm").submit();
						
					</c:if>
				});
				
				<c:if test = "${categoriaForm.esCreacion == false}">
					
					$("#cat_activa_modal").click(function(e){
						
						e.preventDefault();
						
						$("#categoriaForm").submit();
						
					});
					
				</c:if>
				
				$("#eliminar").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Categoría</h4>',
			            message: 'Se eliminará la Categoría ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Eliminar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#categoriaForm").attr("action", "categoria-delete");
								$("#categoriaForm").submit();
			                }
			            }
			        });
					
				});
				
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>