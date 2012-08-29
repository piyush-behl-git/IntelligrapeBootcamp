<%@ page import="com.ig.bc.User" %>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
    <label for="email">
        <g:message code="user.email.label" default="Email"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="email" name="email" required="" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'dateOfBirth', 'error')} ">
    <label for="dateOfBirth">
        <g:message code="user.dateOfBirth.label" default="Date Of Birth"/>

    </label>
    <g:textField id="datepicker" name="dateOfBirth" value="" default="none"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'fullName', 'error')} ">
    <label for="fullName">
        <g:message code="user.fullName.label" default="Full Name"/>

    </label>
    <g:textField name="fullName" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'isMale', 'error')} ">
    <label for="male">
        <g:message code="user.male.label" default="Male"/>

    </label>
    <g:checkBox name="male" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} ">
    <label for="password">
        <g:message code="user.password.label" default="Password"/>

    </label>
    <g:textField name="password" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'confirmPassword', 'error')} ">
    <label for="confirmPassword">
        Confirm Password
    </label>
    <g:textField name="confirmPassword" value=""/>
</div>