<%@ page import="com.ig.bc.Subscription" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'subscription.label', default: 'Subscription')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-subscription" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="list-subscription" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div id="subscriptionListDiv">
        <g:render template="list" model="[list: subscriptionInstanceList]"/>
        <fieldset class="buttons">
            <input type="button" name="unsubscribe-button" class="save" value="Unsubscribe"/>
        </fieldset>
    </div>

    <div class="pagination">
        <g:paginate total="${subscriptionInstanceTotal}"/>
    </div>
</div>
</body>
</html>
