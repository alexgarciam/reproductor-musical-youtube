<%@ page import="es.alex.futvre.DTO.LastFMAlbumDTO;" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
	
	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>			
<fmt:setBundle basename="MessageResource"/>					
									
<script>

	function reproducirCancionBusq2(idRep){
		//alert(idRep);		
		var params = idRep.split('_');
		reproducirCancionEnCola(params[0],
				params[1], params[2], params[3],
				params[4]);			
	}
	
	function ponerEnColaCancionBusq2(idRep){
		//alert(idRep);		
		var params = idRep.split('_');
		anadirCancionEnCola(params[0], params[1],
				params[2], params[3], params[4],'');		
	}

	$(document).ready(function(){ 	
			$('#album_table a.dur').each(function(){			
				//console.debug($(this).text());
				var seg=$(this).text();
				var seg2=segundos_a_minutos(seg);
				$(this).text(seg2);
			});

			$('#album_table a.reemp').each(function(){			
				//console.debug($(this).text());
				var seg=$(this).text();
				//console.debug("seg: "+seg);
				var seg2=unescape(seg);
				
				seg2=replaceAll(seg2,'+',' ');
				//console.debug("seg2: "+seg2);
				$(this).text(seg2);
			});		

			
			$("#mascontent").hide();
			var content=$("#content").text();
			var album=$("#albumArtista").html();
			if(content.length>300)
			{
				$("#mascontent").show();
			}
			$("#mascontent").click(function(){
				//este alert saca la información del album 
				$.fancybox.open(album+"<br/><br/>"+content );
				//alert(content);
			});		


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


	    	$('#album_table tr').hover( function () {
		    	//alert('test');
				 var id_padre=$(this).attr("id");

				
				 id_padre=escape(id_padre);
				 //alert('id: '+id_padre);
				 
				//esto coge el primer td del tr 
				var ee=$(this).find('td:eq(0)');
				
				$(ee).html('&nbsp;&nbsp;&nbsp;<img src="images/botones/play_32.png"  height="16px"   style="cursor: pointer;" onclick="reproducirCancionBusq2(\''+id_padre+'\');">&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/botones/plus_32.png"  height="16px"  style="cursor: pointer;" onclick="ponerEnColaCancionBusq2(\''+id_padre+'\');">');
				//alert('test');
			},
				function (){
				 var id_padre=$(this).attr("id");
				 //alert('id: '+id_padre);
				 			 
				var ee=$(this).find('td:eq(0)');			
				$(ee).html('');
				}
			
			);	


	    	
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
		
	});
</script>	
				
						
	<br /><br />	
	
	<logic:present name="album"   scope="session">
		<table width="100%" id="album_table_ppal" border="0"  >
			<tr>
				<td align="left" valign="top" width="180px">
					<img src='<bean:write name="album" property="urlImage"/>'	/>
				</td>
		
				<td align="left" valign="top">
					
						<div id="albumArtista"><h2>
							<bean:write name="album" property="name"/>
						</h2>
						&nbsp;&nbsp;(<fmt:formatDate value="${album.releaseDate}" pattern="yyyy"/>)
						</div>
						<br/>
					
					<div id="content" style="position:relative;height:  50px; overflow: hidden">
						<%						
							LastFMAlbumDTO album=(LastFMAlbumDTO)request.getAttribute("album");
							out.println(album.getDescription());							
						%>						
					</div>
					
					<div>
						<strong><a href="#" id="mascontent"><fmt:message key="application.music.album.description.leermas" /></a></strong>
					</div>
				</td>
			</tr>
		</table>
					<br/><br/>
									
					<!--  -->
					<logic:present name="album" property="tracks" >	
								<div id="myalbumrep">
								<table  id="album_table" class="striped" width="100%" cellspacing="0" cellpadding="0" border="0">
									<!-- 
									<thead>
										<tr>											
											<th align="left"><fmt:message key="application.music.nombre" /></th>
											<th align="left"><fmt:message key="application.music.duracion" /></th>
											<th width="100px"></th>
										</tr>
									</thead>
									 -->
							<logic:iterate id="track" name="album" property="tracks">
								
									<tbody>									
										<tr height="40px" class="menuc"  id="<bean:write name="track" property="name"/>_<bean:write name="track" property="name"/>_<bean:write name="track" property="artist"/>_<bean:write name="track" property="album"/>_<bean:write name="track" property="duration"/>_tr" ondblclick='reproducirAlbumEnCola("<bean:write name="track" property="id"/>","<bean:write name="track" property="id"/>",escape("<bean:write name="track" property="name"/>"),escape("<bean:write name="track" property="artist"/>"),escape("<bean:write name="track" property="album"/>"))';>
										
											<td class="playBtn_track"  width="10%"></td>
											<td align="left" width="50%"><a class="reemp" id="<bean:write name="track" property="id"/>" href="#" style="text-decoration: none;cursor: default;padding-left: 10px;font-weight: normal;font-size: 10pt" ><bean:write name="track" property="name"/></a></td>
											<td align="left" width="10%"><a href="#" class="dur"  style="text-decoration: none;cursor: default;font-weight: normal;font-size: 10pt"><bean:write name="track" property="duration"/></a></td>
																					
										</tr>
									</tbody>
								
							</logic:iterate>
							
								</table>
								</div>
					</logic:present>
					<logic:notPresent  name="album" property="tracks" >
							<fmt:message key="application.music.nohaycanciones" />
					</logic:notPresent>
					<!--  -->
				
												
	</logic:present>
					
	<logic:notPresent name="album">
		<tr>
			<td><fmt:message key="application.music.nohayalbum" /></td>
		</tr>										
	</logic:notPresent>		
		