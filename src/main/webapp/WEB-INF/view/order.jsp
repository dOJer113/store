<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Resourcer resourcer = ProjectResourcer.getInstance();
%>
<html>
<head>
    <title><%= resourcer.getString("order.details.page.title") %></title>
</head>
<body>
<h2><%= resourcer.getString("order.details.page.header") %></h2>
<jsp:useBean id="orderDetails" scope="request" type="ru.techcoredev.store.objects.OrderDetails"/>
<p><%= resourcer.getString("order.details.page.order.number") %>: ${orderDetails.number}</p>
<p><%= resourcer.getString("order.details.page.registration.date") %>: ${orderDetails.registrationDate}</p>
<p><%= resourcer.getString("order.details.page.closing.date") %>: ${orderDetails.closingDate}</p>
<p><%= resourcer.getString("order.details.page.status") %>: ${orderDetails.status}</p>
<p><%= resourcer.getString("order.details.page.client.name") %>: ${orderDetails.clientName}</p>
<p><%= resourcer.getString("order.details.page.client.surname") %>: ${orderDetails.clientSurname}</p>
<p><%= resourcer.getString("order.details.page.client.phone.number") %>: ${orderDetails.clientPhoneNumber}</p>
<p><%= resourcer.getString("order.details.page.client.address") %>: ${orderDetails.clientAddress}</p>

<h3><%= resourcer.getString("order.details.page.products.header") %></h3>
<table border="1">
    <tr>
        <th><%= resourcer.getString("order.details.page.product.name") %></th>
        <th><%= resourcer.getString("order.details.page.product.description") %></th>
        <th><%= resourcer.getString("order.details.page.product.price") %></th>
        <th><%= resourcer.getString("order.details.page.product.remains") %></th>
    </tr>
    <jsp:useBean id="products" scope="request" type="java.util.List<ru.techcoredev.store.objects.Product>"/>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.remains}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>