<%@ page import="com.ig.bc.DocumentResource" %>




<div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'title', 'error')} required">
    <label for="title">
        <g:message code="documentResource.title.label" default="Title" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="title" required="" value="${documentResourceInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'fileName', 'error')} required">
    <label for="fileName">
        <g:message code="documentResource.fileName.label" default="File Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="fileName" required="" value="${documentResourceInstance?.fileName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'contentType', 'error')} required">
    <g:hiddenField name="contentType"/>        User currentUserInstance = User.findByEmail("${session.email}")
</div>

<div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'fileName', 'error')} required">
    <label for="file">
        Select File<span class="required-indicator">*</span>
    </label>
    <input type="file" name="file" />
</div>

<div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'summary', 'error')} ">
    <label for="summary">
        <g:message code="documentResource.summary.label" default="Summary" />

    </label>
    <g:textArea name="summary" cols="40" rows="5" maxlength="1024" value="${documentResourceInstance?.summary}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: documentResourceInstance, field: 'readingItems', 'error')} ">
    <label for="readingItems">
		<g:message code="documentResource.readingItems.label" default="Reading Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${documentResourceInstance?.readingItems?}" var="r">
    <li><g:link controller="readingItem" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="readingItem" action="create" params="['documentResource.id': documentResourceInstance?.id]">${message(code: 'default.add.label', args: [message(code:
        'readingItem.label', default: 'ReadingItem')])}</g:link>
</li>
</ul>

</div>

