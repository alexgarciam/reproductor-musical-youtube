<!DOCTYPE html>
<html lang="es">
<head>
<title>Formularios</title>

<title>Formularios</title>

<!-- API Para formularios -->
<script>
	function iniciar() {
		nombre1 = document.getElementById("nombre");
		nombre2 = document.getElementById("apellido");
		nombre1.addEventListener("input", validacion, false);
		nombre2.addEventListener("input", validacion, false);
		validacion();
	}
	function validacion() {
		if (nombre1.value == '' && nombre2.value == '') {
			nombre1.setCustomValidity('inserte al menos un nombre');
			nombre1.style.background = '#FFDDDD';
		} else {
			nombre1.setCustomValidity('');
			nombre1.style.background = '#FFFFFF';
		}
	}
	window.addEventListener("load", iniciar, false);
</script>

</head>
<body>
	<section>
		<form name="miformulario" id="miformulario" method="get">
			<input type="text" name="nombre" id="nombre" required>
			<input type="text" name="apellido" id="apellido" required>  
			<input type="email" name="miemail1" id="miemail1" required>
			<input type="submit" value="Enviar"> 
			<input type="email" name="miemail" id="miemail" multiple autofocus> 
			
			<input type="date" name="date1" id="date1">
			<!-- <input type="week" name="semana" id="semana"> -->
			<input type="search" name="busqueda" id="busqueda" placeholder="escriba su búsqueda"> 
			<input type="number" name="numero" id="numero" min='0' max='10' step='5'> 
			<input type="range" name="numero" id="numero" min='0' max='10' step='5'>
			<input type="color" name="micolor" id="micolor"> <br />

			<!-- Datos para un autocompletador -->
			<datalist id="informacion">
				<option value='123123123' label='Teléfono1'>
				<option value='456456456' label='Teléfono2'>
			</datalist>
			<input type="tel" name="telefono" id="telefono" list="informacion">

		</form>
	</section>
</body>
</html>