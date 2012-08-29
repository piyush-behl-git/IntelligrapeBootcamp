<%@ page import="com.ig.bc.LinkResource; com.ig.bc.DocumentResource" %>
<table>
    <thead>
    <tr>
        <g:sortableColumn property="title" title="${message(code: 'resource.title.label', default: 'Title')}"/>

        <td>Resource</td>

        <g:sortableColumn property="dateCreated" title="${message(code: 'resource.dateCreated.label', default: 'Date Created')}"/>

        <g:sortableColumn property="lastUpdated" title="${message(code: 'resource.lastUpdated.label', default: 'Last Updated')}"/>

        <g:sortableColumn property="summary" title="Summary"/>
    </tr>
    </thead>
    <tbody>
    <g:each in="${resourceList}" status="i" var="resource">
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