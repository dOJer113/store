<%@ page import="ru.techcoredev.store.resourcer.Resourcer" %>
<%@ page import="ru.techcoredev.store.resourcer.ProjectResourcer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Resourcer resourcer = ProjectResourcer.getInstance();
    String exception = (String) session.getAttribute("exception");
%>
<html>
<head>
    <title><%= resourcer.getString("login.page.title") %>
    </title>
</head>
<body>

<div class="form">

    <h1><%= resourcer.getString("login.page.header") %>
    </h1><br>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <input type="text" required placeholder="<%= resourcer.getString("login.page.email.placeholder") %>"
               name="email"><br>
        <input type="password" required placeholder="<%= resourcer.getString("login.page.password.placeholder") %>"
               name="password"><br><br>
        <input class="button" type="submit" value="<%= resourcer.getString("login.page.button.submit") %>">
        <% if (exception != null) { %>
        <p style="color: red;"><%= resourcer.getString("login.page.error") %>: <%= exception %>
        </p>
        <%
                session.removeAttribute("exception");
            }
        %>
    </form>

</div>
</body>
</html>
