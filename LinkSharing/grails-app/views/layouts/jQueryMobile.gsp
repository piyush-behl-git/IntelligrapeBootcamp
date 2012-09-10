<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>

    <title>Link Sharing | <g:layoutTitle/></title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.mobile-1.1.1.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jqm-datebox-1.1.0.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'myStyle.css')}"/>
    <g:javascript library="jquery" plugin="jquery"/>
    <g:javascript src="jquery.mobile-1.1.1.js"/>
    <g:javascript src="jqm-datebox-1.1.0.core.js"/>
    <g:javascript src="jqm-datebox-1.1.0.mode.slidebox.js"/>
    <g:javascript src="JQueryCustomApplication.js"/>
    <g:javascript src="jquery.validate.js"/>
    <script type="text/javascript">
        urls = {
            markReadUrl:"${createLink(controller: "readingItem", action: "markRead")}",
            markUnreadUrl:"${createLink(controller: 'readingItem', action: 'markUnread')}",
            markUnmarkFavUrl:"${createLink(controller: 'readingItem', action: 'markUnmarkFav')}",
            markCurrentReadUrl:"${createLink(controller: 'readingItem', action: 'markCurrentRead')}",
            searchUrl:"${createLink(controller: 'search', action: 'searchUsers')}",
            checkEmailUrl:"${createLink(controller: "login", action: "checkEmailUrl")}",
            subscribeUrl:"${createLink(controller: "subscription", action: "subscribe")}",
            unsubscribeUrl:"${createLink(controller: 'subscription', action: 'unsubscribe')}"
        }
    </script>
    <g:layoutHead/>
</head>

<body>
<div data-role="page" class="type-home" data-content-theme="c" data-theme="c">
    <div data-role="header" data-theme="b">
        <g:link uri="/" data-icon="home" data-role="button" data-iconpos="notext" data-transition="fade">Home</g:link>
        <h1 class="ui-title" role="heading" aria-level="1">Link Sharing</h1>
        <a href="#" data-icon="search" data-iconpos="notext">Search</a>
    </div>
    <ls:ifLoggedIn>
        <div class="ui-body ui-body-c">
            <g:link uri="/" data-role="button" data-theme="c" data-icon="home" data-inline="true">Home</g:link>
            <g:link controller="topic" action="list" data-role="button" data-theme="c" data-icon="" data-inline="true">Topics</g:link>
            <g:link controller="linkResource" action="list" data-role="button" data-theme="c" data-icon="" data-inline="true">Link Resources</g:link>
            <g:link controller="documentResource" action="list" data-role="button" data-theme="c" data-icon="" data-inline="true">Document Resources</g:link>
            <g:link controller="subscription" action="list" data-role="button" data-theme="c" data-icon="" data-inline="true">Subscriptions</g:link>
            <ls:ifAdmin><g:link controller="admin" action="stats" data-role="button" data-theme="c" data-icon="" data-inline="true">Statistics</g:link></ls:ifAdmin>
            <g:link controller="login" action="logout" data-role="button" data-theme="c" data-inline="true">Logout</g:link>
        </div>
    </ls:ifLoggedIn>

    <g:layoutBody/>
    <div data-role="footer" data-theme="b">
    </div>
</div>
</body>
</html>