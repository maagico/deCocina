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
							
						Imagen del Artículo '<c:out value = "${articulo.nombre}"/>'
							
					</div>
					
				</div>
				
			</div>
			
			<div class="row">
				
				<div class ="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
					<ul class="nav nav-tabs" style = "margin-bottom:10px">
						<li role="presentation">
							<a href="<c:url value = "/admin/consola/articulo/articulo-modify?id=${imagenForm.idArticulo}&cat_id=${imagenForm.idCategoria}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}"/>">
								Artículo
							</a>
						</li>
						  
						<li role="presentation" class="active">
							<a href = "#">Imagen</a>
						</li>
						 
						<%--
						<li role="presentation" class="dropdown">
						 	<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
						    	Acciones <span class="caret"></span>
						    </a>
						    <ul class="dropdown-menu" role="menu">
						   		
						   		<li role="presentation"><a href="#">Campaña</a></li>
						
						 		<li role="presentation"><a href="#">Comparador</a></li>
						   		
						    </ul>
						</li>
						--%>
						
					</ul>
					
				</div>
				
				<div class ="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
					<c:if test = "${imagenForm.esCreacion}">
						
						<c:set var="accion" value="imagen-insert" />
					
					</c:if>
					
					<c:if test = "${!imagenForm.esCreacion}">
						
						<c:set var="accion" value="imagen-update" />
					
					</c:if>
					
					<form:form id = "imagenForm" action = "${accion}" modelAttribute="imagenForm" class="form-horizontal" role="form" acceptCharset="UTF-8" enctype="multipart/form-data">
					
						<decocina:input formulario="${imagenForm}"/>
						
						<div class="form-group">
						
                           	<label for="imagen" class="col-md-3 control-label">Imagen</label>
                        	
                        	<div class="col-md-9">
                        		
                        		<c:if test ="${imagenForm.imagen.id != null}">
                        		
                               		<div class="thumbnail">
                               			
                               			<img src = "<c:url value ="/${imagenForm.imagen.uri}"/>">
                               			
                               		</div>
                               	
                               	</c:if>
                               	
                               	<input type="file" id="mfImagen" name="mfImagen" class = "fileupload"/>
                               	<form:errors path="mfImagen" class="form-error"/>
                           		
                          	</div>
                          	
                        </div>
						
						<div class="form-group">
	                            
	                       <div class="col-xs-2 col-sm-1 col-md-1 col-md-offset-3 col-lg-1 col-md-offset-3 ">
								
								<a href="<c:url value = "/admin/consola/articulo/articulo-modify?id=${imagenForm.idArticulo}&cat_id=${imagenForm.idCategoria}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}"/>" class="btn btn-primary">
									Volver	
								</a>
												
							</div>
							
							<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
								
								<c:if test = "${imagenForm.esCreacion}">
								
									<a id = "accion" href = "#" class="btn btn-success">Subir</a>	
								
								</c:if>
								
								<c:if test = "${!imagenForm.esCreacion}">
									
									<a id = "eliminar" href = "#" class="btn btn-danger">Eliminar</a>
									<a id = "accion" href = "#"  class="btn btn-success">Modificar</a>
								
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
						
					$("#imagenForm").submit();
						
				});
				
				$("#eliminar").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Imagen</h4>',
			            message: 'Se eliminará la Imagen ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Eliminar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#imagenForm").attr("action", "imagen-delete");
								$("#imagenForm").submit();
			                }
			            }
			        });
					
				});
				
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>