var meses; 
	meses=new Array(12);
	meses[0]='Enero';
	meses[1]='Febrero';
	meses[2]='Marzo';
	meses[3]='Abril';
	meses[4]='Mayo';
	meses[5]='Junio';
	meses[6]='Julio';
	meses[7]='Agosto';
	meses[8]='Septiembre';
	meses[9]='Octubre';
	meses[10]='Noviembre';
	meses[11]='Diciembre';
	
var dias = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves",
			 "Viernes", "Sabado"); 
function getFecha()
{
	Stamp = new Date();
	year = Stamp.getFullYear();    	    	
	var Hours;
	var Mins;
	var Time;
	    	
	Hours = Stamp.getHours();
	if (Hours >= 12) {
		Time = " PM";
	}
	else {
		Time = " AM";
	}
	if (Hours > 12) {
		Hours -= 12;
	}
	if (Hours == 0) {
		Hours = 12;
	}
	Mins = Stamp.getMinutes();
	if (Mins < 10) {
		Mins = "0" + Mins;
	}
	
	if (year < 2000) year = 1900 + year;
	var fechora='<font  style="color: white" size="2" face="Arial"><B>' + dias[Stamp.getDay()] +" "+ Stamp.getDate() + " de " + meses[Stamp.getMonth()] + " de " +year +" - " + Hours + ":" + Mins + Time + '</B></font>';
	return fechora;
}
