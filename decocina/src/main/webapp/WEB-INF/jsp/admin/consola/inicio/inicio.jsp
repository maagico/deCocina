<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>

<tiles:insertDefinition name="template-central-admin">
	
    <tiles:putAttribute name="body-admin">
		
		<div class="row" style = "margin-bottom:20px">
				
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
				<div class="panel panel-default">

      				<div class="panel-heading">
      					<h3 class="panel-title">Número de pedidos este mes</h3>
         			</div>
         		</div>
				
				<div>
					<c:out value = "${numeroPedidos}"/> - <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${sumaTotalMes}"/>€
				</div>
			</div>
		
		</div>
		
		<div class="row">
				
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				 	
			 	<div class="panel panel-default">

      				<div class="panel-heading">
      					<h3 class="panel-title">Últimos artículos añadidos</h3>
         			</div>
         		</div>
         		
				<table class="table table-condensed">
					<thead>
						<tr>
							<th class = "categoria-th-ancho-id">Id</th>	
							<th class = "categoria-th-ancho-imagen-activa"></th>
							<th class = "categoria-th-ancho-imagen">Imagen</th>	
							<th>Nombre</th>		
				        	<th></th>					        	
				      	</tr>
				    </thead>
				    <tbody>
				    
				        <c:forEach var="articulo" items="${articulos}">
	
					    	<tr>
					        	<td>
					        		<c:out value = "${articulo.id}"/>
					        	</td>
					        	<td>
					        		<c:if test = "${articulo.activo}">
					        		
					        			<i class="glyphicon glyphicon-eye-open"></i>
					        		
					        		</c:if>
					        		
					        		<c:if test = "${!articulo.activo}">
					        		
					        			<%-- 
					        			<i class="glyphicon glyphicon-eye-close"></i>
					        			--%>
					        			
					        		</c:if>
					        		
					        	</td>
					        	
					        	<td>
										
				        			<c:if test = "${articulo.imagenG.uri != null}">
				        			
				        				<div class="thumbnail">
				        					
				        					<img src="<c:url value = "/${articulo.imagenG.uri}"/>" alt = "${articulo.imagenT.alt}" title = "${articulo.imagenT.title}" width = "100" height = "100"/>	
				        				
				        				</div>
				        		
				        			</c:if>
								
				        		</td>
					        		
					        	
					        	<td>
					        		<a href = "<c:url value = "/admin/consola/articulo/articulo-modify?path=${categoriaArticuloBuscador.path}&id=${articulo.id}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}" excluir = "path"/>"><c:out value = "${articulo.nombre}"/></a>
					        	</td>
					        	<td>
					        		<a href = "<c:url value = "/admin/consola/articulo/articulo-modify?path=${categoriaArticuloBuscador.path}&id=${articulo.id}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}" excluir = "path"/>" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-edit"></i></a>
					        	</td>
					        </tr>
					   
				        </c:forEach>
				        
				    </tbody>
				</table>
				
			</div>
			
		</div>
         	
		 	
			
 	</tiles:putAttribute>

</tiles:insertDefinition>