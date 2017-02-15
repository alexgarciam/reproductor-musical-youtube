<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/swfobject.js"></script>

	<script type="text/javascript">
		var params = { allowScriptAccess: "always", wmode: "transparent" };
		var atts = { id: "myytplayer" };
		swfobject.embedSWF("http://www.youtube.com/v/Zhawgd0REhA?enablejsapi=1&playerapiid=ytplayer", 
		                   "ytapiplayer", "250", "250", "8", null, null, params, atts);	

		var ytplayer;
		 function onYouTubePlayerReady(playerId) {
			  ytplayer = document.getElementById("myytplayer");
		      ytplayer.addEventListener("onStateChange", "onytplayerStateChange");
	          ytplayer.addEventListener("onError", "onPlayerError");
		      setInterval(updateytplayerInfo, 250);
	          updateytplayerInfo();
	          var s=ytplayer.getDuration();
		    }

		 function updateytplayerInfo() {        	
			 $( "#amount" ).val( "eeeee");
	        }   

		 function onytplayerStateChange(newState) {
	         
	        }

		 $(document).ready(function(){
		   // alert('test5');	
			$( "#ocultar").click(function(){	    			
				 $( "#capaOcultacion").animate({
					    width: "600px",
					    fontSize: "3em",
					    borderWidth: "10px"
					  }, 1500 );
	    	});	    
			$( "#mostrar").click(function(){	    			
				 $( "#capaOcultacion").hide();
	    	});
		 });	           
	</script>
	
	<style>
	#capaOcultacion{
		text-align:center;
		position:absolute;
		background-color:gray;
		left:0px;
		width:0px;
		height:350px;
		background-image:url('../images/texturas/textura (15).jpg');
		background-repeat: repeat;
		z-index: 2;
	}
	</style>
</head>

<body>
	<div style="position: relative" align="center">
		<DIV id="capaOcultacion">			
		</DIV>
	</div>
	
	<div id="ytapiplayer" style="z-index: 1;">
	    You need Flash player 8+ and JavaScript enabled to view this video. NOW!
	</div>	
	
	<br />
	 <a href="javascript:ytplayer.playVideo()"> Play</a> | 
	 <a href="javascript:ytplayer.pauseVideo()"> Pause</a> | 
	 <a href="javascript:ytplayer.stopVideo()"> Stop</a> | 
	 
	 <br />
	
	 <a href="javascript:ytplayer.loadVideoById('D2CsHodyN48',0,'medium')">Load Video</a> | 
	 <a href="javascript:ytplayer.cueVideoById('D2CsHodyN48',0,'medium')">Poner video en cola </a> | 
	 <a href="javascript:ytplayer.seekTo(55,true)">ir al segundo 55 </a>
	 <div id="amount">
	 test - 
	 </div>


<br />
	
	 <a href="javascript:ytplayer.mute()">Mute video</a> | 
	 <a href="javascript:ytplayer.unMute()">Unmute video</a> | 
	 <a href="javascript:alert(ytplayer.isMuted());">is muted?</a> |
	 <a href="javascript:ytplayer.setVolume(50);">volumen al 50%</a> |  
	 <a href="javascript:alert(ytplayer.getVolume());">volumen?</a> |
	 
<br />
	 <a href="javascript:ytplayer.setSize(640,480)">Tamaño a 640x480</a> | 

<br />
	 <a href="#" id="ocultar">Ocultar video</a> |		
		 <a href="#" id="mostrar">mostrar video</a> |	
	
</body>
</html>