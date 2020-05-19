<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/buscadorSpotify.css">
<meta charset="ISO-8859-1">
<title>Prueba Api Spotify</title>
</head>
<body id="spotify">

<iframe src="https://open.spotify.com/embed/track/${requestScope.tracks}" width="500" height="300" 
frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>

<iframe src="https://open.spotify.com/embed/track/${requestScope.tracks1}" width="500" height="300" 
frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>

<iframe src="https://open.spotify.com/embed/track/${requestScope.tracks2}" width="500" height="300" 
frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>

<iframe src="https://open.spotify.com/embed/track/${requestScope.tracks3}" width="500" height="300" 
frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>

<iframe src="https://open.spotify.com/embed/track/${requestScope.tracks4}" width="500" height="300" 
frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>

</body>
</html>