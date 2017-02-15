	
/**
 * Funciones de busqueda javascript
 * 
 */

 		function searchArtist( artista ){
	 	 
			//alert("Buscando artista "+artista);
			artista=replaceAll(artista,' ','+');
			$('#searchGif').show();
			$('#searchresults').html('');
			$('#searchresults').load('searchArtist.do?artist='+artista, function() {
				$('#searchGif').hide();
	   			 // alert('Load was performed.');
				//$('#tabs').tabs('select', 0);	
   			});
			//busqueda en los videos de youtube
			youtubeSearch(artista);
			
	    }
 		
 		//variable para contar los albumes del artista y saber cuando se han recogido todos
 		var gets=0;
 		var numalbumes=0;
 		var imgArtist="";
 		var artist="";
 		function searchArtist2( artista ){
 			 closeSuggestions();	
 			
 			
			//alert("Buscando artista "+artista);				
			artista=replaceAll(artista,' ','+');
			
			$('#searchGif').show();
			$.getJSON("searchArtistHeader.do?artist="+artista, function(data) {
				var test=data;
				//actualiza la información del artista en la pagina
				$('#searchresults').load('music/searchArtist2.jsp', function() {						
					$('#imgArtist').attr("src",test.imageURL);
					var elname=replaceAll(unescape(test.name),'+',' ');
					$('#nameArtist').text(elname);
					
					var summary=decodeURIComponent(test.summary);
					summary=replaceAll(summary,'+',' ');
					$('#descArtist').html((summary));	
					imgArtist=test.imageURL;
					artist=test.name;
					
					//artistas similares
					var similares=test.similares;
					var htmlSimilares="<h3>Artistas Similares</h3>";
					for(var i=0;i<similares.length-1;i++){
						//alert(similares[i].name);
						var artistaSimilar=replaceAll(similares[i].name,'+',' ');
						htmlSimilares+=" <a href='#' style='color:black' onclick='searchArtist2(\""+escape(decodeURIComponent(similares[i].name))+"\")'>"+decodeURIComponent(artistaSimilar)+"</a> ";
					}
					$('#similaresArts').html((htmlSimilares));	
					$('#searchGif').hide();
	   			});
				
				var album=test.albums;
				
				var albumes=new Array();
				//console.debug("gets inicial: "+gets);
				//ponemos el numero de albumes del artista  a 0
				gets=0;
				//obtiene las canciones de los albumes
				numalbumes=album.length;
				$('#searchGif').show();
				for (var x = 0 ; x < album.length ; x++) {						
					//console.debug("album: "+album[x].name);						
					$.getJSON("getAlbumArtista.do?artist="+artista+"&album="+album[x].name, function(data) {
						//actualiza los albumes en la pagina del artista
						
						albumes.push(data);
						//console.debug(albumes);
						actualizarAlbumesArtista(data);	
						
					})
					.error(function() {actualizarAlbumesArtistaError(); });						
				}	
				$('#searchGif').hide();
				showMusica();
			});
			
			
			//busqueda en los videos de youtube
			youtubeSearch(artista);
				
		 }
 		
 		//saca los albumes del artista por pantalla
 		function actualizarAlbumesArtista(album){
 			
 			//alert("eeee --> "+album.artista);
 			var tracks=album.tracks;
 			gets++;
 			//console.debug("gets1 x: "+gets); 
 			//ponemos para que pare la barra de progreso faltando 5 albumes
 			//por si acaso fallan algunos...
 			if(gets>=numalbumes-5){
				//console.debug("gets total x: "+gets); 	
				$('#searchGif').hide();
			}
 			
 			//solo mostramos el disco si lleva alguna cancion
 			if(tracks.length>=0)
 			{
	 			//codigo html del album
	 			var html=""+album.name+"<br />";
	 			
	 			//-----------------------------------
	 			
	 			//sacamos el año de releaseDate:
	 			//formato: Jun 5, 2007 12:00:00 AM
	 			
	 			var releaseDate="";
	 			
	 			if (typeof album.releaseDate == "undefined"){
	 			   //console.debug(album.name+ "NO Existe fecha");
	 			}else{
	 				//console.debug(album.name +"Existe fecha");
	 				var fragmentos = album.releaseDate.split(',');
	 				var fragmentos2=fragmentos[1].split(' ');
	 				//console.debug('fecha: '+fragmentos2[1]);
	 				releaseDate="("+fragmentos2[1]+")";
	 			} 
	 			
	 			//si el album viene sin imagen ponemos la del artista!
	 			var img=album.urlImage;
	 			//console.debug("imagen: "+img);
	 			if (typeof album.urlImage == "undefined" || album.urlImage=="" ){
	 				img=imgArtist;
	 			}
	 			
	 			//ponemos el nombre del album para que se muestre correctamente
	 			var albunname=album.name;
	 			albunname=unescape(albunname);
	 			albunname=replaceAll(albunname,'+',' '); 				
	 			
	 			var html2_1="";	 			 			
	 			//aquí tomamos las canciones del album
	 			
	 			//-------------------------------------	 			
	 			if(tracks.length>0){
	 				html2_1="<table cellspacing='10px' class='testTable' width='100%' border='0' bordercolor='green'>" +
 					"<tr>" +
 					"	<td align='left' valign='top' width='10%'>" +
 					"		<a href='#' class='linkNoDecore' style='cursor: default' class='menuAlbums' id='"+album.name+"_"+artist+"' ondblclick='searchAlbum(\""+unescape(artist)+"\",\""+album.name+"\")'><img src='"+img+"' /></a>"
					+"</td>"
					+"<td align='left' valign='top'>"
					+"<strong><a href='#'  class='linkNoDecore' onclick='searchAlbum(\""+artist+"\",\""+album.name+"\")'>"+albunname+"</a></strong>  "+releaseDate						
					+"<br/><div></div><br/><br/>"					
					+"<table class='album_artista' cellspacing='0' width='100%'  border='0' bordercolor='red'>"
					+"<tbody>";

	 			
		 			for (var x = 0 ; x < tracks.length ; x++) {
		 				var style='';
		 				if ( x % 2 == 0 ) {
		 	    			/* even row: add class="even" */
		 					style='odd';
		 	    		} 
		 				
		 				var nombre=unescape(tracks[x].name);
		 				var elartista=unescape(album.artist);
		 				elartista=replaceAll(elartista,'+',' ');
		 				nombre=replaceAll(nombre,'+',' ');
		 				//alert("elartista: "+tracks[x].artist);
		 				var artista=unescape(tracks[x].artist);
		 				artista=replaceAll(artista,'+',' ');
		 				var albumnombre=unescape(tracks[x].album);
		 				albumnombre=replaceAll(albumnombre,'+',' ');
		 				
		 				html2_1 = html2_1+"	<tr  class='menuc' id='"+tracks[x].name+"_"+tracks[x].name+"_"+tracks[x].artist+"_"+tracks[x].album+"_"+tracks[x].duration+"' ondblclick='reproducirAlbumEnCola(\""+tracks[x].id+"\",\""+tracks[x].id+"\",\""+escape(tracks[x].name)+"\",\""+album.artist+"\",\""+escape(albumnombre)+"\",\""+tracks[x].duration+"\");'>"
						
						+"<td class='"+style+"' align='left'><a href='#'  class='linkNoDecore' style='text-decoration: none;cursor: default;font-weight:normal' >"+nombre+"</a></td>"
						
						+"<td class='"+style+"'>&nbsp;</td>"
						+"<td class='"+style+"'  align='left'><a href='#'  class='linkNoDecore' style='text-decoration: none;cursor: default;font-weight:normal' onclick='searchArtist();'>"+elartista+"</a></td>"
						+"<td class='"+style+"'>&nbsp;</td>"
						+"<td class='"+style+"'  align='left'><a href='#'  class='linkNoDecore' style='text-decoration: none;cursor: default;font-weight:normal' onclick='searchAlbum();'>"+albumnombre+"</a></td>"
						+"<td class='"+style+"'>&nbsp;</td>"
						+"<td class='"+style+"'  align='left'><a href='#'  class='linkNoDecore' style='text-decoration: none;cursor: default;font-weight:normal'>"+segundos_a_minutos(tracks[x].duration)+"</a></td>"
						 											
					+"</tr>";	 				
		 				
		 			}
		 			
	 			}
	 			else{
	 				//html2_1 = html2_1+"<tr><td colspan='4'>No se encontraron canciones</td></tr>";
	 			}
				//--------------------------------------
	 			var html2_2="</tbody>"
						+"</td>"
					+"</tr>"			
				+"</table><br/><br/>";	 			
	 			
	 			//----------------------------------- 	
	 			
	 			
	 			$('.menuc').draggable({
		         	revert: true , 
		         	cursor: "move",
		         	opacity: 0.99,
		         	 zIndex: 9999999999999,
		         	 helper: function(event, ui) {
		         		 	var id=$(this).attr('id'); 
		         		 	
		         		 	var params=id.split("_");
		         		 	var cancion=params[1];
		         		 	var artista=params[2];
		         		 	
		         		 	cancion=replaceAll(cancion,"+"," ");
		         		 	artista=replaceAll(artista,"+"," ");
		         		 	
		         	        var foo = $('<span style="white-space:nowrap;border:1px solid #000;background-color: #FFFAF0; color:black; padding-left:5px;padding-right:5px;font-size: 10pt "></span>').text("  "+cancion + " de " + artista+ "   ");
		         	        
		         	        return foo;
		         	    }
		         	});
	 			
	 			$('#ArtistAlbums').append(html2_1); 	
	 			$('#ArtistAlbums').append(html2_2);
 			}
	 			
 		}
 		//se produce un error al buscar el album del artista
 		function actualizarAlbumesArtistaError(){		
 			gets++;
 		//	console.debug("gets2 x: "+gets); 	
 			if(gets>=numalbumes-5){
				//console.debug("gets total x: "+gets); 	
				$('#searchGif').hide();
			}
 		}
 		
 
 
	    function searchAlbum( artista, album ){
	    	
	    	 closeSuggestions();
	    	
			//alert('searchAlbum.do?artist='+artista+'&album='+album);
			artista=replaceAll(artista,' ','+');
			album=replaceAll(album,' ','+');
			$('#searchGif').show();
			$('#searchresults').load('searchAlbum.do?artist='+artista+'&album='+album, function() {
	   			//alert('Load was performed.');
				$('#searchGif').hide();
				//muestra la pestaña
				//showAlbums();
				showMusica();
   			});
			
			//busqueda en los videos de youtube
			youtubeSearch(artista+"+"+album);
			
			
			
	    }
	    
	    //FIXME Esta función no hace nada ahora mismo, solo es una copia de la de arriba para 
	    //hacer el menu secundario en la pagina de albums 
	    function searchAlbum2( artista, album ){
	    	 closeSuggestions();
	    	
			//alert('searchAlbum.do?artist='+artista+'&album='+album);
			artista=replaceAll(artista,' ','+');
			album=replaceAll(album,' ','+');
			$('#searchGif').show();
			$('#div_tab_albums').load('searchAlbum.do?artist='+artista+'&album='+album, function() {
	   			// alert('Load was performed.');
				$('#searchGif').hide();
				//muestra la pestaña
				showAlbums();
   			});
			//busqueda en los videos de youtube
			youtubeSearch(artista+"+"+album);
			
	    }
	    
	    function getTrackInfo(cancion, artista){
	    	//alert('1');
			var trackInfo={"id":"182354617","nombre":"A+Milli","album":"A+Milli","artista":"Lil%27+Wayne","duracion":"223"};
			//alert("id: "+trackInfo.artista);
			$.getJSON('getTrackInfo.do?cancion='+cancion+"&artista="+artista, function(data) {
				var test=data;				
				//alert("artista: "+test.artista);
				 reproducirCancionEnCola(test.id,test.nombre,test.artista,test.album,test.duracion);
			});
	    }
	    
	    
	    function getTrackInfo2(cancion, artista){
	    	//alert('2');
			var trackInfo={"id":"182354617","nombre":"A+Milli","album":"A+Milli","artista":"Lil%27+Wayne","duracion":"223"};
			//alert("id: "+trackInfo.artista);
			$.getJSON('getTrackInfo.do?cancion='+cancion+"&artista="+artista, function(data) {
				var test=data;				
				//alert("artista: "+test.artista);
				// reproducirCancionEnCola(test.id,test.nombre,test.artista,test.album,test.duracion);
				 anadirCancionEnCola(test.id,test.nombre,test.artista,test.album,test.duracion);
			});
	    }
	    
	    	    
	    function youtubeSearch(query){
	    	var tamanyoPantalla=TamVentana();
		   // alert('buscando en youtube... '+query);
		    $('#youtubeResults').load('youtubeSearch.do?q='+query+'&tamPantalla='+tamanyoPantalla[0], function() {
		    	$("#searchVideos").hide();
   			});
	    }