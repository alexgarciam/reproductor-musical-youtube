/**
 * codigo de interfaz grafica de la aplicacion *
 */

// radio buttons

$( function() {
	$("#radio").buttonset();
	$('input:radio[name=radio]')[0].checked = false;
	$('input:radio[name=radio]')[1].checked = true;
});

// funciones ocultar dev de copia de enlaces de canciones
function OcultarDiv(name) {
	$("#dialog-modal").dialog('close');
}

$(document)
		.ready( function() {
		
			$("#radio").buttonset();
			$('input:radio[name=radio]')[0].checked = false;
			$('input:radio[name=radio]')[1].checked = true;
			
			// botones de radio para cambiar entre modo musica o videoclips
				$("#radio1").click( function() {
					// alert('1');
						// si hay que cambiar de modo se lanza la accion (alert)
						if (modo == "musica") {
							modo = "videoClip";
							establecerModoReproduccion();
						}
					});

				$("#radio2").click( function() {
					// alert('2');
						// si hay que cambiar de modo se lanza la accion (alert)
						if (modo == "videoClip") {
							modo = "musica";
							establecerModoReproduccion();
						}
					});

				// funciones para copiar texto en el portapapeles
				$("#dialog:ui-dialog").dialog("destroy");
				$("#dialog-modal").dialog( {
					// resizable:false,
					height :140,
					modal :true
				});
				$("#dialog-modal").dialog('close');

				/**
				 * Pone los controles del reproductor para que cambien cuando se
				 * les pase por encima
				 */
				$("#controles td.ui-state-default").hover( function() {
					$(this).addClass("ui-state-hover");
				}, function() {
					$(this).removeClass("ui-state-hover");
				});
				
				var tamanyoPantalla = TamVentana();
				// en estas busquedas se envía el tamaño de la pantalla para
				// despues en la pagina poder
				// usarlas con codigo java,
				// Arreglar en un futuro cambiando la respuesta del servidor por
				// JSON
				// para poder tratar los elementos sin necesidad de codigo Java
				// solo con Javascript
				$('#artistasEscuchados')
						.click(
								function() {
									$('#searchGif').show();
									$('#searchresults')
											.load(
													'topArtists.do?tamPantalla=' + tamanyoPantalla[0],
													function() {
														// $('#tabs').tabs('select',
														// 0);
														$('#searchGif').hide();
													});
									showMusica();
								});

				$('#temasEscuchados')
						.click(
								function() {
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

				$('#artistasAlza')
						.click(
								function() {
									$('#searchGif').show();
									$('#searchresults')
											.load(
													'hypedArtists.do?tamPantalla=' + tamanyoPantalla[0],
													function() {
														// $('#tabs').tabs('select',
														// 0);
														$('#searchGif').hide();
													});
									showMusica();
								});

				$('#temasAlza')
						.click(
								function() {
									$('#searchGif').show();
									$('#searchresults')
											.load(
													'hypedTracks.do?tamPantalla=' + tamanyoPantalla[0],
													function() {
														// $('#tabs').tabs('select',
														// 0);
														$('#searchGif').hide();
													});
									showMusica();
								});

				/***************************************************************
				 * Menu para canciones de busqueda
				 **************************************************************/
				$.contextMenu( {
							selector :'.menuc',
							callback : function(key, opt) {
								// alert("Clicked on " + key + " on element " +
								// opt.$trigger.attr('id'));
								var param = opt.$trigger.attr('id');
								var params = param.split('_');
								if (key == 'reproducir') {
									
									reproducirCancionEnCola(params[0],
											params[1], params[2], params[3],
											params[4]);
								}
								if (key == 'anacol') {
									anadirCancionEnCola(params[0], params[1],
											params[2], params[3], params[4]);
								}
								if (key == 'desc') {
									alert('funcionalidad no implementada aun. Espere a la proxima versión :-)');
								}
								if (key == 'copiar') {
									/*
									 * var clip2 = new ZeroClipboard.Client();
									 * clip2.setText(
									 * 'http://localhost:8080/Futvre2/indice.jsp?'+params[1] );
									 * clip2.glue( 'd_clip_button' );
									 * $('#d_clip_button').click();
									 */
									var texto = "nuevo texto a copiar";
									// var html="<embed id='flashCopy'
									// src='tests/clipboard/test2.swf'
									// flashvars='texto="+params[0]+"'
									// width='130px' heigth='75px'
									// type='application/x-shockwave-flash'></embed>";

									// llamada al servicio de generacion de url
									$
											.get(
													'getUrlTrack.do?cancion='
															+ params[1]
															+ '&album='
															+ params[3]
															+ '&artista='
															+ params[2],
													function(data) {

														var html2 = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0" width="80" height="20">';
														html2 = html2 + '<param name=movie value="tests/clipboard/test2.swf">';
														html2 = html2 + '<param name=quality value=high>';
														html2 = html2
																+ '<embed src="tests/clipboard/test2.swf" flashvars="texto='
																+ data
																+ '"  quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="80" height="20">';
														html2 = html2 + '</embed>';
														html2 = html2 + '</object>';

														$('#mydiv').html(html2);
														$('#mydiv').show();

														$("#dialog-modal")
																.dialog(
																		{
																			height :200,
																			width :300,
																			modal :true
																		});

													});
								}
							},
							items : {
								reproducir : {
									name :"reproducir",
									icon :"edit"
								},
								sep1 :"---------",
								anacol : {
									name :"añadir a cola",
									icon :"cut"
								},
								anaa : {
									name :"añadir a ",
									icon :"copy",
									disabled :true
								},
								desc : {
									name :"descargar",
									icon :"paste"
								},
								"copiar" : {
									name :"copiar http",
									icon :"delete"
								},
								//sep1 :"---------"
							}
						});

				/***************************************************************
				 * Menu para canciones de cola de reproducción
				 **************************************************************/
				$
						.contextMenu( {
							selector :'.menur',
							callback : function(key, opt) {
								// alert("Clicked on " + key + " on element " +
								// opt.$trigger.attr('id'));
								var param = opt.$trigger.attr('id');
								var params = param.split('_');
								if (key == 'reproducir') {
									elegirCancionDeLaLista(params[1], params[3]);
								}
								if (key == 'quitar') {
									quitarCancionDeCola(params[0], params[1],
											params[2], params[3], params[4]);
								}
								if (key == 'desc') {
									alert('funcionalidad aun no disponible');
								}
								if (key == 'copiar') {
									alert('copiando url');
									/*
									 * var clip2 = new ZeroClipboard.Client();
									 * clip2.setText(
									 * 'http://localhost:8080/Futvre2/indice.jsp?'+params[1] );
									 * clip2.glue( 'd_clip_button' );
									 * $('#d_clip_button').click();
									 */
									var texto = "nuevo texto a copiar";
									// var html="<embed id='flashCopy'
									// src='tests/clipboard/test2.swf'
									// flashvars='texto="+params[0]+"'
									// width='130px' heigth='75px'
									// type='application/x-shockwave-flash'></embed>";

									// llamada al servicio de generacion de url
									$
											.get(
													'getUrlTrack.do?cancion='
															+ params[1]
															+ '&album='
															+ params[3]
															+ '&artista='
															+ params[2],
													function(data) {

														var html2 = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0" width="80" height="20">';
														html2 = html2 + '<param name=movie value="tests/clipboard/test2.swf">';
														html2 = html2 + '<param name=quality value=high>';
														html2 = html2
																+ '<embed src="tests/clipboard/test2.swf" flashvars="texto='
																+ data
																+ '"  quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="80" height="20">';
														html2 = html2 + '</embed>';
														html2 = html2 + '</object>';

														$('#mydiv').html(html2);
														$('#mydiv').show();

														$("#dialog-modal")
																.dialog(
																		{
																			height :200,
																			width :300,
																			modal :true
																		});

													});
								}
							},
							items : {
								reproducir : {
									name :"reproducir",
									icon :"edit"
								},
								sep1 :"---------",
								quitar : {
									name :"quitar de cola",
									icon :"cut"
								},
								anaa : {
									name :"añadir a ",
									icon :"copy",
									disabled :true
								},
								desc : {
									name :"descargar",
									icon :"paste"
								},
								"copiar" : {
									name :"copiar http",
									icon :"delete"
								},
								sep1 :"---------"
							}
						});

				/***************************************************************
				 * Menu para canciones de lovedTracks
				 **************************************************************/
				$
						.contextMenu( {
							selector :'.menuc1',
							callback : function(key, opt) {
								// alert("Clicked on " + key + " on element " +
								// opt.$trigger.attr('id'));
							var param = opt.$trigger.attr('id');
							var params = param.split('_');
							if (key == 'reproducir') {
								// reproducirCancionEnCola(params[0],params[1],params[2],params[3],params[4]);
							getTrackInfo(params[1], params[2]);
						}
						if (key == 'anacol') {
							// anadirCancionEnCola(params[0],params[1],params[2],params[3],params[4]);
							getTrackInfo2(params[1], params[2]);
						}
						if (key == 'desc') {
							alert('funcionalidad no implementada aun. Espere a la proxima versión :-)');
						}
						if (key == 'favorito') {
							//alert('añadir a favoritos: '+params[1]+ " "+ params[2]);
							agregarAFavoritos(params[1],params[2],'','','');
						}
						if (key == 'copiar') {

							/*
							 * var clip2 = new ZeroClipboard.Client();
							 * clip2.setText(
							 * 'http://localhost:8080/Futvre2/indice.jsp?'+params[1] );
							 * clip2.glue( 'd_clip_button' );
							 * $('#d_clip_button').click();
							 */
							var texto = "nuevo texto a copiar";
							// var html="<embed id='flashCopy'
							// src='tests/clipboard/test2.swf'
							// flashvars='texto="+params[0]+"' width='130px'
							// heigth='75px'
							// type='application/x-shockwave-flash'></embed>";

							// llamada al servicio de generacion de url
							$
									.get(
											'getUrlTrack.do?cancion='
													+ params[1] + '&album='
													+ params[3] + '&artista='
													+ params[2],
											function(data) {

												var html2 = '<div  style="padding-top:-60px"><object align="middle" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0" width="80" height="1">';
												html2 = html2 + '<param name=movie value="tests/clipboard/test2.swf">';
												html2 = html2 + '<param name=quality value=high>';
												html2 = html2
														+ '<embed src="tests/clipboard/test2.swf" flashvars="texto='
														+ data
														+ '"  quality="high" pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="80" height="50">';
												html2 = html2 + '</embed>';
												html2 = html2 + '</object></div>';

												$('#mydiv').html(html2);
												$('#mydiv').show();

												$("#dialog-modal").dialog( {
													height :200,
													width :300,
													modal :true
												});

											});
						}
					},
					items : {
						reproducir : {
							name :"reproducir",
							icon :"edit"
						},
						sep1 :"---------",
						anacol : {
							name :"añadir a cola",
							icon :"copy"
						},/*
						favorito : {
							name :"añadir a favoritos",
							icon :"copy"
						},*/
						desc : {
							name :"descargar",
							icon :"paste"
						},
						"copiar" : {
							name :"copiar http",
							icon :"delete"
						},
						sep1 :"---------"
					}
						});

				/*
				 * $(this).bind("contextmenu", function(e) { e.preventDefault();
				 * alert('eeeee!!'); });
				 */

				/***************************************************************
				 * Menu para videos de youtube
				 **************************************************************/
				$
						.contextMenu( {
							selector :'.menuYoutube',
							callback : function(key, opt) {
								// alert("Clicked on " + key + " on element " +
								// opt.$trigger.attr('id'));
								var param = opt.$trigger.attr('id');
								var params = param.split('_');
								if (key == 'reproducir') {
									reproducirCancionEnCola(params[0],
											params[1], params[2], params[3],
											params[4]);
								}
								if (key == 'anacol') {
									anadirCancionEnCola(params[0], params[1],
											params[2], params[3], params[4]);
									// getTrackInfo2(params[1],params[2]);
								}
								if (key == 'desc') {
									alert('funcionalidad no implementada aun. Espere a la proxima versión :-)');
								}
								if (key == 'copiar') {

									var clip2 = new ZeroClipboard.Client();
									clip2
											.setText('http://localhost:8080/Futvre2/indice.jsp?' + params[1]);
									clip2.glue('d_clip_button');
									$('#d_clip_button').click();
								}
							},
							items : {
								reproducir : {
									name :"reproducir",
									icon :"edit"
								},
								sep1 :"---------",
								anacol : {
									name :"añadir a cola",
									icon :"copy"
								},
								desc : {
									name :"descargar",
									icon :"paste"
								},
								"copiar" : {
									name :"copiar http",
									icon :"delete"
								},
								sep1 :"---------"
							}
						});

				/*
				 * $(this).bind("contextmenu", function(e) { e.preventDefault();
				 * alert('eeeee!!'); });
				 */

				/***************************************************************
				 * Menu para discos 1 (para los que sale la caratula y las canciones)
				 **************************************************************/
				$
						.contextMenu( {
							selector :'.menuAlbums',
							callback : function(key, opt) {
								// alert("Clicked on " + key + " on element " +
								// opt.$trigger.attr('id'));
								var param = opt.$trigger.attr('id');
								var params = param.split('_');
								if (key == 'reproducir') {
									reproducirAlbumEnCola('', '', '',
											params[1], params[0]);
								}
								if (key == 'anacol') {
									anadirAlbumEnCola(params[0], params[1]);
								}
								if (key == 'desc') {
									alert('funcionalidad no implementada aun. Espere a la proxima versión :-)');
								}
								if (key == 'copiar') {

									var clip2 = new ZeroClipboard.Client();
									clip2
											.setText('http://localhost:8080/Futvre2/indice.jsp?' + params[1]);
									clip2.glue('d_clip_button');
									$('#d_clip_button').click();
								}

							},
							items : {
								reproducir : {
									name :"reproducir album ",
									icon :"edit"
								},
								sep1 :"---------",
								anacol : {
									name :"añadir a ",
									icon :"copy"
								},
								desc : {
									name :"descargar album",
									icon :"paste",
									disabled :true
								},
								"copiar" : {
									name :"copiar http",
									icon :"delete"
								},
								sep1 :"---------"
							}
						});
				
				
				 /**************************************************************
				 * Menu para discos 2 (para los que solo salen las caratulas)  *
				 **************************************************************/
				$
						.contextMenu( {
							selector :'.menuAlbums2',
							callback : function(key, opt) {
								// alert("Clicked on " + key + " on element " +
								// opt.$trigger.attr('id'));
								var param = opt.$trigger.attr('id');
								var params = param.split('_');
								if (key == 'reproducir') {
									reproducirAlbumEnCola('', '', '',params[1], params[0]);
									showRepo();
								}if (key == 'ver') {
									searchAlbum( params[1], params[0] );
									//showMusica();
								}

							},
							items : {
								reproducir : {
									name :"reproducir album  ",
									icon :"edit"
								},
								sep1 :"---------",
								ver : {
									name :"ver  ",
									icon :"copy"
								},
								sep1 :"---------"
							}
						});

				/***************************************************************
				 * Menu para artistas
				 **************************************************************/
				$.contextMenu( {
					selector :'.menuArtistas',
					callback : function(key, opt) {
						// alert("Clicked on " + key + " on element " +
						// opt.$trigger.attr('id'));
					var param = opt.$trigger.attr('id');
					if (key == 'ver') {
						searchArtist2(param);
					}

				},
				items : {
					ver : {
						name :"ver",
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
				
				
				
				
				/***************************************************************
				 * Menu para canciones de listas de usuarios
				 **************************************************************/
				$.contextMenu( {
					selector :'.menulu',
					callback : function(key, opt) {
						// alert("Clicked on " + key + " on element " +
						// opt.$trigger.attr('id'));
					var param = opt.$trigger.attr('id');
					if (key == 'eliminar') {
						//alert('param: '+param);
						var idp=param.split("_");
						var idCancion=idp[1];
						var idListas=idp[3];
						//-------------------								
						$.ajax({
							type: 'POST',
							url: 'RemoveCancionFromListaUsuario.do?idLista='+idListas+'&idCancion='+idCancion,
							data: $("#contacto").serialize(),
							success: function(data) {								
								if(data){		
									//alert(idListas);
									getCancionesListaUsuario(idListas);											
								}
								else{
									alert('error');
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
				
				
				
				
				/***************************************************************
				 * Menu para favoritos de usuarios
				 **************************************************************/
				$.contextMenu( {
					selector :'.menufav',
					callback : function(key, opt) {
						// alert("Clicked on " + key + " on element " +
						// opt.$trigger.attr('id'));
					var param = opt.$trigger.attr('id');
					if (key == 'eliminar') {
						//alert('param: '+param);
						var idp=param.split("_");
						var idCancion=idp[1];
						//-------------------								
						$.ajax({
							type: 'POST',
							url: 'RemoveCancionFromFavoritos.do?idCancion='+idCancion,
							data: $("#contacto").serialize(),
							success: function(data) {	
								
								if(data==1){		
									//alert(idListas);
									getFavositosUsuario();	
									$.fancybox.open("&nbsp;&nbsp;&nbsp;<h3>Canción eliminada de favoritos ;) </h3> &nbsp;&nbsp;&nbsp;" );
									setTimeout("$.fancybox.close()", 2000);
								}
								else{
									
									$.fancybox.open("&nbsp;&nbsp;&nbsp;<h3>No sse puedo quitar la canción de favoritos</h3> &nbsp;&nbsp;&nbsp;" );
									setTimeout("$.fancybox.close()", 2000);
								}	
								
							}
						});									
						//-------------------
						
						
						
					}

				},
				items : {
					eliminar : {
						name :"quitar de favoritos",
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
				

			});





			



function gup(name) {
	var regexS = "[\\?&]" + name + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var tmpURL = window.location.href;
	var results = regex.exec(tmpURL);
	if (results == null)
		return "";
	else
		return results[1];
}
