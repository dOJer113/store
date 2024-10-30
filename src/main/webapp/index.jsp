<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<h2>Choose an action</h2>
<form action="${pageContext.request.contextPath}/users" method="get">
    <input type="submit" value="Show all clients">
</form>
<br>
<form action="${pageContext.request.contextPath}/id" method="get">
    <label for="id">Input id for search user:</label>
    <input type="number" id="id" name="id" required><br>
    <input type="submit" value="Search client by id">
</form>
<br>
</body>
</html>
