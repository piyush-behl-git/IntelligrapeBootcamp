
<%@ page import="com.ig.bc.Topic" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'topic.label', default: 'Topic')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-topic" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-topic" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:render template="list" model="[list: topicInstanceList]"/>
			<div class="pagination">
				<g:paginate total="${topicInstanceTotal}" />
			</div>
		</div>

        <div id="invitationDiv">
            <g:form action="bindInvitation">
                <pre>
                    Email :<g:textField name="email1" />
                    Email :<g:textField name="email2" />
                    Email :<g:textField name="email3" />
                    Content
                    <g:textArea name="content" rows="" cols="" />
                    <g:submitButton name="submit" value="Invite"/>
                </pre>
            </g:form>
        </div>
	</body>
</html>
