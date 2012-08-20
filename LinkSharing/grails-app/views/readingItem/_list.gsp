<table>
    <thead>
    <tr>

        <g:sortableColumn property="favorite" title="${message(code: 'readingItem.favorite.label', default: 'Favorite')}" />

        <g:sortableColumn property="read" title="${message(code: 'readingItem.read.label', default: 'Read')}" />

        <th><g:message code="readingItem.resource.label" default="Resource" /></th>

        <th><g:message code="readingItem.user.label" default="User" /></th>

    </tr>
    </thead>
    <tbody>
    <g:each in="${list}" status="i" var="item">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link action="show" id="${item.id}">${fieldValue(bean: item, field: "favorite")}</g:link></td>

            <td><g:formatBoolean boolean="${item.read}" /></td>

            <td>${fieldValue(bean: item, field: "resource")}</td>

            <td>${fieldValue(bean: item, field: "user")}</td>

        </tr>
    </g:each>
    </tbody>
</table>