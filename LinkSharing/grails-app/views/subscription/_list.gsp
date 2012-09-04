<table>
    <thead>
    <tr>
        <th><g:message code="subscription.topic.label" default="Topic" /></th>

        <g:sortableColumn property="dateCreated" title="${message(code: 'subscription.dateCreated.label', default: 'Date Created')}" />

        <g:sortableColumn property="lastUpdated" title="${message(code: 'subscription.lastUpdated.label', default: 'Last Updated')}" />

        <g:sortableColumn property="seriousness" title="${message(code: 'subscription.seriousness.label', default: 'Seriousness')}" />

        <th><g:message code="subscription.subscriber.label" default="Subscriber" /></th>


    </tr>
    </thead>
    <tbody>
    <g:each in="${list}" status="i" var="item">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td><g:link action="show" id="${item.id}">${fieldValue(bean: item, field: "topic")}</g:link></td>

            <td><ls:formattedDate date="${item.dateCreated}" /></td>

            <td><ls:formattedDate date="${item.lastUpdated}" /></td>

            <td>${fieldValue(bean: item, field: "seriousness")}</td>

            <td>${fieldValue(bean: item, field: "subscriber")}</td>


        </tr>
    </g:each>
    </tbody>
</table>