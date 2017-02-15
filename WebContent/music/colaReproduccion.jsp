<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>			
<fmt:setBundle basename="MessageResource"/>				




	
	
	<script>
	$(document).ready(function(){ 

			var lista=listaRep;			
			var temas = lista.temas;							
			var html;	    			
	    	for (var x = 0 ; x < temas.length ; x++) {
	    		var tema=unescape(temas[x].nombre);
	    		//alert("1: "+temas[x].artista + " - 2: "+decodeURIComponent(temas[x].artista));
				tema=replaceAll(tema,'+',' '); 	    		
	    		var art=decodeURIComponent(temas[x].artista);
	    		art=replaceAll(art,'+',' '); 
	    		var albm=temas[x].album;	    		
	    		var art_unscaped=unescape(temas[x].artista);
	    		art_unscaped=replaceAll(art_unscaped,'+',' '); 
	    		var albm_unscaped=unescape(temas[x].album);
	    		albm_unscaped=replaceAll(albm_unscaped,'+',' '); 
	    		var art_escaped=escape(temas[x].artista);
	    		var albm_escaped=escape(temas[x].album);
		    	
		    	//alert('nombre: '+temas[x].nombre + ' idYoutube: '+temas[x].idYoutube);				
		    	if(temas[x].reproduciendo=='true')
				{
			    	html += '<tr class="menur" id="' + unescape(temas[x].nombre) + '_'+ unescape(temas[x].nombre) + '_' + art_unscaped + '_' + albm_unscaped+ '_' + unescape(temas[x].duracion) +'_' + unescape(temas[x].idYoutube) + '">';
			   		//alert('reproduciendo: '+temas[x].nombre);
					html += '<td align="left" width="20px"><div ><img src="images/playing.png" heigth="16px" width="16px"/></div></td>';
					html += '<td align="left"><a style="font-weight:bold" href="#">' + tema + '</a></td>';
			    	html += '<td align="left"><a style="font-weight:bold" href="#" onclick=searchArtist2(\''+escape(art)+'\')>' + art + '</a></td>';
			    	html += '<td align="left" style="font-weight:bold"><a  href="#" onclick=searchAlbum(\''+temas[x].artista+'\',\''+temas[x].album+'\')>' + albm_unscaped + '</a></td>';
			    	html += '<td align="left" style="font-weight:bold"><a href="#" class="dur">' + temas[x].duracion + '</a></td></tr>';
			  	}
		    	else{
		    		html += '<tr  id="' + unescape(temas[x].nombre) + '_'+ unescape(temas[x].nombre) + '_' + art_unscaped + '_' + albm_unscaped+ '_' + unescape(temas[x].duracion) + '" class="menur" ondblclick="elegirCancionDeLaLista(\''+escape(temas[x].nombre)+'\',\''+escape(temas[x].album)+'\');">';
			    	html += '<td align="left"></td>';
			    	html += '<td align="left"><a href="#" style="text-decoration: none;cursor: default">' + tema + '</a></td>';
			      	html += '<td align="left"><a  href="#" onclick=searchArtist2(\''+art_escaped+'\')>' + art + '</a></td>';
			    	html += '<td align="left" ><a  href="#" onclick=searchAlbum(\''+art_escaped+'\',\''+albm_escaped+'\')>' + albm_unscaped + '</a></td>';
			    	html += '<td align="right" ><a href="#"  style="text-decoration: none;cursor: default" class="dur">' + temas[x].duracion + '</a></td></tr>';
			   	}	
		    	$('#colarepro').html(html);

		    	var row = 0;
		    	$('table.striped tbody tr').each(function() {
		    		row++;									 
		    		if ( row % 2 == 0 ) {
		    			/* even row: add class="even" */
		    			$(this).addClass('even');
		    		} 
		    		else{
		    			$(this).addClass('odd');
		    		}
		    	});			    	
		    	
		    	$('.menur').draggable({
		         	revert: true , 
		         	cursor: "move",
		         	opacity: 0.99,
		         	 zIndex: 9999999999999,
		         	 helper: function(event, ui) {
		         		 	var id=$(this).attr('id'); 
		         		 	
		         		 	var params=id.split("_");
		         		 	var cancion=params[1];
		         		 	var artista=params[2];
		         		 	
		         		 	cancion=decodeURIComponent(replaceAll(cancion,"+"," "));
		         		 	artista=decodeURIComponent(replaceAll(artista,"+"," "));
		         		 	
		         	        var foo = $('<span style="white-space:nowrap;border:1px solid #000;background-color: #FFFAF0; color:black; padding-left:5px;padding-right:5px;font-size: 10pt "></span>').text("  "+cancion + " de " + artista+ "   ");
		         	        
		         	        return foo;
		         	    }
		         	});
	    	}
		
		$('#colaRepro_table a.dur').each(function(){			
			//console.debug($(this).text());
			var seg=$(this).text();
			var seg2=segundos_a_minutos(seg);
			$(this).text(seg2);
		});
			
		
	});
	
	</script>
	
			
	<table cellspacing="0" cellpadding="0" width="100%" id="colaRepro_table" class="striped">
					<thead>
						<tr>
							<th></th>
							<th align="left"><fmt:message key="application.music.nombre" /></th>
							<th align="left"><fmt:message key="application.music.artista" /></th>
							<th align="left"><fmt:message key="application.music.album" /></th>
							<th align="left"><fmt:message key="application.music.duracion" /></th>
						</tr>
					</thead>
					
					<tbody id="colarepro">									
							<tr >
								<td align="left"><a href="#"></a></td>
								<td align="left"><a href="#"></a></td>
								<td align="left"><a href="#"></a></td>
								<td align="left"><a href="#"></a></td> 											
							</tr>
					</tbody>						
				
		</table>