<%@ page import="com.ig.bc.Topic" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="jQueryMobile">
    <g:set var="entityName" value="${message(code: 'topic.label', default: 'Topic')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<div id="list-topic" class="content scaffold-list" role="main">
    <h1>Topics</h1>
    <div class="status-message" role="status"></div>
    <ul data-role="controlgroup" data-type="horizontal" class="localnav">
        <li><a href="${createLink(controller: 'topic', action: 'create')}" data-rel="dialog" data-role="button" data-transition="fade" class="ui-btn-active">Create</a></li>
        <li><a href="content-collapsible-set-options.html" data-role="button" data-transition="fade">Remove</a></li>
        <li><input type="button" name="subscribe-button" data-transition="fade" value="Subscribe"></li>
    </ul>
    <g:render template="/topic/list" model="[list: topicInstanceList]"/>
    <div class="pagination">
        <g:paginate total="${topicInstanceTotal}"/>
    </div>
</div>
</body>
</html>
