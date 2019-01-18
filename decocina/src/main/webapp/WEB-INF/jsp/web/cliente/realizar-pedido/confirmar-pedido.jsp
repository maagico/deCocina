<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<tiles:insertDefinition name="template-central-web">
		
	<tiles:putAttribute name="title-web">
		<title>
			Confirmar el pedido - deCocina
		</title>
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
		
		<div class="form-horizontal">
			
			<div class="row">
				<div class="container">
				
					<div class="alert alert-info text-center hidden-xs col-sm-12 col-md-12 col-lg-12">
						<a id = "accion" href = "<c:url value = "realizar-pedido"/>">
							1 - Envío y Pago
						</a>
						| 
						<strong>2 - <c:if test = "${clienteWrapper.pedidoWrapper.formaPago.pasoAdicional}"></c:if><c:out value = "${clienteWrapper.pedidoWrapper.formaPago.nombreBoton}" /> </strong>
						| 
						3 - Finalizado
					</div>
					
				</div>
			</div>
			
			<div class="alert alert-info text-center visible-xs"><c:out value = "${clienteWrapper.pedidoWrapper.formaPago.nombreBoton}" /> - Paso 2 de 3</div>

			<div class="panel panel-default">
				
				<div class="panel-heading">
				    <h3 class="panel-title">Dirección de entrega</h3>
				</div>
				
				<div class="panel-body">
					<div class = "panel-realizar-pedido">
	
						<c:out value = "${clienteWrapper.cliente.nombre}"/>	<c:out value = "${clienteWrapper.cliente.apellidos}"/>						
	
					</div>
					<div class = "panel-realizar-pedido">
	
						<c:out value = "${clienteWrapper.cliente.direccion.calle}"/>						
	
					</div>
					<div class = "panel-realizar-pedido">
	
						<c:out value = "${clienteWrapper.cliente.direccion.poblacion}"/>						
	
					</div>
					<div class = "panel-realizar-pedido">
	
						<c:out value = "${clienteWrapper.cliente.direccion.codigoPostal}"/>	<c:out value = "${clienteWrapper.cliente.direccion.zona.nombre}"/> España
	
					</div>
					
					<%--
					<div class = "panel-realizar-pedido">
						<c:out value = "${clienteWrapper.cliente.telefono}"/>	
					</div>
					<div class = "panel-realizar-pedido">
						<c:out value = "${clienteWrapper.cliente.email}"/>	
					</div>
					--%>
				</div>
			</div>
			
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
						    	
						    	<c:forEach var = "articuloWrapper" items = "${clienteWrapper.pedidoWrapper.cestaManager.cesta}">
						    	
						    	<tr>
					    			<td colspan ="4" class = "visible-xs">
					    				<a href = "<c:url value="/${articuloWrapper.articulo.urlAmigable}"/>"><c:out value = "${articuloWrapper.articulo.nombre}"/></a>
					    			</td>
					    		</tr>
						    	
						    	<tr>
						    		<td>
							    		<img class = "thumbnail" src = "<c:url value="/${articuloWrapper.articulo.imagenG.uri}"/>" width = "100" height = "100">
							        </td>
							    	<td class = "hidden-xs">
							    		<c:out value = "${articuloWrapper.cantidad}"/> x <a href = "<c:url value="/${articuloWrapper.articulo.urlAmigable}"/>"><c:out value = "${articuloWrapper.articulo.nombre}"/></a>
							        </td>
							        <td class = "hidden-xs text-center">
							        	<fmt:formatNumber minFractionDigits="2" value="${articuloWrapper.articulo.impuesto.valor}"/>%
							        </td>
							        <td class = "text-right">
							        	<c:out value = "${articuloWrapper.cantidad}"/> x <strong><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${articuloWrapper.articulo.precio}"/>€</strong>
							        </td>
							        <td>
							        	<span class = "color-precio-articulo"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${articuloWrapper.precio}"/>€</span>
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
							    		<span class = "color-precio-articulo"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${clienteWrapper.pedidoWrapper.cestaManager.subTotal}"/>€</span>
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
							    		<c:out value = "${clienteWrapper.pedidoWrapper.formaEnvio.nombre}"/>:
							    	</td>
							    	
							    	<td>
							    		 <span class = "color-precio-articulo"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${clienteWrapper.pedidoWrapper.formaEnvio.gasto}"/>€</span>
							    	</td>
							    </tr>
							    <tr class = "visible-xs">
							    	
							    	<td colspan = "2" class = "text-right">
							    		<c:out value = "${clienteWrapper.pedidoWrapper.formaEnvio.nombre}"/>:
							    	</td>
							    	
							    	<td>
							    		 <span class = "color-precio-articulo"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${clienteWrapper.pedidoWrapper.formaEnvio.gasto}"/>€</span>
							    	</td>
							    </tr>
							    
							    <tr class = "hidden-xs">
							    	
							    	<td colspan = "4" class = "text-right">
							    		<c:out value = "${clienteWrapper.pedidoWrapper.formaPago.nombre}"/>:
							    	</td>
							    	
							    	<td>
							    		 <span class = "color-precio-articulo"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${clienteWrapper.pedidoWrapper.formaPago.gasto}"/>€</span>
							    	</td>
							    </tr>
							    <tr class = "visible-xs">
							    	
							    	<td colspan = "2" class = "text-right">
							    		<c:out value = "${clienteWrapper.pedidoWrapper.formaPago.nombre}"/>:
							    	</td>
							    	
							    	<td>
							    		 <span class = "color-precio-articulo"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${clienteWrapper.pedidoWrapper.formaPago.gasto}"/>€</span>
							    	</td>
							    </tr>
							    <tr class = "hidden-xs">
									
									<td colspan = "4" class = "text-right">
										<c:forEach var = "impuestoTotal" items = "${clienteWrapper.pedidoWrapper.cestaManager.impuestosTotales}">
											(<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${impuestoTotal.impuesto.valor}"/>% IVA: <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${impuestoTotal.total}"/>€)
										  	<BR>
										</c:forEach>
									</td>
							    	<td>
							    	</td>
							    	
							  	</tr>
							  	<tr class = "visible-xs">
									
									<td colspan = "2" class = "text-right">
										<c:forEach var = "impuestoTotal" items = "${clienteWrapper.pedidoWrapper.cestaManager.impuestosTotales}">
											(<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${impuestoTotal.impuesto.valor}"/>% IVA: <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${impuestoTotal.total}"/>€)
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
							    		 <span class = "color-precio-articulo"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${clienteWrapper.pedidoWrapper.precio}"/>€</span>
							    	</td>
							    </tr>
							    <tr class = "visible-xs">
							    	
							    	<td colspan = "2" class = "text-right">
							    		Total:
							    	</td>
							    	
							    	<td>
							    		 <span class = "color-precio-articulo"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${clienteWrapper.pedidoWrapper.precio}"/>€</span>
							    	</td>
							    </tr>
							</tbody>
						</table>
		  				
					</div>
					
				</div>
				
			</div>
			
			<%-- Datos del pago --%>
			<c:if test = "${clienteWrapper.pedidoWrapper.formaPago.comentario != ''}">
				
				<div id = "dp" class="panel panel-default">
					
					<div class="panel-heading">
					    <h3 class="panel-title">Información del pago</h3>
					</div>
					
					<div class="panel-body">
						
						<c:out value = "${clienteWrapper.pedidoWrapper.formaPago.comentario}" escapeXml="false"/>		
						
					</div>
					
				</div>
				
			</c:if>
			
			<c:if test = "${clienteWrapper.pedidoWrapper.observaciones != ''}">
			
				<div id = "observaciones" class="panel panel-default">
					
					<div class="panel-heading">
					    <h3 class="panel-title">Observaciones</h3>
					</div>
					
					<div class="panel-body">
							
						<c:out value = "${clienteWrapper.pedidoWrapper.observaciones}"/>
					
					</div>
					
				</div>
			
			</c:if>
			
			<div class="form-group">  
			  
		       	<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
				
					<a id = "accion" href = "<c:url value = "realizar-pedido"/>" class="btn btn-primary text-right">Volver</a>
											
				</div>
				
				<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10 text-right">
				
					<a id = "accion" href = "<c:url value = "${urlSiguiente}"/>" class="btn btn-success text-right">
					
						<c:out value = "${clienteWrapper.pedidoWrapper.formaPago.nombreBoton}" />
					
					</a>
				
				</div>
			
			</div>
			
		</div>
		
 	</tiles:putAttribute>
	
</tiles:insertDefinition>