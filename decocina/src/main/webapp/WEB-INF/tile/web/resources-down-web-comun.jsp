<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value = "/js/v${versionJS}/jquery-1.11.2.min.js"/>"></script>
<script src="<c:url value = "/js/v${versionJS}/bootstrap.min.js"/>"></script>
<script src="<c:url value = "/js/v${versionJS}/jquery.bootstrap-touchspin.min.js"/>"></script>
<script src="<c:url value = "/js/v${versionJS}/bootstrap-dialog.min.js"/>"></script>

<c:if test = "${mostrarAvisoLeyCookies}">
	
	<script>
		
		function cerrarAvisoLeyCookies(){
			
			$("#aviso-ley-cookies").hide();
		}
	
	</script>
	
</c:if>

<script>
		
	$("#buscar").click(function(e){
		
		e.preventDefault();
		
		var q = $("#q").val();
		
		if(q != ""){
		
			$("#buscador").submit();
		}
		
	});

</script>