<%@ page import="ru.itis.javalab.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <%
        User user = (User) request.getAttribute("user");
    %>
    Hello, <%=user.getUsername()%>
</div>
</body>
</html>