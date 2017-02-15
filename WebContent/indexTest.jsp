<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<fmt:setBundle basename="MessageResource"/>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title><fmt:message key="application.name" /></title>    
	
	<link rel="stylesheet" type="text/css" href="css/index5.css" /> 
		  	 
	<script type="text/javascript" src="js/jquery.js"></script>
	<!-- Funciones javascript donde esta todo el mogollon de codigo -->
    
	<script type="text/javascript" src="js/utiles.js"></script>
	<script type="text/javascript" src="js/searchFunctions.js"></script>
	<script type="text/javascript" src="js/reproductionFunctions.js"></script>
	
	<link href="css/jquery.contextMenu.css" rel="stylesheet" type="text/css" />			
	<script src="js/jquery.contextMenu.js" type="text/javascript"></script>
	
	
	 
	<script type="text/javascript" src="js/interfaz.js"></script>
	<script type="text/javascript" src="js/inicializador.js"></script>
	
	<!-- Estilos para las tablas -->
		<link href="css/tables/redandblack.css" rel="stylesheet" type="text/css" />
		<link href="css/tables/itunes.css" rel="stylesheet" type="text/css" />
	<!-- Stilos para las tablas -->
	
	
	<!-- Funciones javascript donde esta todo el mogollon de codigo -->
		
	<!-- jquery ui -->
	<!-- ORIGINAL <link type="text/css" href="css/jquery-ui.css" rel="stylesheet" /> -->
	<link id="stylesheet" type="text/css" href="css/ui-darkness/jquery-ui-1.8.16.custom.css" rel="stylesheet" />		
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<!-- jquery ui -->
		
	<!-- Para el reproductor de youtube -->
	<script src="http://www.google.com/jsapi"></script>
	
    <script>
      google.load("swfobject", "2.1");
    </script>
	<!-- Para el reproductor de youtube -->
	
    <script type="text/javascript" src="js/ytfunctions.js"></script>
 	<script type="text/javascript">		
 	 function onYouTubePlayerReady(playerId) {
 		onYouTubePlayerReady2(playerId); 	 
	 }
	</script>
	
	
</head>

<body>


	<div id="contenedor">
	<!-- Contenedor -->
	
	<!-- Publicidad -->
	<div id="publiDiv" style="position:absolute;z-index:10;top: 0px;left: 0px; width: 1440px;height: 800px; background-color: fuchsia">
		
	</div>
	<!-- Publicidad -->
	
	<!-- Superior -->
		<div id="superior" >
	
			<table bordercolor="#FFF" border="0">
				<tr>
					<td align="left">						
						<span style="color:white;"><fmt:message key="application.menu.search" /></span><input type="text" name="buscar_txt" id="buscar_txt" size="40" />	 		
					</td>
					<td align="left">
						<img src="images/icons/search3.png" id="buscar_btn" />
						<!--  <input type="button" name="buscar_btn" id="buscar_btn" value="GO!">  --> 
					</td>
					<td align="left">
						<div id="searchGif">
							<img src="images/gifloader/ajax_loader.gif" /> 
						</div>					
					</td>
					<td><a href="#"  onclick="testOriginalsize()">Test Original size</a> | <a href="#" onclick="testFullsize();">Test Full Size</a> | 
					 <a href="#" onclick="loadTest()">Load Video</a> | <a href="javascript:tamVentana(640,480)">Tamaño a 640x480</a> | <a href="javascript:tamVentana(250,200)">Tamaño original</a> |					 
	 <a href="javascript:fullsize()">full size</a> | <a href="#" id="cerrarSession">Cerrar Sesion</a>
	 
	 							<div id="result"><a href="#" id="spots">publicidad</a></div>
					</td>
				</tr>
			</table>
					
		</div>
	<!-- Superior -->
	
	<!-- Centro -->
		<div id="centro" style="width: 1440px;height: 650px;color: white;border: 0px solid white">
			<table width="100%" border="1" bordercolor="#F00">
				<tr>
					<td width="250px" align="left" valign="top" bordercolor="#FFFFFF" >
						<div id="lateralizq" style="padding-left: 0px;position: relative; ">
								
								
							<!-- El tamaño de este div en pantalla se genera en el metodo resize de utiles.js -->						
							<div id="listasYEso" style="padding-bottom: 5px;overflow: auto">
								<ul>
									<li class="opcioneslistas"><a id="verCola" href="#"><fmt:message key="application.menu.colareproduccion" /></a></li>
									<li class="opcioneslistas"><a id="artistasEscuchados" href="#"><fmt:message key="application.menu.topartistas" /></a></li>
									<li class="opcioneslistas"><a id="temasEscuchados" href="#"><fmt:message key="application.menu.topcanciones" /></a></li>
									<li class="opcioneslistas"><a id="artistasAlza" href="#"><fmt:message key="application.menu.lovedartistas" /></a></li>
									<li class="opcioneslistas"><a id="temasAlza" href="#"><fmt:message key="application.menu.lovedcanciones" /></a></li>
								</ul>								
							</div>								
							
													
							
							<!--  Radio buttons -->
							<div id="radio" align="center" style="text-align: center">
								<input type="radio" id="radio1" name="radio" value="video" /><label for="radio1"><fmt:message key="application.menu.modoVideo" /></label>								
							 	<input type="radio" id="radio2" name="radio" value="audio" /><label for="radio2"><fmt:message key="application.menu.modoAudio" /></label>			
							</div>
							<!--  Radio buttons -->
							<br />							
							<div style="position: relative" align="center">
								<DIV id="capaOcultacion">	
									<IMG id="imageAlbum" SRC="" border="0" width="200px" height="200px">								
								</DIV>
							</div>
							<!--  -->
								
							<!-- Video de youtube  -->
							<div id="videoClip">
							    <div id="ytapiplayer" style="z-index: 1;">
							    	<fmt:message key="application.menu.flashPlayer.error" />
							    </div>
						    
							    <table align="center" >
								    <tr>
									    <td>
									   
										    <script type="text/javascript">
										      // <![CDATA[
										
										      // allowScriptAccess must be set to allow the Javascript from one 
										      // domain to access the swf on the youtube domain
										      var params = { allowScriptAccess: "always", bgcolor: "#cccccc", wmode: "transparent" };
										      // this sets the id of the object or embed tag to 'myytplayer'.
										      // You then use this id to access the swf and make calls to the player's API
										      var atts = { id: "myytplayer" };
										      swfobject.embedSWF("http://www.youtube.com/apiplayer?enablejsapi=1&playerapiid=ytplayer&version=3", 
										                         "ytapiplayer", "250px", "200px", "8", null, null, params, atts);
										      //]]>
										    </script>
						    			</td>
						    		</tr>			    		
							    </table>					    
						   	</div>	
							<!-- fin Video de youtube  -->						
					
							<DIV id="reproductionParams" STYLE="" align="center">					    		
								<div id="reproductionTitle"></div><br>
					    		<div id="reproductionArtist"></div><br>
							</DIV>
							
						</div>			   				   
					    
					</td>
					<td width="10px" valign="top">
						<div style="width: 10px; height: 100%;background-color: red">
						</div>
					</td>
				
					<td align="left"  valign="top">						
						<div id="tabs">
							<ul>
								<li><a href="#searchresults"><fmt:message key="application.menu.tabs.musica" /></a></li>
								<li><a href="#youtubeResults"><fmt:message key="application.menu.tabs.youtube" /></a></li>
								<li><a href="#actualRepro"><fmt:message key="application.menu.tabs.listaReproduccion" /></a></li>
								<li><a href="#texto"><fmt:message key="application.menu.tabs.Texto" /></a></li>
							</ul>
							<div id="searchresults" style="overflow: auto; "></div>
							<div id="youtubeResults" style="overflow: auto;" ></div>
							<div id="actualRepro" style="overflow: auto;"></div>
							<div id="texto" style="overflow: auto;"></div>
						</div>				
					</td>		
					
					<td id="publicidad" style="width:300px">
						Test Publicidad
					</td>
					
				</tr>
			</table>
		</div>
	<!-- Centro -->

	<!-- Controles  -->
		<div id="controles" >
			 <!-- Slider Control del video de youtube -->			    	
		    	<table width="1000px" border="0" bordercolor="#FFF" align="center">
		    		<tr valign="middle">
		    			<td width="150px"></td>
		    			<td id="prev_td" valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="prev" href="#" class="ui-icon ui-icon-seek-prev" onclick="previous();"></a></td>
		    			<td id="play_td" valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="play" href="#" class="ui-icon ui-icon-play" onclick="play_pause();"></a></td>
		    			<td id="next_td" valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-seek-next" onclick="next();"></a></td>
		    			
		    			<td valign="middle" width="80px"><div style="padding-left: 10px;padding-right: 10px"><div id="slider2"></div></div></td>
		    			
		    			<td valign="middle"  width="500px"><div style="padding-left: 10px;padding-right: 5px"><div id="slider"></div></div></td>
		    			
		    			<td valign="middle" width="40px"><div id="amount" style="color: white;"></div></td>
		    			<td valign="middle" width="3px"></td>
		    			<td id="repeat"  valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-refresh" onclick="repetirCancionFunction();"></a></td>
		    			<td valign="middle" width="3px"></td>
		    			<td id="repeat"  valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-arrow-4-diag" onclick="myFullsize();"></a></td>
		    			<td valign="middle" width="3px"></td>	    			
		    			<td id="shuffle" valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-shuffle" onclick="reproduccionAleatoriaFunction();"></a></td>
		    		</tr>
		    	</table>
		    <!-- Slider -->	    
		</div>	
	<!-- fin Controles  -->	
	 
	 
		<!-- footer -->
		<div id="footer">
			<table  align="center" >
				<tr>
					<td>
						<strong><fmt:message key="application.name" /></strong><fmt:message key="application.footer.copyright" /> <fmt:message key="application.footer.year" />
					</td>
					<td>
					|
					</td>
					<td>
					<a href="#" id="helpText">Uso</a>
					</td>
					<td>
					|
					</td>
					<td>
					<a href="#" id="helpText">Ayudanos a mejorar</a>
					</td>
					<td>
					|
					</td>
					<td>
					Powered by:
					</td>
					<td>
					&nbsp;&nbsp;
					</td>
					<td>
					 <a href="http://www.youtube.com"><img src="images/logos/youtube_logo_v3_white_2.png" height="15px" alt="YouTube" /></a>
					</td>
					<td>
					&nbsp;&nbsp;
					</td>
					<td>
					<a href="http://lastfm.es"><img src="images/logos/Last.fm_Logo.png" height="15px" /></a>
					</td>
				</tr>
			</table>
			
		</div>
		<!-- footer -->
	
	
	<!-- contenedor -->
	</div>
	
	<!--  -->
	<div id="dialog-message" title="Este no es el video que quería" style="width: 500px">
		<p>
			<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
			Cada vez que realizas una busqueda o reproduces un video automaticamente se hace una busqueda en YouTube, 
			si el video que aparece por defecto no es el que querías, puedes seleccionar otro <b>pulsando en la pestaña
			YouTube</b> justo debajo de la casilla de busqueda.
		</p>	
	</div>
 
	 <!-- Clipboard  -->
	 <div id="dialog-modal" title="Copiar Vinculo http" class="dialog-modal1">
		<p>El enlace se copiará en el portapapeles al pulsar OK</p>
		 <div id="mydiv">		
		 </div> 							
	 </div> 	
	 <!-- Clipboard  -->
	 
	 
	<!--  -->
	
</body>
</html>