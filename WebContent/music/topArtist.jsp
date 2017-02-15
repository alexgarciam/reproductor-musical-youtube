<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	

<%@ page import="es.alex.futvre.DTO.LastFMArtistDTO" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>					
				
				
	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>			
<fmt:setBundle basename="MessageResource"/>				
						
	<script>
		$(document).ready(function(){ 	
				$('#artist_table a.dur').each(function(){			
					//console.debug($(this).text());
					var seg=$(this).text();
					var seg2=segundos_a_minutos(seg);
					$(this).text(seg2);
				});						
		});
	</script>	
	
	<h1><fmt:message key="application.menu.topartistas" />	</h1>
	
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
		int est3=est2/120;	
		
			Collection<LastFMArtistDTO> topArtists=(Collection)request.getAttribute("topArtists");																		
			Iterator<LastFMArtistDTO> it=topArtists.iterator();
			while(it.hasNext())
			{
			%>										
			<tr>											
			<% 
			for(int i=0;i<est3;i++)
			{
				if(it.hasNext())
				{
					LastFMArtistDTO artist=(LastFMArtistDTO)it.next();												
					out.println("<td width='20%' class='menuArtistas' style='padding-left:30px' id='"+artist.getName()+"' ondblclick='searchArtist2(\""+artist.getName()+"\")' ><img src="+artist.getImageURL()+"  width='100px' height='100px'><br/><span class='artistaCuadro'>"+artist.getName()+"</span></td><td>&nbsp;&nbsp;&nbsp;</td>");
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
				