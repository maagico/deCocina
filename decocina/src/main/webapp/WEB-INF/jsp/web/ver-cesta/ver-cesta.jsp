<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="meta-web">
	 	
	 	<meta name="viewport" content="width=device-width; initial-scale=1, user-scalable=yes"/>
	 	
	 	<meta name="robots" content="noindex,nofollow"/>
	
	</tiles:putAttribute>
    
    <tiles:putAttribute name="title-web">
		
		<title>
			Cesta
		</title>
	
	</tiles:putAttribute>
    
    <tiles:putAttribute name="body-web">
			
			<div class="form-horizontal">
				
				<table class="table table-condensed">
						<thead>
					    	<tr>
					    		
					    		<%-- Para md 
					    			
					    			<th class ="col-sm-6" style = "width:600px">Utensilios</th>
					    			
					    		--%>
					    		
					        	<th class ="col-sm-6 width-utensilios">Utensilios</th>
					        	<th>Precio</th>
					        	<th>Cantidad</th>
					        	<th>Total</th>					        	
					      	</tr>
					    </thead>
					    <tbody>
					    	
					    	<c:forEach var ="articuloWrapper" items="${articulos}">
					    		
					    		<tr id = "fila-titulo-<c:url value="${articuloWrapper.articulo.id}"/>">
					    			<td colspan ="4" class = "visible-xs">
					    				<a href = "<c:url value="${articuloWrapper.articulo.urlAmigable}"/>"><c:out value = "${articuloWrapper.articulo.nombre}"/></a>
					    			</td>
					    		</tr>
						    	
						    	<tr id = "fila-<c:url value="${articuloWrapper.articulo.id}"/>">
						        	<td rowspan="2">
						        		
						        		<span class="media-left">        
										
											<img class = "thumbnail" src = "<c:url value="${articuloWrapper.articulo.imagenG.uri}"/>" width ="100" height = "100">
											
										</span>
							
										<div class="media-body hidden-xs">
											<a href = "<c:url value="${articuloWrapper.articulo.urlAmigable}"/>"><c:out value = "${articuloWrapper.articulo.nombre}"/></a>
										</div>
						        
						        	</td>
						        	
						        	<td rowspan="2">
						        		<span class = "color-precio-articulo"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${articuloWrapper.articulo.precio}"/>€</span>
						        	</td>
						        	
						        	<td rowspan="2" class = "cesta-cantidad">
						        		<input id="input-<c:url value="${articuloWrapper.articulo.id}"/>" class="form-control articulo-cesta" type="text" name="<c:url value="${articuloWrapper.articulo.id}"/>" value = "<c:out value = "${articuloWrapper.cantidad}"/>">
						        	</td>
						        	
						        	<td>
						        		<div id = "precio-total-<c:url value="${articuloWrapper.articulo.id}"/>" class = "color-precio-articulo">
						        			<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${articuloWrapper.precio}"/>€
						        		</div>
						        		<div id = "img-espera-<c:url value="${articuloWrapper.articulo.id}"/>" class = "img-espera">
					        				<img src = "<c:url value = "/img/app/esperar-ajax.gif"/>">
					        			</div>
						        	</td>
						        	
						      	</tr>
						      	<tr id = "fila-eliminar-<c:url value="${articuloWrapper.articulo.id}"/>" >
						      		
						        	<td class = "borde-superior-blanco">
						        		<a href = "javascript:eliminar(<c:url value="${articuloWrapper.articulo.id}"/>);" title = "Eliminar de la cesa"><i class="glyphicon glyphicon-remove-sign"></i></a>
						        		<a class = "hidden-xs" href = "javascript:eliminar(<c:url value="${articuloWrapper.articulo.id}"/>);" title = "Eliminar de la cesa">Quitar de la cesta</a>
						        	</td>
						      		
						      	</tr>	
						      				      	
					      	</c:forEach>
					      
					      <c:if test="${fn:length(articulos) != 0}">
					      
						      	<tr id = "subtotal">
						      		<td colspan="3" class ="text-right">
						      			<strong>Subtotal:</strong>
						      		</td>
						      		<td>
						      			<div id = "subtotal-precio" class = "color-precio-articulo"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${pedidoWrapper.cestaManager.subTotal}"/>€</div>
						      			<div id = "img-espera-subtotal" class = "espera-subtotal">
						        			<img src = "<c:url value = "/img/app/esperar-ajax.gif"/>">
						        		</div>
						      		</td>
						      	<tr>
						      	<tr id = "calculo-gastos-envio">
						      		<td  colspan="3">
						      			<div id = "msg-gastos" class ="text-right strong">
						      				<c:if test = "${pedidoWrapper.zona.id == null}">
						      				Para calcular los gastos de envío:
						      				</c:if>
						      				<c:if test = "${pedidoWrapper.zona.id != null}">
						      				Gastos para:
						      				</c:if>
						      			</div>
						      		</td>
						      		<td></td>
						      	<tr>
						      	<tr id = "zona">
						      		<td colspan="3" class ="text-right">
						      			
						      			<div align="right">
						      				
						      				<c:if test = "${clienteWrapper.esInvitado}">
						      				
							      				<form:select id = "zonas" name = "zonas" path="pedidoWrapper.zona.id" class = "form-control" style = "width:230px">
													<form:option value="" label="Selecciona tu provincia"/>
													<form:options items="${zonas}" itemValue="id" itemLabel="nombre"/>
												</form:select>
												 
											</c:if>
											
											<c:if test = "${!clienteWrapper.esInvitado}">
												
												<form:select disabled="true" id = "zonas" name = "zonas" path="pedidoWrapper.zona.id" class = "form-control" style = "width:230px">
													<form:option value="${pedidoWrapper.zona.id}" label="${pedidoWrapper.zona.nombre}"/>
												</form:select> 
											
											</c:if>
											
	 									</div>
	 									
						      		</td>
						      		<td></td>
						      	<tr>
						      	
						      	<c:if test = "${!pedidoWrapper.zona.activa && pedidoWrapper.zona.id != null}">
						      	<tr id = "no-envio-a-provincia">
						      		<td colspan="3" class ="text-right">
						      			
						      			<strong>Lo sentimos, pero actualmente no enviamos pedidos a <c:out value = "${pedidoWrapper.zona.nombre}"/></strong>
						      			
						      		</td>
						      		<td></td>
						      	</tr>
						      	</c:if>
						      	<c:if test = "${pedidoWrapper.zona.activa || pedidoWrapper.zona.id == null}">
							      	<tr id = "forma-envio" <c:if test = "${pedidoWrapper.zona.id == null}">class = "panel-display-none"</c:if>>
							      	
							      		<td colspan="3" class ="text-right">
							      			
							      			<div align="right">
							      				
							      				
							      				
							      					<form:select id = "formasEnvio" name = "formasEnvio" path="pedidoWrapper.formaEnvio.id" class = "form-control" style = "width:230px">
												   		<form:option value="" label="Selecciona la forma de envío"/>
												   		<form:options items="${formasEnvio}" itemValue="id" itemLabel="nombre"/>
													</form:select> 
												
												
		 									</div>
		 									
							      		</td>
							      		<td>
							      			<div id = "gasto-forma-envio" class = "color-precio-articulo">
							      				
							      				<c:if test = "${pedidoWrapper.formaEnvio.gasto != null}">
							      					<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${pedidoWrapper.formaEnvio.gasto}"/>€
							      				</c:if>
							      				
							      			</div>
							      			
							      			<div id = "img-espera-gasto-forma-envio" class = "img-espera">
							        			<img src = "<c:url value = "/img/app/esperar-ajax.gif"/>">
							        		</div>
							      		</td>
							      		
							      	</tr>
						      	</c:if>
						      	<c:if test = "${pedidoWrapper.zona.activa || pedidoWrapper.zona.id == null}">
							      	<tr id = "forma-pago" <c:if test = "${pedidoWrapper.formaEnvio.id == null}">class = "panel-display-none"</c:if>>
							      	
							      		<td colspan="3" class ="text-right">
							      			
							      			<div align="right">
							      				
							      				<form:select id = "formasPago" name = "formasPago" path="pedidoWrapper.formaPago.id" class = "form-control" style = "width:230px">
												   <form:option value="" label="Selecciona la forma de pago"/>
												   <form:options items="${formasPago}" itemValue="id" itemLabel="nombre"/>
												</form:select> 
		 										
		 									</div>
		 									
							      		</td>
							      		<td>
							      			<div id = "gasto-forma-pago" class = "color-precio-articulo">
							      			
							      				<c:if test = "${pedidoWrapper.formaPago.gasto != null}">
							      					<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${pedidoWrapper.formaPago.gasto}"/>€
							      				</c:if>
							      				
							      			</div>
							      			
							      			<div id = "img-espera-gasto-forma-pago" class = "img-espera">
							        			<img src = "<c:url value = "/img/app/esperar-ajax.gif"/>">
							        		</div>
							      		</td>
							      	</tr>
						      	</c:if>
						      	
						      	<c:if test = "${pedidoWrapper.zona.activa || pedidoWrapper.zona.id == null}">
							      	<tr id = "total" <c:if test = "${pedidoWrapper.formaPago.id == null}">class = "panel-display-none"</c:if>>
							      		
							      		<td colspan="3" class ="text-right"><strong>Total:</strong></td>
							      		<td>
							      			<div id = "total-precio" class = "color-precio-articulo">
							      				<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${pedidoWrapper.precio}"/>€
							      			</div>
							      			<div id = "img-espera-total" class = "espera-total">
							        			<img src = "<c:url value = "/img/app/esperar-ajax.gif"/>">
							        		</div>
							      		</td>
							      		
							      	</tr>
						      	</c:if>
						      	
						      	<c:if test = "${pedidoWrapper.zona.activa || pedidoWrapper.zona.id == null}">	
							      	<tr id = "pides-hoy">
							      		
							      		<td colspan="4" class ="text-center" style = "color:green">
							      			
							      			<strong>Envío 24/48H</strong>
							      			
							      		</td>
							      		
							      	</tr>
						      	</c:if>
					      	</c:if>
					      	
					      	<tr id = "no-hay-articulos-cesta-sup" <c:if test="${fn:length(articulos) != 0}">class = "panel-display-none"</c:if>>
						      		
						    	<td colspan="4" class ="text-center">&nbsp;</td>
						      		
						    <tr>
						    
					      	<tr id = "no-hay-articulos-cesta" <c:if test="${fn:length(articulos) != 0}">class = "panel-display-none"</c:if>>
						      		
						    	<td colspan="4" class ="text-center">
						      		<span class = "no-hay-articulos">No hay artículos en la cesta</span>
						      	</td>
						      		
						    <tr>
						    
					    </tbody>
					    
					</table>	
				
				
				<div class="form-group">  
					
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-left">
						
						<a href = "<c:url value = "/"/>"  class="btn btn-primary"><i class="glyphicon glyphicon-chevron-left"></i>Seguir comprando</a>
					</div>
					<%-- 
					<c:if test="${fn:length(articulos) != 0 && pedidoWrapper.zona.activa}">
					--%>	
						<div id = "btn-realizar-pedido" class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-right">
							<a href = "<c:url value = "/cliente/realizar-pedido"/>"  class="btn btn-success">Realizar pedido<i class="glyphicon glyphicon-chevron-right"></i></a>
						</div>
					<%-- 	
					</c:if>
					--%>	
				</div>
				
		 </div>
		 
 	</tiles:putAttribute>
 	
	<tiles:putAttribute name="resources-down-web">
	
		<script src="<c:url value = "/js/v${versionJS}/cesta.js"/>"></script>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>