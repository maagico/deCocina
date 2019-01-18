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
							
						<c:if test = "${articuloForm.esCreacion}">
							Creando Artículo
						</c:if>
						
						<c:if test = "${!articuloForm.esCreacion}">
							Modificando Artículo '<c:out value = "${articuloForm.articulo.nombre}"/>'
						</c:if>
							
					</div>
					
				</div>
				
			</div>
			
			<div class="row">
				
				<div class ="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					
					<ul class="nav nav-tabs" style = "margin-bottom:10px">
					  
					  <li role="presentation" class="active"><a href="#">Artículo</a></li>
						
						<c:if test = "${!articuloForm.esCreacion}">
							
							<li role="presentation">
								<a href="<c:url value = "/admin/consola/imagen/imagen-modify?id=${articuloForm.articulo.imagenG.id}&art_id=${articuloForm.articulo.id}&cat_id=${articuloForm.articulo.categoria.id}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}"/>">
							  		Imagen
							  	</a>
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
							  
						</c:if>
						
					</ul>
					
				</div>
				
				<div class ="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
					<c:if test = "${articuloForm.esCreacion}">
						
						<c:set var="accion" value="articulo-insert" />
					
					</c:if>
					
					<c:if test = "${!articuloForm.esCreacion}">
						
						<c:set var="accion" value="articulo-update" />
					
					</c:if>
					
					<form:form id = "articuloForm" action = "${accion}" modelAttribute="articuloForm" class="form-horizontal" role="form" acceptCharset="UTF-8" enctype="multipart/form-data">
					
						<decocina:input formulario="${articuloForm}"/>
						
						<form:hidden path="articulo.categoria.id"/>   
						<form:hidden path="articulo.idTwitter"/>   
						
						<spring:hasBindErrors name="articuloForm">
		                        
							<c:if test="${errors.hasFieldErrors('articulo.nombre')}">
								<c:set var="nombre" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('articulo.metaDescripcion')}">
								<c:set var="metaDescripcion" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('articulo.metaKeywords')}">
								<c:set var="metaKeywords" value="has-error" />
							</c:if>							
							
							<c:if test="${errors.hasFieldErrors('articulo.descripcionCorta')}">
								<c:set var="descripcionCorta" value="has-error" />
							</c:if>	
							
							<c:if test="${errors.hasFieldErrors('articulo.impuesto')}">
								<c:set var="impuesto" value="has-error" />
							</c:if>	
							
							<c:if test="${errors.hasFieldErrors('articulo.precio')}">
								<c:set var="precio" value="has-error" />
							</c:if>	
							
							<c:if test="${errors.hasFieldErrors('articulo.peso')}">
								<c:set var="peso" value="has-error" />
							</c:if>	
							
							<c:if test="${errors.hasFieldErrors('articulo.cantidad')}">
								<c:set var="cantidad" value="has-error" />
							</c:if>	
							
							<c:if test="${errors.hasFieldErrors('articulo.orden')}">
								<c:set var="orden" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('articulo.visitas')}">
								<c:set var="visitas" value="has-error" />
							</c:if>	
							
							<c:if test="${errors.hasFieldErrors('imagen')}">
								<c:set var="imagen" value="has-error" />
							</c:if>		
						
                        </spring:hasBindErrors>
					
						<div class="form-group <c:out value = "${fecha}"/>">
                           	<form:label path="articulo.fecha" class="col-md-3 control-label">Fecha (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="articulo.fecha" class="form-control" placeholder="Fecha"/>
                            	<form:errors path="articulo.fecha" class="form-error"/>
                           	</div>
                       	</div>
                    	
                        <div class="form-group <c:out value = "${nombre}"/>">
                           	<form:label path="articulo.nombre" class="col-md-3 control-label">Nombre (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="articulo.nombre" class="form-control" placeholder="Nombre" maxlength="99"/>
                            	<form:errors path="articulo.nombre" class="form-error"/>
                           	</div>
                       	</div>
                        
                        <div class="form-group <c:out value = "${metaDescripcion}"/>">
                           	<form:label path="articulo.metaDescripcion" class="col-md-3 control-label">Meta Descripción (*)</form:label>
                           	<div class="col-md-9">
                            	<form:textarea path="articulo.metaDescripcion" class="form-control" placeholder="Meta Descripción" maxlength="256" rows="5"/>
                            	<form:errors path="articulo.metaDescripcion" class="form-error"/>
                           	</div>
                       	</div>
                       	
                       	<div class="form-group <c:out value = "${metaKeywords}"/>">
                           	<form:label path="articulo.metaKeywords" class="col-md-3 control-label">Meta Keywords (*)</form:label>
                           	<div class="col-md-9">
                            	<form:textarea path="articulo.metaKeywords" class="form-control" placeholder="Meta Keywords" maxlength="256"/>
                            	<form:errors path="articulo.metaKeywords" class="form-error"/>
                           	</div>
                       	</div>
                        
                        <div class="form-group <c:out value = "${descripcionCorta}"/>">
                           	<form:label path="articulo.descripcionCorta" class="col-md-3 control-label">Descripción corta (*)</form:label>
                        	<div class="col-md-9">
                               	<form:textarea path="articulo.descripcionCorta" class="form-control" placeholder="Descripción corta" rows="4" maxlength="214"/>
                               	<form:errors path="articulo.descripcionCorta" class="form-error"/>
                           	</div>
                        </div>
                        
                       	<div class="form-group">
                           	<form:label path="articulo.descripcion" class="col-md-3 control-label">Descripción</form:label>
                        	<div class="col-md-9">
                               	<form:textarea cssClass="tinyMCE" path="articulo.descripcion" class="form-control" placeholder="Descripción" rows="10" maxlength="255"/>
                               	<form:errors path="articulo.descripcion" class="form-error"/>
                           	</div>
                        </div>
                      
                      	<c:if test = "${!articuloForm.esCreacion}">
                      	
	                        <div class="form-group">
	                           	<label for="imagen" class="col-md-3 control-label">Imagen</label>
	                        	<div class="col-md-9">
	                        		
	                        		<c:if test ="${articuloForm.articulo.imagenG.id == null}">
	                               		Sin imagen
	                               	</c:if>
	                        		
	                        		<c:if test ="${articuloForm.articulo.imagenG.id != null}">
	                               		<div class="thumbnail">
	                               			<img src = "<c:url value ="/${articuloForm.articulo.imagenG.uri}"/>" width = "100" height = "100"/>
	                               			<form:hidden path="articulo.imagenG.uri"/>
	                               		</div>
	                               	</c:if>
	                               	
	                          	</div>
	                          	
	                        </div>
	                        
                        </c:if>
                        
                        <div class="form-group">
                           	<form:label path="articulo.fabricante.id" class="col-md-3 control-label">Fabricante</form:label>
                        	<div class="col-md-9">
                            	<form:select path="articulo.fabricante.id" class = "form-control">
								   <form:option value="" label="Selecciona un fabricante..."/>
								   <form:options items="${fabricantes}" itemValue="id" itemLabel="nombre"/>
								</form:select>   	
                           	</div>
                        </div>
                        
                        <div class="form-group <c:out value = "${impuesto}"/>">
                           	<form:label path="articulo.impuesto.id" class="col-md-3 control-label">Impuesto (*)</form:label>
                        	<div class="col-md-9">
                            	<form:select path="articulo.impuesto.id" class = "form-control">
								   <form:option value="" label="Selecciona un impuesto..."/>
								   <form:options items="${impuestos}" itemValue="id" itemLabel="nombre"/>
								</form:select> 
								<form:errors path="articulo.impuesto" class="form-error"/>
                           	</div>
                        </div>
                       	 
                       	<div class="form-group <c:out value = "${precio}"/>" >
                           	<form:label path="articulo.precio" class="col-md-3 control-label">Precio (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="articulo.precio" class="form-control" placeholder="Precio"/>
                            	<form:errors path="articulo.precio" class="form-error"/>
                           	</div>
                       	</div>
                       	
                       	<div class="form-group <c:out value = "${peso}"/>" >
                           	<form:label path="articulo.peso" class="col-md-3 control-label">Peso (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="articulo.peso" class="form-control" placeholder="Peso"/>
                            	<form:errors path="articulo.peso" class="form-error"/>
                           	</div>
                       	</div>
                       	
                       	<div class="form-group <c:out value = "${cantidad}"/>" >
                           	<form:label path="articulo.cantidad" class="col-md-3 control-label">Cantidad (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="articulo.cantidad" class="form-control" placeholder="Cantidad"/>
                            	<form:errors path="articulo.cantidad" class="form-error"/>
                           	</div>
                       	</div>
                       	
                       	<div class="form-group <c:out value = "${orden}"/>" >
                           	<form:label path="articulo.orden" class="col-md-3 control-label">Orden (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="articulo.orden" class="form-control" placeholder="Orden"/>
                            	<form:errors path="articulo.orden" class="form-error"/>
                           	</div>
                       	</div>
                       	
                       	<div class="form-group <c:out value = "${visitas}"/>" >
                           	<form:label path="articulo.visitas" class="col-md-3 control-label">Visitas (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="articulo.visitas" class="form-control" placeholder="Visitas" readonly="true"/>
                            	<form:errors path="articulo.visitas" class="form-error"/>
                           	</div>
                       	</div>
                       	
                       	<c:if test = "${!articuloForm.esCreacion}">
                       	
	                       	<div class="form-group">
	                           	<label for="nombre" class="col-md-3 control-label">URL</label>
	                           	<div class="col-md-9">
	                            	<form:input path = "articulo.urlAmigable" class="form-control" readonly="true"/>
	                           	</div>
	                       	</div>
	                    
	                    </c:if>
                       	
                       	<%-- 
                       	<div class="form-group">
                           	<label for="articulo.destacado1" class="col-md-3 control-label">Destacado</label>
                           	<div class="col-md-9">
                                <form:checkbox path="articulo.destacado"/>
                           	</div>
                       	</div>
                       	--%>
                       	
                       	<c:if test = "${!articuloForm.esCreacion}">
	                       	
	                       	<div class="form-group">
	                           	
	                           	<label for="articulo.activo1" class="col-md-3 control-label">Visible</label>
	                           	
	                           	<div class="col-md-9">
	                          		
	                          		<c:set var = "esDisabled" value="${articuloForm.esCreacion || articuloForm.articulo.imagenT.id == null}" />
	                          		
                          			<form:checkbox path="articulo.activo" disabled="${esDisabled}"/>
                                
                                	<c:if test = "${esDisabled}">
                                		No se puede hacer visible hasta que no se añada una imagen
                                	</c:if>
                                	
	                        	</div>
	                        	
	                       	</div>
	                       	
                       	</c:if>
                       	
                       	<c:if test = "${articuloForm.esCreacion}">
	                                
                        	<form:hidden path="articulo.activo"/>
                         
                        </c:if>
                       	                       
                       	<div class="form-group">
                            
                        	<div class="col-md-offset-2 col-xs-2 col-sm-1 col-md-2 col-lg-2">
								
								<c:if test = "${categoriaArticuloBuscador.desdeRaiz}">
									<a href = "<c:url value = "/admin/consola/categoria/categoria-search?"/><decocina:parametros busqueda='${categoriaArticuloBuscador}'/>" class="btn btn-primary">
										Volver	
									</a>
								</c:if>	
								
								<c:if test = "${!categoriaArticuloBuscador.desdeRaiz && !categoriaArticuloBuscador.desdeInicio}">
									<a href = "<c:url value = "/admin/consola/categoria/categoria-find?"/><decocina:parametros busqueda='${categoriaArticuloBuscador}'/>" class="btn btn-primary">
										Volver	
									</a>
								</c:if>
								
								<c:if test = "${categoriaArticuloBuscador.desdeInicio}">
									<a href = "<c:url value = "/admin/consola/inicio"/>" class="btn btn-primary">
										Volver	
									</a>
								</c:if>
								
											
							</div>
							
							<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
								
								<c:if test = "${articuloForm.esCreacion}">
								
									<a id = "accion" href = "#" class="btn btn-success text-right">Crear</a>	
								
								</c:if>
								
								<c:if test = "${!articuloForm.esCreacion}">
									
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
				
				tinyMCE.init({
					mode : "textareas",
					editor_selector : "tinyMCE",
					menubar : false
				});
	
				$("#accion").click(function(e){
						
					e.preventDefault();
					
					setDescripcion();
					
					$("#articuloForm").submit();
						
				});
				
				$("#eliminar").click(function(e){
					
					e.preventDefault();
					
					setDescripcion();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Artículo</h4>',
			            message: 'Se eliminará el Artículo ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Eliminar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                	
			                	$("#articuloForm").attr("action", "articulo-delete");
								$("#articuloForm").submit();
			                }
			            }
			        });
					
				});
				
				function setDescripcion(){
					
					var descripcion = tinyMCE.get("articulo.descripcion").getContent();
					
					$("#articulo\\.descripcion").val(descripcion);
				}
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>