
<%@ page import="com.ig.bc.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-user" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="email" title="${message(code: 'user.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="dateOfBirth" title="${message(code: 'user.dateOfBirth.label', default: 'Date Of Birth')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'user.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="fullName" title="${message(code: 'user.fullName.label', default: 'Full Name')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'user.lastUpdated.label', default: 'Last Updated')}" />
					
						<g:sortableColumn property="male" title="${message(code: 'user.isMale.label', default: 'Male')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userInstanceList}" status="i" var="userInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "email")}</g:link></td>
					
						<td><ls:formattedDate date="${userInstance.dateOfBirth}" /></td>
					
						<td><ls:formattedDate date="${userInstance.dateCreated}" /></td>
					
						<td>${fieldValue(bean: userInstance, field: "fullName")}</td>
					
						<td><ls:formattedDate date="${userInstance.lastUpdated}" /></td>
					
						<td><g:if test="${userInstance.isMale}">Yes</g:if><g:else>No</g:else></td>

                    </tr>
                </g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
