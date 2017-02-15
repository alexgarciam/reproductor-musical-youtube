<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>
      YouTube Chromeless Player Example page
    </title>
    <!-- Use the Google AJAX Libraries API:
        http://code.google.com/apis/ajaxlibs/ -->
    <script src="http://www.google.com/jsapi"></script>
    <script>
      google.load("swfobject", "2.1");
    </script>

    <style type="text/css">

    body {
      font-family: verdana, helvetica;
      background-color: white;
    }

    #timedisplay {
      border: solid 1px red;
      width: 50px;
    }
    </style>
    <script type="text/javascript">

        function updateHTML(elmId, value) {
          document.getElementById(elmId).innerHTML = value;
        }

        function setytplayerState(newState) {
          updateHTML("playerstate", newState);
        }

        function onYouTubePlayerReady(playerId) {
          ytplayer = document.getElementById("myytplayer");
          setInterval(updateytplayerInfo, 250);
          updateytplayerInfo();
          ytplayer.addEventListener("onStateChange", "onytplayerStateChange");
          ytplayer.addEventListener("onError", "onPlayerError");
        }

        function onytplayerStateChange(newState) {
          setytplayerState(newState);
        }

        function onPlayerError(errorCode) {
          alert("An error occured: " + errorCode);
        }

        function updateytplayerInfo() {
          updateHTML("bytesloaded", getBytesLoaded());
          updateHTML("bytestotal", getBytesTotal());
          updateHTML("videoduration", getDuration());
          updateHTML("videotime", getCurrentTime());
          updateHTML("startbytes", getStartBytes());
          updateHTML("volume", getVolume());
        }

        // functions for the api calls
        function loadNewVideo(id, startSeconds) {
          if (ytplayer) {
            ytplayer.loadVideoById(id, parseInt(startSeconds));
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
        

    </script>
  </head>
  <body id="page">
    <div>
      <p>Click "Load Video" or "Cue Video" below to start playing a video. <br />To play a different
      video, enter a YouTube video id and click "Load Video" or "Cue Video" again.</p>
    <!-- embed the player -->

    <div id="ytapiplayer">
      You need Flash player 8+ and JavaScript enabled to view this video.
    </div>
    <script type="text/javascript">
      // <![CDATA[

      // allowScriptAccess must be set to allow the Javascript from one 
      // domain to access the swf on the youtube domain
      var params = { allowScriptAccess: "always", bgcolor: "#cccccc" };
      // this sets the id of the object or embed tag to 'myytplayer'.
      // You then use this id to access the swf and make calls to the player's API
      var atts = { id: "myytplayer" };
      swfobject.embedSWF("http://www.youtube.com/apiplayer?enablejsapi=1&playerapiid=ytplayer", 
                         "ytapiplayer", "400", "300", "8", null, null, params, atts);
      //]]>
    </script>
    
    <!-- HTML below here is for display of the player info + state -->
    <div>
      Player state: <span id="playerstate">--</span>
    </div>

    <br />
      <a href="javascript:void(0);" onclick="play();">Play</a> | <a href="javascript:void(0);" onclick="pause();">Pause</a> | <a href="javascript:void(0);" onclick="stop();">Stop</a> | <a href="javascript:void(0);" onclick="mute();">Mute</a> | <a href="javascript:void(0);" onclick="unMute();">Unmute</a>

    <br /><br />
    <form action="" onsubmit="seekTo(document.getElementById('seekto').value); return false;">
      <div><input id="seekto" type="text" size="4" /><input type="submit" value="Seek to" /></div>
    </form>
    <br />
    <div>
      Duration: <span id="videoduration">--:--</span> | Current Time: <span id="videotime">--:--</span>

    </div>
    <br />
    <div id="bytesdisplay">
      Bytes Total: <span id="bytestotal">--</span> | Start Bytes: <span id="startbytes">--</span> | Bytes Loaded: <span id="bytesloaded">--</span>
    </div>

    <br />
    <div>
      <input type="text" size="11" id="loadvideoid" value="u1zgFlCw8Aw" />
      <a href="javascript:void(0)" onclick="loadNewVideo(document.getElementById('loadvideoid').value, document.getElementById('startseconds').value)">&lt;-
        Load video</a> | Start at: <input type="text" size="4" id="startseconds" value="0" />
    </div>
    <br />
    <div>

      <input id="vol" type="text" size="2" /> 
      <a href="javascript:void(0)" onclick="setVolume(document.getElementById('vol').value)">&lt;- Set Volume</a>  | Volume: <span id="volume">--</span>
    </div>
    <br />
    <div>
      <input type="text" size="11" id="cuevideoid" value="u1zgFlCw8Aw" />
      <a href="javascript:void(0)" onclick="cueNewVideo(document.getElementById('cuevideoid').value, document.getElementById('startseconds2').value)">&lt;- Cue video</a> | Start at: <input type="text" size="4" id="startseconds2" value="0" />

    </div>
    <br />
    <div>
        <a href="javascript:void(0)" onclick="getEmbedCode()">Get Embed Code</a> | <a href="javascript:void(0)" onclick="getVideoUrl()">Get Video URL</a> | <a href="javascript:void(0);" onclick="clearVideo()">Clear Video</a>  
    </div>
  </div>

  </body>
</html>
