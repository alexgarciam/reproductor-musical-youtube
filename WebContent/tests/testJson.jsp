<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="../js/jquery.js"></script>
 <script type="text/javascript">

		//JSON para una reproduccion de video de youtube
        $(document).ready(function(){ 		
           

            var listaRep = {
				    "nombre" : "milista",
				    "temas":[
				    	{
				    		"nombre" : 	"tu calorro",
				    		"artista": 	"Estopa",
				    		"album"	 : 	"Estopa",
				    		"id"     :  "4792430",
				    		"reproduciendo" : "false"
				    	},
				    	{
				    		"nombre" : 	"demonios",
				    		"artista": 	"Estopa",
				    		"album"	 : 	"Destrangis",
				    		"id"     :  "3658552",
				    		"reproduciendo" : "false"
				    	}
				    ]
				};
			
            $('#testrep').click(function(){
    		    alert('ok2');    	
    		    	    
    		    var temas = listaRep.temas;
    		    
    	    	for (var x = 0 ; x < temas.length ; x++) {
    		    	alert('nombre: '+temas[x].nombre + ' id: '+temas[x].id);
    	    	}
    		});

	    });


	    

</script>

</head>
<body>



<a href="#" id="testrep">ver</a>


</body>
</html>