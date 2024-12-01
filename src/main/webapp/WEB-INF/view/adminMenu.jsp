<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Resourcer resourcer = ProjectResourcer.getInstance();
    String exception = (String) session.getAttribute("exception");
%>
<html>
<head>
    <title><%= resourcer.getString("admin.menu.title") %>
    </title>
</head>
<body>
<h2><%= resourcer.getString("admin.menu.title") %>
</h2>

<form action="${pageContext.request.contextPath}/users" method="get">
    <input type="submit" value="<%= resourcer.getString("admin.menu.show.users") %>">
</form>
<form action="${pageContext.request.contextPath}/id" method="get">
    <label for="id"><%= resourcer.getString("admin.menu.enter.id") %>:</label>
    <input type="number" id="id" name="id" required><br>
    <input type="submit" value="<%= resourcer.getString("admin.menu.search") %>">
</form>
<form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="<%= resourcer.getString("admin.menu.logout") %>">
</form>

<% if (exception != null) { %>
<p style="color: red;"><%= resourcer.getString("admin.menu.exception") %>: <%= exception %>
</p>
<%
        session.removeAttribute("exception");
    }
%>
</body>
</html>
