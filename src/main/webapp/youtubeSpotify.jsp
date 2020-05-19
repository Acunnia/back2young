<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

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

<iframe id="player" type="text/html" width="640" height="360"
  src="http://www.youtube.com/embed/${requestScope.video}?enablejsapi=1"
  frameborder="0"></iframe>

</body>
</html>