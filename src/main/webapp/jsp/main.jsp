<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Main</title>
</head>
<body>
<h1> Hello ${user}</h1>
${mas[0]}
<br/>
${mas}
<br/>
${filter_msg}
<br/>
<ul>
    <c:forEach var="item" items="${mas}">
        <li>${item}</li>
    </c:forEach>
</ul>
<br/>
${pageContext.request.characterEncoding}
<br/>
<%--Why doesn't work--%>
<c:set var="user1" value="${user}" scope="page"/>
<c:if test="${not empty user1 and user1 eq '123'}">
        Hello ${user1}
</c:if>
<br/>
<a href="controller?command=logout">logout</a>
<<br/>
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
    <img src="images/error/error300.png" alt="erro image">
    Generation exception:
    ${myExceptio}
</c:if>
</body>
</html>
