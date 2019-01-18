<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>

<tiles:insertDefinition name="template-central-admin">

    <tiles:putAttribute name="body-admin">
			
			<div class = "row">
				
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
					
					<div class="alert alert-info" role="alert">
							
						<c:if test = "${!pedidoForm.esCreacion}">
							Modificando Pedido
						</c:if>
							
					</div>
					
				</div>
				
			</div>
			
			<div class="row">
	
				<div class ="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					
					<c:if test = "${!pedidoForm.esCreacion}">
						
						<c:set var="accion" value="pedido-update" />
					
					</c:if>
					
					<form:form id = "pedidoForm" action = "${accion}" modelAttribute="pedidoForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
					
						<decocina:input formulario="${pedidoForm}"/>
						
						<form:hidden path="pedido.id"/>   
						
						<spring:hasBindErrors name="pedidoForm">
		                        
							<%-- 
							<c:if test="${errors.hasFieldErrors('cliente.nombre')}">
								<c:set var="nombre" value="has-error" />
							</c:if>
							--%>
												
                        </spring:hasBindErrors>
						
						<div class="panel panel-default">
				
							<div class="panel-heading">
							    <h3 class="panel-title">Dirección de entrega</h3>
							</div>
							
							<div class="panel-body">
								<div class = "panel-realizar-pedido">
				
									<c:out value = "${pedidoForm.pedido.nombreCliente}"/>					
				
								</div>
								<div class = "panel-realizar-pedido">
				
									<c:out value = "${pedidoForm.pedido.calle}"/>						
				
								</div>
								<div class = "panel-realizar-pedido">
				
									<c:out value = "${pedidoForm.pedido.poblacion}"/>						
				
								</div>
								<div class = "panel-realizar-pedido">
				
									<c:out value = "${pedidoForm.pedido.codigoPostal}"/> <c:out value = "${pedidoForm.pedido.zona}"/> España
				
								</div>
								<div class = "panel-realizar-pedido">
								&nbsp;
								</div>
								
								<div class = "panel-realizar-pedido">
									<c:out value = "${pedidoForm.pedido.telefono}"/>	
								</div>
								<div class = "panel-realizar-pedido">
									<c:out value = "${pedidoForm.pedido.email}"/>	
								</div>
							</div>
							
						</div>
						
						<c:if test = "${pedidoForm.pedido.comentarioFormaPago != ''}">
						
							<div id = "informacion-pago" class="panel panel-default">
					
								<div class="panel-heading">
								    <h3 class="panel-title">Información del pago</h3>
								</div>
								
								<div class="panel-body">
									<div>
					
										<c:out value = "${pedidoForm.pedido.comentarioFormaPago}" escapeXml="false"/>					
					
									</div>
								</div>	
								
							</div>
									
						</c:if>
						
						<c:if test = "${pedidoForm.pedido.observaciones != ''}">
						
							<div id = "observaciones" class="panel panel-default">
					
								<div class="panel-heading">
								    <h3 class="panel-title">Observaciones</h3>
								</div>
								
								<div class="panel-body">
									<div>
					
										<c:out value = "${pedidoForm.pedido.observaciones}"/>					
					
									</div>
								</div>	
								
							</div>
									
						</c:if>
						
						<div id = "articulos" class="panel panel-default">
				
							<div class="panel-heading">
							    <h3 class="panel-title">Artículos</h3>
							</div>
							
							<div class="panel-body">
								
								<div class="list-group">
					  
					  				<table class="table table-condensed">
										<thead>
											<tr>
												<th></th>	
												<th class = "hidden-xs"></th>	
												<th class = "hidden-xs text-center">Impuestos</th>
												<th width = "100px"></th>
												<th></th>					        	
									      	</tr>
									    </thead>
									    <tbody>
									    	<c:forEach var = "articulo" items = "${articulos}">
									    	
									    	<tr>
								    			<td colspan ="4" class = "visible-xs">
								    				<c:out value = "${articulo.nombre}"/>
								    			</td>
								    		</tr>
									    	
									    	<tr>
									    		<td>
									    			<c:if test = "${articulo.uri != null && articulo.uri != ''}">
										    			<img class = "thumbnail" src = "<c:url value="/${articulo.uri}"/>" width = "100" height = "100">
										        	</c:if>
										        </td>
										    	<td class = "hidden-xs">
										    		<c:out value = "${articulo.cantidad}"/> x <c:out value = "${articulo.nombre}"/>
										        </td>
										        <td class = "hidden-xs text-center">
										        	<c:out value="${articulo.iva}"/>%
										        </td>
										        <td class = "text-right">
										        	<c:out value = "${articulo.cantidad}"/> x <strong><c:out value="${articulo.precio}"/>€</strong>
										        </td>
										        <td>
										        	<span class = "color-precio-articulo"><c:out value="${articulo.precioTotal}"/>€</span>
										        </td>
										    </tr>
										    </c:forEach>
										    <tr>
										    	<td>
										    	</td>
										    	<td class = "hidden-xs">
										    	</td>
										    	<td class = "hidden-xs">
										    	</td>
										    	<td class = "text-right">
										    		<strong>subtotal:</strong>
										    	</td>
										    	<td>
										    		<span class = "color-precio-articulo"><c:out value="${pedidoForm.pedido.precioTotalCesta}"/>€</span>
										    	</td>
										    </tr>
										    <tr>
										    	<td>
										    	</td>
										    	<td class = "hidden-xs">
										    	</td>
										    	<td class = "hidden-xs">
										    	</td>
										    	<td class = "text-right">
										    		&nbsp;
										    	</td>
										    	<td>
										    		
										    	</td>
										    </tr>
										    <tr class = "hidden-xs">
										    	
										    	<td colspan = "4" class = "text-right">
										    		<c:out value = "${pedidoForm.pedido.nombreFormaEnvio}"/>:
										    	</td>
										    	
										    	<td>
										    		 <span class = "color-precio-articulo"><c:out value="${pedidoForm.pedido.precioFormaEnvio}"/>€</span>
										    	</td>
										    </tr>
										    <tr class = "visible-xs">
										    	
										    	<td colspan = "2" class = "text-right">
										    		<c:out value = "${pedidoForm.pedido.nombreFormaEnvio}"/>:
										    	</td>
										    	
										    	<td>
										    		 <span class = "color-precio-articulo"><c:out value="${pedidoForm.pedido.precioFormaEnvio}"/>€</span>
										    	</td>
										    </tr>
										    
										    <tr class = "hidden-xs">
										    	
										    	<td colspan = "4" class = "text-right">
										    		<c:out value = "${pedidoForm.pedido.nombreFormaPago}"/>:
										    	</td>
										    	
										    	<td>
										    		 <span class = "color-precio-articulo"><c:out value="${pedidoForm.pedido.precioFormaPago}"/>€</span>
										    	</td>
										    </tr>
										    <tr class = "visible-xs">
										    	
										    	<td colspan = "2" class = "text-right">
										    		<c:out value = "${pedidoForm.pedido.nombreFormaPago}"/>:
										    	</td>
										    	
										    	<td>
										    		 <span class = "color-precio-articulo"><c:out value="${pedidoForm.pedido.precioFormaPago}"/>€</span>
										    	</td>
										    </tr>
										    <tr class = "hidden-xs">
												
												<td colspan = "4" class = "text-right">
													<c:forEach var = "ivaTotal" items = "${ivasTotal}">
														(<c:out value = "${ivaTotal}"/>)
													  	<BR>
													</c:forEach>
												</td>
										    	<td>
										    	</td>
										    	
										  	</tr>
										  	<tr class = "visible-xs">
												
												<td colspan = "2" class = "text-right">
													<c:forEach var = "ivaTotal" items = "${ivasTotal}">
														(<c:out value = "${ivaTotal}"/>)
													  	<BR>
													</c:forEach>
												</td>
										    	<td>
										    	</td>
										    	
										  	</tr>
										  	
										  	<tr class = "hidden-xs">
										    	
										    	<td colspan = "4" class = "text-right">
										    		Total:
										    	</td>
										    	
										    	<td>
										    		 <span class = "color-precio-articulo"><c:out value="${pedidoForm.pedido.precioTotal}"/>€</span>
										    	</td>
										    </tr>
										    <tr class = "visible-xs">
										    	
										    	<td colspan = "2" class = "text-right">
										    		Total:
										    	</td>
										    	
										    	<td>
										    		 <span class = "color-precio-articulo"><c:out value="${pedidoForm.pedido.precioTotal}"/>€</span>
										    	</td>
										    </tr>
										</tbody>
									</table>
					  				
								</div>
								
							</div>
							
						</div>
						
						<div id = "estados" class="panel panel-default">
					
								<div class="panel-heading">
								    <h3 class="panel-title">Estados</h3>
								</div>
								
								<div class="panel-body">
								
									<table class="table table-condensed">
										<thead>
											<tr>
												<th class = "hidden-xs">Fecha</th>
												<th>Estado</th>
												<th>Notificado</th>
												<th>Comentario</th>
											</tr>
											<c:forEach var = "estado" items = "${pedidoEstadosHistorial}">
											<tr>
												<td class = "hidden-xs">
													<fmt:formatDate type="date"  pattern = "dd/MM/yyyy" value="${estado.fecha.time}" />
												</td>
												<td>
													<c:out value = "${estado.estado.nombre}"/>
												</td>
												<td>
													<c:if test = "${estado.clienteNotificado}">
														Sí
													</c:if>
													
													<c:if test = "${!estado.clienteNotificado}">
														No
													</c:if>
												</td>
												<td>
													<c:out value = "${estado.comentario}"/>
												</td>
											</tr>
											</c:forEach>
									</table>
								
								</div>	
								
						</div>
						
						<c:if test = "${pedidoForm.pedido.borrado == null}">
						
							<div id = "add-estados" class="panel panel-default">
						
								<div class="panel-heading">
								    <h3 class="panel-title">Añadir estado</h3>
								</div>
								
								<div class="panel-body">
									<div>
										<form:label for = "pedido.notificarEstado1" path = "pedido.notificarEstado">
											Notificar estado <form:checkbox path="pedido.notificarEstado"  value = "true"/>
										</form:label>
									</div>
									
									<div class="form-group <c:out value = "${estado}"/>">
			                           		<form:label path="pedido.estadoAdmin.id" class="col-md-1 control-label">Estado(*)</form:label>
			                        	<div class="col-md-12">
			                            	<form:select path="pedido.estadoAdmin.id" class = "form-control">
											   <form:option value="" label="Selecciona un estado..."/>
											   <form:options items="${estados}" itemValue="id" itemLabel="nombre"/>
											</form:select> 
											<form:errors path="pedido.estadoAdmin" class="form-error"/>
			                           	</div>
			                        </div>
	                        		
	                        		 <div class="form-group <c:out value = "${comentarioCambioEstado}"/>">
			                           	<form:label path="pedido.comentarioCambioEstado" class="col-md-1 control-label">Comentario(*)</form:label>
			                           	<div class="col-md-12">
			                            	<form:textarea path="pedido.comentarioCambioEstado" class="form-control" placeholder="Comentario" maxlength="255" rows="5"/>
			                            	<form:errors path="pedido.comentarioCambioEstado" class="form-error"/>
			                           	</div>
			                       	</div>
			                       	
			                       	<div>
			                       		<a id = "add-nuevo-estado" href = "#">Añadir nuevo estado</a>
			                       	</div>
								</div>	
								
							</div>
						
						</c:if>
                        
                        <div class="form-group">
                            
                        	<div class="col-xs-2 col-sm-1 col-md-2 col-lg-2">
								
								<decocina:volverPost url = "pedido" busqueda="${pedidoBuscador}"/>
								
							</div>
							
							<div class="col-xs-10 col-sm-11 col-md-10 col-lg-10 text-right">
								
								<c:if test = "${!pedidoForm.esCreacion}">
									
									<a id = "eliminar" href = "#" class="btn btn-danger">Eliminar</a>
								
								</c:if>
								
							</div>
					
					 	</div>
					                       	 
					</form:form>
				   	   
				</div>
			</div>
			
			<decocina:parametrosPost id = "pedidoModifyForm" name = "pedidoModifyForm" url = "pedido-find" busqueda="${pedidoBuscador}" excluir="id"/>
			
			<decocina:goPostVolver id = "pedidoModifyForm" name = "pedidoModifyForm"/>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-admin">
	
		<script>
		
			$(document).ready(function() {
				
				$("#add-nuevo-estado").click(function(e){
					
					e.preventDefault();
						
					$("#pedidoForm").attr("action", "pedido-add-nuevo-estado?<decocina:parametros busqueda="${pedidoBuscador}"/>");
					$("#pedidoForm").submit();
						
				});
				
				$("#eliminar").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Pedido</h4>',
			            message: 'Se eliminará el Pedido ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Eliminar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#pedidoForm").attr("action", "pedido-delete");
								$("#pedidoForm").submit();
			                }
			            }
			        });
					
				});
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>