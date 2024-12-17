<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Resourcer resourcer = ProjectResourcer.getInstance();
    String exception = (String) session.getAttribute("exception");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= resourcer.getString("add.product.page.title") %>
    </title>
</head>
<body>
<h2><%= resourcer.getString("add.product.page.header") %>
</h2>
<form action="${pageContext.request.contextPath}/addProduct" method="post">
    <label for="name"><%= resourcer.getString("add.product.page.name.label") %>:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="description"><%= resourcer.getString("add.product.page.description.label") %>:</label>
    <textarea id="description" name="description" required></textarea><br><br>

    <label for="price"><%= resourcer.getString("add.product.page.price.label") %>:</label>
    <input type="number" id="price" name="price" step="0.01" required><br><br>

    <label for="remains"><%= resourcer.getString("add.product.page.remains.label") %>:</label>
    <input type="number" id="remains" name="remains" step="0.01" required><br><br>

    <input type="submit" value="<%= resourcer.getString("add.product.page.submit.button") %>">
    <% if (exception != null) { %>
    <p style="color: red;"><%= resourcer.getString("login.page.error") %>: <%= exception %>
    </p>
    <%
            session.removeAttribute("exception");
        }
    %>
</form>
</body>
</html>