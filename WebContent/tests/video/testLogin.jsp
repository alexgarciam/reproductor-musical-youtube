<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../js/jquery.lightbox_me.js"></script>
	
	<script type="text/javascript">
	
	$('#testLogin').click(function(e) {
	    $('#sign_up').lightbox_me({
	        centered: true, 
	        onLoad: function() { 
	            $('#sign_up').find('input:first').focus()
	            }
	        });
	    e.preventDefault();
	});
	
	</script>
	
</head>
<body>
esto es un test de prueba de login!!

<a href="#" id="testLogin">Test Login</a>
</body>
</html>