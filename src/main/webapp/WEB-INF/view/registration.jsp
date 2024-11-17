<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Регистрация пользователя</title>
</head>
<body>
<h2>Регистрация нового пользователя</h2>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="email">Введите электронную почту:</label>
    <input type="text" id="email" name="email" required><br><br>

    <label for="password">Введите пароль:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="name">Введите имя:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="surname">Введите фамилию:</label>
    <input type="text" id="surname" name="surname" required><br><br>

    <label for="phone">Номер телефона:</label>
    <input type="tel" id="phone" name="phone" pattern="\+7 \(\d{3}\) \d{3}-\d{2}-\d{2}" required>
    <small>Формат: +7 (000) 000-00-00</small><br><br>

    <label for="address">Введите свой домашний адрес:</label>
    <input type="text" id="address" name="address" required><br><br>

    <input type="submit" value="Регистрация">
</form>
<%
    String exception = (String) session.getAttribute("exception");
    if (exception != null) {
%>
<p style="color: red;">${exception}</p>
<%
        session.removeAttribute("exception");
    }
%>
</body>
</html>
