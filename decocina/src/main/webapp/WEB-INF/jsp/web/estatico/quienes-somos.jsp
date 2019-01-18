<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			¿Quiénes somos? - deCocina
		</title>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body-web">
			
		<div class="row">
			<div class= "container">	
				
				<div class="jumbotron">
			
					<div class="container">
			    		
			    		<h3>
			    			Bienvenido a <a href= "<c:url value = "/"/>">deCocina.es</a> la tienda online de la empresa deCocina S.L.
			    		</h3>
				    		    
				   		<p class = "info-quienes-somos">
				   			deCocina S.L. lleva más de 60 años vendiendo menaje de cocina a empresas y particulares. Somos distribuidores de vajilla, cristalería, cubertería, menaje y maquinaria de hostelería. Suministramos a restaurantes, bares, cafeterías, hoteles, colectividades, hospitales y particulares.
						</p>
						<p>
							La web <a href= "<c:url value = "/"/>">decocina.es</a> surge con la idea de abrir una tienda orientada a los particulares y a la venta especializada de utensilios de cocina.
						</p>
						<p>
							Si quieres visitarnos te interesa saber <a href= "<c:url value = "donde-estamos"/>">dónde estamos</a>.
				   		</p>
			   			
			   		</div>
			   		
			   	</div>
		   	
			</div>
		</div>
		
	</tiles:putAttribute>
	
</tiles:insertDefinition>
