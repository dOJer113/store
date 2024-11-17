package ru.techcoredev.store.servlets;

import ru.techcoredev.store.dbmanagers.UserDBManager;
import ru.techcoredev.store.objects.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/id")
public class IdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            User user = UserDBManager.getUsers().stream().filter(x -> x.getUserId() == id).findFirst().orElse(new User());
            req.setAttribute("user", user);
            req.getRequestDispatcher(JSPPages.USER_BY_ID.getUrl()).forward(req, resp);
        } catch (NullPointerException | NumberFormatException e) {
            req.getSession().setAttribute("exception", "Ошибка! Id пользователя введён неверно!");
            req.getRequestDispatcher(JSPPages.ADMIN.getUrl()).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
