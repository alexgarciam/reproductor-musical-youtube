<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<fmt:setBundle basename="MessageResource" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="application.name" />
</title>

<jsp:include page="scripts2.jsp"></jsp:include>

<link rel="stylesheet" href="tinyScrollbar/css/website.css"
	type="text/css" media="screen" />
<script type="text/javascript"
	src="tinyScrollbar/js/jquery.tinyscrollbar.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#scrollbar1').tinyscrollbar();
		$('#update').click(function() {
			$("#nuevaListaUser").show();
			$('#scrollbar1').tinyscrollbar();
		});
		
		$('#registroUser').hide();
		
	});
</script>
<c:if test="${not empty param.authfailed}">
<script type="text/javascript">
	$(document).ready(function() {
		$('#div_tab_login_account').load('login3.jsp?authfailed=true', function() {
			showLogin();
		});
	});
	</script>							  
							</c:if>		


<style type="text/css">
label.error {
	float: none;
	color: gray;
	padding-left: .5em;
	vertical-align: top;
}
</style>
<!--  -->


<style>
#droppable {
	width: 150px;
	height: 150px;
	padding: 0.5em;
	float: left;
	margin: 10px;
	border: 1px solid black;
}

.hover {
	background-color: #696969;
}
</style>

<style>

#suggtable .searchTabletd1 {
	/*border: 0px solid black;*/
    background: orange;    
}

#suggtable .searchTabletd2 {
	/*border: 0px solid black;*/
 padding-left: 10px;    
}

#suggtable .searchTabletd3 {
	/*border: 0px solid black;*/
 min-width: 200px;    
}

#suggtable .finTable {	
	padding-bottom: 20px;
}

#suggtable tr:hover, tr.highlight { background-color: #aaa; } 
 
#suggSearch{
	border-left: 1px solid black;
	border-right: 1px solid black;
	border-bottom: 1px solid black;
	border-top: 0px;
}
 
 
</style>

</head>

<body style="margin: 0px; margin-left: 0px; padding-left: 0px">
	<div id="contenedor" style="width: 100%;">
		<!-- cabecera -->
		<div id="cabecera" style="width: 100%; background-color: gray;">
			<div>
				<table height="25px" width="100%" border="1">
					<tr>
						<td>LOGO3</td>
						<td><a href="getListasUsuario.do">getListas</a> |</td>	
						<td><a id="newAccount" class="modalbox" href="#registroUser">Crear Cuentaaaa1</a> |</td>
						<sec:authorize ifAllGranted="ROLE_ADMIN">	
																						
							<td>Hola usuario admin!!</td>
							<td><a href="j_spring_security_logout">Cerrar sesion</a> |</td>
						</sec:authorize>
						
						<td><a href="#" id="spots">publicidad</a></td>
												
						<td>						
							<c:if test="${not empty param.authfailed}">fallo!!							
							    <span id="infomessage" class="errormessage" >
							    	Login failed due to2:
							    	<span id="errorLogin"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></span> 
							    </span>
							</c:if>				 
						</td>
												
						<td>
							<div id="result"></div>
						</td>
						
						<sec:authorize ifAllGranted="ROLE_ANONYMOUS">
							
							<td  width="40%" align="right">
								<a href='#' id='createAccount'>Create Account</a>
							</td>
							
							<td >
								<!-- Login Starts Here -->
								<div id="loginContainer">
									<a href="#" id="loginButton"><span>Login</span><em></em>
									</a>
									<div style="clear: both;"></div>
									<div id="loginBox">
										<form id="loginForm" action="j_spring_security_check">
											<fieldset id="body">
												<fieldset>
													<label for="j_username">usuario</label> <input type="text"
														name="j_username" id="j_username" />
												</fieldset>
												<fieldset>
													<label for="j_password">Password</label> <input
														type="password" name="j_password" id="j_password" />
												</fieldset>
												<input type="submit" id="login" value="Sign in" />
											</fieldset>
											<span><a href="#">Forgot your password?</a>
											</span>
										</form>
									</div>
								</div> <!-- Login ends here --> |</td>
						</sec:authorize>
					</tr>
				</table>
			</div>
		</div>
		<!-- cabecera -->


		<!-- controles -->
		<div id="menu"
			style="background-image: url('images/interfaz/bg-menu.png'); height: 67px; margin: 0px; padding: 0px">
			<!-- menu -->
			<table width="100%" id="separadorMenu" border="0px"
				bordercolor="#CC0000">
				<tr>
					<td width="30%">
						<table>
							<tr>
								<td>
									<div class="searchform">
										<input class="searchfield" type="text" value="Search..."
											onfocus="if (this.value == 'Search...') {this.value = '';}"
											onblur="if (this.value == '') {this.value = 'Search...';}"
											name="buscar_txt" id="buscar_txt" /> <input
											class="searchbutton" type="button" id="buscar_btn" value="Go" />
									</div>
									<div id="suggestions"
										style="background-color: white; position: absolute; top: 88px; left: 17px; z-index: 9999; max-width: 282px">										
										<div id="suggSearch"></div>
									</div></td>
								<td>
									<div id="searchGif">
										<img src="images/gifloader/ajax_loader_orange_300.gif"
											width="30px" height="30px" />
									</div></td>
							</tr>
						</table></td>
					<td width="19%" height="55px" valign="top">
						<!-- Imagen de la reproducción -->
						<div id="repInfo" style="background-color: black;">
							<table>
								<tr>
									<td><img src="" id="repInfoImg" width="55px" height="55px" />
									</td>
									<td>
										<table>
											<tr>
												<td><div id="repInfoImg_cancion"
														style="color: white; font-size: 10pt"></div>
												</td>
											</tr>
											<tr>
												<td id="repInfoImg_artista"
													style="color: gray; font-size: 8pt"></td>
											</tr>
										</table></td>
								</tr>
							</table>
						</div> <!-- Imagen de la reproducción --></td>
					<td width="2%"></td>
					<td valign="middle" width="49%" align="right">
						<!-- Controles  -->
						<div id="controls">
							<!-- Slider Control del video de youtube -->
							<table align="right" border="0" bordercolor="#00ff00">
								<tr valign="middle">

									<td id="prev_td" valign="middle" width="20px"
										class="ui-state-default ui-corner-all"><a id="prev"
										href="#" class="ui-icon ui-icon-seek-prev"
										onclick="previous();"></a>
									</td>
									<td id="play_td" valign="middle" width="20px"
										class="ui-state-default ui-corner-all"><a id="play"
										href="#" class="ui-icon ui-icon-play" onclick="play_pause();"></a>
									</td>
									<td id="next_td" valign="middle" width="20px"
										class="ui-state-default ui-corner-all"><a id="next"
										href="#" class="ui-icon ui-icon-seek-next" onclick="next();"></a>
									</td>

									<td valign="middle" width="80px"><div
											style="padding-left: 10px; padding-right: 10px">
											<div id="slider2"></div>
										</div>
									</td>
									<td width="3px"></td>
									<td id="mute" valign="middle" width="10px"
										class="ui-state-default ui-corner-all"><a id="mutebtn"
										href="#" class="ui-icon ui-icon-volume-on"></a>
									</td>
									<td width="3px"></td>
									<td valign="middle" width="400px"><div
											style="padding-left: 10px; padding-right: 5px">
											<div id="slider"></div>
										</div>
									</td>

									<td valign="middle" width="40px"><div id="amount"
											style="color: white; padding-left: 15px"></div>
									</td>
									<td valign="middle" width="3px"></td>
									<td id="repeat" valign="middle" width="20px"
										class="ui-state-default ui-corner-all"><a id="next"
										href="#" class="ui-icon ui-icon-refresh"
										onclick="repetirCancionFunction();"></a>
									</td>
									<td valign="middle" width="3px"></td>

									<!-- <td id="repeat"  valign="middle" width="20px" class="ui-state-default ui-corner-all"><a id="next" href="#" class="ui-icon ui-icon-arrow-4-diag" onclick="myFullsize();"></a></td>  -->
									<td valign="middle" width="3px"></td>
									<td id="shuffle" valign="middle" width="20px"
										class="ui-state-default ui-corner-all"><a id="next"
										href="#" class="ui-icon ui-icon-shuffle"
										onclick="reproduccionAleatoriaFunction();"></a>
									</td>
								</tr>
							</table>
							<!-- Slider -->
						</div> <!-- fin Controles  --></td>
				</tr>
			</table>
			<!-- menu -->
		</div>
		<!-- controles -->



		<!-- Zona media (o central) -->
		<div id="media"
			style="margin: 0px; padding: 0px; margin-left: 0px; padding-left: 0px">
			<!-- media -->
			<table id="separadorMedia" width="100%" border="0" bordercolor="red">
				<tr>

					<!-- panel lateral izq -->
					<td valign="top" width="275px"><img
						src="images/interfaz/panelLateral_up5.png" width="270px">
						<div id="panelLateral"
							style="background-image: url('images/texturas/textura (15).jpg'); background-repeat: repeat; width: 270px; border: 0px solid blue">

							<!-- panel lateral -->

							<div id="Home"
								style="padding-left: 20px; border: 0px solid green">
								<table width="100%">
									<tr>
										<td width="20px"><a href="#" id="linkHome1"
											style="text-decoration: none; color: white;"><img
												src="images/botones/home3_18.png" />
										</a></td>
										<td><a href="#" id="linkHome2"
											style="text-decoration: none; color: white;">Home </a></td>
									</tr>
								</table>

							</div>

							<!-- Listas de reproducción -->
							<sec:authorize ifAllGranted="ROLE_ADMIN">
								<div style="padding-left: 20px">

									<table border="0" bordercolor="blue" width="100%">
										<tr id="myFavoritos">
											<td width="20px"><a href="#"
												style="text-decoration: none; color: white;"><img
													src="images/botones/favoritos_icon.png" />
											</a></td>
											<td><a href="#"
												style="text-decoration: none; color: white;">Favoritos</a></td>
										</tr>
										<tr>
											<td width="20px"><a id="update" class="modalbox"
												href="#inline" style="text-decoration: none; color: white;"><img
													src="images/botones/music_beam_add.png" />
											</a></td>
											<td><a id="update" class="modalbox" href="#inline"
												style="text-decoration: none; color: white;">Nueva lista</a>
											</td>
										</tr>
									</table>
								</div>
								<div id="recuListas" style="color: white; padding-left: 30px;">
									<img src="images/gifloader/ajax_loader_orange_300.gif"
										width="30px" height="30px" /> Recogiendo listas...
								</div>
							</sec:authorize>

							<div id="scrollbar1"
								style="padding-left: 30px; margin-bottom: 15px">
								<div class="scrollbar">
									<div class="track">
										<div class="thumb">
											<div class="end"></div>
										</div>
									</div>
								</div>
								<div class="viewport">
									<div id="scroller" class="overview" style="color: white;">
										<h3>Crea Tu cuenta</h3>
										<p>Y podrás crear tus propias listas de reproducción</p>

									</div>
								</div>
							</div>
							<!-- Listas de reproducción -->

							<!--  Radio buttons -->
							<div id="radio" align="center"
								style="text-align: center; font-size: 8pt;">
								<input type="radio" id="radio1" name="radio" value="video" /><label
									for="radio1"><fmt:message
										key="application.menu.modoVideo" />
								</label> <input type="radio" id="radio2" name="radio" value="audio" /><label
									for="radio2"><fmt:message
										key="application.menu.modoAudio" />
								</label>
							</div>
							<!--  Radio buttons -->


							<!-- reproductor -->

							<!--  -->
							<br />
							<div id="padreOcultacion"
								style="position: relative; text-align: center" align="center">
								<DIV id="capaOcultacion"
									style="text-align: center; text-align: center; position: absolute; background-color: gray; left: 0px; width: 270px; height: 1px; background-image: url('images/texturas/textura (15).jpg'); background-repeat: repeat; z-index: 2;">
									<IMG id="imageAlbum" SRC="" width="250px" height="250px">
								</DIV>
							</div>
							<!--  -->

							<!-- Video de youtube  -->
							<div id="videoClip">
								<table align="center" width="100%">
									<tr>
										<td align="center">
											<div id="ytapiplayer" style="z-index: 1;">
												<fmt:message key="application.menu.flashPlayer.error" />
											</div></td>
									</tr>
								</table>
								<table align="center" width="100%">
									<tr>
										<td align="center"><script type="text/javascript">
											//          

											// allowScriptAccess must be set to allow the Javascript from one 
											// domain to access the swf on the youtube domain
											//param wmode:"transparent" hace que podamos ocultar el visor d eyoutube montando una
											//capa por encima
											var params = {
												allowScriptAccess : "always",
												bgcolor : "#cccccc",
												wmode : "transparent"
											};
											// this sets the id of the object or embed tag to 'myytplayer'.
											// You then use this id to access the swf and make calls to the player's API
											var atts = {
												id : "myytplayer"
											};

											//ponemos el tamaño										      
											swfobject
													.embedSWF(
															"http://www.youtube.com/apiplayer?enablejsapi=1&playerapiid=ytplayer&version=3&wmode=transparent",
															"ytapiplayer", 250,
															250, "8", null,
															null, params, atts);
											//
										</script></td>
									</tr>
								</table>
							</div>
							<!-- fin Video de youtube  -->
							<!-- Informacion del album  -->
							<DIV id="infoPanel2" STYLE="" align="left"></DIV>
							<!--  -->
							<!-- reproductor -->


							<!-- panel lateral -->

						</div> <img src="images/interfaz/panelLateral_bottom5.png" width="270px">
					</td>
					<!-- panel lateral izq -->

					<!-- panel central -->
					<td width="100%" valign="top" align="left"
						style="text-align: left; background-color: white;">
						<!--  -->
						<div id="home" style="width: 100%;">
							<div id="container">

								<!-- Contenido de las pestañas / atributo class asocia con la pestaña  -->
								<div id="searchresults" style="overflow: auto;">Haz una
									busqueda</div>

								<div id="youtubeResults" style="overflow: auto;">Haz una
									busqueda</div>
								<div id="actualRepro" style="overflow: auto;">Aun no hay
									nada para reproducir</div>
								<div id="div_tab_albums" style="overflow: auto;">Haz una
									busqueda</div>
								<div id="div_tab_artists" style="overflow: auto;">Haz una
									busqueda</div>
								<div id="div_tab_listas" style="overflow: auto;">Selecciona
									una lista de reproducción</div>
								<div id="div_tab_login_account" style="overflow: auto;">Login del usuario</div>


							</div>
						</div> <!--  --></td>
					<!-- panel central -->

					<!-- lateral derecho (publicidad y otras) -->
					<td width="100%" valign="top" align="left">
						<div style="background-color: orange;" id="publicidad">
							ower</div></td>
					<!-- lateral derecho (publicidad y otras) -->

				</tr>
				<!-- media -->
			</table>
		</div>
		<!-- Zona media (o central) -->


		<!-- ESTE FORMULARIO NO SE ESTÁ USANDO, SE USA EL DE HTML,  login.jsp -->
		<!-- hidden inline form -->
		<div id="inline">
			<h2>Crear Nueva lista</h2>
			<div id="message1"></div>
			<form id="nuevaListaUser" name="nuevaListaUser" action="#"
				method="post">
				<label for="nombreListaForm">Nombre de la lista</label> <input
					type="text" id="formLista" name="formLista" class="txt" value="">
				<button id="send">Guardar</button>
			</form>
		</div>
		<!-- nueva lista -->


		<!-- nuevo usuario -->
		<!-- hidden inline form -->
		<div id="registroUser" style="min-width: 550px;min-height:350px ;text-align: center;">
			<h2>Registro de Usuario</h2>
			<div id="message1"></div>
			<form id="nuevaListaUser2" name="nuevaListaUser2" action="registrarUsuario.do"
				method="post">
				<table>
					<tr>
						<td align="left"><label for="userNameForm">Usuario</label>
						</td>
						<td align="left"><input type="text" id="userNameForm" name="userNameForm"
							class="txt20 required" size="20" maxlength="20" minlength="2">
						</td>
					</tr>
					<tr>
						<td align="left"><label for="userEmailForm">Email</label>
						</td>
						<td align="left"><input type="text" id="userEmailForm"
							name="userEmailForm" class="txt20 email required" size="20"
							maxlength="20">
						</td>
					</tr>
					<tr>
						<td><label for="userPassword1Form">Password</label>
						</td>
						<td><input type="password" id="userPassword1Form"
							name="userPassword1Form" class="txt20 required">
						</td>
					</tr>
					<tr>
						<td><label for="userPassword2Form">Password</label>
						</td>
						<td><input type="password" id="userPassword2Form"
							name="userPassword2Form" class="txt20 required">
						</td>
					</tr>
					<tr>
						<td colspan="2"><button id="sendUserForm">Registro</button>
						</td>
					</tr>
				</table>
			</form>


		</div>
		<!-- nuevo usuario -->


		<!-- pié -->
		<div style="clear: both"></div>
		<div id="pieApp"
			style="width: 100%; height: 25px; background-color: gray;">
			Hooooolaaaaaaaa de nuevoooo!</div>
		<!-- pié -->
	</div>

</body>
</html>