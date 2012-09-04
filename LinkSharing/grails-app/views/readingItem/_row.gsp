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