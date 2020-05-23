<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="styles/salidaSpoty.css">
<title>Busqueda final</title>
</head>
<body>
<header>
<div class="header">
			<h1>Back2Young</h1>
			<div class="optionsBar">
                
            </div>
        </div>
        <nav>
            <ul>
                <li><a href="index.html">Inicio</a></li>
                <li class="principal">
                    <a href="pruebaSpotify.html">Buscador</a>
                </li>
                <li class="principal">
                    <a href="aboutus.html">About US</a>
                    <ul>
                        <li><a href="aboutus.html">Miembros</a></li>
                        <li><a href="apis.html">Listado de APIS</a></li>
                    </ul>
            </ul>
        </nav>

<div class="divLyrics">
<p class="lyrics">${requestScope.lyricsSong}</p>
</div>

<div class="divSpotify">
<iframe class="iframeSpotify" src="https://open.spotify.com/embed/track/${trackSpotifyId}"  
    			frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
</div>

<div class="divYoutube">
<iframe class="iframeYoutube" id="player" type="text/html" 
  src="http://www.youtube.com/embed/${requestScope.videoSong}?enablejsapi=1" 
  frameborder="0"></iframe>
</div>




</body>
</html>