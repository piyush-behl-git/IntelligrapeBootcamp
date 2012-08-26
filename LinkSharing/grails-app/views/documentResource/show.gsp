<%@ page import="com.ig.bc.DocumentResource" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'documentResource.label', default: 'DocumentResource')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-documentResource" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-documentResource" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list documentResource">

        <g:if test="${documentResourceInstance?.summary}">
            <li class="fieldcontain">
                <span id="summary-label" class="property-label"><g:message code="documentResource.summary.label" default="Summary"/></span>

                <span class="property-value" aria-labelledby="summary-label"><g:fieldValue bean="${documentResourceInstance}" field="summary"/></span>

            </li>
        </g:if>

        <g:if test="${documentResourceInstance?.title}">
            <li class="fieldcontain">
                <span id="title-label" class="property-label"><g:message code="documentResource.title.label" default="Title"/></span>

                <span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${documentResourceInstance}" field="title"/></span>

            </li>
        </g:if>

        <g:if test="${documentResourceInstance?.fileName}">
            <li class="fieldcontain">
                <span id="fileName-label" class="property-label"><g:message code="documentResource.fileName.label" default="File Name"/></span>
                <span class="property-value" aria-labelledby="fileName-label">
                    <g:link controller="documentResource" action="documentDownload" id="${documentResourceInstance?.id}">
                        <g:fieldValue bean="${documentResourceInstance}" field="fileName"/>
                    </g:link>
                </span>
            </li>
        </g:if>

        <g:if test="${documentResourceInstance?.contentType}">
            <li class="fieldcontain">
                <span id="contentType-label" class="property-label"><g:message code="documentResource.contentType.label" default="Content Type"/></span>

                <span class="property-value" aria-labelledby="contentType-label"><g:fieldValue bean="${documentResourceInstance}" field="contentType"/></span>

            </li>
        </g:if>

        <g:if test="${documentResourceInstance?.dateCreated}">
            <li class="fieldcontain">
                <span id="dateCreated-label" class="property-label"><g:message code="documentResource.dateCreated.label" default="Date Created"/></span>

                <span class="property-value" aria-labelledby="dateCreated-label"><ls:formattedDate date="${documentResourceInstance?.dateCreated}"/></span>

            </li>
        </g:if>

        <g:if test="${documentResourceInstance?.lastUpdated}">
            <li class="fieldcontain">
                <span id="lastUpdated-label" class="property-label"><g:message code="documentResource.lastUpdated.label" default="Last Updated"/></span>

                <span class="property-value" aria-labelledby="lastUpdated-label"><ls:formattedDate date="${documentResourceInstance?.lastUpdated}"/></span>

            </li>
        </g:if>

        <g:if test="${documentResourceInstance?.readingItems}">
            <li class="fieldcontain">
                <span id="readingItems-label" class="property-label"><g:message code="documentResource.readingItems.label" default="Reading Items"/></span>

                <g:each in="${documentResourceInstance.readingItems}" var="r">
                    <span class="property-value" aria-labelledby="readingItems-label"><g:link controller="readingItem" action="show"
                                                                                              id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${documentResourceInstance?.id}"/>
            <g:link class="edit" action="edit" id="${documentResourceInstance?.id}"><g:message code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
