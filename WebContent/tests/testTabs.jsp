<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="es-ES"> 
<head>  
    <link type="text/css" href="main.css" rel="stylesheet" />	
    <link type="text/css" href="../css/jquery-ui.css" rel="stylesheet" />	
    <script>
    $(document).ready(function() {  
        
        //Cuando el sitio carga...  
          
        $(".tab_content").hide(); //Esconde todo el contenido  
          
        $("ul.tabs li:first").addClass("active").show(); //Activa la primera tab  
          
        $(".tab_content:first").show(); //Muestra el contenido de la primera tab  
          
        //On Click Event  
          
        $("ul.tabs li").click(function() {  
          
        $("ul.tabs li").removeClass("active"); //Elimina las clases activas  
          
        $(this).addClass("active"); //Agrega la clase activa a la tab seleccionada  
          
        $(".tab_content").hide(); //Esconde todo el contenido de la tab  
          
        var activeTab = $(this).find("a").attr("href"); //Encuentra el valor del atributo href para identificar la tab activa + el contenido  
          
        $(activeTab).fadeIn(); //Agrega efecto de transición (fade) en el contenido activo  
          
        return false;  
          
        });  
          
        });  
    </script>    
</head>  
<body>  
    <ul class="tabs">  
        <li><a href="#tab1">Gallery</a></li>  
        <li><a href="#tab2">Submit</a></li>  
    </ul>  
      
    <div class="tab_container">  
        <div id="tab1" class="tab_content">  
            <!--Contenido del bloque de texto-->  
            
            sdfasdf
        </div>  
        <div id="tab2" class="tab_content">  
           <!--Contenido del bloque de texto-->  
           
           asdfasdf
        </div>  
    </div>    
</body>  
</html>  