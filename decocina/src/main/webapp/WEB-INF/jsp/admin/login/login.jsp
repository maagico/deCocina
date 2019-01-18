<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<html>
	<head>
		
		<meta name="viewport" content="width=device-width; initial-scale=1, user-scalable=yes"/>
		
		<link rel="stylesheet" href="<c:url value = "/css/v${versionCSS}/css-admin.css"/>" media="screen">
		<link rel="stylesheet" href="<c:url value = "/css/v${versionCSS}/bootstrap.min.css"/>" media="screen">
		
		<script src="<c:url value = "/js/v${versionJS}/jquery-1.11.2.min.js"/>"></script>
		<script src="<c:url value = "/js/v${versionJS}/bootstrap.min.js"/>"></script>
		
		<script>
		
			function doSubmit()
			{
				$("#login").submit();	
			}
			
		</script>
		
	</head>
	
	<body>
	
		<div class ="container">
			
			<div class = "row">
 			
 				<div class="col-md-4"></div>
 				<div class="col-md-4">
			
					<div class="panel panel-default panel-login">
		               
		               <div class="panel-body">
	
		                    <form:form id = "login" action = "login" modelAttribute="administrador" class="form-horizontal form-sesion" role="form">
		                       
		                       	<c:if test = "${error != '' && error != null}">
		                        	<p class = "error-login"><c:out value = "${error}"/></p>
		                        		
		                        	<c:set var="usuarioError" value="has-error" />
									<c:set var="passwordError" value="has-error" />
									
		                        </c:if>
		                        
		                        <spring:hasBindErrors name="administrador">
		                        
									<c:if test="${errors.hasFieldErrors('usuario')}">
										<c:set var="usuarioError" value="has-error" />
									</c:if>
									
									<c:if test="${errors.hasFieldErrors('password')}">
										<c:set var="passwordError" value="has-error" />
									</c:if>
								
		                        </spring:hasBindErrors>
		                        
		                        <div class="input-group form-sesion-input <c:out value = "${usuarioError}"/>">
	                            	
	                            	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
	                               	<form:input id="usuario" type="text" class="form-control" path="usuario" placeholder="usuario" maxlength="15"/>                                        
	                           		
	                           	</div>
		                        <label class="text-danger" for="usuario"><form:errors path="usuario"/></label>   
		                        
		                        <div class="input-group form-sesion-input <c:out value = "${passwordError}"/>">
		                        
		                        	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		                        	<form:input id="password" type="password" class="form-control" path="password" placeholder="contraseña" maxlength="15"/>
		                        	
		                        </div>
		                        <label class="text-danger" for="password"><form:errors path="password"/></label>
		                        
		                        <%-- 
		                        <div class="input-group form-sesion-input">
		                        
		                           	<div class="checkbox">
		                             
		                               	<form:label for = "recuerdame1" path = "recuerdame">
		                                	<form:checkbox path="recuerdame"/> Recuérdame
		                            	</form:label>
		                            
		                            </div>
		                        
		                        </div>
								--%>
								
		                        <div class="form-group botones-iniciar-sesion">
		                              
		                        	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 controls boton-iniciar-sesion">
		                            	
		                            	<a href="javascript:doSubmit();" class="btn btn-success btn-large btn-block">Iniciar sesión</a>	
		                           	
		                           	</div>
		                           	
								</div>
								
							</form:form>
							
						</div>                     
					</div>  
	        	</div>
	        	<div class="col-md-4"></div>
			
			</div>
		</div>
		
	</body>
</html>