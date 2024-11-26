package ru.techcoredev.store.servlets;

import ru.techcoredev.store.dbconnect.DBType;
import ru.techcoredev.store.dbmanagers.ClientAndUserManager;

import javax.annotation.security.PermitAll;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
@PermitAll
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPPages.REGISTRATION.getUrl()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        if (new ClientAndUserManager(DBType.HIBERNATE_POSTGRES).addUserAndClient(email, password, name, surname, phone, address)) {
            resp.sendRedirect(req.getContextPath() + "/success");
        } else {
            req.getSession().setAttribute("exception", "Ошибка! Регистрация отклонена");
            this.doGet(req, resp);
        }


    }
}
