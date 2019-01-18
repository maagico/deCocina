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
							
						<c:if test = "${zonaForm.esCreacion}">
							Creando Zona
						</c:if>
						
						<c:if test = "${!zonaForm.esCreacion}">
							Modificando Zona
						</c:if>
							
					</div>
					
				</div>
				
			</div>
			
			<div class="row">
	
				<div class ="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
					<c:if test = "${zonaForm.esCreacion}">
						
						<c:set var="accion" value="zona-insert" />
					
					</c:if>
					
					<c:if test = "${!zonaForm.esCreacion}">
						
						<c:set var="accion" value="zona-update" />
					
					</c:if>
					
					<form:form id = "zonaForm" action = "${accion}" modelAttribute="zonaForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
					
						<decocina:input formulario="${zonaForm}"/>
						
						<spring:hasBindErrors name="zonaForm">
		                        
							<c:if test="${errors.hasFieldErrors('zona.nombre')}">
								<c:set var="nombre" value="has-error" />
							</c:if>
						
                        </spring:hasBindErrors>
					
						<div class="form-group <c:out value = "${nombre}"/>" >
                           	<form:label path="zona.nombre" class="col-md-3 control-label">Nombre (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="zona.nombre" class="form-control" placeholder="Nombre" maxlength="255"/>
                            	<form:errors path="zona.nombre" class="form-error"/>
                           	</div>
                       	</div>
					
                        <div class="form-group">
                           	<label for="zona.activa1" class="col-md-3 control-label">Activa</label>
                           	<div class="col-md-9">
                                <form:checkbox path="zona.activa"/>
                           	</div>
                       	</div>
                       	
                       	<c:if test = "${!zonaForm.esCreacion}">
                       	
                       	<div class="form-group" >
                           	<form:label path="zona.zonaPadre.id" class="col-md-3 control-label">Zona Padre</form:label>
                           	<div class="col-md-9">
                           		
                           		<div class = "zona-margin-top">
                           			
                           			<c:if test = "${zonaForm.zona.zonaPadre.id == null}">
                           				Sin padre asignado
                           			</c:if>
                           			<c:if test = "${zonaForm.zona.zonaPadre.id != null}">
                           				<c:out value = "${zonaForm.zona.zonaPadre.nombre}"/>
                           			</c:if>
                           			
                           			<BR>
                           			
                           			<a id = "asignar_padre" href = "#">Asignar Padre</a> 
                           			
                           			<c:if test = "${zonaForm.zona.zonaPadre.id != null}">
                           				
                           				<a href = "<c:url value = "/admin/consola/zona/zona-padre-insert?id=${zonaForm.zona.id}&"/><decocina:parametros busqueda="${zonaBuscador}"/>" class = "a-desasignar-padre">Desasignar Padre</a>
                           			
                           			</c:if>
                           		</div>
                           		
                            	<form:hidden path="zona.zonaPadre.id" class="form-control"/>
                           	</div>
                       	</div>
                       	
                       	
                       	<div class="form-group">
                           	<label for="zona.heredarFormas1" class="col-md-3 control-label">Heredar Formas</label>
                           	<div class="col-md-9">
                                <form:checkbox path="zona.heredarFormas" disabled="${zonaForm.zona.zonaPadre.id == null}" value = "true"/> Heredar Impuestos, Formas de Envío y Pago
                           	</div>
                       	</div>
	                   
                       	
                       	<div class="row">
                       		<div class="col-xs-2 col-sm-12 col-md-2 col-lg-2">
							</div>
                       		<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 impuestos-margin"><strong>Impuestos</strong>
							</div>
                       	</div>
                       	
                       	<div class="row">
				
							<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
							</div>
							<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
								<table class="table table-condensed">
									<thead>
										<tr>
											<th>Nombre</th>	
								        	<th>Valor</th>		
								        	<th></th>					        	
								      	</tr>
								    </thead>
								    <tbody>
								    
								    	<c:forEach var="zonaImpuesto" items="${zonaForm.zona.zonaImpuestos}">
					
									    	<tr>
									        	<td>
									        		<c:out value = "${zonaImpuesto.impuesto.nombre}"/>
									        	</td>
									        	<td>
									        		<c:out value = "${zonaImpuesto.impuesto.valor}"/>
									        	</td>
									        	<td>
									        		<c:if test="${!zonaForm.zona.heredarFormas}">
							        		
									        			<a href = "#" onClick = "javascript:borrarImpuesto(${zonaImpuesto.id},${zonaForm.zona.id});" class="btn btn-default btn-sm">
									        				<i class="glyphicon glyphicon-remove-circle"></i>
									        			</a>
									        			
									        		</c:if>
									        	</td>
									        </tr>
									   
								        </c:forEach>
								        <tr>
								        	<c:if test="${!zonaForm.zona.heredarFormas}">
								        	
								        		<td colspan = "3"><a id = "add_impuesto" href = "#">Añadir Impuesto</a></td>
								        	
								        	</c:if>
								        </tr>
								    </tbody>
								</table>
								
							</div>
				
						</div>
						
                       	<div class="row">
				
							<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
							</div>
							<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
								<table class="table table-condensed">
									<thead>
										<tr>
											<th>Forma de Envío</th>	
											<th></th>		
								        	<th>Forma de Pago</th>		
								        	<th></th>					        	
								      	</tr>
								    </thead>
								    <tbody>
								    
								    	
								    	<c:forEach var="zonaFormaEnvio" items="${zonaForm.zona.zonaFormasEnvio}">
										
									    	<tr>
									        	<td>
									        		<c:out value ="${zonaFormaEnvio.formaEnvio.nombre}"/>
									       		
									       		</td>
									        	<td>
									        		<c:if test="${!zonaForm.zona.heredarFormas}">
									        			
									        			<a href = "#" onClick = "javascript:borrarFormaEnvio(${zonaFormaEnvio.formaEnvio.id}, ${zonaForm.zona.id});" class="btn btn-default btn-sm">    			
									        				<i class="glyphicon glyphicon-remove-circle"></i>
									        			</a>
									        				
									        		</c:if>
									        	</td>
									        	
									        	<td>
									        		
									        		<table class="table table-condensed tabla-formas-pago-border">
									    	   			<c:forEach var="formaEnvioFormaPago" items="${zonaFormaEnvio.formaEnvio.formaEnvioFormasPago}">
										        			<tr>
											       				<td>
											       					<c:out value ="${formaEnvioFormaPago.formaPago.nombre}"/>
										        				</td>
											        			<td class = "text-right">
											        				
											        				<c:if test="${!zonaForm.zona.heredarFormas}">
											        					
											        					<a href = "#" onClick = "javascript:borrarFormaPago(${formaEnvioFormaPago.id}, ${zonaForm.zona.id});" class="btn btn-default btn-sm">
												        					<i class="glyphicon glyphicon-remove-circle"></i>
												        				</a>
												        				
												        			</c:if>
												        			
											        			</td>
										        			</tr>
									        			</c:forEach>
									        			<tr>
									        				<c:if test="${!zonaForm.zona.heredarFormas}">
									        					
									        					<td colspan = "2"><a id = "add_forma_pago" href = "#" onclick = "javascript:addFormaPago(${zonaFormaEnvio.formaEnvio.id});">Añadir Forma de Pago</a></td>
									        				
									        				</c:if>
									        			</tr>
									        		</table>
									        		
									        	</td>
									        	
									        	<td>
									        	</td>
									        </tr>
									   
								        </c:forEach>
								        
								        <tr>
								        	<c:if test="${!zonaForm.zona.heredarFormas}">
								        	
								        		<td colspan = "3"><a id = "add_forma_envio" href = "#">Añadir Forma de Envío</a></td>
								        	
								        	</c:if>
								        </tr>
								    </tbody>
								</table>
								
							</div>
							</div>
                       	
                       	</c:if> 
                       	
                        <div class="form-group">
                            
                        	<div class="col-md-offset-2 col-xs-2 col-sm-1 col-md-2 col-lg-2">
								
								
								<c:if test = "${!zonaBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/zona/zona-find?"/><decocina:parametros busqueda='${zonaBuscador}'/>" class="btn btn-primary">Volver</a>
									
								</c:if>
								
								<c:if test = "${zonaBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/zona/zona-search?"/><decocina:parametros busqueda="${zonaBuscador}"/>" class="btn btn-primary">Volver</a>
								
								</c:if>
								
							</div>
							
							<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
								
								<c:if test = "${zonaForm.esCreacion}">
								
									<a id = "accion" href = "#" class="btn btn-success text-right">Crear</a>	
								
								</c:if>
								
								<c:if test = "${!zonaForm.esCreacion}">
									
									<a id = "eliminar" href = "#" class="btn btn-danger">Eliminar</a>
									<a id = "accion" href = "#"  class="btn btn-success text-right">Modificar</a>
								
								</c:if>
								
							</div>
					
					 	</div>
					            
					                      	 
					</form:form>
				   	   
				</div>
			</div>
			
			<div id = "modal_advertencia_eliminar" class="modal">
				
				<div class="modal-dialog">
			    	
			    	<div class="modal-content">
			      		
			      		<div class="modal-header">
			        		
			        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        		<h4 class="modal-title">zona</h4>
			      		
			      		</div>
			      
			      		<div class="modal-body">
			        		
			        		<p>Se eliminará la Zona. ¿Continuar? </p>
			      		
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
			
			var dialogZonas;
		
			$(document).ready(function() {
				
				$("#accion").click(function(e){
						
					e.preventDefault();
						
					$("#zonaForm").submit();
						
				});
				
				$("#eliminar").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Zona</h4>',
			            message: 'Se eliminará la Zona ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Eliminar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
								$("#zonaForm").attr("action", "zona-delete");
								$("#zonaForm").submit();
			                }
			            }
			        });
					
				});
				
				$("#asignar_padre").click(function(e){
				
					dialogZonas = new BootstrapDialog();
					dialogZonas.setTitle('<h4>Zonas</h4>');
					dialogZonas.setType(BootstrapDialog.TYPE_SUCCESS);
			        
					dialogZonas.setButtons([{
		                label: 'Cancelar',
		                action: function(dialog){
		                	
		                	dialog.close();
		                }
		            }]);
			        
					dialogZonas.setMessage($('<div></div>').load('<c:url value = "/admin/consola/zona/zona-find-popup"/>?id=${zonaForm.zona.id}&<decocina:parametros busqueda="${zonaBuscador}"/>'));
			        
					dialogZonas.open();
					
				});
				
				$("#add_impuesto").click(function(e){
					
					var dialogImpuestos = new BootstrapDialog();
			        
					dialogImpuestos.setTitle('<h4>Impuestos</h4>');
					dialogImpuestos.setType(BootstrapDialog.TYPE_SUCCESS);
			        
					dialogImpuestos.setButtons([{
		                label: 'Cancelar',
		                action: function(dialog){
		                	
		                	dialog.close();
		                }
		            }]);
			        
					dialogImpuestos.setMessage($('<div></div>').load('<c:url value = "/admin/consola/impuesto/impuesto-find-popup"/>?id=${zonaForm.zona.id}&<decocina:parametros busqueda="${zonaBuscador}"/>'));
			        
					dialogImpuestos.open();
					
				});
				
				$("#add_forma_envio").click(function(e){
					
					var dialogImpuestos = new BootstrapDialog();
			        
					dialogImpuestos.setTitle('<h4>Formas de Envío</h4>');
					dialogImpuestos.setType(BootstrapDialog.TYPE_SUCCESS);
			        
					dialogImpuestos.setButtons([{
		                label: 'Cancelar',
		                action: function(dialog){
		                	
		                	dialog.close();
		                }
		            }]);
			        
					dialogImpuestos.setMessage($('<div></div>').load('<c:url value = "/admin/consola/forma-envio/forma-envio-find-popup"/>?id=${zonaForm.zona.id}&<decocina:parametros busqueda="${zonaBuscador}"/>'));
			        
					dialogImpuestos.open();
					
				});
			});
			
			function addFormaPago(idFormaEnvio){
				
				
				var dialogImpuestos = new BootstrapDialog();
		        
				dialogImpuestos.setTitle('<h4>Formas de Pago</h4>');
				dialogImpuestos.setType(BootstrapDialog.TYPE_SUCCESS);
		        
				dialogImpuestos.setButtons([{
	                label: 'Cancelar',
	                action: function(dialog){
	                	
	                	dialog.close();
	                }
	            }]);
		        
				dialogImpuestos.setMessage($('<div></div>').load('<c:url value = "/admin/consola/forma-pago/forma-pago-find-popup"/>?id=${zonaForm.zona.id}&forma_envio_id='+idFormaEnvio+'&<decocina:parametros busqueda="${zonaBuscador}"/>'));
		        
				dialogImpuestos.open();
			}
			
			function borrarImpuesto(id, idZona){
				
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
		                	
		                	document.location.href = '<c:url value = "/admin/consola/zona/zona-impuesto-delete?"/>id='+id+'&zona_id='+idZona+'&<decocina:parametros busqueda="${zonaBuscador}"/>';
		                }
		            }
		        });
				
			}
			
			function borrarFormaEnvio(id, idZona){
				
				BootstrapDialog.confirm({
					
					title: '<h4>Impuesto</h4>',
		            message: 'Se eliminará la Forma de Envío y sus Formas de Pago ¿Continuar?',
		            type: BootstrapDialog.TYPE_WARNING,
		            closable: true,
		            draggable: true,
		            btnCancelLabel: 'Cancelar',
		            btnOKLabel: 'Eliminar',
		            btnOKClass: 'btn-primary', 
		            callback: function(result) {
		                
		                if(result) {
		                	
		                	document.location.href = '<c:url value = "/admin/consola/zona/zona-forma-envio-delete?"/>id='+id+'&zona_id='+idZona+'&<decocina:parametros busqueda="${zonaBuscador}"/>';
		                }
		            }
		        });
			}
			
			function borrarFormaPago(id, idZona){
				
				BootstrapDialog.confirm({
					
					title: '<h4>Impuesto</h4>',
		            message: 'Se eliminará la Forma de Pago ¿Continuar?',
		            type: BootstrapDialog.TYPE_WARNING,
		            closable: true,
		            draggable: true,
		            btnCancelLabel: 'Cancelar',
		            btnOKLabel: 'Eliminar',
		            btnOKClass: 'btn-primary', 
		            callback: function(result) {
		                
		                if(result) {
		                	
		                	document.location.href = '<c:url value = "/admin/consola/zona/zona-forma-pago-delete?"/>id='+id+'&zona_id='+idZona+'&<decocina:parametros busqueda="${zonaBuscador}"/>';
		                }
		            }
		        });
			}
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>