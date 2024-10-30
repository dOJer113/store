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

@WebServlet("/id")
public class ServletId extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = UserDBManager.getUsers().stream().filter(x -> x.getUserId() == id).findFirst().orElse(new User());
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>User searched by id</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>User:</h1>");
            if (user.getUserId() != 0) {
                writer.println(user);
            } else {
                writer.println("No user with this id in DB");
            }
            writer.println("</body>");
            writer.println("</html>");
        }
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
