<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			Protección de datos - deCocina
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
						En <a href= "<c:url value = "/"/>">decocina.es</a> ponemos todos los medios a nuestro alcance para ofrecer a todos nuestros clientes la seguridad y la confidencialidad de sus datos.
					</p>
					<p>
						En el momento que un cliente se registra en nuestro servidor o realiza un pedido, sus datos personales, domiciliarios, etc., son incorporados a nuestra base de datos, utilizándose únicamente para tramitar el pedido, así como para enviar información sobre ofertas y servicios que puedan resultar de su interés.
					</p>
					<p>
						Aseguramos la confidencialidad de los datos aportados por nuestros clientes, y garantizamos que en ningún caso será cedida a terceros ajenos a nosotros.
					</p>
					<p>
						Ley de Protección de datos:
					</p>
					<p>
						En cumplimiento de lo dispuesto en la Ley Orgánica 15/1999 de 13 de Diciembre, de Protección de Datos de Carácter Personal, el cliente de decocina.es, podrá en todo momento ejercitar los derechos de acceso, rectificación, cancelación y oposición, comunicándolo por correo electrónico a nuestra dirección infoDeCocina@gmail.es.
					</p>
					
			   	</div>
			   		
			</div>
		   	
		</div>
		
	</tiles:putAttribute>
	
</tiles:insertDefinition>
