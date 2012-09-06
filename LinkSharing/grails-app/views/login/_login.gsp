<g:form controller="user" action="loginHandler" method="post">

    <div id="login-box">
        <H2>Login</H2>
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
        <g:submitButton name="login"
                        style="border: 0px;background: #2F5B9A url(${resource(dir: 'images', file: 'login-btn.png')}); width: 103px;height:42px;margin: 0 0 0 90px;"
                        value=""/>
    </div>
</g:form>
