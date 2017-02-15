
<html>
  
  <head>
    
    <title>TEST JSON</title>
  	<script type="text/javascript" src="js/jquery.js"></script>
  
  	<script>
  	 $(document).ready(function(){
  			/*getJSON.do?json=eeeeee*/

  			$('#getJSON').click(function() {
				alert('test JSON3');
				listaRep = {"temas":[{"id":"123123","nombre":"vino tinto","album":"estopa","artista":"estopa","duracion":"122","reproduciendo":"false"}],"nombreLista":"estopa - estopa"};
				$('#result').load('getJSON.do?json='+listaRep, function() {
				  alert('Load was performed.');
				});	
  	  		});
	 });
  	</script>
  
  </head>
  
  <body>
   
   	OK!!
   	
   	<a id="getJSON" href="#">test JSON!!</a>
   
   
   <div id="result">
   aquí el test
   </div>
  </body>

 </html>