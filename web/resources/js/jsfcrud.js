function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}
$(document).ready(function () {
    $('.ir-arriba').click(function () {
        $('body, html').animate({
            scrollTop: '0px'
        }, 500);
    });
    $(window).scroll(function () {
        if ($(this).scrollTop() > 0) {
            $('.ir-arriba').fadeIn(700);
        } else {
            $('.ir-arriba').fadeOut(700);
        }
    });


});








//$(document).ready(function () {
//    $(".caja_oferta2").hover(
//            function () {
//                $(".linkOferta").css({"color": "black"});
//                $(".textoDescripcion2").css({"color": "black"});
//                $(".textoDescripcion").css({"color": "black"});
////                $(".ver").css({"background-color": "green"});
//            }, function () {
//        $(".linkOferta").css({"color": ""});
//        $(".textoDescripcion2").css({"color": ""});
//        $(".textoDescripcion").css({"color": ""});
////        $(".ver").css({"background-color": ""});
//    }
//    );
//});


