<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"  %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>	

<%@ page import="es.alex.futvre.DTO.YoutubeVideoDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>		
			
<fmt:setBundle basename="MessageResource"/>		

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>									

<script>
	$(document).ready(function(){ 	
			
	
		
		$('.menuYoutube').draggable({
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
		
		
		
		$('#videosEncontrados a.dur').each(function(){			
			//console.debug($(this).text());
			var seg=$(this).text();
			var seg2=segundos_a_minutos(seg);
			$(this).text(seg2);
		});

		$('#listadoVideos2').hide();
		
		$('#cuadroVideosBtn').click(function (){
			$('#listadoVideos1').show();
			$('#listadoVideos2').hide();		
		});

		$('#listadoVideosBtn').click(function (){
			$('#listadoVideos1').hide();
			$('#listadoVideos2').show();		
		});		
				
	});
</script>					

<div>
	  <a href="#" id="listadoVideosBtn">listado</a>
	| <a href="#"  id="cuadroVideosBtn">Cuadros</a>
	
		<%
			String size=(String)request.getAttribute("tamanyoPantalla");
			//adapatamos el numero de elementos en la pantalla para que coja todo el ancho
			//cogemos el tamaño de la pantalla (resolucion) que se ha enviado al servidor y lo pillamos 
			//de la sesion 
			int est=Integer.parseInt(size);
			//quitamos el panel lateral
			int est2=est - 280;
			//dividimos por lo que ocupa cada columna
			int est3=est2/150;	
		%>
		
</div>								
							
			<br /><br />
			<div id="listadoVideos1" style="width: 100%">
					
			<!-- ------------------- -->
		<table width="100%">
	<% 
				Collection<YoutubeVideoDTO> topArtists=(Collection)request.getAttribute("videosYoutube");																		
				Iterator<YoutubeVideoDTO> it=topArtists.iterator();
				while(it.hasNext())
				{
				%>										
				<tr valign="top">											
				<% 
				//ponemos el numero de columnas calculado arriba
				for(int i=0;i<est3;i++)
				{
					if(it.hasNext())
					{
						YoutubeVideoDTO video=(YoutubeVideoDTO)it.next();																							
						out.println("<td class='menuYoutube' style='padding-left:20px'"+
								" id='"+video.getTitulo()+"_"+video.getTitulo()+"_youtube video_"+video.getDuration()+"'"+
								"ondblclick='reproducirCancionEnCola(escape(\""+video.getTitulo()+"\"),escape(\""+video.getTitulo()+"\"),\"youtube video\",\"youtube\",\""+video.getTitulo()+"\");' width='20%' id='"+video.getTitulo()+"'  ><img width='100px' height='100px' src="+video.getUrlImagen()+"><br/>"+video.getTitulo()+"<br/>"+video.getDuration()+"</td><td>&nbsp;&nbsp;&nbsp;</td>");
						
					}
				}
				%>											
				</tr>
				<tr style="height: 40px">
					<td colspan="5" style="height: 40px" >
						
					</td>
				</tr>
				<%
				}
			
			%>	
</table>
		<!-- -------------------
			
			
			<logic:present name="videosYoutube"  scope="session">						
				<table id="youtube_table">	
				<logic:iterate id="mistemas" name="videosYoutube"  scope="session">
					<!-- 
					<tr>
						<td align="left">						
							<a href="#" onclick="loadNewVideo('<bean:write name="mistemas" property="idVideo"/>',0)"><img src="<bean:write name="mistemas" property="urlImagen"/>" /></a>
						</td>
						<td align="left">						
							<a href="#" onclick="loadNewVideo('<bean:write name="mistemas" property="idVideo"/>',0)"><bean:write name="mistemas" property="titulo"/></a><br />
						</td>
					</tr>
					<tr ondblclick="reproducirCancionEnCola(escape('<bean:write name="mistemas" property="titulo"/>'),escape('<bean:write name="mistemas" property="titulo"/>'),'youtube video','youtube','<bean:write name="mistemas" property="duration"/>');">
					
						<td class='menuYoutube' id="<bean:write name="mistemas" property="titulo"/>_<bean:write name="mistemas" property="titulo"/>_youtube video_youtube_<bean:write name="mistemas" property="duration"/>">
							<img src="<bean:write name="mistemas" property="urlImagen"/>"> 
						</td>	
						<td class='menuYoutube' id="<bean:write name="mistemas" property="titulo"/>_<bean:write name="mistemas" property="titulo"/>_youtube video_youtube_<bean:write name="mistemas" property="duration"/>">
							 <bean:write name="mistemas" property="titulo"/>
						</td>
						<td >							
							 <a href="#" class="dur"  style="text-decoration: none;cursor: default" ><bean:write name="mistemas" property="duration"/></a>
						</td>					
					</tr>
				</logic:iterate>
				</table>
			</logic:present>	
							
			<logic:notPresent name="videosYoutube">
				<fmt:message key="application.music.youtube.noresultados" />						
			</logic:notPresent>
			
			-->
			
			</div>
				
			
		
		<!-- -->
		<div id="listadoVideos2" style="height: 540px">
		<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {				
				$('#videosEncontrados').dataTable({		 
			        "sPaginationType": "full_numbers"
				});
			} );
		</script>
		
		<div id="dt_example4" >
		<div id="container4"  class="ex_highlight_row">
			<div id="demo4">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="videosEncontrados" width="100%">
					<thead>
						<tr>
							<th align="left"></th>
							<th align="left"><fmt:message key="application.music.nombre" /></th>
							<th align="left"><fmt:message key="application.music.artista" /></th>
						</tr>
					</thead>
					<tbody>	
						
						<!-- ------------------------------------------------ -->
						<logic:present name="videosYoutube"  scope="session">
					
							<logic:iterate id="misVideos" name="videosYoutube"  scope="session">
											
									<!-- 
										class="menuc" esta clase a parte de dar estilo
										sirve para meter en la fila un menú secundario
									  -->							
									<tr class="gradeA" ondblclick="reproducirCancionEnCola(escape('<bean:write name="misVideos" property="titulo"/>'),escape('<bean:write name="misVideos" property="titulo"/>'),'youtube video','youtube','<bean:write name="misVideos" property="duration"/>');">
										<td class="menuYoutube" width="10%"><img src="<bean:write name="misVideos" property="urlImagen"/>" width="80px"></td>
										<td class="menuYoutube" width="45%" align="left"><bean:write name="misVideos" property="titulo"/></td>
										<td class="menuYoutube" width="45%" align="left"> <a href="#" class="dur"  style="text-decoration: none;cursor: default" ><bean:write name="misVideos" property="duration"/></a></td>
									</tr>
									
							</logic:iterate>
				
						</logic:present>	
						
						<logic:notPresent name="videosYoutube">
							<tr>
									<td><fmt:message key="application.music.noresultados" /></td>
								</tr>										
						</logic:notPresent>
						<!-- ------------------------------------------------ -->
							
					</tbody>
					
				</table>
			</div>	
			
			
		</div>
	</div> 
	</div>
	
				
		<!-- -->