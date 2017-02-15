<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>							
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
			
<%@ page import="es.alex.futvre.DTO.LastFMGTrackDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.net.URLEncoder" %>			


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>			
<fmt:setBundle basename="MessageResource"/>			
						
			<script>
	$(document).ready(function(){ 
		
		$('#tracks_table a.dur').each(function(){			
			//console.debug($(this).text());
			var seg=$(this).text();
			var seg2=segundos_a_minutos(seg);
			$(this).text(seg2);
		});

		
			
		
});

</script>						
							
			<h1><fmt:message key="application.menu.lovedcanciones" /></h1>	
			
			<table>
										<tr>
									  		<td><div id="uno" class="draggable" style="color: white;">uno</div></td>
									  		<td><div id="dos" class="draggable" style="color: white;">dos</div></td>
									  		<td><div id="tres" class="draggable" style="color: white;">tres</div></td>
									  	</tr>  
									  </table>
									  
									  		
			<table>				
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
				
				List<LastFMGTrackDTO> lovedTracks=(List)request.getAttribute("hypedTracks"); 		
				
					Iterator it=lovedTracks.iterator();
					while(it.hasNext())
					{
					%>					
					<tr>						
					<% 
					for(int i=0;i<est3;i++)
					{
						if(it.hasNext())
						{
							LastFMGTrackDTO track=(LastFMGTrackDTO)it.next();
							out.println("<td width='20%' style='padding-left:30px' class='menuc1' id='"+track.getName()+"_"+track.getName()+"_"+track.getArtist()+"_"+track.getName()+"_"+track.getDuration()+"' ondblclick='getTrackInfo(\""+URLEncoder.encode(track.getName())+"\",\""+URLEncoder.encode(track.getArtist())+"\")'><img src="+track.getImageURL()+"  width='100px' height='100px'><br/><span class='nombreCuadro'>"+track.getName()+"</span><br><span class='artistaCuadro'>"+track.getArtist()+"</span></td><td>&nbsp;&nbsp;&nbsp;</td>");
						}
					}
					%>						
					</tr>
					<tr style="height: 20px">
						<td colspan="5" style="height: 40px">
							
						</td>
					</tr>
					<%
					}				
				%>				
			</table>