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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="application.name" /></title>

<jsp:include page="scripts.jsp"></jsp:include>



</head>
<body style="background-color: #333">

	<div id="contenedor">
	<!-- Contenedor -->
			
		<div id="fachada" style="position: absolute;top: 0px;left: 0px;z-index: 100;width:100%;height: 100%;background-image: url('images/texturas/textura (15).jpg');background-repeat: repeat">
		</div>
	
		<div id="cabecera" style="background-color:black;height: 67px;color: white;">
		<!--  cabecera  -->
			Cabecera4
			<div>
				<a href="#" id="cerrarSession">Cerrar sesion</a>  |
				<a href="#" id="spots">publicidad</a>  
				<div id="result"></div>
			</div>
		<!--  cabecera  -->
		</div>
		
		<!-- Separador -->
		
		<div id="menu" style="background-image: url('images/interfaz/bg-menu.png');height: 67px;">
			<!-- menu -->
			<table width="100%" id="separadorMenu" border="0px" bordercolor="#CC0000">
				<tr>
					<td width="30%" >
						
						<table>	
							<tr>							
							<td>	
								<div class="searchform">					
									<input class="searchfield" type="text" value="Search..." onfocus="if (this.value == 'Search...') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Search...';}"  name="buscar_txt" id="buscar_txt" />
									<input class="searchbutton" type="button" id="buscar_btn"  value="Go" />
								</div>	
							</td>
							
							</tr>
						</table>
					</td>
					<td width="19%" height="55px" valign="top" >
						<!-- Imagen de la reproducción -->
						<div id="repInfo" style="background-color: black;">
							<table>
								<tr>
									<td>
										<img src="" id="repInfoImg" width="55px" height="55px" />
									</td>
									<td>
										<table>
											<tr>
												<td><div id="repInfoImg_cancion" style="color: white;font-size: 10pt"></div></td>
											</tr>
											<tr>
												<td id="repInfoImg_artista"  style="color: gray;font-size: 8pt"></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<!-- Imagen de la reproducción -->
					</td>
					<td width="2%">
					
					</td>
					<td valign="middle" width="49%" align="right">
						<!-- Controles  -->
							<div id="controls" >
								 <!-- Slider Control del video de youtube -->			    	
							    	<table align="right" border="0" bordercolor="#00ff00">
							    		<tr valign="middle">
							    			
							    			<td id="prev_td" valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="prev" href="#" class="ui-icon ui-icon-seek-prev" onclick="previous();"></a></td>
							    			<td id="play_td" valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="play" href="#" class="ui-icon ui-icon-play" onclick="play_pause();"></a></td>
							    			<td id="next_td" valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-seek-next" onclick="next();"></a></td>
							    			
							    			<td valign="middle" width="80px"><div style="padding-left: 10px;padding-right: 10px"><div id="slider2"></div></div></td>
							    			<td valign="middle" width="3px"></td>
							    			<td valign="middle"  width="400px"><div style="padding-left: 10px;padding-right: 5px"><div id="slider"></div></div></td>
							    			
							    			<td valign="middle" width="40px"><div id="amount" style="color: white;padding-left:15px"></div></td>
							    			<td valign="middle" width="3px"></td>
							    			<td id="repeat"  valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-refresh" onclick="repetirCancionFunction();"></a></td>
							    			<td valign="middle" width="3px"></td>
							    			
							    			<!-- <td id="repeat"  valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-arrow-4-diag" onclick="myFullsize();"></a></td>  -->
							    			<td valign="middle" width="3px"></td>	    			
							    			<td id="shuffle" valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-shuffle" onclick="reproduccionAleatoriaFunction();"></a></td>
							    		</tr>
							    	</table>
							    <!-- Slider -->	    
							</div>	
						<!-- fin Controles  -->	
						
					</td>
				</tr>
			</table>
			<!-- menu -->
		</div>
		
		<!-- Separador -->
		
		<!-- Zona media (o central) -->
		<div id="media" style="height: 500px">
			<!-- media -->
			<table id="separadorMedia" width="80%" border="0" bordercolor="red">
				<tr>
				
					<td valign="top" width="275px">
						<img src="images/interfaz/panelLateral_up.png">
						<div id="panelLateral" style="background-image:url('images/texturas/textura (15).jpg');
	background-repeat: repeat;width: 271px;">						
							
							<!--  Radio buttons -->
							<!--  -->
							<div id="radio" align="center" style="text-align: center;font-size: 8pt;">							
								<input  type="radio" id="radio1" name="radio" value="video" /><label for="radio1"><fmt:message key="application.menu.modoVideo" /></label>								
							 	<input  type="radio" id="radio2" name="radio" value="audio" /><label for="radio2"><fmt:message key="application.menu.modoAudio" /></label>			
							</div>
							
							<!--  Radio buttons -->
								
							<!--  -->
							<!-- Informacion del album  -->
							<DIV id="infoPanel1" STYLE="" align="left">									    		
								<div id="nombre_pl"></div>
						    	<div id="artista_pl"></div>					    		
							</DIV>
							<!--  -->	
							<!--  -->					
							
							<br />							
							<div style="position: relative;text-align: center" align="center" >
								<DIV id="capaOcultacion" style="text-align: center;">	
									<IMG id="imageAlbum" SRC="" width="250px" height="200px">							
								</DIV>
							</div>
							<!--  -->
								
							<!-- Video de youtube  -->
							<div id="videoClip">
								<table align="center" width="100%" >
									<tr>
										<td align="center">
										    <div id="ytapiplayer"  style="z-index: 1;">
										    	<fmt:message key="application.menu.flashPlayer.error" />
										    </div>
									    </td>
								    </tr>
						    	</table>
							    <table align="center" width="100%">
								    <tr>
									    <td align="center">
									   
										    <script type="text/javascript">
										      // <![CDATA[
										
										      // allowScriptAccess must be set to allow the Javascript from one 
										      // domain to access the swf on the youtube domain
										      //param wmode:"transparent" hace que podamos ocultar el visor d eyoutube montando una
										      //capa por encima
										      var params = { allowScriptAccess: "always", bgcolor: "#cccccc", wmode: "transparent" };
										      // this sets the id of the object or embed tag to 'myytplayer'.
										      // You then use this id to access the swf and make calls to the player's API
										      var atts = { id: "myytplayer" };

										      //tamaño del reproductor
										      var Tam = TamVentana(); 
										      var size=(Tam[1]-280)/3;
										      //ponemos el tamaño										      
										      swfobject.embedSWF("http://www.youtube.com/apiplayer?enablejsapi=1&playerapiid=ytplayer&version=3&wmode=transparent", 
										                         "ytapiplayer", 250, 200, "8", null, null, params, atts);
										      //]]>
										    </script>
						    			</td>
						    		</tr>			    		
							    </table>					    
						   	</div>	
							<!-- fin Video de youtube  -->						
					
							<!-- Informacion del album  -->
							<DIV id="infoPanel2" STYLE="" align="left">	
								<table>
									<tr>
										<td class="TituloInfoPanelLateral" valign="top">Album: </td><td><div id="infoAlbum"> </div></td>
									</tr>
								</table>
								<table>
									<tr>
										<td class="TituloInfoPanelLateral" valign="top">Duración: </td><td><div id="infoDuration">22 </div></td>
									</tr>
								</table>
								<table>
									<tr>
										<td class="TituloInfoPanelLateral" valign="top">Año: </td><td><div id="infoYear"></div></td>
									</tr>
								</table>
								<!--  
								<table>
									<tr>
										<td class="TituloInfoPanelLateral" valign="top">Tag: </td><td><div id="infoTag"></div></td>
									</tr>
								</table>
								<table>
									<tr>
										<td class="TituloInfoPanelLateral" valign="top">Género:  </td><td><div id="infoGenero"></div></td>
									</tr>
								</table> 
								-->		
							</DIV>
													
							
							<!--  -->
						</div>
						<img src="images/interfaz/panelLateral_bottom.png">
					</td>
					
					
					<td width="100%" valign="top" align="left" style="text-align: left">						
						
						<!-- Menu de inicio -->
							<table>
								<tr>
									<td>
										<div id="menulista">
											<ul>
												<!-- <li ><a id="verCola" href="#"><fmt:message key="application.menu.colareproduccion" /></a></li> -->
												<li ><a id="artistasEscuchados" href="#"><fmt:message key="application.menu.topartistas" /></a></li>
												<li ><a id="temasEscuchados" href="#"><fmt:message key="application.menu.topcanciones" /></a></li>
												<li ><a id="artistasAlza" href="#"><fmt:message key="application.menu.lovedartistas" /></a></li>
												<li ><a id="temasAlza" href="#"><fmt:message key="application.menu.lovedcanciones" /></a></li>
											</ul>
																		
										</div>	
									</td>
									<td>
									
										<!-- Loader -->
										<div id="searchGif">
											<img src="images/gifloader/ajax_loader_orange_300.gif" width="30px" height="30px" /> 
										</div>	
										<div id="searchOpts">											 
											<a href="#" id="searchMusica">  Canciones </a>  
											<a href="#" id="searchAlbums">  Albums </a>  
											<a href="#" id="searchArtistas">  Artistas </a>  
											<a href="#" id="searchVideos">  Videos </a>
										</div>
										<!-- Loader -->
										
									</td>
								</tr>
							</table>	
						<!-- Menu de inicio -->
												
						<!--  -->
						
							<div id="container" >
								<ul class="menu">
									<li id="tab_search" class="active"><fmt:message key="application.menu.tabs.musica" /></li>
									<li id="tab_albums">Albums</li>
									<li id="tab_artists">Artistas</li>
									<li id="tab_videos">Videos <fmt:message key="application.menu.tabs.youtube" /></li>
									<li id="tab_rep"><fmt:message key="application.menu.tabs.listaReproduccion" /></li>									
								</ul>				
												
								<span class="clear"></span>		
														
								<!-- Contenido de las pestañas / atributo class asocia con la pestaña  -->
								<div id="searchresults"   class="content tab_search"  style="height:500px;overflow: scroll">Haz una busqueda</div>
								<div id="youtubeResults"  class="content tab_videos"  style="height:500px;overflow: scroll">Haz una busqueda</div>
								<div id="actualRepro"     class="content tab_rep"     style="height:500px;overflow: scroll">Aun no hay nada para reproducir</div>
								<div id="div_tab_albums"  class="content tab_albums"  style="height:500px;overflow: scroll">Haz una busqueda</div>
								<div id="div_tab_artists" class="content tab_artists" style="height:500px;overflow: scroll">Haz una busqueda</div>									
																
							</div>
						<!--  -->
					</td>
					
					<!-- lateral derecho (publicidad y otras) -->
					<td >
						<div style="" id="publicidad">
							
						</div>
					</td>					
				</tr>
				
			</table>
			<!-- media -->
		</div>
		<!-- Separador -->
		
		
		

		
	<!-- Contenedor -->
	</div>
	
	<!-- pié -->
	<div style="clear:both"></div>
	<div id="pieApp" style="position:absolute;width: 1430px;height: 57px; margin-top: 80px;" >
		
	</div>
	<!-- pié -->
	
	
	<!-- EXTRAS -->
	
	 <!-- Clipboard  -->
	 <div id="dialog-modal" title="Copiar Vinculo http" class="dialog-modal1">
		<p>El enlace se copiará en el portapapeles al pulsar OK</p>
		 <div id="mydiv">		
		 </div> 							
	 </div> 	
	 <!-- Clipboard  -->

</body>
</html>