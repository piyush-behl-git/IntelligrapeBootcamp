<%--
  Created by IntelliJ IDEA.
  User: intelligrape
  Date: 20/8/12
  Time: 5:56 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
  <title>Stats</title>
    <style type="text/css">
      #stats {
          width: 400px;
          margin: 5px 5px;
          display: block;
      }
    </style>
</head>
<body>
<div id="stats">
    Number of Users : ${numberOfUsers} <br>
    Number of Subscriptions : ${numberOfSubscriptions}
</div>
</body>
</html>