/**
 * codigo para el login
 */

var getCancionesAjax;
var getArtistasAjax;
var getAlbumsAjax;

 //-------------------------------------------------------			 
			 //autocompletador
			 $(document).ready(function(){
				 /*
				 $( "#buscar_txt" ).focus(function (){
					  var txt=$( "#buscar_txt" ).val();
			    	  if(txt.length>=3){
			    	  	openSuggestions();
			    	  }					 
				 });
				 */
				 $( "#buscar_txt" ).click(function (){
					 openSuggestions();
				 });				 
				  $( "#buscar_txt" ).keyup(function() {  
					  openSuggestions();	
					  
					  //aborta la peticion anterior si no había terminado
					  if (typeof(getCancionesAjax) != "undefined"){
						  getCancionesAjax.abort();
						  console.debug("cancelando peticiones anteriores de canciones");
					  }
					  if (typeof(getAlbumsAjax) != "undefined"){
						  getAlbumsAjax.abort();
						  console.debug("cancelando peticiones anteriores de albums");
					  }
					  if (typeof(getArtistasAjax) != "undefined"){
						  getArtistasAjax.abort();
						  console.debug("cancelando peticiones anteriores de artistas");
					  }
					 // alert('key: ' + key + ' val: ' + val.name );
			    	  var txt=$( "#buscar_txt" ).val();
			    	  if(txt.length>=3){
			    		  console.debug("llamada a autocompletador");
			    		 // $( "#suggestions" ).html("");    		  
			    		  var numResults=5;
			    		  var html="";
			    		  var htmlCanciones="";
			    		  var htmlArtistas="";
			    		  var htmlAlbums="";
			    		  $( "#suggSearch" ).html("<table id='suggtable' border='0' celpadding='0' cellspacing='0'  bgcolor='f0f0f0'>" +
			    				"<col class='searchTabletd1'/>"+
			    				"<col/>"+
			    		  		"<thead><tr><th class='searchTabletd1'><img src='images/autocompletador/Music_Alt-32.png'></th><th class='searchTabletd3'>Canciones</th></tr></thead>" +
			    		  		"<tbody id='suggtableCanciones'></tbody>" +
			    		  		"<thead><tr><th class='searchTabletd1'><img src='images/autocompletador/User-32.png'></th><th class='searchTabletd3'>Artistas</th></tr></thead>" +
			    		  		"<tbody id='suggtableArtistas'></tbody>" +
			    		  		"<thead><tr><th class='searchTabletd1'><img src='images/autocompletador/Music-Record-32.png'></th><th class='searchTabletd3'>Albums</th></tr></thead>" +
			    		  		"<tbody id='suggtableAlbums'></tbody>" +
			    		  		"</table>");
			    		  
			    		  //autocompletador para canciones
			    		  getCancionesAjax= $.getJSON('http://ws.audioscrobbler.com/2.0/?method=track.search&track='+txt+'&api_key='+api_key+'&format=json', function(data) {			    			  
			    			  var tracks=data.results["trackmatches"].track;
			    			  thetracks = new Array();        			 	
			    			  var cont=0;
			    			  $.each(tracks, function(key, val) {
			   				  	thetracks[cont] = val.name + " - " +val.artist;
			   				  	var name=escape(val.name);
			   				  	var artist=escape(val.artist);
			   				 	if(cont<numResults){
			   				  		htmlCanciones+="<tr onclick='searchCancionSuggested(\""+name+"\",\""+artist+"\");'><td></td><td class='searchTabletd2'><a href='#' style='text-decoration:none;color: black;' onclick='searchCancionSuggested(\""+name+"\",\""+artist+"\");'>"+val.name + " - " +val.artist+"</a></td></tr>";
			   				  		console.debug("numero de resultados canciones: "+cont);
			   				 	}
			   				 	cont++;       				    
			   				  }); 
			    			  
			    			  $("#suggtableCanciones").append(htmlCanciones);
			    		});  
			    		  
			    		//autocompletador para artistas
			    		  getArtistasAjax=$.getJSON('http://ws.audioscrobbler.com/2.0/?method=artist.search&artist='+txt+'&api_key='+api_key+'&format=json', function(data) {
			      			    			  
			   			    var artists=data.results["artistmatches"].artist;
			   			    thetracks = new Array();        			 	
			   			    var cont=0;
			   			    $.each(artists, function(key, val) {
							  	thetracks[cont] = val.name;
							  	if(cont<numResults){
							  		var name=val.name;
							  		if(name.length>50){
							  			name=name.substring(0,30);
							  			name+="...";
							  		}
							  		console.debug("numero de resultados artistas: "+cont);
							  		var name2=escape(val.name);
							  		htmlArtistas+="<tr onclick='searchArtistSuggested(\""+name2+"\");'><td></td><td class='searchTabletd2'><a href='#' style='text-decoration:none;color: black;' onclick='searchArtistSuggested(\""+name2+"\");'>"+name + "</a></td>";
			   			    	}
							 	cont++;       				    
							  }); 
				   			$("#suggtableArtistas").append(htmlArtistas);
			      		}); 
			    		
			  			//autocompletador para albums
			    		  getAlbumsAjax=$.getJSON('http://ws.audioscrobbler.com/2.0/?method=album.search&album='+txt+'&api_key='+api_key+'&format=json', function(data) {			  				    			  
			   			    var albums=data.results["albummatches"].album;
			   			    thetracks = new Array();        			 	
			   			    var cont=0;
			   			    $.each(albums, function(key, val) {
							  	thetracks[cont] = val.name;
								var name=escape(val.name);
			   				  	var artist=escape(val.artist);
							  	if(cont<numResults){
							  		console.debug("numero de resultados albums: "+cont);
							  		htmlAlbums+="<tr onclick='searchAlbumSuggested(\""+artist+"\",\""+name+"\");'><td></td><td class='searchTabletd2'><a href='#' style='text-decoration:none;color: black;' onclick='searchAlbumSuggested(\""+artist+"\",\""+name+"\");'>"+val.name + " - " +val.artist+"</a></td></tr>";
							  	}
							 	cont++;       				    
							  }); 			   			 	
			   			 $("#suggtableAlbums").append(htmlAlbums);
			   			 $("#suggtableAlbums").append("<tr class='finTable'><td></td><td>&nbsp;</td></tr>");
			      		}); 
			    		  
			    	  } 
			    	  else{
			    		  $( "#suggAlbums" ).html("");
						  $( "#suggArtistas" ).html("");
						  $( "#suggCanciones" ).html("");
						  
						  $("#suggtableArtistas").html("");
						  $("#suggtableCanciones").html("");
						  $("#suggtableAlbums").html("");
						  closeSuggestions();
			    	  }
			    	});				  
				});	
						 			 
			 //-------------------------------------------------------



function searchAlbumSuggested(artista, nombre) {
	$("#buscar_txt").val(unescape(nombre + " de " + artista));
	searchAlbum(artista, nombre);
	 closeSuggestions();
}

function searchArtistSuggested(artista) {
	$("#buscar_txt").val(unescape(artista));
	searchArtist2(artista);
	 closeSuggestions();
}

function searchCancionSuggested(nombre, artista) {
	$("#buscar_txt").val(unescape( nombre+ " de " + artista));
	getInfoTrack(nombre, artista);
	 closeSuggestions();
}