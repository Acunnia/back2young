<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" href="styles/salidaSpoty.css">
<meta charset="ISO-8859-1">
<title>Prueba Api Spotify</title>
</head>
<style>
scrollbars {overflow:scroll; height:300px;}
</style>
<body id="spotify">

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
        
 </header> 

<table style="width:100%">

	<c:forEach items="${tracks}" var="track">
		<tr>
    		<td>
    		<div class="container">
  				<div class="div-img" >
				<form action="Back2youngController" method="post" accept-charset="UTF-8">
					<input type="hidden" name="songName" value="${track.name}"/>
					<input type="hidden" name="artistName" value="${track.artists[0].name}"/>
					<button type="submit"><img class="img" src="${track.album.images[0].url}" title="${track.name}" alt="Foto10"></button>
   					<div class="text" >${track.name} - ${track.artists[0].name}</div>
   					</form>
   				 		</div>
  					</div>
				
			</td>
  		</tr>
	</c:forEach>
</table>

</body>
</html>