<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			Acceso a deCocina.es - deCocina
		</title>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="meta-web">
	 	
	 	<meta name="viewport" content="width=device-width; initial-scale=1, user-scalable=yes"/>
	 	
	 	<meta name="robots" content="noindex,nofollow"/>
	
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
	
	<div class="row">
	
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<div class="panel panel-default">
                 	
                	<div class="panel-heading">
                    	
                    	<div class="panel-title">Soy un nuevo cliente</div>
                    	
                	</div>  
                	<div class="panel-body" >
                    	
                    	<form:form id = "clienteForm" action = "alta-cliente" modelAttribute="clienteForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
							
							<form:hidden path="from"/>
							
							<spring:hasBindErrors name="clienteForm">
						                   
								<c:if test="${errors.hasFieldErrors('cliente.nombre')}">
									<c:set var="nombre" value="has-error" />
								</c:if>
								
								<c:if test="${errors.hasFieldErrors('cliente.apellidos')}">
									<c:set var="apellidos" value="has-error" />
								</c:if>
								
								<c:if test="${errors.hasFieldErrors('cliente.direccion.zona')}">
									<c:set var="zona" value="has-error" />
								</c:if>
	
                   			</spring:hasBindErrors>
	
	
                         <div class="form-group <c:out value = "${nombre}"/>">
                      		<form:label path="cliente.nombre" class="col-md-3 control-label">Nombre (*)</form:label>
                      		<div class="col-md-9">
                       		<form:input path="cliente.nombre" class="form-control" placeholder="Nombre" maxlength="100"/>
                       		<form:errors path="cliente.nombre" class="form-error"/>
                      	</div>
                  	</div>
                    
                  	<div class="form-group <c:out value = "${apellidos}"/>">
                      	<form:label path="cliente.apellidos" class="col-md-3 control-label">Apellidos (*)</form:label>
                   		<div class="col-md-9">
                          	<form:input path="cliente.apellidos" class="form-control" placeholder="Apellidos" maxlength="100"/>
                           <form:errors path="cliente.apellidos" class="form-error"/>
                      	</div>
                   </div>
                   
                   <div class="form-group <c:out value = "${email}"/>">
                      	<form:label path="cliente.email" class="col-md-3 control-label">Email (*)</form:label>
	                   	<div class="col-md-9">
                          	<form:input path="cliente.email" class="form-control" placeholder="Email" maxlength="100"/>
                          	<form:errors path="cliente.email" class="form-error"/>
                      	</div>
                   </div>
                 
                   	<div class="form-group <c:out value = "${password}"/>">
                       	<form:label path="cliente.password" class="col-md-3 control-label">Contraseña (*)</form:label>
                       	<div class="col-md-9">
                           	<form:password path="cliente.password" class="form-control" placeholder="Contraseña"/>
                       		<form:errors path="cliente.password" class="form-error"/>
                       	</div>
                   	</div>
                        	
                    <div class="form-group <c:out value = "${telefono}"/>">
                      	<form:label path="cliente.telefono" class="col-md-3 control-label">Teléfono (*)</form:label>
                   		<div class="col-md-9">
                          	<form:input path="cliente.telefono" class="form-control" placeholder="Teléfono" maxlength="15"/>
                          	<form:errors path="cliente.telefono" class="form-error"/>
                      	</div>
                   </div>
                        	
                   <div class="form-group <c:out value = "${calle}"/>">
                      	<form:label path="cliente.direccion.calle" class="col-md-3 control-label">Calle (*)</form:label>
                  		 <div class="col-md-9">
                          	<form:input path="cliente.direccion.calle" class="form-control" placeholder="Calle, número, piso y letra " maxlength="150"/>
                          	<form:errors path="cliente.direccion.calle" class="form-error"/>
                      	</div>
                   </div>
                        	
                   <div class="form-group <c:out value = "${codigoPostal}"/>">
                      	<form:label path="cliente.direccion.codigoPostal" class="col-md-3 control-label">Código postal (*)</form:label>
                   	<div class="col-md-9">
                          	<form:input path="cliente.direccion.codigoPostal" class="form-control" placeholder="Código postal" maxlength="5"/>
                          	<form:errors path="cliente.direccion.codigoPostal" class="form-error"/>
                      	</div>
                   </div>
                        	
                       	<div class="form-group <c:out value = "${zona}"/>">
		                    <form:label path="cliente.direccion.zona.id" class="col-md-3 control-label">Provincia (*)</form:label>
		                   	<div class="col-md-9">
		                    <form:select path="cliente.direccion.zona.id" class = "form-control">
						   		<form:option value="" label="Selecciona tu provincia"/>
						   		<form:options items="${provincias}" itemValue="id" itemLabel="nombre"/>
							</form:select> 
							<form:errors path="cliente.direccion.zona" class="form-error"/>
                      	</div>
                   </div>
                        	
                   <div class="form-group <c:out value = "${poblacion}"/>">
	               		<form:label path="cliente.direccion.poblacion" class="col-md-3 control-label">Población (*)</form:label>
	                   	<div class="col-md-9">
                         	<form:input path="cliente.direccion.poblacion" class="form-control" placeholder="Población" maxlength="100"/>
                         	<form:errors path="cliente.direccion.poblacion" class="form-error"/>
                     	</div>
                   </div>
                        	
                   	<div class="form-group">
                   		<div class="col-md-3"></div>
                       	<div class="col-md-9">
                       	<label>
                       		<form:checkbox path="politica"/> He leído y acepto <a href = "javascript:abrirPopup();">la política de privacidad</a>
                       	</label>
                       	<div id ="error_politica"></div>
                       	<form:errors path="politica" class="form-error"/>
                       	</div>
                   		
                   	</div>
                        	
                        	 
                    <div  class="form-group">
                        
                        <div class="col-md-offset-3 col-sx-9 col-sm-12 col-md-9 col-lg-9">
                            <button id = "validar-form" type="button" class="btn btn-success btn-large btn-block">Registrarse</button>
                        </div>                                           
                            
                    </div>
                             	 
					</form:form>
					
				</div>
			</div>
			
		</div>
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<div class="panel panel-default">
                
                <div class="panel-heading" >
                       <div class="panel-title">Ya soy cliente</div>	                        
                    </div>     

                    <div class="panel-body" >

						<form:form id = "login" action = "login-cliente" modelAttribute="clienteLoginForm" class="form-horizontal form-sesion" role="form" acceptCharset="UTF-8">
	                       
	                       	<form:hidden path="from"/>
	                       
	                       	<c:if test = "${error != '' && error != null}">
	                        	<p class = "error-login"><c:out value = "${error}"/></p>
	                        		
	                        	<c:set var="emailError" value="has-error" />
								<c:set var="passwordError" value="has-error" />
								
	                        </c:if>
	                        
	                        <spring:hasBindErrors name="clienteLoginForm">
	                        
								<c:if test="${errors.hasFieldErrors('clienteLogin.email')}">
									<c:set var="emailError" value="has-error" />
								</c:if>
								
								<c:if test="${errors.hasFieldErrors('clienteLogin.password')}">
									<c:set var="passwordError" value="has-error" />
								</c:if>
							
	                        </spring:hasBindErrors>
	                        
	                        <div class="input-group  <c:out value = "${emailError}"/>">
                            	
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                               	<form:input path="clienteLogin.email" class="form-control"  placeholder="email" maxlength="100"/>                                        
                           		
                           	</div>
	                        <label class="text-danger" for="usuario"><form:errors path="clienteLogin.email"/></label>   
	                        
	                        <div class="input-group <c:out value = "${passwordError}"/>">
	                        
	                        	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
	                        	<form:input path="clienteLogin.password" type="password" class="form-control" placeholder="contraseña" maxlength="15"/>
	                        	
	                        </div>
	                        <label class="text-danger" for="password"><form:errors path="clienteLogin.password"/></label>
	      
	
	                        <div class="form-group botones-iniciar-sesion">
	                              
	                        	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 controls boton-iniciar-sesion">
	                            	
	                            	<a href="javascript:doSubmit();" class="btn btn-success btn-large btn-block">Iniciar sesión</a>	
	                           	
	                           	</div>
	                           	
							</div>
							
							<div class="form-group">
                                
                                <div class="col-md-12 control no-recuerdo">
                                	
                                	<a id = "recuperar-password" href="<c:url value ="recuperar-password"/>">No recuerdo mi contraseña</a>
                                	
                                </div>
                                
                            </div>
						</form:form>
						
						
					</div>                     
				</div>  
        	</div>
		</div>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-web">
	
		
		<script src="<c:url value = "/js/jquery.validate.min.js"/>"></script>
		<script src="<c:url value = "/js/validaciones.form.alta.js"/>"></script>
		
		<script>
		
			function doSubmit()
			{
				$("#login").submit();	
			}
			
		</script>
			
	</tiles:putAttribute>
	
</tiles:insertDefinition>