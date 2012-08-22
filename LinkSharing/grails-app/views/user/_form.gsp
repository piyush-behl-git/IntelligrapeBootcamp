<%@ page import="com.ig.bc.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
    <label for="email">
        <g:message code="user.email.label" default="Email"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="email" name="email" required="" value="${userInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'dateOfBirth', 'error')} ">
    <label for="dateOfBirth">
        <g:message code="user.dateOfBirth.label" default="Date Of Birth"/>

    </label>
    <g:datePicker name="dateOfBirth" precision="day" value="${userInstance?.dateOfBirth}" default="none"
                  noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'fullName', 'error')} ">
    <label for="fullName">
        <g:message code="user.fullName.label" default="Full Name"/>

    </label>
    <g:textField name="fullName" value="${userInstance?.fullName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'isMale', 'error')} ">
    <label for="male">
        <g:message code="user.male.label" default="Male"/>

    </label>
    <g:checkBox name="male" value="${userInstance?.isMale}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
    <label for="password">
        <g:message code="user.password.label" default="Password"/>

    </label>
    <g:textField name="password" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'confirmPassword', 'error')} ">
    <label for="confirmPassword">
        Confirm Password
    </label>
    <g:textField name="confirmPassword" value="${userInstance?.confirmPassword}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'subscriptions', 'error')} ">
    <label for="subscriptions">
        <g:message code="user.subscriptions.label" default="Subscriptions"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${userInstance?.subscriptions ?}" var="s">
            <li><g:link controller="subscription" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="subscription" action="create"
                    params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'subscription.label', default: 'Subscription')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'topics', 'error')} ">
    <label for="topics">
        <g:message code="user.topics.label" default="Topics"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${userInstance?.topics ?}" var="t">
            <li><g:link controller="topic" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="topic" action="create"
                    params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'topic.label', default: 'Topic')])}</g:link>
        </li>
    </ul>

</div>

