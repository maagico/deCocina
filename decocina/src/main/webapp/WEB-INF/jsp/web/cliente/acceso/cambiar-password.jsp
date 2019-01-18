<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>


<tiles:insertDefinition name="template-central-web">
	
	<tiles:putAttribute name="title-web">
		<title>
			Cambiar la contraseña - deCocina
		</title>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="meta-web">
	 	
	 	<meta name="viewport" content="width=device-width; initial-scale=1, user-scalable=yes"/>
	 	
	 	<meta name="robots" content="noindex,nofollow"/>
	
	</tiles:putAttribute>
	
    <tiles:putAttribute name="body-web">
			
			<div class="row">
	
				<div class ="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					
					<div class="jumbotron">
			
						<div class="container">
							<form:form id = "passwordForm" action = "cambiar-password" modelAttribute="clienteCambiarPasswordForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
								
							
								<p>
									<div class="form-group">
			                            
			                        	<div class="col-lg-offset-1 col-xs-12 col-sm-12 col-md-11 col-lg-11">
											<h3>
												Introduce la nueva contraseña
											</h3>	
										</div>
								 	</div>
								 		
									<spring:hasBindErrors name="passwordForm">
					                    
										<c:if test="${errors.hasFieldErrors('clienteCambiarPassword.nuevoPassword')}">
											<c:set var="nuevoPassword" value="has-error" />
										</c:if>
										
										<c:if test="${errors.hasFieldErrors('clienteCambiarPassword.nuevoPasswordConfirmacion')}">
											<c:set var="nuevoPasswordConfirmacion" value="has-error" />
										</c:if>
									
			                        </spring:hasBindErrors>
			                        
			                        <form:hidden path="clienteCambiarPassword.token"/>
			                        
			                       	<div class="form-group <c:out value = "${nuevoPassword}"/>">
			                           	<form:label path="clienteCambiarPassword.nuevoPassword" class="col-md-3 control-label">Nueva contraseña (*)</form:label>
			                        	<div class="col-md-9">
			                               	<form:input type = "password" path="clienteCambiarPassword.nuevoPassword" class="form-control" placeholder="Nueva contraseña" maxlength="15"/>
			                               	<form:errors path="clienteCambiarPassword.nuevoPassword" class="form-error"/>
			                           	</div>
			                        </div>
			                    
			                        <div class="form-group <c:out value = "${nuevoPasswordConfirmacion}"/>">
			                           	<form:label path="clienteCambiarPassword.nuevoPasswordConfirmacion" class="col-md-3 control-label">Confirma la contraseña (*)</form:label>
			                        	<div class="col-md-9">
			                               	<form:input type = "password" path="clienteCambiarPassword.nuevoPasswordConfirmacion" class="form-control" placeholder="Confirme contraseña" maxlength="15"/>
			                               	<form:errors path="clienteCambiarPassword.nuevoPasswordConfirmacion" class="form-error"/>
			                           	</div>
			                        </div>
			                       	 
			                        <div class="form-group">
			                            
			                        	<div class="col-sm-8 col-md-8 col-lg-9">
										</div>
										
										<div class="col-xs-12 col-sm-4 col-md-4 col-lg-3 text-right">
											
											<a id = "accion" href = "#"  class="btn btn-success btn-block text-right">Actualizar contraseña</a>
											
										</div>
								
								 	</div> 
								 	
								</p>
								
							</form:form>
				   	   </div>
				   
					</div>
				</div>
			</div>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-web">
	
		<script>
		
			$(document).ready(function() {
				
				$("#accion").click(function(e){
						
					e.preventDefault();
						
					$("#passwordForm").submit();
						
				});
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>