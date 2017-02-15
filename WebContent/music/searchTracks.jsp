<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>			
			
			
<fmt:setBundle basename="MessageResource"/>					

						
<script>
	$(document).ready(function(){ 	
		
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
		
		$('#tracks_table a.dur').each(function(){			
			//console.debug($(this).text());
			var seg=$(this).text();
			var seg2=segundos_a_minutos(seg);
			$(this).text(seg2);
		});

		$('#example a.dur').each(function(){			
			//console.debug($(this).text());
			var seg=$(this).text();
			var seg2=segundos_a_minutos(seg);
			$(this).text(seg2);
		});
/*
		$('#example td.playBtn_track').hover( function () {
			 var padre=$(this).parent();
			 var id_padre=$(padre).attr("id");
			// alert('ee2: '+ee2);
			
		    $(this).html('<img src="css/tables/images/play_track_arrow.gif" onclick="reproducir(\''+id_padre+'\');">');
			  }, 
		  function () {
		    $(this).html('');		
		});
*/
		
		$('#example tr').hover( function () {
			var id_padre=$(this).attr("id");
			//alert('id: '+id_padre);
			id_padre=escape(id_padre);
			//esto coge el primer td del tr 
			var ee=$(this).find('td:eq(4)');
			$(ee).html('<table><tr><td  align="left"><img src="css/tables/images/play_track_arrow.gif" onclick="reproducirCancionBusq(\''+id_padre+'\');"></td><td align="left"><img src="css/tables/images/addBtn.gif" onclick="ponerEnColaCancionBusq(\''+id_padre+'\');"></td></tr></table>');
			//alert('test');
		},function (){
			var id_padre=$(this).attr("id");
			//alert('id: '+id_padre);
			var ee=$(this).find('td:eq(4)');			
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
		reproducirCancionEnCola(params[0],
				params[1], params[2], params[3],
				params[4]);			
	}

	function ponerEnColaCancionBusq(idRep){		
		var params = idRep.split('_');
		anadirCancionEnCola(params[0], params[1],
				params[2], params[3], params[4]);		
	}

</script>	
	testttttttttt!!!!!
<div style="width: 100%">			
						
		<div id="dt_example" style="width:100%;">
		
		<div id="container" class="ex_highlight_row" style="width:100%;">
			
			<div id="demo" style="width:100%;">
				<table cellpadding="0" cellspacing="0" border="0"  width="100%">
					<thead>
						<tr>							
							<th align="left"><fmt:message key="application.music.nombre" /></th>
							<th align="left"><fmt:message key="application.music.artista" /></th>
							<th align="left"><fmt:message key="application.music.album" /></th>
							<th align="left"><fmt:message key="application.music.duracion" /></th>
							<th align="left"></th>
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
									<tr class="menuc" 
									
									id='<bean:write name="mistemas2" property="name"/>_<bean:write name="mistemas2" property="name"/>_<bean:write name="mistemas2" property="artist"/>_<bean:write name="mistemas2" property="album"/>_<bean:write name="mistemas2" property="duration"/>'
									ondblclick='reproducirCancionEnCola(escape("<bean:write name="mistemas2" property="id"/>"),escape("<bean:write name="mistemas2" property="name"/>"),escape("<bean:write name="mistemas2" property="artist"/>"),escape("<bean:write name="mistemas2" property="album"/>"),escape("<bean:write name="mistemas2" property="duration"/>"));'>
										
										<td class="gradeA" align="left"><a  class="linkNoDecore" href="#" ><bean:write name="mistemas2" property="name"/></a></td>
										<td class="gradeA" align="left">
											<a  class="linkNoDecore" href="#" onclick='searchArtist2(escape("<bean:write name="mistemas2" property="artist"/>"));'>
												<bean:write name="mistemas2" property="artist"/>
											</a>
										</td>
										
										<td class="gradeA" align="left">
											<a class="linkNoDecore" href="#" onclick='searchAlbum(escape("<bean:write name="mistemas2" property="artist"/>"),escape("<bean:write name="mistemas2" property="album"/>"));'>
												<bean:write name="mistemas2" property="album"/>
											</a>
										</td>
										<td class="gradeA" align="left"><div><a href="#" class="dur"  style="text-decoration: none;color:#222;	font-size: 10ptcursor: default" ><bean:write name="mistemas2" property="duration"/></a></div></td>
										
										<td class="playBtn_track" id="test" width="100px"> </td>
										
									</tr>
									
							</logic:iterate>
				
						</logic:present>	
						<!-- ------------------------------------------------ -->
							
					</tbody>
					
				</table>
			</div>
			
			
			
			
		</div>
	</div> 
	
</div>
		