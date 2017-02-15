<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title>Futvre</title>    
	
	<link rel="stylesheet" type="text/css" href="css/index5.css" /> 
	
	  	 
	<script type="text/javascript" src="js/jquery.js"></script>
	<!-- Funciones javascript donde esta todo el mogollon de codigo -->
	<script type="text/javascript" src="js/inicializador.js"></script>
    
	<script type="text/javascript" src="js/utiles.js"></script>
	<script type="text/javascript" src="js/searchFunctions.js"></script>
	<script type="text/javascript" src="js/reproductionFunctions.js"></script>
		
	
	<link href="css/jquery.contextMenu.css" rel="stylesheet" type="text/css" />			
	<script src="js/jquery.contextMenu.js" type="text/javascript"></script>
	<script type="text/javascript" src="tests/ZeroClipboard.js"></script>
	<script type="text/javascript" src="js/interfaz.js"></script>
	
	
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
		    
    <script>

    $(document).ready(function(){ 	
		//alert("pagina cargada");
		$("#videoClip").show();
		$("#fachada").hide();
		<%
			Boolean openTrack=(Boolean)request.getSession().getAttribute("openTrack");
			if(openTrack!=null && openTrack)
			{
				%>
					$("#fachada").load("music/trackUrl.jsp");
					$("#fachada").show();
				<%				
			}
		%>

		var v = gup( 'v' );
		//alert('v: '+v );
		state=getPlayerState();
		while(state!=-1)
		{
			state=getPlayerState();
		}
		
		alert('ahora');
		if(v!=""){
			
			
		}
		else{
			alert("no hay parametros");
		}
			
    });
    
    </script>
    
    
   
 
</head>
<body>


<!-- este div pone ancho y alto con jquey dinamicamente segun la medida del usuario-->
<div id="contenedor" style="width: 1440px;height: 700px;">
	<div id="fachada" style="position: absolute;top: 0px;left: 0px;z-index: 100;width:100%;height: 100%;background-image: url('images/texturas/textura (15).jpg');background-repeat: repeat">
		
	</div>
	<div id="superior" style="width: 1440px;height: 50px;color: white;border: 1px solid white;padding-left: 30px">
		
		<table bordercolor="#FFF" border="0">
			<tr>
				<td align="left">
					<span style="color:white;">Buscar: </span><input type="text" name="buscar_txt" id="buscar_txt" size="40" />	 		
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
			</tr>
		</table>
		
	</div>
	
	<div id="centro" style="width:1440px; height:650px ;color:white; border:1px solid white">
		<table width="100%" border="0" bordercolor="#F00">
			<tr>
				<td width="250px" align="left" valign="top">
				<div id="lateralizq" style="padding-left: 0px;position: relative; ">
												
						<div id="listasYEso" style="width: 250px; height: 336px">
							listas de reproduccion
								<ul>
									<li><a id="artistasEscuchados" href="#">Artistas mas escuchados</a></li>
									<li><a id="temasEscuchados" href="#">Temas mas escuchados</a></li>
									<li><a id="artistasAlza" href="#">Artistas en alza</a></li>
									<li><a id="temasAlza" href="#">Temas en alza</a></li>
								</ul>
							
						</div>	
						
						
						
						 <script language="JavaScript">
				                var clip = new ZeroClipboard.Client();
				                clip.setText( 'ahora' );
				                clip.glue( 'd_clip_button' );
				        </script>						
						
					 <!-- embed the player -->		
					    <a id="ocultarVideo" id="ocultarVideo" href="#">Modo Música</a>						
					    <a id="verVideo" id="verVideo" href="#">Modo Video Clip</a>
							
						<div style="position: relative">
					    	
							<!--  -->
							<DIV id="capaOcultacion" STYLE="position:absolute; top:0px; left:0px; width:0px; height:250px; visibility:visible;background-color: black;z-index: 9; " align="center">
								<IMG id="imageAlbum" SRC="" border="0" width="250px" height="250px">
							</DIV>
							
							<!--  -->							
 							
							<div id="videoClip" STYLE="position:absolute; top:0px; left:0px; width:250px; height:200px;  z-index: 0; background-color: black" align="center">
							
							    <div id="ytapiplayer">
							      Necesitas al menos flash 8 para visualizar el video.
							    </div>
						    
							    <table align="center" >
								    <tr>
									    <td>
									   
										    <script type="text/javascript">
										      // <![CDATA[
										
										      // allowScriptAccess must be set to allow the Javascript from one 
										      // domain to access the swf on the youtube domain
										      var params = { allowScriptAccess: "always", bgcolor: "#cccccc" };
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
					    	<DIV id="reproductionParams" STYLE="position:absolute; top:250px; left:0px; width:250px; height:40px; visibility:visible;" align="center">
								<div id="reproductionTitle"></div><br>
					    		<div id="reproductionArtist"></div><br>
							</DIV>
					    </div>					    
					    			    	
				    <!-- Embed player -->					   
				    
				</div>
				</td>
				
				<td width="10px" valign="top">
					<div style="width: 10px; height: 100%;background-color: red">
					</div>
				</td>
				
				<td align="left"  valign="top">
				
						
						<div id="tabs">
							<ul>
								<li><a href="#searchresults">Musica</a></li>
								<li><a href="#youtubeResults">Youtube</a></li>
								<li><a href="#actualRepro">Reproduccion actual</a></li>
							</ul>
							<div id="searchresults" style="overflow: auto; ">
			 					Search Results
			 				</div>
							<div id="youtubeResults" style="overflow: auto;" >Youtube Results</div>
							<div id="actualRepro" style="overflow: auto;">Lista de reproduccion actual.</div>
						</div>
				
				
				
				</td>
			</tr>
		</table>
	</div>
	
	<div id="controles" style="width: 1440px;height: 50px;color: white;border: 1px solid white; padding-top: 10px">
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
	    			<td id="shuffle" valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-shuffle" onclick="reproduccionAleatoriaFunction();"></a></td>
	    		</tr>
	    	</table>
	    <!-- Slider -->
	</div>

</div>
	




 <div id="dialog-message" title="Este no es el video que quería" style="width: 500px">
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		Cada vez que realizas una busqueda o reproduces un video automaticamente se hace una busqueda en YouTube, 
		si el video que aparece por defecto no es el que querías, puedes seleccionar otro <b>pulsando en la pestaña
		YouTube</b> justo debajo de la casilla de busqueda.
	</p>	
</div>
 
 <!-- Clipboard  -->

 <div id="dialog-modal" title="Portapapeles">
	<p>El enlace se copiará en el portapapeles al pulsar OK</p>
	 <div id="mydiv" style="">		
	 </div> 							
 </div> 

 <!-- Clipboard  -->


</body>
</html>
