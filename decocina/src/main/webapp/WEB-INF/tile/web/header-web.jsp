<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<nav class="navbar navbar-default">
	<div class="container">
    	
    	<div class="navbar-header">
      		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		        <span class="sr-only">Navegación</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
      		</button>
      		<h1 class = "index-titulo"><a class="navbar-brand" href="<c:url value = "/"/>">deCocina</a></h1>
    	</div>
    
	    <div id="navbar" class="collapse navbar-collapse">
	    	<ul class="nav navbar-nav">
	        	
	        	<c:forEach var = "categoriaMenu" items="${categoriasMenu}">
	        	
		        	<li>
		        		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		        			<c:out value = "${categoriaMenu.nombre}"/><span class="caret"></span>
		        		</a>
		        		<ul class="dropdown-menu" role="menu">
		                	
		                	<c:forEach var = "subcategoriaMenu" items="${categoriaMenu.subCategorias}">
		                  		<li><a href="<c:url value = "/${subcategoriaMenu.urlAmigable}"/>"><c:out value = "${subcategoriaMenu.nombre}"/></a></li>
		                	</c:forEach>
		               	</ul>
		                
		        	</li>
		        	
	        	</c:forEach>
	        	
	      	</ul>
        	
	    </div>
	    
	</div>
</nav>

<div class="row">
	
	<div class="col-sm-5 col-md-6 col-lg-6 submenu-bottom-padding">
		
		<form role="search" id = "buscador" name = "buscador" action = "<c:url value ="/buscar"/>" method = "get">
	    	
	    	<div class="input-group">
	    			
	        	<input id = "q" type="text" class="form-control" placeholder="Búsqueda" name="q">
	           	
	           	<div class="input-group-btn">
	               	<button id = "buscar" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button>
				</div>
			
			</div>
	   </form>
	   	   
	</div>
	
	<div class="col-xs-12 col-sm-7 col-md-6 col-lg-6 submenu-bottom-padding">
	
		<div class = "cesta-pedido">
			<a href = " <c:url value = "/iniciar-sesion"/>" class = "mi-cuenta">Mi cuenta</a>
			
			<c:if test = "${mostrarCesta || mostrarCesta == null}">
				
				<a href = "<c:url value = "/ver-cesta"/>" class = "">Cesta<span class="badge ver-cesta"><c:out value = "${numeroArticulosCesta}"/></span></a>
				
			</c:if>
			
			<a href = "<c:url value = "/cliente/realizar-pedido"/>" class = "realizar-pedido">Realizar pedido</a>
			
			<c:if test = "${!sessionScope.CLIENTE_WEB_LOGUEADO.esInvitado}">
				<a href = " <c:url value = "/cliente/salir"/>" class = "">Salir</a>
			</c:if>
			
		</div>
	</div>

</div>

<div class="row">
	
	<div class="col-sm-12 col-md-12">
		
		<ol <c:if test = "${enArticuloDetalle}">id = "miga"</c:if> class="breadcrumb">
			
			<c:forEach var = "miga" items="${migasPan.migasPan}">
				
				<c:if test = "${miga.enlace != null}">
					
					<li>
						<a href="<c:url value = "${miga.enlace}"/>">
						
							<span <c:if test = "${miga.ultima}"> class = "hidden-xs"</c:if>>
								<c:out value = "${miga.texto}"/>
							</span>	
							
						</a>
					</li>
				
				</c:if>
					
				<c:if test = "${miga.enlace == null}">
					
					<li class = "active">
						<span <c:if test = "${miga.ultima}"> class = "hidden-xs"</c:if>>
							<c:out value = "${miga.texto}"/>
						</span>
					</li>
					
				</c:if>
				
			</c:forEach>
		</ol>
		
	</div>
	
</div>

<c:if test = "${msj_error != '' && msj_error != null}">
	
	<div class="row">
		
		<div class="container">
		
			<div class="alert alert-danger text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<c:out value = "${msj_error}"/>
			</div>
		
		</div>
	
	</div>
	
</c:if>
	