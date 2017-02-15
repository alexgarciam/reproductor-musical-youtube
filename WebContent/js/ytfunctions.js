 // functions for the api calls

		var ytplayer = document.getElementById("myytplayer");


		function loadTest(){
			//console.debug('loadTest');
			initiateSlider();
			ytplayer.loadVideoById('D2CsHodyN48',0,'medium');
		}


        function loadNewVideo(id, startSeconds) {        	
          if (ytplayer) {        	  
            ytplayer.loadVideoById(id, parseInt(startSeconds));
          }
        }
        
        function loadURLVideo(url, startSeconds) {        	
            if (ytplayer) {
              ytplayer.loadVideoByUrl("http://www.youtube.com/watch?v=ExY9ft1YHSg&feature=related", parseInt(0));
            }
          }

        function cueNewVideo(id, startSeconds) {        	 
          if (ytplayer) {        	 
            ytplayer.cueVideoById(id, startSeconds);
          }
        }

        function play() {
          if (ytplayer) {
            ytplayer.playVideo();
          }
        }

        function pause() {
          if (ytplayer) {
            ytplayer.pauseVideo();
          }
        }

        function stop() {
          if (ytplayer) {
            ytplayer.stopVideo();
          }
        }

        function getPlayerState() {
          if (ytplayer) {
            return ytplayer.getPlayerState();
          }
        }

        function seekTo(seconds) {
          if (ytplayer) {
            ytplayer.seekTo(seconds, true);
          }
        }

        function getBytesLoaded() {
          if (ytplayer) {
            return ytplayer.getVideoBytesLoaded();
          }
        }

        function getBytesTotal() {
          if (ytplayer) {
            return ytplayer.getVideoBytesTotal();
          }
        }

        function getCurrentTime() {    
          if (ytplayer) {
            return ytplayer.getCurrentTime();
          }
        }

        function getDuration() {
          if (ytplayer) {
            return ytplayer.getDuration();
          }
        }

        function getStartBytes() {
          if (ytplayer) {
            return ytplayer.getVideoStartBytes();
          }
        }

        function mute() {
          if (ytplayer) {
            ytplayer.mute();
          }
        }

        function unMute() {
          if (ytplayer) {
            ytplayer.unMute();
          }
        }
        
        function isMuted() {
		    if (ytplayer) {
		    	return   ytplayer.isMuted();
		    }
		}      
        
        function getEmbedCode() {
          alert(ytplayer.getVideoEmbedCode());
        }

        function getVideoUrl() {
          alert(ytplayer.getVideoUrl());
        }
        
        function setVolume(newVolume) {
          if (ytplayer) {
            ytplayer.setVolume(newVolume);
          }
        }

        function getVolume() {
          if (ytplayer) {
            return ytplayer.getVolume();
          }
        }

        function clearVideo() {
          if (ytplayer) {
            ytplayer.clearVideo();
          }
        }
        
        
        function setSize(width, height){
        	 if (ytplayer) {
        		 ytplayer.setSize(640,480);
        	 }
        }
        
        function fullsize(){
			var tam=TamVentana();
			$('#myytplayer').css('width', tam[0]-50);
			$('#myytplayer').css('height', tam[1]-100);
		}
	
	    function tamReproductor(width,heigth){	
	    
			$('#myytplayer').css('width', width);
			$('#myytplayer').css('height', heigth);
	        //myytplayer.setSize(tam[0],tam[1]);
	    }