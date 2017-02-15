<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<!-- styles needed by jScrollPane -->
	<link type="text/css" href="jquery.jscrollpane.css" rel="stylesheet" media="all" />
	
	<!-- latest jQuery direct from google's CDN -->
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js">
	</script>
	
	<!-- the mousewheel plugin - optional to provide mousewheel support -->
	<script type="text/javascript" src="jquery.mousewheel.js"></script>
	
	<!-- the jScrollPane script -->
	<script type="text/javascript" src="jquery.jscrollpane.min.js"></script>
	
	<script>
	$(function()
			{
				$('#scroll-pane').jScrollPane();
			});
	</script>
	
	<style>
	#scroll-pane
{
	width: 200px;
	height: 200px;
	overflow: auto;
}
.horizontal-only
{
	color:blue;
	height: auto;
	max-height: 200px;
}
	
	</style>

</head>
<body>

	<div id="scroll-pane" class="horizontal-only">
	<ul>
		<li>
		listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
		<li>listas</li>
	</ul>	
	</div>
</body>
</html>