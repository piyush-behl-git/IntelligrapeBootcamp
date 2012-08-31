<%@ page import="com.ig.bc.DocumentResource" %>

<div id="create-documentResource" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.document" args="[entityName]"/></h1>
    <g:if test="${flash.documentResource}">
        <script type="text/javascript">
            $(document).ready(function () {
                $("#document-dialog").dialog("open")
            })
        </script>
        <div class="message" role="status">${flash.documentResource}</div>
    </g:if>
    <g:form action="save" controller="documentResource" method="post" enctype="multipart/form-data" name="docForm">
        <fieldset class="form">

            <div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'title', 'error')} ">
                <label for="title">
                    <g:message code="documentResource.title.label" default="Title"/>
                </label>
                <g:textField name="title" value=""/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'fileName', 'error')} required">
                <label for="file">
                    Select File<span class="required-indicator">*</span>
                </label>
                <input type="file" name="file" id="file"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'summary', 'error')} ">
                <label for="summary">
                    <g:message code="documentResource.summary.label" default="Summary"/>
                </label>
                <g:textArea name="summary" cols="40" rows="5" maxlength="1024" value=""/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'topic', 'error')} required">
                <g:hiddenField id="topic" name="topic.id" required="" value="${topicInstance?.id}"/>
            </div>
        </fieldset>
    </g:form>
</div>





