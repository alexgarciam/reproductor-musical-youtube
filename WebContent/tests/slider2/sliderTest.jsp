<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <title>Slider test</title>
    <script type="text/javascript" src="prototype.js"></script>
    <script type="text/javascript" src="scriptaculous.js"></script>
    <script type="text/javascript">
    // <![CDATA[
        var hWidth, bWidth, slide;
        
        function setBgPos(v) {
            var off = v * hWidth;
            var pos = -bWidth + (v * bWidth);
            $('slider').setStyle({backgroundPosition: Math.round(pos - off) + 'px'});
        }
        
        function setSlideOutput(v) {
            $('percent').update(Math.round(v * 100) + '%');
        }
        
        Event.observe(window, 'load', function() {
            hWidth = $('slider-handle').getWidth();
            bWidth = $('slider-bar').getWidth();
            slide = new Control.Slider('slider-handle', 'slider-bar', {
                sliderValue: 0.25,
                onSlide: 
                    function(v) {
                        setBgPos(v);
                        setSlideOutput(v);
                    },
                onChange:
                    function(v) {
                        setBgPos(v);
                        setSlideOutput(v);
                    }
            });
            setBgPos(slide.value);
            setSlideOutput(slide.value);
            Event.observe('slider', 'mousewheel', function(e) {
                slide.setValueBy((Event.wheel(e)/20));
            });
        });
    // ]]>
    </script>
    <style type="text/css">
        @import url(slider.css);
        body {
            font-family: arial, verdana, sans-seif;
        }
    </style>
</head>

<body>

<p>Slider works with a click on the track, pulling the handle, or the mouse wheel.</p>
<div id="slider">
    <div id="slider-bar">
        <div id="slider-handle"><p id="percent"></p></div>
    </div>
</div>

<div id="debug"></div>

</body>
</html>