<%@ page import="com.ig.bc.LinkResource; com.ig.bc.DocumentResource" contentType="text/html;charset=UTF-8" %>
<meta content="main" name="layout">

<div id="list-readingItem" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:each in="${newTopicResourceList}" status="i" var="topicResource">

        <table>
            <thead>${topicResource.topic.name}
            <tr>
                <td>Title</td>
                <td>Resource</td>
                <td>Date Created</td>
                <td>Last Updated</td>
                <td>Summary</td>
            </tr>
            </thead>
            <tbody>
            <g:each in="${topicResource.resources}" status="j" var="resource">
                <tr class="${(j % 2) == 0 ? 'even' : 'odd'}">
                    <td>${fieldValue(bean: resource, field: "title")}</td>
                    <g:if test="${resource.instanceOf(DocumentResource)}">
                        <td>${resource.fileName}</td>
                    </g:if>
                    <g:if test="${resource.instanceOf(LinkResource)}">
                        <td>${resource.url}</td>
                    </g:if>
                    <td><g:formatDate date="${resource.dateCreated}"/></td>
                    <td><g:formatDate date="${resource.lastUpdated}"/></td>
                    <td>${resource.summary}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </g:each>
</div>