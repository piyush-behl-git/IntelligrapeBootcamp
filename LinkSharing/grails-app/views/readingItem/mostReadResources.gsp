<%@ page import="com.ig.bc.ReadingItem" %>
<!doctype html>
<html>
<head>
    <meta content="main" name="layout">
    <g:set var="entityName" value="${message(code: 'readingItem.label', default: 'ReadingItem')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-readingItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="list-readingItem" class="content scaffold-list" role="main">
    <h1>Most Unread Resources</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table id="dlist">
        <g:each in="${topicResourceCountList}" status="i" var="item">
            <thead>
            <tr>
                <th>${item.topic.name}</th>
            </tr>
            <tr>
                <g:sortableColumn property="title" title="${message(code: 'documentResource.title.label', default: 'Title')}"/>
                <g:sortableColumn property="fileName" title="${message(code: 'documentResource.fileName.label', default: 'File Name')}"/>
                <g:sortableColumn property="url" title="${message(code: 'linkResource.url.label', default: 'Url')}"/>
                <g:sortableColumn property="dateCreated" title="${message(code: 'documentResource.dateCreated.label', default: 'Date Created')}"/>
                <g:sortableColumn property="lastUpdated" title="${message(code: 'documentResource.lastUpdated.label', default: 'Last Updated')}"/>
                <th>Read Count</th>
            </tr>
            </thead>
            <g:each in="${item.resourceAndCountList}" status="j" var="rc">
                <tr class="${(j % 2) == 0 ? 'even' : 'odd'}">
                    <td>${rc.resource.title}</td>
                    <td>${rc.resource.dateCreated}</td>
                    <td>${rc.resource.lastUpdated}</td>
                    <td>${rc.readCount}</td>
                </tr>
            </g:each>
        </g:each>
    </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="10"/>
    </div>
</div>
</body>
</html>
