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

$('textarea.txtOA').keyup(function() {
    var $textarea = $(this);
    var max = 250;
    if ($textarea.val().length > max) {
        $textarea.val($textarea.val().substr(0, max));
    }
});
$('textarea.txtEA').keyup(function() {
    var $textarea = $(this);
    var max = 250;
    if ($textarea.val().length > max) {
        $textarea.val($textarea.val().substr(0, max));
    }
});

});

