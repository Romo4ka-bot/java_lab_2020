<%--
  Created by IntelliJ IDEA.
  User: romanleontev
  Date: 28.10.2020
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.itis.javalab.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Marsel
  Date: 15.10.2020
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <h1 style="color: ${cookie.get("color").value}">USERS</h1>
    <form action="/users" method="post">
        <select name="color">
            <option value="red">RED</option>
            <option value="green">GREEN</option>
            <option value="blue">BLUE</option>
        </select>
        <input type="submit" value="OK">
    </form>
</div>
<div>
    <table>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <%
            List<User>  users = (List<User>) request.getAttribute("usersForJsp");
            for (User user : users) {
        %>
        <tr>
            <td><%=user.getFirstName()%></>
            <td><%=user.getLastName()%></>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>

