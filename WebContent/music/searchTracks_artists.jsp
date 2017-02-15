<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ page import="es.alex.futvre.DTO.LastFMArtistDTO"%>
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
		$('#cuadros2').hide();	
		
		$('#listadoArtistas').click(function(){
			$('#listado2').show();
			$('#cuadros2').hide();
		});

		$('#cuadroArtistas').click(function(){
			$('#listado2').hide();
			$('#cuadros2').show();
		});		

		$('#artistasEncontrados tr').dblclick(function(){
			 var id_padre=$(this).attr("id");
			 var params = id_padre.split('_');
			 searchArtist2(param);
			 
		});
		
	});
</script>

<br />
<br />


<div>
	<a href="#" id="listadoArtistas">listado</a>
	| <a href="#"  id="cuadroArtistas">Cuadros</a>
</div>

<!-- Artist -->
<div id="listado2" style="height: 600px">
<!--  -->
<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {				
				$('#artistasEncontrados').dataTable({
			        "sPaginationType": "full_numbers"
				});				
			} );
		</script>
		
		<div id="dt_example3" >
		<div id="container3" class="ex_highlight_row">
			<div id="demo3">
				<table cellpadding="0" cellspacing="0" border="0" class="display" id="artistasEncontrados" width="100%">
					<thead>
						<tr>
							<th align="left"></th>
							<th align="left"><fmt:message key="application.music.nombre" /></th>
						</tr>
					</thead>
					<tbody>	
						
						<!-- ------------------------------------------------ -->
						<logic:present name="artistas"  scope="session">
					
							<logic:iterate id="misartistas" name="artistas"  scope="session">
															
									<tr class="menuArtistas" id="<bean:write name="misartistas" property="name"/>" ondblclick="searchArtist2('<bean:write name="misartistas" property="name"/>');">
										<td width="10%" class="gradeA"><img src="<bean:write name="misartistas" property="imageURL"/>" width="80px" height="80px"></td>
										<td class="gradeA" align="left" width="90%"><bean:write name="misartistas" property="name"/></td>										
									</tr>
									
							</logic:iterate>
				
						</logic:present>	
						
						<logic:notPresent name="artistas">
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
<!--  -->



<!-- Artist -->



<div id="cuadros2" style="height: 540px">
	<logic:present name="artistas"  scope="session">
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
	
	
		Collection<LastFMArtistDTO> topArtists = (Collection) request
				.getAttribute("artistas");
		Iterator<LastFMArtistDTO> it = topArtists.iterator();
		while (it.hasNext()) {
	%>
	<tr valign="top">
		<%
			for (int i = 0; i < est3; i++) {
					if (it.hasNext()) {
						LastFMArtistDTO artist = (LastFMArtistDTO) it.next();
						out
								.println("<td width='20%' class='menuArtistas' id='"
										+ artist.getName()
										+ "' ondblclick='searchArtist2(\""+artist.getName()+"\");' ><img width='100px' height='100px' src="
										+ artist.getImageURL()
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


</logic:present>
	<logic:notPresent name="artistas">
		<tr>
			<td><fmt:message key="application.music.noresultados" /></td>
		</tr>										
	</logic:notPresent>
</div>


<!-- -------------------------------------------------------------------------------------- -->

