function iniciar() {
	cajadatos = document.getElementById('cajadatos');
	cajadatos.addEventListener('dragenter', function(e) {
		e.preventDefault();
	}, false);
	cajadatos.addEventListener('dragover', function(e) {
		e.preventDefault();
	}, false);
	cajadatos.addEventListener('drop', soltado, false);
}
function soltado(e) {
	e.preventDefault();
	var archivos = e.dataTransfer.files;
	if (archivos.length) {
		var lista = '';
		for ( var f = 0; f < archivos.length; f++) {
			var archivo = archivos[f];
			lista += '<blockquote>Archivo: ' + archivo.name;
			lista += '<br><span><progress value="0" max="100">0%</progress></span>';
			lista += '</blockquote>';
		}
		cajadatos.innerHTML = lista;
		var cuenta = 0;
		var subir = function() {
			var archivo = archivos[cuenta];
			var datos = new FormData();
			datos.append('archivo', archivo);
			var url = "procesar.php";
			var solicitud = new XMLHttpRequest();
			var xmlupload = solicitud.upload;
			xmlupload.addEventListener('progress', function(e) {
				if (e.lengthComputable) {
					var hijo = cuenta + 1;
					var por = parseInt(e.loaded / e.total * 100);
					var barraprogreso = cajadatos
							.querySelector("blockquote:nth-child(" + hijo
									+ ") > span > progress");
					barraprogreso.value = por;
					barraprogreso.innerHTML = por + '%';
				}
			}, false);
			xmlupload.addEventListener('load', function() {
				var hijo = cuenta + 1;
				var elemento = cajadatos.querySelector("blockquote:nthchild("
						+ hijo + ") > span");
				elemento.innerHTML = 'Terminado!';
				cuenta++;
				if (cuenta < archivos.length) {
					subir();
				}
			}, false);
			solicitud.open("POST", url, true);
			solicitud.send(datos);
		}
		subir();
	}
}
window.addEventListener('load', iniciar, false);