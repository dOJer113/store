<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Resourcer resourcer = ProjectResourcer.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= resourcer.getString("success.page.title") %>
    </title>
</head>
<body>
<h1><%= resourcer.getString("success.page.header") %>
</h1>

<a href="index.jsp"><%= resourcer.getString("success.page.return.link") %>
</a>

</body>
</html>
