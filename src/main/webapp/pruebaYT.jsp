<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles/buscadorYT.css">

<title>Prueba API YouTube</title>
</head>
<body id="youtube">

<iframe id="player" type="text/html" width="640" height="360"
  src="http://www.youtube.com/embed/${requestScope.videoID}?enablejsapi=1"
  frameborder="0"></iframe>

</body>
</html>