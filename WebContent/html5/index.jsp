<!DOCTYPE html>
<html lang="es">

	<head>
		<meta charset="iso-8859-1" />
		<meta name="description" content="Ejemplo de HTML5" />
		<meta name="keywords" content="HTML5, CSS3, Javascript" />
		<title>pagina en HTML5 Sputifly</title>
		<link rel="stylesheet" href="estiloHTML5.css">
	</head>
	
	<body>
		<div>
			<header id="cabecera">
				<h1>Esta es la cabecera de la web en HTML5</h1>
			</header>
			
			<nav id="menu">
				<ul>
					<li>principal</li>
					<li>fotos</li>
					<li>videos</li>
					<li>contacto</li>				
				</ul>
			</nav>
			
			<section id="seccion">
				seccion principal
				
				<article>
					<header>
						<hgroup>
							<h1>Título del mensaje uno</h1>
							<h2>Subtítulo del mensaje uno</h2>
						</hgroup>
						<time datetime="2011-10-12" pubdate>publicado 12-10-2011</time>
					</header>
					Este es el texto de mi primer mensaje
					<span>Esta <mark>palabra</mark> marcada es relevante</span>
					
					<figure>
						<img src="http://minkbooks.com/content/myimage.jpg">
						<figcaption>
							Esta es la imagen del primer mensaje
						</figcaption>
					</figure>
	
					<footer>
						<p>comentarios (0)</p>
					</footer>
				</article>
				
				<article>
					<header>
						<hgroup>
							<h1>Título del mensaje dos</h1>
							<h2>Subtítulo del mensaje dos</h2>
							
						</hgroup>
						<time datetime="2011-10-12" pubdate>publicado 12-10-2011</time>
					</header>
					Este es el texto de mi segundo mensaje
					<span>esto es una cita del libro <cite>El niño con el pijama de Rayas</cite></span>
					<footer>
						<p>comentarios (0)</p>
					</footer>
				</article>
	
			</section>
			
			<aside id="columna">
				barra lateral
			</aside>
			
			<footer id="pie">
				<small>Derechos Reservados &copy; 2010-2011</small>
				<address>
					<a href="http://www.jdgauchat.com">JD Gauchat</a>
				</address>
			</footer>
		</div>
	</body>

</html>