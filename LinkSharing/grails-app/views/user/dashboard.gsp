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
    Unread Items
    <ls:unreadItems count="10" />
    %{--<g:render template="/readingItem/list" model="[list: unreadItems]"/>--}%
    Subscriptions
    <ls:subscribedTopics />
    %{--<g:render template="/subscription/list" model="[list: subscriptions]"/>--}%
    <ls:ownedTopics count="5">Owned Topics</ls:ownedTopics>
    %{--<g:render template="/topic/list" model="[list: topics]" />--}%
</body>
</html>