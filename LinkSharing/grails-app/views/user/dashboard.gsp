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

<div id="nav_tabs">
    <ul>
        <li><a href="#unread_items_tab">Unread Items</a></li>
        <li><a href="#subscriptions_tab">Subscriptions</a></li>
        <li><a href="#owned_topics_tab">Owned Topics</a></li>
        <li><a href="#highest_subscribed_topics_tab">Highest Subscribed Topic</a></li>
    </ul>

    <div id="unread_items_tab">
        <span class="unread">Unread Items</span>
        <ls:unreadItems count="10"/>
    </div>

    <div id="subscriptions_tab">
        Subscriptions
        <ls:subscribedTopics/>
    </div>

    <div id="owned_topics_tab">
        <ls:ownedTopics count="5">Owned Topics</ls:ownedTopics>
    </div>

    <div id="highest_subscribed_topics_tab">
        Highest Subscribed Topic
        <g:render template="/topic/list" model="[list: highestSubscribedTopic]"/>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $(".unread").on("click", function () {
            $("#dlist").fadeToggle(1000)
        });
    });
    urls = {
        markReadUrl:"${createLink(controller: "readingItem", action: "markRead")}",
        markUnreadUrl:"${createLink(controller: 'readingItem', action: 'markUnread')}",
        markFavUrl:"${createLink(controller: 'readingItem', action: 'markFav')}",
        unmarkFavUrl:"${createLink(controller: 'readingItem', action: 'unmarkFav')}",
        markCurrentReadUrl:"${createLink(controller: 'readingItem', action: 'markCurrentRead')}",
        markCurrentUnreadUrl:"${createLink(controller: 'readingItem', action: 'markCurrentUnread')}"
    }
</script>
</body>
</html>