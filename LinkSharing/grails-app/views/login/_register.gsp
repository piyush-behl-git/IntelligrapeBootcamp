<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<%@ page import="com.ig.bc.User" %>
<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} required">
    <label for="email">
        <g:message code="user.email.label" default="Email"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="email" name="email" value=""/>
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

<div style="padding: 100px 0 0 250px;">
    <g:form controller="user" action="loginHandler" method="post">

        <div id="login-box">
            <H2>Register New User</H2>
            <br/>
            <br/>

            <div id="login-box-name" style="margin-top:20px;">Email:</div>

            <div id="login-box-field" style="margin-top:20px;">
                <g:field name="email" type="email" class="form-login" title="Username" value="" size="30" maxlength="2048"/>
            </div>

            <div id="login-box-name">Password:</div>

            <div id="login-box-field">
                <g:passwordField name="password" class="form-login" title="Password" value="" size="30" maxlength="2048"/>
            </div>
            <br/>
            <span class="login-box-options"><g:checkBox name="rememberMe" value="1"/>  Remember Me</span>
            <br/>
            <br/>
            <g:submitButton name="login" style="background-image: url(${resource(dir: 'images', file: 'login-btn.png')}); width: 103px;height:42px;margin: 0 0 0 90px" value=""/>
        </div>
    </g:form>
</div>