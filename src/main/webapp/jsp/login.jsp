<%--
  Created by IntelliJ IDEA.
  User: arturmarkowski
  Date: 09/10/2024
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PASSION FLOWERS</title>
</head>
<body>
    <form method="POST" action="controller">
        <input type="hidden" name="command" value="login" />
            Login: <br/>
        <label>
            <input type="text" name="username" value=""/>
        </label>
        <br/>Password: <br/>
        <label>
            <input type="password" name="password" value=""/>
        </label> <br/>
        ${errorLoginPassMessage} <br/>
        ${wrongAction} <br/>
        ${nullPage} <br/>
        <input type="submit" value="login"/>
    </form>
</body>
</html>
