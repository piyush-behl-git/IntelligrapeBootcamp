<ul data-role="listview" data-theme="c" data-inset="true">
    <li data-role="list-divider">
        <div class="ui-grid-c">
            <div
                    class="ui-block-a"><g:select data-theme="c" id="selectTopic" data-iconpos="notext" name="selectTopic"
                                                 from="['Select Option','Check All', 'Uncheck All', 'Inverse']"></g:select></div>
            <div class="ui-block-b"><h3>Name</h3></div>
            <div class="ui-block-c"><h3>Owner</h3></div>
            <div class="ui-block-d"><h3>Visibility</h3></div>
        </div>
    </li>
    <g:each in="${list}" status="i" var="item">
        <li><g:checkBox data-role="button" name="status" value="${item.topic.id}" disabled="${item.isSubscribed}" checked="false"/>
            <div class="ui-grid-c">
                <div class="ui-block-a"></div>
                <div class="ui-block-b"><g:link controller="topic" action="show" id="${item.topic.id}">${item.topic.name}</g:link></div>
                <div class="ui-block-c">${item.topic.owner.fullName}</div>
                <div class="ui-block-d">${item.topic.visibility}</div>
            </div>
        </li>
    </g:each>
</ul>