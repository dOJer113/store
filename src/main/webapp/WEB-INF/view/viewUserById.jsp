<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Информация о пользователе</title>
</head>
<body>
<c:choose>
    <c:when test="${user.userId == 0}">
        <p style="color: red;">Ошибка: Пользователь не найден.</p>
    </c:when>
    <c:otherwise>
        <h2>Информация о пользователе</h2>
        <p>Идентификатор: ${user.userId}</p>
        <p>Электронная почта: ${user.email}</p>
        <p>Роль: ${user.role}</p>
        <p>Пароль: ${user.password}</p>
    </c:otherwise>
</c:choose>
</body>
</html>