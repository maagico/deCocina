<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			Selecciona la forma de envío y pago - deCocina
		</title>
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
		
		<form:form id = "pedidoForm" modelAttribute="pedidoForm" action = "confirmar-pedido"  class="form-horizontal" role="form" acceptCharset="UTF-8">
		
			<form:hidden path="formaEnvio"/>
			<form:hidden path="formaPago"/>
			
			<div class="row">
				<div class="container">
					<div class="alert alert-info text-center hidden-xs col-sm-12 col-md-12 col-lg-12">
						<strong>1 - Envío y Pago </strong>| 2 - <span id = "paso-dos">
							<c:if test = "${clienteWrapper.pedidoWrapper.formaPago.nombreBoton == '' || clienteWrapper.pedidoWrapper.formaPago.nombreBoton == null}">
								Confirmar y finalizar
							</c:if>
							<c:if test = "${clienteWrapper.pedidoWrapper.formaPago.nombreBoton != ''}">
								<c:out value = "${clienteWrapper.pedidoWrapper.formaPago.nombreBoton}" />
							</c:if>
						</span> | 3 - Finalizado</div>
					<div>
					</div>
				</div>
			</div>
			
			<div class="alert alert-info text-center visible-xs">Envío y Pago - Paso 1 de 3</div>
		
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
				</div>
			</div>
			
			<div id = "fe" class="panel panel-default">
				
				<div class="panel-heading">
				    <h3 class="panel-title">Selecciona una forma de envío</h3>
				</div>
				
				<div class="panel-body">
					<div class = "panel-realizar-pedido">
							
						<div class="list-group">
	    					
	    					<c:set var = "formaEnvioSel" value ="false"/>
	    					
							<c:forEach var = "zonaFormaEnvio" items = "${zona.zonaFormasEnvio}">
								
								<c:if test = "${clienteWrapper.pedidoWrapper.formaEnvio.id == zonaFormaEnvio.formaEnvio.id}">
									<c:set var = "formaEnvioSel" value ="true"/>
								</c:if>
								
								<a id = "fe-<c:out value = "${zonaFormaEnvio.formaEnvio.id}"/>" href="javascript:formaEnvio(<c:out value = "${zonaFormaEnvio.formaEnvio.id}"/>)" class="list-group-item <c:if test = "${clienteWrapper.pedidoWrapper.formaEnvio.id == zonaFormaEnvio.formaEnvio.id}">active</c:if>">
								
								    <c:out value = "${zonaFormaEnvio.formaEnvio.nombre}"/>
								   
								    <span class = "precio-realizar-pedido"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${zonaFormaEnvio.formaEnvio.gasto}"/>€</span>
									
									<c:if test = "${zonaFormaEnvio.formaEnvio.descripcion != ''}">
											
											<span class = "descripcion-forma-pago">
											
									   	 		<c:out value = "${zonaFormaEnvio.formaEnvio.descripcion}"/>
											
											</span>
											
										</c:if>
								</a>		
								
							</c:forEach>				
						
						</div>
						
					</div>
				</div>
				
			</div>
			
			<div id = "fp" class="panel panel-default <c:if test = "${!formaEnvioSel}">panel-display-none</c:if>">
				
				<div class="panel-heading">
				    <h3 class="panel-title">Selecciona una forma de pago</h3>
				</div>
				
				<div class="panel-body">
					<div class = "panel-realizar-pedido">
							
						<div class="list-group">
	    
							<c:forEach var = "zonaFormaEnvio" items = "${zona.zonaFormasEnvio}">
							
								<c:forEach var = "formaEnvioFormasPago" items = "${zonaFormaEnvio.formaEnvio.formaEnvioFormasPago}">
									
									<c:if test = "${clienteWrapper.pedidoWrapper.formaPago.id == formaEnvioFormasPago.formaPago.id}">
										<c:set var = "formaPagoSel" value ="true"/>
									</c:if>
									
									<a id = "fp-${zonaFormaEnvio.formaEnvio.id}-${formaEnvioFormasPago.formaPago.id}" href="javascript:formaPago(${formaEnvioFormasPago.formaPago.id}, false);" class="list-group-item <c:if test = "${clienteWrapper.pedidoWrapper.formaPago.id == formaEnvioFormasPago.formaPago.id}">active</c:if>">
									
										<c:out value = "${formaEnvioFormasPago.formaPago.nombre}"/>
										
										<c:if test = "${formaEnvioFormasPago.formaPago.descripcion != ''}">
											
											<span class = "descripcion-forma-pago">
											
									   	 		<c:out value = "${formaEnvioFormasPago.formaPago.descripcion}" escapeXml="false"/>
											
											</span>
											
										</c:if>
										
									</a>		
										
								</c:forEach>
								
							</c:forEach>				
						
						</div>
						
					</div>
				</div>
				
			</div>
			
			<%--
			<div class="panel panel-default panel-display-none">
				
				<div class="panel-heading">
				    <h3 class="panel-title">Cupón de descuento</h3>
				</div>
				
				<div class="panel-body">
					<div class = "panel-realizar-pedido">
							
						<div class="list-group">
	    
	    					<div class = "cupon-block">
	    						Si tiene un cupón de descuento, introdúzcalo:
	    					</div>
							
							
							<div class="form-group">
	                        	<div class = "container col-sm-12">   	
	                        		<input type = "text"  class="form-control"/>
	                           	</div>
	                       	</div>
							
							<div>
	    						<a href = "#">Comprobar cupón</a>
	    					</div>
							
						</div>
						
					</div>
				</div>
				
			</div>
			
			--%>
			
			<div id ="observaciones" class = "<c:if test = "${!formaPagoSel}">panel-display-none</c:if>">
				<div class="panel panel-default">
					
					<div class="panel-heading">
					    <h3 class="panel-title">Observaciones sobre el pedido</h3>
					</div>
					
				</div>
				
				<div class="form-group">
			    	<div class = "container">   
			   			<form:textarea path = "observaciones" rows = "3" class="form-control" placeholder="Si tienes alguna observación sobre el pedido, hazla aquí."></form:textarea>
			      	</div>
			  	</div>
			</div>	
					
			<div class="form-group">
                           
                <div class="col-md-offset-2 col-xs-2 col-sm-1 col-md-2 col-lg-2">
									
				</div>
				
				<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
					
					<a id = "accion" href = "javascript:confirmarPedido();" class="btn btn-success text-right">Continuar</a>
					
				</div>
		
		 	</div>	
		 	
		</form:form>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-web">
	
		<script src="<c:url value = "/js/v${versionJS}/realizar.pedido.js"/>"></script>
		
	</tiles:putAttribute>
	
</tiles:insertDefinition>