<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			Envíos y devoluciones - deCocina
		</title>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="meta-web">
	 	
	 	<meta name="viewport" content="width=device-width; initial-scale=1, user-scalable=yes"/>
	 	
	 	<meta name="robots" content="noindex,nofollow"/>
	
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body-web">
			
		<div class="row">
			<div class= "container">	
				
				<div class="jumbotron">

					<p>
						El plazo de entrega será de 1 a 2 días hábiles.Si efectúa el pago contrareembolso tendrá un cargo de 3€.
					</p>
	
					<p>
						Los pedidos que se recojan en tienda se entregan en el momento.
					</p>
					
					<p>
						Podrá ver el precio del transporte antes de aceptar su pedido, este será calculado de una forma automática según la zona geográfica de destino y el peso del mismo. también puede elegir la opción de recoger su pedido en nuestra tienda, esta opción no genera ningún tipo de gasto de envío.
					</p>
					<p>
						Una vez recibido el pedido efectuado a través de nuestra tienda, el cliente dispondrá de un plazo de 7 días naturales para devolverlo a <a href= "<c:url value = "/"/>">decocina.es</a> con las siguientes limitaciones: 
					</p>
					<p>
						Sólo se admitirá la devolución de artículos en perfecto estado, en su embalaje original y que no hayan sido utilizados por el cliente.
					</p>

					<p>
						<strong>Todas las devoluciones quedan sujetas a las siguiente normas:</strong>
					</p>
					<p>
						El comprador deberá contactar con el centro de atención al cliente de <a href= "<c:url value = "/"/>">decocina.es</a> en el número 915430259 o por correo electrónico en la dirección infoDeCocinaPFC@gmail.es y solicitar el método de devolución del producto. No se aceptarán devoluciones no autorizadas expresamente por el vendedor.
					</p>
					<p>
						La mercancía será devuelta en perfectas condiciones, en su embalaje original y debidamente protegida. El cliente podrá enviar el producto por el medio de transporte de su elección, incluyendo el servicio de Correos. Es responsabilidad del cliente el abono de todos los gastos de devolución que se generen.
					</p>
					<p>
						Junto con la mercancía devuelta, el cliente incluirá una copia de la factura de venta que se envío con la mercancía, así como un número de cuenta en el que se haya de realizar el abono de la mercancía. Una vez verificado el estado de la misma, y descontados los gastos de envío, se procederá al abono del precio del producto en las 48h siguientes a la recepción de la devolución.
					</p>
					<p>
						Las devoluciones que sean aceptadas por <a href= "<c:url value = "/"/>">decocina.es</a> como resultado de un error en el envío no imputable al cliente, correrán por cuenta del vendedor, incluyendo los gastos derivados del envío y recogida de la mercancía errónea.
					</p>
			   	</div>
			   		
			</div>
		   	
		</div>
		
	</tiles:putAttribute>
	
</tiles:insertDefinition>
