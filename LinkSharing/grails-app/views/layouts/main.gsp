<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="LinkSharing"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <g:javascript library="jquery" plugin="jquery"/>
    <jqui:resources/>
    <g:javascript src="jquery.validate.js"/>
    <g:javascript src="application.js"/>
    <g:layoutHead/>
</head>

<body>
<div>
    <ls:ifLoggedIn>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><a class="home" href="${createLink(controller: 'topic', action: 'list')}">Topics</a></li>
                <li><a class="home" href="${createLink(controller: 'documentResource', action: 'list')}">Documents</a></li>
                <li><a class="home" href="${createLink(controller: 'linkResource', action: 'list')}">Links</a></li>
                <li><a class="home" href="${createLink(controller: 'subscription', action: 'list')}">Subscriptions</a></li>

                <ls:ifAdmin><li><g:link class="stats" controller="admin" action="stats">Statistics</g:link></li></ls:ifAdmin>
                <ls:ifLoggedIn><li><g:textField name="searchField" id="searchField"/></li></ls:ifLoggedIn>
                <li><g:link class="logout" controller="login" action="logout">Logout</g:link></li>
            </ul>
        </div>
    </ls:ifLoggedIn>
    <g:layoutBody/>
    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
</div>
<script type="text/javascript">
    urls = {
        markReadUrl:"${createLink(controller: "readingItem", action: "markRead")}",
        markUnreadUrl:"${createLink(controller: 'readingItem', action: 'markUnread')}",
        markUnmarkFavUrl:"${createLink(controller: 'readingItem', action: 'markUnmarkFav')}",
        markCurrentReadUrl:"${createLink(controller: 'readingItem', action: 'markCurrentRead')}",
        searchUrl:"${createLink(controller: 'search', action: 'searchUsers')}",
        checkEmailUrl: "${createLink(controller: "login", action: "checkEmailUrl")}",
        subscribeUrl:"${createLink(controller: "subscription", action: "subscribe")}",
        unsubscribeUrl:"${createLink(controller: 'subscription', action: 'unsubscribe')}",
        loadSubscriptionsUrl:"${createLink(controller: 'admin', action: 'loadSubscriptions')}"
    }
</script>
</body>
</html>