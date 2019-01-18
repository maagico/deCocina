<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value = "/css/v${versionCSS}/bootstrap.min.css"/>" media="screen">
<link rel="stylesheet" href="<c:url value = "/css/v${versionCSS}/jquery.bootstrap-touchspin.min.css"/>" media="screen">
<link rel="stylesheet" href="<c:url value = "/css/v${versionCSS}/css1.css"/>" media="screen">

<link rel="icon" href="<c:url value = "/img/app/favicon1.ico"/>" type="image/x-icon"/>

<script>
	
	<%--
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	ga('create', 'UA-26801501-1', 'auto');
	ga('send', 'pageview');
	
	<c:if test = "${transaccionAnalytics != null}">
	
		ga('require', 'ecommerce');
	
		ga('ecommerce:addTransaction', {
			   'id': '<c:out value = "${transaccionAnalytics.id}"/>',                     
			   'revenue': '<c:out value = "${transaccionAnalytics.revenue}"/>',               
			   'shipping': '<c:out value = "${transaccionAnalytics.shipping}"/>',                  
			   'tax': '<c:out value = "${transaccionAnalytics.tax}"/>'
			});
		
		<c:forEach items = "${transaccionAnalytics.articulosAnalytics}" var = "articuloAnalytics">
		
			ga('ecommerce:addItem', {
			   'id': '<c:out value = "${transaccionAnalytics.id}"/>',               
			   'name': '<c:out value = "${articuloAnalytics.name}"/>',
			   'price': '<c:out value = "${articuloAnalytics.price}"/>',
			   'quantity': '<c:out value = "${articuloAnalytics.quantity}"/>'
			});
		
		</c:forEach>
		
		ga('ecommerce:send');
		
	</c:if>
	--%>
</script>
