<ul data-role="listview" data-theme="c" data-inset="true">
    <li data-role="list-divider">
        <div class="ui-grid-e">
            <div class="ui-block-a"><g:select data-theme="c" id="selectTopic" data-iconpos="notext" name="selectTopic"
                                              from="['Select Option','Check All', 'Uncheck All', 'Inverse']"></g:select></div>

            <div class="ui-block-b"><h3>Topic</h3></div>

            <div class="ui-block-c"><h3>Date Created</h3></div>

            <div class="ui-block-d"><h3>Last Updated</h3></div>

            <div class="ui-block-e"><h3>Seriousness</h3></div>
        </div>
    </li>

    <g:each in="${subscriptionInstanceList}" status="i" var="subscription">
        <li>
            <g:checkBox data-role="button" name="status" value="${subscription.id}" checked="false"/>
            <div class="ui-grid-e">
                <div class="ui-block-a"></div>

                <div class="ui-block-b">${subscription.topic}</div>

                <div class="ui-block-c"><ls:formattedDate date="${subscription.dateCreated}"/></div>

                <div class="ui-block-d"><ls:formattedDate date="${subscription.lastUpdated}"/></div>

                <div class="ui-block-e">${subscription.seriousness}</div>
            </div>
        </li>
    </g:each>
</ul>