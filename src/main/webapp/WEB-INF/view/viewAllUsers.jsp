<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Все пользователи</title>
</head>
<body>
<h2>Пользователи</h2>
<c:choose>
    <c:when test="${not empty noUsers}">
        <p style="color: red;">Ошибка: ${noUsers}</p>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <th>Идентификатор</th>
                <th>Роль пользователя</th>
                <th>Электронная почта</th>
                <th>Пароль</th>
            </tr>
            <jsp:useBean id="users" scope="request" type="java.util.List"/>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.role}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>