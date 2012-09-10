<%@ page import="com.ig.bc.Subscription" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="jQueryMobile">
    <g:set var="entityName" value="${message(code: 'subscription.label', default: 'Subscription')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
    <h1>Subscriptions</h1>

    <div class="status-message" role="status"></div>
    <g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>
    <ul data-role="controlgroup" data-type="horizontal" class="localnav">
        <li><input type="button" name="unsubscribe-button" data-transition="fade" value="Unsubscribe"></li>
    </ul>
    <g:render template="list" model="[subscriptionInstanceList: subscriptionInstanceList]"/>

    <div class="pagination">
        <g:paginate total="${subscriptionInstanceTotal}"/>
    </div>
</body>
</html>
