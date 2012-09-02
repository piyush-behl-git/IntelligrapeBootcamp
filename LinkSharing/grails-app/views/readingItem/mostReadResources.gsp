<%@ page import="com.ig.bc.LinkResource; com.ig.bc.DocumentResource; com.ig.bc.ReadingItem" %>
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
        <g:each in="${topicsMostReadResources}" status="i" var="topicMostReadResources">
            <thead>
            <tr>
                <th>${topicMostReadResources.topic.name}</th>
            </tr>
            <tr>
                <th>Resource</th>
                <g:sortableColumn property="dateCreated" title="${message(code: 'documentResource.dateCreated.label', default: 'Date Created')}"/>
                <g:sortableColumn property="lastUpdated" title="${message(code: 'documentResource.lastUpdated.label', default: 'Last Updated')}"/>
                <th>Read Count</th>
            </tr>
            </thead>
            <g:each in="${topicMostReadResources.mostReadResources}" status="j" var="mostReadResource">
                <tr class="${(j % 2) == 0 ? 'even' : 'odd'}">
                    <g:if test="${mostReadResource.resource.instanceOf(DocumentResource)}">
                        <td>
                            <g:link controller="documentResource" action="show" id="${mostReadResource.resource?.id}">${mostReadResource.resource?.fileName}</g:link>
                        </td>
                    </g:if>
                    <g:if test="${mostReadResource.resource.instanceOf(LinkResource)}">
                        <td><g:link controller="linkResource" action="show" id="${mostReadResource.resource?.id}">${mostReadResource.resource?.url}</g:link></td>
                    </g:if>
                    <td><ls:formattedDate date="${mostReadResource.resource.dateCreated}"/></td>
                    <td><ls:formattedDate date="${mostReadResource.resource.lastUpdated}"/></td>
                    <td>${mostReadResource.readCount}</td>
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
