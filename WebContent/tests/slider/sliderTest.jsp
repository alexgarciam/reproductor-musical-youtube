<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>Using Images to Spruce Up a Slider Demo</title>
		<script src="prototype.js" type="text/javascript"></script>
		<script src="slider.js" type="text/javascript"></script>
		<script type="text/javascript">

		  var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-247334-30']);
		  _gaq.push(['_trackPageview']);

		  (function() {
		    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  })();

		</script>
		<style type="text/css" media="screen" >
				
			/* put the left rounded edge on the track */
			#track1-left {
				position: absolute;
				width: 5px;
				height: 9px;
				background: transparent url(images/slider-images-track-left.png) no-repeat top left;
			}
			
			/* put the track and the right rounded edge on the track */
			#track1 {
				background: transparent url(images/slider-images-track-right.png) no-repeat top right;
			}

		</style>
	</head>
	<body>
		<h1>Using Images to Spruce Up a Slider</h1>

		<h2>Horizontal Slider Using Images</h2> 
		<div id="track1" style="width:200px; height:9px;">
			<div id="track1-left"></div><div id="handle1" style="width:19px; height:20px;"><img src="images/slider-images-handle.png" alt="" style="float: left;" /></div>
		</div>
		<p id="debug1">&nbsp;</p>

		<script type="text/javascript" language="javascript">
		// <![CDATA[
		
			// horizontal slider control
			new Control.Slider('handle1', 'track1', {
				onSlide: function(v) { $('debug1').innerHTML = 'slide: ' + v },
				onChange: function(v) { $('debug1').innerHTML = 'changed: ' + v }
			});

		// ]]>
		</script>
	
		<p><a href="index.html">Back to the main demo page</a></p>

		<script type="text/javascript" charset="utf-8">
			var is_ssl = ("https:" == document.location.protocol);
			var asset_host = is_ssl ? "https://s3.amazonaws.com/getsatisfaction.com/" : "http://s3.amazonaws.com/getsatisfaction.com/";
			document.write(unescape("%3Cscript src='" + asset_host + "javascripts/feedback-v2.js' type='text/javascript'%3E%3C/script%3E"));
		</script>
		<script type="text/javascript" charset="utf-8">
			var feedback_widget_options = {};
			feedback_widget_options.display = "overlay";  
			feedback_widget_options.company = "aldenta";
			feedback_widget_options.placement = "right";
			feedback_widget_options.color = "#222";
			feedback_widget_options.style = "idea";
			var feedback_widget = new GSFN.feedback_widget(feedback_widget_options);
		</script>
	</body>
</html>
