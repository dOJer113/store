package ru.techcoredev.store.web.servlets;

import ru.techcoredev.store.db.dbconnect.DBType;
import ru.techcoredev.store.db.dbmanagers.UserDBManager;
import ru.techcoredev.store.objects.builders.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPPages.LOGIN.getUrl()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        DBType dbType = DBType.valueOf(req.getServletContext().getAttribute("dbType").toString());
        User user = new UserDBManager(dbType).getUserEmailPassword(email, password);
        HttpSession session = req.getSession();
        if (user.getUserId() != 0) {
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            resp.sendRedirect(user.getRole().getUrl());
        } else {
            session.setAttribute("exception", "Пользователь не найден, попробуйте снова");
            req.getRequestDispatcher(JSPPages.LOGIN.getUrl()).forward(req, resp);
        }
    }
}
