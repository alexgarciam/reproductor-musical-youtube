<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<fmt:setBundle basename="MessageResource"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="application.name" /></title>

<jsp:include page="scripts.jsp"></jsp:include>


<link rel="stylesheet" href="tinyScrollbar/css/website.css" type="text/css" media="screen"/>
	
	<script type="text/javascript" src="tinyScrollbar/js/jquery.tinyscrollbar.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){			
			$('#scrollbar1').tinyscrollbar();				
			$('#update').click(function() {		
				$("#nuevaListaUser").show();
				  $('#scrollbar1').tinyscrollbar();	
			});			
		});
	</script>	
	
	
<style>
    #droppable { width: 150px; height: 150px; padding: 0.5em; float: left; margin: 10px; border: 1px solid black }  
	.hover{ background-color: #696969; }
</style>
    
    
    <script>
    $(function() {
    	 $( ".draggable" ).draggable({
         	revert: true , 
         	cursor: "move",
         	opacity: 0.35,
         	 zIndex: 9999999999999,
         	 helper: function(event, ui) {
         	        var foo = $('<span style="white-space:nowrap;border:1px solid #CCC;background-color: yellow;">DRAG TEST</span>').text($(this).text() + " helper"); 
         	        return foo;
         	    }
         	});
        $( "#droppable" ).droppable({
            //accept: "#draggable",
            //activeClass: "hover2",
            hoverClass: "hover",
            drop: function( event, ui ) {
            	var draggable = ui.draggable;
           		alert( 'The square with ID "' + draggable.attr('id') + '" was dropped onto me!' );
            }
        });
    });
    </script>
</head>

<body style="background-color: #333">	

	<div id="contenedor">
	<!-- Contenedor -->
			
	<div id="fachada" style="position: absolute;top: 0px;left: 0px;z-index: 100;width:100%;height: 100%;background-image: url('images/texturas/textura (15).jpg');background-repeat: repeat">
	</div>
	
		<div id="cabecera" style="background-color:black;color: white;">
		<!--  cabecera  -->
						
			<div >
				<table height="100%" width="100%">				
					<tr>
						<td>LOGO</td>
						<td>	<a href="#" id="newAccount">Crear Cuenta</a>    	|</td>
						
						<td>	<a href="getListasUsuario.do" >getListas</a>    	| </td>						
						
						<sec:authorize ifAllGranted="ROLE_ADMIN" >			
							Hola usuario admin!!
						<td>	<a href="j_spring_security_logout">Cerrar sesion</a>  	|</td>
						</sec:authorize>							
						
						<td>	<a href="#" id="spots">publicidad</a>				 </td>
						<td>	<div id="result"></div>								 </td>
												
						<sec:authorize ifAllGranted="ROLE_ANONYMOUS" >
						
						<td width="60%">	
								
								 <!-- Login Starts Here -->
						            <div id="loginContainer">
						                <a href="#" id="loginButton"><span>Login</span><em></em></a>
						                <div style="clear:both"></div>
						                <div id="loginBox">                
						                    <form id="loginForm" action="j_spring_security_check">
						                        <fieldset id="body">
						                            <fieldset>
						                                <label for="email">Email Address</label>
						                                <input type="text" name="j_username" id="j_username" />
						                            </fieldset>
						                            <fieldset>
						                                <label for="password">Password</label>
						                                <input type="password" name="j_password" id="j_password" />
						                            </fieldset>
						                            <input type="submit" id="login" value="Sign in" />
						                            <label for="checkbox"><input type="checkbox" id="checkbox" />Remember me</label>
						                        </fieldset>
						                        <span><a href="#">Forgot your password?</a></span>
						                    </form>
						                </div>
						            </div>
								<!-- Login ends here -->
						|</td>
						</sec:authorize>
					</tr>
				</table>	
			</div >			
			
		<!--  cabecera  -->
		</div>
		
		<!-- Separador -->
		
		<div id="menu" style="background-image: url('images/interfaz/bg-menu.png');margin-top: -17px">
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
							    			<td width="3px"></td>
							    			<td id="repeat"  valign="middle" width="10px" class="ui-state-default ui-corner-all"><a id="mutebtn" href="#" class="ui-icon ui-icon-volume-on"></a></td>
							    			<td width="3px"></td>
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
						<img src="images/interfaz/panelLateral_up5.png" width="270px">
						<div id="panelLateral" style="background-image:url('images/texturas/textura (15).jpg');
	background-repeat: repeat;width: 270px;">		
								<!-- panel lateral -->
								
									<!-- Listas de reproducción -->
									<sec:authorize ifAllGranted="ROLE_ADMIN" >		
										<div style="padding-left: 20px">
											<a id="update" class="modalbox" href="#inline">+ Nueva lista</a>
										</div>
									<div id="recuListas" style="color: white;padding-left: 30px;"><img src="images/gifloader/ajax_loader_orange_300.gif"  width="30px" height="30px"/> Recogiendo listas...</div>	
									</sec:authorize>
									
									
									
									<div id="scrollbar1" style="padding-left: 30px; margin-bottom: 15px">
										<div class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
										<div class="viewport">
											 <div id="scroller" class="overview">
												<h3>Crea Tu cuenta</h3>
												<p>Y podrás crear tus propias listas de reproducción</p>
												                   
											</div>
										</div>
									</div>		
									<!-- Listas de reproducción -->
								
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
						    	<div id="infoAlbum"> </div>			    		
							</DIV>							
							<!--  -->	
							
							<!--  -->	
							<br />							
							<div style="position: relative;text-align: center" align="center" >
								<DIV id="capaOcultacion" style="text-align: center;">	
									<IMG id="imageAlbum" SRC="" width="250px" height="250px">							
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

										     
										      //ponemos el tamaño										      
										      swfobject.embedSWF("http://www.youtube.com/apiplayer?enablejsapi=1&playerapiid=ytplayer&version=3&wmode=transparent", 
										                         "ytapiplayer", 250, 250, "8", null, null, params, atts);
										      //]]>
										    </script>
						    			</td>
						    		</tr>			    		
							    </table>					    
						   	</div>	
							<!-- fin Video de youtube  -->						
					
							<!-- Informacion del album  -->
							<DIV id="infoPanel2" STYLE="" align="left">	
								
								
							</DIV>
							<!--  -->								
							
							<!-- panel lateral -->
						</div>
						<img src="images/interfaz/panelLateral_bottom5.png" width="270px">
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
												<!-- <li ><a id="temasAlza" href="#"><fmt:message key="application.menu.lovedcanciones" /></a></li> -->
											</ul>
																		
										</div>	
									</td>
									<td>
									
										<!-- Loader -->
										<div id="searchGif">
											<img src="images/gifloader/ajax_loader_orange_300.gif" width="30px" height="30px" /> 
										</div>	
										<div id="searchOpts">											 
											<a href="#" id="searchMusica">    Canciones </a>  
											<a href="#" id="searchAlbums">    Albums    </a>  
											<a href="#" id="searchArtistas">  Artistas  </a>  
											<a href="#" id="searchVideos">    Videos    </a>
											<a href="#" id="ListasUsuario">   Listas    </a>
										</div>
										<!-- Loader -->
										
									</td>
								</tr>
							</table>	
						<!-- Menu de inicio -->
												
						<!--  -->
						
							<div id="container" style="z-index: 9999" >
								<ul class="menu">
									<li id="tab_search" class="active"><fmt:message key="application.menu.tabs.musica" /></li>
									<li id="tab_albums">Albums</li>
									<li id="tab_artists">Artistas</li>
									<li id="tab_videos">Videos <fmt:message key="application.menu.tabs.youtube" /></li>
									<li id="tab_rep"><fmt:message key="application.menu.tabs.listaReproduccion" /></li>
									<li id="tab_listas">Listas del Usuario</li>												
								</ul>				
												
								<span class="clear"></span>		
														
								<!-- Contenido de las pestañas / atributo class asocia con la pestaña  -->
								<div id="searchresults"   class="content tab_search"  style="height:500px;overflow: scroll">Haz una busqueda</div>
								<div id="youtubeResults"  class="content tab_videos"  style="height:500px;overflow: scroll">Haz una busqueda</div>
								<div id="actualRepro"     class="content tab_rep"     style="height:500px;overflow: scroll">Aun no hay nada para reproducir</div>
								<div id="div_tab_albums"  class="content tab_albums"  style="height:500px;overflow: scroll">Haz una busqueda</div>
								<div id="div_tab_artists" class="content tab_artists" style="height:500px;overflow: scroll">Haz una busqueda</div>									
								<div id="div_tab_listas" class="content tab_listas"  style="height:500px;overflow: scroll">Selecciona una lista de reproducción</div>
																
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
		
		
						<!-- nueva lista -->
						
						
					<!-- hidden inline form -->
			<div id="inline">
				<h2>Crear Nueva lista</h2>
				
				<div id="message1"></div>
				
				<form id="nuevaListaUser" name="nuevaListaUser" action="#" method="post">
					<label for="nombreListaForm">Nombre de la lista</label>
					<input type="text" id="formLista" name="formLista" class="txt" value="">
					
					
					<button id="send">Guardar</button>
				</form>
			</div>
						<!-- nueva lista -->
		
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