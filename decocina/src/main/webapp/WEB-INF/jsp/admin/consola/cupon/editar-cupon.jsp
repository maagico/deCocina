<%@ page contentType="text/html; charset=UTF-8" %> 

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.decocina.es/tags" prefix="decocina"%>


<tiles:insertDefinition name="template-central-admin">

    <tiles:putAttribute name="body-admin">
			
			<div class = "row">
				
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
					
					<div class="alert alert-info" role="alert">
								
						<c:if test = "${cuponForm.esCreacion}">
							Creando Cupón
						</c:if>
						
						<c:if test = "${!cuponForm.esCreacion}">
							Modificando Cupón
						</c:if>
						
					</div>
					
				</div>
				
			</div>
			
			<div class="row">
	
				<div class ="col-xs-12 col-sm-12 col-md-10 col-lg-10">
					
					<c:if test = "${cuponForm.esCreacion}">
						
						<c:set var="accion" value="cupon-insert" />
					
					</c:if>
					
					<c:if test = "${!cuponForm.esCreacion}">
						
						<c:set var="accion" value="cupon-update" />
					
					</c:if>
					
					<form:form id = "cuponForm" action = "${accion}" modelAttribute="cuponForm" class="form-horizontal" role="form" acceptCharset="UTF-8">
					
						<decocina:input formulario="${cuponForm}"/>
						
						<spring:hasBindErrors name="cuponForm">
		                        
							<c:if test="${errors.hasFieldErrors('cupon.nombre')}">
								<c:set var="nombre" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('cupon.tipoImporte')}">
								<c:set var="tipoImporte" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('cupon.descuento')}">
								<c:set var="descuento" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('cupon.fechaDesde')}">
								<c:set var="fechaDesde" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('cupon.fechaHasta')}">
								<c:set var="fechaHasta" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('cupon.precioMinimo')}">
								<c:set var="precioMinimo" value="has-error" />
							</c:if>
							
							<c:if test="${errors.hasFieldErrors('cupon.numeroUsos')}">
								<c:set var="numeroUsos" value="has-error" />
							</c:if>
							
						
                        </spring:hasBindErrors>
					
                        <div class="form-group <c:out value = "${nombre}"/>" >
                           	<form:label path="cupon.nombre" class="col-md-3 control-label">Nombre (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="cupon.nombre" class="form-control" placeholder="Nombre" maxlength="99"/>
                            	<form:errors path="cupon.nombre" class="form-error"/>
                           	</div>
                       	</div>
                        
                        <div class="form-group <c:out value = "${tipoImporte}"/>">
                           	<form:label path="cupon.tipoImporte" class="col-md-3 control-label">Tipo de importe (*)</form:label>
                        	<div class="col-md-9">
                            	<form:select path="cupon.tipoImporte" class = "form-control">
								   <form:option value="" label="Selecciona el tipo..."/>
								   
								   <form:option value="0" label="Porcentaje"/>
								   <form:option value="1" label="Importe fijo"/>
								   
								</form:select> 
								
								<form:errors path="cupon.tipoImporte" class="form-error"/>
                           	</div>
                        </div>
                        
                        <div class="form-group <c:out value = "${descuento}"/>" >
                           	<form:label path="cupon.descuento" class="col-md-3 control-label">Descuento (*)</form:label>
                           	<div class="col-md-9">
                            	<form:input path="cupon.descuento" class="form-control" placeholder="Descuento"/>
                            	<form:errors path="cupon.descuento" class="form-error"/>
                           	</div>
                       	</div>
                         
						
						<div class="form-group <c:out value = "${fechaDesde}"/>" >
                           	<form:label path="cupon.fechaDesde" class="col-md-3 control-label">Fecha desde</form:label>
                           	<div class="col-md-9">
                            	<form:input path="cupon.fechaDesde" class="form-control" placeholder="Fecha desde"/>
                            	<form:errors path="cupon.fechaDesde" class="form-error"/>
                           	</div>
                       	</div>
                       	
                       	
                       	<div class="form-group <c:out value = "${fechaHasta}"/>" >
                           	<form:label path="cupon.fechaHasta" class="col-md-3 control-label">Fecha hasta</form:label>
                           	<div class="col-md-9">
                            	<form:input path="cupon.fechaHasta" class="form-control" placeholder="Descuento"/>
                            	<form:errors path="cupon.fechaHasta" class="form-error"/>
                           	</div>
                       	</div>                         
                         
                        <div class="form-group <c:out value = "${precioMinimo}"/>" >
                        	<form:label path="cupon.precioMinimo" class="col-md-3 control-label">Precio mínimo</form:label>
                           	<div class="col-md-9">
                            	<form:input path="cupon.precioMinimo" class="form-control" placeholder="Precio mínimo"/>
                            	<form:errors path="cupon.precioMinimo" class="form-error"/>
                           	</div>
                       	</div>
                        
                        <div class="form-group <c:out value = "${numeroUsos}"/>" >
                        	<form:label path="cupon.numeroUsos" class="col-md-3 control-label">Número de usos</form:label>
                           	<div class="col-md-9">
                            	<form:input path="cupon.numeroUsos" class="form-control" placeholder="Precio mínimo"/>
                            	<form:errors path="cupon.numeroUsos" class="form-error"/>
                           	</div>
                       	</div>
                        
                       	<div class="form-group">
                           	<form:label path="cupon.descripcion" class="col-md-3 control-label">Descripción</form:label>
                        	<div class="col-md-9">
                               	<form:textarea path="cupon.descripcion" class="form-control" placeholder="Descripción" rows="4" maxlength="255"/>
                               	<form:errors path="cupon.descripcion" class="form-error"/>
                           	</div>
                        </div>
                       	 
                        <div class="form-group">
                            
                        	<div class="col-md-offset-2 col-xs-2 col-sm-1 col-md-2 col-lg-2">
								
								
								<c:if test = "${!cuponBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/cupon/cupon-find?"/><decocina:parametros busqueda='${cuponBuscador}'/>" class="btn btn-primary">Volver</a>
									
								</c:if>
								
								<c:if test = "${cuponBuscador.hayBusqueda}">
								
									<a href = "<c:url value = "/admin/consola/cupon/cupon-search?"/><decocina:parametros busqueda="${cuponBuscador}"/>" class="btn btn-primary">Volver</a>
								
								</c:if>
								
							</div>
							
							<div class="col-xs-10 col-sm-11 col-md-8 col-lg-8 text-right">
								
								<c:if test = "${cuponForm.esCreacion}">
								
									<a id = "accion" href = "#" class="btn btn-success text-right">Crear</a>	
								
								</c:if>
								
								<c:if test = "${!cuponForm.esCreacion}">
									
									<a id = "eliminar" href = "#" class="btn btn-danger">Eliminar</a>
									<a id = "accion" href = "#"  class="btn btn-success text-right">Modificar</a>
								
								</c:if>
								
							</div>
					
					 	</div>
					                       	 
					</form:form>
				   	   
				</div>
			</div>
			
 	</tiles:putAttribute>
	
	<tiles:putAttribute name="resources-down-admin">
	
		<script>
		
			$(document).ready(function() {
				
				$("#accion").click(function(e){
						
					e.preventDefault();
						
					$("#cuponForm").submit();
						
				});
				
				$("#eliminar").click(function(e){
					
					e.preventDefault();
					
					BootstrapDialog.confirm({
						
						title: '<h4>Cupón</h4>',
			            message: 'Se eliminará el Cupón ¿Continuar?',
			            type: BootstrapDialog.TYPE_WARNING,
			            closable: true,
			            draggable: true,
			            btnCancelLabel: 'Cancelar',
			            btnOKLabel: 'Eliminar',
			            btnOKClass: 'btn-primary', 
			            callback: function(result) {
			                
			                if(result) {
			                	
			                	$("#cuponForm").attr("action", "cupon-delete");
								$("#cuponForm").submit();
			                }
			            }
			        });
					
				});
			});
		
		</script>
	
	</tiles:putAttribute>

</tiles:insertDefinition>