<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>

<tiles:insertDefinition name="template-central-admin">
	
    <tiles:putAttribute name="body-admin">
			
			<div class="row">
				
				<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 search-bottom-padding" style= "margin-top:10px">
					
					<form:form id = "buscadorForm" action = "categoria-search" modelAttribute="categoriaArticuloBuscador" class="form-horizontal" role="search" acceptCharset="UTF-8" method="GET">
				     	
				     	<spring:hasBindErrors name="categoriaArticuloBuscador">
					     	<c:if test="${errors.hasFieldErrors('idCatArt')}">
									<c:set var="idCatArt" value="has-error" />
							</c:if>
				     	</spring:hasBindErrors>
				     	
			    		<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
					    	
					    	<input type ="hidden" id = "path" name = "path" value ="<c:out value = "${path}"/>"/> 
				        	<form:hidden path="hayBusqueda"/>
				        	
				        	<div class="form-group  <c:out value = "${idCatArt}"/>">
				        		<form:input path = "idCatArt" class="form-control form-input-buscador" placeholder="Id"/>
				        		<form:errors path="idCatArt" class = "form-error"/>
				        	</div>	
				        	
				        	<div class="form-group">
				        		<form:input path = "nombre" class="form-control" placeholder="Nombre"/>
					        </div>
						</div>
						
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							
							<a id = "buscar" href = "#" class="btn btn-default form-input-buscador"><i class="glyphicon glyphicon-search"></i></a>
							
							<c:if test = "${categoriaArticuloBuscador.hayBusqueda}">	
					
								<a id = "deshacerBusqueda" href = "<c:url value = "/admin/consola/categoria/categoria-find?path=-1"/>" class="btn btn-default"><i class="glyphicon glyphicon-remove-circle"></i></a>
								
							</c:if>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						</div>
						
				   </form:form>
				   	   
				</div>
				
			</div>
			
		 	<div class="row">
				
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		
					<table class="table table-condensed">
						<thead>
							<tr>
								<th class = "categoria-th-ancho-id">Id</th>	
								<th class = "categoria-th-ancho-imagen-activa"></th>
								<c:if test = "${profundidadArticulo || busqueda}">
									
									<th class = "categoria-th-ancho-imagen">Imagen</th>	
								
								</c:if>
					        	
					        	<th>Nombre</th>		
					        	<th></th>					        	
					      	</tr>
					    </thead>
					    <tbody>
					    
					    	<c:forEach var="categoria" items="${categorias}">
		
						    	<tr>
						        	<td>
						        		<c:out value = "${categoria.id}"/>
						        	</td>
						        	
						        	<td>
						        		<c:if test = "${categoria.activa}">
						        		
						        			<i class="glyphicon glyphicon-eye-open"></i>
						        		
						        		</c:if>
						        		
						        		<c:if test = "${!categoria.activa}">
						        		
						        			<%-- 
						        			<i class="glyphicon glyphicon-eye-close"></i>
						        			--%>
						        			
						        		</c:if>
						        		<c:if test = "${profundidadArticulo || busqueda}">
						        			<td>
						        				<i class="glyphicon glyphicon-folder-close"></i>
						        			</td>
						        		</c:if>
						        	</td>
						        	
						        	<c:if test = "${profundidadArticulo}">
						        	
						        		<td>
						        			
						        		</td>
						        		
						        	</c:if>
						        	
						        	<td>
						        		<a href = "<c:url value = "/admin/consola/categoria/categoria-find?path=${path}_${categoria.id}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}" excluir = "path"/>"><c:out value = "${categoria.nombre}"/></a>
						        	</td>
						        	<td>
						        		<a href = "<c:url value = "/admin/consola/categoria/categoria-modify?path=${path}_${categoria.id}&cat_id=${categoria.id}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}" excluir = "path"/>" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-edit"></i></a>
						        	</td>
						        </tr>
						   
					        </c:forEach>
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
						        	
						        	<c:if test = "${profundidadArticulo || busqueda}">
									
										<td>
											
						        			<c:if test = "${articulo.imagenG.uri != null}">
						        			
						        				<div class="thumbnail">
						        					<%-- class="lazy" src="<c:url value = "/img/admin/t.png"/>" data-original = "<c:url value = "/${articulo.imagenG.uri}"/>" --%>
						        					<img src="<c:url value = "/${articulo.imagenG.uri}"/>" alt = "${articulo.imagenT.alt}" title = "${articulo.imagenT.title}" width = "100" height = "100"/>	
						        				</div>
						        		
						        			</c:if>
										
						        		</td>
						        		
						        	</c:if>
						        	
						        	<td>
						        		<a href = "<c:url value = "/admin/consola/articulo/articulo-modify?path=${path}&id=${articulo.id}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}" excluir = "path"/>"><c:out value = "${articulo.nombre}"/></a>
						        	</td>
						        	<td>
						        		<a href = "<c:url value = "/admin/consola/articulo/articulo-modify?path=${path}&id=${articulo.id}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}" excluir = "path"/>" class="btn btn-default btn-sm"><i class="glyphicon glyphicon-edit"></i></a>
						        	</td>
						        </tr>
						   
					        </c:forEach>
					        
					    </tbody>
					</table>
					
				</div>
				
			</div>
			
			<div class = "row">
				
				<div class="col-xs-2 col-sm-1 col-md-1 col-lg-1">
				
					<c:if test = "${!esRaiz}">
						
						<c:if test = "${!categoriaArticuloBuscador.hayBusqueda}">
							
							<a href = "<c:url value = "/admin/consola/categoria/categoria-find?"/><decocina:parametros busqueda="${categoriaArticuloBuscador}"/>" class="btn btn-primary">Volver</a>
									
						</c:if>
						
						<c:if test = "${categoriaArticuloBuscador.hayBusqueda}">
						
							<c:if test="${pathVolver == '-1'}">
							
								<a href = "<c:url value = "/admin/consola/categoria/categoria-search?"/><decocina:parametros busqueda="${categoriaArticuloBuscador}"/>" class="btn btn-primary">Volver</a>
								
							</c:if>
							
							<c:if test="${pathVolver != '-1'}">
							
								<a href = "<c:url value = "/admin/consola/categoria/categoria-find?"/><decocina:parametros busqueda="${categoriaArticuloBuscador}"/>" class="btn btn-primary">Volver</a>
								
							</c:if>
						
						</c:if>
					
					</c:if>
					
				</div>
				
				<div class="col-xs-10 col-sm-11 col-md-11 col-lg-11 text-right">
					
					<c:if test = "${!profundidadArticulo}">
						
						<a href = "<c:url value = "/admin/consola/categoria/categoria-modify?path=${path}&"/><decocina:parametros busqueda="${categoriaArticuloBuscador}" excluir = "path"/>" class="btn btn-success">Crear categoría</a>
					
					</c:if>
					
					<c:if test = "${profundidadArticulo}">
						
						<a href = "#" class="btn btn-success text-right">Crear artículo</a>
					
					</c:if>
					
				</div>
					
			</div>		        
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-admin">
	
		<script>
		
			$(document).ready(function() {
				
				<%--
				$("img.lazy").lazyload({
					
					effect : "fadeIn"
				});
				--%>
				
				$("#buscar").click(function(e){
					
					e.preventDefault();
					
					var id = $("#idCatArt").val();
					var nombre = $("#nombre").val();
					
					if(id.trim() != "" || nombre.trim() != ""){
					
						$("#buscadorForm").submit();
					}
					
				});
				
			});
		
		</script>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>