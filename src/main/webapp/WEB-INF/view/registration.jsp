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
    <title><%= resourcer.getString("registration.page.title") %>
    </title>
</head>
<body>
<h2><%= resourcer.getString("registration.page.header") %>
</h2>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="email"><%= resourcer.getString("registration.page.email.label") %>:</label>
    <input type="text" id="email" name="email" required><br><br>

    <label for="password"><%= resourcer.getString("registration.page.password.label") %>:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="name"><%= resourcer.getString("registration.page.name.label") %>:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="surname"><%= resourcer.getString("registration.page.surname.label") %>:</label>
    <input type="text" id="surname" name="surname" required><br><br>

    <label for="phone"><%= resourcer.getString("registration.page.phone.label") %>:</label>
    <input type="tel" id="phone" name="phone" pattern="\+7 \(\d{3}\) \d{3}-\d{2}-\d{2}" required>
    <small><%= resourcer.getString("registration.page.phone.format") %>
    </small><br><br>

    <label for="address"><%= resourcer.getString("registration.page.address.label") %>:</label>
    <input type="text" id="address" name="address" required><br><br>

    <input type="submit" value="<%= resourcer.getString("registration.page.submit.button") %>">
</form>
<% if (exception != null) { %>
<p style="color: red;"><%= resourcer.getString("registration.page.error") %>: <%= exception %>
</p>
<%
        session.removeAttribute("exception");
    }
%>
</body>
</html>
