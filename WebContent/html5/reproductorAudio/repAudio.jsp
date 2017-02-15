<!DOCTYPE html>
<html lang="es">
<head>
<title>Reproductor de Audio</title>
<link rel="stylesheet" href="reproductor.css">
<script src="reproductor.js"></script>
</head>
<body>
	<section id="reproductor">
		<audio id="medio">
			<source src="http://minkbooks.com/content/beach.mp3"></source>
			<source src="http://minkbooks.com/content/beach.ogg"></source>
		</audio>
		<nav>
			<div id="botones">
				<button type="button" id="reproducir">Reproducir</button>
			</div>
			<div id="barra">
				<div id="progreso"></div>
			</div>
			<div style="clear: both"></div>
		</nav>
	</section>
</body>
</html>