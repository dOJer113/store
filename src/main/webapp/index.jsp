<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%
    String locale = (String) session.getAttribute("locale");
    if (locale == null) {
        locale = "ru";
    }
    Resourcer resourcer = ProjectResourcer.getInstance();
%>
<head>
    <title><%= resourcer.getString("menu.title") %>
    </title>
</head>
<body>
<h2><%= resourcer.getString("menu.choose.action") %>
</h2>

<form action="${pageContext.request.contextPath}/login" method="get">
    <input type="submit" value="<%= resourcer.getString("menu.login") %>">
</form>
<br>
<form action="${pageContext.request.contextPath}/registration" method="get">
    <input type="submit" value="<%= resourcer.getString("menu.registration") %>">
</form>
<br>
<form action="${pageContext.request.contextPath}/locale" method="post">
    <label for="language"><%= resourcer.getString("menu.select.language") %>:</label>
    <select name="language" id="language">
        <option value="ru" <%= "ru".equals(locale) ? "selected" : "" %>>Русский</option>
        <option value="en" <%= "en".equals(locale) ? "selected" : "" %>>English</option>
    </select>
    <button type="submit"><%= resourcer.getString("menu.set.language") %>
    </button>
</form>
</body>
</html>
