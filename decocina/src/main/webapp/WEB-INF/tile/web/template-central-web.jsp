<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setLocale value="es_ES" scope="session"/>

<!DOCTYPE html>
<html lang="es">
	
	<head>
		
		<tiles:insertAttribute name="title-web"/>
		<tiles:insertAttribute name="meta-web"/>
		<tiles:insertAttribute name="resources-up-web-comun"/>
		<tiles:insertAttribute name="resources-up-web"/>
	
	</head>
	
	<body>
		
		<div class = "container">
			
			<tiles:insertAttribute name="header-web"/>
		   	
		   	<tiles:insertAttribute name="body-web"/>
		    
		    <tiles:insertAttribute name="footer-web"/>
		    			    	    
	    	<tiles:insertAttribute name="resources-down-web-comun"/>
	    	<tiles:insertAttribute name="resources-down-web"/>
	    	
	    </div>
	    
	</body>
    	
</html>
