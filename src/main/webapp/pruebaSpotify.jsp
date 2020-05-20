<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" href="styles/buscadorSpotify.css">
<meta charset="ISO-8859-1">
<title>Prueba Api Spotify</title>
</head>
<body id="spotify">

<table style="width:100%">
  	<tr>
    	<th>Song</th>
    	<th>More info</th>
  	</tr>
	<c:forEach items="${tracks}" var="track">
		<tr>
    		<td>
    			<iframe src="https://open.spotify.com/embed/track/${track.id}" width="500" height="80" 
    			frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
    		</td>
    		<td>
				<form action="Back2youngController" method="post">
					<input type="hidden" name="songName" value="${track.name}"/>
					<input type="hidden" name="artistName" value="<c:out value="${track.artists[0].name}"/>"/>
					<button type="submit">${track.name}</button>
				</form>
			</td>
  		</tr>
	</c:forEach>
</table>

</body>
</html>