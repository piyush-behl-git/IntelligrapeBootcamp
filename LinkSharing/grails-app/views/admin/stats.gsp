<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.ig.bc.User" %>
<html>
<head>
    <meta name="layout" content="jQueryMobile">
    <title>Stats</title>
</head>

<body>
<div id="stats">
    <g:render template="/admin/userList" model="[userInstanceList: userInstanceList]"/>
    <div class="pagination">
        <g:paginate total="${userInstanceTotal}"/>
    </div>
</div>

</body>
</html>