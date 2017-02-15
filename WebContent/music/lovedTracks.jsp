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
						
			
<%@page import="es.alex.futvre.DTO.LastFMGTrackDTO"%>

<script>

function reproducir_playbtn(id){
	  //alert(id);
	  var params = id.split('_');
	  getTrackInfo(params[1], params[2]);	  
}

	$(document).ready(function(){ 
		$('#tracks_table a.dur').each(function(){			
			//console.debug($(this).text());
			var seg=$(this).text();
			var seg2=segundos_a_minutos(seg);
			$(this).text(seg2);
		});
		
		  $('.menuc1').draggable({
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
		  
		  /*
		  $('img').hover(
					function(){
						console.log("test!");
						console.log('entrada img: '+$(this).attr('src') );
						console.log('entrada img2: '+$(this).attr('id') );
					},
					  function () {
						console.log('salida img: '+$(this).id);						
					  }			  
				  );
		  */
		  
		  	  
		  $('.menuc1').hover(
			function(){		
				//$(this).append('<div><img src="images/botones/play_btn_48.png"></div>');
				var _playbtn="#"+this.abbr;
				//$(ee).hide();
				//alert($(this).attr('abbr'));
				console.log("test: "+"#"+this.abbr);
				$(_playbtn).css("display", "block");
			 	// alert('entrada'+this.id);			
			},
			  function () {
				//alert('salida'+this.id);
				var _playbtn="#"+this.abbr;
				$(_playbtn).css("display", "none");
			  }			  
		  );
		  
		  
		  
});

</script>		
			
			<h1><fmt:message key="application.menu.topartistas" /></h1>
			
			 			
			<table>
				
				<%
				
				String size=(String)request.getAttribute("tamanyoPantalla");
				//adapatamos el numero de elementos en la pantalla para que coja todo el ancho
				//cogemos el tamaño de la pantalla (resolucion) que se ha enviado al servidor y lo pillamos 
				//de la sesion 
				int est=Integer.parseInt(size);
				//quitamos el panel lateral
				int est2=est - 280;
				//quitamos la parte del lateral derecho
				est2=est2 - 280;
				
				//dividimos por lo que ocupa cada columna
				int est3=est2/150;
				
					List<LastFMGTrackDTO> lovedTracks=(List)request.getAttribute("lovedTracks"); 
					int cont=0;
				
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
							String playbtn="playbtn_"+cont;
							String id=URLEncoder.encode(track.getName())+"_"+URLEncoder.encode(track.getName())+"_"+URLEncoder.encode(track.getArtist())+"_"+URLEncoder.encode(track.getName())+"_"+track.getDuration();
							out.println("<td width='20%' class='menuc1' style='padding-left:30px' abbr='"+playbtn+"' id='"+id+"' ondbclick='getTrackInfo(\""+URLEncoder.encode(track.getName())+"\",\""+URLEncoder.encode(track.getArtist())+"\")'>"+
							"<div style='position: relative'><img style='z-index: 2; position: relative;' alt='bottom image' src='"+track.getImageURL()+"' width='100px' height='100px'>"+
									"<div style='z-index: 3; left: 25px; display:none; position: absolute; top: 25px; ' id='"+playbtn+"' onclick='reproducir_playbtn(\""+id+"\");' >"+
							"<img alt='top image' src='images/botones/play_btn_48.png' style='opacity:0.8;filter:alpha(opacity=80);'>"+
						"</div>"+
					"</div></div></div><br/><span class='nombreCuadro'>"+track.getName()+"</span><br><span class='artistaCuadro'>"+track.getArtist()+"</span></td><td>&nbsp;&nbsp;&nbsp;</td>");
							cont++;
						}
					}
					%>
						
					</tr>
					<tr style="height: 40px;">
						<td colspan="5" style="height: 40px;">
							
						</td>
					</tr>
					<%
					}
				
				%>
				
			</table>
			
			
			  
			