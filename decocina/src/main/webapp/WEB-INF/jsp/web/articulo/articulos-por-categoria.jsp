<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<tiles:insertDefinition name="template-web">
	
	<tiles:putAttribute name="title-web">
		<title>
		
			<c:if test = "${esBusqueda == null || !esBusqueda}">
				<c:out value = "${categoria.nombre}"/> - deCocina
			</c:if>
			
			<c:if test = "${esBusqueda != null && esBusqueda}">
				Resultados de la búsqueda - deCocina
			</c:if>
			
		</title>
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
		
		<c:if test="${fn:length(articulos) == 0 && esBusqueda}">
			
			<div class="row">
				
				<div class="col-xs-12 col-sm-12 col-md-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							Tu búsqueda no ha devuelto ningún resultado 		
						</div>
					</div>
									
				</div>
				
			</div>
			
		</c:if>
		
		<div class="row">
			
			<c:forEach var="articulo" items = "${articulos}">
				
				<div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">
					
					<div class="thumbnail">
					
						<a href="<c:url value = "/${articulo.urlAmigable}"/>">
							<img src="<c:url value = "/${articulo.imagenG.uri}"/>" alt = "<c:out value = "${articulo.imagenP.alt}"/>" title = "<c:out value = "${articulo.imagenP.title}"/>" width= "200" height = "200" class = "imagen-articulo-bottom"/>
						</a>
						<p class=" text-center alto-nombre-articulo">
							<a href="<c:url value = "/${articulo.urlAmigable}"/>">
								
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