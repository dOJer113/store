<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню администратора</title>

    <form action="${pageContext.request.contextPath}/users" method="get">
        <input type="submit" value="Показать всех пользователей">
    </form>
    <form action="${pageContext.request.contextPath}/id" method="get">
        <label for="id">Введите идентификатор для поиска пользователя:</label>
        <input type="number" id="id" name="id" required><br>
        <input type="submit" value="Поиск">
    </form>

    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Выход">
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
</head>
<body>

</body>
</html>
