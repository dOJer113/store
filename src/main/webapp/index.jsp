<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Меню</title>
</head>
<body>
<h2>Выберите действие</h2>
<form action="${pageContext.request.contextPath}/login" method="get">
    <input type="submit" value="Вход">
</form>
<br>
<form action="${pageContext.request.contextPath}/registration" method="get">
    <input type="submit" value="Регистрация">
</form>
<br>
<%--<form action="${pageContext.request.contextPath}/admin" method="get">
    <input type="submit" value="Секретная админская кнопка">
</form>--%>
<br>
</body>
</html>
