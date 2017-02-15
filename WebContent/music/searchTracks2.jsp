<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>			
		
		
	
			
<fmt:setBundle basename="MessageResource"/>					

						
<script>
	$(document).ready(function(){ 
		
		//metemos los artistas en los resultados de busqueda
		//--------------
		
		var myartistas=<% out.println(request.getAttribute("artistasJSON"));%>		
		//ancho de los artistas disponible (ancho1=433)
		//ancho de las imagenes de artistas: 80px
		//ancho de nombre del artista: 116px
		//total 196px		
		//caben 2: 196*2=432 < 392		
		var html1="<table>";
		for(var j=0;j<myartistas.length;j++){
			var art=escape(myartistas[j].name);
			
			var test="<a class='test22' href='#' onclick='alert();' >test</a>";
			//alert(test);
			
			html1+="<tr>";
			html1+="<td><img src='"+myartistas[j].imageURL+"' width='80px'  /></td><td><a href='#' class='linkNoDecore' onclick=searchArtist2('"+escape(myartistas[j].name)+"');>"+myartistas[j].name+"</a></td>";
			html1+="</tr>";
		}
		html1+="</table>";			
		$("#mySearchArtista").html("<h2>Artistas</h2>"+html1);
		$(".test22").click(function (){
			//alert();
		});
		//----		
		var myalbums=<% out.println(request.getAttribute("albumsJSON"));%>			
		//ancho de los artistas disponible (ancho2=433)
		//ancho de las imagenes de artistas: 80px
		//ancho de nombre del artista: 116px
		//total 196px		
		//caben 2: 196*2=432 < 392		
		var html1="<table>";
		for(var j=0;j<myalbums.length;j++){
			html1+="<tr>";
			html1+="<td><img src='"+myalbums[j].urlImage+"' width='80px'  /></td><td><a href='#' class='linkNoDecore' onclick=searchAlbum('"+escape(myalbums[j].artist)+"','"+escape(myalbums[j].name)+"');>"+myalbums[j].name+"</a></td>";
			html1+="</tr>";
		}
		html1+="</table>";			
		$("#mySearchAlbum").html("<h2>Albums</h2>"+html1);
		
		//----		
		var myYoutube=<% out.println(request.getAttribute("youtubeJSON"));%>			
		
		//ancho de los artistas disponible (ancho3=433)
		//ancho de las imagenes de artistas: 80px
		//ancho de nombre del artista: 116px
		//total 196px		
		//caben 2: 196*2=432 < 392		
		var html1="<table>";
		for(var j=0;j<myYoutube.length;j++){
			html1+="<tr>";
			html1+="<td><img src='"+myYoutube[j].urlImagen+"' width='80px'  /></td><td><a href='#' class='linkNoDecore' onclick=reproducirCancionEnCola('"+escape(myYoutube[j].titulo)+"','"+escape(myYoutube[j].titulo)+"','Youtube%20Artist','Youtube','"+myYoutube[j].duration+"','"+myYoutube[j].idVideo+"');>"+(myYoutube[j].titulo)+"</a></td>";
			html1+="</tr>";
		}
		html1+="</table>";			
		$("#mySearchYoutube").html("<h2>Youtube Videos</h2>"+html1);
		
		
		//-------------------------------------------------------
		
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
	         		 	
	         		 	cancion=decodeURIComponent(replaceAll(cancion,"+"," "));
	         		 	artista=decodeURIComponent(replaceAll(artista,"+"," "));
	         		 	
	         	        var foo = $('<span style="white-space:nowrap;border:1px solid #000;background-color: #FFFAF0; color:black; padding-left:5px;padding-right:5px;font-size: 10pt "></span>').text("  "+cancion + " de " + artista+ "   ");
	         	        
	         	        return foo;
	         	    }
	         	});
		
		$('#searchTable a.dur').each(function(){			
			//console.debug($(this).text());
			var seg=$(this).text();
			var seg2=segundos_a_minutos(seg);
			$(this).text(seg2);
		});
		
		
		var row = 0;
    	$('table.searchTable tbody tr').each(function() {
    		row++;									 
    		if ( row % 2 == 0 ) {
    			/* even row: add class="even" */
    			$(this).addClass('even');
    		} 
    		else{
    			$(this).addClass('odd');
    		}
    	});	

		$('#searchTable tr').hover( function () {
			var id_padre=$(this).attr("id");
			//alert('id: '+id_padre);
			id_padre=escape(id_padre);
			//esto coge el primer td del tr 
			var ee=$(this).find('td:eq(0)');
			$(ee).html('<div style="vertical-align: middle;"><span style="margin-left:10px"><img src="images/botones/play_32.png" height="16px" onclick="reproducirCancionBusq(\''+id_padre+'\');"> </span><span style="margin-left:10px">   <img src="images/botones/plus_32.png" height="16px"  onclick="ponerEnColaCancionBusq(\''+id_padre+'\');"></span></div>');
			//alert('test');
		},function (){
			var id_padre=$(this).attr("id");
			//alert('id: '+id_padre);
			var ee=$(this).find('td:eq(0)');			
			$(ee).html('');
		});		

		/*
		$('#example td.playBtn_track').click(function(){
			alert('otro test');				
		});
		*/
	});

	function reproducirCancionBusq(idRep){	
		var params = idRep.split('_');
		//alert("id: "+unescape(params[0]));
		reproducirCancionEnCola(params[0],params[1], params[2], params[3],params[4]);			
	}

	function ponerEnColaCancionBusq(idRep){	
		//alert(idRep);
		var params = idRep.split('_');
		//alert("id: "+params[0]);
		anadirCancionEnCola(params[0], params[1],
				params[2], params[3], params[4]);		
	}

</script>	
	
<style>


</style>

<div>		
		<div>
			<table width="100%">
				<tr>
					<td width="33%">
						<div id="mySearchArtista" style="height:200px;overflow: auto;">
							Artistas
							<% //out.println(request.getAttribute("artistasJSON"));%>
						</div>						
					</td>
					
					<td width="33%">
						<div id="mySearchAlbum" style="height:200px;overflow:auto;">
							Albums
							<% //out.println(request.getAttribute("albumsJSON"));%>							
						</div>						
					</td>
					
					<td width="33%">
						<div id="mySearchYoutube" style="height:200px;overflow:auto;width: 100%">
							Youtube
							<% //out.println(request.getAttribute("youtubeJSON"));%>		
						</div>						
					</td>
					
				</tr>
			</table>
		</div>


		<div id="container" class="ex_highlight_row" style="width:100%;padding-top: 0px;">
				<table cellpadding="0" cellspacing="0" border="0" bordercolor="green" id="searchTable" class="searchTable" width="100%">
				 
					<thead>
						<tr>
							<th align="left"></th>							
							<th align="left"><fmt:message key="application.music.nombre" /></th>
							<th align="left"><fmt:message key="application.music.artista" /></th>
							<th align="left"><fmt:message key="application.music.album" /></th>
							<th align="left"><fmt:message key="application.music.duracion" /></th>
							
						</tr>
					</thead>
				 
					<tbody>
						
						
						<!-- ------------------------------------------------ -->
						<logic:present name="temas"  scope="session">
					
							<logic:iterate id="mistemas2" name="temas"  scope="session">
											
									<!-- 
										class="menuc" esta clase a parte de dar estilo
										sirve para meter en la fila un menú secundario
									  -->							
									<tr class="menuc" style="height: 24px"
									
									id='<bean:write name="mistemas2" property="name"/>_<bean:write name="mistemas2" property="name"/>_<bean:write name="mistemas2" property="artist"/>_<bean:write name="mistemas2" property="album"/>_<bean:write name="mistemas2" property="duration"/>'
									ondblclick='reproducirCancionEnCola(escape("<bean:write name="mistemas2" property="id"/>"),escape("<bean:write name="mistemas2" property="name"/>"),escape("<bean:write name="mistemas2" property="artist"/>"),escape("<bean:write name="mistemas2" property="album"/>"),escape("<bean:write name="mistemas2" property="duration"/>"));'>
										
										
										<td class="playBtn_track" id="test" width="8%" valign="middle">  </td>
										
										<td class="gradeA" align="left" width="30%"><a  class="linkNoDecore" href="#" ><bean:write name="mistemas2" property="name"/></a></td>
										<td class="gradeA" align="left" width="30%">
											<a  class="linkNoDecore" href="#" onclick='searchArtist2(escape("<bean:write name="mistemas2" property="artist"/>"));'>
												<bean:write name="mistemas2" property="artist"/>
											</a>
										</td>
										
										<td class="gradeA" align="left" width="27%">
											<a class="linkNoDecore" href="#" onclick='searchAlbum(escape("<bean:write name="mistemas2" property="artist"/>"),escape("<bean:write name="mistemas2" property="album"/>"));'>
												<bean:write name="mistemas2" property="album"/>
											</a>
										</td>
										<td class="gradeA" align="left"  width="5%"><div><a href="#" class="dur"  style="text-decoration: none;color:#222;	font-size: 10ptcursor: default" ><bean:write name="mistemas2" property="duration"/></a></div></td>
										
										
										
									</tr>
									
							</logic:iterate>
				
						</logic:present>	
						<!-- ------------------------------------------------ -->
							
					</tbody>
					
				</table>
		</div>
	</div>
		