<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "DTD/xhtml1-transitional.dtd">
<html>
<head>
        <title>frequency decoder - accessible, unobtrusive slider demo</title>
        <meta http-equiv="Content-Type"         content="text/html; charset=iso-8859-1" />
        <meta name="author"                     content="frequency decoder" />
        <meta name="description"                content="An unobtrusive javascript that turns any text input or selectlist into a keyboard-accessible slider control" />
        <meta http-equiv="imagetoolbar"         content="no" />
        <link rel="stylesheet" type="text/css" media="screen, projection" href="slider.css" />
        <link rel="Shortcut Icon" href="/favicon.ico" type="image/x-icon" />
<style type="text/css" media="screen">
/* Generic CSS used for the demo */
body
        {
        background:#fff;
        color:#333;
        text-align:center;
        font-size:11px;
        font-family: verdana,arial,sans-serif;
        color:#545454;
        padding:0;
        margin:0;
        border:0;
        line-height:1.4;
        }
h1
        {
        font-weight:lighter;
        font-family:georgia, times new roman, times, georgia, palatino, serif;
        text-align:center;
        margin-top:0.6em;
        color:#111;
        font-size:2em;
        }
h2
        {
        font-weight:lighter;
        font-family:verdana,arial,sans-serif;
        text-align:center;
        margin:1em 0;
        color:#333;
        text-transform:uppercase;
        letter-spacing:1px;
        font-size:1.2em;
        }
kbd
        {
        background-color:#eeeeee;
        padding:2px;
        border:1px solid #dddddd;
        border-bottom-color:#999999;
        border-left-color:#999999;
        }
kbd, code
        {
        font-family:'andale mono','lucida console','courier new',monospace;
        font-size:1em;
        }
p
        {
        line-height:1.6em;
        margin:0 0 1.6em 0;
        text-align:left;
        }
#article-wrapper
        {
        text-align:center;
        border:3px double #ccc;
        background:#fcfcfc;
        width:60%;
        margin:2em auto;
        padding:1em;
        }
fieldset
        {
        margin:0;
        padding:1em;
        text-align:left;
        }
form > fieldset
        {
        border:1px solid #ccc;
        }
form
        {
        margin:0 auto 1.4em 0;
        padding:0;
        text-align:center;
        margin:0 auto;
        }
input
        {
        border:1px solid #ccc;
        border-top:1px solid #888;
        border-left:1px solid #888;
        width:2em;
        margin-left:0.2em;
        }
label
        {
        display:block;
        padding:0;
        margin-right:0.2em;
        text-align:right;
        font-style:oblique;
        font-weight:bold;
        color:#5c5c5c;
        }
code
        {
        font-family:'andale mono', 'lucida console', 'courier new', monospace;
        font-size:12px;
        color:#222;
        }
a
        {
        font-weight:normal;
        outline:none;
        }
a:link,
a:visited
        {
        color:#333;
        text-decoration:underline;
        }
a:hover
        {
        color:#fff;
        text-decoration:none;
        background:#000;
        }
a:active
        {
        color:#000;
        text-decoration:underline;
        }
#tooltip
        {
        position:absolute;
        top:0;
        left:0;
        width:100px;
        height:25px;
        line-height:25px;
        background:transparent url(../tooltip-dongle.png) no-repeat 50% 100%;
        padding-bottom:5px;
        }
#tooltipInner1
        {
        padding-left:5px;
        overflow:hidden;
        color:#fff;
        background:transparent url(../tooltipleft.png) no-repeat 0 0;
        }
#tooltipInner2
        {
        padding:0 5px 0 0;
        height:22px;
        line-height:22px;
        overflow:hidden;
        color:#fff;
        text-align:center;
        background:transparent url(../tooltip.png) no-repeat 100% 0;
        }
</style>
<!--[if lte IE 6]>
<link rel="stylesheet" type="text/css" media="screen, projection" href="../ie.css" />
<style type="text/css">
/* Basic styles for non png aware Internet Explorer 6 */
#tooltip
        {
        background-image:url(../tooltip-dongle.gif);
        }
#tooltipInner1
        {
        background-image:none;
        padding:0;
        }
#tooltipInner2
        {
        background-image:none;
        background:#000;
        padding:0;
        }
</style>
<![endif]-->
<script type="text/javascript" src="slider.js"></script>
<script type="text/javascript">
//<![CDATA[

// Preloading the images used for the slider handle
var imgList = ["slider-disabled.png", "slider-disabled-1.png", "slider.png", "slider-1.png"];
var preloadImg = []
for(var i = 0, imgSrc; imgSrc = imgList[i]; i++) {
        preloadImg[i] = new Image();
        preloadImg[i].src = "http://www.frequency-decoder.com/demo/slider-revisited/" + imgSrc;
};

// Remember folks, none of this JavaScript is actually necessary, it's just there to show you
// how to extend the slider functionality with callback functions/Object.methods

// The tooltip object
var TT = {
        tooltip:null,
        tooltipInner:null,
        handle:null,
        init:function(e) {
                // Construct the slider
                fdSliderController.construct();
                
                // Grab a reference to the slider handle
                var handle = document.getElementById("fd-slider-handle-selectTest1");

                // If something has gone wrong then bail out...
                if(!handle) { return; };

                // Create the tooltip...
                var tt = document.createElement("div");
                tt.id = "tooltip";
                        
                var TTinner1 = document.createElement("div");
                TTinner1.id = "tooltipInner1";

                var TTinner2 = document.createElement("div");
                TTinner2.id = "tooltipInner2";

                TTinner1.appendChild(TTinner2);
                tt.appendChild(TTinner1);
                        
                document.body.appendChild(tt);

                // Cache a reference to the tooltip, tooltip inner & slider handle
                TT.tooltip      = tt;
                TT.tooltipInner = TTinner2;
                TT.handle       = handle;
                
                // Add the required events...
                
                // Show the tooltip when the slider is focused
                fdSliderController.addEvent(handle, "focus",   TT.show);
                
                // Hide the tooltip when the slider is blurred
                fdSliderController.addEvent(handle, "blur",    TT.hide);
                
                // Reposition the tooltip when the page is resized
                fdSliderController.addEvent(window, "resize",  TT.update);

                /*@cc_on@*/
                /*@if(@_win32)
                setTimeout(fdSliderController.onload, 200);
                /*@end@*/
                
                // Position & hide
                TT.update();
                TT.tooltip.style.display = "none";
                TT.tooltip.style.visibility = "hidden";
        },
        // A function that positions the tooltip and updates it's value
        // This is also used as the callback function for the slider
        update:function(e) {
                TT.show();
                
                var curleft = 0;
                var curtop  = 0;
                var obj     = TT.handle;
                var osw     = obj.offsetWidth;
                
                // Try catch for IE's benefit
                try {
                        while (obj.offsetParent) {
                                curleft += obj.offsetLeft;
                                curtop  += obj.offsetTop;
                                obj      = obj.offsetParent;
                        };
                } catch(err) {};

                TT.tooltip.style.left = Math.round((curleft - ((TT.tooltip.offsetWidth - osw) / 2))) + "px";
                TT.tooltip.style.top = (curtop - 30)  + "px";

                while(TT.tooltipInner.firstChild) TT.tooltipInner.removeChild(TT.tooltipInner.firstChild);

                var txt = document.getElementById("selectTest1");
                txt = txt.options[txt.selectedIndex].text;

                TT.tooltipInner.appendChild(document.createTextNode(txt));
        },
        show:function(e) {
                e = e || window.event;
                if(e && e.type && e.type != "focus") return;
                
                TT.tooltip.style.display = "block";
                TT.tooltip.style.visibility = "visible";
        },
        hide:function(e) {
                e = e || window.event;
                if(e && e.type && e.type != "blur") return;

                TT.tooltip.style.display = "none";
                TT.tooltip.style.visibility = "hidden";
        }
};

// Removing the onload event bundled with the slider...
fdSliderController.removeEvent(window, "load",   fdSliderController.construct);

// Removing an Internet Explorer specific event bundled with the slider...
/*@cc_on@*/
/*@if(@_win32)
fdSliderController.removeEvent(window, "load",   function() { setTimeout(fdSliderController.onload, 200) });
/*@end@*/

// Add a new onload event that creates the tooltip and the slider
fdSliderController.addEvent(window, "load",   TT.init);
//]]>
</script>
</head>
<body>
<div id="article-wrapper">
  <form action="" method="post">
    <h1>Accessible Unobtrusive Slider Demo</h1>
    <h2>Accessibility Enhancements (<abbr title="Accessible Rich Internet Applications">ARIA</abbr>)</h2>
    <!-- The following paragraph is used to set the ARIA describedby property -->
    <p id="fd_slider_describedby">Whenever a slider has focus, the arrow keys <kbd title="Left arrow key">&larr;</kbd>, <kbd title="Right arrow key">&rarr;</kbd>, <kbd title="Up arrow key">&uarr;</kbd> and <kbd title="Down arrow key">&darr;</kbd> can be used to control the slider handle, the <kbd title="Home key">Home</kbd> key to set the slider at it&#8217;s minimum value and the <kbd title="End key">End</kbd> key to set the slider at it&#8217;s maximum value.</p>
    <p>The script automatically assigns each slider the required ARIA role of &#8220;slider&#8221; and states &#8220;valuemax&#8221;, &#8220;valuemin&#8221; and &#8220;valuenow&#8221;.</p>
    <p>Should an element with an id of <code>fd_slider_describedby</code> exist, this is used to set the ARIA &#8220;describedby&#8220; relationship. Additionally, should the sliders associated form element itself have an associated <code>label</code>, this label is used to set the ARIA &#8220;labelledby&#8221; relationship.</p>
    <p>View the <a href="./slider.js">JavaScript source</a> or read the <a href="http://www.frequency-decoder.com/2007/09/10/unobtrusive-slider-control-revisited">related article</a>.</p>
    <h2>SelectList demo with tooltip</h2>
    <fieldset>
      <p>The following slider has been created from a select list whose options contain non-numeric values. A simple callback function has been defined that updates a dynamically created tooltip with the current slider value.</p>
      <p><code>class="fd_slider fd_callback_TT-update"</code></p>
      <label for="selectTest1" id="selectTest1Label">Range "lowest" to "highest"</label>
      <select name="selectTest1" id="selectTest1" class="fd_slider fd_callback_TT-update">
        <optgroup label="low ranges">
          <option value="-3">lowest</option>
          <option value="-2">lower</option>
          <option value="-1">low</option>
        </optgroup>
        <option value="0" selected="selected">normal</option>
        <optgroup label="high ranges">
          <option value="1">high</option>
          <option value="2">higher</option>
          <option value="3">highest</option>
        </optgroup>
      </select>
    </fieldset>

    <h2>Don&#8217;t forget folks</h2>
    <p>The rather nice png images and styles for the black sliders were located at <a href="http://www.schillmania.com/">schillmania</a>. Go visit&#8230;</p>
  </form>
</div>
</body>
</html>
