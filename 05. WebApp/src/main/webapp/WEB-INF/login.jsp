<%--
  Created by IntelliJ IDEA.
  User: romanleontev
  Date: 22.10.2020
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="username">
    <input type="text" name="password">
    <input type="submit">
</form>
</body>
</html>
