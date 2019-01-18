<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

		<c:if test="${fn:length(articulosMasVendidos) != 0}">
	       	
	       	<div class="list-group">
	       			
       			<div class="panel panel-default">
		
        			<div class="panel-heading">
        				<h3 class="panel-title">Más vendidos</h3>
           			</div>
           			
	           		<c:forEach var="articulo" items = "${articulosMasVendidos}">
	           
			           	<div class="panel-body panel-custom">
			            	
				            <div class="media-left">        
								<a href="<c:url value = "/${articulo.urlAmigable}"/>">
									<img src="<c:url value = "/${articulo.imagenG.uri}"/>" alt = "<c:out value = "${articulo.imagenT.alt}"/>" title = "<c:out value = "${articulo.imagenT.title}"/>" width= "100" height = "100"/>
								</a>
							</div>
						
							<div class="media-body">
								<a href = "<c:url value = "/${articulo.urlAmigable}"/>"><c:out value = "${articulo.nombre}"/></a><br>
								
								<div class="color-precio-articulo">
									<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${articulo.precio}"/>€
								</div>
								
							</div>
	            		</div>
	           
	           		</c:forEach>
	           		
	           	</div>
	           	
			</div>
		
		</c:if>
		
		<c:if test="${fn:length(articulosMasVistos) != 0}">
	       	
	       	<div class="list-group">
	      		
	      		<div class="panel panel-default">
		
        			<div class="panel-heading">
        				<h3 class="panel-title">Más vistos</h3>
           			</div>
           			
	          		<c:forEach var="articulo" items = "${articulosMasVistos}">
	          
		            	<div class="panel-body panel-custom">
		            	
			            	<div class="media-left">        
								<a href="<c:url value = "${articulo.urlAmigable}"/>">
									<img src="<c:url value = "/${articulo.imagenG.uri}"/>" alt = "<c:url value = "${articulo.imagenT.alt}"/>" title = "<c:url value = "${articulo.imagenT.title}"/>" width= "100" height = "100"/>
								</a>
							</div>
					
							<div class="media-body">
								<a href = "<c:url value = "${articulo.urlAmigable}"/>"><c:out value = "${articulo.nombre}"/></a><br>
							
								<div class="color-precio-articulo">
									<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${articulo.precio}"/>€
								</div>
							
							</div>
	           			</div>
	          
	          		</c:forEach>
		          		
		    	</div>
	          
	    	</div>
		</c:if>
		<%-- 
        <div class="list-group">
        	<h5 class="text-left list-group-item active">Bar</h5>
            <a href="#" class="list-group-item">Link</a>
            <a href="#" class="list-group-item">Link</a>
            <a href="#" class="list-group-item">Link</a>
            <a href="#" class="list-group-item">Link</a>
		</div>
		--%>
 			