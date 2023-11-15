(function ($) {
    "use strict";

    $.fn.magnificinfinitescroll = function(options) {
        if(jQuery().magnificPopup){
            jQuery('.ct-js-magnificPortfolioPopupGroup').each(function() { // the containers for all your galleries
                jQuery(this).magnificPopup({
                    type: 'ajax',
                    delegate: '.ct-js-magnificPortfolioPopup',
                    //mainClass: 'ct-magnificPopup-bottomArrows',
                    fixedContentPos: false,
                    closeBtnInside: true,
                    closeOnContentClick: false,
                    closeOnBgClick: false,
                    gallery: {
                        enabled: false
                    },
                    callbacks: {
                        ajaxContentAdded: function() {
                            $(".ct-slider-afterANDbefore").twentytwenty();
                        }
                        /*buildControls: function() {
                         // re-appends controls inside the main container
                         this.contentContainer.append(this.arrowLeft.add(this.arrowRight));
                         }*/

                    }
                });
            });

            $('.ct-js-magnificPopupMedia').magnificPopup({
                disableOn: 700,
                type: 'iframe',
                mainClass: 'mfp-fade',
                removalDelay: 160,
                preloader: true,

                fixedContentPos: false
            });
            $('.ct-js-magnificPopupImage').magnificPopup({
                disableOn: 700,
                type: 'image',
                mainClass: 'ct-magnificPopup--image',
                removalDelay: 160,
                preloader: true,

                fixedContentPos: false,
                gallery:{
                    enabled: false
                }
            });
        }
    };
    $(document).ready(function(){
        $('.ct-gallery').magnificinfinitescroll();
    })
}(jQuery));