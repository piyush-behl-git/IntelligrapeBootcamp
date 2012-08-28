<%--
  Created by IntelliJ IDEA.
  User: intelligrape
  Date: 20/8/12
  Time: 6:31 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Dashboard</title>
</head>

<body>
<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="stats" controller="admin" action="stats">Statistics</g:link></li>
        <li><g:link class="logout" controller="login" action="logout">Logout</g:link></li>
    </ul>
</div>

<div>
    <span class="unread">Unread Items</span>
    <ls:unreadItems count="10"/>
</div>

<div>
    Subscriptions
    <ls:subscribedTopics/>
</div>

<div>
    <ls:ownedTopics count="5">Owned Topics</ls:ownedTopics>
</div>

<div>
    Highest Subscribed Topic
    <g:render template="/topic/list" model="[list: highestSubscribedTopic]"/>
</div>
<script type="text/javascript">
    $(function(){
      $(".unread").on("click", function(){
          $("#dlist").fadeToggle(1000)
      });
    });
</script>
</body>
</html>