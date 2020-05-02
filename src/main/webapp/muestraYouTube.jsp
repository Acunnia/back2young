<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html>
 <html>
 <head>
 	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 	<link rel="stylesheet" href="styles/buscadores.css">
 	<title>Busqueda Youtube</title>
 </head>
 <body id="youtube">
 	<fieldset id="videos">
 		<legend>Youtube search for <c:out value="${param.searchQuery}"/></legend>
 		<c:forEach items="${requestScope.videos}" var="video">
			<iframe id="player" type="text/html" width="640" height="360"
  		src="http://www.youtube.com/embed/${video.id.videoId}?enablejsapi=1&origin=http://example.com"
  		frameborder="0"></iframe>
	</c:forEach>
 	</fieldset>
 	
 
 </body>
 </html>