/**
 * createIT main javascript file.
 */

var $devicewidth = (window.innerWidth > 0) ? window.innerWidth : screen.width;
var $deviceheight = (window.innerHeight > 0) ? window.innerHeight : screen.height;
var $bodyel = jQuery("body");
var $navbarel = jQuery(".navbar");

/* ========================== */
/* ==== HELPER FUNCTIONS ==== */

function validatedata($attr, $defaultValue) {
    "use strict";
    if ($attr !== undefined) {
        return $attr
    }
    return $defaultValue;
}

function parseBoolean(str, $defaultValue) {
    "use strict";
    if (str == 'true') {
        return true;
    } else if (str == "false") {
        return false;
    }
    return $defaultValue;
}

var logoswitch = function(){
    if (( ($devicewidth < 1200) )){
        $('#ct-js-logo').attr('src',$logo.src)
    }
    else {
        $('#ct-js-logo').attr('src', $logotext.src)
    }
};


(function ($) {
    "use strict";
    if(document.getElementById('ct-js-wrapper')){
        var snapper = new Snap({
            element: document.getElementById('ct-js-wrapper')
        });

        snapper.settings({
            disable: "left",
            addBodyClasses: true
        });
    }

    $(document).ready(function () {

        logoswitch();

        // Navbar Inverse --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        if (jQuery('.navbar-inverse, .navbar-success, .navbar-info, .navbar-warning, .navbar-danger, .navbar-transparent').length > 0) {
            $('#ct-js-logo').attr('src',$logotext2.src)
        }

        // Add Color // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        $(".ct-js-color").each(function(){
            $(this).css("color", '#' + $(this).attr("data-color"))
        });


        // Flexslider height ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        var $mediaSection = $(".ct-mediaSection");
        if($mediaSection.length > 0)
        {
            $mediaSection.each(function()
            {
                if( $(this).attr("data-height") == "100%")
                {
                    $(this).find(".flexslider").css("height",  $deviceheight + "px");
                }
            });
        }

        // Snap Navigation in Mobile // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if ($devicewidth > 767 && document.getElementById('ct-js-wrapper')) {
            snapper.disable();
        }

        $(".navbar-toggle").click(function () {
            if($bodyel.hasClass('snapjs-right')){
                snapper.close();
            } else{
                snapper.open('right');
            }
        });


        $('.ct-menuMobile .ct-menuMobile-navbar .dropdown > a').click(function(e) {
            return false; // iOS SUCKS
        });
        $('.ct-menuMobile .ct-menuMobile-navbar .dropdown > a').click(function(e){
            var $this = $(this);
            if($this.parent().hasClass('open')){
                $(this).parent().removeClass('open');
            } else{
                $('.ct-menuMobile .ct-menuMobile-navbar .dropdown.open').toggleClass('open');
                $(this).parent().addClass('open');
            }
        });

        // Animations Init // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if ($().appear) {
            if (device.mobile() || device.tablet()) {
                $("body").removeClass("cssAnimate");
            } else {
                $('.cssAnimate .animated').appear(function () {
                    var $this = $(this);
                    $this.each(function () {
                        if ($this.data('time') != undefined) {
                            setTimeout(function () {
                                $this.addClass('activate');
                                $this.addClass($this.data('fx'));
                            }, $this.data('time'));
                        } else {
                            $this.addClass('activate');
                            $this.addClass($this.data('fx'));
                        }
                    });
                }, {accX: 50, accY: -350});
            }
        }

        // Tooltips and Popovers // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        $("[data-toggle='tooltip']").tooltip();

        $("[data-toggle='popover']").popover({trigger: "click", html: true});

        // Link Scroll to Section // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        $('.ct-js-btnScroll[href^="#"]').click(function (e) {
            e.preventDefault();

            var target = this.hash, $target = $(target);

            $('html, body').stop().animate({
                'scrollTop': $target.offset().top - 70
            }, 900, 'swing', function () {
                window.location.hash = target;
            });
        });
        $('.ct-js-btnScrollUp').click(function (e) {
            e.preventDefault();
            $("body,html").animate({scrollTop: 0}, 1200);
            return false;
        });



        // Navbar Search // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        var $searchform = $(".ct-navbar-search");
        $('#ct-js-navSearch').click(function(e){
            e.preventDefault();

            $(this).toggleClass('is-active');
            $searchform.fadeToggle(250, function () {
                if (($searchform).is(":visible")) {
                    $searchform.find("[type=text]").focus();
                }
            });
            return false;
        });

        // Placeholder Fallback // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if ($().placeholder) {
            $("input[placeholder],textarea[placeholder]").placeholder();
        }
    });

    $(window).load(function(){
        // Masonry For Sidebar // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if (jQuery().masonry  && (jQuery(window).width()<992) && (jQuery(window).width()>767)) {

            jQuery('.ct-js-sidebar .row').masonry({
                itemSelector: '.col-sm-6.col-md-12',
                layoutMode: 'sloppyMasonry',
                resizable: false, // disable normal resizing
                // set columnWidth to a percentage of container width
                masonry: { }
            });
        }
    });

    $(window).scroll(function(){
        var scroll = $(window).scrollTop();

        if (scroll > 600) {
            jQuery('.ct-js-btnScrollUp').addClass('is-active');
        } else {
            jQuery('.ct-js-btnScrollUp').removeClass('is-active');
        }

        // Navbar Height // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if (($bodyel.hasClass("ct-navbar--fixedTop")) || ($bodyel.hasClass("ct-js-navbarMakeSmaller"))) {
            if (scroll >= 100) {
                $(".ct-navbar--fixedTop .navbar").css("box-shadow","0 0 15px -6px #4c505e");

                if($bodyel.hasClass("ct-js-navbarMakeSmaller")){
                    $bodyel.addClass("ct-navbar--fixedTop--is-small");
                }
                if ($bodyel.hasClass("ct-navbar-isTransparent-toInverse") || $bodyel.hasClass("ct-navbar-isTransparent-toDefault")){
                    $navbarel.removeClass("ct-navbar--transparent");
                }
                if ($bodyel.hasClass("ct-navbar-isTransparent-toInverse")){
                    $navbarel.addClass("navbar-inverse");
                }
                if ($bodyel.hasClass("ct-navbar-isTransparent-toDefault")){
                    $navbarel.removeClass("navbar-transparent");
                    $navbarel.addClass("navbar-default");
                    logoswitch();
                }
            } else {
                if($bodyel.hasClass("ct-js-navbarMakeSmaller")){
                    $bodyel.removeClass("ct-navbar--fixedTop--is-small");
                    $(".ct-navbar--fixedTop .navbar").css("box-shadow","none");

                }
                if ($bodyel.hasClass("ct-navbar-isTransparent-toDefault") || $bodyel.hasClass("ct-navbar-isTransparent-toInverse")){
                    $navbarel.removeClass("navbar-default");
                    $navbarel.removeClass("navbar-inverse");
                    $navbarel.addClass("navbar-transparent");
                    if (( ($devicewidth < 1200) )){
                        $('#ct-js-logo').attr('src', $logo2.src)
                    }
                    else {
                        $('#ct-js-logo').attr('src', $logotext2.src)
                    }
                }
            }
        }

        // fixed navbar
        if ($bodyel.is(".navbar-fixed.with-topbar")) {
            if (scroll >= 100) {
                $bodyel.addClass("hide-topbar");
                if (!($bodyel.is(".revert-to-transparent"))) {
                    $bodyel.addClass("navbar-with-shadow");
                }
            } else {
                $bodyel.removeClass("hide-topbar navbar-with-shadow");
            }
        }

    });

    // Disable Image and Links Dragging -----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //$("img").mousedown(function(){
    //    return false;
    //});
    $("a").mousedown(function(){
        return false;
    });

    // Video Autoplay on Hover ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    $(function(){
        $(".ct-js-video").hover(function(){
            this.play();
        },function(){
            this.pause()
        });
    });


    // Tabs Cycle (for Testimonials in Fishtank) ------ .ct-testimonials .row ul > li -----------------------------------------------------------------------------------------------------------------------

    var tabChange = function(){
        var tabs = $('.ct-testimonials > .row > ul > li');
        var active = tabs.filter('.active');
        var next = active.next('li').length ? active.next('li').find('a') : tabs.filter(':first-child').find('a');
        next.tab('show');
    };
    var tabCycle = setInterval(tabChange, 5000);
    //--------------------------------------------------------------------------------------
    $('.ct-testimonials > .row > ul > li > a').on('click', function (e) {
        e.preventDefault();
        // Stop the cycle
        clearInterval(tabCycle);
        // Show the clicked tabs associated tab-pane
        $(this).tab('show');
        // Start the cycle again in a predefined amount of time
        tabCycle = setInterval(tabChange, 5000);
        return false;
    });


    // Snap.js -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    $(window).on('resize', function() {
        if ($(window).width() < 768) {
            snapper.enable();
        } else{
            snapper.disable();
        }
    });

    // Navbar Search ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    $(document).mouseup(function (e) {
        var $searchform = $(".ct-navbar-search");

        if(!$('#ct-js-navSearch').is(e.target)){
            if (!$searchform.is(e.target) // if the target of the click isn't the container...
                && $searchform.has(e.target).length === 0) // ... nor a descendant of the container
            {
                $searchform.hide();
                $('#ct-js-navSearch').removeClass('is-active');
            }
        }
    });

    // Intro Image
    $(".ct-intro-topImage").css("min-height",  $deviceheight + "px");

})(jQuery);