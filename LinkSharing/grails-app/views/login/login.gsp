<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="main" name="layout">
    <title>Login</title>
    <style type="text/css">
    #loginDiv {
        margin: 0px 0px;
        width: 43.5%;
        float: left;
        padding: 5px 5px;
    }

    #login-box {
        width: 333px;
        height: 352px;
        padding: 58px 76px 0 76px;
        color: #ebebeb;
        font: 12px Arial, Helvetica, sans-serif;
        background: url("${resource(dir: 'images', file: 'login-box-backg.png')}") no-repeat left top;
    }

    #login-box img {
        border: none;
    }

    #login-box h2 {
        padding: 0;
        margin: 0;
        color: #ebebeb;
        font: bold 44px "Calibri", Arial;
    }

    #login-box-name {
        float: left;
        display: inline;
        width: 80px;
        text-align: right;
        padding: 14px 10px 0 0;
        margin: 0 0 7px 0;
    }

    #login-box-field {
        float: left;
        display: inline;
        width: 230px;
        margin: 0;
        margin: 0 0 7px 0;
    }

    .form-login {
        width: 205px;
        padding: 10px 4px 6px 3px;
        border: 1px solid #0d2c52;
        background-color: #1e4f8a;
        font-size: 16px;
        color: #ebebeb;
    }

    .login-box-options {
        clear: both;
        padding-left: 87px;
        font-size: 11px;
    }

    .login-box-options a {
        color: #ebebeb;
        font-size: 11px;
    }

    #registrationDiv {
        width: 55%;
        float: right;
        background-color: #ffffaa;
        border: 1px;
    }

    #forgotPasswordDiv {
        width: 44%;
        background-color: #ffffaa;
        float: left;
        border: 1px;
    }
    </style>
</head>

<body style="background-image: url(${resource(dir: 'images', file: 'page-background.JPG')})">
<div id="loginDiv">
    <g:render template="/login/login"/>
</div>
</body>
</html>