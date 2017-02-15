<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ page import="es.alex.futvre.DTO.LastFMAlbumDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>

<fmt:setBundle basename="MessageResource" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<script type="text/javascript">
	$(document).ready( function() {
		$('#cuadrosAlbums').hide();
		$('#listadoAlbums').show();

		$('#listadoAlbumsBtn').click( function() {
			$('#listadoAlbums').show();
			$('#cuadrosAlbums').hide();
		});

		$('#cuadroAlbumsBtn').click( function() {
			$('#listadoAlbums').hide();
			$('#cuadrosAlbums').show();
		});

		$('#table_id tr').dblclick(function(){
			 var id_padre=$(this).attr("id");
			 var params = id_padre.split('_');
			 searchAlbum( params[1], params[0] );
			 
		});
		
	});
</script>



<br />
<br />


<div><a href="#" id="listadoAlbumsBtn">listado</a> | <a href="#"
	id="cuadroAlbumsBtn">Cuadros</a></div>

<!-- Artist -->
<div id="listadoAlbums" style="height: 540px"><!--  --> <script
	type="text/javascript" charset="utf-8">
	$(document).ready( function() {
		$('#table_id').dataTable();

		

		
	});
</script>

<div  class="ex_highlight_row">
<table id="table_id"  class="display" cellspacing="0" cellpadding="0">
	<thead>
		<tr>
			<th></th>
			<th><fmt:message key="application.music.nombre" /></th>
			<th>artista</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<logic:present name="albums" scope="session">

			<logic:iterate id="misAlbums" name="albums" scope="session">
			
				  <tr class="menuAlbums2" id="<bean:write name="misAlbums" property="name"/>_<bean:write name="misAlbums" property="artist"/>">
					<td width="10%" class="gradeA"><img src="<bean:write name="misAlbums" property="urlImage"/>" width="80px" height="80px"></td>
					<td class="gradeA" align="left" width="25%"><a class="linkNoDecore" style="color:#222"><bean:write name="misAlbums" property="name"/></a></td>
					<td class="gradeA" align="left" width="25%"><a class="linkNoDecore" style="color:#222"><bean:write name="misAlbums" property="artist"/></a></td>
					<td></td>											
				</tr>				

			</logic:iterate>
		</logic:present>


	</tbody>
</table>
</div>



</div>
<!--  -->



<!-- Artist -->



<div id="cuadrosAlbums" style="height: 540px">

<table>
	<%
		String size = (String) request.getAttribute("tamanyoPantalla");
		//adapatamos el numero de elementos en la pantalla para que coja todo el ancho
		//cogemos el tamaño de la pantalla (resolucion) que se ha enviado al servidor y lo pillamos 
		//de la sesion 
		int est = Integer.parseInt(size);
		//quitamos el panel lateral
		int est2 = est - 280;
		//dividimos por lo que ocupa cada columna
		int est3 = est2 / 120;

		Collection<LastFMAlbumDTO> topArtists = (Collection) request
				.getAttribute("albums");
		Iterator<LastFMAlbumDTO> it = topArtists.iterator();
		while (it.hasNext()) {
	%>
	<tr valign="top">
		<%
			for (int i = 0; i < est3; i++) {
					if (it.hasNext()) {
						LastFMAlbumDTO artist = (LastFMAlbumDTO) it.next();
						out
								.println("<td width='20%' class='menuAlbums2' id='"+ artist.getName()+"_"+artist.getArtist()
										+ "' ondblclick='alert(\""+artist.getArtist()+"\");' ><img width='100px' height='100px' src="
										+ artist.getUrlImage()
										+ "><br/>"
										+ artist.getName()
										+ "</td><td>&nbsp;&nbsp;&nbsp;</td>");
					}
				}
		%>
	</tr>
	<tr style="height: 20px">
		<td colspan="5" style="height: 20px"></td>
	</tr>
	<%
		}
	%>
</table>
</div>


<!-- -------------------------------------------------------------------------------------- -->


