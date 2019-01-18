<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="es">
	
	<head>
		
		<tiles:insertAttribute name="meta-admin"/>
		<tiles:insertAttribute name="resources-up-admin-comun"/>
		<tiles:insertAttribute name="resources-up-admin"/>
	
	</head>
	
	<body>
		
		<div class = "container">
			
			<tiles:insertAttribute name="header-admin"/>
		    	
		    <tiles:insertAttribute name="body-admin"/>
		    
		    <tiles:insertAttribute name="footer-admin"/>
		    			    	    
		    <tiles:insertAttribute name="resources-down-admin-comun"/>
		    
		    <tiles:insertAttribute name="resources-down-admin"/>
	    
	    </div>
	    
	</body>
    	
</html>
