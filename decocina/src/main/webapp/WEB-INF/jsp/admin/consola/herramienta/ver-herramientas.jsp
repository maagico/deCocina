<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertDefinition name="template-central-admin">
	
    <tiles:putAttribute name="body-admin">
		
		<div class="row">
				
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
				<a id = "borrar-cache-ibatis" href = "#">
					Borrar caché de Ibatis	
				</a>
				<br>
				
				<c:if test = "${!activo}">
					<a id = "cachear-web" href = "#">
						Cachear web.	
					</a>
				</c:if>
				
				<c:if test = "${activo}">
					Cachear web. El trabajo está ejecutándose. <a id = "cachear-web-delete" href = "#">Parar</a>
				</c:if>
			</div>
				
		</div>
	
		<form id = "accionForm" name = "accionForm"  method = "POST">
		</form>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-admin">
	
		<script>
		
			$(document).ready(function() {
				
				$("#borrar-cache-ibatis").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Caché de Ibatis</h4>',
			            message: 'Se borrará la caché de Ibatis ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Aceptar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#accionForm").attr("action", "cache-ibatis-delete");
								$("#accionForm").submit();
			                }
			            }
			        });
					
				});
				
				
				$("#cachear-web").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Cachear web</h4>',
			            message: 'Se cacheará la web ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Aceptar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#accionForm").attr("action", "cachear-web");
								$("#accionForm").submit();
			                }
			            }
			        });
					
				});
				
				$("#cachear-web-delete").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Cachear web</h4>',
			            message: 'Se parará el trabajo de cacheo de la web ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Aceptar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#accionForm").attr("action", "cachear-web-delete");
								$("#accionForm").submit();
			                }
			            }
			        });
					
				});

			});
		
		</script>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>