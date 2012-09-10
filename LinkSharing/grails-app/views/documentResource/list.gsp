<%@ page import="com.ig.bc.DocumentResource" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="jQueryMobile">
    <g:set var="entityName" value="${message(code: 'documentResource.label', default: 'DocumentResource')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<h1>Document Resources</h1>
<div class="status-message" role="status"></div>
<ul data-role="controlgroup" data-type="horizontal" class="localnav">
    <li><input type="button" name="mark-read-button" data-transition="fade" value="Mark Read"></li>
    <li><input type="button" name="mark-unread-button" data-transition="fade" value="Mark Unread"></li>
</ul>
<ul data-role="listview" data-theme="c" data-inset="true" data-content-theme="d">
    <li data-role="list-divider">
        <div class="ui-grid-e">
            <div
                    class="ui-block-a"><g:select data-theme="c" id="selectTopic" data-iconpos="notext" name="selectTopic"
                                                 from="['Select Option','Check All', 'Uncheck All', 'Inverse']"></g:select></div>
            <div class="ui-block-b"><h3>Title</h3></div>
            <div class="ui-block-c"><h3>File Name</h3></div>
            <div class="ui-block-d"><h3>Date Created</h3></div>
            <div class="ui-block-e"><h3>Last Updated</h3></div>
            <div class="ui-block-f"><h3>Owner</h3></div>
            <div class="ui-block-g"><h3>Summary</h3></div>
        </div>
    </li>

    <div data-role="collapsible-set" data-theme="c" data-content-theme="d">
        <g:each in="${topicResourceMap}" status="i" var="item">
            <g:if test="${i == 0}"><div data-role="collapsible" data-collapsed='false'></g:if>
            <g:else><div data-role="collapsible"></g:else>

            <h3><li data-role="list-divider">${item.key}</li></h3>
            <ul data-role="listview" data-theme="d">
                <g:each in="${item.value}" var="readingItem">
                    <li>
                        <g:checkBox data-role="button" name="status" value="${readingItem.resource.id}" checked="false"/>
                        <g:if test="${readingItem.isFavorite}"><img id="img${readingItem.id}" class="ui-li-icon" name="on" src="${resource(dir: 'images', file: 'star_on.png')}" alt="true" onclick="changeFav(${readingItem.id})"></g:if>
                        <g:else><img id="img${readingItem.id}" class="ui-li-icon" name="off" src="${resource(dir: 'images', file: 'star_off.png')}" alt="false" onclick="changeFav(${readingItem.id})"></g:else>
                        <div class="ui-grid-e">
                            <div class="ui-block-a"></div>
                            <div class="ui-block-b"><g:link action="show" id="${readingItem.resource.id}">${readingItem.resource.title}</g:link></div>
                            <div class="ui-block-c"><a href="${createLink(action: 'download', controller: 'documentResource', id: readingItem.resource.id)}" target="_blank">${readingItem.resource.fileName}</a></div>
                            <div class="ui-block-d"><ls:formattedDate date="${readingItem.resource.dateCreated}"/></div>
                            <div class="ui-block-e"><ls:formattedDate date="${readingItem.resource.lastUpdated}"/></div>
                            <div class="ui-block-f">${readingItem.resource.owner}</div>
                            <div class="ui-block-g">${readingItem.resource.summary}</div>
                        </div>
                    </li>
                </g:each>
            </ul>
            </div>
        </g:each>
    </div>
</ul>
<div class="pagination">
    <g:paginate total="${documentResourceInstanceTotal}"/>
</div>
</body>
</html>
