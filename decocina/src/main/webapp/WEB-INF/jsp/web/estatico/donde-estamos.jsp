<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
		</title>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="body-web">
			
		<div class="row">
			<div class= "container">	
				
				<div class="jumbotron">
				
					<h3>¿Dónde estamos?</h3>
					
					<p class = "info-quienes-somos">
						<strong>deCocina S.L.</strong>
						<br/>	
						
						Calle del Manojo de Rosas 1, 28015 Madrid<br/>
						
						Teléfono de contacto: 91 123 45 67<br/>
					</p>
			
					<p>
						<strong>Horario</strong><br/>
						Lunes a viernes: 09:30 - 13:30 16:30 - 20:00 <br/>
					
						Sábado: 09:30 - 13:30
					</p>	
			   		
			   		<p>
						
						<%--
			   				Los Utensilios del chef
			   				https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3036.805819701744!2d-3.7167184489679865!3d40.43529846233259!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd422842e1a83c11%3A0xc1e2a2772a8094af!2sLos+Utensilios+del+Chef!5e0!3m2!1ses!2ses!4v1444858175061
			   			 --%>
			   			
			   			<%-- 
			   			<object type="text/html" data="http://maps.google.es/maps?f=q&amp;hl=es&amp;geocode=&amp;q=fernandez+de+los+rios+73&amp;sll=40.43612,-3.713379&amp;sspn=0.007954,0.013626&amp;ie=UTF8&amp;om=1&amp;s=AARTsJqLcRF7ZjCZQmrBSU3jA17BB1W86Q&amp;ll=40.43937,-3.712392&amp;spn=0.016331,0.021458&amp;z=15&amp;iwloc=addr&amp;output=embed" style="width:100%; height:500px;">
						</object>
						--%>
					</p>
					
			   	</div>
			   		
			</div>
		   	
		</div>
		
	</tiles:putAttribute>
	
</tiles:insertDefinition>
