<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>

<div class="form">

    <h1>Вход</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <input type="text" required placeholder="Email: " name="email"><br>
        <input type="password" required placeholder="Пароль:" name="password"><br><br>
        <input class="button" type="submit" value="Войти">
        <%
            String exception = (String) session.getAttribute("exception");
            if (exception != null) {
        %>
        <p style="color: red;">${exception}</p>
        <%
                session.removeAttribute("exception");
            }
        %>
    </form>

</div>
</body>
</html>
