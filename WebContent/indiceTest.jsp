
    
 <div id="buscar_content">
 
 	<!-- <table border="1" bordercolor="#FFF" width="1200px"> -->
 	<table width="1200px">
 		<tr>
 			<td width="40%">
 				<!--  -->
 				 <div>
	 				 	<div id="lnk_cola_rep_div">
	 				 		<a href="#" id="lnk_cola_rep">Cola de reproduccion</a> 
	 				 	</div>
 				 
				      	<div id="tituloReproduccion" style="color:#FFF"></div>				
				
				      <div id="noestevideo">
				     	<br/><a href="#" style="color:#FFF" onclick="$('#dialog-message').dialog('open')">¿No es el video que buscabas?</a><br/>
				      </div>
				      
				    <!-- embed the player -->
				
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
							                         "ytapiplayer", "400", "300", "8", null, null, params, atts);
							      //]]>
							    </script>
			    			</td>
			    		</tr>
			    		<tr bgcolor="#000">
			    			<td>
						    <!-- Slider Control del video de youtube -->				    	
						    	<table width="400px" border="0" bordercolor="#FFF" align="center">
						    		<tr>
						    			<td width="20px" class="ui-state-default ui-corner-all"><a id="play" href="#" class="ui-icon ui-icon-play" onclick="play_pause();"></a></td>
						    			<td><div style="padding-left: 5px;padding-right: 5px"><div id="slider"></div></div></td>
						    			<td width="40px"><div id="amount" style="color: #FFF"></div></td>
						    		</tr>
						    	</table>
						    <!-- Slider -->
						    </td>
					   	</tr>
				    </table>
				    
				    <!-- HTML below here is for display of the player info + state -->				   
				   
				   
				   
				   
				  </div>
 				<!--  -->
 			</td>
 			<td width="60%" valign="top">
 				<div style="margin-left: 50px"> 				
 					<table>
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
 				
 				
 				
 				<div id="tabs">
					<ul>
						<li><a href="#searchresults">Musica</a></li>
						<li><a href="#youtubeResults">Youtube</a></li>
						<li><a href="#SoundCloudRestults">SoundCloud</a></li>
						<li><a href="#actualRepro">Reproduccion actual</a></li>
					</ul>
					<div id="searchresults" style="height:500px;overflow: auto; ">
	 					resultados 2 	
	 				</div>
					<div id="youtubeResults" style="height:500px;overflow: auto;" >Youtube Results</div>
					<div id="SoundCloudRestults">SoundCloud Results.</div>
					<div id="actualRepro">Lista de reproduccion actual.</div>
				</div>
		
		
 				
 			</td>
 		</tr>
 		<tr>
 			<td>
 				Por Alex García
 			</td>
 		</tr>
 	</table>
 
 </div>
 
 
 
 <div id="dialog-message" title="Este no es el video que quería" style="width: 500px">
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		Cada vez que realizas una busqueda o reproduces un video automaticamente se hace una busqueda en YouTube, 
		si el video que aparece por defecto no es el que querías, puedes seleccionar otro <b>pulsando en la pestaña
		YouTube</b> justo debajo de la casilla de busqueda.
	</p>	
</div>
   
