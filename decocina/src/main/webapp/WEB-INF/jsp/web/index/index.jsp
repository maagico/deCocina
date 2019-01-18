<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<tiles:insertDefinition name="template-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			deCocina. Tu tienda online de utensilios de cocina.
		</title>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="meta-web">
	
		<meta name="viewport" content="width=device-width; initial-scale=1, user-scalable=yes"/>
		
		<meta name="keywords" content="Utensilios de cocina, sopletes, mandolinas, tablas de cortar, corta patatas, biberones, sifones de cocina"/>
		<meta name="description" content="Venta de utensilios y menaje de cocina. Máquinas de pasta, sopletes de cocina, sifones, mandolinas y mucho más."/>
		 	
	</tiles:putAttribute>	
	
    <tiles:putAttribute name="body-web">
			
		<div class="row">
					
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
				<div class="panel panel-default">
					<div class="panel-heading">
                    	
                    	<div class="panel-title">Novedades</div>
                    	
                	</div> 
				</div>
								
			</div>
			
		 </div>
				
		 <div class="row">
				
			<c:forEach var="articulo" items = "${articulos}">
				
				<div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">
					
					
					<div class="thumbnail">
					
						<a href="<c:url value = "${articulo.urlAmigable}"/>">
							<img src="<c:url value = "/${articulo.imagenG.uri}"/>" alt = "<c:url value = "${articulo.imagenT.alt}"/>" title = "<c:url value = "${articulo.imagenT.title}"/>" width= "200" height = "200" class = "imagen-articulo-bottom"/>
						</a>
						<p class=" text-center alto-nombre-articulo">
							<a href="<c:url value = "${articulo.urlAmigable}"/>">
								
								<c:out value = "${articulo.nombre}"/>
								
							</a>
						</p>
						<p class="text-center color-precio-articulo">
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${articulo.precio}"/>€
						</p>
					</div>
					
				</div>
			
			</c:forEach>
			
		</div>
			
 	</tiles:putAttribute>

</tiles:insertDefinition>