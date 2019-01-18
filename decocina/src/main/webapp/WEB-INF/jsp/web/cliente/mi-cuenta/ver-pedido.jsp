<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>


<tiles:insertDefinition name="template-central-web">

	<tiles:putAttribute name="title-web">
		<title>
			Ver pedido - deCocina
		</title>
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
		
		<div class="panel panel-default">
				
			<div class="panel-heading">
			    <h3 class="panel-title">Dirección de entrega</h3>
			</div>
			
			<div class="panel-body">
				<div class = "panel-realizar-pedido">
	
					<c:out value = "${pedido.nombreCliente}"/>					
	
				</div>
				<div class = "panel-realizar-pedido">
	
					<c:out value = "${pedido.calle}"/>						
	
				</div>
				<div class = "panel-realizar-pedido">
	
					<c:out value = "${pedido.poblacion}"/>						
	
				</div>
				<div class = "panel-realizar-pedido">
	
					<c:out value = "${pedido.codigoPostal}"/> <c:out value = "${pedido.zona}"/> España
	
				</div>
				
				
				<%--
				<div class = "panel-realizar-pedido">
					<c:out value = "${pedido.telefono}"/>	
				</div>
				<div class = "panel-realizar-pedido">
					<c:out value = "${pedido.email}"/>	
				</div>
				--%>
			</div>
			
		</div>
		
		<c:if test = "${pedido.comentarioFormaPago != ''}">
		
			<div id = "informacion-pago" class="panel panel-default">
	
				<div class="panel-heading">
				    <h3 class="panel-title">Información del pago</h3>
				</div>
				
				<div class="panel-body">
					<div>
	
						<c:out value = "${pedido.comentarioFormaPago}" escapeXml="false"/>					
	
					</div>
				</div>	
				
			</div>
					
		</c:if>
		
		<c:if test = "${pedido.observaciones != ''}">
		
			<div id = "observaciones" class="panel panel-default">
	
				<div class="panel-heading">
				    <h3 class="panel-title">Observaciones</h3>
				</div>
				
				<div class="panel-body">
					<div>
	
						<c:out value = "${pedido.observaciones}"/>					
	
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
						    		<span class = "color-precio-articulo"><c:out value="${pedido.precioTotalCesta}"/>€</span>
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
						    		<c:out value = "${pedido.nombreFormaEnvio}"/>:
						    	</td>
						    	
						    	<td>
						    		 <span class = "color-precio-articulo"><c:out value="${pedido.precioFormaEnvio}"/>€</span>
						    	</td>
						    </tr>
						    <tr class = "visible-xs">
						    	
						    	<td colspan = "2" class = "text-right">
						    		<c:out value = "${pedido.nombreFormaEnvio}"/>:
						    	</td>
						    	
						    	<td>
						    		 <span class = "color-precio-articulo"><c:out value="${pedido.precioFormaEnvio}"/>€</span>
						    	</td>
						    </tr>
						    
						    <tr class = "hidden-xs">
						    	
						    	<td colspan = "4" class = "text-right">
						    		<c:out value = "${pedido.nombreFormaPago}"/>:
						    	</td>
						    	
						    	<td>
						    		 <span class = "color-precio-articulo"><c:out value="${pedido.precioFormaPago}"/>€</span>
						    	</td>
						    </tr>
						    <tr class = "visible-xs">
						    	
						    	<td colspan = "2" class = "text-right">
						    		<c:out value = "${pedido.nombreFormaPago}"/>:
						    	</td>
						    	
						    	<td>
						    		 <span class = "color-precio-articulo"><c:out value="${pedido.precioFormaPago}"/>€</span>
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
						    		 <span class = "color-precio-articulo"><c:out value="${pedido.precioTotal}"/>€</span>
						    	</td>
						    </tr>
						    <tr class = "visible-xs">
						    	
						    	<td colspan = "2" class = "text-right">
						    		Total:
						    	</td>
						    	
						    	<td>
						    		 <span class = "color-precio-articulo"><c:out value="${pedido.precioTotal}"/>€</span>
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
								<th>Fecha</th>
								<th>Estado</th>
								<th>Comentario</th>
							</tr>
							<c:forEach var = "estado" items = "${pedidoEstadosHistorial}">
							<tr>
								<td>
									<fmt:formatDate type="date"  pattern = "dd/MM/yyyy" value="${estado.fecha.time}" />
								</td>
								<td>
									<c:out value = "${estado.estado.nombre}"/>
								</td>
								<td>
									<c:out value = "${estado.comentario}"/>
								</td>
							</tr>
							</c:forEach>
					</table>
				
				</div>	
				
		</div>
		
 	</tiles:putAttribute>

</tiles:insertDefinition>