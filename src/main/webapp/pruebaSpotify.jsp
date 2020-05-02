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

 <iframe src="https://open.spotify.com/embed/track/${requestScope.tracks}" width="1000" height="600" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>

</body>
</html>