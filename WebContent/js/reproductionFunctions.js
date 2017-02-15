
/**
 * Funciones de reproduccion javascript
 * 
 */


		/**
		 * Funciones para detectar la tecla pulsada para hacer un pause y subir y bajar volumen
		 */
		var volume=0;
		function callkeydownhandler(evnt) {
			
		   var code = evnt.keyCode ? evnt.keyCode : evnt.which ? evnt.which : evnt.charCode;		
		   //alert('keycode: '+code);
		   if(code=='179'){			   
			   if(getPlayerState()==1){
				   //alert("pause"); 
				   pause();	
				   $("#play.ui-icon").removeClass("ui-icon-play");
				   $("#play.ui-icon").addClass("ui-icon-pause");
				   
			   }
			   else if(getPlayerState()==2){
				   //alert("play"); 
				   play();
				   $("#play.ui-icon").addClass("ui-icon-play");
				   $("#play.ui-icon").removeClass("ui-icon-pause");
			   }
			   //play_pause();
		   }
		   if(code=='173'){
			   mimute();
		   }
		   
		   //bajar el volumen
		   if(code=='174'){			   
			   var volume=getVolume();
			   if(volume>0){
				   volume=volume-10;
				  if(volume<0){
					   volume=0;
					   $("#mutebtn.ui-icon").removeClass("ui-icon-volume-on");
					   $("#mutebtn.ui-icon").addClass("ui-icon-volume-off");
				  }
			   }
			   else{
				   $("#mutebtn.ui-icon").removeClass("ui-icon-volume-on");
				   $("#mutebtn.ui-icon").addClass("ui-icon-volume-off");
			   }
			   
			   
			   
			   $( "#slider2" ).slider( "value" ,volume);
			   setVolume(volume);
		   }
		   
		   //subir volumen
		   if(code=='175'){
			   var volume=getVolume();			   
			   if(volume<100){
				   volume+=10;
				   if(volume>100)
					   volume=100;
				   
			   }
			   $( "#slider2" ).slider( "value" ,volume);
			   setVolume(volume);
			   $("#mutebtn.ui-icon").removeClass("ui-icon-volume-off");
			   $("#mutebtn.ui-icon").addClass("ui-icon-volume-on");
		   }
		}
			
		

		if(typeof String.prototype.trim !== 'function') {
		  String.prototype.trim = function() {
		    return this.replace(/^\s+|\s+$/g, ''); 
		  }
		}



		//esta variable es para que al pasar a la siguiente cancion de la lista no pase mas de 
		//una cancion ya que se hace comprobando el estado del reproductor y este se comprueba
		//cada cierto tiempo.
		var encSigCancion=true;
		
		var reproduccionAleatorio=false;
		var repetirCancion=false;
		var fullScreen=false;
		
		function myFullsize(){
			if(fullScreen==true){
				testOriginalsize();
			}
			else{
				testFullsize();
			}
		}
		
		function testFullsize(){
			$('#superior').hide();
			$('#tabs').hide();		
			fullScreen=true;
			var tam=TamVentana();
			$('#myytplayer').css('position', 'absolute');
			$('#myytplayer').css('top', '0');
			$('#myytplayer').css('left', '0');
			$('#myytplayer').css('width', tam[0]-50);
			$('#myytplayer').css('height', tam[1]-150);	
		}	

		
		function testOriginalsize(){
			$('#superior').show();
			$('#tabs').show();	
				
			fullScreen=false;
			var tam=TamVentana();
			$('#myytplayer').css('position', 'relative');
			$('#myytplayer').css('top', '250');
			$('#myytplayer').css('left', '0');
			$('#myytplayer').css('width', 251);
			$('#myytplayer').css('height', 200);				
		}		
		

		/**
		 * Funcion para repetir la cancion que se esta escuchando, lo que hace es poner a on/off 
		 * el boton de repetir la cancion
		 * @return
		 */
		function repetirCancionFunction(){
			if(repetirCancion)
			{
				repetirCancion=false;
				$('#repeat').removeClass('ui-state-active ui-corner-all');
				$('#repeat').addClass('ui-state-default ui-corner-all');
			}
			else{
				repetirCancion=true;
				$('#repeat').removeClass('ui-state-default ui-corner-all');
				$('#repeat').addClass('ui-state-active ui-corner-all');
			}
			//alert("repetición: "+repetirCancion);
		}
	
		/**
		 * Funcion para escuchar de forma aleatoria una lista, lo que hace es poner a on/off 
		 * el boton de alatorio
		 * @return
		 */
		function reproduccionAleatoriaFunction(){
			if(reproduccionAleatorio)
			{
				reproduccionAleatorio=false;
				$('#shuffle').removeClass('ui-state-active ui-corner-all');
				$('#shuffle').addClass('ui-state-default ui-corner-all');
			}
			else{
				reproduccionAleatorio=true;
				$('#shuffle').removeClass('ui-state-default ui-corner-all');
				$('#shuffle').addClass('ui-state-active ui-corner-all');
			}
			//alert("aleatorio: "+reproduccionAleatorio);
		}
		
		/*
		 * Function que toma la cancion y el autor de last.fm y llama a track.getInfo de la api de last.fm
		 * para tomar todos los datos de la cancion y reproducirla despues.
		 */
		function getCancion(cancion, artista){
			$.get('getCancion.do?cancion='+cancion+"&artista"+artista, function(data) {
				var id=data;
				//alert(id);	   			
			});
		}

		//funciones para la cola de reproduccion
	    function reproducirCancionEnCola(id,nombre,artista,album,duracion,idYoutube)
		{	    
	    	//alert('nombre: '+ nombre+ ' artista: '+artista+ ' duracion: '+duracion+ ' idYoutube: '+idYoutube);
	    	$.getJSON('setListaRep.do?modo=new&id='+id+'&nombre='+nombre+'&artista='+artista+'&album='+album+'&duracion='+duracion+'&idYoutube='+idYoutube, function(data)  {
				 // alert('echo3! - data: '+data);
				  listaRep=data;
				  
				  reproducirLista();
			});		
	    	
	    	//alert('reproducirCancionEnCola - listaRep: '+listaRep);
	    	
	    	/*
			listaRep = {
				    "nombre" : "milista",
				    "temas":[
				    	{
				    		"nombre" : 	unescape(nombre),
				    		"artista": 	unescape(artista),
				    		"album"	 : 	unescape(album),
				    		"id"     :  id,
				    		"duracion" : duracion,
				    		"reproduciendo" : "true"
				    	}
				    ]
				};
			*/
			
		}
	    
	    function anadirCancionEnCola(id,nombre,artista,album,duracion,idYoutube)
		{			
	    	//alert('anadirCancionEnCola - listaRep: '+listaRep);
	    	if(listaRep.temas=="undefined"){
	    		//console.debug('lista vacia. Reproduciendo');	    		
	    		$.getJSON('setListaRep.do?modo=new&id='+id+'&nombre='+nombre+'&artista='+artista+'&album='+album+'&duracion='+duracion+'&idYoutube='+idYoutube, function(data)  {
					 // alert('echo3! - data: '+data);
					  listaRep=data;
				});
	    		reproducirLista();
	    		//reproducir(id,nombre, artista, album,duracion);
	    	}
	    	else{	 	    		
	    		//console.debug('lista no vacia. Reproduciendo');	    		
	    		$.getJSON('setListaRep.do?modo=add&id='+id+'&nombre='+nombre+'&artista='+artista+'&album='+album+'&duracion='+duracion, function(data)  {
					// alert('echo3! - data: '+data);
					listaRep=data;
					var temas=listaRep.temas;
					for(var i=0;i<temas.length;i++)
					{
					 //console.debug("tema: "+temas[i].nombre);
					}	
					$('#searchGif').show();		
					$('#actualRepro').load('music/colaReproduccion.jsp', function() {				
						$('#searchGif').hide();
			   			
		   			});	
				});
	    	}
			$('#searchGif').show();
			$('#actualRepro').load('music/colaReproduccion.jsp', function() {
				$('#searchGif').hide();
   			});
		}		
	   	    
	    
	    function anadirAlbumEnCola(album,artista)
		{				    	
	    	//alert('artista: '+artista + " album: "+album);
	    	if(listaRep.temas==undefined){
	    		//console.debug('lista vacia. Reproduciendo');
	    		 reproducirAlbumEnCola('','','',artista,album);	    		
	    	}
	    	else{	 
	    		//console.debug('lista no vacia!');	    		
	    		$('#searchGif').show();			
				
	    		$.getJSON('getListaRepAlbum.do?album='+album+"&artista="+artista, function(data) {
					var colocarTemas=data;
					var temas = listaRep.temas;
					var temas2=colocarTemas.temas;
					//console.debug("temas: "+temas2);
					for(var i=0;i<temas2.length;i++)
					{
						
						tema =  {
					    		"nombre" : 	temas2[i].nombre,
					    		"artista": 	temas2[i].artista,
					    		"album"	 : 	temas2[i].album,
					    		"id"     :  temas2[i].id,
					    		"duracion" : temas2[i].duracion,
					    		"reproduciendo" : "false"
					    };
						//console.debug("añadiendo: "+temas2[i].nombre);
						temas.push(tema);
					}			
					
					$('#actualRepro').load('music/colaReproduccion.jsp', function() {						
						$('#searchGif').hide();
			   			 // alert('Load was performed.');
						
						//comprobar el estado de la lista antes de poner a reproducir
						state=getPlayerState();
						if(state==-1 || state==0)
						{
							reproducirLista();
						}
		   			});			
								 				 	
				});					
	    	}
			$('#searchGif').show();
			$('#actualRepro').load('music/colaReproduccion.jsp', function() {
				$('#searchGif').hide();
   			});   	
			//verListaReproduccion();
		}		

	    function quitarCancionDeCola(id,nombre,artista,album,duracion)
		{		    	
	    	listaRepOrig=listaRep;
	    	$.getJSON('setListaRep.do?modo=quit&id='+id+'&nombre='+nombre+'&artista='+artista+'&album='+album+'&duracion='+duracion, function(data)  {
				// alert('echo3! - data: '+data);
				listaRep=data;
				$('#searchGif').show();		
				$('#actualRepro').load('music/colaReproduccion.jsp', function() {				
					$('#searchGif').hide();
		   			
	   			});	
				
				var iguales=compararListasRepro(listaRepOrig, listaRep)
				if(!iguales){
					reproducirLista();
				}
			});
	    	
			/*
	    	//console.debug('quitando la cancion '+nombre+ ' de la cola de reproduccion');
	    	 var temas = listaRep.temas;	
	    	 var j=0;
	    	 var enc=false;
	    	 
    		 var s_nombre;
    		 var s_album;
    		 var s_artist;
    		 
	    	 while(j<temas.length && enc==false){
	    		
	    		 s_nombre=unescape(temas[j].nombre);
	    		 s_album=unescape(temas[j].album);
	    		 s_artist=unescape(temas[j].artista);
	    		 
	    		 if(s_nombre==nombre && (s_album==album) && (s_artist==artista))
	 		     {	    			 
	    			 enc=true;	 
	    		 	 temas.splice(j,1);
	 		     }	 		     
	    		 j++;
	    	 } 	    	
	    	
			$('#searchGif').show();
			$('#actualRepro').load('music/colaReproduccion.jsp', function() {
				$('#searchGif').hide();
   			});   	*/
		}		
    
		function reproducirAlbumEnCola(idTrack,idAlbum,nombreCancion,artista,album){
			//alert("album: "+album + " - a: artist"+ artista+ " - cancion: "+nombreCancion + " id: "+idTrack);
			//llamamos a la pagina que devuelve el json de la lista que se va a crear
			$('#searchGif').show();			
			$.getJSON('getListaRepAlbum.do?album='+album+"&artista="+artista+"&idTrack="+idTrack, function(data) {
				var id=data;
			 	listaRep=id;
				$('#actualRepro').load('music/colaReproduccion.jsp', function() {
					
					$('#searchGif').hide();
		   			 // alert('Load was performed.');
					reproducirLista();
	   			});			
							 				 	
			});					
			
   			//reproducimos la lista completa desde la cancion señalada
   			//hay que hacer una funcion nueva parecida a la de reproduccion de un album pero que
   			//sirva para cualquier lista que se le pase, se puedan añadir a la lista, eliminar...
		}
		
		function reproducirArtistaEnCola(idTrack,idAlbum,nombreCancion,artista,album){
			//alert("se va a reproducir el artista con todo en cola: "+album + " de "+ artista+ " empezando por: "+nombreCancion + " id: "+idTrack);
			//llamamos a la pagina que devuelve el json de la lista que se va a crear
			$('#searchGif').show();			
			$.getJSON('getListaRepArtist.do?album='+album+"&artista="+artista+"&idTrack="+idTrack, function(data) {
				var id=data;
			 	listaRep=id;
				$('#actualRepro').load('music/colaReproduccion.jsp', function() {
					
					$('#searchGif').hide();
		   			 // alert('Load was performed.');

					reproducirLista();
	   			});			
							 				 	
			});					
			
   			//reproducimos la lista completa desde la cancion señalada
   			//hay que hacer una funcion nueva parecida a la de reproduccion de un album pero que
   			//sirva para cualquier lista que se le pase, se puedan añadir a la lista, eliminar...
		}
			
		/**
		 * Esta funcion se lanza si se ha pega en el navegador un enlace con uri http de copiar una cancion!
		 * la funcion gup esta en interfaz.js y extrae el valor del parametro de la url que se pasa como 
		 * parametro
		 *  
		 * @return
		 */
		function reproducirVideo(){   			
			anadirCancionEnCola(gup( 'v' ),gup( 'nombre' ),gup( 'artista' ),gup( 'album' ),gup( 'duracion' ));
			$('#searchGif').hide();
		}
		
	    function reproducir(id,cancion, artista, album,duracion){
	    	
		   // alert('id de la cancion: '+id);
		    listaRep = {
				    "nombre" : "song",
				    "temas":[
				    	{
				    		"nombre" : 	cancion,
				    		"artista": 	artista,
				    		"album"	 : 	album,
				    		"id"     :  id,
				    		"duracion" : duracion,
				    		"reproduciendo" : "true"
				    	}
				    ]
			};
			
		    $('#searchGif').show();
			$('#actualRepro').load('music/colaReproduccion.jsp', function() {				
				actualizarAlbumReproduccion(artista,album);		
				$('#searchGif').hide();
   			});		    
		    			
		    //ponemos la variable de errores de pista a 0
			errorsOnPlaySong=0;
			ytplayer.addEventListener("onError", "errorOnPlayList");
				 
			cancion=cancion.trim();
			var song=cancion;
			cancion=replaceAll(cancion,' ','+');
			artista=artista.trim();
			var artist=artista;
			artista=replaceAll(artista,' ','+');
			cancion=cancion+ " - " + artista;		
				
			$.get('reproducirVideo.do?cancion='+cancion, function(data) {
				var id=data;
				id=id.trim();
				
				var c1=replaceAll(song,'+',' ');
				var s1=replaceAll(artist,'+',' ');
				//alert("c1: "+c1 + " - s1: "+s1);
				$("#reproductionTitle").text(unescape(c1));
				$("#reproductionArtist").text(unescape(s1));
				
			 	loadNewVideo(id, "0");
	   			loaded1=true;
	   			loaded2=false;	   			
			});			
			
			//busqueda en los videos de youtube
			//youtubeSearch(cancion);

			$("#play").removeClass("ui-icon ui-icon-pause");			
			$("#play").addClass("ui-icon ui-icon-play");	
	    }

	    //Reproducción de lista de reproducción
		function reproducirLista(){
			showRepo();
			$.getJSON('getListaRep.do', function(data)  {
				//alert('getListaRep! - data: '+data);
				if(data==null){
					//console.debug('no hay lista de reproducción para cargar');
				}
				else{
					listaRep=data;
				    var temas = listaRep.temas;
				    //console.debug('lista reproduccion');
				    /*
		 	    	for (var x = 0 ; x < temas.length ; x++) 
		 	    	{
		 		    	console.debug('nombre: '+temas[x].nombre+' album: '+temas[x].album+' artista='+temas[x].artista+' & album='+temas[x].album+' & reproduciendo='+temas[x].reproduciendo+' & idYoutube='+temas[x].idYoutube);							
		 		    }		 	  
		 		    */  	
				    //console.debug();
				    $('#searchGif').show();		
					$('#actualRepro').load('music/colaReproduccion.jsp', function() {				
						$('#searchGif').hide();
			   			 // alert('Load was performed.');
		   			});		
					
					//ponemos la variable de errores de pista a 0
					 errorsOnPlaySong=0;		
					 	 
					 var temas = listaRep.temas;
					 ytplayer.addEventListener("onStateChange", "test2");
					 ytplayer.addEventListener("onError", "errorOnPlayList");
			 		    
		 	    	for (var x = 0 ; x < temas.length ; x++) {
		 		    	//alert('nombre: '+temas[x].nombre + ' id: '+temas[x].id);
		 		    	if(temas[x].reproduciendo=='true')
		 		    	{ 		    							
							//------------
		 		    		cancion=temas[x].nombre.trim();
							var cancion2=replaceAll(cancion,' ','+');
							artista=temas[x].artista;
							var artist=artista;
							var artista2=replaceAll(artista,' ','+');
							cancion=cancion2+ " - " + artista2;	
							
							var myalbum=temas[x].album;
							var duracion=temas[x].duracion;
							//alert('tema: '+temas[x].nombre + " - youtube: "+temas[x].idYoutube);
							if(temas[x].idYoutube==''){
								$.get('reproducirVideo.do?cancion='+cancion, function(data) {
									var id=data;
									id=id.trim();
									
									var c1=replaceAll(cancion2,'+',' ');
									var s1=replaceAll(artist,'+',' ');
									myalbum=replaceAll(myalbum,'+',' ');
									//alert("c1: "+c1 + " - s1: "+s1);							
									
									//----------------------------------------------------------------
									$.get('getAlbumImage.do?artista='+artista+"&album="+myalbum, function(data) {
										var imageAlbum=data;
										imageAlbum=trim(imageAlbum);
										if(imageAlbum=="404"){
											imageAlbum="http://localhost:8080/SputiflyJars/images/albumNotFound.jpg";
										}
										$('#imageAlbum').show();
										
										actualizaInfoReproduccion(c1,s1,myalbum,duracion,'',imageAlbum);
										establecerModoReproduccion();
									});	
									
									//-----------------------------------------------------------------
									
									//TODO LLAMAR para pedir información del album para poner en 
									//la pantalla de información del panel lateral lateral izquierdo
									
								 	loadNewVideo(id, "0");
						   			loaded1=true;
						   			loaded2=false;	
						   			encSigCancion=true;
								});
							}
							else{
								var c1=replaceAll(cancion2,'+',' ');
								var s1=replaceAll(artist,'+',' ');
								myalbum=replaceAll(myalbum,'+',' ');
								//alert("c1: "+c1 + " - s1: "+s1);							
								
								//----------------------------------------------------------------
								$.get('getAlbumImage.do?artista='+artista+"&album="+myalbum, function(data) {
									var imageAlbum=data;
									imageAlbum=trim(imageAlbum);
									if(imageAlbum=="404"){
										imageAlbum="http://localhost:8080/SputiflyJars/images/albumNotFound.jpg";
									}
									$('#imageAlbum').show();
									
									actualizaInfoReproduccion(c1,s1,myalbum,duracion,'',imageAlbum);
									establecerModoReproduccion();
								});	
								
								
								loadNewVideo(temas[x].idYoutube, "0");
					   			loaded1=true;
					   			loaded2=false;	
					   			encSigCancion=true;
							}
								
							
		 		    	}
		 	    	} 	    	
			 	    //verListaReproduccion();
				}	
				
				$('#repInfo').show();
				
			});
			
		}		
		
		function actualizaInfoReproduccion(cancion,artista,album,duracion,año,imageAlbum){
			//alert("actualizando información artista: "+artista);
			//actualiza informacion en panel lateral
			$("#nombre_pl").text(unescape(cancion));
			$("#artista_pl").text(unescape(artista));
			//alert('album: '+album + ' - duration: '+duracion);
			$('#imageAlbum').attr("src",imageAlbum);
			$("#infoAlbum").text(''+unescape(album));
			$("#infoDuration").text(''+segundos_a_minutos(duracion));
			
			//actualiza información en menu de cabecera
			$('#repInfoImg').attr("src",imageAlbum);
			$("#repInfoImg_cancion").text(unescape(cancion));
			$("#repInfoImg_artista").text(decodeURIComponent(artista));
			
			
		}
		
		
		
		/**
		 * Esta función esta puesta como evento del reproductor, es decir cuando algo pasa en el reproductor
		 * empieza una cancion, termina, pause, error, se llama a esta función, es por tanto el corazón de la
		 * funcionalidad de reproducción
		 * 
		 * @return
		 */
		function test2(){
			//alert('cambio de estado');
			state=getPlayerState();
   			//console.debug("state: "+state +" video url: "+getVideoUrl());
   			//alert("state: "+state +" video url: "+getVideoUrl());
			if(state==0 && encSigCancion)
			{	
				encSigCancion=false;
				if(repetirCancion)
				{
						//alert('debería repetir la canción');
						reproducirLista();
				}else if(reproduccionAleatorio){
					$.getJSON('setListaRep.do?modo=random', function(data)  {
						 // alert('echo3! - data: '+data);
						  listaRep=data;
						  reproducirLista();
					});  
				}else{
					$.getJSON('setListaRep.do?modo=next', function(data)  {
						 // alert('echo3! - data: '+data);
						  listaRep=data;
						  reproducirLista();
					});  
				}
				
				/*
				encSigCancion=false;
				console.debug('finalizado y buscando la siguiente');
				//ponemos la variable de errores de pista a 0
				 errorsOnPlaySong=0;
				
				var temas = listaRep.temas;
				var x=0;
				var enc=false;
				while(x<temas.length && !enc)
				{
					console.debug('...buscando la siguiente');
					if(temas[x].reproduciendo=='true' && x+1<temas.length)
	 		    	{
		 		    	enc=true;
		 		    	if(!repetirCancion && !reproduccionAleatorio)
		 		    	{
		 		    		temas[x].reproduciendo='false';	 		    		
		 		    		temas[x+1].reproduciendo='true';
		 		    	}
		 		    	if(reproduccionAleatorio){
		 		    		var numeroAleatorio=generarAleatorio(0,temas.length);
		 		    		temas[x].reproduciendo='false';	 	
		 		    		temas[numeroAleatorio].reproduciendo='true';
		 		    		//alert("numeroAleatorio: "+numeroAleatorio);
		 		    	}
	 		    		reproducirSigCancionLista();
	 		    	}
					if(temas[x].reproduciendo=='true' && x+1<=temas.length)
					{
						console.debug('...repitiendo la ultima/unica cancion');
						if(repetirCancion)
		 		    	{
							reproducirSigCancionLista();
		 		    	}
						if(reproduccionAleatorio){
		 		    		var numeroAleatorio=generarAleatorio(0,temas.length);
		 		    		temas[x].reproduciendo='false';	 	
		 		    		temas[numeroAleatorio].reproduciendo='true';
		 		    		//alert("numeroAleatorio: "+numeroAleatorio);
		 		    		reproducirSigCancionLista();
		 		    	}	 		    		
					}
					else{
						console.debug('tracks: '+temas.length + " repetir: "+repetirCancion);
					}
	 		    	x++;
				}
				console.debug("enc: "+enc);
				*/
			}
			
		}
			

		/**
		* Esta funcion se lanza cuando el usuario selecciona una cancion de la lista de reproduccion
		*/
		function elegirCancionDeLaLista(cancion,album){
			//console.debug('elegirCancionDeLaLista()');			
			//alert("cancion: "+cancion + " - album: "+album);		
			//ponemos la variable de errores de pista a 0
			/*
			errorsOnPlaySong=0;
			var temas = listaRep.temas;
			var selectedSong=0;
			var actualSong=0;
 	    	for (var x = 0 ; x < temas.length ; x++) 
 	    	{	   	    		
 	    		if(temas[x].reproduciendo=='true')
 		    	{  
 	 		    	actualSong=x;
 		    	} 
 	    		if(temas[x].nombre==unescape(cancion) && (temas[x].album==unescape(album) || temas[x].album==undefined))
	 		    {
 	    			selectedSong=x;			
	 		    }		    	
 	    		temas[actualSong].reproduciendo='false';
 	    		temas[selectedSong].reproduciendo='true';	
 	    	}
 	    	//reproducirLista();
 	    	*/
			$.getJSON('setListaRep.do?modo=choose&nombre='+cancion+'&album='+album, function(data)  {
				 // alert('echo3! - data: '+data);
				  listaRep=data;
				  reproducirLista();
			});    	    	
		}

		/**
		* Esta funcion se lanza cuando termina una cancion de la lista para que pase a la siguiente
		*/
		function reproducirSigCancionLista(){
			//alert('reproducirSigCancionLista()');
			//verListaReproduccion();
			//actualizamos la marca de reproduccion en la lista de reproduccion
			$('#searchGif').show();
			$('#actualRepro').load('music/colaReproduccion.jsp', function() {
				$('#searchGif').hide();
   			});   			
			
			//console.debug("reproduciendo la siguiente cancion de la lista: ");			
			var temas = listaRep.temas;	    
 	    	for (var x = 0 ; x < temas.length ; x++) {
 		    	//alert('nombre: '+temas[x].nombre + ' id: '+temas[x].id);
 		    	//console.debug("ee1 "+x);
 		    	
 		    	if(temas[x].reproduciendo=='true')
 		    	{ 		    			
 	 		    	var track_num=x;
 	 		    	track_num++;
					//alert('reproduciendo: '+temas[x].nombre);
 	 		    	actualizarAlbumReproduccion(temas[x].artista, temas[x].album);
 					$("#reproductionTitle").text(unescape(temas[x].nombre));
 					$("#reproductionArtist").text(unescape(temas[x].artista)); 	 		    	
 					
 					reproducirLista(); 
 		    	}
 	    	}
		}
		
		function reproducirAnteriorCancionLista(){
			//console.debug('reproducirAnteriorCancionLista()');
			//verListaReproduccion();
			//actualizamos la marca de reproduccion en la lista de reproduccion
			$('#searchGif').show();
			$('#actualRepro').load('music/colaReproduccion.jsp', function() {
				$('#searchGif').hide();
   			});   			
			
			//console.debug("reproduciendo la siguiente cancion de la lista: ");			
			var temas = listaRep.temas;	    
 	    	for (var x = 0 ; x < temas.length ; x++) {
 		    	//console.debug("ee1 "+x);
 		    	
 		    	if(temas[x].reproduciendo=='true')
 		    	{ 		    			
 	 		    	var track_num=x;
 	 		    	//alert('reproduciendo: '+temas[x].nombre);
 	 		    	actualizarAlbumReproduccion(temas[x].artista, temas[x].album);
 					$("#reproductionTitle").text(unescape(temas[x].nombre));
 					$("#reproductionArtist").text(unescape(temas[x].artista));	 		    	
 					
 					reproducirLista();
 		    	}
 	    	}
		}

		/**
		* Function para intentar encontrar una alternativa a las canciones eliminadas en youtube
		*/
		function errorOnPlayList(){			
			
			//console.debug("numero de error "+errorsOnPlaySong+"antes de incrementarlo");
			errorsOnPlaySong++;
			 
			state=getPlayerState();
			
			//console.debug("se ha producido un error durante la reproducción: "+state);
			if(state==100){
				//console.debug("video no encontrado");
			}
			else{
				//console.debug("El video ha sido eliminado por el autor");
			}
			
			//buscamos una alternativa al video:
			//console.debug("buscando cancion alternativa: ");			
			var temas = listaRep.temas;	    
 	    	for (var x = 0 ; x < temas.length ; x++) {
 		    	
 		    	if(temas[x].reproduciendo=='true')
 		    	{
 		    		//console.debug("eee2 - encontrada la pista de reproducción: ");
 		    		
 	 		    	var track_num=x;
 	 		    	track_num++;
					//alert('reproduciendo: '+temas[x].nombre);
					if(listaRep.nombre=="song")
					{
					//	console.debug("buscando alternativa de una sola cancioón");
						$.get('reproducirVideo.do?cancion='+temas[x].nombre+" - "+temas[x].artista+"&error="+errorsOnPlaySong, function(data) {
							var id=data;
							id=id.trim();						 	
						 	loadNewVideo(id, "0");
				   			loaded1=true;
				   			loaded2=false;
				   			
						});
					}
					else{
						//console.debug("buscando alternativa de unun disco");
						$.get('getAlbumTrack.do?artista='+temas[x].artista+'&album='+temas[x].album+'&track='+track_num+"&error="+errorsOnPlaySong, function(data) {
							var id=data;
						 	//$('#tituloReproduccion').text(temas[x].artista+" - ");
						 	loadNewVideo(id, "0");
				   			loaded1=true;
				   			loaded2=false;
				   			state=getPlayerState();
				   			//console.debug("state: "+state);
						});
					}
 		    	}
 	    	} 	    	
		}
	
		/**
		 * Este conjunto de 3 funciones controla la reproducción de un album completo
		 */
	    var cancionActual=1;
	    var myartista;
	    var myalbum;
	    var mynum_tracks=1;
	   
	    //reproduce un album completo
	    function reproducirAlbum(artista, album){
	    	$('#searchGif').show();			
			$.getJSON('reproducirAlbum.do?album='+album+"&artista="+artista, function(data) {
				var id=data;
			 	listaRep=id;
				$('#actualRepro').load('music/colaReproduccion.jsp', function() {					
					$('#searchGif').hide();
		   			// alert('Load was performed.');
					reproducirLista();
	   			});			 				 	
			});				
	    }
	   
	    function test(){
	    	//console.debug('test');
			//alert(number);
			state=getPlayerState();
			if(state==0)
			{
				cancionActual++;
				reproducirSigCancionAlbum(myartista, myalbum,mynum_tracks,cancionActual);
			}
			
	    }
	   
	    function reproducirSigCancionAlbum(artista, album,num_tracks,cancionActual){
	    	if(cancionActual<=num_tracks)
	    	{
	    		//console.debug("reproduciendo la siguiente cancion!");
		    	$.get('getAlbumTrack.do?artista='+artista+'&album='+album+'&track='+cancionActual, function(data) {
					var id=data;
				 	$('#tituloReproduccion').text(artista+" - ");
				 	loadNewVideo(id, "0");
		   			loaded1=true;
		   			loaded2=false;
		   			state=getPlayerState();
		   			//console.debug("state: "+state);
		   			
				});
	    	}		
	    }
	   
        function updateHTML(elmId, value) {
        	//alert(newState);
        }

        function setytplayerState(newState) {
          updateHTML("playerstate", newState);
        }

        function onYouTubePlayerReady2(playerId) {   
        
          ytplayer = document.getElementById("myytplayer");
        
          setInterval(updateytplayerInfo, 250);
          updateytplayerInfo();
          ytplayer.addEventListener("onStateChange", "onytplayerStateChange");
          ytplayer.addEventListener("onError", "onPlayerError");
          initiateSliderVolume();            
          initiateSlider();
          
        //---------------------------------------------------------------
  		
  		$.getJSON('getListaRep.do', function(data)  {
				  //alert('getListaRep! - data: '+data);
  				if(data==null){
  					//console.debug('no hay lista de reproducción para cargar');
  				}
  				else{
  						listaRep=data;
  					    var temas = listaRep.temas;
  					    //console.debug('lista reproduccion');
			 	    	for (var x = 0 ; x < temas.length ; x++) 
			 	    	{
			 		    	//console.debug('nombre: '+temas[x].nombre+' album: '+temas[x].album+' artista='+temas[x].artista+' & album='+temas[x].album+' & reproduciendo='+temas[x].reproduciendo);							
			 		    }		 	    	
  					   // console.debug();
  					    reproducirLista();
  				}				 
			});	  			
        }

        function onytplayerStateChange(newState) {
          setytplayerState(newState);
        }

        function onPlayerError(errorCode) {
          //alert("An error occured: " + errorCode);
        	//console.debug("ocurrio un error al reproducir el video: "+state);
        }
       
        function updateytplayerInfo() {   
        	updateSlider();
        // console.debug('updating... '+getDuration());
         
        }   

        function initiateSlider(){
        	
        	//console.debug('ReproductionFunctions - initiateSlider initial: '+getDuration());
        	$( "#slider" ).slider({
    			range: "min",
    			value: 0,
    			min: 1,
    			max: getDuration(),
    			slide: function( event, ui ) {
        			//console.debug('ui.value: '+ui.value)
    				$( "#amount" ).val( ui.value+"/"+getDuration() );        			
        			var seconds=(ui.value);
    				seekTo(seconds);
    				
    			}  			 			
    		});  
    		loaded2=true; 
    		
        	$( "#amount" ).val( segundos_a_minutos(getDuration()) );
    		//console.debug('ReproductionFunctions - initiateSlider '+segundos_a_minutos(getDuration()));
    		//alert('inicializado'); 

    		initiateSliderVolume();   	  	
        }
        
        function initiateSliderVolume(){
        	//console.debug('ReproductionFunctions - initiateSliderVolume');        	
        	$( "#slider2" ).slider({
    			range: "min",
    			value: getVolume(),
    			min: 1,
    			max: 100,
    			slide: function( event, ui ) {    				
    				var volume=ui.value;
    				setVolume(volume);
    				//alert('volumen: '+volume);	    				  				
    			}  			 			
    		});
        }

        function updateSlider(){
        	$( "#slider" ).slider({
    			range: "min",
    			value: 0,
    			min: 1,
    			max: getDuration()
        	});  
        	
        	var s=getCurrentTime();
        	
        	$( "#slider" ).slider( "value" ,s);
        	$( "#amount" ).html( segundos_a_minutos(getCurrentTime())+'/'+segundos_a_minutos(getDuration()) ); 
        }   
     
        function play_pause(){
        	//console.debug("play");
			var state=getPlayerState();
			//pausado
			if(state==2)
			{
				//console.debug("play");
				play();
				$("#play.ui-icon").removeClass("ui-icon-play");
				$("#play.ui-icon").addClass("ui-icon-pause");
//				$("#play").removeClass("ui-icon ui-icon-pause");			
//				$("#play").addClass("ui-icon ui-icon-play");				
				
			}
			//reproduciendo
			if(state==1){
				pause();
				$("#play.ui-icon").removeClass("ui-icon-pause");
				$("#play.ui-icon").addClass("ui-icon-play");
				
				//console.debug("pause");
//				$("#play").removeClass("ui-icon ui-icon-play");			
//				$("#play").addClass("ui-icon ui-icon-pause");
				
			}
        }
    
        function mimute(){
        	if(isMuted()){
				 unMute();  
				 $("#mutebtn.ui-icon").removeClass("ui-icon-volume-off");
				 $("#mutebtn.ui-icon").addClass("ui-icon-volume-on");
				 $.fancybox.open("&nbsp;&nbsp;&nbsp;<div style='width:150px'><img src='images/mute/sound.png' /></div>&nbsp;&nbsp;&nbsp;" );
				setTimeout("$.fancybox.close()", 1000);
			   }else{
				   mute();
				   $("#mutebtn.ui-icon").removeClass("ui-icon-volume-on");
				   $("#mutebtn.ui-icon").addClass("ui-icon-volume-off");
				   $.fancybox.open("&nbsp;&nbsp;&nbsp;<div style='width:150px'><img src='images/mute/Mute.png' /></div>&nbsp;&nbsp;&nbsp;" );
					setTimeout("$.fancybox.close()", 1000);
		   }
        }
        
        function previous(){
        	
        	//console.debug('previous');
        	if(getCurrentTime()>3){
        		//console.debug('getCurrentTime()>3');
        		seekTo(0);
        	}
        	else{
        		
        		$.getJSON('setListaRep.do?modo=previous', function(data)  {
   				 // alert('echo3! - data: '+data);
	   				  listaRep=data;
	   				  reproducirLista();
	   			});
	           
           	
        		/*
        		console.debug('getCurrentTime()<3');
        		
            	//ponemos la variable de errores de pista a 0
    			 errorsOnPlaySong=0;
    			
    			var temas = listaRep.temas;
    			var x=0;
    			var enc=false;
    			while(x<temas.length && !enc)
    			{    				
    				//alert('nombre: '+temas[x].nombre + ' id: '+temas[x].id);
    		    	if(temas[x].reproduciendo=='true' && x+1<=temas.length)
    		    	{
    		    		console.debug('encontrado - x: '+x);
    	 		    	enc=true;
    	 		    	if(x-1 >= 0){
	    		    		temas[x].reproduciendo='false';	 		    		
	    		    		temas[x-1].reproduciendo='true';	 		    		
	    		    		reproducirAnteriorCancionLista();
    	 		    	}
    		    	}
    		    	x++;
    			}
    			
    			if(enc==false){
    				console.debug('no se ha encontrado!');
    				//verListaReproduccion();
    			}
    			*/
        	}
        	
        	
        	
        }

        function next(){
        	/*
        	console.debug('next');
        	//ponemos la variable de errores de pista a 0
			 errorsOnPlaySong=0;
			
			var temas = listaRep.temas;
			var x=0;
			var enc=false;
			while(x<temas.length && !enc)
			{
				//alert('nombre: '+temas[x].nombre + ' id: '+temas[x].id);
		    	if(temas[x].reproduciendo=='true' && x+1<temas.length)
		    	{
	 		    	enc=true;
		    		temas[x].reproduciendo='false';	 		    		
		    		temas[x+1].reproduciendo='true';	 		    		
		    		reproducirSigCancionLista();
		    	}
		    	x++;
			}if(enc==false){
				console.debug('no se ha encontrado!');
				//verListaReproduccion();
			}
			*/
        	//alert('next');
        	$.getJSON('setListaRep.do?modo=next', function(data)  {
				 // alert('echo3! - data: '+data);
				  listaRep=data;
				  reproducirLista();
			});        	
        }        
    	

        	
			function establecerModoReproduccion(){
				
				if(modo=="videoClip"){
					//alert('modo video clips');
					$("#capaOcultacion").animate({"height": "1px"}, "slow");
					$('#imageAlbum').hide();
				}
				else if(modo=="musica"){
					//alert('modo musica');
					$('#imageAlbum').show();
					 var Tam = TamVentana(); 
					var size=((Tam[1]-280)/3);
					
					$("#capaOcultacion").animate({"height": 260}, "slow");
				}
											
			}
			
			function selectMusicMode(){
				modo=="musica"
				establecerModoReproduccion();		
			}
			
			
			function verListaReproduccion(){
				
				 var temas = listaRep.temas;
					    
	 	    	for (var x = 0 ; x < temas.length ; x++) {
	 		    	//alert('nombre: '+temas[x].nombre + ' id: '+temas[x].id);
	 		    	{
	 		    		//console.debug('nombre: '+temas[x].nombre+' album: '+temas[x].album+' artista='+temas[x].artista+' & album='+temas[x].album+' & reproduciendo='+temas[x].reproduciendo);							
	 		    	}
	 	    	}
			}
			
			function isDefined(variable) {
			    return (typeof(window[variable]) == "undefined")?  false: true;
			}
			
			function fullScreen(){
				//alert('toggling to full screen');
				 ytplayer = document.getElementById("myytplayer");
				 ytplayer.setSize(640,480);
				//alert('toggled');
			}
			
			
			
			/**
			 * Esta funcion sirve para cuando eliminamos una cancion en la lista de reproducción 
			 * si las canciones que se estan reproduciendo antes y la nueva no coinciden se debe lanzar 
			 * de nuevo la funcion de reproducir lista
			 * 
			 * @param listaRepOrig
			 * @param listaRepNueva
			 * @return
			 */
			function compararListasRepro(listaRepOrig, listaRepNueva){				
				 
				var iguales=true;
				
				var temas = listaRepOrig.temas;	
		    	 var j=0;
		    	 var i=0;
		    	 var enc=false;
		    	 
		    	 var nombre1;
		    	 var artista1;		    	 
		    	 var album1;

		    	 var nombre2;
		    	 var artista2;
		    	 var album2;
		    	 
		    	 while(j<temas.length && enc==false){   			    	 
		    		
		    		 if(temas[j].reproduciendo=='true')
		 		     {	    			 
		    			 enc=true;	 
		    			 nombre1=temas[j].nombre;
		    			 artista1=temas[j].artista;
		    			 album1=temas[j].album;	    			 
		 		     }	 		     
		    		 j++;
		    	 } 	  
		    	 
		    	 while(i<temas.length && enc==false){ 
			    	
		    		 if(temas[i].reproduciendo=='true')
		 		     {	    			 
		    			 enc=true;	 
		    			 nombre1=temas[i].nombre;
		    			 artista1=temas[i].artista;
		    			 album1=temas[i].album;	    			 
		 		     }	 		     
		    		 i++;
		    	 } 	  
		    	 
		    	 if(nombre1!=nombre2 || artista1!=artista2 || album1!=album2){
		    		 iguales=false;
		    	 }
		    	 return iguales;
			}
    		
			
			//----------------------------------------------------------------------------------			
			
			//*********************************************************************************//
			//																				   //
			//					Funciones para listas reprucción de usuarios				   //
			//																				   //			
			//*********************************************************************************//
			var myIdLista=0;
			function agregarALista(idLista,cancion,artista,album,duracion,idYoutube){	
				myIdLista=idLista;
				$.ajax({
					type: 'POST',
					url: 'addCancionToUserList.do?idLista='+idLista+'&cancion='+cancion+'&artista='+artista+'&album='+album+'&duracion='+duracion+'&idYoutube='+idYoutube,
					success: function(data,idLista) {
						if(data){
							//alert('canción agregada a la lista');
							$.fancybox.open("&nbsp;&nbsp;&nbsp; <h3>Canción agregada a la lista ;) </h3>&nbsp;&nbsp;&nbsp;" );
							setTimeout("$.fancybox.close()", 1000);
							getCancionesListaUsuario(myIdLista);
						}							
						else{
							alert('no se pudo agregar la canción a la lista');
						}	
					}
				});	
			}
						
			function getCancionesListaUsuario(idLista,nombreLista){	
				$("#div_tab_listas").load('getCancionesFromUserList.do?idLista='+idLista+"&nombreLista="+nombreLista, function() {					
					 showListas();
	   			});	
	   			
				/*
				$.getJSON('getCancionesFromUserList.do?idLista='+idLista, function(data)  {
					  alert('data: '+data[0].nombre);
					  //listaRep=data;					  
				});  	
				*/			
			}
			
			function getFavositosUsuario(){	
				$("#div_tab_listas").load('getFavoritosFromUser.do', function() {					
					 showListas();
	   			});		
			}
			
			function agregarAFavoritos(cancion,artista,album,duracion,idYoutube){	
				$.ajax({
					type: 'POST',
					url: 'addCancionToFavoritosUser.do?cancion='+cancion+'&artista='+artista+'&album='+album+'&duracion='+duracion+'&idYoutube='+idYoutube,
					success: function(data) {
						if(data==1){
							//alert('canción agregada a la lista');
							$.fancybox.open("&nbsp;&nbsp;&nbsp; <h3>Canción agregada a Favoritos ;) </h3>&nbsp;&nbsp;&nbsp;" );
							setTimeout("$.fancybox.close()", 1000);
							getFavositosUsuario();
						}							
						else if(data==0){
							$.fancybox.open("&nbsp;&nbsp;&nbsp; <h3>No se pudo agregar a favoritos :(</h3>&nbsp;&nbsp;&nbsp;" );
							setTimeout("$.fancybox.close()", 1000);
						}	
						else if(data==2){
							$.fancybox.open("&nbsp;&nbsp;&nbsp; <h3>El tema ya está en favoritos ;)</h3>&nbsp;&nbsp;&nbsp;" );
							setTimeout("$.fancybox.close()", 1000);
						}
					}
				});	
			}
			
			
			function reproducirListaUsuario(idCancion,idLista){
				var ids=idCancion.split("_");
				var miid=ids[1];
				$.getJSON('SetListaRepUserAction.do?idCancion='+miid+'&idLista='+idLista, function(data)  {
					  //alert('echo3! - data: '+data);
					  listaRep=data;
					  reproducirLista();
				});	
			}
			
			function reproducirCancionDeFavoritos(idCancion){
				var ids=idCancion.split("_");
				var miid=ids[1];
				$.getJSON('SetFavRepUserAction.do?idCancion='+miid, function(data)  {
					  //alert('echo3! - data: '+data);
					  listaRep=data;
					  reproducirLista();
				});	
			}
			
			function ponerCancionDeFavoritosEnCola(idCancion){
				var ids=idCancion.split("_");
				//alert(idCancion);
				var miid=ids[1];
				$.getJSON('getFavorito.do?idFavorito='+miid, function(data)  {
					var id=data.idCancion;
					var nombre=data.nombre;
					var artista=data.artista;
					var album=data.album;
					var duracion=data.duracion;
					var idYoutube=data.id_youtube;
					anadirCancionEnCola(id,nombre,artista,album,duracion,idYoutube);
				});	
			}
			
			function ponerCancionDeListaEnCola(idCancion){
				var ids=idCancion.split("_");
				var miid=ids[1];
				$.getJSON('getCancionLista.do?idCancion='+miid, function(data)  {
					var id=data.idCancion;
					var nombre=data.nombre;
					var artista=data.artista;
					var album=data.album;
					var duracion=data.duracion;
					var idYoutube=data.id_youtube;
					anadirCancionEnCola(id,nombre,artista,album,duracion,idYoutube);
				});	
			}
			
			