<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
	#fachada{
		background-image: url("images/texturas/textura (15).jpg");
		background-repeat:repeat;
		background-color: black;
		color:white;
	}
	
</style>

<script>
function reproducirUrl(a,b,c,d,e){
	reproducirCancionEnCola(a,b,c,d,e);
	//$("#fachada").hide("slow");
	$("#fachada").slideUp();
	
	
}

</script>


</head>
<body>

<div  align="center" style="text-align: center;width: 100%">

<span style="color:#FFFFFF"></span>

		<br><br>
		<br><br>

	<div style="padding-top: 100px">

		<table width="60%" align="center" border="0" bordercolor="white">
			<tr>
				<td>
					<img src="<bean:write name="image"/>" />
				</td>
			
				<td>
					<table>
						<tr>
							<td>
								<a id="playTrackUrl" href='#' onclick='reproducirUrl(escape("<bean:write name="id"/>"),escape("<bean:write name="cancion"/>"),escape("<bean:write name="artista"/>"),escape("<bean:write name="album"/>"),escape("<bean:write name="duracion"/>"));'><img src="images/botones/play_btn_48.png" /></a>			
							</td>
							<td>
								<table>
									<tr>
										<td>
											<h2><bean:write name="cancion"/></h2>								
										</td>
										<td>
											
										</td>
									</tr>
									<tr>
										<td>
										<bean:write name="album"/>
										<bean:write name="artista"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						
					</table>
					
					
				</td>
			</tr>
		</table>
	
		
		
	</div>
	
</div>




</body>
</html>