<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Resourcer resourcer = ProjectResourcer.getInstance();
%>
<html>
<head>
    <title><%= resourcer.getString("users.page.title") %></title>
</head>
<body>
<h2><%= resourcer.getString("users.page.header") %></h2>
<c:choose>
    <c:when test="${not empty noUsers}">
        <p style="color: red;"><%= resourcer.getString("users.page.error.noUsers") %>: ${noUsers}</p>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <th><%= resourcer.getString("users.page.table.id") %></th>
                <th><%= resourcer.getString("users.page.table.role") %></th>
                <th><%= resourcer.getString("users.page.table.email") %></th>
                <th><%= resourcer.getString("users.page.table.password") %></th>
            </tr>
            <jsp:useBean id="users" scope="request" type="java.util.List"/>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.role}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
