<%@ page import="com.ig.bc.DocumentResource; com.ig.bc.LinkResource" %>
<div data-role="collapsible-set" data-collapsed="false" data-theme="c" data-content-theme="c">
<g:each in="${list}" var="item" status="i">
    <g:if test="${i == 0}"><div data-role="collapsible" data-theme="b" data-content-theme="d" data-collapsed="false"></g:if>
    <g:else><div data-role="collapsible" data-theme="b" data-content-theme="d"></g:else>
    <h3>${item.key}</h3>
    <ul data-role="listview" data-theme="c" data-inset="true">
        <g:each in="${item.value}" var="readingItem">
            <li><g:if test="${readingItem.isFavorite}"><img class="ui-li-icon" id="img${readingItem.id}" name="on" src="${resource(dir: 'images', file: 'star_on.png')}"
                                                            alt="true" onclick="changeFav(${readingItem.id})"></g:if>
                <g:else><img class="ui-li-icon"
                             id="img${readingItem.id}" name="off"
                             src="${resource(dir: 'images', file: 'star_off.png')}"
                             alt="false"
                             onclick="changeFav(${readingItem.id})"></g:else>
                <span
                        onclick="markCurrentRead(${readingItem.id})"><g:if
                        test="${readingItem.resource.instanceOf(LinkResource)}"><a href="${readingItem.resource.url}" onclick="window.location.reload()" target="_blank"></g:if> <g:if
                        test="${readingItem.resource.instanceOf(DocumentResource)}"><a href="${createLink(action: 'download', controller: 'documentResource', id: readingItem.resource.id)}"></g:if> ${readingItem.resource}</a>
                </span></li>
        </g:each>
    </ul>
    </div>
</g:each>
</div>
