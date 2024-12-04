<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Resourcer resourcer = ProjectResourcer.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= resourcer.getString("user.info.page.title") %>
    </title>
</head>
<body>
<c:choose>
    <c:when test="${user.userId == 0}">
        <p style="color: red;"><%= resourcer.getString("user.info.page.error.notFound") %>
        </p>
    </c:when>
    <c:otherwise>
        <h2><%= resourcer.getString("user.info.page.header") %>
        </h2>
        <p><%= resourcer.getString("user.info.page.userId") %>: ${user.userId}</p>
        <p><%= resourcer.getString("user.info.page.email") %>: ${user.email}</p>
        <p><%= resourcer.getString("user.info.page.role") %>: ${user.role}</p>
        <p><%= resourcer.getString("user.info.page.password") %>: ${user.password}</p>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${not empty noOrders}">
        <p style="color: red;"><%= resourcer.getString("users.page.error.orders") %>: ${noUsers}</p>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <th><%= resourcer.getString("orders.page.table.number") %>
                </th>
                <th><%= resourcer.getString("orders.page.table.userid") %>
                </th>
                <th><%= resourcer.getString("orders.page.table.registrationdate") %>
                </th>
                <th><%= resourcer.getString("orders.page.table.closingdate") %>
                </th>
                <th><%= resourcer.getString("orders.page.table.status") %>
                </th>
            </tr>
            <jsp:useBean id="orders" scope="request" type="java.util.List"/>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.number}</td>
                    <td>${order.userId}</td>
                    <td>${order.registrationDate}</td>
                    <td>${order.closingDate}</td>
                    <td>${order.status}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
