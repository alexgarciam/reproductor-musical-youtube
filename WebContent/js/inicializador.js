
/**
 * codigo de inicializacion de la aplicacion *
 */
// variable que controla la reproduccion del video para el slider del
// reproductor
    	var loaded1=false;
    	var loaded2=false;

    	var executed=false;
		// variable para controlar el numero de veces que se falla al buscar un
		// mismo video y reproducirlo
		// así podemos buscar otro video similar (el siguiente en la lista de
		// videos en youtube)
		var errorsOnPlaySong=0;
    	
    	// variable para json que controla los elementos de la lista de
		// reproduccion
    	var listaRep;

    	var modo="videoClip";
    	
    	var api_key="993319ba71219f25c6ce1afb58e1e27a";
    	
	    $(document).ready(function(){
	    	
	    	//eventos de botones
	    	if (window.document.addEventListener) {
	 		   window.document.addEventListener("keydown", callkeydownhandler, false);		  
	 		} else {
	 		   window.document.attachEvent("onkeydown", callkeydownhandler);
	 		}	
	    		    	
	    	// ponemos el modo musica
	    	modo="musica";
	    	establecerModoReproduccion();
	    	showMusica();
	    	
	    	
	    	// ocultamos la información de reproducción
	    	$('#repInfo').hide();
	    	$('#imageAlbum').hide();
	    	
	    	
	    	// alert("resolucion: "+screen.width + " " + screen.height);
	    	
    		resize();
    		
    		// listas del usuario
    		getListasUsuario();
	    	
			// la lista de reproduccion(testing)
			// ---------------------------------------------------------------
    		    		
    		
			listaRep = {}; // variable json que contiene la lista de
							// reproduccion actual
			// -----------------------------------------------------------------
			
			$('#fullScreen').click(function(){
				fullScreen();			
			});
						
			// publicidad!!
			$('#publicidad').hide();
			$('#publicidad').load('spots/spots1.jsp');
			
			
			$('#Home').click(function(){
				$('#searchGif').show();
				$('#searchresults')
						.load(
								'lovedTracks.do?tamPantalla=' + tamanyoPantalla[0],
								function() {
									// $('#tabs').tabs('select',
									// 0);
									$('#searchGif').hide();
								});

				showMusica();			
			});
			
						
			$('#publiDiv').click(function(){				
				$('#publiDiv').animate({"left": "+=1440px"}, "slow");
				$('#publicidad').show();				
			});
			
			$('#spots').click(function(){
				$('#publicidad').toggle();
			});		
			
			$('#mutebtn').click(function(){
				mimute();
			});	
			
			$('#myFavoritos').click(function(){
				getFavositosUsuario();
			});				
			
			$('#cerrarSession').click(function (){
				alert('cerrar sesion');
				$('#result').load('cerrarSession.do', function() {
					// console.debug('sesion cerrada');
					alert('sesion cerrada');
	   			}); 
			});			
					    		
    		    	
	    	// slider
	    	$(function() {
	    		$( "#slider" ).slider({
	    			range: "min",
	    			value: 1,
	    			min: 1,
	    			max: 100 			 			
	    		});
			});

	    	// slider
	    	$(function() {	    		
	    		$( "#slider2" ).slider({
	    			range: "min",
	    			value: 1,
	    			min: 1,
	    			max: 100,
	    			slide: function( event, ui ) {    				
	    				var volume=ui.value;	    				  				
	    			}  			 			
	    		});
			});
	    	
	    	// initiateSliderVolume();
	    	// Tabs
			
			$('#searchGif').hide();
			$('#noestevideo').hide();
						
			/*
			 * Funcion para buscar
			 */
			$('#buscar_btn').click(function(){
				sendSearch();
			});
			
			/**
			 * Funcionalidad para presionar intro en el formulario de busqueda
			 * de canciones. identica a la anterior
			 */
			$('#buscar_txt').keypress(function(e) {
				
		        if(e.which == 13) {
		        	sendSearch();		        	
		        }
		    });		    
			
			// variables para poner y quitar el gif de busqueda
			// cuando las 3 busquedas hayan terminado estarán las 3 a true y
			// entonces podemos
			// ocualtar el gif
			
			$("#searchOpts").hide();
			
			var busqTask=false;
			var busqAlbum=false;
			var busqArtist=false;
			
			
			/**
			 * Función para hacer la busqueda de canciones, albums, artistas y
			 * videos ademas manda el tamaño de la pantalla al servidor para
			 * recoger el valor en la pagina y mostrar la filas que necesita
			 * 
			 * TODO cambiar en el futuro: la respuesta del servidor debe ser
			 * json y así no necesitamos enviar el tamaño de la pantalla, pq en
			 * la pagina no usaríamos codigo Java
			 */
			function sendSearch(){
				
				var tamanyoPantalla=TamVentana();
				
				if(!executed)
				{
					executed=true;
					var buscar=$('#buscar_txt').val();
					
					//alert("1: "+escape(buscar)+ "2: "+encodeURIComponent(buscar));
					
					if(buscar!=''){
						
						// elementos que muestran el estado actual de la
						// busqueda
						$("#searchOpts").show();
						$("#searchMusica").show();
						$("#searchAlbums").show();
						$("#searchArtistas").show();
						$("#searchVideos").show();
						
						
						
						buscar=replaceAll(buscar,' ','+');
						buscar=escape(buscar);	
						
						
						// mostramos el gif de busqueda
						$('#searchGif').show();
						
						busqTask=false;
						$('#searchresults').load('searchTrack.do?q='+buscar+'&tamPantalla='+tamanyoPantalla[0], function() {
							executed=false;
							busqTask=true;
							$("#searchMusica").hide();
							if(busqTask==true && busqAlbum==true && busqArtist==true ){
								$('#searchGif').hide();
								$("#searchOpts").hide();
							}
							showMusica();
			    		});	
						
						busqAlbum=false;
						$('#div_tab_albums').load('searchTrackAlbum.do?q='+buscar+'&tamPantalla='+tamanyoPantalla[0], function() {
							busqAlbum=true;	
							$("#searchAlbums").hide();
							if(busqTask==true && busqAlbum==true && busqArtist==true){
								$('#searchGif').hide();	
								$("#searchOpts").hide();
							}
			    		});
						
						busqArtist=false;		
						$('#div_tab_artists').load('searchTrackArtist.do?q='+buscar+'&tamPantalla='+tamanyoPantalla[0], function() {
							busqArtist=true;
							$("#searchArtistas").hide();
							if(busqTask==true && busqAlbum==true && busqArtist==true ){
								$('#searchGif').hide();
								$("#searchOpts").hide();
							}
			    		});					
						
						// busqueda en los videos de youtube
						youtubeSearch(buscar);
						
						if(busqTask==true && busqAlbum==true && busqArtist==true ){						
							$('#searchGif').hide();
							$("#searchOpts").hide();
						}
					}
						
					
				}
			}			

			// Dialogo para video incorrecto
			$( "#dialog:ui-dialog" ).dialog( "destroy" );
			
			$( "#dialog-message" ).dialog({
				autoOpen: false,
				modal: true,
				buttons: {
					Ok: function() {
						$('#tabs').tabs('select', 1);
						$( this ).dialog( "close" );
					}
				}
			});

			// cola de reproduccion
			$('#lnk_cola_rep').click(function(){
				$('#searchGif').show();
				$('#actualRepro').load('music/colaReproduccion.jsp', function() {
					$('#searchGif').hide();
		   			 // alert('Load was performed.');
	   			});
			});
			
			
			// ponemos como comienzo de la aplicación los temas mas escuchados
			// en last.fm
			$('#searchGif').hide();
			var tamanyoPantalla=TamVentana();
			$('#searchresults').load('lovedTracks.do?tamPantalla='+tamanyoPantalla[0], function() {
				$('#searchGif').hide();
			});
			
			
			$('#repInfo').click(function(){
				showRepo();
			});		
			
			// -------------------------------------------------------
			// modal form para nuevas listas
			$(".modalbox").fancybox();
			$("#nuevaListaUser").submit(function() { return false; });
			
			$("#send").on("click", function(){
				var formLista=$("#formLista").val();
				// var formLista=$("#formLista").text();
				// alert("formLista: "+formLista);
				// $("#formLista").text('holaaa');
				// alert("formLista: "+formLista);
				//$("#message1").replaceWith("<em>Creando la lista...</em>");				
				$.ajax({
					type: 'POST',
					url: 'addListaUsuario.do?listName='+formLista,
					success: function(data) {
						if(data==1){
							/*
							$("#nuevaListaUser").fadeOut("fast", function(){
							});
							*/							
							getListasUsuario();
							//$("#message1").replaceWith("<p><strong>La lista ha sido creada! :)</strong></p>");
							setTimeout("$.fancybox.close()", 1000);
							$('#scroll-pane').jScrollPane();								
							setTimeout("$.fancybox.close()", 2000);							
							$.fancybox.open("&nbsp;&nbsp;&nbsp;<h3>Lista Creada ;) </h3> &nbsp;&nbsp;&nbsp;" );
						}
						else{
							//$("#message1").replaceWith("<p><strong>Error! La lista no pudo ser creada :(</strong></p>");
							setTimeout("$.fancybox.close()", 3000);
							$("#send").replaceWith("<button id='send'>Guardar</button>");
							$.fancybox.open("&nbsp;&nbsp;&nbsp;<h3>La lista no pudo ser creada</h3> &nbsp;&nbsp;&nbsp;" );
							setTimeout("$.fancybox.close()", 2000);
						}	
					}
				});				
			});	
			// ---------------------------------------
			
			 $( "#contenedor" ).click(function(){
				 closeSuggestions();
			 });
			
			//favoritos con drag&drop
			 $( "#myFavoritos" ).droppable({
		            //accept: "#draggable",
		            //activeClass: "hover2",
		            hoverClass: "hover",
		            drop: function( event, ui ) {
		            	var draggable = ui.draggable;
		            	var param =  draggable.attr('id');
						var params = param.split('_');
		           		//alert( 'The square with ID "' + draggable.attr('id') + '" was dropped onto me! id: '+param );
		           		agregarAFavoritos(params[1],params[2],params[3],params[4],params[5]);
		            }
		        });
			 
			 
			
			 
			 
			 //---------------------------------------------------------
			 //DRAG'N DROP
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
		           		//alert( 'The square with ID "' + draggable.attr('id') + '" was dropped onto me!' );
		            }
		        });     
		        //-------------------------------------------------------------
	    	});
	    
	    
	    
	   
	    
	    
	    function getInfoTrack(cancion, artista ){
			 //alert("artista: "+artista+" - cancion: "+cancion);
	    	
			 //eliminar tildes de canción y artista
			 cancion=quitaAcentos(unescape(cancion));
			 artista=quitaAcentos(unescape(artista));
			 $.getJSON('http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key='+api_key+'&artist='+artista+'&track='+cancion+'&format=json', function(data) {
				 var album=data.track["album"];
				 if (typeof album == "undefined" || album.title=='') {
					 //alert("error al buscar");
				 }
				 else{
					// alert(album.title);
					 searchAlbum(artista,escape(album.title));
				 }				  
			 });
		}	    
	    
	    function changeCSSListaMouseOver(idLista){
	    	//alert('e: '+idLista); 
	    	$('#'+idLista).css("color","#CCCCCC");
	    	$('#'+idLista).css("cursor","default");
	    }

	    function changeCSSListaMouseOut(idLista){
	    	//alert('e: '+idLista); 
	    	$('#'+idLista).css("color","white");
	    }	  
	    
	    // scroll para la lista de listas del usuario
	    // -------------------------------------------
	    function getListasUsuario(){	
	    	
			$.getJSON('getListasUsuario.do', function(data) {
				//alert(data);
				if(data==''){
					$('#scroller').html("");
					$('#recuListas').hide();		
				}					
				else{	
					var html="";
					html+="<table id='tableListasUsuario' width='200px' border='0' bordercolor='#FFFFFF'>";
					$.each(data, function(key, val) {					 
						html=html+"<tr class='menuListasUsuario' id='lista_"+ val.idLista+"'><td  width='100%' class='droppable' id='lista_"+ val.idLista+"' onmouseover='changeCSSListaMouseOver(\"lista_"+val.idLista+"\")';  onmouseout='changeCSSListaMouseOut(\"lista_"+val.idLista+"\")'; onclick='getCancionesListaUsuario(\""+val.idLista+"\",\""+val.nombreLista+"\");'  ><img src='images/botones/music_beam.png' /> "+val.nombreLista+"</td></tr>";
					});					
					html=html+"</table>";
										
					$('#scroller').html(html);
					$('#scrollbar1').tinyscrollbar();
					$('.droppable').droppable( {
						hoverClass: "hover",
				        drop: function( event, ui ) {
			            	// aquí va la funcionalidad para agregar las
							// canciones a las listas
			            	var draggable = ui.draggable;				            	
			            	var id=draggable.attr('id');
			            	var idLista=$(this).attr("id");	
			            	var ids=idLista.split("_");
			            	var elid=ids[1];
		         		 	var params=id.split("_");
		         		 	var cancion=params[1];
		         		 	var artista=params[2];
		         		 	var duration=params[4];
		         		 	
		         		 	cancion=encodeURIComponent(replaceAll(cancion,"+"," "));
		         		 	artista=encodeURIComponent(replaceAll(artista,"+"," "));
			            	//alert('cancion: '+cancion);
		         		 	agregarALista(elid, cancion,artista,'',duration,'');
			           		// alert( 'The square with ID "' +
							// draggable.attr('id') + '" was dropped onto me:
							// '+$(this).attr("id") );
				        }
					});
					
					
					/****************************************************************
					 * 					Menu para listas del Usuario				*
					 ****************************************************************/
					$.contextMenu( {
						selector :'.menuListasUsuario',
						callback : function(key, opt) {
							// alert("Clicked on " + key + " on element " +
							// opt.$trigger.attr('id'));							
							var param = opt.$trigger.attr('id');		
							var idp=param.split("_");
							var id=idp[1];
							if (key == 'eliminar') {
								//-------------------								
								$.ajax({
									type: 'POST',
									url: 'RemoveListaUsuario.do?idLista='+id,
									success: function(data) {
										if(data==1){			
											
											$.fancybox.open("&nbsp;&nbsp;&nbsp;<h3>Lista borrada ;) </h3> &nbsp;&nbsp;&nbsp;" );
											setTimeout("$.fancybox.close()", 2000);
											
											getListasUsuario();												
											$('#scroll-pane').jScrollPane();												
										}
										else{
											$.fancybox.open("&nbsp;&nbsp;&nbsp;<h3>Error! La lista no se pudo  borrar  :( </h3><br/> intentaló mas tarde &nbsp;&nbsp;&nbsp;" );
											setTimeout("$.fancybox.close()", 2000);
										}	
									}
								});									
								//-------------------	
						}
					},
					items : {
						eliminar : {
							name :"eliminar",
							icon :"edit"
						},
						sep1 :"---------"
					}
					});

					// ------------------------
					var videoId = "";
					videoId = gup('v');
					if (videoId != "") {
						// alert('video id: '+videoId);
						$('#searchGif').show();

						// anadirCancionEnCola(videoId,videoId,gup( 'nombre' ),gup(
						// 'artista' ),gup( 'album' ),100);
						setTimeout("reproducirVideo()", 3000);
					}					
					$('#recuListas').hide();
				}				
			});			
		}	    	    