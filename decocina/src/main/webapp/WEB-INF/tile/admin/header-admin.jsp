<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>

<nav class="navbar navbar-default">
	<div class="container">
    	
    	<div class="navbar-header">
      		
      		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		        <span class="sr-only">Navegación</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
      		</button>
      		
      		<a class="navbar-brand" href="<c:url value = "/admin/consola/inicio/"/>">deCocina Admin</a>
    	
    	</div>
    
	    <div id="navbar" class="collapse navbar-collapse">
	    	
	    	<ul class="nav navbar-nav">
	        	
	        	<li>
	        		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	        			Catálogo<span class="caret"></span>
	        		</a>
	                
	                <ul class="dropdown-menu" role="menu">
	                  
	                  <li><a href="<c:url value = "/admin/consola/categoria/categoria-find?path=-1"/>">Categorías y artículos</a></li>
	                  
	                  <li><a href="<c:url value = "/admin/consola/fabricante/fabricante-find"/>">Fabricantes</a></li>
	               		
	               	  <%-- 
	               	  <li><a href="<c:url value = "/admin/consola/cupon/cupon-find"/>">Cupones</a></li>	
	               	  --%>
	                </ul>
	                
	        	</li>
	        
	        	<li>
	        		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	        			Clientes<span class="caret"></span>
	        		</a>
	              	
	                <ul class="dropdown-menu" role="menu">
	                  
	                  	<li><a href="<c:url value = "/admin/consola/cliente/cliente-find"/>">Clientes</a></li>
	                  	<li><a href="<c:url value = "/admin/consola/pedido/pedido-find"/>">Pedidos</a></li>
	                
	                </ul>
	                
	        	</li>
	        	
	        	<li>
	        		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	        			Configuración<span class="caret"></span>
	        		</a>
	              	
	                <ul class="dropdown-menu" role="menu">
	                  
	                  	 
	                 	<li><a href="<c:url value = "/admin/consola/zona/zona-find"/>">Zonas</a></li>
	                  	
	                		<li><a href="<c:url value = "/admin/consola/forma-envio/forma-envio-find"/>">Formas de Envío</a></li>
	                	
	                		<li><a href="<c:url value = "/admin/consola/forma-pago/forma-pago-find"/>">Formas de Pago</a></li>
	                  	
	                  	<li><a href="<c:url value = "/admin/consola/impuesto/impuesto-find"/>">Impuestos</a></li>
	                
	                </ul>
	                
	        	</li>
	        	
	        	<%--
	        	<li>
	        		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	        			Herramientas<span class="caret"></span>
	        		</a>
	              	<ul class="dropdown-menu" role="menu">
	                	
	                	<li><a href="<c:url value = "/admin/consola/herramienta/ie-stock-find"/>">Exportar/Importar Stock</a></li>
	                  	  
	                  	<li><a href="<c:url value = "/admin/consola/herramienta/herramienta-find"/>">Herramientas</a></li>
	                  	
	                </ul>
	        	</li>
	        	--%>
	        	
	      	</ul>
	      	
        	<ul class="nav navbar-nav navbar-right">
    			<li class = "nav-derecha-salir">
    				<a href="<c:url value = "/admin/consola/salir"/>">Salir</a>
    			</li>
    		</ul>
    		
	    </div>
	    
	    
	</div>
</nav>


<div class="row">
	
	<div class="hidden-xs col-sm-12 col-md-12 col-lg-12">
		
		<c:if test = "${miga != null}">
			<ol class="breadcrumb">
				
				<li>
					<c:out value = "${miga.texto}"/>		
						
				</li>
			
			</ol>
		</c:if>
		
		<c:if test = "${categoriasMigasPan != null}">
			<ol class="breadcrumb">
				
				<c:forEach items="${categoriasMigasPan}" var="miga">
			  		
			  		<li>
			  		
			  			<a href = "<c:url value = "/admin/consola/categoria/categoria-find?"/>path=${miga.enlace}&<decocina:parametros busqueda='${categoriaArticuloBuscador}' excluir = "path"/>">
							<c:out value = "${miga.texto}"/>	
						</a>
							
			  		</li>
			  			
				</c:forEach>
			
			</ol>
		</c:if>
	</div>
	
</div>

<div class = "row">
				
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
		
		<c:if test = "${mensaje != null}">
			
			<div class="alert alert-success" role="alert">
				<c:out value = "${mensaje}"/>
			</div>
			
		</c:if>
		
		<c:if test = "${mensajeError != null}">
			
			<div class="alert alert-danger" role="alert">
				<c:out value = "${mensajeError}"/>
			</div>
			
		</c:if>
			
	</div>
		
</div>	