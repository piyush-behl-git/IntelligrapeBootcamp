<table>
    <thead>
    <tr>
        <th>Topic</th>

        <g:sortableColumn property="dateCreated" title="Date Created"/>

        <g:sortableColumn property="lastUpdated" title="Last Updated"/>

        <g:sortableColumn property="seriousness" title="Seriousness"/>

    </tr>
    </thead>
    <tbody>
    <g:each in="${list}" status="i" var="item">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

            <td>${fieldValue(bean: item, field: "topic")}</td>

            <td><ls:formattedDate date="${item.dateCreated}"/></td>

            <td><ls:formattedDate date="${item.lastUpdated}"/></td>

            <td>${fieldValue(bean: item, field: "seriousness")}</td>

        </tr>
    </g:each>
    </tbody>
</table>