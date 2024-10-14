
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catch</title>
</head>
<body>
    <c:set var="number" value="5.7" scope="page"/>

    <c:catch var="myExceptio">
        <c:if test="${number <9}">
            <c:out value="Number ${number}"/> is smaller then 9
        </c:if>
    </c:catch>

    <c:if test="${not empty myExceptio}">
        <br/>
        <c:out value="${number}"/>
        <br/>
        <img src=""  alt="image">
        Generation exception:
        ${myExceptio}
    </c:if>
</body>
</html>
