<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta content="main" name="layout">
    <title>Login</title>
    <style type="text/css">
        #loginDiv {
            margin: 0px auto;
            width: 300px;
            padding: 5px 5px;
            background-color: #dddddd;
            border: 1px;
        }
        .inputDiv {
            float: left;
        }
        .inputDiv label input {
            width: 150px;
        }

    </style>
</head>
<body>
<div id="loginDiv">
    <form action="${createLink(controller: 'user', action: 'loginHandler')}" method="POST">
      <div class="inputDiv">
          <label for="email"> Email ID </label>
          <input type="text" name="email" size="15"/>
      </div>
      <div class="inputDiv">
          <label for="password">Password</label>
          <input type="password" name="password" size="15"/>
      </div>
      <div class="submit">
          <input type="submit" value="Login" />
          <input type="checkbox">Stay Signed in</input>
      </div>
    </form>
</div>
</body>
</html>