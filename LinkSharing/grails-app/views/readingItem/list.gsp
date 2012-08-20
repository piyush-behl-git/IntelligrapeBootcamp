
<%@ page import="com.ig.bc.ReadingItem" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'readingItem.label', default: 'ReadingItem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-readingItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-readingItem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="favorite" title="${message(code: 'readingItem.favorite.label', default: 'Favorite')}" />
					
						<g:sortableColumn property="read" title="${message(code: 'readingItem.read.label', default: 'Read')}" />
					
						<th><g:message code="readingItem.resource.label" default="Resource" /></th>
					
						<th><g:message code="readingItem.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${readingItemInstanceList}" status="i" var="readingItemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${readingItemInstance.id}">${fieldValue(bean: readingItemInstance, field: "favorite")}</g:link></td>
					
						<td><g:formatBoolean boolean="${readingItemInstance.read}" /></td>
					
						<td>${fieldValue(bean: readingItemInstance, field: "resource")}</td>
					
						<td>${fieldValue(bean: readingItemInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${readingItemInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
