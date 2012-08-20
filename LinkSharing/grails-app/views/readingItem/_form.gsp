<%@ page import="com.ig.bc.ReadingItem" %>



<div class="fieldcontain ${hasErrors(bean: readingItemInstance, field: 'favorite', 'error')} ">
	<label for="favorite">
		<g:message code="readingItem.favorite.label" default="Favorite" />
		
	</label>
	<g:checkBox name="favorite" value="${readingItemInstance?.favorite}" />
</div>

<div class="fieldcontain ${hasErrors(bean: readingItemInstance, field: 'read', 'error')} ">
	<label for="read">
		<g:message code="readingItem.read.label" default="Read" />
		
	</label>
	<g:checkBox name="read" value="${readingItemInstance?.read}" />
</div>

<div class="fieldcontain ${hasErrors(bean: readingItemInstance, field: 'resource', 'error')} required">
	<label for="resource">
		<g:message code="readingItem.resource.label" default="Resource" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="resource" name="resource.id" from="${com.ig.bc.Resource.list()}" optionKey="id" required="" value="${readingItemInstance?.resource?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: readingItemInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="readingItem.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.ig.bc.User.list()}" optionKey="id" required="" value="${readingItemInstance?.user?.id}" class="many-to-one"/>
</div>

