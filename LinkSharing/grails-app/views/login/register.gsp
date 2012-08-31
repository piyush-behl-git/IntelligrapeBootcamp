<%@ page import="com.ig.bc.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:form action='registrationHandler' method="get" name="registrationForm">
    <fieldset class="form">
        <g:render template="form"/>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="Register" class="save" value="Register"/>
    </fieldset>
</g:form>
<script type="text/javascript">
    urls = {
        checkEmailUrl: "${createLink(controller: "login", action: "checkEmailUrl")}"
    }
</script>
</body>
</html>