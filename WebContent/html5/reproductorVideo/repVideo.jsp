<!DOCTYPE html>
<html lang="es">
<head>
<title>Reproductor de Video</title>
<link rel="stylesheet" href="reproductor.css">
<script src="reproductor.js"></script>
</head>
<body>
	<section id="reproductor">
		<video id="medio" width="720" height="400">
			<source src="http://minkbooks.com/content/trailer.mp4"></source>
			<source src="http://minkbooks.com/content/trailer.ogg"></source>
		</video>
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