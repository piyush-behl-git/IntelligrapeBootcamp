var urls ={
    checkEmailUrl:""
};
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
    $('#resource-dialog').dialog({
        autoOpen: false,
        draggable: false,
        modal: true,
        show: 'fadeIn',
        hide: 'fadeOut',
        width:700,
        resizable:false,
        buttons:{
            Ok:function(){
                $(this).dialog("close")
            },
            Cancel: function() {
                $(this).dialog("close")
            }
        }

    });
    $('#addResource').bind("click",function(){
        $('#resource-dialog').dialog("open")
    });
    $('#document-dialog').dialog({
        autoOpen: false,
        draggable: false,
        modal: true,
        show: 'fadeIn',
        hide: 'fadeOut',
        width:800,
        resizable:false,
        buttons:{
            Ok:function(){
                $('form[name=docForm]').submit()
                $(this).dialog("close")
            },
            Cancel: function() {
                $(this).dialog("close")
            }
        }

    });
    $('#docButton').bind("click",function(){
        $('#resource-dialog').dialog("close")
        $('#document-dialog').dialog('open')
    });
    $('#link-dialog').dialog({
        autoOpen: false,
        draggable: false,
        modal: true,
        show: 'fadeIn',
        hide: 'fadeOut',
        width:800,
        resizable:false,
        buttons:{
            Ok:function(){
                $('form[name=linkForm]').submit()
                $(this).dialog("close")
            },
            Cancel: function() {
                $(this).dialog("close")
            }
        }

    });
    $('#linkButton').bind("click",function(){
        $('#resource-dialog').dialog("close")
        $('#link-dialog').dialog('open')
    });

});

