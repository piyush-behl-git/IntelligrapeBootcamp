<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="jQueryMobile">
    <title>Dashboard</title>
</head>

<body>
<div data-role="content">

    <div class="content-primary">
        <ls:unreadItems  count='10'/>
    </div>

    <div class="content-secondary">
        <g:render template="topics" model="[list: highestSubscribedTopic, listName: listName]"/>
        <ls:ownedTopics listName='Owned Topics'/>
        <ls:subscribedTopics listName='Subscribed Topics'/>
    </div>

</div>
%{--<div class="my-ui-grid-a">--}%
    %{--<div class="my-ui-block-a"><ls:unreadItems  count='10'/></div>--}%
    %{--<div class="my-ui-block-b"><g:render template="topics" model="[list: highestSubscribedTopic, listName: listName]"/> </div>--}%
%{--</div>--}%
%{--<div class="my-ui-grid-a">--}%
    %{--<div class="my-ui-block-a"><ls:ownedTopics listName='Owned Topics'/></div>--}%
    %{--<div class="my-ui-block-b"><ls:subscribedTopics listName='Subscribed Topics'/></div>--}%
%{--</div>--}%
</body>
</html>