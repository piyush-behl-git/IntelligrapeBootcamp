<div data-role="collapsible-set" data-collapsed="false" data-theme="c" data-content-theme="c">

    <ul data-role="listview" data-theme="c" data-inset="true">
        <li data-role="list-divider">${listName}</li>
        <g:each in="${list}" status="i" var="item">
            <li>${item.name}</li>
        </g:each>
    </ul>
</div>