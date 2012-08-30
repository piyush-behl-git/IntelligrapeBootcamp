<%@ page import="com.ig.bc.DocumentResource" %>

<div id="create-documentResource" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${documentResourceInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${documentResourceInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="save" controller="documentResource" method="post" enctype="multipart/form-data" name="docForm">
        <fieldset class="form">

            <div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'title', 'error')} ">
                <label for="title">
                    <g:message code="documentResource.title.label" default="Title"/>

                </label>
                <g:textField name="title" value="${documentResourceInstance?.title}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'fileName', 'error')} required">
                <label for="file">
                    Select File<span class="required-indicator">*</span>
                </label>
                <input type="file" name="file"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'summary', 'error')} ">
                <label for="summary">
                    <g:message code="documentResource.summary.label" default="Summary"/>

                </label>
                <g:textArea name="summary" cols="40" rows="5" maxlength="1024" value="${documentResourceInstance?.summary}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'topic', 'error')} required">
                <g:hiddenField id="topic" name="topic" required="" value="${topicInstance?.id}"/>
            </div>
        </fieldset>
    </g:form>
</div>





