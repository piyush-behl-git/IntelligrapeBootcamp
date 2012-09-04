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
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" style="cursor: pointer">
            <td>
                <g:checkBox name="status" value="${item.id}"/>
            </td>
            <td>
                <g:if test="${item.isFavorite}">
                    <img name="on" id="fav-img" src="${resource(dir: 'images', file: 'star_on.png')}" alt="true" onclick="changeFavStatusUnmark(${item.id})">
                </g:if>
                <g:else>
                    <img name="off" id="fav-img" src="${resource(dir: 'images', file: 'star_off.png')}" alt="false" onclick="changeFavStatusMark(${item.id})">
                </g:else>
            </td>

            <td>
                <g:if test="${item.isRead}">
                    <span onclick="markCurrentUnread(${item.id})">
                        ${fieldValue(bean: item, field: "resource")}
                    </span>
                </g:if>
                <g:else>
                    <span style="font-weight: bold;" onclick="markCurrentRead(${item.id})">
                        ${fieldValue(bean: item, field: "resource")}
                    </span>
                </g:else>
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