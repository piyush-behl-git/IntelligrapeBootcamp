
<%@ page import="com.ig.bc.Subscription" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'subscription.label', default: 'Subscription')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-subscription" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-subscription" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list subscription">
			
				<g:if test="${subscriptionInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="subscription.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><ls:formattedDate date="${subscriptionInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${subscriptionInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="subscription.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><ls:formattedDate date="${subscriptionInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${subscriptionInstance?.seriousness}">
				<li class="fieldcontain">
					<span id="seriousness-label" class="property-label"><g:message code="subscription.seriousness.label" default="Seriousness" /></span>
					
						<span class="property-value" aria-labelledby="seriousness-label"><g:fieldValue bean="${subscriptionInstance}" field="seriousness"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${subscriptionInstance?.subscriber}">
				<li class="fieldcontain">
					<span id="subscriber-label" class="property-label"><g:message code="subscription.subscriber.label" default="Subscriber" /></span>
					
						<span class="property-value" aria-labelledby="subscriber-label"><g:link controller="user" action="show" id="${subscriptionInstance?.subscriber?.id}">${subscriptionInstance?.subscriber?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${subscriptionInstance?.topic}">
				<li class="fieldcontain">
					<span id="topic-label" class="property-label"><g:message code="subscription.topic.label" default="Topic" /></span>
					
						<span class="property-value" aria-labelledby="topic-label"><g:link controller="topic" action="show" id="${subscriptionInstance?.topic?.id}">${subscriptionInstance?.topic?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${subscriptionInstance?.id}" />
					<g:link class="edit" action="edit" id="${subscriptionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
