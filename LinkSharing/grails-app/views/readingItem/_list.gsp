<%@ page import="com.ig.bc.DocumentResource; com.ig.bc.LinkResource" %>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<table id="dlist">

    <thead>
    <tr>
        <th><g:select id="selectReadingItem" name="selectReadingItem"
                      from="['Select Options', 'Check All', 'Uncheck All', 'Inverse']">
        </g:select>
        </th>
        <th><g:message code="readingItem.isFavorite.label" default="Favorite'"/></th>

        <th><g:message code="readingItem.resource.label" default="Resource"/></th>

        <th>Topic</th>

    </tr>
    </thead>
    <tbody>
    <g:each in="${list}" status="i" var="item">
        <g:if test="${item.isRead}">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" style="cursor: pointer; font-weight: normal;" id="row${item.id}">
        </g:if>
        <g:else>
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" style="cursor: pointer; font-weight: bold;" id="row${item.id}">
        </g:else>
        <td>
            <g:checkBox name="status" value="${item.id}"/>
        </td>
        <td>
            <g:if test="${item.isFavorite}">
                <img id="img${item.id}" name="on" id="fav-img" src="${resource(dir: 'images', file: 'star_on.png')}" alt="true" onclick="changeFav(${item.id})">
            </g:if>
            <g:else>
                <img id="img${item.id}" name="off" id="fav-img" src="${resource(dir: 'images', file: 'star_off.png')}" alt="false" onclick="changeFav(${item.id})">
            </g:else>
        </td>

        <td>
            <span onclick="markCurrentRead(${item.id})">
                <g:if test="${item.resource.instanceOf(LinkResource)}">
                    <a href="${item.resource.url}" target="_blank">
                </g:if>
                <g:if test="${item.resource.instanceOf(DocumentResource)}">
                    <a href="${createLink(action: 'download', controller: 'documentResource', id: item.resource.id)}" target="_blank">
                </g:if>

                ${fieldValue(bean: item, field: "resource")}
            </a>
            </span>
        </td>

        <td>${item.resource.topic.name}</td>

        </tr>
    </g:each>
    </tbody>
</table>