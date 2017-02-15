<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<fmt:setBundle basename="MessageResource"/>

<div style="height:500px; overflow: auto">
	<h3><fmt:message key="application.music.help" /> <fmt:message key="application.name" /></h3>
		
		<div>
			<h3><a name="howtouse"><fmt:message key="application.music.help.howtouse" /> <fmt:message key="application.name" /></a></h3>
			<p>
			Para empezar a usar Sputifly, debemos elegir una canción/ video a visualizar, esto podemos hacerlo
			realizando una busqueda en la parte superior izquierda de la pantalla o elegiendo uno de los videos de
			las listas que aparecen en la parte izquierda de la pantalla.			
			</p>
			<p>
			Una vez seleccionado el video, este empezará a reproducirse hasta que termine, si el video llega al final,
			la reproducción del video terminará y si hay algún otro video en la cola de reproducción se pasará al 
			siguiente video automaticamente.
			</p>
		</div>
		<div>
			<h3><a name="howtochangemode"><fmt:message key="application.music.help.howtochangemode" /></a></h3>
			<p>
			Con Sputifly puede elegir entre escuchar la música visualizando la caratula del album o artista o 
			visualizar el video de la canción, para ello solo debe de pulsar el boton respectivo en la parte 
			izquierda de la pantalla, pulsando sobre modo audio se mostrará la caratula del album a la que
			pertenece la canción que se está reproduciendo, en caso de que la canción no pertenezca a un album
			o no sea posible encontrar el album o la caratula, se mostrará una foto del artista, en caso de que 
			no sea posible o de que se cometa un error se mostrará una imagen de album no encontrado.
			</p>
		</div>
		<div>
			<h3><a name="howtouserepfunctions"><fmt:message key="application.music.help.howtouserepfunctions" /></a></h3>
			<p>
			Para controlar la reproducción de un video puede utilizar la barra de reproducción que se encuentra
			justo antes del pie de pagina, en esta barra aparecen de izquierda a derecha los siguientes controles 
			de reproducción:
			</p>
				<ul>
					<li><p><strong>previous: </strong>si la canción lleva mas de 3 segundos reproduciendose se volverá a reproducir la 
					cancion desde el comienzo de la misma, en caso de que no lleve mas de 3 minutos reproduciendo se
					empezará a reproducir la cancion anterior en la lista de reproducción si la hubiera, si no la hubiera
					no produciria efecto alguno.</p></li>
					<li><p><strong>play/pause:</strong> Este boton comienza o para la reproducción del video/canción según el mismo esté
					parado o reproduciendose.</p></li>
					<li><p><strong>next: </strong>Al pulsar el boton se reproducirá la siguiente canción/video de la lista de reproducción. 
					En caso de ser la única o última de la lista no tendrá efectos.</p></li>
					<li><p>
					<strong>Barra de desplazamiento:</strong> La barra indica la duración de la cancion o video, el  nivel
					representa el tiempo que ya ha sido reproducido, y la parte que queda por reproducir, que puede deslizar
					la barra para ir a cualquier instante de la canción o del video. Detrás se indica con digitos
					la duración total y la cantidad de tiempo que se ha reproducido.		</p>			
					</li>
					<li><p>
					<strong>Repeat: </strong>Pulsando en este botón reproduciremos indefinidamente la cancion o video actual, se desactiva
					pulsando de nuevo el mismo botón, se indica que está siendo usado con un color distinto al normal.</p>
					</li>
					<li><p>
					<strong>Fullscreen: </strong>Al pulsar este botón la pantalla que muestra la reproducción del video actual ocupara
					la totalidad de la pantalla. Para salir hay que pulsar en el mismo botón.		</p>			
					</li>
					<li><p>
					<strong>Random: </strong>Al pulsar este botón se reproduciran los elementos de la lista de manera aleatoria, es
					decir, sin seguir el orden normal de los elementos en la lista, para desactivar la reproducción 
					aleatoria pulsar nuevamente en el mismo botón.</p>
					</li>
				</ul> 
			
		</div>
		<div>
			<h3><a name="howtoshare"><fmt:message key="application.music.help.howtoshare" /></a></h3>
			<p>
				Puedes compartir con tus amigos tus temas favoritos enviandoles un enlace al mismo, todo 
				lo que tienes que hacer es click con el boton derecho del raton y en el menú emergente elegir
				<i>"copiar http"</i> despues pulsar OK, recuerda que si no pulsas OK, no copiarás el enlace.
				Una vez hecho esto tendrás en el portapapeles el enlace y podrás pegarlo donde quieras.
			</p>
		</div>
		<div>
			<h3><a name="videonotfound"><fmt:message key="application.music.help.videonotfound" /></a></h3>
			<p>
				A la hora de buscar un tema, es posible que este no coincida exactamente con el video que esperabas
				ver, si esto ocurre puedes seleccionar un video alternativo de la pestaña de youtube, esta pestaña
				muestra los resultados de la busqueda que has realizado en los videos almacenados en youtube.
			</p>
		</div>
		<div>
			<h3><a name="createLists"><fmt:message key="application.music.help.createLists" /></a></h3>
			<p>
				Aun no se ha implementado esta funcionalidad. Estará disponible en futuras versiones.
			</p>
		</div>
		<div>
			<h3><a name="shareLists"><fmt:message key="application.music.help.shareLists" /></a></h3>
			<p>
				Aun no se ha implementado esta funcionalidad. Estará disponible en futuras versiones.
			</p>
			
		</div>
	
		
	
</div>

