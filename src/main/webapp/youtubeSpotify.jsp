<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<iframe src="https://open.spotify.com/embed/track/${trackSpotifyId}" width="500" height="80" 
    			frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>



<iframe id="player" type="text/html" width="640" height="360"
  src="http://www.youtube.com/embed/${requestScope.videoSong}?enablejsapi=1"
  frameborder="0"></iframe>
  
<p>${requestScope.lyricsSong}</p>

</body>
</html>