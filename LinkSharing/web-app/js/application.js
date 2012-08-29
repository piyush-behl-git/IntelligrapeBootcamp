if (typeof jQuery !== 'undefined') {
    (function ($) {
        $('#spinner').ajaxStart(function () {
            $(this).fadeIn();
        }).ajaxStop(function () {
                $(this).fadeOut();
            });
    })(jQuery);
}
$(document).ready(function () {
    $('#nav_tabs').tabs();
    $('#datepicker').datepicker();
    $('#registrationForm').validate({
        rules:{
            email:{
                required:true,
                email:true,
                remote:urls.checkEmailUrl
            },
            password:{
                required:true,
                maxLength:8
            },
            confirmPassword:{
                required:true,
                equalTo:"#password"
            }
        }
    });
});

