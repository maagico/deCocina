<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			¡Tu pedido ha sido realizado! - deCocina
		</title>
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
		
		<div class="jumbotron">
			<div class="container">
		    	<h3>¡Tu pedido ha sido realizado!</h3>
		   		<p>
		   			<c:if test = "${idFormaPagoPedido != null && (idFormaPagoPedido == 1 || idFormaPagoPedido == 4)}">
		   				Lo recibirás en un plazo de 24/48H.
		   			</c:if>
		   			
		   			<c:if test = "${idFormaPagoPedido != null && idFormaPagoPedido == 3}">
		   				Lo recibirás en un plazo de 24/48H a partir de que nos llegue el comprobante de pago de la transferencia bancaría. 
		    		</c:if>
		    		
		    		<c:if test = "${idFormaPagoPedido != null && idFormaPagoPedido == 5}">
		   				Pásate cuando quieras por la tienda para recogerlo.
		   			</c:if>
		    	</p>
		    	<p>
		    		Hemos enviado un email a tu dirección de correo con el detalle del pedido.
		    	</p>
		    	<p>
		    		Gracias por comprar en deCocina.
		    	</p>
		  	</div>
		</div>
		
 	</tiles:putAttribute>
	
</tiles:insertDefinition>