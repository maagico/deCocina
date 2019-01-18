<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			Recuperar la contraseña - deCocina
		</title>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="meta-web">
	 	
	 	<meta name="viewport" content="width=device-width; initial-scale=1, user-scalable=yes"/>
	 	
	 	<meta name="robots" content="noindex,nofollow"/>
	
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
	
	<div class="row">
	
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			
			<div class="jumbotron">
			
				<div class="container">
		    		
		    		<form:form id = "clienteRecuperarPasswordForm" action = "recuperar-password" modelAttribute="clienteRecuperarPasswordForm" class="form-horizontal form-sesion" role="form" acceptCharset="UTF-8">
	                
			    		<h3>Recupera la contraseña</h3>
			    		    
			   			<p>
			   				Introduce tu email para recuperar la contraseña
			   			</p>
			   			<p>
			   				
			   				<spring:hasBindErrors name="clienteRecuperarPasswordForm">
	                        
								<c:if test="${errors.hasFieldErrors('clienteRecuperarPassword.email')}">
									<c:set var="emailError" value="has-error" />
								</c:if>
							
	                        </spring:hasBindErrors>
	                        
	                        <div class="input-group <c:out value = "${emailError}"/>">
                            	
                            	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                               	<form:input path="clienteRecuperarPassword.email" class="form-control" placeholder="email" maxlength="100"/>                                        
                           		
                           	</div>
		                    <form:errors path="clienteRecuperarPassword.email" class="form-error"/>
		                    <c:if test = "${error != '' && error != null}">
	                        	<span id="clienteRecuperarPassword.email.errors" class="form-error"><c:out value = "${error}"/></span>
	                        </c:if>
			   			</p>
			   			<p>
							
							<a class = "btn btn-success col-xs-12 col-sm-4 col-md-3 col-lg-3 text-right" href="javascript:doSubmit();" >Recuperar contraseña</a>
							
			   			</p>
		   			
		   			</form:form>
		   			
		   		</div>
		   	</div>
		   	
		</div>
	
	</div>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-web">
		
		<script>
		
			function doSubmit()
			{
				$("#clienteRecuperarPasswordForm").submit();	
			}
			
		</script>
			
	</tiles:putAttribute>
	
</tiles:insertDefinition>