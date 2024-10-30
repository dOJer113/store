package ru.techcoredev.store.servlets;


import ru.techcoredev.store.dbmanagers.UserDBManager;
import ru.techcoredev.store.objects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class ServletAll extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        List<User> userList = UserDBManager.getUsers();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Users List</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>Users List</h1>");
            writer.println("<ul>");
            if (userList.size() >= 1) {
                for (User user : userList) {
                    writer.println(user.toString());
                    writer.println("<br>");
                }
            } else {
                writer.println("No users!");
            }
            writer.println("</ul>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }

}
