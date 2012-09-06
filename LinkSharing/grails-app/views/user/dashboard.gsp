<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Dashboard</title>
</head>

<body>
<a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="nav_tabs">
    <ul>
        <li><a href="#unread_items_tab">Unread Items</a></li>
        <li><a href="#subscriptions_tab">Subscriptions</a></li>
        <li><a href="#owned_topics_tab">Owned Topics</a></li>
        <li><a href="#highest_subscribed_topics_tab">Highest Subscribed Topic</a></li>
    </ul>

    <div id="unread_items_tab">
        <ls:unreadItems count="10"/>
    </div>

    <div id="subscriptions_tab">
        <ls:subscribedTopics/>
    </div>

    <div id="owned_topics_tab">
        <ls:ownedTopics count="5">Owned Topics</ls:ownedTopics>
    </div>

    <div id="highest_subscribed_topics_tab">
        <g:render template="/topic/list" model="[list: highestSubscribedTopic]"/>
    </div>
</div>
</body>
</html>