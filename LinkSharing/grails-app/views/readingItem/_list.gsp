<table>
    <thead>
    <tr>

        <g:sortableColumn property="favorite" title="${message(code: 'readingItem.isFavorite.label', default: 'Favorite')}" />

        <g:sortableColumn property="read" title="${message(code: 'readingItem.isRead.label', default: 'Read')}" />

        <th><g:message code="readingItem.resource.label" default="Resource" /></th>

        <th><g:message code="readingItem.user.label" default="User" /></th>

    </tr>
    </thead>
    <tbody>
    <g:each in="${list}" status="i" var="item">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link controller="readingItem" action="show" id="${item.id}">${fieldValue(bean: item, field: "isFavorite")}</g:link></td>

            <td><g:link controller="readingItem" action="markRead" id="${item.id}"><g:formatBoolean boolean="${item.isRead}" /></g:link></td>

            <td>${fieldValue(bean: item, field: "resource")}</td>

            <td>${fieldValue(bean: item, field: "user")}</td>

        </tr>
    </g:each>
    </tbody>
</table>