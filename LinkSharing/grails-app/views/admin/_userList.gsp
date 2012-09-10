<ul data-role="listview" data-theme="c" data-inset="true">
    <li data-role="list-divider">
        <div class="ui-grid-e">
            <div class="ui-block-a"><h3>Full Name</h3></div>
            <div class="ui-block-b"><h3>Email</h3></div>
            <div class="ui-block-c"><h3>Date Of Birth</h3></div>
            <div class="ui-block-d"><h3>Date Created</h3></div>
            <div class="ui-block-e"><h3>Last Updated</h3></div>
            <div class="ui-block-f"><h3>Male</h3></div>
        </div>
    </li>
    <g:each in="${userInstanceList}" status="i" var="userInstance">
        <li>
        <div class="ui-grid-e">
            <div class="ui-block-a">${userInstance.fullName}</div>
            <div class="ui-block-b">${userInstance.email}</div>
            <div class="ui-block-c"><ls:formattedDate date="${userInstance.dateOfBirth}"/></div>
            <div class="ui-block-d"><ls:formattedDate date="${userInstance.dateCreated}"/></div>
            <div class="ui-block-e"><ls:formattedDate date="${userInstance.lastUpdated}"/></div>
            <div class="ui-block-f"><g:if test="${userInstance.isMale}">Yes</g:if><g:else>No</g:else></div>
        </div>
        </li>
    </g:each>
</ul>