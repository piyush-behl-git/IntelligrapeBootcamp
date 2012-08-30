<%@ page import="com.ig.bc.LinkResource; com.ig.bc.DocumentResource" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta content="main" name="layout">
    <g:set var="entityName" value="${message(code: 'readingItem.label', default: 'ReadingItem')}"/>
    <title></title>
</head>

<body>
<div id="list-readingItem" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <table>
        <thead>
        <tr>

            <td>Title</td>
            <td>Resource</td>
            <td>Date Created</td>
            <td>Last Updated</td>
            <td>Summary</td>

        </tr>





        </thead>
        <tbody>
        <g:each in="${unreadResourceList}" status="i" var="resource">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: resource, field: "title")}</td>
                <g:if test="${resource.instanceOf(DocumentResource)}">
                    <td>${resource.fileName}</td>
                </g:if>
                <g:if test="${resource.instanceOf(LinkResource)}">
                    <td>${resource.url}</td>
                </g:if>

                <td><g:formatDate date="${resource.dateCreated}"/></td>

                <td><g:formatDate date="${resource.lastUpdated}"/></td>

                <td><g:link action="show" id="${resource.id}">${fieldValue(bean: resource, field: "summary")}</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<div class="pagination">
    %{--<g:paginate total="${totalResources}" />--}%
</div>
</body>
</html>