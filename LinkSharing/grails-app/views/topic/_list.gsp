<table>
    <thead>
    <tr>

        <g:sortableColumn property="name" title="${message(code: 'topic.name.label', default: 'Name')}" />

        <th><g:message code="topic.owner.label" default="Owner" /></th>

        <g:sortableColumn property="visibility" title="${message(code: 'topic.visibility.label', default: 'Visibility')}" />

    </tr>
    </thead>
    <tbody>
    <g:each in="${list}" status="i" var="item">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td><g:link action="show" id="${item.id}">${fieldValue(bean: item, field: "name")}</g:link></td>

            <td>${fieldValue(bean: item, field: "owner")}</td>

            <td>${fieldValue(bean: item, field: "visibility")}</td>

        </tr>
    </g:each>
    </tbody>
</table>