<?xml version="1.0" encoding="UTF-8"?>

<%@ page contentType="text/xml; charset=UTF-8" %> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
	
	<url> 
       <loc><c:out value = "${url}"/></loc>
       <changefreq>monthly</changefreq>
       <priority>1</priority>
    </url>
    
    <c:forEach var="categoria" items="${categorias}">
    	<url>
	       <loc><c:out value = "${url}${categoria.urlAmigable}"/></loc>
	       <changefreq>monthly</changefreq>
	       <priority>0.7</priority>
	    </url>
    </c:forEach>
    
    <c:forEach var="articulo" items="${articulos}">
    	<url>
	       <loc><c:out value = "${url}${articulo.urlAmigable}"/></loc>
	       <lastmod><fmt:formatDate type="date"  pattern = "yyyy-MM-dd" value="${articulo.fechaModificacion.time}" /></lastmod>
	       <changefreq>monthly</changefreq>
	       <priority>0.8</priority>
	    </url>
    </c:forEach>
    
    <url> 
       <loc><c:out value = "${url}donde-estamos"/></loc>
       <changefreq>monthly</changefreq>
       <priority>0.5</priority>
    </url>
    
    <url> 
       <loc><c:out value = "${url}quienes-somos"/></loc>
       <changefreq>monthly</changefreq>
       <priority>0.5</priority>
    </url>
    
</urlset>