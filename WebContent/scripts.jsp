 <!--
 <link rel="stylesheet" type="text/css" href="css/indexTest3.css" /> 
  -->

<link rel="stylesheet" type="text/css" href="css/indexTest4.css" /> 
<link rel="stylesheet" type="text/css" href="css/formSearch.css" />
<!-- <link rel="stylesheet" type="text/css" href="css/index5.css" />  --> 

<!-- pestañas (nuevo) -->
		
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	
	<!-- pestañas (nuevo) -->		
	<script type="text/javascript" src="js/tabs.js"></script>
	<link rel="stylesheet" href="css/tabs.css" type="text/css" media="screen" />
	<!-- pestañas (nuevo) -->
	
	
			<!-- Scroll pane -->
	<link type="text/css" href="css/jquery.jscrollpane.css" rel="stylesheet" media="all" />
	<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
			<!-- Scroll pane -->
	
	
		
	<!-- Funciones javascript donde esta todo el mogollon de codigo -->
	<script type="text/javascript" src="js/utiles.js"></script>
	<script type="text/javascript" src="js/inicializador.js"></script>  
	<script type="text/javascript" src="js/searchFunctions.js"></script>
	<script type="text/javascript" src="js/reproductionFunctions.js"></script>
	
	<link href="css/jquery.contextMenu.css" rel="stylesheet" type="text/css" />			
	<script src="js/jquery.contextMenu.js" type="text/javascript"></script>	 
	<script type="text/javascript" src="js/interfaz.js"></script>
	
	<script type="text/javascript" src="js/userAccount.js"></script>
	
								<!-- Login -->
	<link href="css/login.css" rel="stylesheet" type="text/css" />			
	<script src="js/login.js" type="text/javascript"></script>	 
								<!-- Login -->
	<!-- Funciones javascript donde esta todo el mogollon de codigo -->
	
	<!-- Estilos para las tablas -->
		<link href="css/tables/sputi_tables.css" rel="stylesheet" type="text/css" />
		<link href="css/tables/itunes.css" rel="stylesheet" type="text/css" />
	<!-- Stilos para las tablas -->		
		
	<!-- jquery ui Estilo de la pagina -->
	<!-- ORIGINAL <link type="text/css" href="css/jquery-ui.css" rel="stylesheet" /> -->	 
	<link id="stylesheet" type="text/css" href="css/ui-darkness/jquery-ui-1.8.16.custom.css" rel="stylesheet" />			
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<!-- jquery ui -->
		
	<!-- Tablas con datatable -->
	<script type="text/javascript" language="javascript" src="js/datatable/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/datatable/demo_table.css" />		
	<!-- Tablas con datatable -->	
	
	<!-- modal forms -->
	<script type="text/javascript" src="js/fancybox/jquery.fancybox.js?v=2.0.6"></script>
	<link rel="stylesheet" type="text/css" media="all" href="css/fancybox/style.css">
	<link rel="stylesheet" type="text/css" media="all" href="css/fancybox/jquery.fancybox.css">
	<!-- modal forms -->

		
	<!-- Para el reproductor de youtube -->
	<script src="http://www.google.com/jsapi"></script>	
    <script>
      google.load("swfobject", "2.1");
    </script>
	<!-- Para el reproductor de youtube -->
	
	
    <script type="text/javascript" src="js/ytfunctions.js"></script>
 	<script type="text/javascript">		
 	 function onYouTubePlayerReady(playerId) {
 		onYouTubePlayerReady2(playerId); 	 
	 }
	</script>
	
	<script>
    $(document).ready(function(){
		$("#fachada").hide();

		//tomamos la url 
		var tmpURL = window.location.href;
		if(tmpURL.indexOf("openTrack.do") != -1){
			<%
			Boolean openTrack=(Boolean)request.getSession().getAttribute("openTrack");
			if(openTrack!=null && openTrack)
			{
				%>					
					$("#fachada").load("music/trackUrl.jsp");
					$("#fachada").show();
				<%				
			}
		%>
    	}	
    });    
</script>