<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>

<div class="form">

    <h1>Вход</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/entry">
        <input type="text" required placeholder="login" name="login"><br>
        <input type="password" required placeholder="password" name="password"><br><br>
        <input class="button" type="submit" value="Login">
    </form>
</div>
</body>
</html>
