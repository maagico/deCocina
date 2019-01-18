<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertDefinition name="template-central-admin">
	
    <tiles:putAttribute name="body-admin">
		
		<div class="row">
				
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				
				<a id = "exportar-stock" href = "#">
					Exportar stock a excel	
				</a>
				<br>
				<br>
				<form:form id = "importarForm" class="form-horizontal" role="form" acceptCharset="UTF-8" enctype="multipart/form-data">
					
					<a id = "importar-stock" href ="">Importar excel de stock</a> 
					
					<input type="file" id="mfImagen" name="mfImagen" class = "fileupload"/>
                              		
                
                </form:form>
                
			</div>
				
		</div>
	
		<form id = "accionForm" name = "accionForm"  method = "POST">
		</form>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-admin">
	
		<script>
		
			$(document).ready(function() {
				
				$("#exportar-stock").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Exportación del Stock</h4>',
			            message: 'Se exportará el Stock a un excel ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Aceptar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                
			                	$("#accionForm").attr("action", "exportar-stock");
								$("#accionForm").submit();
			                }
			            }
			        });
					
				});
				
				$("#importar-stock").click(function(e){
					
					e.preventDefault();
					
					var nombreFichero = $("#mfImagen").val();
					
					if(nombreFichero != ""){
						
						BootstrapDialog.confirm({
							
							title: '<h4>Importación del Stock</h4>',
				            message: 'Se importará el Stock ¿Continuar?',
				            type: BootstrapDialog.TYPE_WARNING,
				            closable: true,
				            draggable: true,
				            btnCancelLabel: 'Cancelar',
				            btnOKLabel: 'Aceptar',
				            btnOKClass: 'btn-primary', 
				            callback: function(result) {
				                
				                if(result) {
				                
				                	$("#importarForm").attr("action", "importar-stock");
									$("#importarForm").submit();
				                }
				            }
				        });
					}
					
				});
			});
		
		</script>
	
	</tiles:putAttribute>
	
</tiles:insertDefinition>