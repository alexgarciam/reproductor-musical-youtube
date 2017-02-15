<!DOCTYPE html>
<html lang="es">
<head>
<title>Formularios</title>
<!-- API Forms (Manejo de errores) -->
<script>
	function iniciar() {
		edad = document.getElementById("miedad");
		edad.addEventListener("change", cambiarrango, false);
		document.informacion.addEventListener("invalid", validacion, true);
		document.getElementById("enviar").addEventListener("click", enviar,
				false);
		
		//hace la validaci�n en tiempo real, no al pulsar el bot�n
		document.informacion.addEventListener("input", controlar, false);
	}
	function cambiarrango() {
		var salida = document.getElementById("rango");
		var calc = edad.value - 20;
		if (calc < 20) {
			calc = 0;
			edad.value = 20;
		}
		salida.innerHTML = calc + ' a ' + edad.value;
	}
	function validacion(e) {
		var elemento = e.target;
		elemento.style.background = '#FFDDDD';
	}
	function enviar() {
		var valido = document.informacion.checkValidity();
		if (valido) {
			document.informacion.submit();
		}
	}

	function controlar(e) {
		var elemento = e.target;
		if (elemento.validity.valid) {
			elemento.style.background = '#FFFFFF';
		} else {
			elemento.style.background = '#FFDDDD';
		}
	}

	window.addEventListener("load", iniciar, false);
</script>
</head>
<body>
	<section>
		<form name="informacion" method="get">
			Usuario: <input pattern="[A-Za-z]{3,}" name="usuario" id="usuario"
				maxlength="10" required> Email: <input type="email"
				name="miemail" id="miemail" required> Rango de Edad: <input
				type="range" name="miedad" id="miedad" min="0" max="80" step="20"
				value="20">
			<output id="rango">0 a 20</output>
			<input type="submit" id="enviar" value="ingresar">
		</form>
	</section>
</body>
</html>