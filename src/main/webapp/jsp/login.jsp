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
<label/>
    <form action="${pageContext.request.contextPath}/mail-servlet", method="post">
        <table>
            <tr>
                <td>Send to:</td>
                <td>
                    <label>
                        <input type="text" name="to"/>
                    </label>
                </td>
            </tr>
            <tr>
                <td>Subject :</td>
                <td>
                    <label>
                        <input type="text" name="subject">
                    </label>
                </td>
            </tr>
        </table>
        <hr/>
        <label>
            <textarea type="text" name="body" rows="5" cols="45">Message text</textarea>
        </label>
        <br/><br/>
        <input type="submit" value="Send message!"/>
    </form>
</body>
</html>
