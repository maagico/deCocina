<%@ page contentType="text/html; charset=UTF-8" %>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test = "${mostrarAvisoLeyCookies}">

	<div id = "aviso-ley-cookies" class = "cookies">
		<p>
			Este espacio utiliza cookies con el fin de mejorar la experiencia del usuario. 
			Si continúas navegando, consideramos que aceptas su uso. 
		</p>
		<p style= "margin-top:10px;text-align:center">
			<a href = "<c:url value = "/ley-cookies"/>" class="btn btn-primary">
				Más información
			</a>
			<a id = "acepta-ley-cookies" href = "javascript:cerrarAvisoLeyCookies();" class="btn btn-success">
				Aceptar
			</a>
		</p>
	</div>
	
</c:if>

<div>
	<div class="row">
		<div class= "container">	
			<div class="panel panel-default">
					
				<div class="panel-body text-center panel-info-footer">
					
					<span>	
						<a href = "<c:url value = "/quienes-somos"/>">¿Quiénes somos?</a>  
						<a href = "<c:url value = "/donde-estamos"/>">¿Dónde estamos?</a>
					</span>
					<br><br>
					<span>
						<a class = "info-proteccion-datos" href = "<c:url value = "proteccion-datos"/>">Protección de datos</a>
						<a href = "<c:url value = "/envios-devoluciones"/>">Envios y devoluciones</a>
						
					</span>	
					<br><br>
					<span>
						<a class = "info-condiciones-uso" href = "<c:url value = "condiciones-uso"/>">Condiciones de uso</a>
						<a href = "<c:url value = "/ley-cookies"/>">Ley de cookies</a>
					</span>
					<br><br>
					<span class="info-empresa">
						deCocina S.L. CIF: B12345678 Calle del Manojo de Rosas 1, 28015 Madrid
					</span>
					<br>	
					<span class="info-iva">
						Todos nuestros productos incluyen el 21% de IVA
					</span>
				</div>
					
			</div>
		</div>
	</div>
</div>
