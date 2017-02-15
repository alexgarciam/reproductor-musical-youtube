<html>
<head>
	<script>	
	var listaJSON="";
	$(function() {
		listaJSON=<% out.println(request.getAttribute("listaRepUser"));%>;
		
		var artistas=listaJSON.imgArtistas;
		var htmlArtistas="";
		//alert("artistas: "+artistas.length);
		if(artistas.length>=1 && artistas.length<4){
			htmlArtistas="<table><tr><td><img src='"+artistas[0]+"' width='120px' /></td></tr></table>";
		}
		else if(artistas.length>=4){
			htmlArtistas="<table><tr><td><img src='"+artistas[0]+"'  width='60px' /></td><td><img src='"+artistas[1]+"'  width='60px' /></td></tr><tr><td><img src='"+artistas[2]+"'  width='60px' /></td><td><img src='"+artistas[3]+"'  width='60px' /></td></tr></table>";
		}
		
		//$('#infoLista').html("<h2>"+listaJSON.nombreLista+"</h2>"+listaJSON.numeroCanciones+" canciones, "+duracionEnhorasMinutosYsegundos(listaJSON.duracion));
		if(listaJSON.numeroCanciones>0)
			$('#infoLista').html("<table><tr><td>"+htmlArtistas+"</td><td><table><tr><td><h2>"+listaJSON.nombreLista+"</h2></td></tr><tr><td>"+listaJSON.numeroCanciones+" canciones, "+duracionEnhorasMinutosYsegundos(listaJSON.duracion)+"</td></tr></table></td></tr></table>");
		else
			$('#infoLista').html("<h3 style='padding: 25px'>Aun no has agredado ninguna canci&oacuten</h3><div style='padding:25px'>Arrastra la cancion que te guste y sueltala encima de la lista para agregarla</div>");
		//alert(duracionEnhorasMinutosYsegundos(300000));
		var cont=0;
		var lista=listaJSON.canciones;
		while( cont < lista.length )
		{		
			var album=lista[cont].album;
			album=replaceAll(album,"+"," ");
			album=unescape(album);
			var fila="<tr class='menulu' height='24px' id='cancion_"+lista[cont].idCancion+"_lista_"+lista[cont].lista+"'>"+
			"<td width='80px'></td><td >"+
			lista[cont].nombre+"</td><td class='gradeA'>"+lista[cont].artista+"</td><td class='gradeA'>"+
			album+"</td><td class='gradeA' align='right'><a href='#' class='dur'  style='text-decoration: none;color:#222;	font-size: 10ptcursor: default' >"+lista[cont].duracion+"</a></td><td width='80px'></td></tr>";
			$('#tableListaUsuario').append(fila);
			cont++;
		}	
		
		
		var row = 0;
    	$('table.tableListaUsuario tbody tr').each(function() {
    		row++;									 
    		if ( row % 2 == 0 ) {
    			/* even row: add class="even" */
    			$(this).addClass('even');
    		} 
    		else{
    			$(this).addClass('odd');
    		}
    	});	
    	
		/*
		$('#tableListaUsuario').dataTable({					
		 	//"sScrollY": 200,			
		 	"iDisplayLength": 20,       
	        "sPaginationType": "full_numbers",
	        "oLanguage": {
	            "sLengthMenu": "Mostrando _MENU_ Canciones por p&aacutegina",
	            "sZeroRecords": "Lo sentimos, no hemos encontrado nada",
	            "sInfo": "Mostrando _START_ a _END_ de _TOTAL_ canciones",
	            "sInfoEmpty": "Mostrando 0 a 0 de 0 Canciones",
	            "sInfoFiltered": "(Filtrados de _MAX_ canciones totales)"
	        }
		});
		*/
		
		$('#tableListaUsuario a.dur').each(function(){			
			//console.debug($(this).text());
			var seg=$(this).text();
			var seg2=segundos_a_minutos(seg);
			$(this).text(seg2);
		});
		
		$('#tableListaUsuario tr').hover( function () {			
			var id_padre=$(this).attr("id");
			//alert('id: '+id_padre);
			id_padre=escape(id_padre);
			//esto coge el primer td del tr 
			var ee=$(this).find('td:eq(0)');
			
			//$(ee).html('<table><tr><td  align="left"><img src="css/tables/images/play_track_arrow.gif" onclick="test(\''+id_padre+'\');"></td><td align="left"><img src="css/tables/images/addBtn.gif" onclick="ponerEnColaCancionBusq(\''+id_padre+'\');"></td></tr></table>');
			$(ee).html('<table><tr><td  align="left"><span style="margin-left:10px"><img src="images/botones/play_32.png" height="16px" onclick="test(\''+id_padre+'\');"></span></td><td align="left"><span style="margin-left:10px"><img src="images/botones/plus_32.png" height="16px" onclick="ponerCancionDeListaEnCola(\''+id_padre+'\');"></span></td></tr></table>');
			//alert('test');
		},function (){
			var id_padre=$(this).attr("id");
			//alert('id: '+id_padre);
			var ee=$(this).find('td:eq(0)');			
			$(ee).html('');
		});	
		
	});
	
	

	function test(idCancion){
		//alert('idCancion: '+idCancion + " - idLista: "+listaJSON[0].lista);
		var json=<% out.println(request.getAttribute("listaRepUser"));%>;
		reproducirListaUsuario(idCancion,json.canciones[0].lista);
	}

	</script>

</head>
<body>

	<div id="header">
		<table>
			<tr >
				<!-- imagenes de los artistas -->
				<td>
				<div id="imgArts">
				
				</div>
				</td>
				<!-- imagenes de los artistas -->
				
				<!-- titulo e información de la lista -->
				<td>
					<div id="infoLista">
					</div>
				</td>
				<!-- titulo e información de la lista -->
			</tr>
		</table>
	</div>
	
	<div>		
		<div id="container" class="ex_highlight_row" style="width:100%;">
			<table id="tableListaUsuario" class="tableListaUsuario"  width="100%" cellspacing="0"  cellpadding="0" style="font-size: 10pt">
				
			</table>
		</div>
	</div>
</body>
</html>

