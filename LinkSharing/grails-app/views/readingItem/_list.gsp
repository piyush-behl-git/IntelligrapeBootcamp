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

        <th><g:message code="readingItem.user.label" default="User"/></th>

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
            <span onclick="markCurrentRead(${item.id})">${fieldValue(bean: item, field: "resource")}</span>
        </td>

        <td>${fieldValue(bean: item, field: "user")}</td>

        </tr>
    </g:each>
    </tbody>
</table>
<fieldset class="buttons">
    <input type="button" name="mark-read-button" class="save" value="Mark Read"/>
    <input type="button" name="mark-unread-button" class="save" value="Mark Unread"/>
</fieldset>