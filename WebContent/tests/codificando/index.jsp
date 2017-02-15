<%@page import="java.net.URLEncoder" %>
<%@page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<script>	
	var test1="Hello I'm Alex García Maño";
	var test2='<%= URLEncoder.encode("Hello I'm Alex García Maño","UTF-8") %>';
	alert("2: "+test2);	
	alert("normal: "+test2);
	alert("decodeURI: "+decodeURI(test2));
	
	
	alert("encode: "+encodeURI(test1));
	</script>

</head>
<body>


</body>
</html>