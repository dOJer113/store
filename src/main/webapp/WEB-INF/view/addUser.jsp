<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Resourcer resourcer = ProjectResourcer.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= resourcer.getString("registration.page.title") %></title>
    <script>
        function toggleFields() {
            const roleSelect = document.getElementById("role");
            const clientFields = document.getElementById("clientFields");

            if (roleSelect.value === "CLIENT") {
                clientFields.style.display = "block";
            } else {
                clientFields.style.display = "none";
            }
        }

        window.onload = function() {
            toggleFields(); // Вызываем функцию после загрузки страницы
        };
    </script>
</head>
<body>
<h2><%= resourcer.getString("registration.page.header") %></h2>
<form action="${pageContext.request.contextPath}/addUser" method="post">
    <label for="role"><%= resourcer.getString("registration.page.role.label") %>:</label>
    <select id="role" name="role" onchange="toggleFields()">
        <option value="ADMIN"><%= resourcer.getString("registration.page.role.admin") %></option>
        <option value="CLIENT"><%= resourcer.getString("registration.page.role.client") %></option>
    </select>
    <br><br>

    <label for="email"><%= resourcer.getString("registration.page.email.label") %>:</label>
    <input type="text" id="email" name="email" required><br><br>

    <label for="password"><%= resourcer.getString("registration.page.password.label") %>:</label>
    <input type="password" id="password" name="password" required><br><br>

    <div id="clientFields" style="display: none;">
        <label for="name"><%= resourcer.getString("registration.page.name.label") %>:</label>
        <input type="text" id="name" name="name"><br><br>

        <label for="surname"><%= resourcer.getString("registration.page.surname.label") %>:</label>
        <input type="text" id="surname" name="surname"><br><br>

        <label for="phone"><%= resourcer.getString("registration.page.phone.label") %>:</label>
        <input type="tel" id="phone" name="phone" pattern="\+7 \(\d{3}\) \d{3}-\d{2}-\d{2}">
        <small><%= resourcer.getString("registration.page.phone.format") %></small><br><br>

        <label for="address"><%= resourcer.getString("registration.page.address.label") %>:</label>
        <input type="text" id="address" name="address"><br><br>
    </div>

    <input type="submit" value="<%= resourcer.getString("registration.page.submit.button") %>">
</form>
</body>
</html>
