<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="jQueryMobile" name="layout">
    <title>Login</title>

</head>

<body>
<div data-role="content">

    <div class="content-primary">
        <g:render template="/login/register"/>
    </div>

    <div class="content-secondary">
        <h1><img src="${resource(dir: 'images', file: 'iglogo.png')}" alt="Intelligrape"/></h1>
        <g:render template="/login/login"/>
        <g:render template="/login/forgotPassword"/>
    </div>

</div>
</body>
</html>