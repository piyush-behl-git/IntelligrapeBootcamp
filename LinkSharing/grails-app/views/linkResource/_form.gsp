<%@ page import="com.ig.bc.LinkResource" %>



<div class="fieldcontain ${hasErrors(bean: linkResourceInstance, field: 'summary', 'error')} ">
	<label for="summary">
		<g:message code="linkResource.summary.label" default="Summary" />
		
	</label>
	<g:textArea name="summary" cols="40" rows="5" maxlength="1024" value="${linkResourceInstance?.summary}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: linkResourceInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="linkResource.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${linkResourceInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: linkResourceInstance, field: 'url', 'error')} required">
	<label for="url">
		<g:message code="linkResource.url.label" default="Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="url" name="url" required="" value="${linkResourceInstance?.url}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: linkResourceInstance, field: 'readingItems', 'error')} ">
	<label for="readingItems">
		<g:message code="linkResource.readingItems.label" default="Reading Items" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${linkResourceInstance?.readingItems?}" var="r">
    <li><g:link controller="readingItem" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="readingItem" action="create" params="['linkResource.id': linkResourceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'readingItem.label', default: 'ReadingItem')])}</g:link>
</li>
</ul>

</div>

