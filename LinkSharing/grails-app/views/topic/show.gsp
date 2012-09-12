<%@ page import="com.ig.bc.LinkResource; com.ig.bc.DocumentResource; com.ig.bc.Topic" %>
<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="jQueryMobile">
    <g:set var="entityName" value="${message(code: 'topic.label', default: 'Topic')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="my-resource-content-primary">
    <div data-role="collapsible-set" data-collapsed="false">
        <div id="topicStatusDiv"></div>
        <ul id="topicDetailsList" data-role="listview" data-theme="c" data-inset="true">
            <li data-role="list-divider">${topicInstance.name} Details</li>
            <li data-role="fieldcontain">
                <label for="name">Topic Name</label>
                <g:textField name="name" value="${topicInstance.name}" disabled="true"/>
            </li>
            <li data-role="fieldcontain">
                <label for="visibility">Visibility</label>
                <g:textField name="visibility" value="${topicInstance.visibility}" class="ui-disabled"/>
            </li>
            <li data-role="fieldcontain">
                <label for="owner">Owner</label>
                <g:textField name="owner" value="${topicInstance.owner.fullName}" class="ui-disabled"/>
            </li>
        </ul>
        <ul data-role="controlgroup" data-type="horizontal" class="localnav">
            <g:if test="${subscriptionStatus}"><li><input type="button" name="individual-topic-subscribe-button" data-transition="fade" value="Unsubscribe" onclick="unsubscribeIndividualTopic(${topicInstance.id})"></li> </g:if>
            <g:else><li><input data-ajax="false" type="button" name="individual-topic-subscribe-button" data-transition="fade" value="Subscribe" onclick="subscribeIndividualTopic(${topicInstance.id})"></li></g:else>
            <li><input type="button" name="mark-read-button" data-transition="fade" value="Edit Topic" data-inline="true"></li>
            <li><input type="button" name="mark-read-button" data-transition="fade" value="Delete Topic" data-inline="true"></li>
            <li><input type="button" name="mark-read-button" data-transition="fade" value="Add Resource" data-inline="true"></li>
            <li><input type="button" name="mark-read-button" data-transition="fade" value="Invite" data-inline="true"></li>
        </ul>
    </div>
</div>

<div class="my-resource-content-secondary">
    <div data-role="collapsible-set" data-collapsed="false">
        <div id="topicResourceStatusDiv"></div>
        <ul id="topicResources" data-role="listview" data-theme="c" data-inset="true">
            <li data-role="list-divider">
                <div class="my-resource-ui-grid-a">
                    <div class="my-resource-ui-block-a">Resource</div>
                    <div class="my-resource-ui-block-b"></div>
                </div>
            </li>
            <g:each in="${topicInstance.resources}" var="resource">
                <li id="li${resource.id}">
                    <div class="my-resource-ui-grid-a">
                        <div class="my-resource-ui-block-a"><g:if test="${resource.instanceOf(DocumentResource)}"><a
                                href="${createLink(action: 'download', controller: 'documentResource', id: resource.id)}" target="_blank">${resource.fileName}</a></g:if><g:else><a
                                href="${resource.url}">${resource.url}</a></g:else></div>

                        <div class="my-resource-ui-block-b"><a onclick="deleteResource(${resource.id})" data-role="button" data-icon="delete" data-mini="true"
                                                               data-iconpos="notext">Delete</a></div>
                    </div>
                </li>
            </g:each>
        </ul>
    </div>
</div>
</body>
</html>
