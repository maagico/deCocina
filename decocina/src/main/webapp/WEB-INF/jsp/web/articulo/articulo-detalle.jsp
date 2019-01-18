<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<tiles:insertDefinition name="template-web">
	
	<tiles:putAttribute name="meta-web">
	
		<meta name="viewport" content="width=device-width; initial-scale=1, user-scalable=yes"/>
		
		<meta name="keywords" content="<c:out value="${articulo.metaKeywords}"/>"/>
		<meta name="description" content="<c:out value="${articulo.metaDescripcion}"/>"/>
		<meta property="og:image" content="<c:url value="${urlCompleta}${articulo.imagenG.uri}"/>"/>
		 	
	</tiles:putAttribute>	
	
	<tiles:putAttribute name="title-web">
		<title>
			<c:out value = "${articulo.nombre}"/> - deCocina
		</title>
	</tiles:putAttribute>
    
    <tiles:putAttribute name="body-web">
	
		<div itemscope itemtype="http://schema.org/Product">
		
			<div class="row">
				
				<div class="col-xs-12 col-sm-12 col-md-12">
					
					<ul class="list-group">
						<li class="list-group-item">
							
							<h1 class = "titulo">
								<a href="<c:url value = "/${articulo.urlAmigable}"/>">
									<span itemprop="name"><c:out value = "${articulo.nombre}"/></span>
								</a>
							</h1>
						 </li>	
					</ul>
									
				</div>
				
			</div>
			
			<div class="row">
			
				<div class="col-xs-12 col-sm-8 col-md-8 col-lg-7">
					
					<div class="thumbnail">
						<img itemprop="image" src = "<c:url value="${articulo.imagenG.uri}"/>" alt = "<c:url value = "${articulo.imagenG.alt}"/>" title = "<c:url value = "${articulo.imagenG.title}"/>" width = "400" height = "400" />
					</div>
					
				</div>
				
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-5">
					
					<div class="panel panel-default">
						<div class="panel-body">
							<c:out value = "${articulo.descripcionCorta}"/> 
					  	</div>
					</div>
											
				</div>
				
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-5">
					
					<div class="panel panel-default precio-stock-text-align">
						<div itemprop="offers" itemscope itemtype="http://schema.org/Offer" class="panel-body precio-stock">
					    	Precio: <span itemprop="price" class ="color-precio-articulo"><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${articulo.precio}"/>€</span> 
					    	
					    	<c:if test = "${articulo.cantidad > 0}">
					    		<div class = "en-stock">
					    			<link itemprop="availability" href="http://schema.org/InStock"/>
					    			<span>En Stock</span>
					    		</div>
					    	</c:if>
					    	
					    	<c:if test = "${articulo.cantidad <= 0}">
					    		<div class = "sin-stock">
					    			Sin Stock
					    		</div>
					    	</c:if>
					    	
					  	</div>
					</div>
											
				</div>
				
				<div class="col-xs-12 col-sm-4 col-md-4 col-lg-5">
					
					<c:if test = "${articulo.cantidad > 0}">
						<a href = "javascript:addArticuloCesta(<c:out value = "${articulo.id}"/>)" class="btn btn-success btn-block btn-lg" type="button" style = "margin-bottom:20px">
							Añadir a la cesta<i class="glyphicon glyphicon-shopping-cart cesta-margin-left"></i>
						</a>
					</c:if>
					
				</div>
				
				<%-- 
				<div class="col-xs-12 col-sm-8 col-md-8 col-lg-7">
					<div class="panel panel-default">
						<div class="panel-body">
							El producto está en nuestro catálogo desde el 22/12/2015
						</div>
					</div>
				</div>
				--%>
				
				<c:if test="${articulo.descripcion != ''}">
					
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						
						<div class="panel panel-default">
							<div class="panel-body">
						    	
						    	<c:out value = "${articulo.descripcion}" escapeXml="false"/>
							
						  	</div>
						</div>
												
					</div>
					
				</c:if>
				
			</div>
			
			<c:if test="${fn:length(articulosRelacionados) > 0}">
				
				<div class="row">
					
					<div class="col-xs-12 col-sm-12 col-md-12">
						
						<div class="panel panel-default">
							<div class="panel-heading">
		                    	
		                    	<div class="panel-title">Utensilios relacionados </div>
		                    	
		                	</div> 
						</div>
									
					</div>
					
				</div>
				
			</c:if>
			
			<div class = "row">
				
				<c:forEach var="articulo" items = "${articulosRelacionados}">
					
					<div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">
						
						
						<div class="thumbnail">
						
							<a href="<c:url value = "/${articulo.urlAmigable}"/>">
								<img src="<c:url value = "/${articulo.imagenG.uri}"/>" alt = "<c:url value = "${articulo.imagenP.alt}"/>" title = "<c:url value = "${articulo.imagenP.title}"/>" width= "200" height = "200" class = "imagen-articulo-bottom"/>
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
			
			 
			<c:if test="${fn:length(articulosVentasRelacionadas) > 0}">
				
				<div class="row">
					
					<div class="col-xs-12 col-sm-12 col-md-12">
						
						<div class="panel panel-default">
							<div class="panel-heading">
		                    	
		                    	<div class="panel-title">Clientes que compraron este utensilio también compraron</div>
		                    	
		                	</div> 
						</div>
										
					</div>
					
				</div>
				
			</c:if>
			
			<div class = "row">
				
				<c:forEach var="articulo" items = "${articulosVentasRelacionadas}">
					
					<div class="col-xs-6 col-sm-4 col-md-4 col-lg-3">
						
						
						<div class="thumbnail">
						
							<a href="<c:url value = "/${articulo.urlAmigable}"/>">
								<img src="<c:url value = "/${articulo.imagenG.uri}"/>" alt = "<c:url value = "${articulo.imagenP.alt}"/>" title = "<c:url value = "${articulo.imagenP.title}"/>" width= "200" height = "200" class = "imagen-articulo-bottom"/>
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
			
		</div>
 	</tiles:putAttribute>

	<tiles:putAttribute name="resources-down-web">
	
		<script>
			
			function addArticuloCesta(id){
				
				document.location.href = "<c:url value = "/add-articulo-cesta"/>?id="+id;
			}
			
		</script>
		
	</tiles:putAttribute>

</tiles:insertDefinition>