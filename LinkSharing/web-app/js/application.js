var urls = {
    markCurrentReadUrl:"",
    checkEmailUrl:"",
    checkDocUrl:"",
    checkDocContentTypeUrl:"",
    markReadUrl:"",
    markUnreadUrl:"",
    subscribeUrl:"",
    unsubscribeUrl:"",
    markFavUrl:"",
    markUnmarkFavUrl:"",
    searchUrl:""
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
            },
            messages:{
                email:{
                    remote: jQuery.format("{0} is already in use")
                }
            }
        }
    });
    $('#resource-dialog').dialog({
        autoOpen:false,
        draggable:false,
        modal:true,
        show:'fadeIn',
        hide:'fadeOut',
        width:700,
        resizable:false,
        buttons:{
            Ok:function () {
                $(this).dialog("close")
            },
            Cancel:function () {
                $(this).dialog("close")
            }
        }

    });
    $('#addResource').bind("click", function () {
        $('#resource-dialog').dialog("open")
    });
    $('#docButton').bind("click", function () {
        $('#resource-dialog').dialog("close")
        $('#document-dialog').dialog('open')
    });
    $('#document-dialog').dialog({
        autoOpen:false,
        draggable:false,
        modal:true,
        show:'fadeIn',
        hide:'fadeOut',
        width:800,
        resizable:false,
        buttons:{
            Ok:function () {
                $('form[name=docForm]').submit()
                $(this).dialog("close")
            },
            Cancel:function () {
                $(this).dialog("close")
            }
        }

    });
    $('#link-dialog').dialog({
        autoOpen:false,
        draggable:false,
        modal:true,
        show:'fadeIn',
        hide:'fadeOut',
        width:800,
        resizable:false,
        buttons:{
            Ok:function () {
                $('form[name=linkForm]').submit()
                $(this).dialog("close")
            },
            Cancel:function () {
                $(this).dialog("close")
            }
        }

    });
    $('#linkButton').bind("click", function () {
        $('#resource-dialog').dialog("close")
        $('#link-dialog').dialog('open')
    });

    $('select#selectTopic').bind('change', function () {
        if ($(this).val() == 'Check All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', true)
            });
        }
        if ($(this).val() == 'Uncheck All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', false)
            });
        }
        if ($(this).val() == 'Inverse') {
            $('input[name=status]').each(function () {
                if ($(this).is(':checked'))
                    $(this).attr('checked', false)
                else
                    $(this).attr('checked', true)
            });
        }
    });
    $('select#selectReadingItem').bind('change', function () {
        if ($(this).val() == 'Check All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', true)
            });
        }
        if ($(this).val() == 'Uncheck All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', false)
            });
        }
        if ($(this).val() == 'Inverse') {
            $('input[name=status]').each(function () {
                if ($(this).is(':checked'))
                    $(this).attr('checked', false)
                else
                    $(this).attr('checked', true)
            });
        }
    });
    $('input[name=mark-read-button]').bind('click', function () {
        var idValues = ''
        $('input#status:checked').each(function () {
            if (idValues.length > 0)
                idValues = idValues + ","
            idValues = idValues + $(this).val()
        });
        $.ajax({
            url:urls.markReadUrl,
            data:"ids=" + idValues,
            success:function (html) {
                $('div#readingItemListDiv').html(html)
            }
        });
    });
    $('input[name=mark-unread-button]').bind('click', function () {
        var idValues = ''
        $('input#status:checked').each(function () {
            if (idValues.length > 0)
                idValues = idValues + ","
            idValues = idValues + $(this).val()
        });
        $.ajax({
            url:urls.markUnreadUrl,
            data:"ids=" + idValues,
            success:function (html) {
                $('div#readingItemListDiv').html(html)
            }
        });
    });
    $('input[name=subscribe-button]').bind('click', function () {
        var idValues = ''
        $('input#status:checked').each(function () {
            if (idValues.length > 0)
                idValues = idValues + ","
            idValues = idValues + $(this).val()
        });
        $.ajax({
            url:urls.subscribeUrl,
            data:"ids=" + idValues,
            success:function (html) {
                $('div#subscriptionListDiv').html(html)
            }
        });
    });
    $('input[name=unsubscribe-button]').bind('click', function () {
        var idValues = ''
        $('input#status:checked').each(function () {
            if (idValues.length > 0)
                idValues = idValues + ","
            idValues = idValues + $(this).val()
        });
        $.ajax({
            url:urls.unsubscribeUrl,
            data:"ids=" + idValues,
            success:function (html) {
                $('div#subscriptionListDiv').html(html)
            }
        });
    });
});
function changeFav(id) {
    $.ajax({
        url:urls.markUnmarkFavUrl,
        data:{id:id},
        success:function (result) {
            if (result == "true")
                $('#img' + id).attr('src', '/LinkSharing/images/star_on.png')
            else if (result == "false")
                $('#img' + id).attr('src', '/LinkSharing/images/star_off.png')
        }
    })
}
function markCurrentRead(id) {
    $.ajax({
        url:urls.markCurrentReadUrl,
        data:{id:id},
        success:function (result) {
            if (result == "true") {
                $('#row' + id).css('font-weight', '400')
            }
        }
    })
}
$(function () {
    function log(message) {
        $("<div/>").text(message).prependTo("#log");
        $("#log").scrollTop(0);
    }

    $('input#searchField').autocomplete({
        source:function (request, response) {
            $.ajax({
                url:urls.searchUrl,
                dataType:"json",
                data:{
                    searchField_startsWith:request.term
                },
                success:function (data) {
                    response($.map(data.results, function (name) {
                        return {
                            label:name,
                            value:name
                        }
                    }));
                }
            });
        }
    });
});