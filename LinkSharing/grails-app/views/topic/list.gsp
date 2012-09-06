<%@ page import="com.ig.bc.Topic" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'topic.label', default: 'Topic')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-topic" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="list-topic" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:render template="list" model="[list: topicInstanceList]"/>
    <fieldset class="buttons">
        <input type="button" name="subscribe-button" class="save" value="Subscribe"/>
        <input type="button" name="unsubscribe-button" class="save" value="Unsubscribe"/>
        <g:link class="create" action="create">Create</g:link>
    </fieldset>

    <div class="pagination">
        <g:paginate total="${topicInstanceTotal}"/>
    </div>
</div>

<div id="invitationDiv">
    <g:form action="bindInvitation">
        <pre>
            Email :<g:textField name="email1"/>
            Email :<g:textField name="email2"/>
            Email :<g:textField name="email3"/>
            Content
            <g:textArea name="content" rows="" cols=""/>
            <g:submitButton name="submit" value="Invite"/>
        </pre>
    </g:form>
</div>
<script type="text/javascript">
    urls = {

    }
</script>
</body>
</html>
