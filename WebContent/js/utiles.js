/**
 * Funciones de utilidad javascript
 * 
 * Funcion que elimina los espacios en blanco por delante y por detras en un
 * String
 * 
 * @param myString
 * @return
 */

function trim(myString) {
	return myString.replace(/^\s+/g, '').replace(/\s+$/g, '');
}

/**
 * Funcion que devuelve un array con dos elementos, ancho y alto del tamaño de
 * la ventana del navegador
 * 
 * @return
 */
function TamVentana() {
	var Tamanyo = [ 0, 0 ];
	if (typeof window.innerWidth != 'undefined') {
		Tamanyo = [ window.innerWidth, window.innerHeight ];
	} else if (typeof document.documentElement != 'undefined'
			&& typeof document.documentElement.clientWidth != 'undefined'
			&& document.documentElement.clientWidth != 0) {
		Tamanyo = [ document.documentElement.clientWidth,
				document.documentElement.clientHeight ];
	} else {
		Tamanyo = [ document.getElementsByTagName('body')[0].clientWidth,
				document.getElementsByTagName('body')[0].clientHeight ];
	}
	return Tamanyo;
}

/**
 * Funcion que sustituye todas las ocurrencias pasada como parametros por el
 * otro parametro
 * 
 * @param text
 * @param busca
 * @param reemplaza
 * @return
 */
function replaceAll(text, busca, reemplaza) {
	while (text.toString().indexOf(busca) != -1)
		text = text.toString().replace(busca, reemplaza);
	return text;
}

/**
 * Funcion que redimensiona la aplicacion al tamaño de la pantalla
 * 
 * @return
 */

function resize() {

	// var Tamanyo = TamVentana();
	var Tamanyo = [ 0, 0 ];
	if (typeof window.innerWidth != 'undefined') {
		Tamanyo = [ window.innerWidth, window.innerHeight ];
	} else if (typeof document.documentElement != 'undefined'
			&& typeof document.documentElement.clientWidth != 'undefined'
			&& document.documentElement.clientWidth != 0) {
		Tamanyo = [ document.documentElement.clientWidth,
				document.documentElement.clientHeight ];
	} else {
		Tamanyo = [ document.getElementsByTagName('body')[0].clientWidth,
				document.getElementsByTagName('body')[0].clientHeight ];
	}

	// 30: cabecera
	// 67: controles
	// 40: pié
	// 54: del alto de las imagenes superior e inferior del panel lateral
	$("#panelLateral").height(Tamanyo[1] - 30 - 67 - 40 - 54);
	
	$("#div_tab_login_account").height(Tamanyo[1] - 30 - 67 - 40);

	// ajustamos el tamaño de las ventanas automaticamente
	$("#searchresults").height(Tamanyo[1] - 30 - 67 - 40 - 10);
	$("#actualRepro").height(Tamanyo[1] - 30 - 67 - 40 - 10);

	var size=$("#panelLateral").height();
	//alert("panel lateral: "+size);
	
	if(size>400){
		$("#scrollbar1").height($("#panelLateral").height()-250 - 150);
		$(".viewport").height($("#panelLateral").height()-250 - 150);
		$('#scrollbar1').tinyscrollbar({ size: $("#panelLateral").height()-250-150});
	}
	else{
		//alert('demasiado pequeño para el reproductor!! --> lo escondemos');
		
		$("#scrollbar1").height($("#panelLateral").height()-250);
		$(".viewport").height($("#panelLateral").height()-250);
		$('#scrollbar1').tinyscrollbar({ size: $("#panelLateral").height()-250 });			
				
		$("#padreOcultacion").width(1);
		$("#padreOcultacion").height(1);	
		
		$("#capaOcultacion").width(1);
		$("#capaOcultacion").height(1);
		
		$("#imageAlbum").width(150);
		$("#imageAlbum").height(150);
		$("#imageAlbum").css('padding-left',50);
		
		$("#videoClip").css('position','absolute');
		$("#videoClip").css('left',-300);
		
		$("#radio").css('position','absolute');
		$("#radio").css('left',-300);
		
	}
		
}

function segundos_a_minutos(seg) {
	var min = Math.round(Math.floor(seg / 60));
	seg = seg % 60;
	seg = Math.round(seg);
	if (seg < 10)
		seg = "0" + seg;
	return min + ":" + seg;
}

function duracionEnhorasMinutosYsegundos(seg) {
	var min = Math.round(Math.floor(seg / 60));
	seg = seg % 60;
	seg = Math.round(seg);
	if (seg < 10)
		seg = "0" + seg;

	if (min > 59) {
		var hora = Math.round(Math.floor(min / 60));
		min = min % 60;
		min = Math.round(min);
		if (min < 10)
			min = "0" + min;
		return hora + " horas y " + min + " minutos";
	} else {
		return min + " minutos y " + seg + " segundos";
	}

}

function generarAleatorio(inferior, superior) {
	numPosibilidades = superior - inferior;
	aleat = Math.random() * numPosibilidades;
	aleat = Math.round(aleat);
	return parseInt(inferior) + aleat;
}


function closeSuggestions(){	
  	$( "#suggestions" ).hide();	
}

function openSuggestions(){
	$( "#suggestions" ).show();	
}


function quitaAcentos(str) {
	str=str.toLowerCase();
	for ( var i = 0; i < str.length; i++) {
		// Sustituye "á é í ó ú"
		if (str.charAt(i) == "á")
			str = str.replace(/á/, "a");
		if (str.charAt(i) == "é")
			str = str.replace(/é/, "e");
		if (str.charAt(i) == "í")
			str = str.replace(/í/, "i");
		if (str.charAt(i) == "ó")
			str = str.replace(/ó/, "o");
		if (str.charAt(i) == "ú")
			str = str.replace(/ú/, "u");
	}
	return str;
}
