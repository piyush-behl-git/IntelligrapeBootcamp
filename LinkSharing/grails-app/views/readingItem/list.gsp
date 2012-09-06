<%@ page import="com.ig.bc.ReadingItem" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'readingItem.label', default: 'ReadingItem')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-readingItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="list-readingItem" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>

    <div id="readingItemListDiv">
        <g:render template="list" model="[list: readingItemInstanceList]"/>
        <fieldset class="buttons">
            <input type="button" name="mark-read-button" class="save" value="Mark Read"/>
            <input type="button" name="mark-unread-button" class="save" value="Mark Unread"/>
        </fieldset>
    </div>

    <div class="pagination">
        <g:paginate total="${readingItemInstanceTotal}"/>
    </div>
</div>
</body>
</html>
