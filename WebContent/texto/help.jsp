<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<fmt:setBundle basename="MessageResource"/>

<div style="height:500px; overflow: auto">
	<h3><fmt:message key="application.music.help" /> <fmt:message key="application.name" /></h3>
		
		<div>
			<h3><a name="howtouse"><fmt:message key="application.music.help.howtouse" /> <fmt:message key="application.name" /></a></h3>
			<p>
			Para empezar a usar Sputifly, debemos elegir una canci�n/ video a visualizar, esto podemos hacerlo
			realizando una busqueda en la parte superior izquierda de la pantalla o elegiendo uno de los videos de
			las listas que aparecen en la parte izquierda de la pantalla.			
			</p>
			<p>
			Una vez seleccionado el video, este empezar� a reproducirse hasta que termine, si el video llega al final,
			la reproducci�n del video terminar� y si hay alg�n otro video en la cola de reproducci�n se pasar� al 
			siguiente video automaticamente.
			</p>
		</div>
		<div>
			<h3><a name="howtochangemode"><fmt:message key="application.music.help.howtochangemode" /></a></h3>
			<p>
			Con Sputifly puede elegir entre escuchar la m�sica visualizando la caratula del album o artista o 
			visualizar el video de la canci�n, para ello solo debe de pulsar el boton respectivo en la parte 
			izquierda de la pantalla, pulsando sobre modo audio se mostrar� la caratula del album a la que
			pertenece la canci�n que se est� reproduciendo, en caso de que la canci�n no pertenezca a un album
			o no sea posible encontrar el album o la caratula, se mostrar� una foto del artista, en caso de que 
			no sea posible o de que se cometa un error se mostrar� una imagen de album no encontrado.
			</p>
		</div>
		<div>
			<h3><a name="howtouserepfunctions"><fmt:message key="application.music.help.howtouserepfunctions" /></a></h3>
			<p>
			Para controlar la reproducci�n de un video puede utilizar la barra de reproducci�n que se encuentra
			justo antes del pie de pagina, en esta barra aparecen de izquierda a derecha los siguientes controles 
			de reproducci�n:
			</p>
				<ul>
					<li><p><strong>previous: </strong>si la canci�n lleva mas de 3 segundos reproduciendose se volver� a reproducir la 
					cancion desde el comienzo de la misma, en caso de que no lleve mas de 3 minutos reproduciendo se
					empezar� a reproducir la cancion anterior en la lista de reproducci�n si la hubiera, si no la hubiera
					no produciria efecto alguno.</p></li>
					<li><p><strong>play/pause:</strong> Este boton comienza o para la reproducci�n del video/canci�n seg�n el mismo est�
					parado o reproduciendose.</p></li>
					<li><p><strong>next: </strong>Al pulsar el boton se reproducir� la siguiente canci�n/video de la lista de reproducci�n. 
					En caso de ser la �nica o �ltima de la lista no tendr� efectos.</p></li>
					<li><p>
					<strong>Barra de desplazamiento:</strong> La barra indica la duraci�n de la cancion o video, el  nivel
					representa el tiempo que ya ha sido reproducido, y la parte que queda por reproducir, que puede deslizar
					la barra para ir a cualquier instante de la canci�n o del video. Detr�s se indica con digitos
					la duraci�n total y la cantidad de tiempo que se ha reproducido.		</p>			
					</li>
					<li><p>
					<strong>Repeat: </strong>Pulsando en este bot�n reproduciremos indefinidamente la cancion o video actual, se desactiva
					pulsando de nuevo el mismo bot�n, se indica que est� siendo usado con un color distinto al normal.</p>
					</li>
					<li><p>
					<strong>Fullscreen: </strong>Al pulsar este bot�n la pantalla que muestra la reproducci�n del video actual ocupara
					la totalidad de la pantalla. Para salir hay que pulsar en el mismo bot�n.		</p>			
					</li>
					<li><p>
					<strong>Random: </strong>Al pulsar este bot�n se reproduciran los elementos de la lista de manera aleatoria, es
					decir, sin seguir el orden normal de los elementos en la lista, para desactivar la reproducci�n 
					aleatoria pulsar nuevamente en el mismo bot�n.</p>
					</li>
				</ul> 
			
		</div>
		<div>
			<h3><a name="howtoshare"><fmt:message key="application.music.help.howtoshare" /></a></h3>
			<p>
				Puedes compartir con tus amigos tus temas favoritos enviandoles un enlace al mismo, todo 
				lo que tienes que hacer es click con el boton derecho del raton y en el men� emergente elegir
				<i>"copiar http"</i> despues pulsar OK, recuerda que si no pulsas OK, no copiar�s el enlace.
				Una vez hecho esto tendr�s en el portapapeles el enlace y podr�s pegarlo donde quieras.
			</p>
		</div>
		<div>
			<h3><a name="videonotfound"><fmt:message key="application.music.help.videonotfound" /></a></h3>
			<p>
				A la hora de buscar un tema, es posible que este no coincida exactamente con el video que esperabas
				ver, si esto ocurre puedes seleccionar un video alternativo de la pesta�a de youtube, esta pesta�a
				muestra los resultados de la busqueda que has realizado en los videos almacenados en youtube.
			</p>
		</div>
		<div>
			<h3><a name="createLists"><fmt:message key="application.music.help.createLists" /></a></h3>
			<p>
				Aun no se ha implementado esta funcionalidad. Estar� disponible en futuras versiones.
			</p>
		</div>
		<div>
			<h3><a name="shareLists"><fmt:message key="application.music.help.shareLists" /></a></h3>
			<p>
				Aun no se ha implementado esta funcionalidad. Estar� disponible en futuras versiones.
			</p>
			
		</div>
	
		
	
</div>

