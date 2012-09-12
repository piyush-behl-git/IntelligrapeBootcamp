var urls = {
    markCurrentReadUrl:"",
    checkEmailUrl:"",
    checkDocUrl:"",
    checkDocContentTypeUrl:"",
    markReadUrl:"",
    markUnreadUrl:"",
    subscribeUrl:"",
    unsubscribeUrl:"",
    topicSubscribeUrl:"",
    markFavUrl:"",
    markUnmarkFavUrl:"",
    searchUrl:"",
    deleteResourceUrl:""
};
$(document).ready(function () {
    console.debug("ready");
    $('#registrationForm').validate({
        rules:{
            email:{
                required:true,
                email:true,
                remote:urls.checkEmailUrl
            }
        }
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
    $('select#selectTopic').bind('change', function () {
        if ($(this).val() == 'Check All') {
            $('input[name=status]').not('[disabled]').each(function () {
                $(this).attr('checked', true)
            });
        }
        if ($(this).val() == 'Uncheck All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', false)
            });
        }
        if ($(this).val() == 'Inverse') {
            $('input[name=status]').not('[disabled]').each(function () {
                if ($(this).is(':checked'))
                    $(this).attr('checked', false)
                else
                    $(this).attr('checked', true)
            });
        }
    });
    $('select#selectReadingItem').bind('change', function () {
        if ($(this).val() == 'Check All') {
            $('input[name=status]').not('[disabled]').each(function () {
                $(this).attr('checked', true)
            });
        }
        if ($(this).val() == 'Uncheck All') {
            $('input[name=status]').each(function () {
                $(this).attr('checked', false)
            });
        }
        if ($(this).val() == 'Inverse') {
            $('input[name=status]').not('[disabled]').each(function () {
                if ($(this).is(':checked'))
                    $(this).attr('checked', false)
                else
                    $(this).attr('checked', true)
            });
        }
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
                $('div.status-message').html(html)
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
                $('input#status:checked').each(function () {
                    $('#li_'+$(this).val()).slideUp('normal', function () {
                        $(this).remove()
                    });
                });
                $('div.status-message').html(html)
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
function deleteResource(id) {
    $.ajax({
        url:urls.deleteResourceUrl,
        data:{id:id},
        success:function (result) {
            if (result == "true") {
                $('#li' + id).slideUp("normal", function () {
                    $(this).remove()
                });
            }
            else {
                $('#topicResourceStatusDiv').text("Cannot remove resource created by others.")
            }
        }
    })
}
function subscribeIndividualTopic(id) {
    alert("hello")
    $.ajax({
        url:urls.topicSubscribeUrl,
        data:{id:id},
        success:function (result) {
            $('#topicStatusDiv').text(result)
        }
    });
    return false;
}
function unsubscribeIndividualTopic(id) {

}