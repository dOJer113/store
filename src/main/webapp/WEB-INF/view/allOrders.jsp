<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Resourcer resourcer = ProjectResourcer.getInstance();
%>
<html>
<head>
    <title><%= resourcer.getString("orders.page.title") %>
    </title>
</head>
<body>
<h2><%= resourcer.getString("orders.page.title") %>
</h2>
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
        <th><%= resourcer.getString("orders.page.table.action") %>
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
            <td>
                <form action="${pageContext.request.contextPath}/allOrders" method="post">
                    <input type="hidden" name="orderNumber" value="${order.number}"/>
                    <input type="submit" value="<%= resourcer.getString("orders.page.table.action.details") %>"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="${pageContext.request.contextPath}/ordersRemove" method="post">
    <label for="deleteDate">
        <%= resourcer.getString("orders.page.delete.label") %>
    </label>
    <input type="date" id="deleteDate" name="deleteDate" required/>
    <input type="submit" value="<%= resourcer.getString("orders.page.delete.button") %>"/>
</form>

</body>
</html>