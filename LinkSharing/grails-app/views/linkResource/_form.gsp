<%@ page import="com.ig.bc.LinkResource" %>

<div id="create-linkResource" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.link.label" args="[entityName]"/></h1>
    <g:if test="${flash.linkResource}">
        <div class="message" role="status">${flash.linkResource}</div>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#link-dialog').dialog('open')
            })
        </script>
    </g:if>
    <g:hasErrors bean="${linkResourceInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${linkResourceInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#link-dialog').dialog('open')
        })
    </script>
    </g:hasErrors>
    <g:form action="save" controller="linkResource" name="linkForm" class="linkForm">
        <fieldset class="form">
            <div class="fieldcontain ${hasErrors(bean: linkResourceInstance, field: 'title', 'error')} ">
                <label for="title">
                    <g:message code="linkResource.title.label" default="Title"/>

                </label>
                <g:textField name="title" value="${linkResourceInstance?.title}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: linkResourceInstance, field: 'url', 'error')} required">
                <label for="url">
                    <g:message code="linkResource.url.label" default="Url"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:field type="url" name="url" required="" value="${linkResourceInstance?.url}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: linkResourceInstance, field: 'summary', 'error')} ">
                <label for="summary">
                    <g:message code="linkResource.summary.label" default="Summary"/>

                </label>
                <g:textArea name="summary" cols="40" rows="5" maxlength="1024" value="${linkResourceInstance?.summary}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: linkResourceInstance, field: 'topic', 'error')} required">
                <g:hiddenField name="topic.id" id="topic" value="${topicInstance?.id}"/>
            </div>

        </fieldset>
       </g:form>
</div>